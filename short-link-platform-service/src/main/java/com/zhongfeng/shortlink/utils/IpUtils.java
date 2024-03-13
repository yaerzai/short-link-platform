package com.zhongfeng.shortlink.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @author kf-zhanghui
 * @date 2023/4/25 20:08
 *
 * IP获取工具类
 */
@Slf4j
public class IpUtils {


    /**
     * 客户获取Ip的方式
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request,String shortLinkNo) {

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headName = headerNames.nextElement();
            log.info("[ShortLinkRequest]{} 请求头信息：{} ->{}",shortLinkNo,headName,request.getHeader(headName));
        }
        String ip = request.getHeader("ali-cdn-real-ip");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-forwarded-for");
            if(StrUtil.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)){
                log.info("[ShortLinkRequest]{} 通过 X-forwarded-for 获取的Ip:{}",shortLinkNo,ip);
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-client-Ip");
            if(StrUtil.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)){
                log.info("[ShortLinkRequest]{} 通过 Proxy-client-Ip 获取的Ip:{}",shortLinkNo,ip);
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-CLient-Ip");
            if(StrUtil.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)){
                log.info("[ShortLinkRequest]{} 通过 WL-Proxy-CLient-Ip 获取的Ip:{}",shortLinkNo,ip);
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            if(StrUtil.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)){
                log.info("[ShortLinkRequest]{} 通过 HTTP_CLIENT_IP 获取的Ip:{}",shortLinkNo,ip);
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            if(StrUtil.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)){
                log.info("[ShortLinkRequest]{} 通过 HTTP_X_FORWARDED_FOR 获取的Ip:{}",shortLinkNo,ip);
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(StrUtil.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)){
                log.info("[ShortLinkRequest]{} 通过 request.getRemoteAddr() 获取的Ip:{}",shortLinkNo,ip);
            }
            //根据网卡取本机配置的IP
            if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        if (ip.contains(",")) {
            ip = ip.substring(0, ip.indexOf(","));
        }
        return ip;
    }
}
