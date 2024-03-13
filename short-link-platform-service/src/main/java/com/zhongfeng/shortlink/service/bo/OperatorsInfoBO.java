package com.zhongfeng.shortlink.service.bo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yuzc
 * @Description: 手机运营商信息
 * @date 2019/1/5 16:54
 */
@Setter
@Getter
@Builder
@ToString
public class OperatorsInfoBO {
    /**
     * 手机号
     */
    private String mobileNo;
    /**
     * 运营商 1-移动、2-联通、3-电信
     */
    private Byte operatorType;
    /**
     * 所属省份
     */
    private String province;
    /**
     * 所属城市
     */
    private String city;

}
