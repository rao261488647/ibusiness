package com.ibusiness.component.form.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * IB_CONF_FORM_TABLE_COLUMS 表单对应字段管理表
 * 
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_CONF_FORM_TABLE_COLUMS")
public class ConfFormTableColumn implements java.io.Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    // 模块包名
    private String packageName;
    // 表单名称
    private String formName;
    // 表单字段名
    private String formColumn;
    // 表单名标题
    private String formColumnTitle;
    // 表名
    private String tableName;
    // 表字段名
    private String tableColumn;
    // 表字段名(小写)生成JSP模板用
    private String tableColumnLower;
    // 组件类型
    private String fcType;
    // 录入宽度
    private String fcWidth;
    // 录入高度
    private String fcHeight;
    // 是否显示
    private String fcDisplay;
    // 是否编辑
    private String fcEdit;
    // 必须输入
    private String fcMust;
    // 是否查询条件字段
    private String fcQuery;
    // 默认值-公式编辑
    private String fcDefault;
    // 字段排序
    private Integer columnNo;
    // 下拉列表内容信息
    private String confSelectInfo;
    
    /**
     * @return the packageName
     */
    @Id
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
     * @return the formName
     */
    @Id
    @Column(name = "FORMNAME", nullable = false)
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
     * @return the formColumn
     */
    @Id
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
     * @return the id
     */
    @Column(name = "TABLENAME")
    public String getTableName() {
        return tableName;
    }
    /**
     * @param id the id to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
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
     * @return the fcType
     */
    @Column(name = "FCTYPE")
    public String getFcType() {
        return fcType;
    }
    /**
     * @param fcType the fcType to set
     */
    public void setFcType(String fcType) {
        this.fcType = fcType;
    }
    /**
     * @return the fcWidth
     */
    @Column(name = "FCWIDTH")
    public String getFcWidth() {
        return fcWidth;
    }
    /**
     * @param fcWidth the fcWidth to set
     */
    public void setFcWidth(String fcWidth) {
        this.fcWidth = fcWidth;
    }
    /**
     * @return the fcHeight
     */
    @Column(name = "FCHEIGHT")
    public String getFcHeight() {
        return fcHeight;
    }
    /**
     * @param fcHeight the fcHeight to set
     */
    public void setFcHeight(String fcHeight) {
        this.fcHeight = fcHeight;
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
     * @return the fcQuery
     */
    @Column(name = "FCQUERY")
    public String getFcQuery() {
        return fcQuery;
    }
    /**
     * @param fcQuery the fcQuery to set
     */
    public void setFcQuery(String fcQuery) {
        this.fcQuery = fcQuery;
    }
    /**
     * @return the fcDefault
     */
    @Column(name = "FCDEFAULT")
    public String getFcDefault() {
        return fcDefault;
    }
    /**
     * @param fcDefault the fcDefault to set
     */
    public void setFcDefault(String fcDefault) {
        this.fcDefault = fcDefault;
    }
    /**
     * @return the tableColumnLower
     */
    @Transient
    public String getTableColumnLower() {
        return tableColumnLower;
    }
    /**
     * @param tableColumnLower the tableColumnLower to set
     */
    public void setTableColumnLower(String tableColumnLower) {
        this.tableColumnLower = tableColumnLower;
    }
    /**
     * @return the columnNo
     */
    @Column(name = "COLUMNNO")
    public Integer getColumnNo() {
        return columnNo;
    }
    /**
     * @param columnNo the columnNo to set
     */
    public void setColumnNo(Integer columnNo) {
        this.columnNo = columnNo;
    }
    /**
     * @return the confSelectInfo
     */
    @Column(name = "CONFSELECTINFO")
    public String getConfSelectInfo() {
        return confSelectInfo;
    }
    /**
     * @param confSelectInfo the confSelectInfo to set
     */
    public void setConfSelectInfo(String confSelectInfo) {
        this.confSelectInfo = confSelectInfo;
    }
    /**
     * @return the fcMust
     */
    @Column(name = "FCMUST")
    public String getFcMust() {
        return fcMust;
    }
    /**
     * @param fcMust the fcMust to set
     */
    public void setFcMust(String fcMust) {
        this.fcMust = fcMust;
    }
}
