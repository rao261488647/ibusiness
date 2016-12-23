package com.ibusiness.workbook.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ibusiness.workbook.DateUtil;
import com.ibusiness.workbook.dao.base.WorkBookBaseDAO;
import com.ibusiness.workbook.model.WorkBook;
import com.ibusiness.workbook.service.base.WorkBookBaseService;

@Component(value="workBookServiceImpl")
public class WorkBookServiceImpl implements WorkBookBaseService {

	private WorkBookBaseDAO workBookDAO;
	private DateUtil dateUtil;

	public WorkBookBaseDAO getWorkBookDAO() {
		return workBookDAO;
	}

	@Resource(name="workBookDAOImpl")
	public void setWorkBookDAO(WorkBookBaseDAO workBookDAO) {
		this.workBookDAO = workBookDAO;
	}
	
	public DateUtil getDateUtil() {
		return dateUtil;
	}

	@Resource(name="dateUtil")
	public void setDateUtil(DateUtil dateUtil) {
		this.dateUtil = dateUtil;
	}	

	@Override
	public boolean addWorkBook(WorkBook workBook) {
		if(workBook == null) return false;
		if(workBook.getEndTime()==null || "".equals(workBook.getEndTime()) || workBook.getEndTime().length()==0){
			workBook.setBeginTime(dateUtil.getFormatDateString(workBook.getBeginTime()+" 00:00"));
			workBook.setEndTime(workBook.getBeginTime());
		}else{
			workBook.setBeginTime(dateUtil.getFormatDateString(workBook.getBeginTime()));
			workBook.setEndTime(dateUtil.getFormatDateString(workBook.getEndTime()));
			if(workBook.getBeginTime().indexOf("error")>=0 || workBook.getEndTime().indexOf("error")>=0){
				return false;
			}
		}
		workBook.setUpdateTime(new Date());
		//去掉冒号和空格
		workBook.setContent(workBook.getContent().replace(":", "&#58;").replace(",", "&#44;").replace("'", "&sbquo;").replace("\\", "&#92;").replace("\"", "&#34;").replace(";", "&#59;").replace("\n", ""));
		boolean flag = workBookDAO.addWorkBook(workBook);
		if(flag == true){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean updateWorkBook(WorkBook workBook) {
		workBook.setBeginTime(dateUtil.getFormatDateString(workBook.getBeginTime()));
		workBook.setEndTime(dateUtil.getFormatDateString(workBook.getEndTime()));
		if(workBook.getBeginTime().indexOf("error")>=0 || workBook.getEndTime().indexOf("error")>=0){
			return false;
		}
		workBook.setUpdateTime(new Date());
		//去掉冒号和空格
		workBook.setContent(workBook.getContent().replace(":", "&#58;").replace(",", "&#44;").replace("'", "&sbquo;").replace("\\", "&#92;").replace("\"", "&#34;").replace(";", "&#59;").replace("\n", ""));
		boolean flag = workBookDAO.updateWorkBook(workBook);
		return flag;
	}
	
	/**
	 * 工作日志页面初始化数据
	 */
	@Override
	public String initWorkBook(String date,String userId){
		if(date == null || userId == null){
			return null;
		}
		int later = dateUtil.getDayOfWeek(date.substring(0, 7)+"-01");
		//定义返回的字符串
		String returnData = "[{firstWeek:'"+later+"',workBooks:[";
		String objTemp = "";
		String timeTemp = "";
		//到数据库中检索
		List<WorkBook> workBooks = workBookDAO.initWorkBook(date.substring(0, 7),userId);
		List<WorkBook> workBooksCopy = new ArrayList<WorkBook>();
		//去重,每天只留一条记录
		for(WorkBook workBook:workBooks){
			if(workBooksCopy.size() == 0){
				workBooksCopy.add(workBook);
			}else{
				for(int i=0;i<workBooksCopy.size();i++){
					if(workBook.getBeginTime().substring(0,10).equals(workBooksCopy.get(i).getBeginTime().substring(0, 10))){
						break;
					}else{
						if(i == workBooksCopy.size()-1){
							workBooksCopy.add(workBook);
						}else{
							continue;
						}
					}
				}
			}
		}
		//对去重后的结果进行处理:时间返回格式YYYY-MM-DD 内容：周一到周五返回80个长度，周六周日返回34个长度,拼接成JSON字符串
		for(WorkBook workBook:workBooksCopy){
			timeTemp = workBook.getBeginTime().substring(0, 10);
			objTemp = "{beginTime:'"+timeTemp+"',content:'";
			if(dateUtil.getDayOfWeek(timeTemp)>=6){
				if(workBook.getContent().length() > 34){
					objTemp += (workBook.getContent().substring(0, 34)+"...");
				}else{
					objTemp += workBook.getContent();
				}
			}else{
				if(workBook.getContent().length() > 80){
					objTemp += (workBook.getContent().substring(0, 80)+"...");
				}else{
					objTemp += workBook.getContent();
				}
			}
			int number = Integer.parseInt(timeTemp.split("-")[2])+later-1;
			objTemp = objTemp+ "',number:'"+number+"'},";
			returnData += objTemp;
		}
		returnData = returnData.substring(0, returnData.length()-1);
		returnData += "]}]";
		return returnData;
	}
	
	/**
	 * 获取用户某一天的一条工作日志
	 */
	@Override
	public String getWorkBookByDate(String date,String workBookNo,String userId,String type){
		String returnData = "[{";
		String countPageNumber = "";
		WorkBook workBook = null;
		//对date进行格式化
		date = dateUtil.getFormatDateString(date+" 00:00").split(" ")[0];
		//检索总条数
		if("-1".equals(workBookNo)){
			countPageNumber = String.valueOf(workBookDAO.getRecordNumber(null,date,userId,type));
			if("0".equals(countPageNumber)){
				returnData = returnData + "countPageNumber:'0'";
			}else{
				workBook = workBookDAO.getWorkBookByDate(date, "0", userId,type);
				returnData = returnData + "countPageNumber:'"+countPageNumber
						+"',currPage:'1',WorkBookId:'"+workBook.getSerialNo()
						+"',WorkBookDate:'"+workBook.getBeginTime().split(" ")[0]
						+"',WorkBookBeginTime:'"+workBook.getBeginTime().split(" ")[1]
						+"',WorkBookEndTime:'"+workBook.getEndTime().split(" ")[1]
						+"',WorkBookWeather:'"+workBook.getWeather()
						+"',WorkBookTitle:'"+workBook.getTitle()
						+"',WorkBookContent:'"+workBook.getContent()+"'";
			}			
		}else{
			workBook = workBookDAO.getWorkBookByDate(date, workBookNo, userId,type);
			returnData = returnData + "WorkBookId:'"+workBook.getSerialNo()
					+"',WorkBookDate:'"+workBook.getBeginTime().split(" ")[0]
					+"',WorkBookBeginTime:'"+workBook.getBeginTime().split(" ")[1]
					+"',WorkBookEndTime:'"+workBook.getEndTime().split(" ")[1]
					+"',WorkBookWeather:'"+workBook.getWeather()
					+"',WorkBookTitle:'"+workBook.getTitle()
					+"',WorkBookContent:'"+workBook.getContent()+"'";
		}
		returnData = returnData + "}]";
		System.out.println(returnData);
		return returnData;
	}
	
	@Override
	public boolean deleteWorkBookById(String WorkBookId){
		WorkBook workBook = new WorkBook();
		workBook.setSerialNo(WorkBookId);
		boolean flag = workBookDAO.deleteWorkBook(workBook);
		return flag;
	}
	
}
