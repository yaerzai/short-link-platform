package com.zhongfeng.shortlink.service.impl;

import com.google.common.collect.Maps;
import com.zhongfeng.common.base.exception.BusinessException;
import com.zhongfeng.common.base.service.bo.LocalCacheBO;
import com.zhongfeng.common.base.utils.DateUtils;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkBatchInfoDataService;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkCustomerConfigDataService;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkInfoDataService;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkBatchInfoDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCertificateDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCustomerConfigDataBO;
import com.zhongfeng.shortlink.service.DataService;
import com.zhongfeng.sms.persistence.dataservice.SysIpBlackDataService;
import com.zhongfeng.sms.persistence.dataservice.SysParamsDataService;
import com.zhongfeng.sms.persistence.enums.SysParamEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @author yuzc
 * @date 2020/2/26 18:42
 */
@Service
@Slf4j
public class DataServiceImpl implements DataService {
    @Resource
    private ShortLinkInfoDataService shortLinkInfoDataService;
    @Resource
    private ShortLinkCustomerConfigDataService customerConfigDataService;
    @Resource
    private ShortLinkBatchInfoDataService batchInfoDataService;
    @Resource
    private SysParamsDataService sysParamsDataService;
    @Resource
    private SysIpBlackDataService sysIpBlackDataService;

    /**
     * 通用缓存
     */
    private Map<String, Map<String, LocalCacheBO<Object>>> commonCacheMap = new ConcurrentHashMap<>();

    @Override
    public int getSysParamInt(SysParamEnum sysParamEnum) {
        String paramValue = getSysParam(sysParamEnum);
        try {
            return Integer.valueOf(paramValue);
        } catch (NumberFormatException e) {
            log.error("系统参数配置错误,非法数字 {}", sysParamEnum);
            throw new BusinessException("系统参数配置错误,非法数字");
        }
    }

    private Object getCacheBO(String commonCacheKey, String cacheName, Supplier<Object> newCacheBO, BiFunction<Long, Long, Boolean> compare) {
        Map<String, LocalCacheBO<Object>> cacheMap = commonCacheMap.computeIfAbsent(commonCacheKey, key -> new ConcurrentHashMap<>());
        LocalCacheBO<Object> localCacheBO = cacheMap.computeIfAbsent(cacheName, key -> new LocalCacheBO(newCacheBO.get(), new AtomicLong((System.currentTimeMillis()))));
        synchronized (localCacheBO) {
            if (!compare.apply(System.currentTimeMillis(), localCacheBO.getLastLoadTime().get())) {
                localCacheBO.setDataBO(newCacheBO.get());
                localCacheBO.getLastLoadTime().getAndSet(System.currentTimeMillis());
                log.info("[二级缓存] 重载信息 {} {}", commonCacheKey, cacheName);
            }
            return localCacheBO.getDataBO();
        }
    }

    @Override
    public String getSysParam(SysParamEnum sysParamEnum) {
        String paramName = sysParamEnum.getParamName();
        return (String) getCacheBO(
                "SysParamInfo", paramName,
                () -> sysParamsDataService.get(paramName, sysParamEnum.getDefaultValue()),
                DateUtils::isSameMinute
        );
    }

    @Override
    public Boolean ipStatus(String ipAddr) {
        return (Boolean) getCacheBO(
                "IpStatus", ipAddr,
                () -> sysIpBlackDataService.ipStatus(ipAddr),
                DateUtils::isSameMinute
        );
    }

//    @Override
//    public ShortLinkInfoDataBO getShortLinkInfo(String shortLinkNo) {
//        return (ShortLinkInfoDataBO) getCacheBO(
//                "ShortLinkInfo", shortLinkNo,
//                () -> shortLinkInfoDataService.get(shortLinkNo),
//                DateUtils::isSameMinute
//        );
//    }

    @Override
    public ShortLinkCustomerConfigDataBO getCustomerConfigInfo(String linkNo) {
        return (ShortLinkCustomerConfigDataBO) getCacheBO(
                "ShortLinkCustomerConfig", linkNo,
                () -> customerConfigDataService.get(linkNo),
                DateUtils::isSameMinute
        );
    }

    @Override
    public ShortLinkCustomerConfigDataBO getCustomerConfigInfoByAddress(String address) {
        return (ShortLinkCustomerConfigDataBO) getCacheBO(
                "ShortLinkCustomerConfigAdress", address,
                () -> customerConfigDataService.getByShortAddress(address),
                DateUtils::isSameMinute
        );
    }

    @Override
    public ShortLinkBatchInfoDataBO getShortLinkBatchInfoDataBO(String linkNo) {
        return (ShortLinkBatchInfoDataBO) getCacheBO(
                "ShortLinkBatchInfo", linkNo,
                () -> batchInfoDataService.get(linkNo),
                DateUtils::isSameMinute
        );
    }

    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void clearLocalCache() {
        log.warn("[ClearLocalCache] 执行");
        long betweenTime = 60 * 60 * 1000;

        List<Map<String, LocalCacheBO<Object>>> commonCacheList = new ArrayList<>(commonCacheMap.values());
        for (Map<String, LocalCacheBO<Object>> cacheMap : commonCacheList) {
            List<String> cacheKeyList = new ArrayList<>(cacheMap.keySet());
            cacheKeyList.forEach(cacheKey -> {
                LocalCacheBO<Object> cacheObj = cacheMap.get(cacheKey);
                if (DateUtils.getCurrentTimeMillis() - cacheObj.getLastLoadTime().get() >= betweenTime) {
                    log.warn("[ChannelInfoMap] remove local cache key:{}", cacheKey);
                    cacheMap.remove(cacheKey);
                }
            });
        }
    }

    @Override
    public ShortLinkCertificateDataBO getShorLinkCertificate() {
        return null;
    }

    /**
     * 获取所有手机品牌
     * <br/>
     * key=品牌标识 value=品牌名称
     */
    @Override
    public Map<Integer, String> getAllBrand() {
        final Map<Integer, String> r = Maps.newHashMap();
        String brandStr = this.getSysParam(SysParamEnum.MODEL_TO_BRAND);
        if (StringUtils.isBlank(brandStr)) {
            return r;
        }
        // ["iphone:1","huawei:2","oppo:3","vivo:4","mi:5"]
        String[] brands = StringUtils.split(brandStr, ",");
        if (ArrayUtils.isEmpty(brands)) {
            return r;
        }

        for (final String brand : brands) {
            String[] b = StringUtils.split(brand, ":");
            r.put(Integer.valueOf(b[1]), b[0]);
        }
        return r;
    }
}
