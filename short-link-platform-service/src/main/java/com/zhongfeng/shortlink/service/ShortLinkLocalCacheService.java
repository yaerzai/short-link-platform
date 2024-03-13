package com.zhongfeng.shortlink.service;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.zhongfeng.common.db.model.PageData;
import com.zhongfeng.common.redis.service.RedisCacheService;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkCustomerConfigDataService;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkInfoDataService;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCustomerConfigDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCustomerConfigQueryBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkInfoDataBO;
import com.zhongfeng.shortlink.persistence.enums.LinkTypeEnum;
import com.zhongfeng.shortlink.service.bo.ShortLinkConfigCacheBO;
import com.zhongfeng.shortlink.service.bo.ShortLinkLocalCache;
import com.zhongfeng.shortlink.utils.SysTimeUtils;
import com.zhongfeng.sms.persistence.enums.SysParamEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 短短链本地缓存服务
 */
@Service
@Slf4j
public class ShortLinkLocalCacheService {
    @Autowired
    private ShortLinkCustomerConfigDataService shortLinkCustomerConfigDataService;
    @Autowired
    private ShortLinkInfoDataService shortLinkInfoDataService;
    @Autowired
    private DataService dataService;
    @Resource
    private RedisCacheService redisCacheService;
    @Value("${server.nodeId}")
    private String nodeId;

    private final static String SYNC_ADD_REDIS_KEY = "ShortLinkCacheSyncAdd";
    private final static String SYNC_DEL_REDIS_KEY = "ShortLinkCacheSyncDel";

    private final static String SYNC_INFO_ADD_REDIS_KEY = "ShortLinkInfoCacheSyncAdd";
    /**
     * 同步添加
     * @param linkNo
     */
    public void syncAdd(String linkNo) {
        syncAdd(ListUtil.of(linkNo));
    }

    /**
     * 同步添加
     * @param linkNos
     */
    public void syncAdd(List<String> linkNos) {
        int shortLinkCacheSynCount = dataService.getSysParamInt(SysParamEnum.SHORTLINK_CACHE_SYN_COUNT);
        for (int i = 0; i < shortLinkCacheSynCount; i++) {
            String key = SYNC_ADD_REDIS_KEY + ":" + (i + 1);
            redisCacheService.lPush(key, linkNos);
            log.warn("[本地缓存] 同步添加本地缓存, key:{}, val: {}", key, JSONUtil.toJsonStr(linkNos));
        }
    }

    public void syncAddInfo(List<String> linkInfo) {
        int shortLinkCacheSynCount = dataService.getSysParamInt(SysParamEnum.SHORTLINK_CACHE_SYN_COUNT);
        for (int i = 0; i < shortLinkCacheSynCount; i++) {
            String key = SYNC_INFO_ADD_REDIS_KEY + ":" + (i + 1);
            redisCacheService.lPush(key, linkInfo);
            log.warn("[本地缓存] 同步添加本地缓存info, key:{}, val: {}", key, JSONUtil.toJsonStr(linkInfo));
        }
    }

    /**
     * 同步删除
     * @param keys
     */
    public void syncDel(String keys) {
        syncDel(ListUtil.of(keys));
    }

    /**
     * 同步删除
     * @param keys
     */
    public void syncDel(List<String> keys) {
        int shortLinkCacheSynCount = dataService.getSysParamInt(SysParamEnum.SHORTLINK_CACHE_SYN_COUNT);
        for (int i = 0; i < shortLinkCacheSynCount; i++) {
            String key = SYNC_DEL_REDIS_KEY + ":" + (i + 1);
            redisCacheService.lPush(key, keys);
            String desc = keys.size() > 10 ? (keys.size() + "个") : JSONUtil.toJsonStr(keys);
            log.warn("[本地缓存] 同步删除本地缓存, key:{}, val: {}", key, desc);
        }
    }

    /**
     * 同步新增
     */
    @Scheduled(fixedDelay = 5000)
    public void syncAddExec() {
        while (true) {
            List<String> linkNos = (List<String>) redisCacheService.lPop(SYNC_ADD_REDIS_KEY + ":" + nodeId, List.class);
            if (linkNos == null) {
                break;
            }
            String desc = linkNos.size() > 10 ? (linkNos.size() + "个") : JSONUtil.toJsonStr(linkNos);
            log.warn("[本地缓存] 收到同步新增, linkNos: {}", desc);
            add(linkNos);
        }
    }

    /**
     * 同步新增
     */
    @Scheduled(fixedDelay = 5000)
    public void syncInfoAddExec() {
        while (true) {
            List<String> linkInfoList = (List<String>) redisCacheService.lPop(SYNC_INFO_ADD_REDIS_KEY + ":" + nodeId, List.class);
            if (linkInfoList == null) {
                break;
            }
            String desc = linkInfoList.size() > 10 ? (linkInfoList.size() + "个") : JSONUtil.toJsonStr(linkInfoList);
            log.warn("[本地缓存] 收到同步新增info, linkInfoList: {}", desc);
            for (String linkInfo : linkInfoList) {
                ShortLinkInfoDataBO shortLinkInfoDataBO = ShortLinkInfoDataBO.buildByInfoCacheStr(linkInfo);
                ShortLinkLocalCache.put(shortLinkInfoDataBO);
            }
        }
    }

