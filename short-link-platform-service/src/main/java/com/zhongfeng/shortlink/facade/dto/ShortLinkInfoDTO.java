package com.zhongfeng.shortlink.facade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("短链信息")
@Data
public class ShortLinkInfoDTO {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "短链接")
    private String shortLinkNo;

    @ApiModelProperty(value = "短链数据类型")
    private String shortLineType;

}
