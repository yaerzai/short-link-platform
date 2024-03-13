package com.zhongfeng.shortlink.persistence.dataservice;

/**
 * 日报表服务
 *
 * @auther fanjun
 */
public interface ShortLinkReportDayCreateService {

    /**
     *
     * @param reportDate
     * @param operatorId
     */
    int createDayReport(String reportDate, String operatorId);
}
