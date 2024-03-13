package com.zhongfeng.shortlink.service.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author gan.feng
 */
@ApiModel("短链访问")
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkLookupBO implements Serializable {
    private static final long serialVersionUID = 4337375052319846587L;
    @ApiModelProperty(value = "链接编号", required = true)
    @NotBlank
    private String linkNo;

    @ApiModelProperty(value = "请求IP", required = true)
    @NotBlank
    private String requestIp;

    @ApiModelProperty(value = "请求终端:IOS、Android、PC", required = true)
    @NotBlank
    private String requestTerminal;

    @ApiModelProperty(value = "请求域名", required = true)
    @NotBlank
    private String requestServerName;

    @ApiModelProperty(value = "UserAgent", required = true)
    @NotBlank
    private String userAgent;

    @ApiModelProperty(value = "PV访问日期")
    private String pvDate;
}
