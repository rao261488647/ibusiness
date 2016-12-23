package com.ibusiness.component.table.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.component.table.dao.TableDao;
import com.ibusiness.component.table.entity.ConfTable;
import com.ibusiness.component.table.entity.ConfTableColumns;

/**
 * 流水表表结构管理 service
 * 
 * @author JiangBo
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TableService {
	
	/**
	 * 实例化公用DAO
	 */
	private TableDao dao;
	
	/**
     * 业务表管理表信息查询
     * 
     * @param string
     * @return
     */
    public List<ConfTable> queryConfTableByTableName(String tableName) {
        String sql = " select * from IB_CONF_TABLE ";
        if (!CommonUtils.isNull(tableName)) {
            sql = sql + " WHERE tableName='" + tableName +"' ";
        }
        return dao.queryConfTableList(sql);
    }
	/**
     * 业务表管理表信息查询
     * 
     * @param string
     * @return
     */
    public List<ConfTable> queryConfTableList(String packageName, String isBpmTable) {
        String sql = " select * from IB_CONF_TABLE ";
        if (!CommonUtils.isNull(packageName)) {
        	sql = sql + " WHERE packageName='" + packageName +"' ";
        }
        if (!CommonUtils.isNull(isBpmTable)) {
            sql = sql + " AND isBpmTable=" + isBpmTable;
        }
        sql = sql + " order by tablenamecomment ";
        return dao.queryConfTableList(sql);
    }
    /**
     * 表字段结构管理表信息查询
     * 
     * @param tableName
     * @return
     */
	public List<ConfTableColumns> queryConfTableColumns(String tableName) {
		String sql = " SELECT tableName, columnValue,columnValue columnValueOld, columnName, columnType, columnType columnTypeOld, columnSize, ISNULL, defaultValue, columnNo FROM IB_CONF_TABLE_COLUMNS ";
        if (!CommonUtils.isNull(tableName)) {
        	sql = sql + " WHERE tableName='" + tableName +"' ";
        }
        sql = sql + " ORDER bY columnNo ";
        return dao.queryConfTableColumns(sql);
	}
	/**
     * 表列字段结构管理表信息查询
     * @param tableName
     * @param columnValue
     * @return
     */
	public ConfTableColumns queryConfTableColumn(String tableName, String columnValue) {
		String sql = " SELECT tableName, columnValue,columnValue columnValueOld, columnName, columnType, columnType columnTypeOld, columnSize, ISNULL, defaultValue, columnNo FROM IB_CONF_TABLE_COLUMNS WHERE tableName='" + tableName +"' AND columnValue ='" + columnValue + "' ";
		return dao.queryConfTableColumn(sql);
	}
	/**
     * 取得列最大编号(预埋字段不算即,90以后的编号不统计)
     * @param tableName
     * @param columnValue
     * @return
     */
    public ConfTableColumns queryMaxTableColumnNo(String tableName) {
        String sql = " SELECT max(columnNo) columnNo FROM IB_CONF_TABLE_COLUMNS WHERE tableName='" + tableName +"' AND columnNo<90 ";
        return dao.queryConfTableColumn(sql);
    }
    /**
     * 插入数据-业务表管理表
     * @param tableName
     */
	public void insertConfTable(List<ConfTable> list) {
		String sql = "insert into IB_CONF_TABLE("
				+ " id, packagename, tablename, tablenamecomment) " + " values(?, ?, ?, ?)";
		dao.insertConfTable(list, sql);
	}
	/**
     * 插入数据-表列字段结构管理表
     * 
     * @param list
     */
	public void insertConfTableColumns(List<ConfTableColumns> list) {
		String sql = " insert into IB_CONF_TABLE_COLUMNS ("
				+ " tableName,columnValue,columnName,columnType,columnSize,"
				+ " IsNull,defaultValue,columnNo) "
				+ " values(?, ?, ?, ?, ?,"
				+ " ?, ?, ?)";
		dao.insertConfTableColumns(list, sql);
	}
	/**
     * 删除业务表管理表数据
     * 
     * @param list
     */
	public void deleteConfTable(List<String> list) {
		String sql = "delete from IB_CONF_TABLE WHERE id = ? ";
		dao.batchDeleteConfTable(list, sql);
	}
	/**
     * 删除表列字段管理表信息
     * 
     * @param selectedItem
     */
	public void deleteConfColumnsTable(List<String> list, String tableName) {
		String sql = "delete from IB_CONF_TABLE_COLUMNS WHERE tableName = ? AND columnValue = ?";
		dao.batchDeleteConfTableColumns(list, tableName, sql);
	}
	/**
     * 变更/修改表列字段管理表信息
     * 
     * @param tableName
     * @param columnValue
     */
	public void updateConfTableColumns(String tableName, ConfTableColumns bean) {
		// 存储数据
		String sql = " UPDATE IB_CONF_TABLE_COLUMNS SET ";
		sql = sql + " columnName='"+bean.getColumnName()+"', columnType='"+bean.getColumnType()+"', columnSize='"+bean.getColumnSize()+"', ";
		sql = sql + " isNull='"+bean.getIsNull()+"', defaultValue='"+bean.getDefaultValue()+"', columnNo='"+bean.getColumnNo()+"'";
		sql = sql + " WHERE tableName='"+tableName+"' AND columnValue='"+bean.getColumnValue()+"'";
		dao.update(sql);
	}
    /**
     * 更新数据
     * @param sql
     */
    public void update(String sql) {
        dao.update(sql);
    }
    /**
     * 执行
     * @param sql
     */
    public int execute(String sql) {
        return dao.execute(sql);
    }
	/**
	 * 设置DAO
	 * @param dao the DAO to set
	 */
    @Resource
	public void setDao(TableDao dao) {
		this.dao = dao;
	}
    public TableDao getDao() {
        return this.dao;
    }
}
