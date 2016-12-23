package com.ibusiness.base.group.entity;

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

import com.ibusiness.base.user.entity.UserBase;

/**
 * JobInfo 职务管理表.
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_JOB_INFO")
public class JobInfo implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private String id;

    /** null. */
    private JobType jobType;

    /** null. */
    private JobTitle jobTitle;

    /** null. */
    private String name;

    /** null. */
    private String scopeid;

    /** . */
    private Set<UserBase> userBases = new HashSet<UserBase>(0);

    public JobInfo() {
    }

    public JobInfo(JobType jobType, JobTitle jobTitle, String name, String scopeId, Set<UserBase> userBases) {
        this.jobType = jobType;
        this.jobTitle = jobTitle;
        this.name = name;
        this.scopeid = scopeId;
        this.userBases = userBases;
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
    @JoinColumn(name = "TYPE_ID")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TITLE_ID")
    public JobTitle getJobTitle() {
        return this.jobTitle;
    }

    /**
     * @param jobTitle
     *            null.
     */
    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jobInfo")
    public Set<UserBase> getUserBases() {
        return this.userBases;
    }

    /**
     * @param jobUsers
     *            .
     */
    public void setUserBases(Set<UserBase> userBases) {
        this.userBases = userBases;
    }
}
