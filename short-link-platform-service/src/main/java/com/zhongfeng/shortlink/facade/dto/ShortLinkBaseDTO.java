package com.zhongfeng.shortlink.facade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 短链基本对象
 * @author codescript.build
 */
@ApiModel("短链基本对象")
@Data
public class ShortLinkBaseDTO implements Serializable {

    private static final long serialVersionUID = 1004397212028497694L;

    @ApiModelProperty(value = "时间戳,格式yyyyMMddHHmmss", required = true)
    @NotBlank
    private String timeStamp;

    @ApiModelProperty(value = "签名", required = true)
    @NotBlank
    private String sign;

}
