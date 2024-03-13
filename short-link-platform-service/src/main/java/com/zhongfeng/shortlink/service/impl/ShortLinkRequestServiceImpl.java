package com.zhongfeng.shortlink.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.LineHandler;
import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.net.Ipv4Util;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.Platform;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhongfeng.common.redis.service.StringRedisCacheService;
import com.zhongfeng.shortlink.api.client.req.BrandClickNumChartDataReq;
import com.zhongfeng.shortlink.api.client.req.DateRangeClickNumChartDataReq;
import com.zhongfeng.shortlink.api.client.req.ProvinceClickNumChartDataReq;
import com.zhongfeng.shortlink.api.client.resp.BrandClickNumChartDataResp;
import com.zhongfeng.shortlink.api.client.resp.DateRangeClickNumChartDataResp;
import com.zhongfeng.shortlink.api.client.resp.ProvinceClickNumChartDataResp;
import com.zhongfeng.shortlink.config.IPInitConfig;
import com.zhongfeng.shortlink.constant.ParamsConstant;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkRequestInfoDataService;
import com.zhongfeng.shortlink.persistence.dataservice.bo.BrandClickNumChartDataRespBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.DateRangeClickNumChartDataRespBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ProvinceClickNumChartDataRespBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkRequestInfoDataBO;
import com.zhongfeng.shortlink.persistence.dto.BrandClickNumChartDataReqDTO;
import com.zhongfeng.shortlink.persistence.dto.DateRangeClickNumChartDataReqDTO;
import com.zhongfeng.shortlink.persistence.dto.ProvinceClickNumChartDataReqDTO;
import com.zhongfeng.shortlink.persistence.enums.DataValidateEnum;
import com.zhongfeng.shortlink.service.DataService;
import com.zhongfeng.shortlink.service.MobileOpertorsService;
import com.zhongfeng.shortlink.service.ShortLinkRequestService;
import com.zhongfeng.shortlink.service.bo.OperatorsInfoBO;
import com.zhongfeng.shortlink.utils.UserAgentUtils;
import com.zhongfeng.sms.persistence.dataservice.PlatMobileModelDataService;
import com.zhongfeng.sms.persistence.dataservice.SysAreaService;
import com.zhongfeng.sms.persistence.dataservice.bo.PlatMobileModelDataBO;
import com.zhongfeng.sms.persistence.dataservice.bo.SysIpConfigDataBO;
import com.zhongfeng.sms.persistence.enums.MobileOSEnum;
import com.zhongfeng.sms.persistence.enums.SysParamEnum;
import com.zhongfeng.sms.persistence.enums.UaSourceEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 短链请求服务
 *
 * @auther fanjun
 */
@Service
@Slf4j
public class ShortLinkRequestServiceImpl implements ShortLinkRequestService {

    @Resource
    private MobileOpertorsService mobileOpertorsService;
    @Resource
    private PlatMobileModelDataService platMobileModelDataService;
    @Resource
    private ShortLinkRequestInfoDataService requestInfoDataService;
    @Resource
    private DataService dataService;
    @Resource
    private StringRedisCacheService stringRedisCacheService;

    @Autowired
    private SysAreaService sysAreaService;

