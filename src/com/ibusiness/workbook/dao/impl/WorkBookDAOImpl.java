package com.ibusiness.workbook.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ibusiness.workbook.dao.base.WorkBookBaseDAO;
import com.ibusiness.workbook.model.WorkBook;

@Component(value="workBookDAOImpl")
public class WorkBookDAOImpl implements WorkBookBaseDAO {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Boolean addWorkBook(WorkBook workBook) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try{
			session.save(workBook);
			session.getTransaction().commit();
			return true;
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}
	}
	
	@Override
	public List<WorkBook> initWorkBook(String date,String userId) {
		Session session = sessionFactory.openSession();
		String hql = "select new WorkBook(content,beginTime) from WorkBook where userId='"+userId+"' and type='工作日志' and beginTime like '"+date+"%'";
		Query query = session.createQuery(hql);
		List<WorkBook> workBooks = query.list();
		return workBooks;
	}
	
	/**
	 * 工作日志/备忘录分页 
	 *
	 */
	@Override
	public WorkBook getWorkBookByDate(String date,String workBookNo,String userId,String type){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from WorkBook where userId='"+userId+"' and type='"+type+"' and beginTime like '"+date+"%'");
		query.setMaxResults(1);
		query.setFirstResult(Integer.parseInt(workBookNo));
		WorkBook workBook = (WorkBook)query.list().get(0);
		return workBook;
	}
	
	/**
	 * 获取某一用户的工作日志/备忘录的数量
	 */
	@Override
	public int getRecordNumber(String lowerDate,String upperDate,String userId,String type){
		Session session = sessionFactory.openSession();
		String hql = "from WorkBook where userId='"+userId+"' and type='"+type+"'";
		if(upperDate == null && lowerDate == null){
			hql = "from WorkBook where userId='"+userId+"' and type='"+type+"'";
		}else{
			if(lowerDate == null){
				hql = "from WorkBook where userId='"+userId+"' and type='"+type+"' and substring(beginTime,1,10)='"+upperDate+"'";
			}else if(upperDate == null){
				hql = "from WorkBook where userId='"+userId+"' and type='"+type+"' and substring(beginTime,1,10)='"+lowerDate+"'";
			}else{
				if("0".equals(lowerDate)){
					hql = "from WorkBook where userId='"+userId+"' and type='"+type+"' and substring(beginTime,0,10)<='"+upperDate+"'";
				}else if("0".equals(upperDate)){
					hql = "from WorkBook where userId='"+userId+"' and type='"+type+"' and substring(beginTime,0,10)>='"+lowerDate+"'";
				}else{
					hql = "from WorkBook where userId='"+userId+"' and type='"+type+"' and substring(beginTime,0,10)>='"+lowerDate+"' and substring(beginTime,0,10)<='"+upperDate+"'";
				}
			}
		}
		Query query = session.createQuery(hql);
		return query.list().size();
	}
	@Override
	public Boolean updateWorkBook(WorkBook workBook) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try{
			session.update(workBook);
			session.getTransaction().commit();
			return true;
		}catch(Exception e){
			session.getTransaction().rollback();
			return false;
		}finally{
			session.close();
		}
	}
	
	@Override
	public Boolean deleteWorkBook(WorkBook workBook) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try{
			session.delete(workBook);
			session.getTransaction().commit();
			return true;
		}catch(Exception e){
			session.getTransaction().rollback();
			return false;
		}finally{
			session.close();
		}
	}
	

}
