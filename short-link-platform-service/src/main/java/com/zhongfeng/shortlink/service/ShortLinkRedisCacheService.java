package com.zhongfeng.shortlink.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.zhongfeng.common.redis.service.JsonRedisCacheService;
import com.zhongfeng.common.redis.service.StringRedisCacheService;
import com.zhongfeng.shortlink.constant.SysConstant;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkInfoDataService;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCustomerConfigDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkInfoDataBO;
import com.zhongfeng.shortlink.persistence.enums.LinkTypeEnum;
import com.zhongfeng.shortlink.utils.BaseTo62BaseUtils;
import com.zhongfeng.shortlink.utils.SysTimeUtils;
import com.zhongfeng.sms.persistence.enums.SysParamEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 短链redis缓存服务
 */
@Service
@Slf4j
public class ShortLinkRedisCacheService {

    @Resource
    private JsonRedisCacheService jsonRedisCacheService;
    @Resource
    private StringRedisCacheService stringRedisCacheService;
    @Resource
    private DataService dataService;
    @Resource
    private ShortLinkInfoDataService shortLinkInfoDataService;
    @Resource
    private ShortLinkLocalCacheService shortLinkLocalCacheService;

    private final static String SHORT_LINK_CONFIG_KEY = "ShortLinkConfig";
    private final static String SHORT_LINK_INFO_KEY = "ShortLinkInfo";

    /**
     * 获取短链配置
     * @param linkNo
     * @return
     */
    public ShortLinkCustomerConfigDataBO getConfig(String linkNo) {
        Map configMap = jsonRedisCacheService.get(SHORT_LINK_CONFIG_KEY + ":" + linkNo, Map.class);
        if (configMap == null) {
            return null;
        }
        return BeanUtil.toBean(configMap, ShortLinkCustomerConfigDataBO.class);
    }

    /**
     * 获取短链信息
     * @param shortLinkNo
     * @return
     */
    public ShortLinkInfoDataBO getInfo(String shortLinkNo) {
        long num = BaseTo62BaseUtils.base62Decode(shortLinkNo);
        long interval = num / 10_0000; //每10万1个key
        String cacheStr = stringRedisCacheService.getHash(SHORT_LINK_INFO_KEY + ":" + interval, shortLinkNo, String.class);
        if (cacheStr == null) {
            return null;
        }
        return ShortLinkInfoDataBO.buildByRedisStr(cacheStr);
    }

    /**
     * 添加缓存
     * @param key
     * @param dataBO
     */
    public void add(String key, ShortLinkCustomerConfigDataBO dataBO) {
        DateTime expirationTime = DateUtil.date(dataBO.getExpirationTime());
        DateTime currTime = DateUtil.date();
        if (expirationTime.isBefore(currTime)) {
            log.error("[Redis缓存] 当前时间[{}]已超过有效时间[{}]", currTime, expirationTime);
//            return;
        }
        long between = currTime.between(expirationTime, DateUnit.SECOND);
        String keyStr = SHORT_LINK_CONFIG_KEY + ":" + key;
        jsonRedisCacheService.set(keyStr, dataBO, (int)between);
        log.warn("[Redis缓存] 添加RedisConfig缓存: {}", keyStr);
    }

    /**
     * 添加缓存
     * @param dataBO
     */
    public void add(ShortLinkInfoDataBO dataBO) {
        String shortLinkNo = dataBO.getShortLinkNo();
        long num = BaseTo62BaseUtils.base62Decode(shortLinkNo);
        long interval = num / 10_0000; //每10万1个key
        String redisStr = dataBO.toRedisStr();
        log.warn("[Redis缓存] Redis缓存存入短链信息{} ", redisStr);
        stringRedisCacheService.setHash(SHORT_LINK_INFO_KEY + ":" + interval, shortLinkNo, redisStr);
    }

    /**
     * 添加缓存
     * @param list
     */
    public void add(List<ShortLinkInfoDataBO> list) {
        list.forEach(this::add);
    }

    /**
     * 移出缓存
     * @param key
     */
    public void removeConfig(String key) {
        String keyStr = SHORT_LINK_CONFIG_KEY + ":" + key;
        jsonRedisCacheService.remove(keyStr);
        log.warn("[Redis缓存] 移出RedisConfig缓存: {}", keyStr);
    }

    /**
     * 移出缓存
     * @param key
     */
    public void removeInfo(String key) {
        long num = BaseTo62BaseUtils.base62Decode(key);
        long interval = num / 10_0000; //每10万1个key
        jsonRedisCacheService.delHash(SHORT_LINK_INFO_KEY + ":" + interval, key);
    }

    /**
     * 移出缓存
     * @param dataBO
     */
    public void remove(ShortLinkCustomerConfigDataBO dataBO) {
        int pageSize = dataService.getSysParamInt(SysParamEnum.ONLINE_EXPORT_PAGE_SIZE);
        if (LinkTypeEnum.SINGLE.eq(dataBO.getLinkType())) {
            removeConfig(dataBO.getShortLinkAddress());
            shortLinkLocalCacheService.syncDel(dataBO.getShortLinkAddress());
            return;
        }
        String linkNo = dataBO.getLinkNo();
        removeConfig(linkNo);
        shortLinkLocalCacheService.syncDel(linkNo);

        long startTime = System.currentTimeMillis();
        List<String> infoList = new ArrayList<>();
        int count = shortLinkInfoDataService.each(linkNo, dataBO.getTableTime(), pageSize, infoData-> {
            String shortLinkNo = infoData.getShortLinkNo();
            removeInfo(shortLinkNo);
            removeLocal(infoList, shortLinkNo);
        });
        if (CollUtil.isNotEmpty(infoList)) {
            removeLocal(infoList);
        }
        log.warn("[Redis缓存] 移出Redis缓存, LinkNo:{}, 缓存数量: {}, 耗时: {}", dataBO.getLinkNo(), count, SysTimeUtils.showTimeNow(startTime));
    }

    /**
     * 移出缓存
     * @param configList
     */
    public void remove(List<ShortLinkCustomerConfigDataBO> configList) {
        for (ShortLinkCustomerConfigDataBO dataBO : configList) {
            SysConstant.redisSyncExecutors.execute(() -> remove(dataBO));
        }
    }

    /**
     * 移出缓存
     * @param dataBO
     */
    public void add(ShortLinkCustomerConfigDataBO dataBO) {
        int pageSize = dataService.getSysParamInt(SysParamEnum.ONLINE_EXPORT_PAGE_SIZE);
        if (LinkTypeEnum.SINGLE.eq(dataBO.getLinkType())) {
            add(dataBO.getShortLinkAddress(), dataBO);
            return;
        }
        String linkNo = dataBO.getLinkNo();
        add(linkNo, dataBO);

        long startTime = System.currentTimeMillis();
        int count = shortLinkInfoDataService.each(linkNo, dataBO.getTableTime(), pageSize, this::add);
        log.warn("[Redis缓存] 添加Redis缓存, LinkNo:{}, 缓存数量: {}, 耗时: {}", dataBO.getLinkNo(), count, SysTimeUtils.showTimeNow(startTime));
    }

    //删除本地缓存
    private void removeLocal(List<String> infoList) {
        shortLinkLocalCacheService.syncDel(infoList);
    }

    //添加本地缓存
    private void removeLocal(List<String> infoList, String shortLinkNo) {
        infoList.add(shortLinkNo);
        if (infoList.size() >= 1000) {
            shortLinkLocalCacheService.syncDel(infoList);
            infoList.clear();
        }
    }

}