    /**
     * 同步删除
     */
    @Scheduled(fixedDelay = 5000)
    public void syncDelExec() {
        while (true) {
            List<String> keys = (List<String>) redisCacheService.lPop("ShortLinkCacheSyncDel:" + nodeId, List.class);
            if (keys == null) {
                break;
            }
            remove(keys);
        }
    }

    /**
     * 清理本地缓存
     */
    @Scheduled(cron = "0 35 0 * * ?")
    public void clear() {
        int shortlinkCacheDays = dataService.getSysParamInt(SysParamEnum.SHORTLINK_CACHE_DAYS);
        AtomicInteger count = new AtomicInteger();
        long startTime = System.currentTimeMillis();
        {
            List<String> clearKeyList = new ArrayList<>();
            for (Map.Entry<String, ShortLinkConfigCacheBO> entry : ShortLinkLocalCache.configMap.entrySet()) {
                DateTime cacheTime = entry.getValue().getCacheTime();
                if (cacheTime.offsetNew(DateField.DAY_OF_MONTH, shortlinkCacheDays).isBefore(DateUtil.date())) {
                    clearKeyList.add(entry.getKey());
                }
            }
            for (String key : clearKeyList) {
                ShortLinkLocalCache.configMap.remove(key);
            }
            count.addAndGet(clearKeyList.size());
        }
        log.warn("[本地缓存] 清理config本地缓存, 数量: {}, 耗时: {}", count.getAndSet(0), SysTimeUtils.showTimeNow(startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < ShortLinkLocalCache.MAP_SIZE; i++) {
            Map<String, String> shortLinkInfoCacheMap = ShortLinkLocalCache.infoMap[i];
            List<String> clearKeyList = new ArrayList<>();
            for (Map.Entry<String, String> entry : shortLinkInfoCacheMap.entrySet()) {
                DateTime cacheTime = ShortLinkLocalCache.getCacheTime(entry.getValue());
                if (cacheTime.offsetNew(DateField.DAY_OF_MONTH, shortlinkCacheDays).isBefore(DateUtil.date())) {
                    clearKeyList.add(entry.getKey());
                }
            }
            for (String key : clearKeyList) {
                shortLinkInfoCacheMap.remove(key);
            }
            count.addAndGet(clearKeyList.size());
        }
        log.warn("[本地缓存] 清理info本地缓存, 数量: {}, 耗时: {}", count.get(), SysTimeUtils.showTimeNow(startTime));
    }

    //添加本地缓存
    private void add(List<String> linkNos) {
        ShortLinkCustomerConfigQueryBO queryBO = new ShortLinkCustomerConfigQueryBO();
        queryBO.setLinkNos(linkNos);
        PageData<ShortLinkCustomerConfigDataBO> page = shortLinkCustomerConfigDataService.query(queryBO);
        if (CollUtil.isEmpty(page.getData())) {
            log.warn("[本地缓存] 添加本地缓存失败, 配置查询为空: {}", linkNos);
            return;
        }
        for (ShortLinkCustomerConfigDataBO dataBO : page.getData()) {
            if (LinkTypeEnum.SINGLE.eq(dataBO.getLinkType())) {
                ShortLinkLocalCache.put(dataBO.getShortLinkAddress(), dataBO);
                log.warn("[本地缓存] 添加config单条缓存, linkNo: {}, shortLinkNo: {}", dataBO.getLinkNo(), dataBO.getShortLinkAddress());
            } else {
                ShortLinkCustomerConfigDataBO oldConfig = ShortLinkLocalCache.getConfig(dataBO.getLinkNo());
                ShortLinkLocalCache.put(dataBO.getLinkNo(), dataBO);
                if (oldConfig != null) {
                    log.warn("[本地缓存] 短链配置缓存存在,不更新短链信息: {}", dataBO.getLinkNo());
                    return;
                }
                add(dataBO);
            }
        }
    }

    //添加本地缓存
    private void add(ShortLinkCustomerConfigDataBO config) {
        int pageSize = dataService.getSysParamInt(SysParamEnum.ONLINE_EXPORT_PAGE_SIZE);
        String linkNo = config.getLinkNo();
        long startTime = System.currentTimeMillis();
        int count = shortLinkInfoDataService.each(linkNo, config.getTableTime(), pageSize, ShortLinkLocalCache::put);
        log.warn("[本地缓存] 添加config本地缓存, LinkNo:{}, 缓存数量: {}, 耗时: {}", linkNo, count, SysTimeUtils.showTimeNow(startTime));
    }

    //移出
    private void remove(List<String> keys) {
        if (CollUtil.isEmpty(keys)) {
            return;
        }
        if (keys.size() == 1) {
            ShortLinkLocalCache.removeConfig(keys.get(0));
            ShortLinkLocalCache.removeInfo(keys.get(0));
            log.warn("[本地缓存] 短链配置缓存删除, key: {}", keys.get(0));
        } else if (keys.size() > 1) {
            for (String key : keys) {
                ShortLinkLocalCache.removeInfo(key);
            }
            log.warn("[本地缓存] 短链信息缓存删除, 数量: {}", keys.size());
        }
    }
}
