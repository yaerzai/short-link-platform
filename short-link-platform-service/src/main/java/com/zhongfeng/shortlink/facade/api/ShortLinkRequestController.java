package com.zhongfeng.shortlink.facade.api;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.zhongfeng.shortlink.service.ShortLinkService;
import com.zhongfeng.shortlink.service.bo.ShortLinkLookupBO;
import com.zhongfeng.shortlink.utils.IpUtils;
import com.zhongfeng.shortlink.utils.UserAgentUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author yuzc
 * created on 2020/2/23
 */
@Controller
@Api(tags = "短链跳转")
@Slf4j
public class ShortLinkRequestController {
    @Autowired
    private ShortLinkService shortLinkService;

    private static final String WX_UA = "micromessenger";


    @GetMapping("/")
    public String view() {
        return "index";
    }

    @GetMapping("/{key}")
    public void lookup(HttpServletRequest request, HttpServletResponse response, @PathVariable String key, @RequestHeader("User-Agent") String userAgent) throws IOException {
        if ("robots.txt".equals(key)) {
            try {
                Writer writer = response.getWriter();
                String lineSeparator = System.getProperty("line.separator", "\n");
                writer.append("User-agent: *").append(lineSeparator);
                writer.append("Disallow: /").append(lineSeparator);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long startTime = System.currentTimeMillis();
        //测试日志
        Cookie[] cookies = request.getCookies();
        String pvDate = null;
        if (cookies != null) {
            Cookie cookie = Arrays.stream(cookies).filter(item -> item.getName().equals(key)).findAny().orElse(null);
            if (cookie != null) {
                pvDate = cookie.getValue();
            }
        }
//        String ipAddr = IpUtil.getIpAddr();
        String ipAddr = IpUtils.getIpAddress(request, key);

        //对浏览器做过滤
//        if(!shortLinkService.isPass(ipAddr,userAgent)){
//            log.info("[ShortLinkRequest]{}-{} UA中包含Windows,请求无效UA:{}",key,ipAddr,userAgent);
//            response.setCharacterEncoding("utf-8");
//            response.getOutputStream().write("无效地址".getBytes("gb2312"));
//            response.setCharacterEncoding("gb2312");
//            return;
//        }
        String ua = UserAgentUtils.browser().toLowerCase();
        log.info("[ShortLinkRequest]{}-{} 开始请求短链 {}", key, ipAddr, ua);
        if (ua.contains(WX_UA)) {
            log.info("[ShortLinkRequest] 微信打开拦截处理：ip:{},UA:{},shortLinkNo：{}", ipAddr, userAgent, key);
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            response.getOutputStream().write(warnHtml().getBytes(StandardCharsets.UTF_8));
            response.setCharacterEncoding("UTF-8");
            return;
        }
        ShortLinkLookupBO linkLookupBO = ShortLinkLookupBO.builder().linkNo(key).requestIp(ipAddr)
                .requestServerName(UserAgentUtils.getServerName()).userAgent(userAgent).pvDate(pvDate).build();
        String originalUrl = shortLinkService.lookup(linkLookupBO);
        log.info("[ShortLinkRequest]{}-{} originalUrl:{}", key, ipAddr, originalUrl);
        long spaceTime = System.currentTimeMillis() - startTime;
        log.debug("[ShortLinkRequestController] 短链：{},IP：{},lookup耗时: {}", key, ipAddr, spaceTime);
        if (StringUtils.isBlank(originalUrl)) {
            log.info("[ShortLinkRequest] 本次请求原始地址为空：ip:{},UA:{},shortLinkNo：{}", ipAddr, userAgent, key);
            response.setCharacterEncoding("utf-8");
            response.getOutputStream().write("无效地址".getBytes("gb2312"));
            response.setCharacterEncoding("gb2312");
            return;
        }
        String currPvDate = linkLookupBO.getPvDate();
        if (currPvDate != null && !currPvDate.equals(pvDate)) {
            Cookie cookie = new Cookie(key, currPvDate);
            DateTime endDate = DateUtil.date();
            endDate.setField(DateField.HOUR_OF_DAY, 23);
            endDate.setField(DateField.MINUTE, 59);
            endDate.setField(DateField.SECOND, 59);
            long between = DateUtil.between(DateUtil.date(), endDate, DateUnit.SECOND);
            cookie.setMaxAge((int) between);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        // 设置302状态码并重定向

        try {
            response.setStatus(HttpServletResponse.SC_FOUND);
//            response.sendRedirect(originalUrl);
            response.setHeader("Location", originalUrl);
            response.addHeader("X-Forwarded-For", ipAddr);
            response.getOutputStream().flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private String warnHtml() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <title>智能短链</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div style=\"text-align: center;\">\n" +
                "    <div style=\"margin-top:5rem;font-weight: 700;font-size: 1.2rem;\">智能短链,大数据运营一手掌握!</div>\n" +
                "    <div style=\"padding: 0px 2rem;margin:10rem 0 1rem 0;height:2px;background-color: #adadad\"></div>\n" +
                "    <div style=\"font-size: small;\">Copyright 2022 粤ICP备2022096508号</div>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>\n" +
                "\n" +
                "<style>\n" +
                "\n" +
                "</style>";
    }
}
