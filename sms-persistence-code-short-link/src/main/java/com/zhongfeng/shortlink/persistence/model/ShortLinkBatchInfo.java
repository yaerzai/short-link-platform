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
public class ShortLinkBatchInfo implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 链接编号
     */
    private String linkNo;

    /**
     * 短链批次,日期+4位序列号
     */
    private String linkBatchNo;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 手机号个数
     */
    private Integer mobileNum;

    /**
     * 点击次数（每天统计后累计）
     */
    private Integer clickNum;

    /**
     * 过期时间
     */
    private Date expireTime;

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