package com.zhongfeng.shortlink.service;

import com.zhongfeng.shortlink.api.client.req.BrandClickNumChartDataReq;
import com.zhongfeng.shortlink.api.client.req.DateRangeClickNumChartDataReq;
import com.zhongfeng.shortlink.api.client.req.ProvinceClickNumChartDataReq;
import com.zhongfeng.shortlink.api.client.resp.BrandClickNumChartDataResp;
import com.zhongfeng.shortlink.api.client.resp.DateRangeClickNumChartDataResp;
import com.zhongfeng.shortlink.api.client.resp.ProvinceClickNumChartDataResp;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkRequestInfoDataBO;

import java.util.List;

/**
 * 短链请求服务
 *
 * @auther fanjun
 */
public interface ShortLinkRequestService {

    void addRequestInfo(ShortLinkRequestInfoDataBO dataBO);

    void refreshCache();

    /**
     * 省份点击数量地图图表数据
     *
     * @param req the req
     * @return the result data
     */
    List<ProvinceClickNumChartDataResp> provinceClickNumChartData(ProvinceClickNumChartDataReq req);

    /**
     * 时间段点击数量趋势折线图图表数据
     *
     * @param req the req
     * @return the result data
     */
    List<DateRangeClickNumChartDataResp> dateRangeClickNumChartData(DateRangeClickNumChartDataReq req);

    /**
     * 手机品牌点击数量柱状图图表数据
     *
     * @param req the req
     * @return the result data
     */
    List<BrandClickNumChartDataResp> brandClickNumChartData(BrandClickNumChartDataReq req);
}
