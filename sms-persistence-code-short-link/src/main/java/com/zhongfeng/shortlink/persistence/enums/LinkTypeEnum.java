package com.zhongfeng.shortlink.persistence.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 链接类型枚举
 * @author fanjun
 */
@AllArgsConstructor
@Getter
public enum LinkTypeEnum {

    /**
     * 单一
     */
    SINGLE((byte) 0, "单一"),
    /**
     * 批量
     */
    BATCH((byte) 1, "批量"),
    /**
     * 批量(多链接地址)
     */
    MULTIPLE_LINK_BATCH((byte) 2, "多个链接地址");
    
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
    public static LinkTypeEnum getDefault() {
        return SINGLE;
    }

    /**
     * 解析
     * @param codeId codeId
     * @return 值
     */
    public static LinkTypeEnum parse(Byte codeId) {
        if (codeId == null) {
            return getDefault();
        }
        return Arrays.stream(values()).filter(item->item.eq(codeId)).findAny().orElse(getDefault());
    }

}
