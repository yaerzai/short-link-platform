package com.zhongfeng.shortlink.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class ShortLinkCertificate implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 长链接唯一识别码
     */
    private String uniqueLongLink;

    /**
     * 客户编号
     */
    private String customerNo;

    /**
     * 客户账号
     */
    private String userNo;

    /**
     * 长链接地址
     */
    private String address;

    /**
     * 凭证文件地址
     */
    private String certificates;

    /**
     * 类型  0：精确匹配 1：域名匹配
     */
    private Byte type;

    /**
     * 状态 0：正常  1：关闭
     */
    private Byte status;

    /**
     * 链接用途
     */
    private String purpose;

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