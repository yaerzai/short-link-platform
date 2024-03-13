package com.zhongfeng.shortlink.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class ShortLinkRequestInfo implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 短链编号
     */
    private String shortLinkNo;

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
     * 长链接MD5
     */
    private String addressMd5;

    /**
     * 访问域名
     */
    private String linkDomain;

    /**
     * 手机号码
     */
    private String mobileNo;

    /**
     * 请求日期 
     */
    private String tableTime;

    /**
     * UA信息
     */
    private String userAgent;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 数据有效性
     */
    private Byte dataValidate;


    /**
     * 短链类型
     */
    private String shortLineType;


    /**
     * 设备品牌
     */
    private Byte brand;

    /**
     * IP十进制数值
     */
    private Long ipNumber;

    /**
     * IP归属运营商
     */
    private String ipIsp;

    /**
     * IP归属市
     */
    private String ipCity;

    /**
     * 手机号归属地_省份
     */
    private String mobileProvince;

    /**
     * 手机号码归属市
     */
    private String mobileCity;

    /**
     * 手机号码归属运营商
     */
    private String mobileIsp;

    /**
     * IP归属省
     */
    private String ipProvince;

    /**
     * 请求时间
     */
    private Date requestTime;

    /**
     * 请求IP地址
     */
    private String requestIp;

    /**
     * 访问设备类型 PC、Android、IOS
     */
    private String requestDevice;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}