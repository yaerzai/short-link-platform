package com.zhongfeng.shortlink.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 手机品牌点击数量柱状图图表数据Req
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandClickNumChartDataReqDTO implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -4336367146934689954L;

    /**
     * 链接编号
     */
    private String linkNo;

    /**
     * 请求日期
     * <br/>
     * 格式：yyyyMMdd
     */
    private String tableTime;
}