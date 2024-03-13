package com.zhongfeng.shortlink.facade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 批量生成短链信息请求
 * @author codescript.build
 */
@ApiModel("批量生成短链信息请求")
@Data
public class ShortLinkBatchGenShortInfoReqDTO extends ShortLinkBaseDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "长链批次")
    private List<String> linkNoList;

    @ApiModelProperty(value = "手机号列表")
    private List<String> mobileList;

    @ApiModelProperty(value = "短链数据类型 以英文逗号分割,")
    private String shortLineType;

}
