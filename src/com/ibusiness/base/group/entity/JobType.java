package com.ibusiness.base.group.entity;

// Generated by Hibernate Tools
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * JobType 职务类型表.
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_JOB_TYPE")
public class JobType implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private String id;

    /** null. */
    private JobType jobType;

    /** null. */
    private String name;

    /** null. */
    private String scopeid;

    /** . */
    private Set<JobInfo> jobInfos = new HashSet<JobInfo>(0);

    /** . */
    private Set<JobType> jobTypes = new HashSet<JobType>(0);

    public JobType() {
    }

    public JobType(JobType jobType, String name, String scopeId,
            Set<JobInfo> jobInfos, Set<JobType> jobTypes) {
        this.jobType = jobType;
        this.name = name;
        this.scopeid = scopeId;
        this.jobInfos = jobInfos;
        this.jobTypes = jobTypes;
    }

    /** @return null. */
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public String getId() {
        return this.id;
    }

    /**
     * @param id
     *            null.
     */
    public void setId(String id) {
        this.id = id;
    }

    /** @return null. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    public JobType getJobType() {
        return this.jobType;
    }

    /**
     * @param jobType
     *            null.
     */
    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    /** @return null. */
    @Column(name = "NAME", length = 50)
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            null.
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jobType")
    public Set<JobInfo> getJobInfos() {
        return this.jobInfos;
    }

    /**
     * @param jobInfos
     *            .
     */
    public void setJobInfos(Set<JobInfo> jobInfos) {
        this.jobInfos = jobInfos;
    }

    /** @return . */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jobType")
    public Set<JobType> getJobTypes() {
        return this.jobTypes;
    }

    /**
     * @param jobTypes
     *            .
     */
    public void setJobTypes(Set<JobType> jobTypes) {
        this.jobTypes = jobTypes;
    }
}