    //    @Async("commonExecutors")
    @Override
    public void addRequestInfo(ShortLinkRequestInfoDataBO dataBO) {
        // 获取手机号码归属地
        String mobileNo = dataBO.getMobileNo();
        String userAgentStr = dataBO.getUserAgent();
        PlatMobileModelDataBO platMobileModelDataBO = null;

        if (StrUtil.isNotEmpty(mobileNo)) {
            MobileOSEnum os = UserAgentUtils.getOs(userAgentStr);
            platMobileModelDataBO = PlatMobileModelDataBO.builder()
                    .mobileNo(mobileNo)
                    .os(os.getCodeId())
                    .model(getMobileModel(userAgentStr))
                    .source(UaSourceEnum.SHORT_LINK_DATA.getCodeId())
                    .build();

            OperatorsInfoBO operatorsInfoBO = mobileOpertorsService.matchMobile(mobileNo);
            if (operatorsInfoBO != null) {
                dataBO.setMobileProvince(operatorsInfoBO.getProvince());
                dataBO.setMobileCity(operatorsInfoBO.getCity());
                dataBO.setMobileIsp(operatorsInfoBO.getOperatorType().toString());

                platMobileModelDataBO.setOperator(operatorsInfoBO.getOperatorType());
                platMobileModelDataBO.setProvince(operatorsInfoBO.getProvince());
                platMobileModelDataBO.setCity(operatorsInfoBO.getCity());
            }
        }

        // 获取IP归属
        String requestIp = dataBO.getRequestIp();
//        SysIpConfigDataBO sysIpConfigDataBO = sysIpConfigDataService.ipInfo(requestIp);
        dataBO.setIpNumber(Ipv4Util.ipv4ToLong(requestIp));
        SysIpConfigDataBO sysIpConfigDataBO = IPInitConfig.getIpByHalf(requestIp);
        if (sysIpConfigDataBO != null) {
            String regionId = StringUtils.isNotBlank(sysIpConfigDataBO.getRegionId()) ? sysIpConfigDataBO.getRegionId().substring(0, 2) : "";
            dataBO.setIpProvince(regionId);
            dataBO.setIpCity(sysIpConfigDataBO.getCityId());
            dataBO.setIpIsp(sysIpConfigDataBO.getIsp());
        }
        Byte brand = getBrand(userAgentStr);
        UserAgent userAgent = UserAgentUtil.parse(userAgentStr);

        //获取品牌、浏览器、设备类型
        dataBO.setBrand(brand);
        dataBO.setBrowser(getBrowser(userAgentStr));
        dataBO.setRequestDevice(getRequestDevice(userAgent.getPlatform()));

        if (StrUtil.isNotEmpty(userAgentStr) && userAgentStr.length() > 256) {
            log.error("[ShortLinkService] UserAgent超过256位,进行截取操作, {}", userAgentStr);
            dataBO.setUserAgent(userAgentStr.substring(0, 256));
        }
        // 添加访问记录
        requestInfoDataService.add(dataBO);

        //添加UA标签库
        if (platMobileModelDataBO != null && ("iOS".equals(dataBO.getRequestDevice()) || "Android".equals(dataBO.getRequestDevice()))) {
            platMobileModelDataBO.setBrand(brand);
            try {
                platMobileModelDataService.add(platMobileModelDataBO);
            } catch (Exception e) {
                platMobileModelDataService.update(platMobileModelDataBO);
            }
        }
        String linkNo = dataBO.getLinkNo();
        Byte dataValidate = dataBO.getDataValidate();
        //记录 总 PV UV IP保存30天
        stringRedisCacheService.incrementAndGet("ShortLink:PV:" + linkNo, Integer.MAX_VALUE, 1, 30 * 24 * 3600);
        if (DataValidateEnum.INITIAL_NORMAL.eq(dataValidate)) {
            stringRedisCacheService.incrementAndGet("ShortLink:UV:" + linkNo, Integer.MAX_VALUE, 1, 30 * 24 * 3600);
        }
        stringRedisCacheService.addSet("ShortLink:IP:" + linkNo, requestIp, 30 * 24 * 3600);
        //记录 短链类型 PV UV IP保存30天
        String shortLineType = dataBO.getShortLineType();
        if (StrUtil.isNotEmpty(shortLineType) && !ParamsConstant.SHORTLINETYPE.equals(shortLineType)) {
            stringRedisCacheService.incrementHash("shortLineType:PV:" + linkNo, shortLineType, 1, Integer.MAX_VALUE, 30 * 24 * 3600);
            if (DataValidateEnum.INITIAL_NORMAL.eq(dataValidate)) {
                stringRedisCacheService.incrementHash("shortLineType:UV:" + linkNo, shortLineType, 1, Integer.MAX_VALUE, 30 * 24 * 3600);
            }
            stringRedisCacheService.addSet("shortLineType:IP:" + linkNo, shortLineType, 30 * 24 * 3600);
            stringRedisCacheService.addSet("shortLineType:IP:" + linkNo + ":" + shortLineType, requestIp, 30 * 24 * 3600);
        }
    }

    @Override
    @Scheduled(fixedDelay = 60_000)
    public void refreshCache() {
        initMobileMobileMap();
    }

