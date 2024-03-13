package com.zhongfeng.shortlink.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.zhongfeng.common.base.exception.BusinessException;
import com.zhongfeng.common.base.service.SftpBaseService;
import com.zhongfeng.common.base.utils.DateUtils;
import com.zhongfeng.common.base.utils.JacksonUtils;
import com.zhongfeng.common.base.utils.MobileUtils;
import com.zhongfeng.common.base.utils.ThreadUtils;
import com.zhongfeng.common.redis.service.RedisCacheService;
import com.zhongfeng.shortlink.api.dto.ShortLinkBatchAddDTO;
import com.zhongfeng.shortlink.constant.SysConstant;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkCustomerConfigDataService;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkInfoDataService;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCustomerConfigDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkInfoDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkRequestInfoDataBO;
import com.zhongfeng.shortlink.persistence.enums.CheckStatusEnum;
import com.zhongfeng.shortlink.persistence.enums.DataValidateEnum;
import com.zhongfeng.shortlink.persistence.enums.LinkTypeEnum;
import com.zhongfeng.shortlink.persistence.enums.StatusEnum;
import com.zhongfeng.shortlink.persistence.mapper.ShortLinkProcessMapper;
import com.zhongfeng.shortlink.service.*;
import com.zhongfeng.shortlink.service.bo.ShortLinkLocalCache;
import com.zhongfeng.shortlink.service.bo.ShortLinkLookupBO;
import com.zhongfeng.shortlink.utils.RSAUtils;
import com.zhongfeng.shortlink.utils.SysTimeUtils;
import com.zhongfeng.sms.persistence.enums.RecordStatusEnum;
import com.zhongfeng.sms.persistence.enums.SysParamEnum;
import com.zhongfeng.sms.persistence.enums.UploadPathEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author yuzc
 * @date 2020/2/23 13:39
 */
@Service
@Slf4j
public class ShortLinkServiceImpl implements ShortLinkService {

    @Autowired
    private DataService dataService;
    @Resource
    private ShortLinkProcessMapper batchProcessMapper;
    @Autowired
    private SeqNumberGenerateService seqNumberGenerateService;
    @Autowired
    private SftpBaseService sftpBaseService;
    @Autowired
    private ShortLinkCustomerConfigDataService shortLinkCustomerConfigDataService;
    @Resource
    private RedisCacheService redisCacheService;
    @Resource
    private ShortLinkRequestService shortLinkRequestService;
    @Resource
    private ShortLinkRedisCacheService shortLinkRedisCacheService;
    @Resource
    private ShortLinkLocalCacheService shortLinkLocalCacheService;


    @Resource
    private ShortLinkInfoDataService shortLinkInfoDataService;


