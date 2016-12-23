package com.ibusiness.bpm.entity;

import java.util.Date;

/**
 * 代办流程信息列表用bean
 * @author JiangBo
 *
 */
public class BpmWorkspaceTask {
    // 编号
    private String id;
    // 节点名称
    private String name;
    // 创建时间
    private Date createTime;
    // 开始时间 -- 已办
    private Date startTime;
    // 结束时间 -- 已办
    private Date endTime;
    // 处理结果 -- 已办
    private String deleteReason;
    // 负责人
    private String assignee;
    // 状态
    private Boolean suspended;
    private String processInstanceId;
    // 流程URL
    private String flowUrl;
    // 流程名
    private String flowTitle;
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
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * @return the assignee
     */
    public String getAssignee() {
        return assignee;
    }
    /**
     * @param assignee the assignee to set
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    /**
     * @return the suspended
     */
    public Boolean getSuspended() {
        return suspended;
    }
    /**
     * @param suspended the suspended to set
     */
    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }
    /**
     * @return the processInstanceId
     */
    public String getProcessInstanceId() {
        return processInstanceId;
    }
    /**
     * @param processInstanceId the processInstanceId to set
     */
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
    /**
     * @return the flowUrl
     */
    public String getFlowUrl() {
        return flowUrl;
    }
    /**
     * @param flowUrl the flowUrl to set
     */
    public void setFlowUrl(String flowUrl) {
        this.flowUrl = flowUrl;
    }
    /**
     * @return the flowName
     */
    public String getFlowTitle() {
        return flowTitle;
    }
    /**
     * @param flowName the flowName to set
     */
    public void setFlowTitle(String flowTitle) {
        this.flowTitle = flowTitle;
    }
    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }
    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }
    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    /**
     * @return the deleteReason
     */
    public String getDeleteReason() {
        return deleteReason;
    }
    /**
     * @param deleteReason the deleteReason to set
     */
    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }
}
