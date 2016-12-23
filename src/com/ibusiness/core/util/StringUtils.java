package com.ibusiness.core.util;

import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 字符串相关方法工具类
 * 
 * @author JiangBo
 * 
 */
public class StringUtils {
    protected StringUtils() {
    }

    /**
     * 判断某字符串是否为空,为空的标准是 text==null 或 text.length()==0 
     * 
     * @param text
     * @return
     */
    public static boolean isEmpty(String text) {
        return org.apache.commons.lang3.StringUtils.isEmpty(text);
    }

    /**
     * 断某字符串是否为空或长度为0或由空格符(whitespace) 构成
     * 
     * @param text
     * @return
     */
    public static boolean isBlank(String text) {
        return org.apache.commons.lang3.StringUtils.isBlank(text);
    }

    public static boolean isNotBlank(String text) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(text);
    }

    /**
     * 首字母大写
     * @param text
     * @return
     */
    public static String capitalize(String text) {
        return org.apache.commons.lang3.StringUtils.capitalize(text);
    }

    public static String substring(String text, int offset, int limit) {
        return org.apache.commons.lang3.StringUtils.substring(text, offset, limit);
    }
    /**
     * 截取到等于第二个参数的字符串为止
     * @param text
     * @param token
     * @return
     */
    public static String substringBefore(String text, String token) {
        return org.apache.commons.lang3.StringUtils.substringBefore(text, token);
    }

    /**
     * 从左往右查到相等的字符开始，保留后边的，不包含等于的字符
     * @param text
     * @param token
     * @return
     */
    public static String substringAfter(String text, String token) {
        return org.apache.commons.lang3.StringUtils.substringAfter(text, token);
    }

    /**
     * 未发现不同的地方,指定字符分割成数组
     * @param text
     * @param separator
     * @return
     */
    public static String[] splitByWholeSeparator(String text, String separator) {
        return org.apache.commons.lang3.StringUtils.splitByWholeSeparator(text, separator);
    }

    public static String join(List list, String separator) {
        return org.apache.commons.lang3.StringUtils.join(list, separator);
    }

    public static String escapeHtml(String text) {
        return StringEscapeUtils.escapeHtml4(text);
    }

    public static String unescapeHtml(String text) {
        return StringEscapeUtils.unescapeHtml4(text);
    }

    public static String escapeXml(String text) {
        return StringEscapeUtils.escapeXml11(text);
    }

    public static String unescapeXml(String text) {
        return StringEscapeUtils.unescapeXml(text);
    }
}
