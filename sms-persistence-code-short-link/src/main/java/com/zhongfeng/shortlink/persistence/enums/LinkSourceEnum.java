package com.zhongfeng.shortlink.persistence.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 短链来源
 * @author fanjun
 */
@AllArgsConstructor
@Getter
public enum LinkSourceEnum {

    /**
     * 单一
     */
    manager((byte) 0, "管理后台"),
    /**
     * 批量
     */
    portal((byte) 1, "门户"),

    marketing((byte) 3, "营销平台");


    
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
    public static LinkSourceEnum getDefault() {
        return manager;
    }

    /**
     * 解析
     * @param codeId codeId
     * @return 值
     */
    public static LinkSourceEnum parse(Byte codeId) {
        if (codeId == null) {
            return getDefault();
        }
        return Arrays.stream(values()).filter(item->item.eq(codeId)).findAny().orElse(getDefault());
    }

}
