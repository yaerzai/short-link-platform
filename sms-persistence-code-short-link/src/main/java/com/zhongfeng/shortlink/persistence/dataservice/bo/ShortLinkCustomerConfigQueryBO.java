package com.zhongfeng.shortlink.persistence.dataservice.bo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.zhongfeng.common.db.model.BaseQueryBO;
import com.zhongfeng.shortlink.persistence.model.ShortLinkCustomerConfigExample;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

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
public class ShortLinkCustomerConfigQueryBO extends BaseQueryBO {
    private static final long serialVersionUID = 5245655845124597775L;

    /**
     * 链接编号
     */
    private String linkNo;

    /**
     * 链接编号列表
     */
    private List<String> linkNos;

    /**
     * 活动名称
     */
    private String linkName;

    /**
     * 平台编号
     */
    private String platformNo;

    /**
     * 客户账户
     */
    private String customerNo;

    /**
     * 客户账号
     */
    private List<String> userNos;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 短链地址
     */
    private String shortLinkAddress;

    /**
     * 域名
     */
    private String linkDomain;

    /**
     * 审核状态  0：待审核 1：审核通过、免审 2：拒绝
     */
    private Byte checkStatus;

    /**
     * 所属分组 默认1001
     */
    private String groupNo;

    /**
     * 类型类型 0：单一 1：批量
     */
    private Byte linkType;

    /**
     * 数据来源 0:管理后台  1：门户
     */
    private Byte source;

    public ShortLinkCustomerConfigExample buildExample() {
        ShortLinkCustomerConfigExample example = new ShortLinkCustomerConfigExample();
        ShortLinkCustomerConfigExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(getOrderBy());
        if (StrUtil.isNotEmpty(platformNo)) {
            criteria.andPlatformNoEqualTo(platformNo);
        }
        if (CollUtil.isNotEmpty(linkNos)) {
            criteria.andLinkNoIn(linkNos);
        } else if (StrUtil.isNotEmpty(linkNo)) {
            criteria.andLinkNoEqualTo(linkNo);
        }
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        if (StrUtil.isNotEmpty(customerNo)) {
            criteria.andCustomerNoEqualTo(customerNo);
        }
        if (CollUtil.isNotEmpty(userNos)) {
            criteria.andUserNoIn(userNos);
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
        if (StrUtil.isNotEmpty(linkDomain)) {
            criteria.andLinkDomainEqualTo(linkDomain);
        }
        if (StringUtils.isNotBlank(linkName)) {
            criteria.andLinkNameLike("%" + linkName + "%");
        }

        return example;
    }
}
