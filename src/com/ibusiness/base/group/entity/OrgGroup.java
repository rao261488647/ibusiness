package com.ibusiness.base.group.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 小组实体Group .
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_GROUP")
public class OrgGroup implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** 编号. */
    private String id;

    /** 公司编号. */
    private String companyid;
    /** 公司名称 */
    private String companyName;
    /** 部门编号. */
    private String deptid;
    /** 部门名称 */
    private String deptName;
    /** 小组名称. */
    private String name;
    /** 备注. */
    private String descn;

    /** 状态. */
    private Integer status;

    /** null. */
    private String ref;

    /** null. */
    private String scopeid;

    public OrgGroup() {
    }

    public OrgGroup(String companyid, String deptid, String name, String descn, Integer status, String ref,
            String scopeId) {
        this.companyid = companyid;
        this.deptid = deptid;
        this.name = name;
        this.descn = descn;
        this.status = status;
        this.ref = ref;
        this.scopeid = scopeId;
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
    @Column(name = "NAME", length = 200)
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
    @Column(name = "DESCN", length = 200)
    public String getDescn() {
        return this.descn;
    }

    /**
     * @param descn
     *            null.
     */
    public void setDescn(String descn) {
        this.descn = descn;
    }

    /** @return null. */
    @Column(name = "STATUS")
    public Integer getStatus() {
        return this.status;
    }

    /**
     * @param status
     *            null.
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /** @return null. */
    @Column(name = "REF", length = 200)
    public String getRef() {
        return this.ref;
    }

    /**
     * @param ref
     *            null.
     */
    public void setRef(String ref) {
        this.ref = ref;
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

    /**
     * @return the companyid
     */
    public String getCompanyid() {
        return companyid;
    }

    /**
     * @param companyid the companyid to set
     */
    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }
    /**
     * @return the deptid
     */
    public String getDeptid() {
        return deptid;
    }
    /**
     * @param deptid the deptid to set
     */
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }
	/**
	 * @return the companyName
	 */
    @Transient
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the deptName
	 */
	@Transient
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
