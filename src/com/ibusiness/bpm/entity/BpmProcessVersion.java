package com.ibusiness.bpm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 流程版本管理
 * 
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_BPM_PROCESS_VERSION")
public class BpmProcessVersion {
    // UUID
    private String id;
    // 流程编号
    private String bpmProsessId;
    // 流程代码
    private String bpmProsessKey;
    // 流程版本
    private Integer bpmProsessVersion;
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
     * @return the bpmProsessId
     */
    @Column(name = "BPMPROSESSID")
    public String getBpmProsessId() {
        return bpmProsessId;
    }
    /**
     * @param bpmProsessId the bpmProsessId to set
     */
    public void setBpmProsessId(String bpmProsessId) {
        this.bpmProsessId = bpmProsessId;
    }
    /**
     * @return the bpmProsessKey
     */
    @Column(name = "BPMPROSESSKEY")
    public String getBpmProsessKey() {
        return bpmProsessKey;
    }
    /**
     * @param bpmProsessKey the bpmProsessKey to set
     */
    public void setBpmProsessKey(String bpmProsessKey) {
        this.bpmProsessKey = bpmProsessKey;
    }
    /**
     * @return the bpmProsessVersion
     */
    @Column(name = "BPMPROSESSVERSION")
    public Integer getBpmProsessVersion() {
        return bpmProsessVersion;
    }
    /**
     * @param bpmProsessVersion the bpmProsessVersion to set
     */
    public void setBpmProsessVersion(Integer bpmProsessVersion) {
        this.bpmProsessVersion = bpmProsessVersion;
    }
}
