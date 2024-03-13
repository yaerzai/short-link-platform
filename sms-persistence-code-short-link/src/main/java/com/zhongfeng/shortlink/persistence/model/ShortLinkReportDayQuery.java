package com.zhongfeng.shortlink.persistence.model;

import lombok.*;

import java.io.Serializable;

/**
 * @author mbg
 */
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShortLinkReportDayQuery implements Serializable {

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
     * 操作系统
     */
    private String os;

    /**
     * 设备品牌
     */
    private Byte brand;

    /**
     * 统计日期 开始
     */
    private String beginDate;

    /**
     * 统计日期 结束
     */
    private String endDate;


    /**
     * 分组
     */
    private String groupBy;

    /**
     * 开始条数
     */
    private Integer beginNum;

    /**
     * 分页大小
     */
    private Integer pageSize;


    private static final long serialVersionUID = 1L;
}