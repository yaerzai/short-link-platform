package com.zhongfeng.shortlink.aop;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateException;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.zhongfeng.common.base.exception.BusinessException;
import com.zhongfeng.common.base.utils.JacksonUtils;
import com.zhongfeng.common.swagger.model.ResultData;
import com.zhongfeng.shortlink.api.client.req.ShortLinkClientBaseReq;
import com.zhongfeng.shortlink.facade.api.ShortLinkRequestController;
import com.zhongfeng.shortlink.facade.dto.ShortLinkBaseDTO;
import com.zhongfeng.shortlink.service.DataService;
import com.zhongfeng.sms.persistence.enums.SysParamEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * API请求打印请求参数信息
 * 针对参数中的String类型做trim操作
 * <p>
 * API响应打印响应数据信息
 *
 * @author luoli
 * create on 2019/3/23
 */
@Aspect
@Component
@Slf4j
public class ControllerAspect {

    @Resource
    private DataService dataService;

    @Pointcut("execution(* com.zhongfeng.shortlink.facade.api..*(..))")
    public void apiItf() {
    }

    @Before("apiItf()")
    public void before(JoinPoint joinPoint) throws Exception {
        Object[] args = joinPoint.getArgs();
        if (args == null || joinPoint.getArgs().length == 0) {
            return;
        }
        Class<?> targetClass = joinPoint.getTarget().getClass();
        if (targetClass.isAssignableFrom(ShortLinkRequestController.class)) {
            return;
        }
        Object[] object = joinPoint.getArgs();
        Object request = object[0];
        if (request instanceof MultipartFile) {
            return;
        }
        if (request instanceof ShortLinkBaseDTO) {
            checkSign((ShortLinkBaseDTO) request);
        }

        // openFeign接口的Controller层签名校验
        if (request instanceof ShortLinkClientBaseReq) {
            final ShortLinkClientBaseReq shortLinkClientBaseReq = (ShortLinkClientBaseReq) request;
            final ShortLinkBaseDTO shortLinkBaseDTO = BeanUtil.copyProperties(shortLinkClientBaseReq, ShortLinkBaseDTO.class);
            this.checkSign(shortLinkBaseDTO);
        }

        Method proxyMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
//        ApiOperation apiOperation = proxyMethod.getAnnotation(ApiOperation.class);
//        if (apiOperation == null) {
//            return;
//        }
        String methodName = proxyMethod.getName();
        if (!"lookup".equals(methodName)) {
            log.info("[" + proxyMethod.getDeclaringClass().getSimpleName() + "-" + methodName + "] Request {}", joinPoint.getArgs());
        }
        Field[] fields = request.getClass().getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            if (field.getGenericType().getTypeName().equals("java.lang.String")) {
                Method getField = request.getClass().getMethod("get" + name);
                Method setFiled = request.getClass().getMethod("set" + name, String.class);
                String value = (String) getField.invoke(request);
                if (value != null) {
                    setFiled.invoke(request, value.trim());
                }
            }
        }
    }

    @AfterReturning(returning = "returnObj", value = "apiItf()")
    public Object afterReturning(JoinPoint joinPoint, Object returnObj) {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        if (targetClass.isAssignableFrom(ShortLinkRequestController.class)) {
            return returnObj;
        }
        if (returnObj != null) {
            Method proxyMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
//            ApiOperation apiOperation = proxyMethod.getAnnotation(ApiOperation.class);
            log.info("[" + proxyMethod.getDeclaringClass().getSimpleName() + "-" + proxyMethod.getName() + "] Response {}", JacksonUtils.jsonObjectSerializer(returnObj));

            if (returnObj instanceof ResultData) {
                ResultData<?> resultData = (ResultData<?>) returnObj;
                Object retObj = resultData.getData();

                if (retObj instanceof ShortLinkBaseDTO) {
                    genSign((ShortLinkBaseDTO) retObj);
                }
            }

        }
        return returnObj;
    }

    private void checkSign(ShortLinkBaseDTO baseDTO) {
        String timeStamp = baseDTO.getTimeStamp();
        checkTimestamp(timeStamp);

        String sign = baseDTO.getSign();
        String commKey = dataService.getSysParam(SysParamEnum.SHORTLINK_INTERFACE_COMM_KEY);

        String signSrc = timeStamp + commKey;
        String genSign = SecureUtil.md5(signSrc);
        if (!sign.equalsIgnoreCase(genSign)) {
            log.warn("[ControllAspect] 请求签名: {}", sign);
            log.warn("[ControllAspect] 签名源串: {}", signSrc);
            log.warn("[ControllAspect] 生成签名: {}", genSign);
            throw new BusinessException("签名校验不通过");
        }
    }

    private void genSign(ShortLinkBaseDTO baseDTO) {
        String timeStamp = DateUtil.date().toString(DatePattern.PURE_DATETIME_PATTERN);
        String commKey = dataService.getSysParam(SysParamEnum.SHORTLINK_INTERFACE_COMM_KEY);

        String signSrc = timeStamp + commKey;
        String genSign = SecureUtil.md5(signSrc);

        log.info("[ControllAspect] 生成源串: {}", signSrc);
        log.info("[ControllAspect] 生成签名: {}", genSign);
        baseDTO.setTimeStamp(timeStamp);
        baseDTO.setSign(genSign);
    }

    private void checkTimestamp(String timestamp) {
        Date tradeTime;
        try {
            tradeTime = DateUtil.parse(timestamp, DatePattern.PURE_DATETIME_PATTERN);
        } catch (DateException e) {
            throw new BusinessException("时间戳参数格式不正确");
        }
        //计算两个时间差
        long diffSeconds = DateUtil.between(tradeTime, DateUtil.date(), DateUnit.SECOND);
        if (diffSeconds > 5*60) {
            //时间戳超过5分钟，此时请求失效
            throw new BusinessException("请求时间戳已超过5分钟");
        }
    }
}
