package com.zhongfeng.shortlink.service;

import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkBatchInfoDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCertificateDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCustomerConfigDataBO;
import com.zhongfeng.sms.persistence.enums.SysParamEnum;

import java.util.Map;

/**
 * @author yuzc
 * @date 2020/2/26 18:41
 */
public interface DataService {

    /**
     * 系统参数
     *
     * @param sysParamEnum
     * @return
     */
    int getSysParamInt(SysParamEnum sysParamEnum);

    /**
     * 系统参数
     *
     * @param sysParamEnum
     * @return
     */
    String getSysParam(SysParamEnum sysParamEnum);

    ShortLinkCertificateDataBO getShorLinkCertificate();

    /**
     * 获取IP状态
     * @param ipAddr
     * @return
     */
    Boolean ipStatus(String ipAddr);

//    /**
//     * 获取短链信息
//     * @param shortLinkNo
//     * @return
//     */
//    ShortLinkInfoDataBO getShortLinkInfo(String shortLinkNo);

    /**
     * 获取客户链接信息
     * @param linkNo
     * @return
     */
    ShortLinkCustomerConfigDataBO getCustomerConfigInfo(String linkNo);

    /**
     * 获取客户链接信息
     * @param address
     * @return
     */
    ShortLinkCustomerConfigDataBO getCustomerConfigInfoByAddress(String address);

    /**
     * 链接批次信息
     * @param linkNo
     * @param linkBatchNo
     * @return
     */
    ShortLinkBatchInfoDataBO getShortLinkBatchInfoDataBO(String linkNo);

    /**
     * 获取所有手机品牌
     * <br/>
     * key=品牌标识 value=品牌名称
     */
    Map<Integer, String> getAllBrand();
}
