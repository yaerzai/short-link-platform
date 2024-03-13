package com.zhongfeng.shortlink.persistence.mapper;

import com.zhongfeng.shortlink.persistence.model.RequestRealtimeStatisticReq;
import com.zhongfeng.shortlink.persistence.model.RequestRealtimeStatisticResp;
import com.zhongfeng.shortlink.persistence.model.RequestReportResp;
import com.zhongfeng.shortlink.persistence.model.ShortLinkRequestInfoExample;

import java.util.List;

/**
 * 类描述
 *
 * @auther fanjun
 */
public interface ShortLinkRequestInfoMapperExt {

    List<RequestRealtimeStatisticResp> requestRealtimeStatistic(RequestRealtimeStatisticReq req);

    List<RequestReportResp> requestReportQuery(ShortLinkRequestInfoExample example);
}
