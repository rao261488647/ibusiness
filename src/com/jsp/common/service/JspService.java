package com.jsp.common.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.core.hibernate.HibernateBasicDao;

@Service
@Transactional
public class JspService extends HibernateBasicDao {

	
	public int deleteById(String sql, String id){
//		String sql = "delete from " + tableName + " where id = ?";
    	int x = this.getJdbcTemplate().update(sql, new Object[]{id});
    	return x;
	}
	
	public int updateBySql(String sql){
		
		int x = this.getJdbcTemplate().update(sql);  
		
		return x;
	}
	
	public void saveByName(String sql) {
		
    	this.getJdbcTemplate().update(sql);
	}
	
}
