package com.zhongfeng.shortlink.persistence.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审核状态枚举
 * @author fanjun
 */
@AllArgsConstructor
@Getter
public enum CheckStatusEnum {

    /**
     * 待审核
     */
    WAIT_CHECK((byte) 0, "待审核"),
    /**
     * 审核通过、免审
     */
    CHECK_PASS((byte) 1, "审核通过、免审"),
    /**
     * 审核拒绝
     */
    CHECK_REJECT((byte) 2, "审核拒绝");
    
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
    public static CheckStatusEnum getDefault() {
        return WAIT_CHECK;
    }

    /**
     * 解析
     * @param codeId codeId
     * @return 值
     */
    public static CheckStatusEnum parse(Byte codeId) {
        if (codeId == null) {
            return getDefault();
        }
        return Arrays.stream(values()).filter(item->item.eq(codeId)).findAny().orElse(getDefault());
    }

}
