package com.zhongfeng.shortlink.persistence.dataservice.bo;

import cn.hutool.core.util.StrUtil;
import com.zhongfeng.common.db.model.BaseQueryBO;
import com.zhongfeng.shortlink.persistence.model.ShortLinkInfoExample;
import lombok.*;

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
public class ShortLinkInfoQueryBO extends BaseQueryBO {
    private static final long serialVersionUID = 5245655845124597775L;
    /**
     * 短链编号
     */
    private String shortLinkNo;

    /**
     * 链接编号
     */
    private String linkNo;
    /**
     * 短链域名
     */
    private String linkDomain;
    /**
     * 手机号码
     */
    private String mobileNo;
    /**
     * 最后一个id
     */
    private Long lastId;

    /**
     * 分表时间
     */
    private String tableTime;


    public ShortLinkInfoExample buildExample() {
        ShortLinkInfoExample example = new ShortLinkInfoExample();
        example.setOrderByClause(getOrderBy());
        ShortLinkInfoExample.Criteria criteria = example.createCriteria();
        if (StrUtil.isNotEmpty(shortLinkNo)) {
            criteria.andShortLinkNoEqualTo(shortLinkNo);
        }
        if (StrUtil.isNotEmpty(linkNo)) {
            criteria.andLinkNoEqualTo(linkNo);
        }
        if (StrUtil.isNotEmpty(mobileNo)) {
            criteria.andMobileNoEqualTo(mobileNo);
        }
        if (StrUtil.isNotEmpty(linkDomain)) {
            criteria.andLinkDomainEqualTo(linkDomain);
        }
        if (lastId != null) {
            criteria.andIdGreaterThan(lastId);
        }
        if (StrUtil.isNotEmpty(tableTime)) {
            criteria.andTableTimeEqualTo(tableTime);
        }
        return example;
    }
}
