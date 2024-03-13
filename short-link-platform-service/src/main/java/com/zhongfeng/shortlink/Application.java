package com.zhongfeng.shortlink;

import cn.hutool.core.collection.ListUtil;
import com.zhongan.wx.corp.push.config.WxCorpMsgConfig;
import com.zhongan.wx.corp.push.util.WxCorpMsgUtils;
import io.shardingsphere.jdbc.spring.boot.SpringBootConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author yuzc
 */
@SpringBootApplication(exclude = {SpringBootConfiguration.class, DataSourceAutoConfiguration.class})
@Slf4j
@EnableScheduling
public class Application {
    /**
     * 汇聚万连系统消息通知群机器人
     */
    public static final String WECHAT_WEBHOOK = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=656917b6-57f5-4345-97b6-4ab287a0481a";

    /**
     * Start
     */
    public static void main(String[] args) {
//        sendStartRunPublishingMsg(args);
        SpringApplication.run(Application.class, args);
        log.info("Start Success");
    }

    private static void sendStartRunPublishingMsg(String[] args) {
        final WxCorpMsgConfig corpMsgConfig = WxCorpMsgConfig.builder()
                // 汇聚万连系统消息通知群机器人
                .wechatWebhook(WECHAT_WEBHOOK)
                .envBlackList(ListUtil.toList("dev"))
                .build();
        WxCorpMsgUtils.initConfig(corpMsgConfig);

        WxCorpMsgUtils.sendStartRunPublishingMsg(args);
    }

}
