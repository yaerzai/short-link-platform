package com.zhongfeng.shortlink.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yuzc
 * @Description: 10进制转换
 * @date 2020/2/23 16:53
 */
public class BaseTo62BaseUtils {
    // 62位字符
    private static final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    //
    private static final int scale = 62;
    /**
     * 将数字转为62进制
     *
     * @param num Long 型数字
     * @return 62进制字符串
     */
    public static String base62Encode(long num) {
//        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        int remainder;
//        int scale = 36;
        while (num > scale - 1) {
            remainder = Long.valueOf(num % scale).intValue();
            sb.append(chars.charAt(remainder));
            num = num / scale;
        }
        sb.append(chars.charAt(Long.valueOf(num).intValue()));
        String value = sb.reverse().toString();
        return StringUtils.leftPad(value, 6, '0');
    }

    /**
     * 36进制字符串转为数字
     *
     * @param str 编码后的36进制字符串
     * @return 解码后的 10 进制字符串
     */
    public static long base62Decode(String str) {
//        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        int scale = 36;
        str = str.replace("^0*", "");
        long num = 0;
        int index;
        for (int i = 0; i < str.length(); i++) {
            index = chars.indexOf(str.charAt(i));
            num += (long) (index * (Math.pow(scale, str.length() - i - 1)));
        }
        return num;
    }


}
