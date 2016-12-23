package com.ibusiness.common.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 基础DAO接口
 * @author 马明君
 * @version 1.0
 */

public interface DAO {
	/**
	 * sxl 获取记录总数
	 * @param entityClass
	 * @return
	 */
	
	public Integer getCounts(Class entityClass);
	/**
	 * sxl根据参数获取记录的总数
	 * @param entityClass
	 * @param paramName
	 * @param value
	 * @return
	 */
	public Integer findCountByParames(Class entityClass, String[] paramName,
			Object[] value);

	/**
	 * (non-Javadoc)
	 * @param entityClass
	 * @return List
	 * @see util.DAO#findAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public  List findAll(Class entityClass) ;
	
	/**
	 * (non-Javadoc)
	 * @param entityClass
	 * @param order
	 * @return List
	 * @see util.DAO#findAll(java.lang.Class,java.utl.Map)
	 */
	@SuppressWarnings({ "unchecked"})
	public  List findAll(Class entityClass, Map<String, String> order);

	/**
	 * (non-Javadoc)
	 * @param <T>
	 * @param entityClass
	 * @return List<T>
	 * @see util.DAO#findAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public  <T> List<T> findALL(Class entityClass);

	/**
	 * (non-Javadoc)
	 * @param <T>
	 * @param entityClass
	 * @param first
	 * @param max
	 * @return List<T>
	 * @see util.DAO#findAll(java.lang.Class,int,int)
	 */
	@SuppressWarnings("unchecked")
	public  <T> List<T> findALL(Class entityClass, int first, int max);
	
	
	/**
	 * (non-Javadoc)
	 * @param <T>
	 * @param entityClass
	 * @param first
	 * @param max
	 * @param order
	 * @return List<T>
	 * @see util.DAO#findAll(java.lang.Class,int,int,java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public  <T> List<T> findALL(Class entityClass, int first, int max,
			Map<String, String> order);

	/**
	 * (non-Javadoc)
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return java.lang.Object
	 * @see util.DAO#findById(java.lang.Class,java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	public  <T> T findById(Class entityClass, Serializable id);

	/**
	 * (non-Javadoc)
	 * @param <T>
	 * @param entityClass
	 * @param paramName
	 * @param value
	 * @return List<T>
	 * @see util.DAO#findByProperty(java.lang.Class,java.lang.String[],java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	public  <T> List<T> findByProperty(Class entityClass,
			String[] paramName, Object[] value);
	
	public  <T> List<T> findByProperty1(Class entityClass,
			String[] paramName, Object[] value);
	/**
	 * (non-Javadoc)
	 * @param <T>
	 * @param entityClass
	 * @param paramName
	 * @param value
	 * @param order
	 * @return List<T>
	 * @see util.DAO#findByProperty(java.lang.Class,java.lang.String[],java.lang.Object[],java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public  <T> List<T> findByProperty(Class entityClass,
			String[] paramName, Object[] value, Map<String, String> order);

	/**
	 * (non-Javadoc)
	 * @param <T>
	 * @param entityClass
	 * @param paramName
	 * @param value
	 * @param first
	 * @param max
	 * @return List<T>
	 * @see util.DAO#findByProperty(java.lang.Class,java.lang.String[],java.lang.Object[],int,int)
	 */
	@SuppressWarnings("unchecked")
	public  <T> List<T> findByProperty(Class entityClass,
			String[] paramName, Object[] value, int first, int max);
	/**
	 * (non-Javadoc)
	 * @param <T>
	 * @param entityClass
	 * @param paramName
	 * @param value
	 * @param first
	 * @param max
	 * @param order
	 * @return List<T>
	 * @see util.DAO#findByProperty(java.lang.Class,java.lang.String[],java.lang.Object[],int,int,java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public  <T> List<T> findByProperty(Class entityClass,
			String[] paramName, Object[] value, int first, int max,
			Map<String, String> order);
	/**
	 * (non-Javadoc)
	 * @param <T>
	 * @param entityClass
	 * @param paramName
	 * @param value
	 * @return Object
	 * @see util.DAO#findFirse(java.lang.Class,java.lang.String[],java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	public  <T> T findFirse(Class entityClass, String[] paramName,
			Object[] value);
	/**
	 * (non-Javadoc)
	 * @param <T>
	 * @param entityClass
	 * @param paramName
	 * @param value
	 * @param order
	 * @return Object
	 * @see util.DAO#findFirse(java.lang.Class,java.lang.String[],java.lang.Object[],java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public <T> T findFirse(Class entityClass, String[] paramName,
			Object[] value,Map<String,String> order);
	/**
	 * (non-Javadoc)
	 * @param entityClass
	 * @param id
	 * @return boolean
	 * @see util.DAO#deleteById(java.lang.Class,java.io.Serializable)
	 */
	@SuppressWarnings( { "unchecked"})
	public  boolean deleteById(Class entityClass, Serializable id);

	/**
	 * (non-Javadoc)
	 * @param entity
	 * @return boolean
	 * @see util.DAO#deleteByExample(java.lang.Object)
	 */
	public  boolean deleteByExample(Object entity);

	/**
	 * (non-Javadoc)
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return boolean
	 * @see util.DAO#deleteByProperty(java.lang.Class,java.lang.String[],java.lang.Object[])
	 */
	@SuppressWarnings( { "unchecked"})
	public  boolean deleteByProperty(Class entityClass,
			String[] propertyName, Object[] value);

