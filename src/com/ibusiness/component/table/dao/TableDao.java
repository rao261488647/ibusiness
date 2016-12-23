package com.ibusiness.component.table.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.dao.BaseEntityDao;
import com.ibusiness.component.table.entity.ConfTable;
import com.ibusiness.component.table.entity.ConfTableColumns;

/**
 * 流水表表结构管理DAO
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TableDao extends BaseEntityDao<ConfTable> {
	
    /**
     * 业务表管理表信息查询
     * 
     * @param sql
     * @return
     */
    public List<ConfTable> queryConfTableList(String sql) {
       try{
           return getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ConfTable.class));
           }catch (Exception e){
           return null;
       }
    }
    /**
     * 表列字段结构管理表信息查询
     * @param sql
     * @return
     */
	public ConfTableColumns queryConfTableColumn(String sql) {
		try {
			List<ConfTableColumns> list = getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(ConfTableColumns.class));
			if (list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
    /**
     * 表字段结构管理表信息查询
     * 
     * @param sql
     * @return
     */
	public List<ConfTableColumns> queryConfTableColumns(String sql) {
		try {
			return getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(ConfTableColumns.class));
		} catch (Exception e) {
			return null;
		}
	}
    /**
     * 插入数据-业务表管理表
     * 
     * @param list
     * @param sql
     */
	public int insertConfTable(final List<ConfTable> list, String sql) {
		try{
	          super.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
	             @Override
	             public int getBatchSize() {
	              return list.size();    //这个方法设定更新记录数，通常List里面存放的都是我们要更新的，所以返回list.size()；
	             }
	              @Override
	              public void setValues(PreparedStatement ps, int i)
	                      throws SQLException {
	            	  ConfTable bean = list.get(i);
	            	  ps.setString(1, bean.getId());
	            	  ps.setString(2, bean.getPackageName());
	                  ps.setString(3, bean.getTableName());
	                  ps.setString(4, bean.getTableNameComment());
	              }
	          });
	          return 1;
	      }catch (Exception e){
	          return 0;
	      }
	}
	/**
     * 插入数据-表列字段结构管理表
     * 
     * @param list
     * @param sql
     */
	public int insertConfTableColumns(final List<ConfTableColumns> list, String sql) {
		try{
		    super.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
	             @Override
	             public int getBatchSize() {
	              return list.size();    //这个方法设定更新记录数，通常List里面存放的都是我们要更新的，所以返回list.size()；
	             }
	              @Override
	              public void setValues(PreparedStatement ps, int i)
	                      throws SQLException {
	            	  ConfTableColumns bean = list.get(i);
	                  ps.setString(1, bean.getTableName());
	            	  ps.setString(2, bean.getColumnValue());
	                  ps.setString(3, bean.getColumnName());
	                  ps.setString(4, bean.getColumnType());
	                  ps.setString(5, bean.getColumnSize());
	                  ps.setString(6, bean.getIsNull());
	                  ps.setString(7, bean.getDefaultValue());
	                  ps.setInt(8, bean.getColumnNo());
	              }
	          });
	          return 1;
	      } catch (Exception e){
	          return 0;
	      }
	}
	/**
     * 删除业务表管理表数据
     * 
     * @param selectedItem
     * @param sql
     */
	public int batchDeleteConfTable(final List<String> list, String sql) {
		try{
		    super.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
             @Override
             public int getBatchSize() {
              return list.size();    //这个方法设定更新记录数，通常List里面存放的都是我们要更新的，所以返回list.size()；
             }
              @Override
              public void setValues(PreparedStatement ps, int i)
                      throws SQLException {
            	  String uuid = list.get(i);
                  ps.setString(1, uuid);
              }
          });
          return 1;
      }catch (Exception e){
          return 0;
      }
	}
	/**
     * 删除表列字段管理表信息
     * @param list
     * @param sql
     */
	public int batchDeleteConfTableColumns(final List<String> list, final String tableName, String sql) {
		try{
		    super.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
	             @Override
	             public int getBatchSize() {
	              return list.size();    //这个方法设定更新记录数，通常List里面存放的都是我们要更新的，所以返回list.size()；
	             }
	              @Override
	              public void setValues(PreparedStatement ps, int i)
	                      throws SQLException {
	            	  String columnValue = list.get(i);
	            	  ps.setString(1, tableName);
	                  ps.setString(2, columnValue);
	              }
	          });
	          return 1;
	      }catch (Exception e){
	          return 0;
	      }
	}
    /**
     * 更新
     * 
     * @param sql
     * @return
     */
    public int update(String sql) {
        try {
            super.getJdbcTemplate().update(sql);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    /**
     * 执行
     * @param sql
     * @return
     */
    public int execute(String sql) {
        try {
            super.getJdbcTemplate().execute(sql);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
