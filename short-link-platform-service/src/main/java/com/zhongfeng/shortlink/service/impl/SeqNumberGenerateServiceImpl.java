package com.zhongfeng.shortlink.service.impl;

import com.zhongfeng.common.base.utils.DateUtils;
import com.zhongfeng.common.redis.service.NumberGeneratorService;
import com.zhongfeng.shortlink.service.SeqNumberGenerateService;
import com.zhongfeng.shortlink.utils.BaseTo62BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yuzc
 * @Description: 业务序列号生成
 * @date 2018/12/27 18:19
 */
@Slf4j
@Service
public class SeqNumberGenerateServiceImpl implements SeqNumberGenerateService {
    @Resource
    private NumberGeneratorService numberGeneratorService;

    @Override
    public String getLinkNo() {
        String currDate = DateUtils.getCurrDate(DateUtils.DateFormatEnum.YYYY_MM_DD_HH_MM_SS);
        String key = "RedisLinkNo";
        long seqNum = numberGeneratorService.genSeqNum(key, 8999) + (long) 1000;
        return currDate + seqNum;
    }

    @Override
    public String getShortLinkNo() {
        String key = "ShortLinkNo";
        long seqNum = numberGeneratorService.genSeqNum(key, Long.MAX_VALUE, 0);
        return BaseTo62BaseUtils.base62Encode(seqNum);
    }
}
