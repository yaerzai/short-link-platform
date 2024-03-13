package com.zhongfeng.shortlink.persistence.dataservice.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 时间段点击数量趋势折线图图表数据Resp
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DateRangeClickNumChartDataRespBO implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -3862148050818905013L;

    /**
     * 时间轴
     * <br/>
     * 按天统计: 返回日期，如:2023-01-01 <br/>
     * 按小时统计: 返回小时，如:0, 1, ... 23
     */
    private String timeAxis;

    /**
     * 数量
     */
    private Integer num;
}
