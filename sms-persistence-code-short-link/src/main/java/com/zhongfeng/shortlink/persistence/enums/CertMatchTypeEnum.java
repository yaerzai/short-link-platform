package com.zhongfeng.shortlink.persistence.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 长链接匹配类型
 * @author fanjun
 */
@AllArgsConstructor
@Getter
public enum CertMatchTypeEnum {

    /**
     * 精确匹配
     */
    AccurateMatch((byte) 0, "精确匹配"),
    /**
     * 域名匹配
     */
    DomainMatch((byte) 1, "域名匹配");
    
    private final byte codeId;
    private final String codeName;

    /**
     * 比较
     * @param codeId codeId
     * @return 比较结果
     */
    public boolean eq(Byte codeId) {
        return this.codeId== codeId;
    }

    /**
     * 解析
     * @param codeId codeId
     * @return 值
     */
    public static CertMatchTypeEnum parse(Byte codeId) {
        if (codeId == null) {
            return null;
        }
        return Arrays.stream(values()).filter(item->item.eq(codeId)).findAny().orElse(null);
    }

}
