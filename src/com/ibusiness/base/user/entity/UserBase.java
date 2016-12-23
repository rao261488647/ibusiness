package com.ibusiness.base.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ibusiness.base.auth.entity.RoleDef;
import com.ibusiness.base.group.entity.JobInfo;

/**
 * UserBase用户表.
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_USER_BASE")
public class UserBase implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** 编号. */
    private String id;
    /** 帐号 */
    private String username;
    /** 显示名 */
    private String displayName;
    /** 密码 */
    private String password;
    // 设备型号
    private String device;
    // 设备唯一标识
    private String imei;
    /** 状态. */
    private Integer status;
    /** 引用排序 */
    private Integer ref;
    /** 多租户范围. */
    private String scopeid;
    /** 邮箱. */
    private String email;
    /** 手机. */
    private String mobile;
    /** 职务管理表. */
    private JobInfo jobInfo;
    /** 角色模板管理表. */
    private RoleDef roleDef;
    /** 对应页面的css样式 */
    private String css;
    /** 公司编号. */
    private String companyId;
    /** 公司名称 */
    private String companyName;
    /** 用户库列表ID  -- 准备废弃字段 */
    private String userRepoId;
    // 个性签名
    private String signMsg;
    // 上传照片
    private String photoFile;

    public UserBase() {
    }
    public UserBase(String userRepoId, String username, String displayName,
            String password, Integer status, Integer ref, String scopeId,
            String email, String mobile, JobInfo jobInfo, String css, RoleDef roleDef) {
        this.userRepoId = userRepoId;
        this.username = username;
        this.displayName = displayName;
        this.password = password;
        this.status = status;
        this.ref = ref;
        this.scopeid = scopeId;
        this.email = email;
        this.mobile = mobile;
        this.jobInfo = jobInfo;
        this.css = css;
        this.roleDef = roleDef;
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
    /**
	 * @return the userRepoId
	 */
    @Column(name = "USER_REPO_ID")
	public String getUserRepoId() {
		return userRepoId;
	}
	/**
	 * @param userRepoId the userRepoId to set
	 */
	public void setUserRepoId(String userRepoId) {
		this.userRepoId = userRepoId;
	}
	/** @return null. */
    @Column(name = "USERNAME", length = 50)
    public String getUsername() {
        return this.username;
    }
    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /** @return null. */
    @Column(name = "DISPLAY_NAME", length = 50)
    public String getDisplayName() {
        return this.displayName;
    }
    /**
     * @param displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    /** @return null. */
    @Column(name = "PASSWORD", length = 50)
    public String getPassword() {
        return this.password;
    }
    /**
     * @param password
     *            null.
     */
    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "REF")
    public Integer getRef() {
        return this.ref;
    }
    /**
     * @param ref
     */
    public void setRef(Integer ref) {
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

    /** @return null. */
    @Column(name = "EMAIL", length = 100)
    public String getEmail() {
        return this.email;
    }
    /**
     * @param email
     *            null.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /** @return null. */
    @Column(name = "MOBILE", length = 50)
    public String getMobile() {
        return this.mobile;
    }
    /**
     * @param mobile
     *            null.
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    /** @return null. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_INFO_ID")
    public JobInfo getJobInfo() {
        return this.jobInfo;
    }
    /**
     * @param jobInfo
     *            null.
     */
    public void setJobInfo(JobInfo jobInfo) {
        this.jobInfo = jobInfo;
    }
    /**
     * @return the css
     */
    @Column(name = "CSS", length = 64)
    public String getCss() {
        return css;
    }
    /**
     * @param css the css to set
     */
    public void setCss(String css) {
        this.css = css;
    }
    /**
     * @return the roleDef
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_DEF_ID")
    public RoleDef getRoleDef() {
        return roleDef;
    }
    /**
     * @param roleDef the roleDef to set
     */
    public void setRoleDef(RoleDef roleDef) {
        this.roleDef = roleDef;
    }
	/**
	 * @return the companyid
	 */
    @Column(name = "COMPANYID")
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyid the companyid to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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
	 * @return the signMsg
	 */
	@Column(name = "SIGNMSG")
	public String getSignMsg() {
		return signMsg;
	}
	/**
	 * @param signMsg the signMsg to set
	 */
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	/**
	 * @return the photoFile
	 */
	@Column(name = "PHOTOFILE")
	public String getPhotoFile() {
		return photoFile;
	}
	/**
	 * @param photoFile the photoFile to set
	 */
	public void setPhotoFile(String photoFile) {
		this.photoFile = photoFile;
	}
	@Column(name = "DEVICE")
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	@Column(name = "IMEI")
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
}
