package com.zhongfeng.shortlink.persistence.dataservice.bo;

import com.zhongfeng.shortlink.persistence.model.ShortLinkReportDay;
import lombok.*;

import java.io.Serializable;

/**
 * @author codescript.build
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShortLinkReportDayDataBO implements Serializable {
    private static final long serialVersionUID = 2501753218683843034L;

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
     * 手机号码归属运营商
     */
    private String mobileIsp;

    /**
     * 手机号码归属省
     */
    private String mobileProvince;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 设备品牌
     */
    private Byte brand;

    /**
     * pv数量
     */
    private Integer pvNum;

    /**
     * uv数量
     */
    private Integer uvNum;

    /**
     * ip数量
     */
    private Integer ipNum;

    /**
     * 统计日期 
     */
    private String reportDate;

    public static ShortLinkReportDayDataBO buildByPO(ShortLinkReportDay po){
        ShortLinkReportDayDataBO bo = new ShortLinkReportDayDataBO();
        bo.setLinkNo(po.getLinkNo());
        bo.setCustomerNo(po.getCustomerNo());
        bo.setUserNo(po.getUserNo());
        bo.setMobileIsp(po.getMobileIsp());
        bo.setMobileProvince(po.getMobileProvince());
        bo.setBrowser(po.getBrowser());
        bo.setOs(po.getOs());
        bo.setBrand(po.getBrand());
        bo.setPvNum(po.getPvNum());
        bo.setUvNum(po.getUvNum());
        bo.setIpNum(po.getIpNum());
        bo.setReportDate(po.getReportDate());
        return bo;
    }

    public ShortLinkReportDay buildPO(){
        ShortLinkReportDay po = new ShortLinkReportDay();
        po.setLinkNo(linkNo);
        po.setCustomerNo(customerNo);
        po.setUserNo(userNo);
        po.setMobileIsp(mobileIsp);
        po.setMobileProvince(mobileProvince);
        po.setBrowser(browser);
        po.setOs(os);
        po.setBrand(brand);
        po.setPvNum(pvNum);
        po.setUvNum(uvNum);
        po.setIpNum(ipNum);
        po.setReportDate(reportDate);
        return po;
    }
}

