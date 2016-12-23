package com.ibusiness.common.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.orm.hibernate3.SessionFactoryUtils;


/** 
 * 基础DAO
 * @author 马明君
 * @version 1.0
 */
public class BaseDAO implements DAO{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#findAll(java.lang.Class)
	 */
	public List findAll(Class entityClass) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from "+entityClass.getName();
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#findAll(java.lang.Class, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List findAll(Class entityClass,Map<String,String> order) {
		Criteria cri = sessionFactory.getCurrentSession().createCriteria(entityClass);
		if(order != null){
			Set<String> keys =order.keySet();
			Iterator<String> it = keys.iterator();
			String key = null;
			while(it.hasNext()){
				key = it.next();
				if(key.equals("asc")){
					cri.addOrder(Order.asc(order.get(key)));
				} else if(key.equals("desc")){
					cri.addOrder(Order.desc(order.get(key)));
				}
			}
		}
		return cri.list();
	}
	
	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#findALL(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findALL(Class entityClass) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from "+entityClass.getName();
		Query query = session.createQuery(hql);
		return (List<T>)query.list();
	//	return (List<T>) super.getHibernateTemplate().loadAll(entityClass);
	}
	
	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#findALL(java.lang.Class, int, int)
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findALL(Class entityClass, int first, int max) {
		return (List<T>) sessionFactory.getCurrentSession().createCriteria(entityClass)
				.setFirstResult(first).setMaxResults(max).list();
	}
	
	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#findALL(java.lang.Class, int, int, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findALL(Class entityClass, int first, int max,Map<String,String> order) {
		 Criteria cri = sessionFactory.getCurrentSession().createCriteria(entityClass)
			.setFirstResult(first).setMaxResults(max);
		 if(order != null){
				Set<String> keys =order.keySet();
				Iterator<String> it = keys.iterator();
				String key = null;
				while(it.hasNext()){
					key = it.next();
					if(key.equals("asc")){
						cri.addOrder(Order.asc(order.get(key)));
					} else if(key.equals("desc")){
						cri.addOrder(Order.desc(order.get(key)));
					}
				}
			}
		 return (List<T>)cri.list();
	}

	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#findById(java.lang.Class, java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	public <T> T findById(Class entityClass, Serializable id) {
		return (T) sessionFactory.getCurrentSession().load(entityClass, id);

	}

	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#findByProperty(java.lang.Class, java.lang.String[], java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByProperty(Class entityClass, String[] paramName,
			Object[] value) {
		Criteria cri = sessionFactory.getCurrentSession().createCriteria(entityClass);
		for (int i = 0; i < paramName.length; i++) {
			cri.add(Restrictions.eq(paramName[i], value[i]));
		}
		return (List<T>) cri.list();
	}
	public  <T> List<T> findByProperty1(Class entityClass,
			String[] paramName, Object[] value){
		Criteria cri = sessionFactory.getCurrentSession().createCriteria(entityClass);
		for (int i = 0; i < paramName.length; i++) {
			cri.add(Restrictions.ne(paramName[i], value[i]));
		}
		return (List<T>) cri.list();
		
	}
	
	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#findByProperty(java.lang.Class, java.lang.String[], java.lang.Object[], java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByProperty(Class entityClass, String[] paramName,
			Object[] value,Map<String,String> order) {
		Criteria cri = sessionFactory.getCurrentSession().createCriteria(entityClass);
		for (int i = 0; i < paramName.length; i++) {
			cri.add(Restrictions.eq(paramName[i], value[i]));
		}
		if(order != null){
			Set<String> keys =order.keySet();
			Iterator<String> it = keys.iterator();
			String key = null;
			while(it.hasNext()){
				key = it.next();
				if(key.equals("asc")){
					cri.addOrder(Order.asc(order.get(key)));
				} else if(key.equals("desc")){
					cri.addOrder(Order.desc(order.get(key)));
				}
			}
		}
		return (List<T>) cri.list();
	}

	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#findByProperty(java.lang.Class, java.lang.String[], java.lang.Object[], int, int)
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByProperty(Class entityClass, String[] paramName,
			Object[] value, int first, int max) {
		Criteria cri = sessionFactory.getCurrentSession().createCriteria(entityClass);
		for (int i = 0; i < paramName.length; i++) {
			cri.add(Restrictions.eq(paramName[i], value[i]));
		}
		cri.setFirstResult(first);
		cri.setMaxResults(max);
		return (List<T>) cri.list();
	}
	
	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#findByProperty(java.lang.Class, java.lang.String[], java.lang.Object[], int, int, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByProperty(Class entityClass, String[] paramName,
			Object[] value, int first, int max,Map<String,String> order) {
		Criteria cri = sessionFactory.getCurrentSession().createCriteria(entityClass);
		for (int i = 0; i < paramName.length; i++) {
			cri.add(Restrictions.eq(paramName[i], value[i]));
		}
		cri.setFirstResult(first);
		cri.setMaxResults(max);
		if(order != null){
			Set<String> keys =order.keySet();
			Iterator<String> it = keys.iterator();
			String key = null;
			while(it.hasNext()){
				key = it.next();
				if(key.equals("asc")){
					cri.addOrder(Order.asc(order.get(key)));
				} else if(key.equals("desc")){
					cri.addOrder(Order.desc(order.get(key)));
				}
			}
		}
		return (List<T>) cri.list();
	}
	
	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#findFirse(java.lang.Class, java.lang.String[], java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	public <T> T findFirse(Class entityClass, String[] paramName,
			Object[] value) {
		Criteria cri = sessionFactory.getCurrentSession().createCriteria(entityClass);
		for (int i = 0; i < paramName.length; i++) {
			cri.add(Restrictions.eq(paramName[i], value[i]));
		}
		List<T> list = cri.list();
		if(list != null && list.size() > 0){
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * sxl根据参数获取记录的总数
	 * @param entityClass
	 * @param paramName
	 * @param value
	 * @return
	 */
	public Integer findCountByParames(Class entityClass, String[] paramName,
			Object[] value) {
		
		 
		Criteria cri = sessionFactory.getCurrentSession().createCriteria(entityClass);
		for (int i = 0; i < paramName.length; i++) {
			cri.add(Restrictions.eq(paramName[i], value[i]));
		}
		Long	count =(Long) cri.setProjection(Projections.rowCount()).uniqueResult();
		cri.setProjection(null);
		cri.setResultTransformer(Criteria.ROOT_ENTITY);
		
		return new Integer(count.intValue());
	}
	
	/**
	 * sxl根据参数获取记录的总数
	 * @param entityClass
	 * @param paramName
	 * @param value
	 * @return
	 */
	public Integer getCounts(Class entityClass) {
		Criteria cri = sessionFactory.getCurrentSession().createCriteria(entityClass);
		Long	count =(Long) cri.setProjection(Projections.rowCount()).uniqueResult();
		cri.setProjection(null);
		cri.setResultTransformer(Criteria.ROOT_ENTITY);
		
		return new Integer(count.intValue());
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#findFirse(java.lang.Class, java.lang.String[], java.lang.Object[],java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public <T> T findFirse(Class entityClass, String[] paramName,
			Object[] value,Map<String,String> order) {
		Criteria cri = sessionFactory.getCurrentSession().createCriteria(entityClass);
		for (int i = 0; i < paramName.length; i++) {
			cri.add(Restrictions.eq(paramName[i], value[i]));
		}
		if(order != null){
			Set<String> keys =order.keySet();
			Iterator<String> it = keys.iterator();
			String key = null;
			while(it.hasNext()){
				key = it.next();
				if(key.equals("asc")){
					cri.addOrder(Order.asc(order.get(key)));
				} else if(key.equals("desc")){
					cri.addOrder(Order.desc(order.get(key)));
				}
			}
		}
		List<T> list = cri.list();
		if(list != null && list.size() > 0){
			return list.get(0);
		} else {
			return null;
		}
	}

	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#deleteById(java.lang.Class, java.io.Serializable)
	 */
	public boolean deleteById(Class entityClass, Serializable id) {
		boolean success = false;
		try {
			Object obj = sessionFactory.getCurrentSession().load(entityClass, id);
			sessionFactory.getCurrentSession().delete(obj);
			success = true;
			return success;
		} catch (Exception e) {
			success = false;
		} finally {
			return success;
		}
	}

	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#deleteByExample(java.lang.Object)
	 */
	@SuppressWarnings("finally")
	public boolean deleteByExample(Object entity) {
		
		boolean success = false;
		try {
			sessionFactory.getCurrentSession().delete(entity);
			success = true;
		} catch (Exception e) {
			System.out.println("执行删除异常");
			e.printStackTrace();
		} finally {
			return success;
		}
	}

	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#deleteByProperty(java.lang.Class, java.lang.String[], java.lang.Object[])
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public boolean deleteByProperty(Class entityClass, String[] propertyName,
			Object[] value) {
		boolean success = false;
		try {
			List list = this.findByProperty(entityClass, propertyName, value);
			for (int i = 0; i < list.size(); i++) {
				sessionFactory.getCurrentSession().delete(list.get(i));
			}
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return success;
		}
	}

	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#saveEntity(java.lang.Object)
	 */
	public Serializable saveEntity(Object entity)  {
		return sessionFactory.getCurrentSession().save(entity);
	}

	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#updateEntity(java.lang.Object)
	 */
	@SuppressWarnings("finally")
	public boolean updateEntity(Object entity) {
		boolean success = false;
		try {
			sessionFactory.getCurrentSession().update(entity);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return success;
		}
	}

	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#findByPropertyMax(java.lang.String, java.lang.String)
	 */
	public Integer findByPropertyMax(String entityClass, String propertyName) {
		// select max
		String queryString = "select max(r."+propertyName+") from "+entityClass+" r";
		Query queryObject = sessionFactory.getCurrentSession().createQuery(queryString);
		return (Integer)queryObject.list().get(0);
	}
	
	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#findByPropertyMax(java.lang.String, java.lang.String)
	 */
	public Integer findMaxProperty(String entityClass, String propertyName,String cons,String consAnother){
		// select max
		String queryString = "select max(r."+propertyName+") from "+entityClass+" r "+"where r."+cons+"='"+consAnother+"'";
		Query queryObject = sessionFactory.getCurrentSession().createQuery(queryString);
		return (Integer)queryObject.list().get(0);
	}
	
	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#findAllTaxis(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findAllTaxis(String entityClass, String propertyName) {
		// order
		String queryString = "from "+entityClass+" r order by r."+propertyName+"";
		Query queryObject = sessionFactory.getCurrentSession().createQuery(queryString);
		return queryObject.list();
	}

	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#findBySortOrderMax(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public Integer findBySortOrderMax(String entityClass, String propertyName , Integer value) {
		// max value
		String queryString = "select max(r."+propertyName+") from "+entityClass+" r where r."+propertyName+" < "+value+"";
		Query queryObject = sessionFactory.getCurrentSession().createQuery(queryString);
		return (Integer)queryObject.list().get(0);
	}

	/** 
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO1#findBySortOrderMin(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public Integer findBySortOrderMin(String entityClass, String propertyName,Integer value) {
		// min value
		String queryString = "select min(r."+propertyName+") from "+entityClass+" r where r."+propertyName+" > "+value+"";
		Query queryObject = sessionFactory.getCurrentSession().createQuery(queryString);
		return (Integer)queryObject.list().get(0);
	}
	
	/**
	 * (non-Javadoc)
	 * @param cri
	 * @param order
	 * @return org.hibernate.Criteria
	 */
	protected Criteria getOrder(Criteria cri,Map<String,String> order){
		if(order != null){
			Set<String> keys =order.keySet();
			Iterator<String> it = keys.iterator();
			String key = null;
			while(it.hasNext()){
				key = it.next();
				if(key.equals("asc")){
					cri.addOrder(Order.asc(order.get(key)));
				} else if(key.equals("desc")){
					cri.addOrder(Order.desc(order.get(key)));
				}
			}
		}
		return cri;
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO#saveOrUpdateEntity(java.lang.Object)
	 */
	public void saveOrUpdateEntity(Object entity){
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO#hqlQuery(java.lang.String,java.lang.String,int,int,java.lang.Object[],java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> hqlQuery(String hql,String where,int first ,int max,Object[] param,Map<String,String> order){
		List<T> list = new ArrayList<T>(1);
		hql +=" where "+where;
		if(order != null){
			this.setOrder(hql, order);
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(first != 0 || max != 0){
			query.setFirstResult(first);
			query.setMaxResults(max);
		}
		this.setParam(query, param);
		list = query.list();
		return list;
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO#hqlQuery(java.lang.String,java.lang.String,java.lang.Object[],java.util.Map)
	 */
	public <T> List<T> hqlQuery(String hql,String where,Object[] param,Map<String,String> order){
		return hqlQuery(hql,where,0 ,0, param, order);
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO#hqlQuery(java.lang.String,java.lang.String,java.lang.Object[])
	 */
	public <T> List<T> hqlQuery(String hql,String where,Object[] param){
		return hqlQuery(hql,where,0 ,0, param, null);
	}
	/**
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO#hqlQuery(java.lang.String,java.lang.String,int,int,java.lang.Object[])
	 */
	public <T> List<T> hqlQuery(String hql,String where,int first,int max,Object[] param){
		return hqlQuery(hql,where,first ,max, param, null);
	}
	
	/**
	 * (non-Javadoc)
	 * @param query
	 * @param param
	 */
	private void setParam(Query query,Object[] param){
		if(param != null){
			for(int i = 0;i<param.length;i++){
				query.setParameter(i, param[i]);
			}
		}
	}
	
	/**
	 * (non-Javadoc)
	 * @param hql
	 * @param order
	 */
	private void setOrder(String hql,Map<String,String> order){
		Set<String> keys = order.keySet();
		for(String key : keys){
			hql += " order by "+key+" , "+order.get(key);
		}
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO#deleteAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public void deleteAll(Class entityClass) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "delete "+entityClass.getName();
		Query query = session.createQuery(hql);
		query.executeUpdate();
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO#findByBlur(java.lang.Class,java.lang.String[])
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByBlur(Class entityClass, String[] paramName,
			Object[] value) {
		// select as like
		Criteria cri = sessionFactory.getCurrentSession().createCriteria(entityClass);
		for (int i = 0; i < paramName.length; i++) {
			cri.add(Restrictions.like(paramName[i], value[i]));
		}
		return (List<T>) cri.list();
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO#querySql(java.lang.String)
	 */
	@SuppressWarnings("deprecation")
	public ResultSet querySql(String sql) throws SQLException{
		Connection conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
		//Connection conn = this.getSession().connection();�ѹ�ʱ,hibernate4��doworkȡ��
		Statement stmt = conn.createStatement();
		return stmt.executeQuery(sql);
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO#executeSql(java.lang.String)
	 */
	@SuppressWarnings("deprecation")
	public void executeSql(String sql) throws SQLException {
		Connection conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO#executeQuerySql(java.lang.String)
	 */
	@SuppressWarnings("deprecation")
	public ResultSet executeQuerySql(final String sql) throws SQLException{ 
		ResultSet s =	(ResultSet) getSessionFactory().getCurrentSession().doReturningWork(
				new ReturningWork(){

					public ResultSet execute(Connection conn)
							throws SQLException {
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						return rs;
					}
					
				}

		) ;
		return s;
	}
	
	
	/**
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO#executeHql(java.lang.String)
	 */
	public void executeHql(String hql) {
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
	}

	/**
	 * (non-Javadoc)
	 * @see com.cctn.util.DAO#executeNativeSql(java.lang.String)
	 */
	public void executeNativeSql(String sql) {
		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
}
