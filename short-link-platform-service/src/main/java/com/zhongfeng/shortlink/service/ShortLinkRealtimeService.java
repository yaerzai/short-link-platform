package com.zhongfeng.shortlink.service;

import com.zhongfeng.shortlink.api.dto.ShortLinkCustomerConfigDetailReqDTO;
import com.zhongfeng.shortlink.api.dto.ShortLinkCustomerConfigQueryRespDTO;
import com.zhongfeng.shortlink.facade.dto.*;

import java.util.List;

/**
 * 短链实时服务
 * @author codescript.build
 */
public interface ShortLinkRealtimeService {

    /**
     * 添加长链配置
     * @param reqDTO
     * @return
     */
    ShortLinkAddConfigRespDTO addConfig(ShortLinkAddConfigReqDTO reqDTO);

    /**
     * 添加长链配置
     * @param reqDTO
     * @return
     */
    List<ShortLinkAddConfigRespDTO> batchAddConfig(ShortLinkBatchAddConfigReqDTO reqDTO);

    /**
     * 生成短链信息
     * @param reqDTO
     * @return
     */
    ShortLinkGenShortInfoRespDTO genShortInfo(ShortLinkGenShortInfoReqDTO reqDTO);

    /**
     * 批量生成短链信息
     * @param reqDTO
     * @return
     */
    ShortLinkGenShortInfoRespDTO batchGenShortInfo(ShortLinkBatchGenShortInfoReqDTO reqDTO);

    /**
     * 根据指定URL生成短链信息
     * @param reqDTO
     * @return
     */
    ShortLinkGenShortInfoRespDTO genShortInfoAndAssignUrl(ShortLinkGenShortAssignUrlReqDTO reqDTO);

    /**
     * 生成短链信息
     * @param reqDTO
     * @return
     */
    ShortLinkCustomerConfigQueryRespDTO queryDetail(ShortLinkCustomerConfigDetailReqDTO reqDTO);

}
