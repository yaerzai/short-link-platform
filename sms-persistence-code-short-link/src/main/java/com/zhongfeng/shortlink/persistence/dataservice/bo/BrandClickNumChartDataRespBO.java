package com.zhongfeng.shortlink.persistence.dataservice.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 手机品牌点击数量柱状图图表数据Resp
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandClickNumChartDataRespBO implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -3862148050818905013L;

    /**
     * 手机品牌NO
     * <br/>
     * 如:1,2,3
     */
    private Integer brand;

    /**
     * 数量
     */
    private Integer num;
}
