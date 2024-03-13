package com.zhongfeng.shortlink.service;

import com.zhongfeng.shortlink.service.bo.OperatorsInfoBO;

/**
 * 手机运营商服务
 *
 * @auther fanjun
 */
public interface MobileOpertorsService {

    OperatorsInfoBO matchMobile(String mobileNo);
}
