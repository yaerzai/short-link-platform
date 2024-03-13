package com.zhongfeng.shortlink.facade.api;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.zhongfeng.common.base.exception.BusinessException;
import com.zhongfeng.common.swagger.model.ResultData;
import com.zhongfeng.shortlink.api.client.ShortLinkRequestInfoClient;
import com.zhongfeng.shortlink.api.client.req.BrandClickNumChartDataReq;
import com.zhongfeng.shortlink.api.client.req.DateRangeClickNumChartDataReq;
import com.zhongfeng.shortlink.api.client.req.ProvinceClickNumChartDataReq;
import com.zhongfeng.shortlink.api.client.resp.BrandClickNumChartDataResp;
import com.zhongfeng.shortlink.api.client.resp.DateRangeClickNumChartDataResp;
import com.zhongfeng.shortlink.api.client.resp.ProvinceClickNumChartDataResp;
import com.zhongfeng.shortlink.service.ShortLinkRequestService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 短链请求信息服务接口
 * <br/>
 * 短链请求信息Feign服务接口实现
 *
 * @author leiguoqing
 * @date 2023年6月2日 18:08:14
 */
@SuppressWarnings(value = "unchecked")
@Slf4j
@Api(tags = "短链请求信息API")
@RestController
@RequestMapping(ShortLinkRequestInfoClient.BASE_PATH)
public class ShortLinkRequestInfoController implements ShortLinkRequestInfoClient {

    @Autowired
    private ShortLinkRequestService shortLinkRequestService;

    @Override
    public ResultData<String> test(Map<String, Object> map) {
        log.info("map : {}", map);
        throw new BusinessException("aaaa");
        //return ResultData.getSuccessData(map.get("hello") + "");
    }

    /**
     * 省份点击数量地图图表数据
     *
     * @param req the req
     * @return the result data
     */
    @Override
    public ResultData<List<ProvinceClickNumChartDataResp>> provinceClickNumChartData(ProvinceClickNumChartDataReq req) {
        final List<ProvinceClickNumChartDataResp> result = shortLinkRequestService.provinceClickNumChartData(req);
        return ResultData.getSuccessData(result);
    }

    /**
     * 时间段点击数量趋势折线图图表数据
     *
     * @param req the req
     * @return the result data
     */
    @Override
    public ResultData<List<DateRangeClickNumChartDataResp>> dateRangeClickNumChartData(DateRangeClickNumChartDataReq req) {
        final Integer statRule = req.getStatRule();
        final Date startDate = req.getStartDate();
        final Date endDate = req.getEndDate();
        final long between = DateUtil.between(startDate, endDate, DateUnit.DAY);
        // 按天统计
        if (Objects.equals(1, statRule)) {
            if (between > 30) {
                return ResultData.getFailResult("开始日期、结束日期间隔不能大于30天");
            }
        }

        // 按小时统计
        if (Objects.equals(2, statRule)) {
            if (between > 1) {
                return ResultData.getFailResult("开始日期、结束日期必须相等");
            }
        }

        final List<DateRangeClickNumChartDataResp> result = shortLinkRequestService.dateRangeClickNumChartData(req);
        return ResultData.getSuccessData(result);
    }

    /**
     * 手机品牌点击数量柱状图图表数据
     *
     * @param req the req
     * @return the result data
     */
    @Override
    public ResultData<List<BrandClickNumChartDataResp>> brandClickNumChartData(BrandClickNumChartDataReq req) {
        final List<BrandClickNumChartDataResp> result = shortLinkRequestService.brandClickNumChartData(req);
        return ResultData.getSuccessData(result);
    }


}

