package com.zhongfeng.shortlink.persistence.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态枚举
 * @author fanjun
 */
@AllArgsConstructor
@Getter
public enum StatusEnum {

    /**
     * 启用
     */
    ENABLE((byte) 1, "启用"),
    /**
     * 禁用
     */
    DISABLE((byte) 0, "禁用");
    
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
    public static StatusEnum getDefault() {
        return DISABLE;
    }

    /**
     * 解析
     * @param codeId codeId
     * @return 值
     */
    public static StatusEnum parse(Byte codeId) {
        if (codeId == null) {
            return getDefault();
        }
        return Arrays.stream(values()).filter(item->item.eq(codeId)).findAny().orElse(getDefault());
    }

}
