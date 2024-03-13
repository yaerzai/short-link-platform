package com.zhongfeng.shortlink.facade.api;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.github.pagehelper.Page;
import com.zhongfeng.common.base.exception.BusinessException;
import com.zhongfeng.common.base.utils.DateUtils;
import com.zhongfeng.common.db.model.PageData;
import com.zhongfeng.common.redis.service.StringRedisCacheService;
import com.zhongfeng.common.swagger.model.ResultData;
import com.zhongfeng.shortlink.api.dto.*;
import com.zhongfeng.shortlink.constant.RedisKeys;
import com.zhongfeng.shortlink.constant.SysConstant;
import com.zhongfeng.shortlink.facade.dto.ShortLinkAddConfigReqDTO;
import com.zhongfeng.shortlink.facade.dto.ShortLinkAddConfigRespDTO;
import com.zhongfeng.shortlink.facade.dto.ShortLinkGenShortInfoReqDTO;
import com.zhongfeng.shortlink.facade.dto.ShortLinkGenShortInfoRespDTO;
import com.zhongfeng.shortlink.facade.dto.ShortLinkInfoDTO;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkCertificateDataService;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkCustomerConfigDataService;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkInfoDataService;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkRequestInfoDataService;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCustomerConfigDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCustomerConfigQueryBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkInfoDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkInfoQueryBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkRequestInfoDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkRequestInfoQueryBO;
import com.zhongfeng.shortlink.persistence.enums.CheckStatusEnum;
import com.zhongfeng.shortlink.persistence.enums.LinkTypeEnum;
import com.zhongfeng.shortlink.persistence.mapper.ShortLinkInfoMapper;
import com.zhongfeng.shortlink.persistence.mapper.ShortLinkRequestInfoMapper;
import com.zhongfeng.shortlink.persistence.mapper.ShortLinkRequestInfoMapperExt;
import com.zhongfeng.shortlink.persistence.model.*;
import com.zhongfeng.shortlink.service.DataService;
import com.zhongfeng.shortlink.service.SeqNumberGenerateService;
import com.zhongfeng.shortlink.service.ShortLinkLocalCacheService;
import com.zhongfeng.shortlink.service.ShortLinkRealtimeService;
import com.zhongfeng.shortlink.service.ShortLinkRedisCacheService;
import com.zhongfeng.shortlink.service.ShortLinkService;
import com.zhongfeng.shortlink.service.impl.ShortLinkRequestServiceImpl;
import com.zhongfeng.shortlink.utils.PageDataUtils;
import com.zhongfeng.shortlink.utils.SysTimeUtils;
import com.zhongfeng.sms.persistence.enums.RecordStatusEnum;
import com.zhongfeng.sms.persistence.enums.SysParamEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuzc
 * created on 2020/2/23
 */
@RestController
@Slf4j
public class ShortLinkConfigController implements com.zhongfeng.shortlink.api.service.ShortLinkConfigService {
    @Autowired
    private ShortLinkService shortLinkService;
    @Autowired
    private ShortLinkCustomerConfigDataService shortLinkCustomerConfigDataService;
    @Autowired
    private ShortLinkInfoDataService shortLinkInfoDataService;
    @Autowired
    private ShortLinkRequestInfoDataService shortLinkRequestInfoDataService;
    @Autowired
    private SeqNumberGenerateService seqNumberGenerateService;
    @Resource
    private ShortLinkInfoMapper shortLinkInfoMapper;
    @Resource
    private ShortLinkRequestInfoMapper shortLinkRequestInfoMapper;
    @Resource
    private ShortLinkRequestInfoMapperExt shortLinkRequestInfoMapperExt;
    @Resource
    private DataService dataService;
    @Resource
    private ShortLinkCertificateDataService shortLinkCertificateDataService;
    @Resource
    private ShortLinkRedisCacheService shortLinkRedisCacheService;
    @Resource
    private ShortLinkLocalCacheService shortLinkLocalCacheService;
    @Resource
    private ShortLinkRealtimeService shortLinkRealtimeService;
    @Resource
    private StringRedisCacheService stringRedisCacheService;

    @Override
    public ResultData add(@Valid @RequestBody ShortLinkCustomerConfigAddDTO addDTO) {
        if (shortLinkCertificateDataService.checkLongLink(ListUtil.of(addDTO.getAddress())) != null) {
            throw new BusinessException("该地址未匹配到长链接凭证" + addDTO.getAddress());
        }
        ShortLinkCustomerConfigDataBO dataBO = new ShortLinkCustomerConfigDataBO();
        dataBO.setLinkNo(seqNumberGenerateService.getLinkNo());
        dataBO.setLinkName(addDTO.getLinkName());
//        dataBO.setShortLinkAddress(seqNumberGenerateService.getShortLinkNo());
        dataBO.setPlatformNo(addDTO.getPlatformNo());
        dataBO.setCustomerNo(addDTO.getCustomerNo());
        dataBO.setUserNo(addDTO.getUserNo());
        dataBO.setAddress(addDTO.getAddress());
        dataBO.setRemark(addDTO.getRemark());
        dataBO.setLinkDomain(addDTO.getLinkDomain());
        dataBO.setLinkType(LinkTypeEnum.SINGLE.getCodeId());
        dataBO.setSource(addDTO.getSource());
        dataBO.setLinkNum(1);
        dataBO.setStatus((RecordStatusEnum.OK.getCodeId()));
        dataBO.setExpirationTime(addDTO.getExpirationTime());
        dataBO.setOperatorId(addDTO.getOperatorId());
        dataBO.setCheckStatus(CheckStatusEnum.CHECK_PASS.getCodeId());
        dataBO.setTableTime(DateUtil.date().toString(DatePattern.PURE_DATE_PATTERN));
        dataBO.setMobileNoParam(addDTO.getMobileNoParam());
        // 短链生成
        dataBO.setShortLinkAddress(seqNumberGenerateService.getShortLinkNo());
        shortLinkCustomerConfigDataService.add(dataBO);
        //同时本地缓存和redis缓存
        shortLinkLocalCacheService.syncAdd(dataBO.getLinkNo());
        shortLinkRedisCacheService.add(dataBO.getShortLinkAddress(), dataBO);
        return ResultData.SUCCESS;
    }

