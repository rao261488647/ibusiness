package com.ibusiness.component.table.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 业务表管理Bean, 对应 ib_conf_table表
 * 
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_CONF_TABLE")
public class ConfTable {
	// UUID
    private String id;
    // 模块包名
    private String packageName;
    // 表名
    private String tableName;
    // 表名含义
    private String tableNameComment;
    // 表类型    1：单表/主表 2：子表
    private String tableType;
    // 关联主表ID
    private String parentTableId;
    // 是否流程表
    private Integer isBpmTable;
    /**
     * @return the id
     */
    @Id
    @Column(name = "ID", nullable = false)
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
	/**
     * 取得tableName
     * @return the tableName
     */
    @Column(name = "TABLENAME")
    public String getTableName() {
        return tableName;
    }
    /**
     * 设置tableName
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    /**
     * 取得tableNameComment
     * @return the tableNameComment
     */
    @Column(name = "TABLENAMECOMMENT")
    public String getTableNameComment() {
        return tableNameComment;
    }
    /**
     * 设置tableNameComment
     * @param tableNameComment the tableNameComment to set
     */
    public void setTableNameComment(String tableNameComment) {
        this.tableNameComment = tableNameComment;
    }
    /**
     * @return the packageName
     */
    @Column(name = "PACKAGENAME")
    public String getPackageName() {
        return packageName;
    }
    /**
     * @param packageName the packageName to set
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    /**
     * @return the tableType
     */
    @Column(name = "TABLETYPE")
    public String getTableType() {
        return tableType;
    }
    /**
     * @param tableType the tableType to set
     */
    public void setTableType(String tableType) {
        this.tableType = tableType;
    }
    /**
     * @return the parentTableId
     */
    @Column(name = "PARENTTABLEID")
    public String getParentTableId() {
        return parentTableId;
    }
    /**
     * @param parentTableId the parentTableId to set
     */
    public void setParentTableId(String parentTableId) {
        this.parentTableId = parentTableId;
    }
    /**
     * @return the isBpmTable
     */
    @Column(name = "ISBPMTABLE")
    public Integer getIsBpmTable() {
        return isBpmTable;
    }
    /**
     * @param isBpmTable the isBpmTable to set
     */
    public void setIsBpmTable(Integer isBpmTable) {
        this.isBpmTable = isBpmTable;
    }
}
