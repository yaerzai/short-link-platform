package com.zhongfeng.shortlink.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.google.common.collect.Lists;
import com.zhongfeng.common.base.exception.BusinessException;
import com.zhongfeng.common.base.utils.JacksonUtils;
import com.zhongfeng.common.redis.service.StringRedisCacheService;
import com.zhongfeng.shortlink.api.dto.ShortLinkCustomerConfigDetailReqDTO;
import com.zhongfeng.shortlink.api.dto.ShortLinkCustomerConfigQueryRespDTO;
import com.zhongfeng.shortlink.api.dto.ShortTypeQueryDTO;
import com.zhongfeng.shortlink.facade.dto.*;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkCustomerConfigDataService;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCustomerConfigDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkInfoDataBO;
import com.zhongfeng.shortlink.persistence.enums.CheckStatusEnum;
import com.zhongfeng.shortlink.persistence.enums.LinkSourceEnum;
import com.zhongfeng.shortlink.persistence.enums.LinkTypeEnum;
import com.zhongfeng.shortlink.persistence.mapper.ShortLinkCustomerConfigExtMapper;
import com.zhongfeng.shortlink.persistence.mapper.ShortLinkProcessMapper;
import com.zhongfeng.shortlink.service.*;
import com.zhongfeng.sms.persistence.enums.RecordStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 短链实时服务
 *
 * @author codescript.build
 */
@Service
@Slf4j
public class ShortLinkRealtimeServiceImpl implements ShortLinkRealtimeService {
    @Autowired
    private SeqNumberGenerateService seqNumberGenerateService;
    @Autowired
    private ShortLinkCustomerConfigDataService shortLinkCustomerConfigDataService;
    @Resource
    private ShortLinkRedisCacheService shortLinkRedisCacheService;
    @Resource
    private ShortLinkLocalCacheService shortLinkLocalCacheService;
    @Resource
    private ShortLinkProcessMapper batchProcessMapper;
    @Resource
    private ShortLinkCustomerConfigExtMapper shortLinkCustomerConfigExtMapper;
    @Resource
    private StringRedisCacheService stringRedisCacheService;
    @Resource
    private DataService dataService;

    //添加长链配置
    @Override
    public ShortLinkAddConfigRespDTO addConfig(ShortLinkAddConfigReqDTO reqDTO) {
        String linkNo = seqNumberGenerateService.getLinkNo();
        ShortLinkCustomerConfigDataBO shortLinkCustomerConfigDataBO = this.getShortLinkDataBO(linkNo, reqDTO);
        shortLinkCustomerConfigDataService.add(shortLinkCustomerConfigDataBO);
        shortLinkRedisCacheService.add(linkNo, shortLinkCustomerConfigDataBO);
        shortLinkLocalCacheService.syncAdd(ListUtil.of(linkNo));

        ShortLinkAddConfigRespDTO respDTO = new ShortLinkAddConfigRespDTO();
        respDTO.setLinkNo(linkNo);
        return respDTO;
    }

    private ShortLinkCustomerConfigDataBO getShortLinkDataBO(String linkNo, ShortLinkAddConfigReqDTO reqDTO) {
        String tableTime = DateUtil.date().toString(DatePattern.PURE_DATE_PATTERN);
        ShortLinkCustomerConfigDataBO shortLinkCustomerConfigDataBO = new ShortLinkCustomerConfigDataBO();
        shortLinkCustomerConfigDataBO.setLinkNo(linkNo);
        shortLinkCustomerConfigDataBO.setLinkName(reqDTO.getLinkName());
        shortLinkCustomerConfigDataBO.setPlatformNo(reqDTO.getPlatform());
        shortLinkCustomerConfigDataBO.setCustomerNo(reqDTO.getCustomerNo());
        shortLinkCustomerConfigDataBO.setUserNo(reqDTO.getUserNo());
        shortLinkCustomerConfigDataBO.setAddress(reqDTO.getAddress());
        shortLinkCustomerConfigDataBO.setRemark(reqDTO.getRemark());
        shortLinkCustomerConfigDataBO.setLinkDomain(reqDTO.getLinkDomain());
        shortLinkCustomerConfigDataBO.setLinkType(LinkTypeEnum.BATCH.getCodeId());
        shortLinkCustomerConfigDataBO.setSource(LinkSourceEnum.marketing.getCodeId());
        shortLinkCustomerConfigDataBO.setExpirationTime(reqDTO.getExpirationTime());
        shortLinkCustomerConfigDataBO.setMobileNoParam(reqDTO.getMobileNoParam());
        shortLinkCustomerConfigDataBO.setStatus(RecordStatusEnum.OK.getCodeId());
        shortLinkCustomerConfigDataBO.setAddressMd5(SecureUtil.md5(reqDTO.getAddress()));
        shortLinkCustomerConfigDataBO.setCheckStatus(CheckStatusEnum.CHECK_PASS.getCodeId());
        shortLinkCustomerConfigDataBO.setTableTime(tableTime);
        shortLinkCustomerConfigDataBO.setLinkNum(0);
        if (Objects.nonNull(reqDTO.getLinkType())) {
            shortLinkCustomerConfigDataBO.setLinkType(reqDTO.getLinkType());
        }
        return shortLinkCustomerConfigDataBO;
    }

