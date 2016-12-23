package com.ibusiness.base.group.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * JobTitle 职位名称管理表.
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_JOB_TITLE")
public class JobTitle implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private String id;

    /** null. */
    private String name;

    /** null. */
    private String scopeid;
    
    private Integer orders;

    /** . */
    private Set<JobInfo> jobInfos = new HashSet<JobInfo>(0);

    public JobTitle() {
    }

    public JobTitle(String name, String scopeId, Integer orders, Set<JobInfo> jobInfos) {
        this.name = name;
        this.scopeid = scopeId;
        this.jobInfos = jobInfos;
        this.orders = orders;
    }

    /** @return null. */
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public String getId() {
        return this.id;
    }
    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
    /** @return null. */
    @Column(name = "NAME", length = 50)
    public String getName() {
        return this.name;
    }
    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /** @return null. */
    @Column(name = "SCOPE_ID", length = 50)
    public String getScopeid() {
        return this.scopeid;
    }
    /**
     * @param scopeId
     *            null.
     */
    public void setScopeid(String scopeid) {
        this.scopeid = scopeid;
    }
    /** @return . */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jobTitle")
    public Set<JobInfo> getJobInfos() {
        return this.jobInfos;
    }
    /**
     * @param jobInfos
     */
    public void setJobInfos(Set<JobInfo> jobInfos) {
        this.jobInfos = jobInfos;
    }
    @Column(name = "ORDERS")
    public Integer getOrders() {
        return orders;
    }
    public void setOrders(Integer orders) {
        this.orders = orders;
    }
}
