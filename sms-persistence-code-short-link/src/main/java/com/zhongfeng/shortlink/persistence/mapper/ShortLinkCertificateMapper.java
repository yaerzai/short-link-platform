package com.zhongfeng.shortlink.persistence.mapper;

import com.zhongfeng.shortlink.persistence.model.ShortLinkCertificate;
import com.zhongfeng.shortlink.persistence.model.ShortLinkCertificateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
 * @author mbg
 */
public interface ShortLinkCertificateMapper {
    long countByExample(ShortLinkCertificateExample example);

    int deleteByExample(ShortLinkCertificateExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(ShortLinkCertificate record);

    com.github.pagehelper.Page<ShortLinkCertificate> selectByExampleWithRowbounds(ShortLinkCertificateExample example, RowBounds rowBounds);

    List<ShortLinkCertificate> selectByExample(ShortLinkCertificateExample example);

    ShortLinkCertificate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShortLinkCertificate record, @Param("example") ShortLinkCertificateExample example);

    int updateByPrimaryKeySelective(ShortLinkCertificate record);
}