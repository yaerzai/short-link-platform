package com.zhongfeng.shortlink.persistence.model;

import lombok.*;

/**
 * 类描述
 *
 * @auther fanjun
 */
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestReportResp {


    /**
     * 手机号
     */
    private Long id;

    /**
     * 分表时间
     */
    private String tableTime;

    /**
     * 手机号
     */
    private String mobileNo;


    /**
     * 短链类型
     */
    private String shortLineType;
    /**
     * 浏览次数
     */
    private Integer pv;

    /**
     * 独立访客
     */
    private Integer uv;

    /**
     * 访问IP
     */
    private Integer ip;
}
