package com.zhongfeng.shortlink.facade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 添加长链配置响应
 * @author codescript.build
 */
@ApiModel("添加长链配置响应")
@Data
public class ShortLinkAddConfigRespDTO extends ShortLinkBaseDTO {

    private static final long serialVersionUID = 3106754634734419924L;

    @ApiModelProperty(value = "长链批次")
    private String linkNo;

}
