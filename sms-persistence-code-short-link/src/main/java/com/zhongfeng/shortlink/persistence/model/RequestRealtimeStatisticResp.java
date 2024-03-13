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
public class RequestRealtimeStatisticResp {

    /**
     * 时间段 yyyyMMddHH
     */
    private String time;

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
