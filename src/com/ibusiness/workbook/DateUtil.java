package com.ibusiness.workbook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component(value="dateUtil")
public class DateUtil {

	//private String[] WeekNames = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" , "星期日" };
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:sss");
	private Date date;
	
	//返回给定日期的星期序号（星期一到星期日对应1到7）
	public int getDayOfWeek(String dateTime) {
		if("null-undefined-01".equals(dateTime)){
			dateTime = simpleDateFormat.format(getCurrDate()).substring(0,8)+"01";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(dateTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int dayForWeek = 0;
		if(c.get(Calendar.DAY_OF_WEEK) == 1){
		   dayForWeek = 7;
		}else{
		   dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}
	
	//通过给定的年份获取当年每月一号的星期序号
	public String getYearMs(String year){
		String yearMs = "";
		String moth = null;
		String day = "01";
		for(int i=1 ; i<=12 ; i++) {
			if(i<10) {
				moth = "0" + String.valueOf(i);
			}else {
				moth = String.valueOf(i);
			}
			yearMs += year+"-"+moth+"-"+day+"-"+String.valueOf(getDayOfWeek(year+"-"+moth+"-"+day));
			if(i != 12) {
				yearMs += "/";
			}
		}
		return yearMs;
	}
	//打印方法
	public static void print(Object a) {
		System.out.println(a);
	};
		
	public Date parseDateString(String dateString){
		try {
			 this.date = this.simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("===========字符串转换日期失败==========");
		}
		return this.date;
	}
	
	public Date ParseDateString(String date,String time){
		return parseDateString(date+" "+time+":"+String.valueOf(new Date().getSeconds()));
	}
	
	public Date getCurrDate(){
		String strFormatDate = this.simpleDateFormat.format(new Date());
		try {
			 this.date = this.simpleDateFormat.parse(strFormatDate);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("===========获取当前日期异常==========");
		}
		return this.date;
	}
	
	/**
	 *	将日期中10以内的月份和日期变成前面加0的2位格式 
	 */
	public String getFormatDateString(String timeString){
		if(timeString.indexOf(" ") >= 0){
			String[] dateStringBuffer = timeString.split(" ")[0].split("-");
			String time = timeString.split(" ")[1];
			if(dateStringBuffer[1].length() == 1){
				dateStringBuffer[1] = "0" + dateStringBuffer[1];
			}
			if(dateStringBuffer[2].length() == 1){
				dateStringBuffer[2] = "0" + dateStringBuffer[2];
			}
			return dateStringBuffer[0]+"-"+dateStringBuffer[1]+"-"+dateStringBuffer[2]+" "+time;
		}else{
			return "error:不是标准的日期格式，yyyy-MM-dd hh:mm";
		}
		
	}
}
