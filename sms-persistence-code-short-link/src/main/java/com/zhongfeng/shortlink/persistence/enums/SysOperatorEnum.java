package com.zhongfeng.shortlink.persistence.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 操作系统
 *
 * @author yuzc
 * @date 2020/12/18
 */
@Getter
public enum SysOperatorEnum {
    unknown("unknown", 0),
    android("Android", 1),
    iPhoneOS("iPhoneOS", 2),
    HarmonyOS("HarmonyOS", 3),
    fuchsiaOS("FuchsiaOS", 4),
    windowsPhone("WindowsPhone", 5),
    symbian("Symbian", 6);;
    /**
     * 状态
     */
    private String name;
    /**
     * 结果
     */
    private Byte codeId;


    SysOperatorEnum(String name, int codeId) {
        this.codeId = (byte) codeId;
        this.name = name;
    }

    /**
     * 比较
     *
     * @param name
     * @return
     */
    public boolean eq(String name) {
        return StringUtils.isBlank(name) ? isDefault() : name.toLowerCase().contains(this.name.toLowerCase());
    }

    /**
     * 是否为默认
     *
     * @return
     */
    public boolean isDefault() {
        return this == getDefault();
    }

    /**
     * 获取默认
     *
     * @return
     */
    public static SysOperatorEnum getDefault() {
        return unknown;
    }

    /**
     * 解析
     *
     * @param name
     * @return
     */
    public static SysOperatorEnum parse(String name) {
        if (StringUtils.isBlank(name)) {
            return getDefault();
        }

        for (SysOperatorEnum item : values()) {
            if (name.toLowerCase().contains(item.getName().toLowerCase())) {
                return item;
            }
        }
        return getDefault();
    }

}
