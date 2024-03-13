package com.zhongfeng.shortlink.service.impl;

import com.zhongfeng.shortlink.service.MobileOpertorsService;
import com.zhongfeng.shortlink.service.bo.OperatorsInfoBO;
import com.zhongfeng.sms.persistence.dataservice.SysMobileSegmentDataService;
import com.zhongfeng.sms.persistence.dataservice.SysMobileTransferDataService;
import com.zhongfeng.sms.persistence.dataservice.bo.business.SysMobileSegmentBusinessBO;
import com.zhongfeng.sms.persistence.dataservice.bo.business.SysMobileTransferBusinessBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 手机运营商服务
 *
 * @auther fanjun
 */
@Service
public class MobileOpertorsServiceImpl implements MobileOpertorsService {

    @Resource
    private SysMobileSegmentDataService mobileSegmentDataService;
    @Resource
    private SysMobileTransferDataService mobileTransferDataService;

    @Override
    public OperatorsInfoBO matchMobile(String mobileNo) {
        if (StringUtils.isBlank(mobileNo) || mobileNo.length() != 11) {
            return null;
        }
        OperatorsInfoBO operatorsInfoBo = OperatorsInfoBO.builder().build();
        // 是否携号转网
        SysMobileTransferBusinessBO sysMobileTransfer = mobileTransferDataService.getCache(mobileNo);
        if (sysMobileTransfer != null) {
            operatorsInfoBo.setMobileNo(mobileNo);
            operatorsInfoBo.setOperatorType(sysMobileTransfer.getTelecomType());
            operatorsInfoBo.setProvince(sysMobileTransfer.getProvince());
            operatorsInfoBo.setCity(sysMobileTransfer.getCity());
            return operatorsInfoBo;
        }
        // 手机卡段
        SysMobileSegmentBusinessBO sysMobileSegment = mobileSegmentDataService.getCache(mobileNo);
        if (sysMobileSegment != null) {
            operatorsInfoBo.setMobileNo(mobileNo);
            operatorsInfoBo.setOperatorType(sysMobileSegment.getTelecomType());
            operatorsInfoBo.setProvince(sysMobileSegment.getProvince());
            operatorsInfoBo.setCity(sysMobileSegment.getCity());
            return operatorsInfoBo;
        }
        return null;
    }
}