    /**
     * 省份点击数量地图图表数据
     *
     * @param req the req
     * @return the result data
     */
    @Override
    public List<ProvinceClickNumChartDataResp> provinceClickNumChartData(ProvinceClickNumChartDataReq req) {
        final ProvinceClickNumChartDataReqDTO dto = BeanUtil.copyProperties(req, ProvinceClickNumChartDataReqDTO.class);
        final List<ProvinceClickNumChartDataRespBO> list = requestInfoDataService.provinceClickNumChartData(dto);

        // key=2位数的省份编码 value=数量
        final Map<String, Integer> map = CollectionUtils.isEmpty(list) ? Maps.newHashMap() :
                list.stream().collect(Collectors.toMap(ProvinceClickNumChartDataRespBO::getProvinceNo, ProvinceClickNumChartDataRespBO::getNum, (a, b) -> b));

        // 获取所有省份的编码，2位数的省份编码
        final Collection<String> allProvinceNo = sysAreaService.getProvinceMap().values();

        // 构建返回结果
        return allProvinceNo.stream()
                .map(pNo -> ProvinceClickNumChartDataResp.builder()
                        .num(map.getOrDefault(pNo, 0))
                        // 省份编码补充完整
                        .provinceNo(pNo + "0000")
                        .build()
                ).collect(Collectors.toList());
    }

    /**
     * 时间段点击数量趋势折线图图表数据
     *
     * @param req the req
     * @return the result data
     */
    @Override
    public List<DateRangeClickNumChartDataResp> dateRangeClickNumChartData(DateRangeClickNumChartDataReq req) {
        final DateRangeClickNumChartDataReqDTO dto = BeanUtil.copyProperties(req, DateRangeClickNumChartDataReqDTO.class);
        // 设置请求日期，表的分片键
        dto.setStartTableTime(DateUtil.format(req.getStartDate(), "yyyyMMdd"));
        dto.setEndTableTime(DateUtil.format(req.getEndDate(), "yyyyMMdd"));

        final List<DateRangeClickNumChartDataRespBO> list = requestInfoDataService.dateRangeClickNumChartData(dto);

        // key=时间轴 value=数量
        final Map<String, Integer> map = CollectionUtils.isEmpty(list) ? Maps.newHashMap() :
                list.stream().collect(Collectors.toMap(DateRangeClickNumChartDataRespBO::getTimeAxis, DateRangeClickNumChartDataRespBO::getNum, (a, b) -> b));

        final Integer statRule = dto.getStatRule();

        final Date newStartDate = Objects.equals(1, statRule) ? req.getStartDate() : DateUtil.beginOfDay(req.getStartDate());
        final Date newEndDate = Objects.equals(1, statRule) ? req.getEndDate() : DateUtil.endOfDay(req.getEndDate());

        final DateField dateField = Objects.equals(1, statRule) ? DateField.DAY_OF_MONTH : DateField.HOUR_OF_DAY;
        final List<DateTime> dateRange = DateUtil.rangeToList(newStartDate, newEndDate, dateField);

        // 构建返回结果
        return dateRange.stream()
                .map(dr -> {
                            final String timeAxis = Objects.equals(1, statRule) ? dr.toDateStr() : dr.getField(DateField.HOUR_OF_DAY) + "";
                            return DateRangeClickNumChartDataResp.builder()
                                    .timeAxis(timeAxis)
                                    .num(map.getOrDefault(timeAxis, 0))
                                    .build();
                        }
                ).collect(Collectors.toList());
    }


    /**
     * 手机品牌点击数量柱状图图表数据
     *
     * @param req the req
     * @return the result data
     */
    @Override
    public List<BrandClickNumChartDataResp> brandClickNumChartData(BrandClickNumChartDataReq req) {
        final BrandClickNumChartDataReqDTO dto = BeanUtil.copyProperties(req, BrandClickNumChartDataReqDTO.class);
        final List<BrandClickNumChartDataRespBO> list = requestInfoDataService.brandClickNumChartData(dto);

        // key=品牌标识 value=数量
        final Map<Integer, Integer> map = CollectionUtils.isEmpty(list) ? Maps.newHashMap() :
                list.stream().collect(Collectors.toMap(BrandClickNumChartDataRespBO::getBrand, BrandClickNumChartDataRespBO::getNum, (a, b) -> b));

        // 获取所有手机品牌 key=品牌标识 value=品牌名称
        final Map<Integer, String> allBrand = dataService.getAllBrand();

        final List<BrandClickNumChartDataResp> r = Lists.newArrayListWithExpectedSize(map.size());

        allBrand.forEach((k, v) -> {
            BrandClickNumChartDataResp resp = BrandClickNumChartDataResp.builder()
                    .brandName(v)
                    .num(map.getOrDefault(k, 0))
                    .build();
            r.add(resp);
        });
        return r;
    }

    private static Map<String, String> mobileModelMap;
    private static Long modelLastTime;

