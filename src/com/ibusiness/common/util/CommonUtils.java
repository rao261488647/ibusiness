package com.ibusiness.common.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 共用方法类
 * 
 * @author JiangBo
 *
 */
public class CommonUtils {

    /**
     * 单例模式
     */
    private static CommonUtils instance = new CommonUtils();
    private CommonUtils() {}
    public static CommonUtils getInstance() {
        return instance;
    }
    
    /**
     * 字符串,日期类型转换用变量
     */
    private SimpleDateFormat ymdhms = new SimpleDateFormat(Constants.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
    private SimpleDateFormat ymd = new SimpleDateFormat(Constants.DATE_FORMAT_YYYY_MM_DD);
    private SimpleDateFormat ym = new SimpleDateFormat(Constants.DATE_FORMAT_YYYY_MM);
    /**
     * Log4j
     */
    private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);
    /**
     * 取得CG代码生成公式计算信息
     * @return
     */
    public String getFormulaByCGInfo(String formulaType) {
        // 系统当前时间
        if ("currentDate".equals(formulaType)) {
            return getCurrentDateTime();
        }
        return "";
    }
    
	/**
	 * 获取当前系统时间
	 * @return
	 */
    public String getCurrentDateTime() {
        return ymdhms.format(new Date());
    }
    /**
     * 判断日期之间的天数
     * 
     * @return
     */
    @SuppressWarnings("deprecation")
    public boolean isDaysBetween(String startTime, String endTime, int number) {
        try {
            Date startDate = ymdhms.parse(startTime.replace("T", " "));
            startDate.setDate(startDate.getDate() + number);
            if (startDate.after(ymdhms.parse(endTime.replace("T", " ")))) {
                return true;
            }
        } catch (ParseException e) {
            logger.error("==========================导出Excel方法 ParseException:" + e.toString());
        }
        return false;
    }
    
    /**
     * 判断字符串是否为bull
     * @param str
     * @return true 为空 false 不为空
     */
    public static boolean isNull(String str) {
        return (null == str || "".equals(str) || "null".equals(str)) ? true : false;
    }
    
    /**
     * Double格式化,返回一位小数
     * @param temp
     * @return
     */
    public static String formatDoubleBy1Decimal(double temp) {
        return new DecimalFormat("0.0").format(temp);
    }
    /**
     * Double格式化,返回2位小数
     * @param temp
     * @return
     */
    public static String formatStringBy2Decimal(double temp) {
        return new DecimalFormat("0.00").format(temp);
    }
    /**
     * Double格式化,返回两位小数
     * @param temp
     * @return
     */
    public static Double formatDoubleBy2Decimal(Double temp) {
    	if (null == temp) {
    		return 0.0;
    	} else {
    		return Double.parseDouble(new DecimalFormat("0.00").format(temp));
    	}
    }
    /**
     * 链接JSON字符串，是否加逗号分隔
     * @return
     */
    public static String appendJsonString(String strOne, String strTwo, boolean flag) {
        if (flag) {
            strOne = strOne + ",";
        }
        strOne = strOne + strTwo;
        return strOne;
    }
    /**
     * list转换JSON字符串
     * @param beanList
     * @param datePattern
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static JSONArray getJsonFromList(List beanList, String datePattern) {
        if (datePattern == null || "".equals(datePattern)) {
            datePattern = Constants.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS;
        }
        JsonConfig jsonConfig = configJson(null, datePattern);
        JSONArray array = JSONArray.fromObject(beanList, jsonConfig);
        return array;
    }
    /**
     * Bean转换JSON字符串
     * @param beanList
     * @param datePattern
     * @return
     */
    public static JSONObject getJsonFromBean(Object bean, String datePattern) {
        if (datePattern == null || "".equals(datePattern)) {
            datePattern = Constants.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS;
        }
        JsonConfig jsonConfig = configJson(null, datePattern);
        JSONObject jsonObject = JSONObject.fromObject(bean, jsonConfig);
        return jsonObject;
    }
    /**
     * JSON字符串转换List对象
     * @param beanList
     * @param datePattern
     * @return
     */
    @SuppressWarnings({"deprecation", "rawtypes"})
    public static Object getListFromJson(String jsonStr, Class clazz) {
        return JSONArray.toList(JSONArray.fromObject(jsonStr), clazz);
    }
    /**
     * JSON字符串转换List对象
     * @param beanList
     * @param datePattern
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Object getBeanFromJson(String jsonStr, Class clazz) {
        return JSONObject.toBean(JSONObject.fromObject(jsonStr), clazz);
    }
    /**
     * 创建JSON配置对象
     * @param excludes
     * @param datePattern
     * @return
     */
    private static JsonConfig configJson(String[] excludes, String datePattern) {
        JsonConfig jsonConfig = new JsonConfig();
        if (excludes != null) {
            jsonConfig.setExcludes(excludes);
        }
        jsonConfig.setIgnoreDefaultExcludes(false);
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(datePattern));
        return jsonConfig;
    }
    /**
     * 字符串转换大写
     */
    public static String toUpperCase(String str) {
        if (isNull(str)) {
            return "";
        } else {
            return str.toUpperCase();
        }
    }
    /**
     * 字符串转换小写
     */
    public static String toLowerCase(String str) {
        if (isNull(str)) {
            return "";
        } else {
            return str.toLowerCase();
        }
    }
    
    /**
     * @return the ymdhms
     */
    public SimpleDateFormat getYmdhms() {
        return ymdhms;
    }
    /**
     * @param ymdhms the ymdhms to set
     */
    public void setYmdhms(SimpleDateFormat ymdhms) {
        this.ymdhms = ymdhms;
    }
	public SimpleDateFormat getYmd() {
		return ymd;
	}
	public void setYmd(SimpleDateFormat ymd) {
		this.ymd = ymd;
	}
	public SimpleDateFormat getYm() {
		return ym;
	}
	public void setYm(SimpleDateFormat ym) {
		this.ym = ym;
	}
	/**
	 * 时间格式化
	 */
	public static String formatTime(String format, Object v) {
        if (v == null)
            return null;
        if (v.equals(""))
            return "";
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(v);
    }
	/**
	 * list有切只有一个对象
	 */
	public static boolean isOneList(List<Map<String, Object>> m){
    	if(m != null && m.size() == 1 && m.get(0) != null){
    		return true;
    	}else {
			return false;
		}
    }
	/**
     * 时间比较，1正常，0相反
     */
	public static String greaterThan(String small, String big) {
		if (small.compareTo(big) <= 0) {
			return "1";
		} else {
			return "0";
		}
	}
	/**
	 * 
	 */
	public static String null2String(Object s) {
        return s == null ? "" : s.toString().trim();
    }
}
