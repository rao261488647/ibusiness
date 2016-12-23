package com.ibusiness.bpm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 流程节点配置表
 * 
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_BPM_FLOW_NODE")
public class BpmFlowNode {
    // 编号
    private String id;
    // 模块包名
    private String packageName;
    // 流程UUID
    private String flowId;
    // 流程版本编号
    private String flowVersionId;
    // 节点代码
    private String nodeCode;
    // 节点名
    private String nodeName;
    // 节点类型
    private String nodeType;
    // 人员
    private String confUser;
    // 事件
    private String confListener;
    // 规则
    private String confRule;
    // 表单
    private String confForm;
    // 操作
    private String confOperation;
    // 提醒
    private String confNotice;
    // 优先级
    private int priority;
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
     * @return the nodeCode
     */
    @Column(name = "NODECODE")
    public String getNodeCode() {
        return nodeCode;
    }
    /**
     * @param nodeCode the nodeCode to set
     */
    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }
    /**
     * @return the name
     */
    @Column(name = "NODENAME")
    public String getNodeName() {
        return nodeName;
    }
    /**
     * @param name the name to set
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    /**
     * @return the type
     */
    @Column(name = "NODETYPE")
    public String getNodeType() {
        return nodeType;
    }
    /**
     * @param type the type to set
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }
    /**
     * @return the confUser
     */
    @Column(name = "CONFUSER")
    public String getConfUser() {
        return confUser;
    }
    /**
     * @param confUser the confUser to set
     */
    public void setConfUser(String confUser) {
        this.confUser = confUser;
    }
    /**
     * @return the confListener
     */
    @Column(name = "CONFLISTENER")
    public String getConfListener() {
        return confListener;
    }
    /**
     * @param confListener the confListener to set
     */
    public void setConfListener(String confListener) {
        this.confListener = confListener;
    }
    /**
     * @return the confRule
     */
    @Column(name = "CONFRULE")
    public String getConfRule() {
        return confRule;
    }
    /**
     * @param confRule the confRule to set
     */
    public void setConfRule(String confRule) {
        this.confRule = confRule;
    }
    /**
     * @return the confForm
     */
    @Column(name = "CONFFORM")
    public String getConfForm() {
        return confForm;
    }
    /**
     * @param confForm the confForm to set
     */
    public void setConfForm(String confForm) {
        this.confForm = confForm;
    }
    /**
     * @return the confOperation
     */
    @Column(name = "CONFOPERATION")
    public String getConfOperation() {
        return confOperation;
    }
    /**
     * @param confOperation the confOperation to set
     */
    public void setConfOperation(String confOperation) {
        this.confOperation = confOperation;
    }
    /**
     * @return the confNotice
     */
    @Column(name = "CONFNOTICE")
    public String getConfNotice() {
        return confNotice;
    }
    /**
     * @param confNotice the confNotice to set
     */
    public void setConfNotice(String confNotice) {
        this.confNotice = confNotice;
    }
    /**
     * @return the priority
     */
    @Column(name = "PRIORITY")
    public int getPriority() {
        return priority;
    }
    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
}
