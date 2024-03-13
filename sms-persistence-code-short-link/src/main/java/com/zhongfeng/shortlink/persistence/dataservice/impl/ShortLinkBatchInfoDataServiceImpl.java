package com.zhongfeng.shortlink.persistence.dataservice.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;
import com.zhongfeng.common.db.model.PageData;
import com.zhongfeng.common.redis.annotation.CacheRemove;
import com.zhongfeng.common.redis.annotation.Cached;
import com.zhongfeng.common.redis.annotation.Param;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkBatchInfoDataService;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkBatchInfoDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkBatchInfoQueryBO;
import com.zhongfeng.shortlink.persistence.mapper.ShortLinkBatchInfoMapper;
import com.zhongfeng.shortlink.persistence.model.ShortLinkBatchInfo;
import com.zhongfeng.shortlink.persistence.model.ShortLinkBatchInfoExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuzc
 * @date 2020/2/23 14:50
 */
@Slf4j
@Service
public class ShortLinkBatchInfoDataServiceImpl implements ShortLinkBatchInfoDataService {
    @Resource
    private ShortLinkBatchInfoMapper mapper;

    @Override
    public PageData<ShortLinkBatchInfoDataBO> query(ShortLinkBatchInfoQueryBO queryBO) {
        ShortLinkBatchInfoExample example = queryBO.buildExample();
        Page<ShortLinkBatchInfo> shortLinkBatchInfoPage = mapper.selectByExampleWithRowbounds(example, new PageRowBounds(queryBO.getPageNo(), queryBO.getPageSize()));
        PageData<ShortLinkBatchInfoDataBO> pageData = PageData.getPageData(shortLinkBatchInfoPage);
        if (!CollectionUtils.isEmpty(shortLinkBatchInfoPage)) {
            pageData.setData(shortLinkBatchInfoPage.stream().map(item -> ShortLinkBatchInfoDataBO.buildByPO(item)).collect(Collectors.toList()));
        }
        return pageData;
    }

    @Override
    public boolean add(ShortLinkBatchInfoDataBO dataBO) {
        return mapper.insertSelective(dataBO.buildPO()) == 1;
    }

    @Cached(cacheName = "ShortLinkBatchInfoDataBO", key = "linkNo", cachedNull = false)
    @Override
    public ShortLinkBatchInfoDataBO get(@Param("linkNo") String linkNo) {
        ShortLinkBatchInfoExample example = new ShortLinkBatchInfoExample();
        example.createCriteria().andLinkNoEqualTo(linkNo);
        List<ShortLinkBatchInfo> list = mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return ShortLinkBatchInfoDataBO.buildByPO(list.get(0));
    }

    @CacheRemove(cacheName = "ShortLinkBatchInfoDataBO", key = "linkBatchNo")
    @Override
    public boolean delete(String linkNo, @Param("linkBatchNo") String linkBatchNo) {
        if (StringUtils.isBlank(linkNo) || StringUtils.isBlank(linkBatchNo)) {
            log.error("删除条件不能为空 {} {}", linkNo, linkBatchNo);
            return false;
        }
        ShortLinkBatchInfoExample example = new ShortLinkBatchInfoExample();
        example.createCriteria().andLinkNoEqualTo(linkNo).andLinkBatchNoEqualTo(linkBatchNo);
        return mapper.deleteByExample(example) == 1;
    }

    @CacheRemove(cacheName = "ShortLinkBatchInfoDataBO", key = "*")
    @Override
    public boolean delete(String linkNo) {
        if (StringUtils.isBlank(linkNo)) {
            log.error("删除条件不能为空 {}", linkNo);
            return false;
        }
        ShortLinkBatchInfoExample example = new ShortLinkBatchInfoExample();
        example.createCriteria().andLinkNoEqualTo(linkNo);
        return mapper.deleteByExample(example) >= 1;
    }
}
