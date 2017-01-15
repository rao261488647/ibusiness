package com.report.utils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateUtils;
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
	public static java.sql.Timestamp rollSqlDate(java.sql.Timestamp date,
			int year, int month, int day, int hour, int minute, int second) {
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

	public static void main(String[] args) {
		printWeekdays();
		convertWeekByDate(toDate("2016-09-01"));
		getWeekOfMonth("2016-07-31");
		System.out.println("上周一："+getLastWeekMonday("2016-08-07"));
		System.out.println("上周日："+getLastWeekSunday("2016-08-07"));
		System.out.println(numFormat(1026,10));
		System.out.println(getYesterDay());
	}

	
	/** 获取上周一
	 * @param date
	 * @return
	 */
	public static String getLastWeekMonday(String date) {
		return getLastWeekMonday(toDate(date));
	}
	/** 获取上周一
	 * @param date
	 * @return
	 */
	public static String getLastWeekMonday(Date date) {
		Date a = DateUtils.addDays(date, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		cal.add(Calendar.WEEK_OF_YEAR, -1);// 一周
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		return toString(cal.getTime(), format);
	}

	/** 获取上周日
	 * @param date
	 * @return
	 */
	public static String getLastWeekSunday(String date) {
		return getLastWeekSunday(toDate(date));
	}
	
	/** 获取上周日
	 * @param date
	 * @return
	 */
	public static String getLastWeekSunday(Date date) {
		Date a = DateUtils.addDays(date, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		cal.set(Calendar.DAY_OF_WEEK, 1);

		return toString(cal.getTime(), format);
	}

	private static final int FIRST_DAY = Calendar.MONDAY;

	private static void printWeekdays() {
		Calendar calendar = Calendar.getInstance();
		while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
			calendar.add(Calendar.DATE, -1);
		}
		for (int i = 0; i < 7; i++) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(dateFormat.format(calendar.getTime()));
			calendar.add(Calendar.DATE, 1);
		}
	}

	public static void convertWeekByDate(String time) {
		convertWeekByDate(toDate(time));
	}

	/**
	 * 根据指定日期计算所在周的周一和周日
	 * 
	 * @param time
	 */
	public static void convertWeekByDate(Date time) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		String imptimeBegin = sdf.format(cal.getTime());
		System.out.println("所在周星期一的日期：" + imptimeBegin);
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		System.out.println("所在周星期日的日期：" + imptimeEnd);
	}

	/**
	 * 根据指定日期计算所在周的周一
	 * 
	 * @param time
	 */
	public static String convertWeekByMonday(String time) {
		return convertWeekByMonday(toDate(time));
	}

	/**
	 * 根据指定日期计算所在周的周一
	 * 
	 * @param time
	 */
	public static String convertWeekByMonday(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		String imptimeBegin = sdf.format(cal.getTime());
		System.out.println("所在周星期一的日期：" + imptimeBegin);
		return imptimeBegin;
	}

	/**
	 * 根据指定日期计算所在周的周日
	 * 
	 * @param time
	 */
	public static String convertWeekBySunday(String time) {
		return convertWeekBySunday(toDate(time));
	}

	/**
	 * 根据指定日期计算所在周的周日
	 * 
	 * @param time
	 */
	public static String convertWeekBySunday(Date time) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		System.out.println("所在周星期日的日期：" + imptimeEnd);
		return imptimeEnd;
	}

	/**
	 * 根据一个日期获取当月第几周
	 * 
	 * @param dateString
	 * @return
	 */
	public static int getWeekOfMonth(String dateString) {
		Date date = toDate(dateString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);// 设置星期一为一周的第一天。
		int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
		System.out.println(weekOfMonth);
		return weekOfMonth;
	}
	
	/** 
	 * @param b 被除数
	 * @param a 除数
	 * @return
	 */
	public static String numFormat (Object b,Object a) {
		Integer a1 = Integer.valueOf(a.toString())*100;
		Integer b1 = Integer.valueOf(b.toString());
		if (b1 == 0) {
			return "0";
		}
		double num= (double)a1/b1;
		DecimalFormat df = new DecimalFormat("0.00");//格式化小数  
		String s = df.format(num);//返回的是String类型
		return s;
	}
	
	/** 获取昨天的日期
	 * @return
	 */
	public static String getYesterDay () {
		Calendar cal =   Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat(format).format(cal.getTime());
		return yesterday;
	}
	

}
