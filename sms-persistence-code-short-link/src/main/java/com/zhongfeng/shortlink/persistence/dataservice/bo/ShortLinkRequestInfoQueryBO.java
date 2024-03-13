package com.zhongfeng.shortlink.persistence.dataservice.bo;

import com.zhongfeng.common.db.model.BaseQueryBO;
import com.zhongfeng.shortlink.persistence.model.ShortLinkRequestInfoExample;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class ShortLinkRequestInfoQueryBO extends BaseQueryBO {
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
     * 客户编号
     */
    private String customerNo;

    /**
     * 客户账号
     */
    private String userNo;

    /**
     * 长链接MD5
     */
    private String addressMd5;

    /**
     * 请求开始时间
     */
    private Date startRequestTime;

    /**
     * 请求结束时间
     */
    private Date endRequestTime;

    /**
     * 请求日期
     */
    private String tableTime;



    /**
     * 浏览器
     */
    private String browser;

    /**
     * 数据有效性
     */
    private Byte dataValidate;

    /**
     * 设备品牌
     */
    private Byte brand;

    /**
     * IP十进制数值
     */
    private Long ipNumber;

    /**
     * IP归属省
     */
    private String ipProvince;

    public ShortLinkRequestInfoExample buildExample() {
        ShortLinkRequestInfoExample example = new ShortLinkRequestInfoExample();
        example.setOrderByClause(getOrderBy());
        ShortLinkRequestInfoExample.Criteria criteria = example.createCriteria();
        criteria.andTableTimeEqualTo(tableTime).andCustomerNoEqualTo(customerNo)
                .andUserNoEqualTo(userNo).andAddressMd5EqualTo(addressMd5)
                .andBrandEqualTo(brand).andDataValidateEqualTo(dataValidate)
                .andBrowserEqualTo(browser).andIpNumberEqualTo(ipNumber).andLinkNoEqualTo(linkNo);
        criteria.andCreateTimeGreaterThanOrEqualTo(startRequestTime);
        criteria.andCreateTimeLessThanOrEqualTo(endRequestTime);
        return example;
    }
}