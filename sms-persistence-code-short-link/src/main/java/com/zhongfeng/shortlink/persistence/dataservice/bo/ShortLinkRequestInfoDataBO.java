package com.zhongfeng.shortlink.persistence.dataservice.bo;

import com.zhongfeng.shortlink.persistence.model.ShortLinkRequestInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class ShortLinkRequestInfoDataBO implements Serializable {
    private static final long serialVersionUID = 3204210810714463245L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 短链编号
     */
    private String shortLinkNo;


    /**
     * 短链类型
     */
    private String shortLineType;


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
     * 访问域名
     */
    private String linkDomain;

    /**
     * 手机号码
     */
    private String mobileNo;

    /**
     * 请求日期
     */
    private String tableTime;

    /**
     * UA信息
     */
    private String userAgent;

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
     * IP归属运营商
     */
    private String ipIsp;

    /**
     * IP归属市
     */
    private String ipCity;

    /**
     * 手机号归属地_省份
     */
    private String mobileProvince;

    /**
     * 手机号码归属市
     */
    private String mobileCity;

    /**
     * 手机号码归属运营商
     */
    private String mobileIsp;

    /**
     * IP归属省
     */
    private String ipProvince;

    /**
     * 请求时间
     */
    private Date requestTime;

    /**
     * 请求IP地址
     */
    private String requestIp;

    /**
     * 请求设备类型 PC、Android、IOS
     */
    private String requestDevice;

    /**
     * 创建时间
     */
    private Date createTime;

    public static ShortLinkRequestInfoDataBO buildByPO(ShortLinkRequestInfo po) {
        ShortLinkRequestInfoDataBO shortLinkRequestInfoDataBO = new ShortLinkRequestInfoDataBO();
        shortLinkRequestInfoDataBO.setId(po.getId());
        shortLinkRequestInfoDataBO.setShortLinkNo(po.getShortLinkNo());
        shortLinkRequestInfoDataBO.setLinkDomain(po.getLinkDomain());
        shortLinkRequestInfoDataBO.setLinkNo(po.getLinkNo());
        shortLinkRequestInfoDataBO.setMobileNo(po.getMobileNo());
        shortLinkRequestInfoDataBO.setRequestTime(po.getRequestTime());
        shortLinkRequestInfoDataBO.setRequestIp(po.getRequestIp());
        shortLinkRequestInfoDataBO.setRequestDevice(po.getRequestDevice());
        shortLinkRequestInfoDataBO.setCreateTime(po.getCreateTime());
        shortLinkRequestInfoDataBO.setTableTime(po.getTableTime());
        shortLinkRequestInfoDataBO.setUserAgent(po.getUserAgent());
        shortLinkRequestInfoDataBO.setBrowser(po.getBrowser());
        shortLinkRequestInfoDataBO.setDataValidate(po.getDataValidate());
        shortLinkRequestInfoDataBO.setBrand(po.getBrand());
        shortLinkRequestInfoDataBO.setIpNumber(po.getIpNumber());
        shortLinkRequestInfoDataBO.setIpIsp(po.getIpIsp());
        shortLinkRequestInfoDataBO.setIpCity(po.getIpCity());
        shortLinkRequestInfoDataBO.setMobileProvince(po.getMobileProvince());
        shortLinkRequestInfoDataBO.setMobileCity(po.getMobileCity());
        shortLinkRequestInfoDataBO.setMobileIsp(po.getMobileIsp());
        shortLinkRequestInfoDataBO.setIpProvince(po.getIpProvince());
        return shortLinkRequestInfoDataBO;
    }

    public ShortLinkRequestInfo buildPO() {
        ShortLinkRequestInfo shortLinkRequestInfo = new ShortLinkRequestInfo();
        shortLinkRequestInfo.setShortLinkNo(shortLinkNo);
        shortLinkRequestInfo.setLinkDomain(linkDomain);
        shortLinkRequestInfo.setLinkNo(linkNo);
        shortLinkRequestInfo.setMobileNo(mobileNo);
        shortLinkRequestInfo.setRequestTime(requestTime);
        shortLinkRequestInfo.setRequestIp(requestIp);
        shortLinkRequestInfo.setRequestDevice(requestDevice);
        shortLinkRequestInfo.setTableTime(tableTime);
        shortLinkRequestInfo.setUserAgent(userAgent);
        shortLinkRequestInfo.setBrowser(browser);
        shortLinkRequestInfo.setDataValidate(dataValidate);
        shortLinkRequestInfo.setBrand(brand);
        shortLinkRequestInfo.setIpNumber(ipNumber);
        shortLinkRequestInfo.setIpIsp(ipIsp);
        shortLinkRequestInfo.setIpCity(ipCity);
        shortLinkRequestInfo.setMobileProvince(mobileProvince);
        shortLinkRequestInfo.setShortLineType(shortLineType);
        shortLinkRequestInfo.setMobileCity(mobileCity);
        shortLinkRequestInfo.setMobileIsp(mobileIsp);
        shortLinkRequestInfo.setIpProvince(ipProvince);
        shortLinkRequestInfo.setCustomerNo(customerNo);
        shortLinkRequestInfo.setUserNo(userNo);
        shortLinkRequestInfo.setAddressMd5(addressMd5);
        return shortLinkRequestInfo;
    }
}
