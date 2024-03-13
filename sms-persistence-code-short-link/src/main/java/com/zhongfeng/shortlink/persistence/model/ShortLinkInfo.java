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
public class ShortLinkInfo implements Serializable {
    /**
     * 主键
     */
    private Long id;

    private String shortLinkNo;

    /**
     * 链接编号
     */
    private String linkNo;

    /**
     * 短链域名
     */
    private String linkDomain;

    /**
     * 手机号码
     */
    private String mobileNo;

    /**
     * 短链类型
     */
    private String shortLineType;


    /**
     * 分表时间
     */
    private String tableTime;

    /**
     * url
     */
    private String url;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}