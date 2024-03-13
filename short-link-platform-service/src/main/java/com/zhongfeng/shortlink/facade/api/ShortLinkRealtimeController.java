package com.zhongfeng.shortlink.facade.api;

import com.zhongfeng.common.swagger.model.ResultData;
import com.zhongfeng.shortlink.api.dto.ShortLinkCustomerConfigDetailReqDTO;
import com.zhongfeng.shortlink.api.dto.ShortLinkCustomerConfigQueryRespDTO;
import com.zhongfeng.shortlink.facade.dto.*;
import com.zhongfeng.shortlink.service.ShortLinkRealtimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(tags = {"短链实时接口"})
@RestController
@RequestMapping("/api/rt")
public class ShortLinkRealtimeController {

    @Resource
    private ShortLinkRealtimeService shortLinkRealtimeService;
    @Resource
    private ShortLinkConfigController shortLinkConfigController;

    @ApiOperation("添加长链配置")
    @PostMapping("/addConfig")
    public ResultData<ShortLinkAddConfigRespDTO> addConfig(@Valid @RequestBody ShortLinkAddConfigReqDTO reqDTO) {
        return new ResultData(shortLinkRealtimeService.addConfig(reqDTO));
    }

    @ApiOperation("添加长链配置")
    @PostMapping("/batchAddConfig")
    public ResultData<ShortLinkAddConfigRespDTO> batchAddConfig(@Valid @RequestBody ShortLinkBatchAddConfigReqDTO reqDTO) {
        return new ResultData(shortLinkRealtimeService.batchAddConfig(reqDTO));
    }

    @ApiOperation("生成短链信息")
    @PostMapping("/genShortInfo")
    public ResultData<ShortLinkGenShortInfoRespDTO> genShortInfo(@Valid @RequestBody ShortLinkGenShortInfoReqDTO reqDTO) {
        return new ResultData(shortLinkRealtimeService.genShortInfo(reqDTO));
    }

    @ApiOperation("批量生成短链信息")
    @PostMapping("/batchGenShortInfo")
    public ResultData<ShortLinkGenShortInfoRespDTO> batchGenShortInfo(@Valid @RequestBody ShortLinkBatchGenShortInfoReqDTO reqDTO) {
        return new ResultData(shortLinkRealtimeService.batchGenShortInfo(reqDTO));
    }

    @ApiOperation("根据指定URL生成短链信息")
    @PostMapping("/genShortInfoAndAssignUrl")
    public ResultData<ShortLinkGenShortInfoRespDTO> genShortInfoAndAssignUrl(@Valid @RequestBody ShortLinkGenShortAssignUrlReqDTO reqDTO) {
        return new ResultData(shortLinkRealtimeService.genShortInfoAndAssignUrl(reqDTO));
    }

    @ApiOperation("短链数据查询")
    @PostMapping("/queryDetail")
    public ResultData<ShortLinkCustomerConfigQueryRespDTO> queryDetail(@Valid @RequestBody ShortLinkCustomerConfigDetailReqDTO reqDTO) {
        return new ResultData(shortLinkRealtimeService.queryDetail(reqDTO));
    }

}
