package com.ibusiness.base.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SAAS多租户范围管理表.
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_SCOPE_INFO")
public class ScopeInfo implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
    // 范围编号
    private String id;
    // 范围名称
    private String name;
    // 范围编码 ---- 准备废弃
    private String code;
    // 共享
    private Integer shared;
    // 登录方式 ---- 准备废弃
    private String userRepoRef;
    // 类型
    private Integer type;

    @Id
    @Column(name = "ID", nullable=false, length=64)
    public String getId() {
        return this.id;
    }
    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
	/**
	 * @return the name
	 */
    @Column(name ="NAME")
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
	 * @return the cODE
	 */
	@Column(name ="CODE")
	public String getCode() {
		return code;
	}
	/**
	 * @param cODE the cODE to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the shared
	 */
	@Column(name ="SHARED")
	public Integer getShared() {
		return shared;
	}
	/**
	 * @param shared the shared to set
	 */
	public void setShared(Integer shared) {
		this.shared = shared;
	}
	/**
	 * @return the userRepoRef
	 */
	@Column(name ="USER_REPO_REF")
	public String getUserRepoRef() {
		return userRepoRef;
	}
	/**
	 * @param userRepoRef the userRepoRef to set
	 */
	public void setUserRepoRef(String userRepoRef) {
		this.userRepoRef = userRepoRef;
	}
	/**
	 * @return the type
	 */
	@Column(name ="TYPE")
	public Integer getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
}
