package com.zhongfeng.shortlink.persistence.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据有效性枚举
 * @author fanjun
 */
@AllArgsConstructor
@Getter
public enum DataValidateEnum {

    /**
     * 初判正常
     */
    INITIAL_NORMAL((byte) 1, "初判正常"),
    /**
     * 疑似无效
     */
    SUSPECT_INVALID((byte) 0, "疑似无效");
    
    private final byte codeId;
    private final String codeName;

    /**
     * 比较
     * @param codeId codeId
     * @return 比较结果
     */
    public boolean eq(Byte codeId) {
        return codeId == null ? isDefault() : this.codeId== codeId;
    }

    /**
     * 是否为默认
     * @return 是否默认
     */
    public boolean isDefault() {
        return this == getDefault();
    }

    /**
     * 获取默认
     * @return 默认值
     */
    public static DataValidateEnum getDefault() {
        return SUSPECT_INVALID;
    }

    /**
     * 解析
     * @param codeId codeId
     * @return 值
     */
    public static DataValidateEnum parse(Byte codeId) {
        if (codeId == null) {
            return getDefault();
        }
        return Arrays.stream(values()).filter(item->item.eq(codeId)).findAny().orElse(getDefault());
    }

}
