package com.zhongfeng.shortlink.persistence.model;

import lombok.*;

/**
 * 类描述
 *
 * @auther fanjun
 */
@lombok.Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestRealtimeStatisticReq {

    /**
     * 分表时间
     */
    private String tableTime;

    /**
     * 短链编号
     */
    private String linkNo;

    /**
     * 操作系统
     */
    private String requestDevice;

    /**
     * 访问环境
     */
    private String browser;

}
