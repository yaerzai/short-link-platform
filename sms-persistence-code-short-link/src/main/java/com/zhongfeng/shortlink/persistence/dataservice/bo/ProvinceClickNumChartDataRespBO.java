package com.zhongfeng.shortlink.persistence.dataservice.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 省份点击数量地图图表数据Resp
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProvinceClickNumChartDataRespBO implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -3862148050818905013L;

    /**
     * 省份编码
     * <br/>
     * 返回2位数的省份编码
     */
    private String provinceNo;

    /**
     * 数量
     */
    private Integer num;
}
