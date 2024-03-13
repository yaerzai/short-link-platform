package com.zhongfeng.shortlink.persistence.dataservice.impl;

import com.zhongfeng.common.db.model.PageData;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkReportDayDataService;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkReportDayDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkReportDayQueryBO;
import com.zhongfeng.shortlink.persistence.mapper.ShortLinkReportDayExtMapper;
import com.zhongfeng.shortlink.persistence.model.ShortLinkReportDay;
import com.zhongfeng.shortlink.persistence.model.ShortLinkReportDayQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author codescript.build
 */
@Service
@Slf4j
public class ShortLinkReportDayDataServiceImpl implements ShortLinkReportDayDataService {
    @Resource
    private ShortLinkReportDayExtMapper shortLinkReportDayExtMapper;

    @Override
    public PageData<ShortLinkReportDayDataBO> query(ShortLinkReportDayQueryBO dataBO) {
        ShortLinkReportDayQuery queryPO = dataBO.buildQueryPO();
        List<ShortLinkReportDay> data = shortLinkReportDayExtMapper.queryStat(queryPO);
        Integer count = shortLinkReportDayExtMapper.countStat(queryPO);

        PageData<ShortLinkReportDayDataBO> pageData = new PageData<>();
        pageData.setPageNo(dataBO.getPageNo());
        pageData.setPageSize(dataBO.getPageSize());
        pageData.setTotal(count);
        pageData.setPages((count + dataBO.getPageSize() - 1)/dataBO.getPageSize());
        pageData.setData(data.stream().map(ShortLinkReportDayDataBO::buildByPO).collect(Collectors.toList()));
        return pageData;
    }

}
