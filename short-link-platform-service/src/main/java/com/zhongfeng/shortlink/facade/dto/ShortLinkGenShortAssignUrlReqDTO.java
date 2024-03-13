package com.zhongfeng.shortlink.facade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 生成短链信息请求
 * @author codescript.build
 */
@ApiModel("根据指定URL生成短链信息请求")
@Data
public class ShortLinkGenShortAssignUrlReqDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "长链批次")
    private String linkNo;

    @ApiModelProperty(value = "短链数据类型 以英文逗号分割,")
    private String shortLineType;

    @ApiModelProperty(value = "手机号列表")
    private List<String> mobileList;

    @ApiModelProperty(value = "URL列表")
    private List<String> urlList;
}
