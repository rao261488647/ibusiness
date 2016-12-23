package com.report.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * DateUtil
 * 
 * @author jiangbo
 * 
 */
public class DateUtil {

  private static Logger log = Logger.getLogger(DateUtil.class);
  public static final String format = "yyyy-MM-dd";
  public static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
  public static final String formatTimeOnly = "HH:mm:ss";

  /**
   * string to date time
   * 
   * @param strDate
   * @return
   */
  public static java.util.Date toDateTime(String strDate) {
    return toDate(DATE_FORMAT_YMDHMS, strDate);
  }

  /**
   * string to date
   * 
   * @param format
   * @return
   */
  public static java.util.Date toDate(String pattern, String strDate) {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    try {
      return sdf.parse(strDate);
    } catch (ParseException e) {
      return null;
    }
  }

  /**
   * string to SQL date
   * 
   * @param format
   */
  public static java.sql.Date toSqlDate(String pattern, String strDate) {
    java.util.Date date = toDate(pattern, strDate);
    if (date != null) {
      return new java.sql.Date(date.getTime());
    } else {
      return null;
    }
  }

  /**
   * string to stamp date
   * 
   * @param pattern
   * @param strDate
   * @return
   */
  public static java.sql.Timestamp toStampDate(String pattern, String strDate) {
    java.util.Date date = toDate(pattern, strDate);
    if (date != null) {
      return new java.sql.Timestamp(date.getTime());
    } else {
      return null;
    }
  }

  /**
   * string to date
   * 
   * @param strDate
   * @return
   */
  public static java.util.Date toDate(String strDate) {
    return toDate(format, strDate);
  }

  /**
   * string to SQL date
   * 
   * @param strDate
   * @return
   */
  public static java.sql.Date toSqlDate(String strDate) {
    return toSqlDate(format, strDate);
  }

  /**
   * string to stamp date
   * 
   * @param strDate
   * @return
   */
  public static java.sql.Timestamp toStampDate(String strDate) {
    return toStampDate(format, strDate);
  }

  /**
   * date to string
   * 
   * @param date
   * @param format
   * @return
   */
  public static String toString(java.sql.Date date, String format) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    String reStr = "";
    if (date != null) {
      reStr = sdf.format(date);
    }
    return reStr;
  }

  /**
   * date to string
   * 
   * @param date
   * @return
   */
  public static String toString(java.sql.Date date) {
    return toString(date, format);
  }

  /**
   * date to string
   * 
   * @param date
   * @param format
   * @return
   */
  public static String toString(java.util.Date date, String format) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    String reStr = "";
    if (date != null) {
      reStr = sdf.format(date);
    }
    return reStr;
  }

  /**
   * date to string
   * 
   * @param date
   * @return
   */
  public static String toString(java.util.Date date) {
    return toString(date, format);
  }

  /**
   * 得到当前的日期时间
   * 
   * @return
   */
  public static java.util.Date getCurrentDate() {
    return new java.util.Date();
  }

  /**
   * 得到时间置零util.Date的日期时间
   * 
   * @return
   */
  public static java.util.Date getSimpleCurrentDate() {
    java.util.Date dd = getCurrentDate();
    return toDate(toString(dd));

  }

  /**
   * roll SQL date
   * 
   * @param date
   * @param year
   * @param month
   * @param day
   * @param hour
   * @param minute
   * @param second
   * @return
   */
  public static java.sql.Timestamp rollSqlDate(java.sql.Timestamp date, int year, int month,
      int day, int hour, int minute, int second) {
    java.util.Calendar calendar = java.util.Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(java.util.Calendar.YEAR, year);
    calendar.add(java.util.Calendar.MONTH, month);
    calendar.add(java.util.Calendar.DATE, day);
    calendar.add(java.util.Calendar.HOUR_OF_DAY, hour);
    calendar.add(java.util.Calendar.MINUTE, minute);
    calendar.add(java.util.Calendar.SECOND, second);
    return new java.sql.Timestamp(calendar.getTimeInMillis());
  }

  /**
   * 从指定日期的字符串中得到相应的日期 日期"XXXX-XX-XX"
   * 
   * @param dateTime
   * @return Timestamp
   */
  public static Timestamp getDateFromDateTime(String dateTime) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    try {
      return new java.sql.Timestamp(sdf.parse(dateTime).getTime());
    } catch (ParseException e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }

  /**
   * 判断是否是日期类型
   * 
   * @param value
   * @return
   */
  public static boolean isDate(String value) {
    String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
    Pattern p = Pattern.compile(eL);
    Matcher m = p.matcher(value);
    return m.matches();
  }

}
