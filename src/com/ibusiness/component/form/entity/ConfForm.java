package com.ibusiness.component.form.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IB_CONF_FORM 表单管理表
 * 
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_CONF_FORM")
public class ConfForm {
    // UUID
    private String id;
    // 模块包名
    private String packageName;
    // 表单名称
    private String formName;
    // 表单名标题
    private String formTitle;
    // 表单URL地址
    private String formURL;
    // 是否可修改
    private Integer isEdit;
    // 是否可新增
    private Integer isAdd;
    // 是否可删除
    private Integer isDelete;
    // 是否可查询
    private Integer isQuery;
    // 是否导出excel
    private Integer isExcelExport;
    // 是否excel导入
    private Integer isImportExport;
    // 是否流程表单
    private Integer isBpmForm;
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
     * @return the formTitle
     */
    @Column(name = "FORMTITLE")
    public String getFormTitle() {
        return formTitle;
    }
    /**
     * @param formTitle the formTitle to set
     */
    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }
    /**
     * @return the isEdit
     */
    @Column(name = "ISEDIT")
    public Integer getIsEdit() {
        return isEdit;
    }
    /**
     * @param isEdit the isEdit to set
     */
    public void setIsEdit(Integer isEdit) {
        this.isEdit = isEdit;
    }
    /**
     * @return the isAdd
     */
    @Column(name = "ISADD")
    public Integer getIsAdd() {
        return isAdd;
    }
    /**
     * @param isAdd the isAdd to set
     */
    public void setIsAdd(Integer isAdd) {
        this.isAdd = isAdd;
    }
    /**
     * @return the isDelete
     */
    @Column(name = "ISDELETE")
    public Integer getIsDelete() {
        return isDelete;
    }
    /**
     * @param isDelete the isDelete to set
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    /**
     * @return the isQuery
     */
    @Column(name = "ISQUERY")
    public Integer getIsQuery() {
        return isQuery;
    }
    /**
     * @param isQuery the isQuery to set
     */
    public void setIsQuery(Integer isQuery) {
        this.isQuery = isQuery;
    }
    /**
     * @return the isBpmForm
     */
    @Column(name = "ISBPMFORM")
    public Integer getIsBpmForm() {
        return isBpmForm;
    }
    /**
     * @param isBpmForm the isBpmForm to set
     */
    public void setIsBpmForm(Integer isBpmForm) {
        this.isBpmForm = isBpmForm;
    }
    /**
     * @return the formURL
     */
    @Column(name = "FORMURL")
    public String getFormURL() {
        return formURL;
    }
    /**
     * @param formURL the formURL to set
     */
    public void setFormURL(String formURL) {
        this.formURL = formURL;
    }
    /**
     * @return the isExcelExport
     */
    @Column(name = "ISEXCELEXPORT")
    public Integer getIsExcelExport() {
        return isExcelExport;
    }
    /**
     * @param isExcelExport the isExcelExport to set
     */
    public void setIsExcelExport(Integer isExcelExport) {
        this.isExcelExport = isExcelExport;
    }
    /**
     * @return the isImportExport
     */
    @Column(name = "ISIMPORTEXPORT")
    public Integer getIsImportExport() {
        return isImportExport;
    }
    /**
     * @param isImportExport the isImportExport to set
     */
    public void setIsImportExport(Integer isImportExport) {
        this.isImportExport = isImportExport;
    }
}
