package com.zhongfeng.shortlink.enums;

import com.zhongfeng.common.base.exception.ServerCode;
import lombok.Getter;

/**
 * @author hu.zhiyu
 * 2019/1/5 11:18
 */
@Getter
public enum BizErrorCodeEnum implements ServerCode {
    /**
     * 请求成功
     */
    SUCCESS("0000", "请求成功"),

    //========================================================================//
    //                              系统错误
    //========================================================================//
    UNKNOWN_EXCEPTION("9999", "系统未知错误"),
    NO_MD5_ALGORITHM("8000", "系统不存在Md5算法"),

    //========================================================================//
    //                              请求校验
    //========================================================================//
    REQUEST_PARAM_ILLEGAL("0001", "请求参数非法"),
    REQUEST_URL_ILLEGAL("0002", "请求URL错误"),

    //========================================================================//
    //          业务错误（需要带上系统编码，1000开始）
    //========================================================================//

    ;
    /**
     * 操作代码
     */
    private final String code;

    /**
     * 描述
     */
    private final String msg;

    BizErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     */
    public static BizErrorCodeEnum getByCode(String code) {
        for (BizErrorCodeEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}
