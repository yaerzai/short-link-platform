package com.zhongfeng.shortlink.task;

import cn.hutool.core.date.DateUtil;
import com.zhongfeng.common.base.service.BaseService;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkReportDayCreateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 短链日报表定时任务
 *
 * @auther fanjun
 */
@Slf4j
@Service
public class ShortLinkReportDayTask {

    @Resource
    private ShortLinkReportDayCreateService shortLinkReportDayService;
    @Autowired
    private BaseService baseService;

    @Scheduled(cron = "0 10 0 * * ?")
    public void reportDay() {
        if (baseService.getNodeId() != 1) {
            return;
        }
        long startTime = System.currentTimeMillis();
        String reportDate = DateUtil.offsetDay(DateUtil.date(), -1).toString("yyyyMMdd");
        log.warn("[ShortLinkReportDayTask] 开始生成日报表[{}]", reportDate);
        int insertCount = shortLinkReportDayService.createDayReport(reportDate, "sys");
        long spaceTime = System.currentTimeMillis() - startTime;
        log.warn("[ShortLinkReportDayTask] 日报表[{}]生成完成, 条数: {}, 耗时: {}", reportDate, insertCount, spaceTime);
    }

}
