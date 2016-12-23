package com.ibusiness.workbook.service.base;

import com.ibusiness.workbook.model.WorkBook;

public interface WorkBookBaseService {

	public boolean addWorkBook(WorkBook workBook);
	
	public String initWorkBook(String date,String userId);
	
	public String getWorkBookByDate(String date,String workBookNo,String userId,String type);
	
	public boolean deleteWorkBookById(String WorkBookId);
	
	public boolean updateWorkBook(WorkBook workBook);
}
