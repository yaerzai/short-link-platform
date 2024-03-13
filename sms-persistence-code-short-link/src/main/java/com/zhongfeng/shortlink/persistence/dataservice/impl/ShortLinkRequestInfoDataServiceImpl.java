package com.zhongfeng.shortlink.persistence.dataservice.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;
import com.zhongfeng.common.db.model.PageData;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkRequestInfoDataService;
import com.zhongfeng.shortlink.persistence.dataservice.bo.BrandClickNumChartDataRespBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.DateRangeClickNumChartDataRespBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ProvinceClickNumChartDataRespBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkRequestInfoDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkRequestInfoQueryBO;
import com.zhongfeng.shortlink.persistence.dto.BrandClickNumChartDataReqDTO;
import com.zhongfeng.shortlink.persistence.dto.DateRangeClickNumChartDataReqDTO;
import com.zhongfeng.shortlink.persistence.dto.ProvinceClickNumChartDataReqDTO;
import com.zhongfeng.shortlink.persistence.mapper.ShortLinkRequestInfoMapper;
import com.zhongfeng.shortlink.persistence.model.ShortLinkRequestInfo;
import com.zhongfeng.shortlink.persistence.model.ShortLinkRequestInfoExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuzc
 * @date 2020/2/26 18:25
 */
@Service
@Slf4j
public class ShortLinkRequestInfoDataServiceImpl implements ShortLinkRequestInfoDataService {
    @Resource
    private ShortLinkRequestInfoMapper mapper;

    @Override
    public PageData<ShortLinkRequestInfoDataBO> query(ShortLinkRequestInfoQueryBO queryBO) {
        ShortLinkRequestInfoExample example = queryBO.buildExample();
        Page<ShortLinkRequestInfo> shortLinkInfoPage = mapper.selectByExampleWithRowbounds(example, new PageRowBounds(queryBO.getPageNo(), queryBO.getPageSize()));
        PageData<ShortLinkRequestInfoDataBO> pageData = PageData.getPageData(shortLinkInfoPage);
        if (!CollectionUtils.isEmpty(shortLinkInfoPage)) {
            pageData.setData(shortLinkInfoPage.stream().map(ShortLinkRequestInfoDataBO::buildByPO).collect(Collectors.toList()));
        }
        return pageData;
    }

    @Override
    public boolean add(ShortLinkRequestInfoDataBO dataBO) {
        return mapper.insertSelective(dataBO.buildPO()) == 1;
    }

    @Override
    public boolean delete(String linkNo, String tableTime) {
        if (StringUtils.isBlank(linkNo)) {
            log.error("删除条件不能为空 {}", linkNo);
            return false;
        }
        ShortLinkRequestInfoExample example = new ShortLinkRequestInfoExample();
        example.createCriteria().andLinkNoEqualTo(linkNo).andTableTimeEqualTo(tableTime);
        return mapper.deleteByExample(example) >= 1;
    }

    @Override
    public List<ShortLinkRequestInfoDataBO> list(ShortLinkRequestInfoQueryBO queryBO) {
        ShortLinkRequestInfoExample example = queryBO.buildExample();
        List<ShortLinkRequestInfo> list = mapper.selectByExample(example);
        return CollectionUtils.isEmpty(list) ? null : list.stream().map(ShortLinkRequestInfoDataBO::buildByPO).collect(Collectors.toList());
    }

    /**
     * 省份点击数量地图图表数据
     *
     * @param dto the dto
     * @return the result data
     */
    @Override
    public List<ProvinceClickNumChartDataRespBO> provinceClickNumChartData(ProvinceClickNumChartDataReqDTO dto) {
        return mapper.provinceClickNumChartData(dto);
    }

    /**
     * 时间段点击数量趋势折线图图表数据
     *
     * @param dto the dto
     * @return the result data
     */
    @Override
    public List<DateRangeClickNumChartDataRespBO> dateRangeClickNumChartData(DateRangeClickNumChartDataReqDTO dto) {
        return mapper.dateRangeClickNumChartData(dto);
    }

    /**
     * 手机品牌点击数量柱状图图表数据
     *
     * @param dto the dto
     * @return the result data
     */
    @Override
    public List<BrandClickNumChartDataRespBO> brandClickNumChartData(BrandClickNumChartDataReqDTO dto) {
        return mapper.brandClickNumChartData(dto);
    }
}
