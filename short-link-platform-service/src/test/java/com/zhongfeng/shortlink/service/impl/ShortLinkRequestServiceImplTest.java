package com.zhongfeng.shortlink.service.impl;


import cn.hutool.core.date.DateUtil;
import com.zhongfeng.shortlink.api.client.req.BrandClickNumChartDataReq;
import com.zhongfeng.shortlink.api.client.req.DateRangeClickNumChartDataReq;
import com.zhongfeng.shortlink.api.client.req.ProvinceClickNumChartDataReq;
import com.zhongfeng.shortlink.api.client.resp.BrandClickNumChartDataResp;
import com.zhongfeng.shortlink.api.client.resp.DateRangeClickNumChartDataResp;
import com.zhongfeng.shortlink.api.client.resp.ProvinceClickNumChartDataResp;
import com.zhongfeng.shortlink.service.ShortLinkRequestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ShortLinkRequestServiceImplTest {

    @Autowired
    private ShortLinkRequestService shortLinkRequestService;

    /**
     * 省份点击数量地图图表数据
     */
    @Test
    public void provinceClickNumChartData() {
        final ProvinceClickNumChartDataReq req = ProvinceClickNumChartDataReq.builder()
                .linkNo("202305301103238489")
                .tableTime("20230530")
                .build();
        final List<ProvinceClickNumChartDataResp> list = shortLinkRequestService.provinceClickNumChartData(req);
        log.info("list:{}", list);
    }

    /**
     * 时间段点击数量趋势折线图图表数据
     */
    @Test
    public void dateRangeClickNumChartData() {
        final DateRangeClickNumChartDataReq req = DateRangeClickNumChartDataReq.builder()
                .linkNo("202305301103238489")
                .statRule(2)
                //.startDate(DateUtil.offsetDay(new Date(), -10))
                .startDate(DateUtil.parseDate("2021-05-30"))
                .endDate(DateUtil.parseDate("2023-05-30"))
                .build();
        final List<DateRangeClickNumChartDataResp> list = shortLinkRequestService.dateRangeClickNumChartData(req);
        log.info("list:{}", list);
    }

    /**
     * 手机品牌点击数量柱状图图表数据
     */
    @Test
    public void brandClickNumChartData() {
        BrandClickNumChartDataReq req = BrandClickNumChartDataReq.builder()
                .linkNo("202305301103238489")
                .tableTime("20230530")
                .build();
        final List<BrandClickNumChartDataResp> list = shortLinkRequestService.brandClickNumChartData(req);
        log.info("list:{}", list);
    }
}