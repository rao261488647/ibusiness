package com.ibusiness.bpm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 流程节点(会签)关联配置表
 * 
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_BPM_NODE_COUNTERSIGN")
public class BpmNodeCountersign {
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
    // 次序
    private Integer sequential;
    // 参与者
    private String participant;
    // 类型
    private String countersignType;
    // 比率
    private Integer countersignRate;
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
     * @return the sequential
     */
    @Column(name = "SEQUENTIAL")
    public Integer getSequential() {
        return sequential;
    }
    /**
     * @param sequential the sequential to set
     */
    public void setSequential(Integer sequential) {
        this.sequential = sequential;
    }
    /**
     * @return the participant
     */
    @Column(name = "PARTICIPANT")
    public String getParticipant() {
        return participant;
    }
    /**
     * @param participant the participant to set
     */
    public void setParticipant(String participant) {
        this.participant = participant;
    }
    /**
     * @return the countersignType
     */
    @Column(name = "COUNTERSIGNTYPE")
    public String getCountersignType() {
        return countersignType;
    }
    /**
     * @param countersignType the countersignType to set
     */
    public void setCountersignType(String countersignType) {
        this.countersignType = countersignType;
    }
    /**
     * @return the countersignRate
     */
    @Column(name = "COUNTERSIGNRATE")
    public Integer getCountersignRate() {
        return countersignRate;
    }
    /**
     * @param countersignRate the countersignRate to set
     */
    public void setCountersignRate(Integer countersignRate) {
        this.countersignRate = countersignRate;
    }
}
