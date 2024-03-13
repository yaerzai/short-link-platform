package com.zhongfeng.shortlink.facade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 生成短链信息响应
 * @author codescript.build
 */
@ApiModel("生成短链信息响应")
@Data
public class ShortLinkGenShortInfoRespDTO extends ShortLinkBaseDTO {

    private static final long serialVersionUID = 6807639034450986836L;

    @ApiModelProperty(value = "长链批次")
    private String linkNo;

    @ApiModelProperty(value = "链接列表")
    private List<ShortLinkInfoDTO> linkList;

}
