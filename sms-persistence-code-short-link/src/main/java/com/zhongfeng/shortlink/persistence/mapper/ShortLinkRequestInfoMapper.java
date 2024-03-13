package com.zhongfeng.shortlink.persistence.mapper;

import com.zhongfeng.shortlink.persistence.dataservice.bo.BrandClickNumChartDataRespBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.DateRangeClickNumChartDataRespBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ProvinceClickNumChartDataRespBO;
import com.zhongfeng.shortlink.persistence.dto.BrandClickNumChartDataReqDTO;
import com.zhongfeng.shortlink.persistence.dto.DateRangeClickNumChartDataReqDTO;
import com.zhongfeng.shortlink.persistence.dto.ProvinceClickNumChartDataReqDTO;
import com.zhongfeng.shortlink.persistence.model.ShortLinkRequestInfo;
import com.zhongfeng.shortlink.persistence.model.ShortLinkRequestInfoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author mbg
 */
public interface ShortLinkRequestInfoMapper {
    long countByExample(ShortLinkRequestInfoExample example);

    int deleteByExample(ShortLinkRequestInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(ShortLinkRequestInfo record);

    com.github.pagehelper.Page<ShortLinkRequestInfo> selectByExampleWithRowbounds(ShortLinkRequestInfoExample example, RowBounds rowBounds);

    List<ShortLinkRequestInfo> selectByExample(ShortLinkRequestInfoExample example);

    ShortLinkRequestInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShortLinkRequestInfo record, @Param("example") ShortLinkRequestInfoExample example);

    int updateByPrimaryKeySelective(ShortLinkRequestInfo record);

    /**
     * 省份点击数量地图图表数据
     *
     * @param dto the dto
     * @return the result data
     */
    List<ProvinceClickNumChartDataRespBO> provinceClickNumChartData(@Param(value = "params") ProvinceClickNumChartDataReqDTO dto);

    /**
     * 时间段点击数量趋势折线图图表数据
     *
     * @param dto the dto
     * @return the result data
     */
    List<DateRangeClickNumChartDataRespBO> dateRangeClickNumChartData(@Param(value = "params") DateRangeClickNumChartDataReqDTO dto);

    /**
     * 手机品牌点击数量柱状图图表数据
     *
     * @param dto the dto
     * @return the result data
     */
    List<BrandClickNumChartDataRespBO> brandClickNumChartData(@Param(value = "params") BrandClickNumChartDataReqDTO dto);
}