    @Override
    public List<ShortLinkAddConfigRespDTO> batchAddConfig(ShortLinkBatchAddConfigReqDTO reqDTO) {
        List<ShortLinkAddConfigRespDTO> list = new ArrayList<>();
        List<String> batchAddress = reqDTO.getBatchAddress();
        for (String address : batchAddress) {
            reqDTO.setAddress(address);
            ShortLinkAddConfigRespDTO shortLinkAddConfigRespDTO = this.addConfig(reqDTO);
            list.add(shortLinkAddConfigRespDTO);
        }
        return list;
    }

    //生成短链信息
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ShortLinkGenShortInfoRespDTO genShortInfo(ShortLinkGenShortInfoReqDTO reqDTO) {
        String linkNo = reqDTO.getLinkNo();
        ShortLinkCustomerConfigDataBO config = shortLinkCustomerConfigDataService.get(linkNo);
        if (config == null) {
            throw new BusinessException("长链配置不存在");
        }
        List<String> mobileList = reqDTO.getMobileList();
//        if (mobileList.size() > 10000) {
//            throw new BusinessException("手机号");
//        }
        String tableTime = config.getTableTime();
        List<ShortLinkInfoDataBO> shortLinkInfoDataBOList = new ArrayList<>();
        List<ShortLinkInfoDataBO> shortLinkInfoCacheList = new ArrayList<>();
        List<ShortLinkInfoDTO> linkList = new ArrayList<>();
        //判断类型是否存在
        if (StringUtils.hasText(reqDTO.getShortLineType())) {
            String[] shortLineTypes = reqDTO.getShortLineType().split(",");
            for (String shortLineType : shortLineTypes) {
                for (String mobile : mobileList) {
                    ShortLinkInfoDataBO shortLinkInfoDataBO = ShortLinkInfoDataBO.builder().build();
                    String shortLinkNo = seqNumberGenerateService.getShortLinkNo();
                    shortLinkInfoDataBO.setLinkNo(linkNo);
                    shortLinkInfoDataBO.setShortLinkNo(shortLinkNo);
                    shortLinkInfoDataBO.setMobileNo(mobile);
                    shortLinkInfoDataBO.setLinkDomain(config.getLinkDomain());
                    shortLinkInfoDataBO.setTableTime(tableTime);
                    //添加短链类型
                    shortLinkInfoDataBO.setShortLineType(shortLineType);
                    shortLinkInfoDataBOList.add(shortLinkInfoDataBO);
                    shortLinkInfoCacheList.add(shortLinkInfoDataBO);
                    if (shortLinkInfoDataBOList.size() >= 2000) {
                        //批量入库
                        batchAdd(shortLinkInfoDataBOList, tableTime);
                    }

                    ShortLinkInfoDTO shortLinkInfoDTO = new ShortLinkInfoDTO();
                    shortLinkInfoDTO.setShortLinkNo(config.getLinkDomain() + "/" + shortLinkNo);
                    shortLinkInfoDTO.setMobile(mobile);
                    shortLinkInfoDTO.setShortLineType(shortLineType);
                    linkList.add(shortLinkInfoDTO);
                }
            }
        } else {
            for (String mobile : mobileList) {
                ShortLinkInfoDataBO shortLinkInfoDataBO = ShortLinkInfoDataBO.builder().build();
                String shortLinkNo = seqNumberGenerateService.getShortLinkNo();
                shortLinkInfoDataBO.setLinkNo(linkNo);
                shortLinkInfoDataBO.setShortLinkNo(shortLinkNo);
                shortLinkInfoDataBO.setMobileNo(mobile);
                shortLinkInfoDataBO.setLinkDomain(config.getLinkDomain());
                shortLinkInfoDataBO.setTableTime(tableTime);
                shortLinkInfoDataBOList.add(shortLinkInfoDataBO);
                shortLinkInfoCacheList.add(shortLinkInfoDataBO);
                if (shortLinkInfoDataBOList.size() >= 2000) {
                    //批量入库
                    batchAdd(shortLinkInfoDataBOList, tableTime);
                }

                ShortLinkInfoDTO shortLinkInfoDTO = new ShortLinkInfoDTO();
                shortLinkInfoDTO.setShortLinkNo( config.getLinkDomain() + "/" + shortLinkNo);
                shortLinkInfoDTO.setMobile(mobile);
                linkList.add(shortLinkInfoDTO);
            }
        }


        //批量入库
        if (CollUtil.isNotEmpty(shortLinkInfoDataBOList)) {
            batchAdd(shortLinkInfoDataBOList, tableTime);
        }

        //本地缓存实时同步
        if (CollUtil.isNotEmpty(shortLinkInfoCacheList)) {
            List<String> cacheStrList = new ArrayList<>();
            for (ShortLinkInfoDataBO shortLinkInfoDataBO : shortLinkInfoCacheList) {
                cacheStrList.add(shortLinkInfoDataBO.toInfoCahceStr());
                if (cacheStrList.size() >= 100) {
                    //本地缓存实时同步
                    shortLinkLocalCacheService.syncAddInfo(cacheStrList);
                    cacheStrList.clear();
                }
            }
            if (CollUtil.isNotEmpty(cacheStrList)) {
                //本地缓存实时同步
                shortLinkLocalCacheService.syncAddInfo(cacheStrList);
                cacheStrList.clear();
            }
            //redis实时同步
            shortLinkRedisCacheService.add(shortLinkInfoCacheList);
        }
        //更新短链数据
        shortLinkCustomerConfigExtMapper.addLinkNum(linkNo, linkList.size());

        //更新配置表短链数量
        ShortLinkGenShortInfoRespDTO respDTO = new ShortLinkGenShortInfoRespDTO();
        respDTO.setLinkNo(linkNo);
        respDTO.setLinkList(linkList);
        return respDTO;
    }

