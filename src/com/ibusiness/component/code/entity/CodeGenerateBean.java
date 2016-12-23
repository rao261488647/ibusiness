package com.ibusiness.component.code.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器用Bean
 * 
 * @author JiangBo
 *
 */
public class CodeGenerateBean {
    // 包名
    private String packageName;
    // 表单名
    private String formName;
    // 实体类名(首字母大写)
    private String entityName;
    // 表名
    private String tableName;
    // 主键生成测率 UUID
    private String keyType;
    // 实体功能描述
    private String entityTitle;
    // 行字段数目 1
    private String rowNumber;
    // 风格
    private String formStyle;
    // 菜单URL
    private String menuUrl;
    // 子表关联配置信息
    private List<CodeGenerateBean> subCodeGenerateBeans = new ArrayList<CodeGenerateBean>();
    // 设备项目选择信息 (页面显示用)
    private List<String> selectedItems = new ArrayList<String>();
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
     * @return the entityName
     */
    public String getEntityName() {
        return entityName;
    }
    /**
     * @param entityName the entityName to set
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
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
     * @return the keyType
     */
    public String getKeyType() {
        return keyType;
    }
    /**
     * @param keyType the keyType to set
     */
    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }
    /**
     * @return the entityTitle
     */
    public String getEntityTitle() {
        return entityTitle;
    }
    /**
     * @param entityTitle the entityTitle to set
     */
    public void setEntityTitle(String entityTitle) {
        this.entityTitle = entityTitle;
    }
    /**
     * @return the rowNumber
     */
    public String getRowNumber() {
        return rowNumber;
    }
    /**
     * @param rowNumber the rowNumber to set
     */
    public void setRowNumber(String rowNumber) {
        this.rowNumber = rowNumber;
    }
    /**
     * @return the formStyle
     */
    public String getFormStyle() {
        return formStyle;
    }
    /**
     * @param formStyle the formStyle to set
     */
    public void setFormStyle(String formStyle) {
        this.formStyle = formStyle;
    }
    /**
     * @return the menuUrl
     */
    public String getMenuUrl() {
        return menuUrl;
    }
    /**
     * @param menuUrl the menuUrl to set
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
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
     * @return the subCodeGenerateBeans
     */
    public List<CodeGenerateBean> getSubCodeGenerateBeans() {
        return subCodeGenerateBeans;
    }
    /**
     * @param subCodeGenerateBeans the subCodeGenerateBeans to set
     */
    public void setSubCodeGenerateBeans(List<CodeGenerateBean> subCodeGenerateBeans) {
        this.subCodeGenerateBeans = subCodeGenerateBeans;
    }
    /**
     * @return the selectedItems
     */
    public List<String> getSelectedItems() {
        return selectedItems;
    }
    /**
     * @param selectedItems the selectedItems to set
     */
    public void setSelectedItems(List<String> selectedItems) {
        this.selectedItems = selectedItems;
    }
}
