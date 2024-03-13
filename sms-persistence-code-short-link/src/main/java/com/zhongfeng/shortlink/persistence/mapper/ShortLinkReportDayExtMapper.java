package com.zhongfeng.shortlink.persistence.mapper;

import com.zhongfeng.shortlink.persistence.model.ShortLinkReportDay;
import com.zhongfeng.shortlink.persistence.model.ShortLinkReportDayQuery;

import java.util.List;

/**
 * @author mbg
 */
public interface ShortLinkReportDayExtMapper {

    List<ShortLinkReportDay> queryStat(ShortLinkReportDayQuery query);

    Integer countStat(ShortLinkReportDayQuery query);

}