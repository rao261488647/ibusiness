package com.ibusiness.bpm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 流程节点表单配置表
 * 
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_BPM_NODE_FORM")
public class BpmNodeForm {
    // UUID编号
    private String id;
    // 模块包名
    private String packageName;
    // 流程UUID
    private String flowId; 
    // 流程版本编号
    private String flowVersionId;
    // 节点编号
    private String nodeId;
    // 表单名
    private String formValue;
    // 类型
    private Integer formType;
    // 外部表单名
    private String originFormValue;
    // 外部表单类型
    private String originFormType;
    // 状态
    private String formStatus;
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
     * @return the flowVersionId
     */
    @Column(name = "FLOWVERSIONID")
    public String getFlowVersionId() {
        return flowVersionId;
    }
    /**
     * @param flowVersionId the flowVersionId to set
     */
    public void setFlowVersionId(String flowVersionId) {
        this.flowVersionId = flowVersionId;
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
     * @return the formValue
     */
    @Column(name = "FORMVALUE")
    public String getFormValue() {
        return formValue;
    }
    /**
     * @param formValue the formValue to set
     */
    public void setFormValue(String formValue) {
        this.formValue = formValue;
    }
    /**
     * @return the formType
     */
    @Column(name = "FORMTYPE")
    public Integer getFormType() {
        return formType;
    }
    /**
     * @param formType the formType to set
     */
    public void setFormType(Integer formType) {
        this.formType = formType;
    }
    /**
     * @return the originFormValue
     */
    @Column(name = "ORIGINFORMVALUE")
    public String getOriginFormValue() {
        return originFormValue;
    }
    /**
     * @param originFormValue the originFormValue to set
     */
    public void setOriginFormValue(String originFormValue) {
        this.originFormValue = originFormValue;
    }
    /**
     * @return the originFormType
     */
    @Column(name = "ORIGINFORMTYPE")
    public String getOriginFormType() {
        return originFormType;
    }
    /**
     * @param originFormType the originFormType to set
     */
    public void setOriginFormType(String originFormType) {
        this.originFormType = originFormType;
    }
    /**
     * @return the formStatus
     */
    @Column(name = "FORMSTATUS")
    public String getFormStatus() {
        return formStatus;
    }
    /**
     * @param formStatus the formStatus to set
     */
    public void setFormStatus(String formStatus) {
        this.formStatus = formStatus;
    }
}