    @Override
    public ResultData batchAdd(@Valid ShortLinkCustomerConfigBatchAddDTO addDTO) {
        String noMatchLink = shortLinkCertificateDataService.checkLongLink(addDTO.getAddressList());
        if (noMatchLink != null) {
            throw new BusinessException("该地址未匹配到长链接凭证" + noMatchLink);
        }
        addDTO.getAddressList().forEach(address -> {
            ShortLinkCustomerConfigDataBO dataBO = new ShortLinkCustomerConfigDataBO();
            dataBO.setLinkNo(seqNumberGenerateService.getLinkNo());
//            dataBO.setShortLinkAddress(seqNumberGenerateService.getShortLinkNo());
            dataBO.setLinkName(addDTO.getLinkName());
            dataBO.setPlatformNo(addDTO.getPlatformNo());
            dataBO.setCustomerNo(addDTO.getCustomerNo());
            dataBO.setUserNo(addDTO.getUserNo());
            dataBO.setAddress(address);
            dataBO.setRemark(addDTO.getRemark());
            dataBO.setLinkDomain(addDTO.getLinkDomain());
            dataBO.setLinkType(LinkTypeEnum.SINGLE.getCodeId());
            dataBO.setSource(addDTO.getSource());
            dataBO.setLinkNum(1);
            dataBO.setStatus((RecordStatusEnum.OK.getCodeId()));
            dataBO.setExpirationTime(addDTO.getExpirationTime());
            dataBO.setOperatorId(addDTO.getOperatorId());
            // 短链生成
            dataBO.setShortLinkAddress(seqNumberGenerateService.getShortLinkNo());
            dataBO.setCheckStatus(CheckStatusEnum.CHECK_PASS.getCodeId());
            dataBO.setTableTime(DateUtil.date().toString(DatePattern.PURE_DATE_PATTERN));
            shortLinkCustomerConfigDataService.add(dataBO);
            //同时本地缓存和redis缓存
            shortLinkLocalCacheService.syncAdd(dataBO.getLinkNo());
            shortLinkRedisCacheService.add(dataBO.getShortLinkAddress(), dataBO);
//            ShortLinkServiceImpl.addShortLinkConfigCache(dataBO.getShortLinkAddress(), dataBO);
        });
        return ResultData.SUCCESS;
    }

    @Override
    public ResultData batchImport(@Valid ShortLinkBatchAddDTO addDTO) {
        shortLinkService.batchMobileFile(addDTO);
        return ResultData.SUCCESS;
    }

    @Override
    public ResultData update(@Valid ShortLinkCustomerConfigUpdReqDTO updReqDTO) {
        if (shortLinkCertificateDataService.checkLongLink(ListUtil.of(updReqDTO.getAddress())) != null) {
            throw new BusinessException("该地址未匹配到长链接凭证" + updReqDTO.getAddress());
        }
        ShortLinkCustomerConfigDataBO oldConfig = shortLinkCustomerConfigDataService.get(updReqDTO.getLinkNo());
        if (oldConfig == null) {
            throw new BusinessException("长链配置不存在" + updReqDTO.getLinkNo());
        }

        ShortLinkCustomerConfigDataBO dataBO = new ShortLinkCustomerConfigDataBO();
        dataBO.setAddress(updReqDTO.getAddress());
        dataBO.setRemark(updReqDTO.getRemark());
        dataBO.setStatus(updReqDTO.getStatus());
        dataBO.setExpirationTime(updReqDTO.getExpirationTime());
        dataBO.setOperatorId(updReqDTO.getOperatorId());
        dataBO.setLinkNo(updReqDTO.getLinkNo());
        dataBO.setLinkName(updReqDTO.getLinkName());
        dataBO.setAddress(updReqDTO.getAddress());
        shortLinkCustomerConfigDataService.upd(dataBO);

        ShortLinkCustomerConfigDataBO newConfig = shortLinkCustomerConfigDataService.get(updReqDTO.getLinkNo());
        Byte oldStatus = oldConfig.getStatus();
        Byte newStatus = newConfig.getStatus();

        if (oldStatus.equals(RecordStatusEnum.OK.getCodeId()) && !oldStatus.equals(newStatus)) {
            //修改为无效或者过期失效
            shortLinkRedisCacheService.remove(newConfig);
        } else {
            //同步本地缓存
            shortLinkLocalCacheService.syncAdd(newConfig.getLinkNo());

            if (oldStatus.equals(newStatus)) {
                //同步redis缓存
                if (LinkTypeEnum.SINGLE.eq(oldConfig.getLinkType())) {
                    shortLinkRedisCacheService.add(newConfig.getShortLinkAddress(), newConfig);
                } else {
                    shortLinkRedisCacheService.add(newConfig.getLinkNo(), newConfig);
                }
            } else {
                SysConstant.redisSyncExecutors.execute(() -> shortLinkRedisCacheService.add(newConfig));
            }
        }
        return ResultData.SUCCESS;
    }