    @Value("${short.window.whitelist:119.136.22.61}")
    private String whitelist;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchMobileFile(ShortLinkBatchAddDTO reqDTO) {
        String linkNo = seqNumberGenerateService.getLinkNo();
        String fileName = reqDTO.getFileName();
        String completeLocalPath = UploadPathEnum.getCompleteLocalPath(dataService.getSysParam(SysParamEnum.IMPORT_FILE_DIR), UploadPathEnum.WebShortLinkMobileFile, Strings.EMPTY, fileName);
        String completeRemotePath = UploadPathEnum.getCompleteRemotePath(dataService.getSysParam(SysParamEnum.UPLOAD_FILE_DIR), UploadPathEnum.WebShortLinkMobileFile, reqDTO.getOperatorId(), fileName);
        if (completeRemotePath.contains("\\")) {
            completeRemotePath = completeRemotePath.replace("\\", "/");
        }
        File downFile;
        try {
            downFile = sftpBaseService.download(completeRemotePath, completeLocalPath);
        } catch (Exception e) {
            ThreadUtils.sleep(2000);
            downFile = sftpBaseService.download(completeRemotePath, completeLocalPath);
        }

        List<ShortLinkInfoDataBO> shortLinkInfoDataBOList = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            String tableTime = DateUtil.date().toString(DatePattern.PURE_DATE_PATTERN);
            AtomicInteger readLine = new AtomicInteger();
            int importTotalNum = 0;
            String mobileNo;
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(downFile)));
            AtomicInteger addCount = new AtomicInteger();
            long allStartTime = System.currentTimeMillis();
            while ((mobileNo = bufferedReader.readLine()) != null) {
                if (StringUtils.isBlank(mobileNo)) {
                    if (readLine.incrementAndGet() > 10) {
                        break;
                    }
                    log.warn("换行 {}", readLine.get());
                    continue;
                }
                readLine.set(0);
                mobileNo = mobileNo.trim();
                // 验证手机号格式
                if (!MobileUtils.checkMobileReg(mobileNo)) {
                    log.warn("[generate] 链接{} 手机号{}格式错误", linkNo, mobileNo);
                    continue;
                }
                // 生成短链数据
                ShortLinkInfoDataBO shortLinkInfoDataBO = ShortLinkInfoDataBO.builder().build();
                shortLinkInfoDataBO.setShortLinkNo(seqNumberGenerateService.getShortLinkNo());
                shortLinkInfoDataBO.setLinkNo(linkNo);
                shortLinkInfoDataBO.setMobileNo(mobileNo);
                shortLinkInfoDataBO.setLinkDomain(reqDTO.getLinkDomain());
                shortLinkInfoDataBO.setTableTime(tableTime);
                shortLinkInfoDataBO.setShortLineType(reqDTO.getLinkName());
                shortLinkInfoDataBOList.add(shortLinkInfoDataBO);
                if (shortLinkInfoDataBOList.size() < 2000) {
                    continue;
                }
                long startTime = System.currentTimeMillis();
                if (batchProcessMapper.batchInsertShortLinkInfo(tableTime, shortLinkInfoDataBOList) != shortLinkInfoDataBOList.size()) {
                    log.error("[generate]  批量插入失败 {}", JacksonUtils.jsonObjectSerializer(shortLinkInfoDataBOList));
                    throw new BusinessException("导入短链批次失败");
                }
                addCount.incrementAndGet();
                log.warn("[generate] 批量插入短链数据, 条数: {}, 批次: {}, 耗时: {}", shortLinkInfoDataBOList.size(), addCount.get(), SysTimeUtils.showTimeNow(startTime));
                importTotalNum += shortLinkInfoDataBOList.size();

                startTime = System.currentTimeMillis();
                shortLinkRedisCacheService.add(shortLinkInfoDataBOList);
                log.warn("[Redis缓存] 添加RedisInfo缓存, linkNo: {}, 条数: {}, 批次: {}, 耗时: {}", linkNo, shortLinkInfoDataBOList.size(), addCount.get(), SysTimeUtils.showTimeNow(startTime));

                shortLinkInfoDataBOList.clear();
            }
            if (!CollectionUtils.isEmpty(shortLinkInfoDataBOList)) {
                try {

                    long startTime = System.currentTimeMillis();
                    if (batchProcessMapper.batchInsertShortLinkInfo(tableTime, shortLinkInfoDataBOList) != shortLinkInfoDataBOList.size()) {
                        log.error("[generate]  批量插入失败 {}", JacksonUtils.jsonObjectSerializer(shortLinkInfoDataBOList));
                        throw new BusinessException("导入短链批次失败");
                    }
                    addCount.incrementAndGet();
                    log.warn("[generate] 批量插入短链数据, 条数: {}, 批次: {}, 耗时: {}", shortLinkInfoDataBOList.size(), addCount.get(), SysTimeUtils.showTimeNow(startTime));
                    importTotalNum += shortLinkInfoDataBOList.size();

                    startTime = System.currentTimeMillis();
                    shortLinkRedisCacheService.add(shortLinkInfoDataBOList);
                    log.warn("[Redis缓存] 添加RedisInfo缓存, linkNo: {}, 条数: {}, 批次: {}, 耗时: {}", linkNo, shortLinkInfoDataBOList.size(), addCount.get(), SysTimeUtils.showTimeNow(startTime));

                } catch (Exception e) {
                    log.error("[generate] 批量插入失败 {}", JacksonUtils.jsonObjectSerializer(shortLinkInfoDataBOList), e);
                    throw new BusinessException("导入短链批次失败");
                }
            }
            log.warn("[generate] 批量生成短链完成, LinkNo:{}, 总数: {}, 总耗时: {}", linkNo, importTotalNum, SysTimeUtils.showTimeNow(allStartTime));

            ShortLinkCustomerConfigDataBO shortLinkCustomerConfigDataBO = new ShortLinkCustomerConfigDataBO();
            shortLinkCustomerConfigDataBO.setLinkNo(linkNo);
            shortLinkCustomerConfigDataBO.setLinkName(reqDTO.getLinkName());
            shortLinkCustomerConfigDataBO.setPlatformNo(reqDTO.getPlatformNo());
            shortLinkCustomerConfigDataBO.setCustomerNo(reqDTO.getCustomerNo());
            shortLinkCustomerConfigDataBO.setUserNo(reqDTO.getUserNo());
            shortLinkCustomerConfigDataBO.setAddress(reqDTO.getAddress());
            shortLinkCustomerConfigDataBO.setRemark(reqDTO.getRemark());
            shortLinkCustomerConfigDataBO.setLinkDomain(reqDTO.getLinkDomain());
            shortLinkCustomerConfigDataBO.setLinkType(LinkTypeEnum.BATCH.getCodeId());
            shortLinkCustomerConfigDataBO.setSource(reqDTO.getSource());
            shortLinkCustomerConfigDataBO.setLinkNum(importTotalNum);
            shortLinkCustomerConfigDataBO.setExpirationTime(reqDTO.getExpirationTime());
            shortLinkCustomerConfigDataBO.setOperatorId(reqDTO.getOperatorId());
            shortLinkCustomerConfigDataBO.setStatus((RecordStatusEnum.OK.getCodeId()));
            shortLinkCustomerConfigDataBO.setAddressMd5(SecureUtil.md5(reqDTO.getAddress()));
            shortLinkCustomerConfigDataBO.setMobileNoParam(reqDTO.getMobileNoParam());
            // 短链生成
            shortLinkCustomerConfigDataBO.setShortLinkAddress(fileName);
            shortLinkCustomerConfigDataBO.setCheckStatus(CheckStatusEnum.CHECK_PASS.getCodeId());
            shortLinkCustomerConfigDataBO.setTableTime(tableTime);
            shortLinkCustomerConfigDataService.add(shortLinkCustomerConfigDataBO);

            shortLinkRedisCacheService.add(linkNo, shortLinkCustomerConfigDataBO);
            shortLinkLocalCacheService.syncAdd(ListUtil.of(linkNo));

        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    log.error("[generate] ", e);
                }
            }
        }
    }

    @Override
    public String lookup(ShortLinkLookupBO linkLookupBO) {
        String shortLinkNo = linkLookupBO.getLinkNo();
        String userAgent = linkLookupBO.getUserAgent();
        String ipAddr = linkLookupBO.getRequestIp();
//        // 校验IP黑名单
//        Boolean ipStatus = dataService.ipStatus(ipAddr);
//        if (ipAddr != null && !ipStatus) {
//            log.warn("[ShortLinkService]ip黑名单, linkNo: {}, ip: {}", shortLinkNo, ipAddr);
//            return "";
//        }
        String ipAccessMaxStr = dataService.getSysParam(SysParamEnum.SHORTLINK_IP_ACCESS_MAX_COUNT);
        String[] ipMaxs = ipAccessMaxStr.split("\\|");
        if (ipMaxs.length > 0) {
            // 校验频次
            int ipAccessMax = Integer.parseInt(ipMaxs[0]);
            long ipCount = redisCacheService.incrementAndGet("IpCount:" + ipAddr + ":" + shortLinkNo, Integer.MAX_VALUE, 1);
            if (ipCount > ipAccessMax) {
                log.warn("[ShortLinkService]{}-{} 超过同一短链同一IP每秒最大访问频次", shortLinkNo, ipAddr);
                return "";
            }
        }
        if (ipMaxs.length > 1) {
            int ipAccessMax = Integer.parseInt(ipMaxs[1]);
            long ipCount = redisCacheService.incrementAndGet("IpCount:Second:" + ipAddr, Integer.MAX_VALUE, 1);
            if (ipCount > ipAccessMax) {
                log.warn("[ShortLinkService]{}-{} 超过IP每秒最大访频次", shortLinkNo, ipAddr);
                return "";
            }
        }
//        if (ipMaxs.length > 2) {
//            int ipAccessMax = Integer.parseInt(ipMaxs[2]);
//            long ipCount = redisCacheService.incrementAndGet("IpCount:Minute:" + ipAddr, Integer.MAX_VALUE, 60);
//            if (ipCount > ipAccessMax) {
//                log.warn("[ShortLinkService]超过IP每分钟最大访频次");
//                return "";
//            }
//        }
//        if (ipMaxs.length > 3) {
//            int ipAccessMax = Integer.parseInt(ipMaxs[3]);
//            long ipCount = redisCacheService.incrementAndGet("IpCount:Hour:" + ipAddr, Integer.MAX_VALUE, 3600);
//            if (ipCount > ipAccessMax) {
//                log.warn("[ShortLinkService]超过IP每小时最大访频次");
//                return "";
//            }
//        }
        if (ipMaxs.length > 2) {
            int ipAccessMax = Integer.parseInt(ipMaxs[2]);
            long ipCount = redisCacheService.incrementAndGet("IpCount:Day:" + ipAddr, Integer.MAX_VALUE, 24 * 3600);
            if (ipCount > ipAccessMax) {
                log.warn("[ShortLinkService]{}-{} 超过IP每天最大访频次", shortLinkNo, ipAddr);
                return "";
            }
        }

        // 获取短链接信息
        ShortLinkInfoDataBO shortLinkInfo = getShortLinkInfo(shortLinkNo);
        //获取短链配置
        ShortLinkCustomerConfigDataBO shortLinkCustomerConfig;
        if (shortLinkInfo == null) {
            // 获取客户配置信息
            shortLinkCustomerConfig = getShortLinkConfig(shortLinkNo); //短链获取配置
        } else {
            shortLinkCustomerConfig = getShortLinkConfig(shortLinkInfo.getLinkNo()); //linkNo获取配置
        }
        // 单一链接获取客户配置信息
        if (shortLinkCustomerConfig == null) {
            log.warn("[ShortLinkService]{}-{} 该短链找不到源地址", shortLinkNo, ipAddr);
            return "";
        }
        //针对同一长链的做Ip限制
        String linkNoIpDayAccessMaxStr = dataService.getSysParam(SysParamEnum.SHORTLINK_LINKNO_IP_DAY_MAX_COUNT);
        if (SysTimeUtils.isInteger(linkNoIpDayAccessMaxStr, true)) {
            long dayLinkNNoIpCount = redisCacheService.incrementAndGet("LinkNoIpCount:Day:" + shortLinkCustomerConfig.getLinkNo() + "-" + ipAddr, Integer.MAX_VALUE, 24 * 3600);
            if (dayLinkNNoIpCount > Integer.parseInt(linkNoIpDayAccessMaxStr)) {
                log.warn("[ShortLinkService]{}-{} 同一长链批次中相同IP超过每天最大访频次,linkNo:{}", shortLinkNo, ipAddr, shortLinkCustomerConfig.getLinkNo());
                return "";
            }
        }
        String linkNoIpHourAccessMaxStr = dataService.getSysParam(SysParamEnum.SHORTLINK_LINKNO_IP_HOUR_MAX_COUNT);
        if (SysTimeUtils.isInteger(linkNoIpHourAccessMaxStr, true)) {
            long hourLinkNNoIpCount = redisCacheService.incrementAndGet("LinkNoIpCount:Hour:" + shortLinkCustomerConfig.getLinkNo() + "-" + ipAddr, Integer.MAX_VALUE, 3600);
            if (hourLinkNNoIpCount > Integer.parseInt(linkNoIpHourAccessMaxStr)) {
                log.warn("[ShortLinkService]{}-{} 同一长链批次中相同IP超过每小时最大访频次,linkNo:{}", shortLinkNo, ipAddr, shortLinkCustomerConfig.getLinkNo());
                return "";
            }
        }
        ;
        // 校验状态
        Byte status = shortLinkCustomerConfig.getStatus();
        if (!StatusEnum.ENABLE.eq(status)) {
            log.warn("[ShortLinkService]{}-{} 客户链接配置未启用,linkNo:{}", shortLinkNo, ipAddr, shortLinkCustomerConfig.getLinkNo());
            return "";
        }
        // 校验审核状态
        Byte checkStatus = shortLinkCustomerConfig.getCheckStatus();
        if (checkStatus != null && !CheckStatusEnum.CHECK_PASS.eq(checkStatus)) {
            log.warn("[ShortLinkService]{}-{} 客户链接配置未通过审核,linkNo:{}", shortLinkNo, ipAddr, shortLinkCustomerConfig.getLinkNo());
            return "";
        }
        // 校验是否失效
        Date expirationTime = shortLinkCustomerConfig.getExpirationTime();
        if (expirationTime != null && DateUtil.date().isAfter(expirationTime)) {
            log.warn("[ShortLinkService]{}-{} 客户链接配置已经失效, {}", shortLinkNo, ipAddr, shortLinkCustomerConfig.getLinkNo());
            return "";
        }
        // 校验疑似无效
        DataValidateEnum dataValidateEnum = DataValidateEnum.INITIAL_NORMAL;
        String pvDate = linkLookupBO.getPvDate();
        String currDate = DateUtil.date().toString("yyyyMMdd");
        log.info("[ShortLinkService]{}-{}   pvDate:{},currDate:{}", shortLinkNo, ipAddr, pvDate, currDate);
        if (pvDate != null
                && NumberUtil.isInteger(pvDate)
                && Integer.parseInt(pvDate) >= Integer.parseInt(currDate)) {
            //同一PV访问
            dataValidateEnum = DataValidateEnum.SUSPECT_INVALID;
            log.warn("[ShortLinkService]{}-{} 同一用户点击,不做UV统计,IP:{},currDate{},pvDate:{}", shortLinkNo, ipAddr, ipAddr, currDate, pvDate);
        } else {
            //当天不同PV访问
            linkLookupBO.setPvDate(currDate);
        }
//        int ipAccessFrequency = dataService.getSysParamInt(SysParamEnum.SHORTLINK_IP_ACCESS_WARN_COUNT);
//        if (ipCount > ipAccessFrequency) {
//            dataValidateEnum = DataValidateEnum.SUSPECT_INVALID;
//        }
        ShortLinkRequestInfoDataBO dataBO = new ShortLinkRequestInfoDataBO();
        dataBO.setShortLinkNo(shortLinkNo);
        dataBO.setLinkNo(shortLinkCustomerConfig.getLinkNo());
        dataBO.setLinkDomain(linkLookupBO.getRequestServerName());
        dataBO.setMobileNo(Optional.ofNullable(shortLinkInfo).map(ShortLinkInfoDataBO::getMobileNo).orElse(null));
        dataBO.setShortLineType(Optional.ofNullable(shortLinkInfo).map(ShortLinkInfoDataBO::getShortLineType).orElse(null));
        dataBO.setRequestTime(DateUtils.now());
        dataBO.setRequestIp(ipAddr);
//        dataBO.setRequestDevice(UserAgentUtils.getDeviceInfo(userAgent));
        dataBO.setUserAgent(userAgent);
        dataBO.setTableTime(currDate);
        dataBO.setDataValidate(dataValidateEnum.getCodeId());
        dataBO.setCustomerNo(shortLinkCustomerConfig.getCustomerNo());
        dataBO.setUserNo(shortLinkCustomerConfig.getUserNo());
        dataBO.setAddressMd5(shortLinkCustomerConfig.getAddressMd5());
        //异步添加访问记录
//        instance.addRequestInfo(dataBO);
//        ((ShortLinkService) AopContext.currentProxy()).addRequestInfo(dataBO);

        SysConstant.commonExecutors.execute(() -> shortLinkRequestService.addRequestInfo(dataBO));

        //每个短链对应一个地址业务场景
        if (shortLinkCustomerConfig.getLinkType() == LinkTypeEnum.MULTIPLE_LINK_BATCH.getCodeId()) {
            if (shortLinkInfo != null) {
                //当长链配置中有手机号参数名且短链对应的手机号不为空时，则将手机号添加到原始长链后面
                if (StringUtils.isNotBlank(shortLinkCustomerConfig.getMobileNoParam()) && StrUtil.isNotEmpty(shortLinkInfo.getMobileNo())) {
                    String paramsSeparatorChar = "?";
                    if (shortLinkInfo.getUrl().contains("?")) {
                        paramsSeparatorChar = "&";
                    }
                    String privateKey = dataService.getSysParam(SysParamEnum.SHORTLINK_MOBILE_PARAM_PRIVATE_KEY);
                    String encryptMobile = RSAUtils.encryptByPublicKey(shortLinkInfo.getMobileNo(), privateKey);
                    if (StrUtil.isNotEmpty(encryptMobile)) {
                        log.info("[ShortLinkService]{}-{} 添加手机号加密参数,参数名:{},加密值:{}", shortLinkNo, ipAddr, shortLinkCustomerConfig.getMobileNoParam(), encryptMobile);
                        return shortLinkCustomerConfig.getAddress() + paramsSeparatorChar + shortLinkCustomerConfig.getMobileNoParam() + "=" + encryptMobile;
                    }
                    return shortLinkInfo.getUrl();
                } else {
                    return shortLinkInfo.getUrl();
                }
            } else {
                ShortLinkInfoDataBO shortLinkInfoDataBO = shortLinkInfoDataService.get(shortLinkNo, shortLinkCustomerConfig.getTableTime());
                return shortLinkInfoDataBO.getUrl();
            }
        }

        if (shortLinkInfo == null) {
            return shortLinkCustomerConfig.getAddress();
        }
        if (StringUtils.isNotBlank(shortLinkCustomerConfig.getMobileNoParam()) && StrUtil.isNotEmpty(shortLinkInfo.getMobileNo())) {
            String paramsSeparatorChar = "?";
            if (shortLinkCustomerConfig.getAddress().contains("?")) {
                paramsSeparatorChar = "&";
            }
            String mobileNoEncrypt = dataService.getSysParam(SysParamEnum.SHORTLINK_LINK_PARAM_ENCRYPT);
            String mobileNoValue = shortLinkInfo.getMobileNo();
            if("1".equals(mobileNoEncrypt)){
                //需要加密
                String privateKey = dataService.getSysParam(SysParamEnum.SHORTLINK_MOBILE_PARAM_PRIVATE_KEY);
                mobileNoValue = RSAUtils.encryptByPublicKey(shortLinkInfo.getMobileNo(), privateKey);
                log.info("[ShortLinkService]{}-{} 添加手机号加密参数,参数名:{},加密值:{}", shortLinkNo, ipAddr, shortLinkCustomerConfig.getMobileNoParam(), mobileNoValue);
            }
            if (StrUtil.isNotEmpty(mobileNoValue)) {
                return shortLinkCustomerConfig.getAddress() + paramsSeparatorChar + shortLinkCustomerConfig.getMobileNoParam() + "=" + mobileNoValue;
            }
        }
        return shortLinkCustomerConfig.getAddress();
    }

    @Override
    public boolean isPass(String ip, String userAgent) {
        List<String> windowWhiteList = Arrays.stream(whitelist.split(",")).collect(Collectors.toList());
        if (windowWhiteList.contains(ip)) {
            return true;
        }
        if (userAgent.contains("Windows")) {
            return false;
        }
        return true;
    }

    //获取短链信息
    private ShortLinkInfoDataBO getShortLinkInfo(String shortLinkNo) {
        ShortLinkInfoDataBO shortLinkInfo = ShortLinkLocalCache.getInfo(shortLinkNo);
        if (shortLinkInfo != null) {
            log.info("[本地缓存] 获取info本地缓存: {},shortLinkInfo {}", shortLinkNo, shortLinkInfo);
            return shortLinkInfo;
        }
        shortLinkInfo = shortLinkRedisCacheService.getInfo(shortLinkNo);
        log.info("[Redis缓存] 取出redis缓存:shortLinkInfo  {}", shortLinkInfo);
        if (shortLinkInfo != null) {
            shortLinkInfo.setShortLinkNo(shortLinkNo);
            ShortLinkLocalCache.put(shortLinkInfo);
            log.warn("[Redis缓存] 获取infoRedis缓存: {}, 同时缓存至本地 shortLinkInfo{}", shortLinkNo, shortLinkInfo);
        }
        return shortLinkInfo;
    }

    //获取短链配置
    private ShortLinkCustomerConfigDataBO getShortLinkConfig(String key) {
        ShortLinkCustomerConfigDataBO shortLinkConfig = ShortLinkLocalCache.getConfig(key);
        if (shortLinkConfig != null) {
            log.info("[本地缓存] 获取config本地缓存: {}", key);
            return shortLinkConfig;
        }
        shortLinkConfig = shortLinkRedisCacheService.getConfig(key);
        if (shortLinkConfig != null) {
            ShortLinkLocalCache.put(key, shortLinkConfig);
            log.warn("[Redis缓存] 获取RedisConfig缓存: {}, 同时缓存至本地", key);
        }
        return shortLinkConfig;
    }
}
