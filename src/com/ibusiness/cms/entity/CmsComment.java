package com.ibusiness.cms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CMS 公告文章评论表.
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_CMS_COMMENT")
public class CmsComment implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private String id;

    /** null. */
    private CmsArticle cmsArticle;
    /** null. */
    private String title;
    /** null. */
    private String content;
    /** null. */
    private Integer status;
    /** null. */
    private Date createTime;
    /** null. */
    private String userId;
    // 范围
    private String scopeid;

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
    @JoinColumn(name = "ARTICLE_ID")
    public CmsArticle getCmsArticle() {
        return this.cmsArticle;
    }

    /**
     * @param cmsArticle
     *            null.
     */
    public void setCmsArticle(CmsArticle cmsArticle) {
        this.cmsArticle = cmsArticle;
    }

    /** @return null. */
    @Column(name = "TITLE", length = 200)
    public String getTitle() {
        return this.title;
    }

    /**
     * @param title
     *            null.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /** @return null. */
    @Column(name = "CONTENT", length = 200)
    public String getContent() {
        return this.content;
    }

    /**
     * @param content
     *            null.
     */
    public void setContent(String content) {
        this.content = content;
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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME", length = 26)
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * @param createTime
     *            null.
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /** @return null. */
    @Column(name = "USER_ID", length = 200)
    public String getUserId() {
        return this.userId;
    }
    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
	/**
	 * @return the scopeid
	 */
    @Column(name = "SCOPEID")
	public String getScopeid() {
		return scopeid;
	}
	/**
	 * @param scopeid the scopeid to set
	 */
	public void setScopeid(String scopeid) {
		this.scopeid = scopeid;
	}
}
