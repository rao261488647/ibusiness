package com.ibusiness.bpm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 流程节点人员(参与者)配置表
 * 
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_BPM_NODE_USER")
public class BpmNodeUser {
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
    // 负责人
    private String userValue;
    // 类型
    private String userType;
    // 状态
    private String userStatus;
    // 优先级
    private Integer priority;
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
     * @return the userValue
     */
    @Column(name = "USERVALUE")
    public String getUserValue() {
        return userValue;
    }
    /**
     * @param userValue the userValue to set
     */
    public void setUserValue(String userValue) {
        this.userValue = userValue;
    }
    /**
     * @return the userType
     */
    @Column(name = "USERTYPE")
    public String getUserType() {
        return userType;
    }
    /**
     * @param userType the userType to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
    /**
     * @return the userStatus
     */
    @Column(name = "USERSTATUS")
    public String getUserStatus() {
        return userStatus;
    }
    /**
     * @param userStatus the userStatus to set
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
    /**
     * @return the priority
     */
    @Column(name = "PRIORITY")
    public Integer getPriority() {
        return priority;
    }
    /**
     * @param priority the priority to set
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
