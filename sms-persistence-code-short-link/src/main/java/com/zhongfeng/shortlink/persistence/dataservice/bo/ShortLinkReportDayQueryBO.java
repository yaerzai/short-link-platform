package com.zhongfeng.shortlink.persistence.dataservice.bo;

import com.zhongfeng.common.db.model.BaseQueryBO;
import com.zhongfeng.shortlink.persistence.enums.ReportTypeEnum;
import com.zhongfeng.shortlink.persistence.model.ShortLinkReportDayExample;
import com.zhongfeng.shortlink.persistence.model.ShortLinkReportDayQuery;
import lombok.*;

/**
 * @author codescript.build
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class ShortLinkReportDayQueryBO extends BaseQueryBO {
    private static final long serialVersionUID = 1448573183070650050L;

    private String linkNo;

    private String customerNo;

    private String userNo;

    private String mobileIsp;

    private String mobileProvince;

    private String os;

    private Byte brand;

    private String beginDate;

    private String endDate;

    private Integer reportType;

    public ShortLinkReportDayExample buildExample() {
        ShortLinkReportDayExample example = new ShortLinkReportDayExample();
        example.setOrderByClause(getOrderBy());
        ShortLinkReportDayExample.Criteria criteria = example.createCriteria();
        criteria.andReportDateBetween(beginDate, endDate);
        if (linkNo != null) {
            criteria.andLinkNoEqualTo(linkNo);
        }
        if (customerNo != null) {
            criteria.andCustomerNoEqualTo(customerNo);
        }
        if (userNo != null) {
            criteria.andUserNoEqualTo(userNo);
        }
        if (mobileIsp != null) {
            criteria.andMobileIspEqualTo(mobileIsp);
        }
        if (mobileProvince != null) {
            criteria.andMobileProvinceEqualTo(mobileProvince);
        }
        if (os != null) {
            criteria.andOsEqualTo(os);
        }
        if (brand != null) {
            criteria.andBrandEqualTo(brand);
        }
        return example;
    }

    public ShortLinkReportDayQuery buildQueryPO() {
        ShortLinkReportDayQuery query = new ShortLinkReportDayQuery();
        query.setBeginDate(beginDate);
        query.setEndDate(endDate);
        query.setCustomerNo(customerNo);
        query.setUserNo(userNo);
        query.setLinkNo(linkNo);
        query.setMobileIsp(mobileIsp);
        query.setMobileProvince(mobileProvince);
        query.setOs(os);
        query.setBrand(brand);
        query.setBeginNum((getPageNo() - 1) * getPageSize());
        query.setPageSize(getPageSize());
        query.setGroupBy(ReportTypeEnum.parse(reportType).getGroupBy());
        return query;
    }
}
