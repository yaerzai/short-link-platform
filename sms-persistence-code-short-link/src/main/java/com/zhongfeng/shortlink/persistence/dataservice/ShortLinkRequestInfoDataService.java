package com.zhongfeng.shortlink.persistence.dataservice;


import com.zhongfeng.common.db.model.PageData;
import com.zhongfeng.shortlink.persistence.dataservice.bo.BrandClickNumChartDataRespBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.DateRangeClickNumChartDataRespBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ProvinceClickNumChartDataRespBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkRequestInfoDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkRequestInfoQueryBO;
import com.zhongfeng.shortlink.persistence.dto.BrandClickNumChartDataReqDTO;
import com.zhongfeng.shortlink.persistence.dto.DateRangeClickNumChartDataReqDTO;
import com.zhongfeng.shortlink.persistence.dto.ProvinceClickNumChartDataReqDTO;

import java.util.List;

/**
 * @author yuzc
 * 2020/2/23 11:23
 * <p>
 * 短链请求信息管理
 */
public interface ShortLinkRequestInfoDataService {
    /***
     * 查询
     * @param  queryBO
     * @return 短链请求结果集
     */
    PageData<ShortLinkRequestInfoDataBO> query(ShortLinkRequestInfoQueryBO queryBO);

    /**
     * 添加
     *
     * @param dataBO
     * @return
     */
    boolean add(ShortLinkRequestInfoDataBO dataBO);

    /**
     * 删除
     * @param linkNo 链接编号
     * @return
     */
    boolean delete(String linkNo, String tableTime);

    /**
     * 获取短链请求信息
     *
     * @param queryBO
     * @return
     */
    List<ShortLinkRequestInfoDataBO> list(ShortLinkRequestInfoQueryBO queryBO);

    /**
     * 省份点击数量地图图表数据
     *
     * @param dto the dto
     * @return the result data
     */
    List<ProvinceClickNumChartDataRespBO> provinceClickNumChartData(ProvinceClickNumChartDataReqDTO dto);

    /**
     * 时间段点击数量趋势折线图图表数据
     *
     * @param dto the dto
     * @return the result data
     */
    List<DateRangeClickNumChartDataRespBO> dateRangeClickNumChartData(DateRangeClickNumChartDataReqDTO dto);

    /**
     * 手机品牌点击数量柱状图图表数据
     *
     * @param dto the dto
     * @return the result data
     */
    List<BrandClickNumChartDataRespBO> brandClickNumChartData(BrandClickNumChartDataReqDTO dto);
}