    @Override
    public ShortLinkGenShortInfoRespDTO batchGenShortInfo(ShortLinkBatchGenShortInfoReqDTO reqDTO) {
        List<String> linkNoList = reqDTO.getLinkNoList();
        List<String> mobileList = reqDTO.getMobileList();
        if (CollectionUtil.isEmpty(mobileList) || CollectionUtil.isEmpty(linkNoList)) {
            throw new BusinessException("手机号和长链编号不能为空");
        }
        if (mobileList.size() > linkNoList.size()) {
            throw new BusinessException("长链编号小于手机号数量数量");
        }
        ShortLinkGenShortInfoReqDTO slgsReqDTO;
        List<ShortLinkInfoDTO> resultLinkList = new ArrayList<>();
        for (int i = 0; i < mobileList.size(); i++) {
            String mobile = mobileList.get(i);
            String linkNo = linkNoList.get(i);
            slgsReqDTO = new ShortLinkGenShortInfoReqDTO();
            slgsReqDTO.setLinkNo(linkNo);
            slgsReqDTO.setMobileList(Lists.newArrayList(mobile));
            slgsReqDTO.setTimeStamp(reqDTO.getTimeStamp());
            slgsReqDTO.setSign(reqDTO.getSign());
            slgsReqDTO.setShortLineType(reqDTO.getShortLineType());
            ShortLinkGenShortInfoRespDTO shortLinkGenShortInfoRespDTO = this.genShortInfo(slgsReqDTO);
            List<ShortLinkInfoDTO> linkList = shortLinkGenShortInfoRespDTO.getLinkList();
            resultLinkList.addAll(linkList);
        }
        ShortLinkGenShortInfoRespDTO respDTO = new ShortLinkGenShortInfoRespDTO();
        respDTO.setLinkNo("");
        respDTO.setLinkList(resultLinkList);
        return respDTO;
    }

