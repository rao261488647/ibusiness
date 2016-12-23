package com.ibusiness.bpm.entity;

import java.util.List;

/**
 * 流程节点字段配置表
 * 
 * @author JiangBo
 *
 */
public class BpmNodeTable {
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
    // 表名称
    private String tableName;
    // 表名称标题
    private String tableNameTitle;
    private List<BpmNodeColums> bpmNodeColumsList;
    /**
     * @return the id
     */
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
     * @return the tableNameTitle
     */
    public String getTableNameTitle() {
        return tableNameTitle;
    }
    /**
     * @param tableNameTitle the tableNameTitle to set
     */
    public void setTableNameTitle(String tableNameTitle) {
        this.tableNameTitle = tableNameTitle;
    }
    /**
     * @return the bpmNodeColumsList
     */
    public List<BpmNodeColums> getBpmNodeColumsList() {
        return bpmNodeColumsList;
    }
    /**
     * @param bpmNodeColumsList the bpmNodeColumsList to set
     */
    public void setBpmNodeColumsList(List<BpmNodeColums> bpmNodeColumsList) {
        this.bpmNodeColumsList = bpmNodeColumsList;
    }
}
