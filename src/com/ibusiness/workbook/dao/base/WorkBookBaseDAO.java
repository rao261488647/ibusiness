package com.ibusiness.workbook.dao.base;

import java.util.List;

import com.ibusiness.workbook.model.WorkBook;

public interface WorkBookBaseDAO {

	public Boolean addWorkBook(WorkBook workBook);
	
	public List<WorkBook> initWorkBook(String date,String userId);
	
	public WorkBook getWorkBookByDate(String date,String workBookNo,String userId,String type);
	
	public Boolean updateWorkBook(WorkBook workBook);
	
	public Boolean deleteWorkBook(WorkBook workBook);
	
	public int getRecordNumber(String lowerDate,String upperDate,String userId,String type);
	
}
