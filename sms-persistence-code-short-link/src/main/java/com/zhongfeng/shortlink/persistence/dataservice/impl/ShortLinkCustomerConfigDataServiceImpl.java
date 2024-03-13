package com.zhongfeng.shortlink.persistence.dataservice.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;
import com.zhongfeng.common.base.exception.BusinessException;
import com.zhongfeng.common.db.model.PageData;
import com.zhongfeng.common.redis.annotation.CacheRemove;
import com.zhongfeng.common.redis.annotation.Cached;
import com.zhongfeng.common.redis.annotation.Param;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkCustomerConfigDataService;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCustomerConfigDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCustomerConfigQueryBO;
import com.zhongfeng.shortlink.persistence.mapper.ShortLinkCustomerConfigMapper;
import com.zhongfeng.shortlink.persistence.model.ShortLinkCustomerConfig;
import com.zhongfeng.shortlink.persistence.model.ShortLinkCustomerConfigExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuzc
 * @date 2020/2/23 12:40
 */
@Service
@Slf4j
public class ShortLinkCustomerConfigDataServiceImpl implements ShortLinkCustomerConfigDataService {
    @Resource
    private ShortLinkCustomerConfigMapper mapper;

    @Override
    public PageData<ShortLinkCustomerConfigDataBO> query(ShortLinkCustomerConfigQueryBO queryBO) {
        ShortLinkCustomerConfigExample example = queryBO.buildExample();
        Page<ShortLinkCustomerConfig> shortLinkCustomerConfigPage = mapper.selectByExampleWithRowbounds(example, new PageRowBounds(queryBO.getPageNo(), queryBO.getPageSize()));
        PageData<ShortLinkCustomerConfigDataBO> pageData = PageData.getPageData(shortLinkCustomerConfigPage);
        if (!CollectionUtils.isEmpty(shortLinkCustomerConfigPage)) {
            pageData.setData(shortLinkCustomerConfigPage.stream().map(item -> ShortLinkCustomerConfigDataBO.buildByPO(item)).collect(Collectors.toList()));
        }
        return pageData;
    }

    @Override
    public boolean add(ShortLinkCustomerConfigDataBO dataBO) {
        return mapper.insertSelective(dataBO.buildPO()) == 1;
    }

    @CacheRemove(cacheName = "ShortLinkCustomerConfigDataBO", key = "dataBO.linkNo")
    @Override
    public boolean upd(@Param("dataBO") ShortLinkCustomerConfigDataBO dataBO) {
        String linkNo = dataBO.getLinkNo();
        if (StringUtils.isBlank(linkNo)) {
            log.error("删除条件不能为空 {}", linkNo);
            return false;
        }
        ShortLinkCustomerConfigExample example = new ShortLinkCustomerConfigExample();
        example.createCriteria().andLinkNoEqualTo(linkNo);
        return mapper.updateByExampleSelective(dataBO.buildPO(), example) == 1;
    }

    @Cached(cacheName = "ShortLinkCustomerConfigDataBO", key = "linkNo", cachedNull = false)
    @Override
    public ShortLinkCustomerConfigDataBO get(@Param("linkNo") String linkNo) {
        ShortLinkCustomerConfigExample example = new ShortLinkCustomerConfigExample();
        example.createCriteria().andLinkNoEqualTo(linkNo);
        List<ShortLinkCustomerConfig> list = mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return ShortLinkCustomerConfigDataBO.buildByPO(list.get(0));
    }

    @Override
    public ShortLinkCustomerConfigDataBO getByShortAddress(String address) {
        ShortLinkCustomerConfigExample example = new ShortLinkCustomerConfigExample();
        example.createCriteria().andShortLinkAddressEqualTo(address);
        List<ShortLinkCustomerConfig> list = mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return ShortLinkCustomerConfigDataBO.buildByPO(list.get(0));
    }

    @CacheRemove(cacheName = "ShortLinkCustomerConfigDataBO", key = "linkNo")
    @Override
    public boolean delete(@Param("linkNo") String linkNo) {
        if (StringUtils.isBlank(linkNo)) {
            log.error("删除条件不能为空 {}", linkNo);
            return false;
        }
        ShortLinkCustomerConfigExample example = new ShortLinkCustomerConfigExample();
        example.createCriteria().andLinkNoEqualTo(linkNo);
        return mapper.deleteByExample(example) == 1;
    }

    @Override
    public ShortLinkCustomerConfig isExistSameLinkNo(ShortLinkCustomerConfigDataBO dateBO) {
        if (StringUtils.isBlank(dateBO.getCustomerNo()) || StringUtils.isBlank(dateBO.getUserNo()) || StringUtils.isBlank(dateBO.getAddress())) {
            log.error("判断条件不能为空");
            throw new BusinessException("判断条件不能为空");
        }
        List<ShortLinkCustomerConfig> shortLinkCustomerConfigs = mapper.selectByExample(dateBO.buildExample());
        if(CollectionUtils.isEmpty(shortLinkCustomerConfigs)){
            return null;
        }
        return shortLinkCustomerConfigs.get(0);
    }
}
