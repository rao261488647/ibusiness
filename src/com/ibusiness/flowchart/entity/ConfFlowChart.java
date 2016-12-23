package com.ibusiness.flowchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IB_CONF_FLOW_CHART 流程图配置表
 * 
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_CONF_FLOW_CHART")
public class ConfFlowChart {
    // UUID
    private String id;
    // 流程ID
    private String flowId;
    // 图控件Id
    private String itemId;
    // 流程节点Id
    private String nodeId;
    // JSON内容
    private String context;
    
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
    @Column(name = "FLOWID")
    public String getFlowId() {
        return flowId;
    }
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }
    /**
     * @return the itemId
     */
    @Column(name = "ITEMID")
    public String getItemId() {
        return itemId;
    }
    /**
     * @param itemId the itemId to set
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    /**
     * @return the context
     */
    @Column(name = "CONTEXT")
    public String getContext() {
        return context;
    }
    /**
     * @param context the context to set
     */
    public void setContext(String context) {
        this.context = context;
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
}