    @Override
    public ShortLinkGenShortInfoRespDTO genShortInfoAndAssignUrl(ShortLinkGenShortAssignUrlReqDTO reqDTO) {
        String linkNo = reqDTO.getLinkNo();
        ShortLinkCustomerConfigDataBO config = shortLinkCustomerConfigDataService.get(linkNo);
        if (config == null) {
            throw new BusinessException("长链配置不存在");
        }
        List<String> mobileList = reqDTO.getMobileList();
        List<String> urlList = reqDTO.getUrlList();
        if (CollectionUtil.isEmpty(mobileList)) {
            throw new BusinessException("手机号列表为空");
        }
        if (CollectionUtil.isEmpty(urlList)) {
            throw new BusinessException("URL列表为空");
        }
        if (mobileList.size() > urlList.size()) {
            throw new BusinessException("URL列表未能覆盖手机号列表");
        }
        String tableTime = config.getTableTime();
        List<ShortLinkInfoDataBO> shortLinkInfoDataBOList = new ArrayList<>();
        List<ShortLinkInfoDataBO> shortLinkInfoCacheList = new ArrayList<>();
        List<ShortLinkInfoDTO> linkList = new ArrayList<>();
        //判断类型是否存在
        if (StringUtils.hasText(reqDTO.getShortLineType())) {
            String[] shortLineTypes = reqDTO.getShortLineType().split(",");
            for (String shortLineType : shortLineTypes) {
                for (int i = 0; i < mobileList.size(); i++) {
                    String url = urlList.get(i);
                    String mobile = mobileList.get(i);
                    ShortLinkInfoDataBO shortLinkInfoDataBO = ShortLinkInfoDataBO.builder().build();
                    String shortLinkNo = seqNumberGenerateService.getShortLinkNo();
                    shortLinkInfoDataBO.setLinkNo(linkNo);
                    shortLinkInfoDataBO.setShortLinkNo(shortLinkNo);
                    shortLinkInfoDataBO.setMobileNo(mobile);
                    shortLinkInfoDataBO.setLinkDomain(config.getLinkDomain());
                    shortLinkInfoDataBO.setTableTime(tableTime);
                    shortLinkInfoDataBO.setUrl(url);
                    //添加短链类型
                    shortLinkInfoDataBO.setShortLineType(shortLineType);
                    shortLinkInfoDataBOList.add(shortLinkInfoDataBO);
                    shortLinkInfoCacheList.add(shortLinkInfoDataBO);
                    if (shortLinkInfoDataBOList.size() >= 2000) {
                        //批量入库
                        batchAdd(shortLinkInfoDataBOList, tableTime);
                    }

                    ShortLinkInfoDTO shortLinkInfoDTO = new ShortLinkInfoDTO();
                    shortLinkInfoDTO.setShortLinkNo(config.getLinkDomain() + "/" + shortLinkNo);
                    shortLinkInfoDTO.setMobile(mobile);
                    shortLinkInfoDTO.setShortLineType(shortLineType);
                    linkList.add(shortLinkInfoDTO);
                }
            }
        } else {
            for (int i = 0; i < mobileList.size(); i++) {
                String url = urlList.get(i);
                String mobile = mobileList.get(i);
                ShortLinkInfoDataBO shortLinkInfoDataBO = ShortLinkInfoDataBO.builder().build();
                String shortLinkNo = seqNumberGenerateService.getShortLinkNo();
                shortLinkInfoDataBO.setLinkNo(linkNo);
                shortLinkInfoDataBO.setShortLinkNo(shortLinkNo);
                shortLinkInfoDataBO.setMobileNo(mobile);
                shortLinkInfoDataBO.setLinkDomain(config.getLinkDomain());
                shortLinkInfoDataBO.setTableTime(tableTime);
                shortLinkInfoDataBO.setUrl(url);
                shortLinkInfoDataBOList.add(shortLinkInfoDataBO);
                shortLinkInfoCacheList.add(shortLinkInfoDataBO);
                if (shortLinkInfoDataBOList.size() >= 2000) {
                    //批量入库
                    batchAdd(shortLinkInfoDataBOList, tableTime);
                }

                ShortLinkInfoDTO shortLinkInfoDTO = new ShortLinkInfoDTO();
                shortLinkInfoDTO.setShortLinkNo(config.getLinkDomain() + "/" + shortLinkNo);
                shortLinkInfoDTO.setMobile(mobile);
                linkList.add(shortLinkInfoDTO);
            }
        }


        //批量入库
        if (CollUtil.isNotEmpty(shortLinkInfoDataBOList)) {
            batchAdd(shortLinkInfoDataBOList, tableTime);
        }

        //本地缓存实时同步
        if (CollUtil.isNotEmpty(shortLinkInfoCacheList)) {
            List<String> cacheStrList = new ArrayList<>();
            for (ShortLinkInfoDataBO shortLinkInfoDataBO : shortLinkInfoCacheList) {
                cacheStrList.add(shortLinkInfoDataBO.toInfoCahceStr());
                if (cacheStrList.size() >= 100) {
                    //本地缓存实时同步
                    shortLinkLocalCacheService.syncAddInfo(cacheStrList);
                    cacheStrList.clear();
                }
            }
            if (CollUtil.isNotEmpty(cacheStrList)) {
                //本地缓存实时同步
                shortLinkLocalCacheService.syncAddInfo(cacheStrList);
                cacheStrList.clear();
            }
            //redis实时同步
            shortLinkRedisCacheService.add(shortLinkInfoCacheList);
        }
        //更新短链数据
        shortLinkCustomerConfigExtMapper.addLinkNum(linkNo, mobileList.size());

        //更新配置表短链数量
        ShortLinkGenShortInfoRespDTO respDTO = new ShortLinkGenShortInfoRespDTO();
        respDTO.setLinkNo(linkNo);
        respDTO.setLinkList(linkList);
        return respDTO;
    }

