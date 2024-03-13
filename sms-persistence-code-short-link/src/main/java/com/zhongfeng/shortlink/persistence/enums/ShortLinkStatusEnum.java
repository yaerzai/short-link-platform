package com.zhongfeng.shortlink.persistence.enums;

import lombok.Getter;

/**
 * 链接配置状态
 *
 * @author yuzc
 */
@Getter
public enum ShortLinkStatusEnum {
    DEFAULT((byte) 0, "正常"),
    CLOSE((byte) 1, "暂停");

    private byte codeId;
    private String codeName;

    ShortLinkStatusEnum(byte codeId, String codeName) {
        this.codeId = codeId;
        this.codeName = codeName;
    }

    public static ShortLinkStatusEnum parse(byte codeId) {
        for (ShortLinkStatusEnum typeEnum : values()) {
            if (typeEnum.codeId == codeId) {
                return typeEnum;
            }
        }
        return null;
    }

}
