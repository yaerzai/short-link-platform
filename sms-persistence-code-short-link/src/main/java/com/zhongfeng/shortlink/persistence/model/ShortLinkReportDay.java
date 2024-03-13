package com.zhongfeng.shortlink.persistence.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mbg
 */
@lombok.Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShortLinkReportDay implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 链接编号
     */
    private String linkNo;

    /**
     * 客户编号
     */
    private String customerNo;

    /**
     * 客户账号
     */
    private String userNo;

    /**
     * 手机号码归属运营商
     */
    private String mobileIsp;

    /**
     * 手机号码归属省
     */
    private String mobileProvince;

    /**
     * IP归属运营商
     */
    private String ipIsp;

    /**
     * IP归属省
     */
    private String ipProvince;

    /**
     * IP归属城市
     */
    private String ipCity;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 设备品牌
     */
    private Byte brand;

    /**
     * pv数量
     */
    private Integer pvNum;

    /**
     * uv数量
     */
    private Integer uvNum;

    /**
     * ip数量
     */
    private Integer ipNum;

    /**
     * 统计日期 
     */
    private String reportDate;

    /**
     * 最后操作员
     */
    private String operatorId;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}