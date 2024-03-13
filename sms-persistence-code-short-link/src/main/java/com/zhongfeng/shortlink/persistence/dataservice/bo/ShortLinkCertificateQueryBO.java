package com.zhongfeng.shortlink.persistence.dataservice.bo;

import com.zhongfeng.common.db.model.BaseQueryBO;
import com.zhongfeng.shortlink.persistence.model.ShortLinkCertificateExample;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author codescript.build
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class ShortLinkCertificateQueryBO extends BaseQueryBO {
    private static final long serialVersionUID = 5110101795501923643L;

    private String uniqueLongLink;

    private String customerNo;

    private String userNo;

    private Byte type;

    private Byte status;

    public ShortLinkCertificateExample buildExample() {
        ShortLinkCertificateExample example = new ShortLinkCertificateExample();
        example.setOrderByClause(getOrderBy());
        ShortLinkCertificateExample.Criteria criteria = example.createCriteria();
        if (uniqueLongLink != null) {
            criteria.andUniqueLongLinkEqualTo(uniqueLongLink);
        }
        if (customerNo != null) {
            criteria.andCustomerNoEqualTo(customerNo);
        }
        if (userNo != null) {
            criteria.andUserNoEqualTo(userNo);
        }
        if (type != null) {
            criteria.andTypeEqualTo(type);
        }
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        return example;
    }
}
