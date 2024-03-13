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
public class ShortLinkCustomerConfig implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 链接编号
     */
    private String linkNo;

    /**
     * 链接名称
     */
    private String linkName;

    /**
     * 平台编号
     */
    private String platformNo;

    /**
     * 客户编号
     */
    private String customerNo;

    /**
     * 客户账号
     */
    private String userNo;

    /**
     * 链接地址
     */
    private String address;

    /**
     * 长链接MD5
     */
    private String addressMd5;

    /**
     * 短链域名
     */
    private String linkDomain;

    /**
     * 短链地址
     */
    private String shortLinkAddress;

    /**
     * 审核状态  0：待审核 1：审核通过、免审 2：拒绝
     */
    private Byte checkStatus;

    /**
     * 所属分组 默认1001
     */
    private String groupNo;

    /**
     * 短链数量
     */
    private Integer linkNum;

    /**
     * 类型类型 0：单一 1：批量
     */
    private Byte linkType;

    /**
     * 数据来源 0:管理后台  1：门户
     */
    private Byte source;

    /**
     * 备注
     */
    private String remark;

    /**
     * 过期时间
     */
    private Date expirationTime;

    /**
     * 手机号参数名，单号单链时有效
     */
    private String mobileNoParam;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 分表时间
     */
    private String tableTime;

    /**
     * 最后操作员
     */
    private String operatorId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}