package com.zhongfeng.shortlink.persistence.mapper;

import com.zhongfeng.shortlink.persistence.model.ShortLinkInfo;
import com.zhongfeng.shortlink.persistence.model.ShortLinkInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
 * @author mbg
 */
public interface ShortLinkInfoMapper {
    long countByExample(ShortLinkInfoExample example);

    int deleteByExample(ShortLinkInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(ShortLinkInfo record);

    com.github.pagehelper.Page<ShortLinkInfo> selectByExampleWithRowbounds(ShortLinkInfoExample example, RowBounds rowBounds);

    List<ShortLinkInfo> selectByExample(ShortLinkInfoExample example);

    ShortLinkInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShortLinkInfo record, @Param("example") ShortLinkInfoExample example);

    int updateByPrimaryKeySelective(ShortLinkInfo record);
}