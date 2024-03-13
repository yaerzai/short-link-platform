package com.zhongfeng.shortlink.persistence.mapper;

import com.zhongfeng.shortlink.persistence.model.ShortLinkCustomerConfig;
import com.zhongfeng.shortlink.persistence.model.ShortLinkCustomerConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
 * @author mbg
 */
public interface ShortLinkCustomerConfigMapper {
    long countByExample(ShortLinkCustomerConfigExample example);

    int deleteByExample(ShortLinkCustomerConfigExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(ShortLinkCustomerConfig record);

    com.github.pagehelper.Page<ShortLinkCustomerConfig> selectByExampleWithRowbounds(ShortLinkCustomerConfigExample example, RowBounds rowBounds);

    List<ShortLinkCustomerConfig> selectByExample(ShortLinkCustomerConfigExample example);

    ShortLinkCustomerConfig selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShortLinkCustomerConfig record, @Param("example") ShortLinkCustomerConfigExample example);

    int updateByPrimaryKeySelective(ShortLinkCustomerConfig record);
}