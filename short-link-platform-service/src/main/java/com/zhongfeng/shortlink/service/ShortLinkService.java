package com.zhongfeng.shortlink.service;


import com.zhongfeng.shortlink.api.dto.ShortLinkBatchAddDTO;
import com.zhongfeng.shortlink.service.bo.ShortLinkLookupBO;

/**
 * @author yuzc
 * @date 2020/2/23 12:59
 */
public interface ShortLinkService {


    /**
     * 批量号码导入
     * @param addDTO
     */
    void batchMobileFile(ShortLinkBatchAddDTO addDTO);

//    /**
//     * 生成短链数据
//     *
//     * @param shortLinkGenerateReqDTO
//     * @throws Exception
//     */
//    void generate(ShortLinkGenerateReqDTO shortLinkGenerateReqDTO);

    /**
     * 通过key找到原地址
     * @param linkLookupBO
     * @return
     */
    String lookup(ShortLinkLookupBO linkLookupBO);

//    /**
//     * 生成短链接
//     * @param reqDTO
//     * @return
//     */
//    String genShortLink(GenShortLinkReqDTO reqDTO);

    /**
     * 判断
     * @param ip
     * @return
     */
    boolean isPass(String ip,String userAgent);

}
