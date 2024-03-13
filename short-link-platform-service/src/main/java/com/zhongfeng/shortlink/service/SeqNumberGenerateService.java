package com.zhongfeng.shortlink.service;

/**
 * @author yuzc
 * @Description: 序列号生成器
 * @date 2019/1/1 14:47
 */
public interface SeqNumberGenerateService {


    /**
     * 生成链接编号
     *
     * @return
     */
    String getLinkNo();

    /**
     * 生成短链编号
     *
     * @return
     */
    String getShortLinkNo();
}
