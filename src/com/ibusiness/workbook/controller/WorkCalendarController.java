package com.ibusiness.workbook.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibusiness.workbook.DateUtil;


@Controller
@RequestMapping(value="/WorkCalendar")
public class WorkCalendarController {

	private DateUtil dateUtil;
	
	@RequestMapping(value="/getCalendar")
	public String getCalendar(String year,String method,HttpServletRequest request,HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			if("getYearMs".equals(method)){
				
				writer.write(dateUtil.getYearMs(year));
				
			}else if("getDayOfWeek".equals(method)){
				String weekOfDay = String.valueOf(dateUtil.getDayOfWeek(year));
				writer.write(weekOfDay);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.close();
		return null;
	}

	public DateUtil getDateUtil() {
		return dateUtil;
	}

	@Resource(name="dateUtil")
	public void setDateUtil(DateUtil dateUtil) {
		this.dateUtil = dateUtil;
	}
	
	
	
}
