package com.zhongfeng.shortlink.service.bo;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCustomerConfigDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkInfoDataBO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ShortLinkLocalCache {

    public static final int MAP_SIZE = 1000;

    public static Map<String, ShortLinkConfigCacheBO> configMap = new ConcurrentHashMap<>();
    public static Map<String, String>[] infoMap = new Map[MAP_SIZE];
    static {
        for (int i = 0; i < MAP_SIZE; i++) {
            infoMap[i] = new ConcurrentHashMap<>();
        }
    }
    //添加本地缓存
    public static void put(String key, ShortLinkCustomerConfigDataBO dataBO) {
        configMap.put(key, new ShortLinkConfigCacheBO(dataBO));
    }
    //添加本地缓存
    public static void put(ShortLinkInfoDataBO dataBO) {
        String key = dataBO.getShortLinkNo();
        int index = Math.abs(key.hashCode() % MAP_SIZE);
        infoMap[index].put(key, dataBO.toCahceStr());
    }
    //移出本地缓存
    public static void removeConfig(String key) {
        configMap.remove(key);
    }
    //移出本地缓存
    public static void removeInfo(String key) {
        int index = Math.abs(key.hashCode() % MAP_SIZE);
        infoMap[index].remove(key);
    }
    //获取短链配置
    public static ShortLinkCustomerConfigDataBO getConfig(String key) {
        ShortLinkConfigCacheBO cacheBO = configMap.get(key);
        if (cacheBO == null) {
            return null;
        }
        return cacheBO.getShortLinkConfig();
    }
    //获取短链信息
    public static ShortLinkInfoDataBO getInfo(String key) {
        int index = Math.abs(key.hashCode() % MAP_SIZE);
        String cacheStr = infoMap[index].get(key);
        if (StrUtil.isEmpty(cacheStr)) {
            return null;
        }
        return ShortLinkInfoDataBO.buildByCacheStr(cacheStr);
    }

    public static DateTime getCacheTime(String cacheStr) {
        long time = Long.parseLong(StrUtil.subBefore(cacheStr, "|", false)) * 1000;
        return DateUtil.date(time);
    }



}
