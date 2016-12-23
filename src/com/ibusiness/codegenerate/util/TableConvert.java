package com.ibusiness.codegenerate.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 表转换器
 * 
 * @author JiangBo
 * 
 */
public class TableConvert {
    public static String getNullAble(String paramString) {
        if (("YES".equals(paramString)) || ("yes".equals(paramString))
                || ("y".equals(paramString)) || ("Y".equals(paramString)))
            return "Y";
        if (("NO".equals(paramString)) || ("N".equals(paramString))
                || ("no".equals(paramString)) || ("n".equals(paramString)))
            return "N";
        return null;
    }

    public static String getNullString(String paramString) {
        if (StringUtils.isBlank(paramString))
            return "";
        return paramString;
    }

    public static String getV(String paramString) {
        return "'" + paramString + "'";
    }
}
