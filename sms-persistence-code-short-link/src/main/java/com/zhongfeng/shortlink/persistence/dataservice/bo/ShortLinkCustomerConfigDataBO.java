package com.zhongfeng.shortlink.persistence.dataservice.bo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.zhongfeng.shortlink.persistence.model.ShortLinkCustomerConfig;
import com.zhongfeng.shortlink.persistence.model.ShortLinkCustomerConfigExample;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mbg
 */
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShortLinkCustomerConfigDataBO implements Serializable {
    private static final long serialVersionUID = -2965649621762201662L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 链接编号
     */
    private String linkNo;

    /**
     * 链接名称
     */
    private String linkName;

    /**
     * 平台编号
     */
    private String platformNo;

    /**
     * 客户编号
     */
    private String customerNo;

    /**
     * 客户账号
     */
    private String userNo;

    /**
     * 链接地址
     */
    private String address;

    /**
     * 长链接MD5
     */
    private String addressMd5;
    /**
     * 短链域名
     */
    private String linkDomain;
    /**
     * 短链地址
     */
    private String shortLinkAddress;

    /**
     * 审核状态  0：待审核 1：审核通过、免审 2：拒绝
     */
    private Byte checkStatus;

    /**
     * 所属分组 默认1001
     */
    private String groupNo;

    /**
     * 短链数量
     */
    private Integer linkNum;

    /**
     * 类型类型 0：单一 1：批量 2:多个链接地址
     */
    private Byte linkType;

    /**
     * 数据来源 0:管理后台  1：门户
     */
    private Byte source;

    /**
     * 备注
     */
    private String remark;

    /**
     * 手机号参数名，单号单链时有效
     */
    private String mobileNoParam;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 分表时间
     */
    private String tableTime;

    /**
     * 过期时间
     */
    private Date expirationTime;
    /**
     * 最后操作员
     */
    private String operatorId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public static ShortLinkCustomerConfigDataBO buildByPO(ShortLinkCustomerConfig po) {
        ShortLinkCustomerConfigDataBO shortLinkCustomerConfigDataBO = new ShortLinkCustomerConfigDataBO();
        shortLinkCustomerConfigDataBO.setId(po.getId());
        shortLinkCustomerConfigDataBO.setLinkNo(po.getLinkNo());
        shortLinkCustomerConfigDataBO.setLinkName(po.getLinkName());
        shortLinkCustomerConfigDataBO.setPlatformNo(po.getPlatformNo());
        shortLinkCustomerConfigDataBO.setCustomerNo(po.getCustomerNo());
        shortLinkCustomerConfigDataBO.setUserNo(po.getUserNo());
        shortLinkCustomerConfigDataBO.setAddress(po.getAddress());
        shortLinkCustomerConfigDataBO.setMobileNoParam(po.getMobileNoParam());
        shortLinkCustomerConfigDataBO.setStatus(po.getStatus());
        shortLinkCustomerConfigDataBO.setOperatorId(po.getOperatorId());
        shortLinkCustomerConfigDataBO.setCreateTime(po.getCreateTime());
        shortLinkCustomerConfigDataBO.setUpdateTime(po.getUpdateTime());
        shortLinkCustomerConfigDataBO.setCheckStatus(po.getCheckStatus());
        shortLinkCustomerConfigDataBO.setLinkDomain(po.getLinkDomain());
        shortLinkCustomerConfigDataBO.setShortLinkAddress(po.getShortLinkAddress());
        shortLinkCustomerConfigDataBO.setCheckStatus(po.getCheckStatus());
        shortLinkCustomerConfigDataBO.setGroupNo(po.getGroupNo());
        shortLinkCustomerConfigDataBO.setLinkNum(po.getLinkNum());
        shortLinkCustomerConfigDataBO.setLinkType(po.getLinkType());
        shortLinkCustomerConfigDataBO.setRemark(po.getRemark());
        shortLinkCustomerConfigDataBO.setExpirationTime(po.getExpirationTime());
        shortLinkCustomerConfigDataBO.setAddressMd5(po.getAddressMd5());
        shortLinkCustomerConfigDataBO.setSource(po.getSource());
        shortLinkCustomerConfigDataBO.setTableTime(po.getTableTime());
        return shortLinkCustomerConfigDataBO;
    }

    public ShortLinkCustomerConfig buildPO() {
        ShortLinkCustomerConfig shortLinkCustomerConfig = new ShortLinkCustomerConfig();
        shortLinkCustomerConfig.setLinkNo(linkNo);
        shortLinkCustomerConfig.setLinkName(linkName);
        shortLinkCustomerConfig.setPlatformNo(platformNo);
        shortLinkCustomerConfig.setCustomerNo(customerNo);
        shortLinkCustomerConfig.setUserNo(userNo);
        shortLinkCustomerConfig.setAddress(address);
        shortLinkCustomerConfig.setMobileNoParam(mobileNoParam);
        shortLinkCustomerConfig.setStatus(status);
        shortLinkCustomerConfig.setOperatorId(operatorId);
        shortLinkCustomerConfig.setCheckStatus(checkStatus);
        shortLinkCustomerConfig.setLinkDomain(linkDomain);
        shortLinkCustomerConfig.setShortLinkAddress(shortLinkAddress);
        shortLinkCustomerConfig.setCheckStatus(checkStatus);
        shortLinkCustomerConfig.setGroupNo(groupNo);
        shortLinkCustomerConfig.setLinkNum(linkNum);
        shortLinkCustomerConfig.setLinkType(linkType);
        shortLinkCustomerConfig.setRemark(remark);
        shortLinkCustomerConfig.setExpirationTime(expirationTime);
        shortLinkCustomerConfig.setAddressMd5(addressMd5);
        shortLinkCustomerConfig.setSource(source);
        shortLinkCustomerConfig.setTableTime(tableTime);
        return shortLinkCustomerConfig;
    }

    public ShortLinkCustomerConfigExample buildExample() {
        ShortLinkCustomerConfigExample example = new ShortLinkCustomerConfigExample();
        ShortLinkCustomerConfigExample.Criteria criteria = example.createCriteria();
        if (StrUtil.isNotEmpty(platformNo)) {
            criteria.andPlatformNoEqualTo(platformNo);
        }
        if (StrUtil.isNotEmpty(linkNo)) {
            criteria.andLinkNoEqualTo(linkNo);
        }
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        if (StrUtil.isNotEmpty(customerNo)) {
            criteria.andCustomerNoEqualTo(customerNo);
        }
        if (StrUtil.isNotEmpty(userNo)) {
            criteria.andUserNoEqualTo(userNo);
        }
        if (StrUtil.isNotEmpty(groupNo)) {
            criteria.andGroupNoEqualTo(groupNo);
        }
        if (checkStatus != null) {
            criteria.andCheckStatusEqualTo(checkStatus);
        }
        if (linkType != null) {
            criteria.andLinkTypeEqualTo(linkType);
        }
        if (source != null) {
            criteria.andSourceEqualTo(source);
        }
        if (StrUtil.isNotEmpty(shortLinkAddress)) {
            criteria.andShortLinkAddressEqualTo(shortLinkAddress);
        }
        if (StrUtil.isNotEmpty(address)) {
            criteria.andAddressEqualTo(address);
        }
        if (StrUtil.isNotEmpty(linkDomain)) {
            criteria.andLinkDomainEqualTo(linkDomain);
        }
        if (StringUtils.isNotBlank(linkName)) {
            criteria.andLinkNameLike("%" + linkName + "%");
        }
        if (StringUtils.isNotBlank(tableTime)) {
            criteria.andTableTimeEqualTo(tableTime);
        }

        return example;
    }
}