    private void batchAdd(List<ShortLinkInfoDataBO> shortLinkInfoDataBOList, String tableTime) {
        if (batchProcessMapper.batchInsertShortLinkInfo(tableTime, shortLinkInfoDataBOList) != shortLinkInfoDataBOList.size()) {
            log.error("[rtGenerate]  批量插入失败 {}", JacksonUtils.jsonObjectSerializer(shortLinkInfoDataBOList));
            throw new BusinessException("导入短链批次失败");
        }
        shortLinkInfoDataBOList.clear();
    }

    @Override
    public ShortLinkCustomerConfigQueryRespDTO queryDetail(ShortLinkCustomerConfigDetailReqDTO reqDTO) {
        String linkNo = reqDTO.getLinkNo();
        ShortLinkCustomerConfigDataBO shortLinkCustomerConfigDataBO = dataService.getCustomerConfigInfo(linkNo);
        if (shortLinkCustomerConfigDataBO == null) {
            throw new BusinessException("链接编号不存在！" + linkNo);
        }
        //pvNum uvNum ipNum数据
        long pvNum = stringRedisCacheService.incrementAndGet("ShortLink:PV:" + linkNo, Integer.MAX_VALUE, 0, 30 * 24 * 3600);
        long uvNum = stringRedisCacheService.incrementAndGet("ShortLink:UV:" + linkNo, Integer.MAX_VALUE, 0, 30 * 24 * 3600);
        long ipNum = stringRedisCacheService.memberSize("ShortLink:IP:" + linkNo);
        ShortLinkCustomerConfigQueryRespDTO shortLinkCustomerConfigQueryRespDTO = new ShortLinkCustomerConfigQueryRespDTO();
        shortLinkCustomerConfigQueryRespDTO.setLinkNo(shortLinkCustomerConfigDataBO.getLinkNo());
        shortLinkCustomerConfigQueryRespDTO.setCustomerNo(shortLinkCustomerConfigDataBO.getCustomerNo());
        shortLinkCustomerConfigQueryRespDTO.setUserNo(shortLinkCustomerConfigDataBO.getUserNo());
        shortLinkCustomerConfigQueryRespDTO.setLinkName(shortLinkCustomerConfigDataBO.getLinkName());
        shortLinkCustomerConfigQueryRespDTO.setAddress(shortLinkCustomerConfigDataBO.getAddress());
        shortLinkCustomerConfigQueryRespDTO.setMobileNoParam(shortLinkCustomerConfigDataBO.getMobileNoParam());
        shortLinkCustomerConfigQueryRespDTO.setStatus(shortLinkCustomerConfigDataBO.getStatus());
        shortLinkCustomerConfigQueryRespDTO.setShortLinkAddress(shortLinkCustomerConfigDataBO.getShortLinkAddress());
        shortLinkCustomerConfigQueryRespDTO.setLinkDomain(shortLinkCustomerConfigDataBO.getLinkDomain());
        shortLinkCustomerConfigQueryRespDTO.setExpirationTime(shortLinkCustomerConfigDataBO.getExpirationTime());
        shortLinkCustomerConfigQueryRespDTO.setCheckStatus(shortLinkCustomerConfigDataBO.getCheckStatus());

        shortLinkCustomerConfigQueryRespDTO.setLinkType(shortLinkCustomerConfigDataBO.getLinkType());
        shortLinkCustomerConfigQueryRespDTO.setSource(shortLinkCustomerConfigDataBO.getSource());
        shortLinkCustomerConfigQueryRespDTO.setCreateTime(shortLinkCustomerConfigDataBO.getCreateTime());


        shortLinkCustomerConfigQueryRespDTO.setPvNum(pvNum);
        shortLinkCustomerConfigQueryRespDTO.setUvNum(uvNum);
        shortLinkCustomerConfigQueryRespDTO.setIpNum(ipNum);
        //pvNum uvNum ipNum  分类 数据
        Map<Object, Object> shortTypePv = stringRedisCacheService.getHash("shortLineType:PV:" + linkNo);
        List<ShortTypeQueryDTO> shortTypePvList = Optional.ofNullable(shortTypePv)
                .map(m -> m.entrySet().stream()
                        .map(entry -> {
                            return ShortTypeQueryDTO.builder().shortTypeName(entry.getKey().toString()).num(entry.getValue().toString()).build();
                        })
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());


        Map<Object, Object> shortTypeUv = stringRedisCacheService.getHash("shortLineType:UV:" + linkNo);
        List<ShortTypeQueryDTO> shortTypeUvList = Optional.ofNullable(shortTypeUv)
                .map(m -> m.entrySet().stream()
                        .map(entry -> {
                            return ShortTypeQueryDTO.builder().shortTypeName(entry.getKey().toString()).num(entry.getValue().toString()).build();
                        })
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());

        List<ShortTypeQueryDTO> shortTypeIpList = new ArrayList<>();
        Set<String> shortTypes = stringRedisCacheService.members("shortLineType:IP:" + linkNo);
        if (!ObjectUtils.isEmpty(shortTypes)) {
            for (String shortType : shortTypes) {
                Long memberSize = stringRedisCacheService.memberSize("shortLineType:IP:" + linkNo + ":" + shortType);
                shortTypeIpList.add(ShortTypeQueryDTO.builder().shortTypeName(shortType).num(String.valueOf(memberSize)).build());
            }
        }
        log.info("[ShortLinkRealtimeServiceImpl] ShortLinkCustomerConfigDetailReqDTO {} ,pvNum {} ,uvNum{} ,ipNum{},shortTypePv{},shortTypeUv {},shortTypes{}",reqDTO,pvNum,uvNum,ipNum,shortTypePv,shortTypeUv,shortTypes);
        shortLinkCustomerConfigQueryRespDTO.setShortTypeUvList(shortTypeUvList);
        shortLinkCustomerConfigQueryRespDTO.setShortTypePvList(shortTypePvList);
        shortLinkCustomerConfigQueryRespDTO.setShortTypeIpList(shortTypeIpList);

        return shortLinkCustomerConfigQueryRespDTO;
    }
}
