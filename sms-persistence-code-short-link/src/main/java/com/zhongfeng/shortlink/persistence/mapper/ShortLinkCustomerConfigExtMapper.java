package com.zhongfeng.shortlink.persistence.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author mbg
 */
public interface ShortLinkCustomerConfigExtMapper {

    int addLinkNum(@Param("linkNo") String linkNo, @Param("linkNum") Integer linkNum);

}