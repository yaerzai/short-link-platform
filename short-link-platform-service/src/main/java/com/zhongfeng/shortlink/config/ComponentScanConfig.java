package com.zhongfeng.shortlink.config;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author hu.zhiyu
 * 2019/1/18 16:09
 */
@ImportAutoConfiguration(value = {com.zhongfeng.common.redis.config.RedisClientConfig.class
        , com.zhongfeng.common.db.config.DbConfig.class})
@Configuration
@ComponentScan({"com.zhongfeng.common", "com.zhongfeng.sms.persistence.dataservice"})
public class ComponentScanConfig {

}

