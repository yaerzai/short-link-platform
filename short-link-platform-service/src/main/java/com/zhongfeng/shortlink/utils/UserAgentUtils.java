package com.zhongfeng.shortlink.utils;

import com.zhongfeng.sms.persistence.enums.MobileOSEnum;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author yuzc
 * @date 2020/2/28 10:03
 */
public class UserAgentUtils {

    public static MobileOSEnum getOs(String userAgent) {
        if (Arrays.stream("iPad,iPhone,iOS".split(",")).anyMatch(item->userAgent.toLowerCase().contains(item.toLowerCase()))) {
            return MobileOSEnum.iOS;
        } else if (Arrays.stream("Android".split(",")).anyMatch(item->userAgent.toLowerCase().contains(item.toLowerCase()))) {
            return MobileOSEnum.Android;
        } else if (Arrays.stream("BlackBerry".split(",")).anyMatch(item->userAgent.toLowerCase().contains(item.toLowerCase()))) {
            return MobileOSEnum.BlackBerryOS;
        } else if (Arrays.stream("Fuchsia".split(",")).anyMatch(item->userAgent.toLowerCase().contains(item.toLowerCase()))) {
            return MobileOSEnum.FuchsiaOS;
        } else if (Arrays.stream("Harmony".split(",")).anyMatch(item->userAgent.toLowerCase().contains(item.toLowerCase()))) {
            return MobileOSEnum.HarmonyOS;
        } else if (Arrays.stream("Symbian".split(",")).anyMatch(item->userAgent.toLowerCase().contains(item.toLowerCase()))) {
            return MobileOSEnum.Symbian;
        } else if (Arrays.stream("WindowsPhone".split(",")).anyMatch(item->userAgent.toLowerCase().contains(item.toLowerCase()))) {
            return MobileOSEnum.WindowsPhone;
        }
        return MobileOSEnum.Unknown;
    }

    public static String getPlatform() {
        String agent = browser();
        //客户端类型常量
        String type;
        if (agent.contains("iPhone") || agent.contains("iPod") || agent.contains("iPad")) {
            type = "IOS";
        } else if (agent.contains("Android") || agent.contains("Linux")) {
            type = "Android";
        } else if (agent.indexOf("micromessenger") > 0) {
            type = "PC";
        } else {
            type = "PC";
        }
        return type;
    }

    public static String browser() {
        /**User Agent中文名为用户代理，简称 UA，它是一个特殊字符串头，使得服务器
         能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等*/
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return request.getHeader("user-agent");
    }

    public static String getServerName() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String serverPort = "";
        if (80 != request.getServerPort()) {
            serverPort = ":" + request.getServerPort();
        }
        return request.getServerName() + serverPort;
    }

}
