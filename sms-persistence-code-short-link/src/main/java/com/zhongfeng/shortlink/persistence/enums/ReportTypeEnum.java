package com.zhongfeng.shortlink.persistence.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ReportTypeEnum {

    ALL(0, "总报表"),
    OPERATOR(1, "运营商"),
    PROVINCE(2, "省份"),
    OS(3, "操作系统"),
    BRAND(4, "设备品牌");

    private Integer codeId;

    private String codeName;

    public static ReportTypeEnum parse(Integer codeId) {
        return Arrays.stream(values()).filter(item-> item.codeId.equals(codeId)).findAny().orElse(null);
    }

    public String getGroupBy() {
        switch (this) {
            case PROVINCE:
                return "mobile_province";
            case OPERATOR:
                return "mobile_isp";
            case OS:
                return "os";
            case BRAND:
                return "brand";
            default:
                return "";
        }
    }
}
