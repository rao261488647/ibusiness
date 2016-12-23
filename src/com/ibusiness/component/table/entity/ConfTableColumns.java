package com.ibusiness.component.table.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 业务表字段结构管理Bean, 对应 ib_conf_table_columns表
 * 
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_CONF_TABLE_COLUMNS")
public class ConfTableColumns implements java.io.Serializable {
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    // 表名称
	private String tableName;
	// 字段名
	private String columnValue;
	// 数据库中的-字段名
	private String columnValueOld;
	// 字段标题
	private String columnName;
	// 字段类型
	private String columnType;
	// 数据库中的-字段类型
	private String columnTypeOld;
	// 长度
	private String columnSize;
	// 数据库中的-长度
	private String columnSizeOld;
	// 允许空
	private String isNull;
	// 默认值
	private String defaultValue;
	// 字段排序编号
	private int columnNo;
	/**
	 * @return the tableName
	 */
	@Id
    @Column(name = "TABLENAME", nullable = false)
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return the columnValue
	 */
	@Id
    @Column(name = "COLUMNVALUE", nullable = false)
	public String getColumnValue() {
		return columnValue;
	}
	/**
	 * @param columnValue the columnValue to set
	 */
	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}
	/**
	 * Transient 用于标识不在数据库表中映射的属性
	 * @return the columnValueOld
	 */
	@Transient
	public String getColumnValueOld() {
		return columnValueOld;
	}
	/**
	 * @param columnValueOld the columnValueOld to set
	 */
	public void setColumnValueOld(String columnValueOld) {
		this.columnValueOld = columnValueOld;
	}
	/**
	 * @return the columnName
	 */
	@Column(name = "COLUMNNAME")
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @return the columnType
	 */
	@Column(name = "COLUMNTYPE")
	public String getColumnType() {
		return columnType;
	}
	/**
	 * @param columnType the columnType to set
	 */
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	/**
	 * @return the columnTypeOld
	 */
	@Transient
	public String getColumnTypeOld() {
		return columnTypeOld;
	}
	/**
	 * @param columnTypeOld the columnTypeOld to set
	 */
	public void setColumnTypeOld(String columnTypeOld) {
		this.columnTypeOld = columnTypeOld;
	}
	/**
	 * @return the columnSize
	 */
	@Column(name = "COLUMNSIZE")
	public String getColumnSize() {
		return columnSize;
	}
	/**
	 * @param columnSize the columnSize to set
	 */
	public void setColumnSize(String columnSize) {
		this.columnSize = columnSize;
	}
	/**
	 * @return the isNull
	 */
	@Column(name = "ISNULL")
	public String getIsNull() {
		return isNull;
	}
	/**
	 * @param isNull the isNull to set
	 */
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	/**
	 * @return the defaultValue
	 */
	@Column(name = "DEFAULTVALUE")
	public String getDefaultValue() {
		return defaultValue;
	}
	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	/**
	 * @return the columnNo
	 */
	@Column(name = "COLUMNNO")
	public int getColumnNo() {
		return columnNo;
	}
	/**
	 * @param columnNo the columnNo to set
	 */
	public void setColumnNo(int columnNo) {
		this.columnNo = columnNo;
	}
	/**
	 * @return the columnSizeOld
	 */
	@Transient
	public String getColumnSizeOld() {
		return columnSizeOld;
	}
	/**
	 * @param columnSizeOld the columnSizeOld to set
	 */
	public void setColumnSizeOld(String columnSizeOld) {
		this.columnSizeOld = columnSizeOld;
	}
}
