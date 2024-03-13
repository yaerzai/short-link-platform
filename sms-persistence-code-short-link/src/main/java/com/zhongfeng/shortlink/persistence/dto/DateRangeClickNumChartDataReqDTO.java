package com.zhongfeng.shortlink.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * 时间段点击数量趋势折线图图表数据Req
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DateRangeClickNumChartDataReqDTO implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -4336367146934689954L;

    /**
     * 链接编号
     */
    private String linkNo;

    /**
     * 统计规则
     * <br/>
     * 按天统计：1 <br/>
     * 按小时统计：2
     * <br/>
     * 如果是按天统计，则可以选择日期区间，如 2023-01-01 - 2023-01-10，最多只能选择30天<br/>
     * 如果是按小时统计，则只能选择当天日期区间，如 2023-01-01 - 2023-01-01  开始日期、结束日期必须相等
     */
    private Integer statRule;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 开始请求日期
     * <br/>
     * 格式：yyyyMMdd
     */
    private String startTableTime;

    /**
     * 结束请求日期
     * <br/>
     * 格式：yyyyMMdd
     */
    private String endTableTime;
}
