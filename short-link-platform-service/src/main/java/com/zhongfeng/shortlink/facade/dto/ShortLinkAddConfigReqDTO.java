package com.zhongfeng.shortlink.facade.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 添加长链配置请求
 * @author codescript.build
 */
@ApiModel("添加长链配置请求")
@Data
public class ShortLinkAddConfigReqDTO extends ShortLinkBaseDTO {

    private static final long serialVersionUID = 7449705185718647135L;

    @ApiModelProperty(value = "平台编号")
    private String platform;

    @ApiModelProperty(value = "短链域名")
    private String linkDomain;

    @ApiModelProperty(value = "手机号透传参数名")
    private String mobileNoParam;

    @ApiModelProperty(value = "活动名称")
    private String linkName;

    @ApiModelProperty(value = "客户号")
    private String customerNo;

    @ApiModelProperty(value = "客户账号")
    private String userNo;

    @ApiModelProperty(value = "真实长链接地址")
    private String address;

    @ApiModelProperty(value = "过期时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expirationTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "短链类型")
    private Byte linkType;

}
