package com.zhongfeng.shortlink.advice;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.zhongfeng.common.base.exception.BusinessException;
import com.zhongfeng.common.swagger.model.ResultData;
import com.zhongfeng.shortlink.enums.BizErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 统一异常处理
 * resfful
 *
 * @author xianyongjie
 */
@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultData exceptionHandler(Exception ex, HttpServletResponse response) throws Exception {
        ResultData rs = new ResultData();
        rs.setMsg("系统繁忙，请稍后再试！");
        if (ex instanceof BusinessException) {
            BusinessException be = (BusinessException) ex;
            rs.setCode(be.getCode());
            rs.setMsg(be.getMessage());
            log.error("系统业务错误:{}", be.getMessage());
            return rs;
        } else if (ex instanceof BindException) {
            BindException be = (BindException) ex;
            List<FieldError> fieldErrors = be.getBindingResult().getFieldErrors();
            String fieldName = "";
            for (FieldError fieldError : fieldErrors) {
                fieldName += fieldError.getField();
            }
            rs.setCode(BizErrorCodeEnum.REQUEST_PARAM_ILLEGAL.getCode());
            rs.setMsg(fieldName + BizErrorCodeEnum.REQUEST_PARAM_ILLEGAL.getMsg());
            log.error("系统错误", ex);
            return rs;
        } else if (ex instanceof HttpMessageNotReadableException) {
            HttpMessageNotReadableException hme = (HttpMessageNotReadableException) ex;
            String fieldName = "";
            if (hme.getCause() instanceof JsonMappingException) {
                JsonMappingException jme = (JsonMappingException) hme.getCause();
                List<JsonMappingException.Reference> path = jme.getPath();
                if (path != null && path.size() > 0) {
                    fieldName = path.get(0).getFieldName();
                }
            }
            rs.setCode(BizErrorCodeEnum.REQUEST_PARAM_ILLEGAL.getCode());
            rs.setMsg(fieldName + BizErrorCodeEnum.REQUEST_PARAM_ILLEGAL.getMsg());
            log.error("系统错误", ex);
            return rs;
        } else if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException man = (MethodArgumentNotValidException) ex;
            String fieldName = man.getBindingResult().getFieldError().getField();
            String msg = man.getBindingResult().getFieldError().getDefaultMessage();
            rs.setCode(BizErrorCodeEnum.REQUEST_PARAM_ILLEGAL.getCode());
            rs.setMsg(BizErrorCodeEnum.REQUEST_PARAM_ILLEGAL.getMsg() + ",[" + fieldName + "]" + msg);
            log.error("请求参数非法 {}", fieldName);
        } else {
            throw ex;
        }
        return rs;
    }
}
