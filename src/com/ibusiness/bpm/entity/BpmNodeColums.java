package com.ibusiness.bpm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 流程节点字段配置表
 * 
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_BPM_NODE_COLUMS")
public class BpmNodeColums {
    // UUID编号
    private String id;
    // 模块包名
    private String packageName;
    // 流程ID
    private String flowId; 
    // 表单名称
    private String formName;
    // 节点ID
    private String nodeId;
    // 表单字段名
    private String formColumn;
    // 表字段标题名称
    private String formColumnTitle;
    // 表名称
    private String tableName;
    // 表字段
    private String tableColumn;
    // 是否显示
    private String fcDisplay;
    // 是否编辑
    private String fcEdit;
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
     * @return the flowId
     */
    @Column(name = "FLOWID")
    public String getFlowId() {
        return flowId;
    }
    /**
     * @param flowId the flowId to set
     */
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }
    /**
     * @return the formName
     */
    @Column(name = "FORMNAME")
    public String getFormName() {
        return formName;
    }
    /**
     * @param formName the formName to set
     */
    public void setFormName(String formName) {
        this.formName = formName;
    }
    /**
     * @return the nodeId
     */
    @Column(name = "NODEID")
    public String getNodeId() {
        return nodeId;
    }
    /**
     * @param nodeId the nodeId to set
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
    /**
     * @return the formColumn
     */
    @Column(name = "FORMCOLUMN")
    public String getFormColumn() {
        return formColumn;
    }
    /**
     * @param formColumn the formColumn to set
     */
    public void setFormColumn(String formColumn) {
        this.formColumn = formColumn;
    }
    /**
     * @return the tableName
     */
    @Column(name = "TABLENAME")
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
     * @return the tableColumn
     */
    @Column(name = "TABLECOLUMN")
    public String getTableColumn() {
        return tableColumn;
    }
    /**
     * @param tableColumn the tableColumn to set
     */
    public void setTableColumn(String tableColumn) {
        this.tableColumn = tableColumn;
    }
    /**
     * @return the fcDisplay
     */
    @Column(name = "FCDISPLAY")
    public String getFcDisplay() {
        return fcDisplay;
    }
    /**
     * @param fcDisplay the fcDisplay to set
     */
    public void setFcDisplay(String fcDisplay) {
        this.fcDisplay = fcDisplay;
    }
    /**
     * @return the fcEdit
     */
    @Column(name = "FCEDIT")
    public String getFcEdit() {
        return fcEdit;
    }
    /**
     * @param fcEdit the fcEdit to set
     */
    public void setFcEdit(String fcEdit) {
        this.fcEdit = fcEdit;
    }
    /**
     * @return the formColumnTitle
     */
    @Column(name = "FORMCOLUMNTITLE")
    public String getFormColumnTitle() {
        return formColumnTitle;
    }
    /**
     * @param formColumnTitle the formColumnTitle to set
     */
    public void setFormColumnTitle(String formColumnTitle) {
        this.formColumnTitle = formColumnTitle;
    }
}