	/**
	 * (non-Javadoc)
	 * @param entity
	 * @return Serializable
	 * @see util.DAO#saveEntity(java.lang.Object)
	 */
	public  Serializable saveEntity(Object entity);

	/**
	 * (non-Javadoc)
	 * @param entity
	 * @return boolean
	 * @see util.DAO#updateEntity(java.lang.Object)
	 */
	public  boolean updateEntity(Object entity);
	/**
	 * (non-Javadoc)
	 * @param entityClass
	 * @param propertyName
	 * @return Integer
	 * @see util.DAO#findByPropertyMax(java.lang.String,java.lang.String)
	 */
	public  Integer findByPropertyMax(String entityClass,
			String propertyName);
	/**
	 * (non-Javadoc)
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @return List<T>
	 * @see util.DAO#findAllTaxis(java.lang.String,java.lang.String)
	 */
	public  <T> List<T> findAllTaxis(String entityClass,
			String propertyName);
	/**
	 * (non-Javadoc)
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return Integer
	 * @see util.DAO#findBySortOrderMax(java.lang.String,java.lang.String,java.lang.Integer)
	 */
	public  Integer findBySortOrderMax(String entityClass,
			String propertyName, Integer value);
	/**
	 * (non-Javadoc)
	 * @param entityClass
	 * @param propertyName
	 * @param cons
	 * @param consAnother
	 * @return Integer
	 * @see util.DAO#findMaxProperty(java.lang.String,java.lang.String,java.lang.String,java.lang.String)
	 */
	public  Integer findMaxProperty(String entityClass, String propertyName,String cons,String consAnother);
	/**
	 * (non-Javadoc)
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return Integer
	 * @see util.DAO#findBySortOrderMin(java.lang.String,java.lang.String.java.lang.Integer)
	 */
	public  Integer findBySortOrderMin(String entityClass,
			String propertyName, Integer value);
	/**
	 * (non-Javadoc)
	 * @param entity
	 * @see uitl.DAO#saveOrUpdateEntity(java.lang.Object)
	 */
	public void saveOrUpdateEntity(Object entity);
	/**
	 * (non-Javadoc)
	 * @param <T>
	 * @param hql
	 * @param where
	 * @param first
	 * @param max
	 * @param param
	 * @param order
	 * @return List<T>
	 * @see util.DAO#hqlQuery(java.lang.String,java.lang.String,int,int,java.lang.Object[],java.util.Map)
	 */
	public <T> List<T> hqlQuery(String hql,String where,int first ,int max,Object[] param,Map<String,String> order);
	/**
	 * (non-Javadoc)
	 * @param <T>
	 * @param hql
	 * @param where
	 * @param param
	 * @param order
	 * @return List<T>
	 * @see util.DAO#hqlQuery(java.lang.String,java.lang.String,java.lang.Object[],java.util.Map)
	 */
	public <T> List<T> hqlQuery(String hql,String where,Object[] param,Map<String,String> order);
	/**
	 * (non-Javadoc)
	 * @param <T>
	 * @param hql
	 * @param where
	 * @param param
	 * @return List<T>
	 * @see util.DAO#hqlQuery(java.lang.String,java.lang.String,java.lang.Object[])
	 */
	public <T> List<T> hqlQuery(String hql,String where,Object[] param);
	/**
	 * (non-Javadoc)
	 * @param <T>
	 * @param hql
	 * @param where
	 * @param first
	 * @param max
	 * @param param
	 * @return List<T>
	 * @see util.DAO#hqlQuery(java.lang.String,java.lang.String,int,int,java.lang.Object[])
	 */
	public <T> List<T> hqlQuery(String hql,String where,int first,int max,Object[] param);
	/**
	 * (non-Javadoc)
	 * @param hql
	 * @see util.DAO#executeHql(java.lang.String)
	 */
	public void executeHql(String hql);
	/**
	 * (non-Javadoc)
	 * @param entityClass
	 * @see util.DAO#deleteAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public void deleteAll(Class entityClass);
	/**
	 * (non-Javadoc)
	 * @param sql
	 * @see util.DAO#executeNativeSql(java.lang.String)
	 */
	public void executeNativeSql(String sql);
	/**
	 * (non-Javadoc)
	 * @param <T>
	 * @param entityClass
	 * @param paramName
	 * @param value
	 * @return List<T>
	 * @see util.DAO#findByBlur(java.lang.Class,java.lang.String[],java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	public  <T> List<T> findByBlur(Class entityClass,
			String[] paramName, Object[] value);
	/**
	 * (non-Javadoc)
	 * @param sql
	 * @return java.sql.ResultSet
	 * @see util.DAO#querySql(java.lang.String)
	 * @throws SQLException
	 */
	public ResultSet querySql(String sql) throws SQLException;
	/**
	 * (non-Javadoc)
	 * @param sql
	 * @see util.DAO#executeSql(java.lang.String)
	 * @throws SQLException
	 */
	public void executeSql(String sql) throws SQLException;
	/**
	 * (non-Javadoc)
	 * @param sql
	 * @return java.sql.ResultSet
	 * @see util.DAO#executeQuerySql(java.lang.String)
	 * @throws SQLException
	 */
	public ResultSet executeQuerySql(final String sql) throws SQLException ;
	
	
}