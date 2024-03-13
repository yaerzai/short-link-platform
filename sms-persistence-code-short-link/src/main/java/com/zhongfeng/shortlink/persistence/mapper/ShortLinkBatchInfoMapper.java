package com.zhongfeng.shortlink.persistence.mapper;

import com.zhongfeng.shortlink.persistence.model.ShortLinkBatchInfo;
import com.zhongfeng.shortlink.persistence.model.ShortLinkBatchInfoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author mbg
 */
public interface ShortLinkBatchInfoMapper {
    long countByExample(ShortLinkBatchInfoExample example);

    int deleteByExample(ShortLinkBatchInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(ShortLinkBatchInfo record);

    com.github.pagehelper.Page<ShortLinkBatchInfo> selectByExampleWithRowbounds(ShortLinkBatchInfoExample example, RowBounds rowBounds);

    List<ShortLinkBatchInfo> selectByExample(ShortLinkBatchInfoExample example);

    ShortLinkBatchInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShortLinkBatchInfo record, @Param("example") ShortLinkBatchInfoExample example);

    int updateByPrimaryKeySelective(ShortLinkBatchInfo record);
}