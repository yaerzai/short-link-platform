package com.zhongfeng.shortlink.persistence.mapper;

import com.zhongfeng.shortlink.persistence.model.ShortLinkReportDay;
import com.zhongfeng.shortlink.persistence.model.ShortLinkReportDayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
 * @author mbg
 */
public interface ShortLinkReportDayMapper {
    long countByExample(ShortLinkReportDayExample example);

    int deleteByExample(ShortLinkReportDayExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(ShortLinkReportDay record);

    com.github.pagehelper.Page<ShortLinkReportDay> selectByExampleWithRowbounds(ShortLinkReportDayExample example, RowBounds rowBounds);

    List<ShortLinkReportDay> selectByExample(ShortLinkReportDayExample example);

    ShortLinkReportDay selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShortLinkReportDay record, @Param("example") ShortLinkReportDayExample example);

    int updateByPrimaryKeySelective(ShortLinkReportDay record);
}