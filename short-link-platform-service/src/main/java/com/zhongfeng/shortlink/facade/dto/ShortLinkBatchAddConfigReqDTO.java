package com.zhongfeng.shortlink.facade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 批量添加长链配置请求
 * @author codescript.build
 */
@ApiModel("批量添加长链配置请求")
@Data
public class ShortLinkBatchAddConfigReqDTO extends ShortLinkAddConfigReqDTO{

    @ApiModelProperty(value = "批量真实长链接地址")
    private List<String> batchAddress;

}