    public static void initMobileMobileMap() {
        String modelPath = getProjectPath() + "/model.json";
        File file = FileUtil.file(modelPath);
        if (!file.exists()) {
            log.error("[ShortLinkRequestService] 机型配置文件不存在: {}", modelPath);
            return;
        }
        long lastTime = file.lastModified();
        if (mobileModelMap == null || modelLastTime == null || lastTime != modelLastTime) {
            Map<String, String> tmpMap = new HashMap<>();
            FileUtil.readUtf8Lines(file, (LineHandler) line -> {
                if (StrUtil.isNotEmpty(line)) {
                    try {
                        JSONObject json = JSONUtil.parseObj(line);
                        String model = json.getStr("Model");
                        String brand = json.getStr("Brand");
                        String outModel = json.getStr("OutModel");
                        String modelStr = outModel;
                        if (!outModel.contains(brand)) {
                            modelStr = brand + outModel;
                        }
                        if (!"未知".equals(modelStr)) {
                            tmpMap.put(model, modelStr);
                        }
                    } catch (Exception e) {
                        log.error("机型解析失败", e);
                    }
                }
            });
            mobileModelMap = tmpMap;
            modelLastTime = lastTime;
            log.warn("[ShortLinkRequestService] 机型配置加载完成: {}", modelPath);
        }
    }

    private Byte getBrand(String userAgent) {
        String modelToBrand = dataService.getSysParam(SysParamEnum.MODEL_TO_BRAND);
//        String modelToBrand = "iphone:1,huawei:2,oppo:3,vivo:4,mi:5";
        String[] modelBrands = modelToBrand.split(",");
        for (String modelBrand : modelBrands) {
            String modelStr = StrUtil.subBefore(modelBrand, ":", false);
            String brand = StrUtil.subAfter(modelBrand, ":", true);
            if (StrUtil.hasEmpty(modelStr, brand)) {
                continue;
            }
            String[] models = modelStr.split(";");
            for (String model : models) {
                if (userAgent.toLowerCase().contains(model.toLowerCase())) {
                    try {
                        return Byte.parseByte(brand);
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                }
            }
        }
        return 0;
    }

    private static String getRequestDevice(Platform platform) {
        if (Arrays.stream("iPhone,iPod,iPad".split(",")).anyMatch(platform.getName()::equals)) {
            return "iOS";
        } else if ("Android".equalsIgnoreCase(platform.getName())) {
            return "Android";
        } else if ("Windows".equalsIgnoreCase(platform.getName())) {
            return "Windows";
        } else if ("Mac".equalsIgnoreCase(platform.getName())) {
            return "Mac";
        }
        return "Other";
    }

    private static String getBrowser(String userAgent) {
        if (isContains("MicroMessenger", userAgent)) {
            return "MicroMessenger";
        } else if (isContains("QQ", userAgent)) {
            return "QQ";
        } else if (isContains(Arrays.asList("Chrome,Firefox,MSIE,MSIE11".split(",")), userAgent)) {
            return "Browser";
        }
        return "Other";
    }

    //获取手机机型
    public static String getMobileModel(String userAgent) {
        if (isContains("iPhone", userAgent)) {
            String version = ReUtil.getGroup1("Version/(\\d+(\\.\\d+){1,2})", userAgent);
            String detail = ReUtil.getGroup1("Mobile/(\\w+)", userAgent);
            String desc = "iPhone" + version;
            if (StrUtil.isNotEmpty(detail)) {
                desc += "(" + detail + ")";
            }
            return desc;
        }
        if (mobileModelMap != null) {
            for (Map.Entry<String, String> entry : mobileModelMap.entrySet()) {
                if (isContains(entry.getKey(), userAgent)) {
                    return entry.getValue();
                }
            }
        }
        return "Other";
    }

    private static boolean isContains(String keyword, String content) {
        return ReUtil.contains(PatternPool.get("\\b" + keyword + "\\b", Pattern.DOTALL | Pattern.CASE_INSENSITIVE), content);
    }

    private static boolean isContains(List<String> keywordList, String content) {
        return keywordList.stream().anyMatch(keyword -> isContains(keyword, content));
    }

    public static String getProjectPath() {
        return System.getProperties().getProperty("user.dir").replace("\\", "/");
    }


    public static void main(String[] args) {
//        System.out.println(ReUtil.contains(PatternPool.get("\\bqq\\b", Pattern.DOTALL | Pattern.CASE_INSENSITIVE), "12312312 qQ welkrwlk"));
        System.out.println(getMobileModel("Mozilla/5.0 (iPhone; CPU iPhone OS 14_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0.2 Mobile/15E148 Safari/604.1\n"));
    }
}
