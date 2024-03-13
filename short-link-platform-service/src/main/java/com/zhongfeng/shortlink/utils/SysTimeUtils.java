package com.zhongfeng.shortlink.utils;

import cn.hutool.core.util.StrUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SysTimeUtils {

    /**
     * 耗时显示
     *
     * @param startTime
     * @return
     */
    public static String showTimeNow(long startTime) {
        long endTime = System.currentTimeMillis();
        return showTime(endTime - startTime);
    }

    /**
     * 耗时显示
     * @param spaceTime
     * @return
     */
    public static String showTime(long spaceTime) {
        StringBuilder builder = StrUtil.builder();
        long hour = spaceTime / 3600_000;
        long minute = (spaceTime / 60_000) % 60;
        long second = (spaceTime / 1000) % 60;
        long ms = spaceTime % 1000;
        if (hour > 0) {
            builder.append(hour).append("时");
        }
        if (minute > 0 || (minute == 0 && hour > 0)) {
            if (hour > 0 && minute < 10) {
                builder.append("0");
            }
            builder.append(minute).append("分");
        }
        builder.append(second);
        if (spaceTime/1000 == 0) {
            builder.append(".").append(String.format("%03d",ms));
        }
        builder.append("秒");
        return builder.toString();
    }

    /**
     * 是否是整数
     * @param str
     * @param isPositive true,判断是否是正整数
     * @return
     */
    public static boolean isInteger(String str,boolean isPositive) {
        if (str == null || "".equals(str)){
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        if (!isPositive)
        return pattern.matcher(str).matches();

        if(pattern.matcher(str).matches()){
            return Integer.parseInt(str) >0;
        }
        return pattern.matcher(str).matches();
    }

    /**
     * 求出当前时间距离今日结束还有多少秒
     * @param currentDate
     * @return
     */
    public static Integer getRemainSecondsOneDay(Date currentDate) {
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),ZoneId.systemDefault());long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return (int) seconds;
    }


    public static void main(String[] args) {
        System.out.println(isInteger("-4",true));

        System.out.println(isInteger("1.98",true));
        System.out.println(getRemainSecondsOneDay(new Date()));

    }
}