    @Override
    public ResultData<ShortLinkCustomerConfigQueryDetailRespDTO> get(@Valid @RequestBody ShortLinkCustomerConfigDetailReqDTO reqDTO) {
        ShortLinkCustomerConfigDataBO result = shortLinkCustomerConfigDataService.get(reqDTO.getLinkNo());
        return new ResultData<>(result == null ? null : buildDetailRespDTO(result));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData delete(@Valid @RequestBody ShortLinkCustomerConfigDetailReqDTO deleteDTO) {
        String linkNo = deleteDTO.getLinkNo();
        ShortLinkCustomerConfigDataBO dataBO = shortLinkCustomerConfigDataService.get(linkNo);
        if (dataBO == null) {
            throw new BusinessException("链接编号不存在！");
        }
        try {
            //删除本地及redis缓存
            shortLinkRedisCacheService.remove(dataBO);

            shortLinkCustomerConfigDataService.delete(linkNo);
            shortLinkInfoDataService.deleteByLinkNo(linkNo, dataBO.getTableTime());
            shortLinkRequestInfoDataService.delete(linkNo, dataBO.getTableTime());
        } catch (Exception e) {
            log.error("删除客户信息失败 {}", linkNo, e);
            throw new BusinessException("删除客户链接配置失败");
        }
        return ResultData.SUCCESS;
    }

    @Override
    public ResultData<ShortLinkRankingStatisticsRespDTO> rankingStatistics(@Valid ShortLinkBatchInfoDetailReqDTO shortLinkBatchInfoDetailReqDTO) {
        return null;
    }

    @Override
    public ResultData<PageData<ShortLinkCustomerConfigQueryRespDTO>> queryConfig(@Valid @RequestBody ShortLinkCustomerConfigQueryReqDTO queryReqDTO) {
        ShortLinkCustomerConfigQueryBO queryBO = new ShortLinkCustomerConfigQueryBO();
        queryBO.setOrderBy("create_time desc");
        queryBO.setLinkNos(queryReqDTO.getLinkNos());
        queryBO.setLinkName(queryReqDTO.getLinkName());
        queryBO.setStatus(queryReqDTO.getStatus());
        queryBO.setShortLinkAddress(queryReqDTO.getShortLinkAddress());
        queryBO.setLinkDomain(queryReqDTO.getLinkDomain());
        queryBO.setLinkType(queryReqDTO.getLinkType());
        queryBO.setSource(queryReqDTO.getSource());
        queryBO.setCustomerNo(queryReqDTO.getCustomerNo());
        queryBO.setUserNos(queryReqDTO.getUserNos());
        queryBO.setPageNo(queryReqDTO.getPageNo());
        queryBO.setPageSize(queryReqDTO.getPageSize());
        PageData<ShortLinkCustomerConfigDataBO> page = shortLinkCustomerConfigDataService.query(queryBO);
        PageData<ShortLinkCustomerConfigQueryRespDTO> result = PageData.getPageData(page);
        if (!CollectionUtils.isEmpty(page.getData())) {
            result.setData(page.getData().stream().map(item -> {
                ShortLinkCustomerConfigQueryRespDTO respDTO = buildRespDTO(item);
                if (LinkTypeEnum.SINGLE.eq(respDTO.getLinkType())) {
                    respDTO.setShortNum(1);
                } else {
                    respDTO.setShortNum(item.getLinkNum());
                }
                return respDTO;
            }).collect(Collectors.toList()));
        }
        return new ResultData<>(result);
    }

    @Override
    public ResultData<PageData<ShortLinkInfoQueryRespDTO>> queryShortLink(@Valid @RequestBody ShortLinkInfoQueryReqDTO queryReqDTO) {
        ShortLinkInfoQueryBO queryBO = new ShortLinkInfoQueryBO();
        queryBO.setOrderBy(queryReqDTO.getOrderBy());
        queryBO.setLinkNo(queryReqDTO.getLinkNo());
        queryBO.setPageNo(queryReqDTO.getPageNo());
        queryBO.setPageSize(queryReqDTO.getPageSize());
        queryBO.setLastId(queryReqDTO.getLastId());
        queryBO.setTableTime(queryReqDTO.getTableTime());
        PageData<ShortLinkInfoDataBO> page = shortLinkInfoDataService.query(queryBO);
        return new ResultData<>(PageDataUtils.changePageData(page, this::buildDetailRespDTO));
    }

    @Override
    public ResultData<Long> countShortLink(@Valid ShortLinkInfoQueryReqDTO queryReqDTO) {
        ShortLinkInfoExample example = new ShortLinkInfoExample();
        ShortLinkInfoExample.Criteria criteria = example.createCriteria();
        String linkNo = queryReqDTO.getLinkNo();
        if (StrUtil.isNotEmpty(linkNo)) {
            criteria.andLinkNoEqualTo(linkNo);
        }
        criteria.andTableTimeEqualTo(queryReqDTO.getTableTime());
        long count = shortLinkInfoMapper.countByExample(example);
        return new ResultData<>(count);
    }

    @Override
    public ResultData<List<RequestRealtimeStatisticRespDTO>> requestRealtimeStatistic(RequestRealtimeStatisticReqDTO reqDTO) {
        List<RequestRealtimeStatisticResp> list = shortLinkRequestInfoMapperExt.requestRealtimeStatistic(buildByDTO(reqDTO));
        return ResultData.getSuccessData(buildListDTO(list));
    }

    @Override
    public ResultData<PageData<ShortLinkRequestInfoQueryRespDTO>> requestQuery(@Valid @RequestBody ShortLinkRequestInfoQueryReqDTO queryReqDTO) {
//        String yesterdayDate = DateUtils.parse(DateUtils.DateFormatEnum.YYYY_MM_DD, -1);
//        Date endTime = DateUtils.getDayEnd(yesterdayDate);
        ShortLinkRequestInfoQueryBO queryBO = new ShortLinkRequestInfoQueryBO();
        queryBO.setOrderBy("request_time desc");
        queryBO.setLinkNo(queryReqDTO.getLinkNo());
//        queryBO.setEndRequestTime(endTime);
        queryBO.setTableTime(queryReqDTO.getTableTime());
        queryBO.setPageNo(queryReqDTO.getPageNo());
        queryBO.setPageSize(queryReqDTO.getPageSize());
        PageData<ShortLinkRequestInfoDataBO> page = shortLinkRequestInfoDataService.query(queryBO);
        PageData<ShortLinkRequestInfoQueryRespDTO> result = PageData.getPageData(page);
        if (!CollectionUtils.isEmpty(page.getData())) {
            result.setData(page.getData().stream().map(this::buildDetailRespDTO).collect(Collectors.toList()));
        }
        return new ResultData<>(result);
    }

    @Override
    public ResultData<PageData<ShortLinkRequestReportQueryRespDTO>> requestReport(@Valid ShortLinkRequestReportQueryReqDTO reqDTO) {
        String linkNo = reqDTO.getLinkNo();
        ShortLinkCustomerConfigDataBO shortLinkCustomerConfigDataBO = shortLinkCustomerConfigDataService.get(linkNo);
        if (shortLinkCustomerConfigDataBO == null) {
            throw new BusinessException("短链配置不存在");
        }
        ShortLinkInfoExample shortLinkInfoExample = new ShortLinkInfoExample();
        ShortLinkInfoExample.Criteria criteria = shortLinkInfoExample.createCriteria()
                .andLinkNoEqualTo(linkNo)
                .andTableTimeEqualTo(shortLinkCustomerConfigDataBO.getTableTime());
        if (reqDTO.getLastId() != null) {
            criteria.andIdGreaterThan(reqDTO.getLastId());
        }
        if (StrUtil.isNotEmpty(reqDTO.getMobileNo())) {
            criteria.andMobileNoEqualTo(reqDTO.getMobileNo());
        }
        if (StrUtil.isNotEmpty(reqDTO.getShortLinkNo())) {
            criteria.andShortLinkNoEqualTo(reqDTO.getShortLinkNo());
        }
        if (StrUtil.isNotEmpty(reqDTO.getOrderBy())) {
            shortLinkInfoExample.setOrderByClause(reqDTO.getOrderBy());
        }

        RowBounds rowBounds = new RowBounds(reqDTO.getPageNo(), reqDTO.getPageSize());
        Page<ShortLinkInfo> shortLinkInfoPage = shortLinkInfoMapper.selectByExampleWithRowbounds(shortLinkInfoExample, rowBounds);
        List<ShortLinkInfo> shortLinkInfoList = shortLinkInfoPage.getResult();
        if (CollUtil.isEmpty(shortLinkInfoList)) {
            return ResultData.SUCCESS;
        }
        DateTime currDateEnd = DateUtil.date();
        currDateEnd.setField(DateField.HOUR_OF_DAY, 23);
        currDateEnd.setField(DateField.MINUTE, 59);
        currDateEnd.setField(DateField.SECOND, 59);

        //未到有效期
        Date createTime = shortLinkCustomerConfigDataBO.getCreateTime();
        DateTime createDateTime = DateUtil.date(createTime);
        int requestReportDays = dataService.getSysParamInt(SysParamEnum.SHORTLINK_REQUEST_REPORT_DAYS);
        String beginDate = createDateTime.toString("yyyyMMdd");
        String endDate = beginDate;
        for (int i = 1; i < requestReportDays; i++) {
            DateTime nextTime = createDateTime.offsetNew(DateField.DAY_OF_MONTH, i);
            if (nextTime.isAfter(currDateEnd)) {
                break;
            }
            endDate = nextTime.toString("yyyyMMdd");
        }

        List<String> mobileList = shortLinkInfoList.stream().map(ShortLinkInfo::getMobileNo).collect(Collectors.toList());
        ShortLinkRequestInfoExample shortLinkRequestInfoExample = new ShortLinkRequestInfoExample();
        shortLinkRequestInfoExample.createCriteria()
                .andLinkNoEqualTo(linkNo)
                .andMobileNoIn(mobileList)
                .andTableTimeBetween(beginDate, endDate);
        List<RequestReportResp> reportList = shortLinkRequestInfoMapperExt.requestReportQuery(shortLinkRequestInfoExample);

        List<ShortLinkRequestReportQueryRespDTO> respList = new ArrayList<>();
        for (ShortLinkInfo shortLinkInfo : shortLinkInfoList) {
            ShortLinkRequestReportQueryRespDTO respDTO = new ShortLinkRequestReportQueryRespDTO();

            String mobileNo = shortLinkInfo.getMobileNo();
            String shortLineType = shortLinkInfo.getShortLineType();
            respDTO.setId(shortLinkInfo.getId());
            respDTO.setMobileNo(mobileNo);
            respDTO.setShortLinkAddress(shortLinkInfo.getLinkDomain() + "/" + shortLinkInfo.getShortLinkNo());
            RequestReportResp requestReportResp = new RequestReportResp();
            //如果没有类型
           if (StringUtils.hasText(shortLineType)) {
                requestReportResp = reportList.stream().filter(item -> item.getMobileNo().equals(mobileNo) && shortLineType.equals(item.getShortLineType())).findAny().orElse(null);
            } else {
                requestReportResp = reportList.stream().filter(item -> item.getMobileNo().equals(mobileNo)).findAny().orElse(null);
            }

            if (requestReportResp != null) {
                respDTO.setPvNum(requestReportResp.getPv());
                respDTO.setUvNum(requestReportResp.getUv());
                respDTO.setIpNum(requestReportResp.getIp());
            } else {
                respDTO.setPvNum(0);
                respDTO.setUvNum(0);
                respDTO.setIpNum(0);
            }
            respList.add(respDTO);
        }
        PageData<ShortLinkRequestReportQueryRespDTO> respPage = new PageData<>();
        respPage.setPageNo(shortLinkInfoPage.getPageNum());
        respPage.setPageSize(shortLinkInfoPage.getPageSize());
        respPage.setPages(shortLinkInfoPage.getPages());
        respPage.setTotal(shortLinkInfoPage.getTotal());
        respPage.setData(respList);
        return ResultData.getSuccessData(respPage);
    }

    @Override
    public ResultData<PageData<ShortLinkRequestDetailQueryRespDTO>> requestDetail(@Valid ShortLinkRequestDetailQueryReqDTO reqDTO) {
        String linkNo = reqDTO.getLinkNo();
        ShortLinkCustomerConfigDataBO shortLinkCustomerConfigDataBO = shortLinkCustomerConfigDataService.get(linkNo);
        if (shortLinkCustomerConfigDataBO == null) {
            throw new BusinessException("短链配置不存在");
        }
        ShortLinkInfoExample shortLinkInfoExample = new ShortLinkInfoExample();
        ShortLinkInfoExample.Criteria criteria = shortLinkInfoExample.createCriteria()
                .andLinkNoEqualTo(linkNo)
                .andTableTimeEqualTo(shortLinkCustomerConfigDataBO.getTableTime());
        if (reqDTO.getLastId() != null) {
            criteria.andIdGreaterThan(reqDTO.getLastId());
        }
        if (StrUtil.isNotEmpty(reqDTO.getOrderBy())) {
            shortLinkInfoExample.setOrderByClause(reqDTO.getOrderBy());
        }

        RowBounds rowBounds = new RowBounds(reqDTO.getPageNo(), reqDTO.getPageSize());
        Page<ShortLinkInfo> shortLinkInfoPage = shortLinkInfoMapper.selectByExampleWithRowbounds(shortLinkInfoExample, rowBounds);
        List<ShortLinkInfo> shortLinkInfoList = shortLinkInfoPage.getResult();
        if (CollUtil.isEmpty(shortLinkInfoList)) {
            return ResultData.SUCCESS;
        }
        DateTime currDateEnd = DateUtil.date();
        currDateEnd.setField(DateField.HOUR_OF_DAY, 23);
        currDateEnd.setField(DateField.MINUTE, 59);
        currDateEnd.setField(DateField.SECOND, 59);

        //未到有效期
        Date createTime = shortLinkCustomerConfigDataBO.getCreateTime();
        DateTime createDateTime = DateUtil.date(createTime);
        int requestReportDays = dataService.getSysParamInt(SysParamEnum.SHORTLINK_REQUEST_REPORT_DAYS);
        String beginDate = createDateTime.toString("yyyyMMdd");
        String endDate = beginDate;
        for (int i = 1; i < requestReportDays; i++) {
            DateTime nextTime = createDateTime.offsetNew(DateField.DAY_OF_MONTH, i);
            if (nextTime.isAfter(currDateEnd)) {
                break;
            }
            endDate = nextTime.toString("yyyyMMdd");
        }

        List<String> mobileList = shortLinkInfoList.stream().map(ShortLinkInfo::getMobileNo).collect(Collectors.toList());
        ShortLinkRequestInfoExample shortLinkRequestInfoExample = new ShortLinkRequestInfoExample();
        shortLinkRequestInfoExample.createCriteria()
                .andLinkNoEqualTo(linkNo)
                .andMobileNoIn(mobileList)
                .andTableTimeBetween(beginDate, endDate);
        List<RequestReportResp> reportList = shortLinkRequestInfoMapperExt.requestReportQuery(shortLinkRequestInfoExample);

        List<ShortLinkRequestDetailQueryRespDTO> respList = new ArrayList<>();
        for (ShortLinkInfo shortLinkInfo : shortLinkInfoList) {
            ShortLinkRequestDetailQueryRespDTO respDTO = new ShortLinkRequestDetailQueryRespDTO();

            String mobileNo = shortLinkInfo.getMobileNo();
            String shortLineType = shortLinkInfo.getShortLineType();
            respDTO.setId(shortLinkInfo.getId());
            respDTO.setMobileNo(mobileNo);
            respDTO.setShortLinkAddress(shortLinkInfo.getLinkDomain() + "/" + shortLinkInfo.getShortLinkNo());
            RequestReportResp requestReportResp = new RequestReportResp();
            //如果没有类型
            if (StringUtils.hasText(shortLineType)) {
                requestReportResp = reportList.stream().filter(item -> item.getMobileNo().equals(mobileNo) && shortLineType.equals(item.getShortLineType())).findAny().orElse(null);
            } else {
                requestReportResp = reportList.stream().filter(item -> item.getMobileNo().equals(mobileNo)).findAny().orElse(null);
            }

            if (requestReportResp != null) {
                respDTO.setPvNum(requestReportResp.getPv());

                Long id = requestReportResp.getId();
                String tableTime = requestReportResp.getTableTime();
                ShortLinkRequestInfo shortLinkRequestInfo = getShortLinkRequestInfo(id, tableTime);
                if (shortLinkRequestInfo != null) {
                    respDTO.setRequestIp(shortLinkRequestInfo.getRequestIp());
                    respDTO.setRequestTime(shortLinkRequestInfo.getRequestTime());
                    respDTO.setMobileModel(ShortLinkRequestServiceImpl.getMobileModel(shortLinkRequestInfo.getUserAgent()));
                    respDTO.setRequestDevice(shortLinkRequestInfo.getRequestDevice());
                }
            } else {
                respDTO.setPvNum(0);
            }
            respList.add(respDTO);
        }
        PageData<ShortLinkRequestDetailQueryRespDTO> respPage = new PageData<>();
        respPage.setPageNo(shortLinkInfoPage.getPageNum());
        respPage.setPageSize(shortLinkInfoPage.getPageSize());
        respPage.setPages(shortLinkInfoPage.getPages());
        respPage.setTotal(shortLinkInfoPage.getTotal());
        respPage.setData(respList);
        return ResultData.getSuccessData(respPage);
    }

    @Override
    public ResultData shortLinkCache(@Valid ShortLinkCacheReqDTO reqDTO) {
        String shortlinkRedisCacheSyncFlag = dataService.getSysParam(SysParamEnum.SHORTLINK_REDIS_CACHE_SYNC_FLAG);
        //同步redis缓存
        ShortLinkCustomerConfigQueryBO queryBO = new ShortLinkCustomerConfigQueryBO();
        queryBO.setLinkNos(reqDTO.getLinkNos());
        queryBO.setPageSize(Integer.MAX_VALUE);
        PageData<ShortLinkCustomerConfigDataBO> page = shortLinkCustomerConfigDataService.query(queryBO);
        if (CollUtil.isEmpty(page.getData())) {
            return ResultData.SUCCESS;
        }
        for (ShortLinkCustomerConfigDataBO dataBO : page.getData()) {
            DateTime expirationTime = DateUtil.date(dataBO.getExpirationTime());
            DateTime currTime = DateUtil.date();
            if (RecordStatusEnum.OK.getCodeId() == dataBO.getStatus() && expirationTime.isBefore(currTime)) {
                log.error("[Redis缓存同步] 配置过期,同步删除: {}", dataBO.getLinkNo());
            }
            if (RecordStatusEnum.OK.getCodeId() == dataBO.getStatus() && expirationTime.isAfter(currTime)) {
                //同步本地缓存
                shortLinkLocalCacheService.syncAdd(dataBO.getLinkNo());

                if ("1".equals(shortlinkRedisCacheSyncFlag)) {
                    SysConstant.redisSyncExecutors.execute(() -> shortLinkRedisCacheService.add(dataBO));
                }
            } else {
                if ("1".equals(shortlinkRedisCacheSyncFlag)) {
                    SysConstant.redisSyncExecutors.execute(() -> shortLinkRedisCacheService.remove(dataBO));
                }
            }
        }
        return ResultData.SUCCESS;
    }

    @Override
    public ResultData<MmsAdShortLinkRespDTO> mmsAdShortLink(@Valid MmsAdShortLinkReqDTO reqDTO) {
        if (CollectionUtils.isEmpty(reqDTO.getAdShortInfos())) {
            throw new BusinessException("手机号和原始长链不存在");
        }
        MmsAdShortLinkRespDTO respDTO = new MmsAdShortLinkRespDTO();
        List<MmsAdShortLinkRespDTO.AdShortLinkResp> adShortLinkRespList = respDTO.getAdShortLinkRespList();
        for (MmsAdShortLinkReqDTO.AdShortInfo adShortInfo : reqDTO.getAdShortInfos()) {
            MmsAdShortLinkRespDTO.AdShortLinkResp adShortLinkResp = new MmsAdShortLinkRespDTO.AdShortLinkResp();
            if (CollectionUtils.isEmpty(adShortInfo.getMobiles()) || StrUtil.isEmpty(adShortInfo.getLinkAddress())) {
                throw new BusinessException("手机号和原始长链不存在");
            }
            ShortLinkAddConfigReqDTO shortConfigReqDTO = new ShortLinkAddConfigReqDTO();
            shortConfigReqDTO.setAddress(adShortInfo.getLinkAddress());
            shortConfigReqDTO.setCustomerNo(reqDTO.getCustomerNo());
            shortConfigReqDTO.setUserNo(reqDTO.getUserNo());
            shortConfigReqDTO.setLinkDomain(reqDTO.getLinkDomain());
            shortConfigReqDTO.setMobileNoParam("");
            shortConfigReqDTO.setPlatform(reqDTO.getPlatformNo());
            shortConfigReqDTO.setRemark("彩信广告推广长链");
            shortConfigReqDTO.setLinkType(LinkTypeEnum.BATCH.getCodeId());
            shortConfigReqDTO.setExpirationTime(DateUtil.nextWeek());
            shortConfigReqDTO.setLinkName(reqDTO.getLinkName());
            //先从redis中获取，如果没有，则从数据库中判断,
            String adMmsCacheLinkNoKey = RedisKeys.SHORT_LINK_MMS_AD_NO + reqDTO.getCustomerNo() + reqDTO.getUserNo() + adShortInfo.getLinkAddress();
            String cacheLinkNo = stringRedisCacheService.get(adMmsCacheLinkNoKey, String.class);
            String linkNo = "";
            if (StrUtil.isBlank(cacheLinkNo)) {
                ShortLinkCustomerConfigDataBO dataBO = ShortLinkCustomerConfigDataBO.builder().customerNo(reqDTO.getCustomerNo()).userNo(reqDTO.getCustomerNo())
                        .address(adShortInfo.getLinkAddress()).remark("彩信广告推广长链").tableTime(DateUtils.getCurrDate(DateUtils.DateFormatEnum.YYYY_MM_DD)).build();
                ShortLinkCustomerConfig existSameLinkNo = shortLinkCustomerConfigDataService.isExistSameLinkNo(dataBO);
                if (existSameLinkNo == null) {
                    ShortLinkAddConfigRespDTO addConfig = shortLinkRealtimeService.addConfig(shortConfigReqDTO);
                    if (addConfig == null || StrUtil.isEmpty(addConfig.getLinkNo())) {
                        throw new BusinessException("添加长链凭证失败");
                    }
                    linkNo = addConfig.getLinkNo();
                    stringRedisCacheService.set(adMmsCacheLinkNoKey, linkNo, SysTimeUtils.getRemainSecondsOneDay(new Date()));
                } else {
                    linkNo = existSameLinkNo.getLinkNo();
                    stringRedisCacheService.set(adMmsCacheLinkNoKey, linkNo, SysTimeUtils.getRemainSecondsOneDay(new Date()));
                }
            } else {
                linkNo = cacheLinkNo;
            }
            ShortLinkGenShortInfoReqDTO shortInfoReqDTO = new ShortLinkGenShortInfoReqDTO();
            shortInfoReqDTO.setLinkNo(linkNo);
            shortInfoReqDTO.setMobileList(adShortInfo.getMobiles());
            ShortLinkGenShortInfoRespDTO shortInfo = shortLinkRealtimeService.genShortInfo(shortInfoReqDTO);
            if (shortInfo == null || CollectionUtils.isEmpty(shortInfo.getLinkList())) {
                throw new BusinessException("生成短链失败");
            }
            List<String> shortLinkList = new ArrayList<>();
            for (int i = 0; i < shortInfo.getLinkList().size(); i++) {
                ShortLinkInfoDTO shortLinkInfoDTO = shortInfo.getLinkList().get(i);
                shortLinkList.add(shortLinkInfoDTO.getShortLinkNo());
            }
            adShortLinkResp.setLinkAddress(adShortInfo.getLinkAddress());
            adShortLinkResp.setShortLinkList(shortLinkList);
            adShortLinkResp.setBeginIndex(adShortInfo.getBeginIndex());
            adShortLinkResp.setEndIndex(adShortInfo.getEndIndex());
            adShortLinkRespList.add(adShortLinkResp);
        }
        return ResultData.getSuccessData(respDTO);
    }

    private ShortLinkRequestInfo getShortLinkRequestInfo(Long id, String tableTime) {
        ShortLinkRequestInfoExample example = new ShortLinkRequestInfoExample();
        example.createCriteria().andIdEqualTo(id).andTableTimeEqualTo(tableTime);
        List<ShortLinkRequestInfo> reqInfoList = shortLinkRequestInfoMapper.selectByExample(example);
        if (CollUtil.isEmpty(reqInfoList)) {
            return null;
        }
        return reqInfoList.get(0);
    }


    private ShortLinkCustomerConfigQueryDetailRespDTO buildDetailRespDTO(ShortLinkCustomerConfigDataBO result) {
        ShortLinkCustomerConfigQueryDetailRespDTO detailRespDTO = new ShortLinkCustomerConfigQueryDetailRespDTO();
        detailRespDTO.setLinkNo(result.getLinkNo());
        detailRespDTO.setCustomerNo(result.getCustomerNo());
        detailRespDTO.setUserNo(result.getUserNo());
        detailRespDTO.setLinkName(result.getLinkName());
        detailRespDTO.setAddress(result.getAddress());
        detailRespDTO.setMobileNoParam(result.getMobileNoParam());
        detailRespDTO.setStatus(result.getStatus());
        detailRespDTO.setLinkDomain(result.getLinkDomain());
        detailRespDTO.setExpirationTime(result.getExpirationTime());
        detailRespDTO.setCheckStatus(result.getCheckStatus());
        detailRespDTO.setRemark(result.getRemark());
        detailRespDTO.setLinkType(result.getLinkType());
        detailRespDTO.setSource(result.getSource());
        detailRespDTO.setOperatorId(result.getOperatorId());
        detailRespDTO.setCreateTime(result.getCreateTime());
        detailRespDTO.setUpdateTime(result.getUpdateTime());
        detailRespDTO.setShortLinkAddress(result.getShortLinkAddress());
        detailRespDTO.setShortNum(result.getLinkNum());
        detailRespDTO.setTableTime(result.getTableTime());
        return detailRespDTO;
    }

    private ShortLinkCustomerConfigQueryRespDTO buildRespDTO(ShortLinkCustomerConfigDataBO bo) {
        ShortLinkCustomerConfigQueryRespDTO shortLinkCustomerConfigQueryRespDTO = new ShortLinkCustomerConfigQueryRespDTO();
        shortLinkCustomerConfigQueryRespDTO.setLinkNo(bo.getLinkNo());
        shortLinkCustomerConfigQueryRespDTO.setCustomerNo(bo.getCustomerNo());
        shortLinkCustomerConfigQueryRespDTO.setUserNo(bo.getUserNo());
        shortLinkCustomerConfigQueryRespDTO.setLinkName(bo.getLinkName());
        shortLinkCustomerConfigQueryRespDTO.setAddress(bo.getAddress());
        shortLinkCustomerConfigQueryRespDTO.setMobileNoParam(bo.getMobileNoParam());
        String shortLinkAddress = bo.getShortLinkAddress();
        if (LinkTypeEnum.SINGLE.eq(bo.getLinkType())) {
            shortLinkAddress = bo.getLinkDomain() + "/" + shortLinkAddress;
        }
        shortLinkCustomerConfigQueryRespDTO.setSource(bo.getSource());
        shortLinkCustomerConfigQueryRespDTO.setShortLinkAddress(shortLinkAddress);
        shortLinkCustomerConfigQueryRespDTO.setStatus(bo.getStatus());
        shortLinkCustomerConfigQueryRespDTO.setExpirationTime(bo.getExpirationTime());
        shortLinkCustomerConfigQueryRespDTO.setLinkType(bo.getLinkType());
        shortLinkCustomerConfigQueryRespDTO.setCheckStatus(bo.getCheckStatus());
        shortLinkCustomerConfigQueryRespDTO.setOperatorId(bo.getOperatorId());
        shortLinkCustomerConfigQueryRespDTO.setCreateTime(bo.getCreateTime());
        return shortLinkCustomerConfigQueryRespDTO;
    }

    private ShortLinkInfoQueryRespDTO buildDetailRespDTO(ShortLinkInfoDataBO dataBO) {
        ShortLinkInfoQueryRespDTO shortLinkInfoQueryRespDTO = new ShortLinkInfoQueryRespDTO();
        shortLinkInfoQueryRespDTO.setMobileNo(dataBO.getMobileNo());
        shortLinkInfoQueryRespDTO.setShortLinkNo(dataBO.getLinkDomain() + "/" + dataBO.getShortLinkNo());
        shortLinkInfoQueryRespDTO.setId(dataBO.getId());
        return shortLinkInfoQueryRespDTO;
    }

    private ShortLinkRequestInfoQueryRespDTO buildDetailRespDTO(ShortLinkRequestInfoDataBO bo) {
        ShortLinkRequestInfoQueryRespDTO queryRespDTO = new ShortLinkRequestInfoQueryRespDTO();
        queryRespDTO.setId(bo.getId());
        queryRespDTO.setLinkNo(bo.getLinkNo());
        queryRespDTO.setCustomerNo(bo.getCustomerNo());
        queryRespDTO.setUserNo(bo.getUserNo());
        queryRespDTO.setAddressMd5(bo.getAddressMd5());
        queryRespDTO.setCreateTime(bo.getCreateTime());
        queryRespDTO.setTableTime(bo.getTableTime());
        queryRespDTO.setMobileProvince(bo.getMobileProvince());
        queryRespDTO.setMobileCity(bo.getMobileCity());
        queryRespDTO.setMobileIsp(bo.getMobileIsp());
        queryRespDTO.setIpProvince(bo.getIpProvince());
        queryRespDTO.setIpCity(bo.getIpCity());
        queryRespDTO.setIpIsp(bo.getIpIsp());
        queryRespDTO.setIpNumber(bo.getIpNumber());
        queryRespDTO.setBrand(bo.getBrand());
        queryRespDTO.setDataValidate(bo.getDataValidate());
        queryRespDTO.setBrowser(bo.getBrowser());
        queryRespDTO.setUserAgent(bo.getUserAgent());
        queryRespDTO.setShortLinkNo(bo.getLinkDomain() + "/" + bo.getShortLinkNo());
        queryRespDTO.setMobileNo(bo.getMobileNo());
        queryRespDTO.setRequestTime(bo.getRequestTime());
        queryRespDTO.setRequestIp(bo.getRequestIp());
        queryRespDTO.setOs(bo.getRequestDevice());
        return queryRespDTO;
    }


    public RequestRealtimeStatisticReq buildByDTO(RequestRealtimeStatisticReqDTO reqDTO) {
        return CglibUtil.copy(reqDTO, RequestRealtimeStatisticReq.class);
    }

    public List<RequestRealtimeStatisticRespDTO> buildListDTO(List<RequestRealtimeStatisticResp> list) {
        return list.stream().map(po -> CglibUtil.copy(po, RequestRealtimeStatisticRespDTO.class)).collect(Collectors.toList());
    }

}
