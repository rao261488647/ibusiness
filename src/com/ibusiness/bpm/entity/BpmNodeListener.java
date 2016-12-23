package com.ibusiness.bpm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 流程节点事件配置表.
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_BPM_NODE_LISTENER")
public class BpmNodeListener implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    // 编号
    private String id;
    // 模块包名
    private String packageName;
    // 流程UUID
    private String flowId;
    // 流程版本编号
    private String flowVersionId;
    // 节点编号
    private String nodeId;
    // 事件监听器
    private String listenerValue;
    // 类型
    private Integer listenerType;
    // 状态
    private String listenerStatus;
    // 优先级
    private Integer priority;

    /**
     * @return
     */
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public String getId() {
        return this.id;
    }
    /**
     * 
     * @param id
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
     * @return the listenerValue
     */
    @Column(name = "LISTENERVALUE")
    public String getListenerValue() {
        return listenerValue;
    }
    /**
     * @param listenerValue the listenerValue to set
     */
    public void setListenerValue(String listenerValue) {
        this.listenerValue = listenerValue;
    }
    /**
     * @return the listenerType
     */
    @Column(name = "LISTENERTYPE")
    public Integer getListenerType() {
        return listenerType;
    }
    /**
     * @param listenerType the listenerType to set
     */
    public void setListenerType(Integer listenerType) {
        this.listenerType = listenerType;
    }
    /**
     * @return the listenerStatus
     */
    @Column(name = "LISTENERSTATUS")
    public String getListenerStatus() {
        return listenerStatus;
    }
    /**
     * @param listenerStatus the listenerStatus to set
     */
    public void setListenerStatus(String listenerStatus) {
        this.listenerStatus = listenerStatus;
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
