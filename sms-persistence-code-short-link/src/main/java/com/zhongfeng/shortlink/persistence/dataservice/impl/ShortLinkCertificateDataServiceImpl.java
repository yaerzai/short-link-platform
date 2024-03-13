package com.zhongfeng.shortlink.persistence.dataservice.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.Page;
import com.zhongfeng.common.db.model.PageData;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkCertificateDataService;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCertificateDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCertificateQueryBO;
import com.zhongfeng.shortlink.persistence.enums.CertMatchTypeEnum;
import com.zhongfeng.shortlink.persistence.mapper.ShortLinkCertificateMapper;
import com.zhongfeng.shortlink.persistence.model.ShortLinkCertificate;
import com.zhongfeng.shortlink.persistence.model.ShortLinkCertificateExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author codescript.build
 */
@Service
@Slf4j
public class ShortLinkCertificateDataServiceImpl implements ShortLinkCertificateDataService {
    @Resource
    private ShortLinkCertificateMapper shortLinkCertificateMapper;

    @Override
    public PageData<ShortLinkCertificateDataBO> query(ShortLinkCertificateQueryBO dataBO) {
        ShortLinkCertificateExample example = dataBO.buildExample();
        Page<ShortLinkCertificate> query = shortLinkCertificateMapper.selectByExampleWithRowbounds(example, dataBO.getRowBounds());
        PageData<ShortLinkCertificateDataBO> pageData = PageData.getPageData(query);
        if (CollUtil.isNotEmpty(query)) {
            pageData.setData(query.getResult().stream().map(ShortLinkCertificateDataBO::buildByPO).collect(Collectors.toList()));
        }
        return pageData;
    }

    @Override
    public ShortLinkCertificateDataBO get(Long id) {
        ShortLinkCertificateExample example = new ShortLinkCertificateExample();
        example.createCriteria().andIdEqualTo(id);
        List<ShortLinkCertificate> list = shortLinkCertificateMapper.selectByExample(example);
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        return ShortLinkCertificateDataBO.buildByPO(list.get(0));
    }

    @Override
    public boolean add(ShortLinkCertificateDataBO dataBO) {
        ShortLinkCertificate po = dataBO.buildPO();
        po.setUniqueLongLink(SecureUtil.md5(po.getAddress()));
        boolean insertFlag = shortLinkCertificateMapper.insertSelective(po) == 1;
        dataBO.setId(po.getId());
        return insertFlag;
    }

    @Override
    public boolean update(ShortLinkCertificateDataBO dataBO) {
        if (dataBO.getId() == null) {
            log.error("更新条件不能为空: {}", dataBO);
            return false;
        }
        ShortLinkCertificateExample example = new ShortLinkCertificateExample();
        example.createCriteria().andIdEqualTo(dataBO.getId());
        return shortLinkCertificateMapper.updateByExampleSelective(dataBO.buildPO(), example) == 1;
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            log.error("删除条件不能为空: {}", id);
            return false;
        }
        ShortLinkCertificateExample example = new ShortLinkCertificateExample();
        example.createCriteria().andIdEqualTo(id);
        return shortLinkCertificateMapper.deleteByExample(example) == 1;
    }

    @Override
    public int batchDelete(List<Long> idList) {
        if (CollUtil.isEmpty(idList)) {
            log.error("批量删除条件不能为空");
            return 0;
        }
        ShortLinkCertificateExample example = new ShortLinkCertificateExample();
        example.createCriteria().andIdIn(idList);
        return shortLinkCertificateMapper.deleteByExample(example);
    }


    @Override
    public String checkLongLink(List<String> longLinkList) {
        List<String> domainList = longLinkList.stream().map(item-> ReUtil.getGroup1("\\w+://([\\w-]+(?:\\.[\\w-]+)+)", item)
        ).collect(Collectors.toList());

        for (int i = 0; i < longLinkList.size(); i++) {
            String longLink = longLinkList.get(i);
            String domain = domainList.get(i);
            ShortLinkCertificateExample example = new ShortLinkCertificateExample();
            example.createCriteria().andAddressEqualTo(longLink).andTypeEqualTo(CertMatchTypeEnum.AccurateMatch.getCodeId());
            ShortLinkCertificateExample.Criteria criteria = example.createCriteria().andAddressLike("%"+domain+"%").andTypeEqualTo(CertMatchTypeEnum.DomainMatch.getCodeId());
            example.or(criteria);
            long count = shortLinkCertificateMapper.countByExample(example);
            if (count < 1) {
                return longLink;
            }
        }
        return null;
    }

//    public static void main(String[] args) {
//        String item = "http://tg-home.qiyuyouxi.com/tg/game/index.html?ch=MTY";
//        String group1 = ReUtil.getGroup1("\\w+://([\\w-]+(?:\\.[\\w-]+)+)", item);
//        System.out.println(group1);
//    }
}
