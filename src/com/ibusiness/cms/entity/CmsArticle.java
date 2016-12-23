package com.ibusiness.cms.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CmsArticle 公告文章管理表.
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_CMS_ARTICLE")
public class CmsArticle implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** 编号. */
    private String id;

    /** null. */
    private CmsCatalog cmsCatalog;

    /** 标题. */
    private String title;

    /** null. */
    private String shortTitle;

    /** null. */
    private String subTitle;

    /** null. */
    private String content;

    /** null. */
    private String summary;

    /** null. */
    private String logo;

    /** null. */
    private String tags;

    /** null. */
    private Integer allowComment;

    /** null. */
    private Integer status;

    /** null. */
    private Date publishTime;

    /** null. */
    private Date closeTime;

    /** null. */
    private Integer articleType;

    /** null. */
    private Integer top;

    /** null. */
    private Integer weight;

    /** null. */
    private Date createTime;

    /** null. */
    private Integer viewCount;

    /** null. */
    private Long recommendId;

    /** null. */
    private Integer recommendStatus;

    /** null. */
    private String userId;
    // 范围
    private String scopeid;
    /** . */
    private Set<CmsComment> cmsComments = new HashSet<CmsComment>(0);

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
    @JoinColumn(name = "CATALOG_ID")
    public CmsCatalog getCmsCatalog() {
        return this.cmsCatalog;
    }

    /**
     * @param cmsCatalog
     *            null.
     */
    public void setCmsCatalog(CmsCatalog cmsCatalog) {
        this.cmsCatalog = cmsCatalog;
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
    @Column(name = "SHORT_TITLE", length = 200)
    public String getShortTitle() {
        return this.shortTitle;
    }

    /**
     * @param shortTitle
     *            null.
     */
    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    /** @return null. */
    @Column(name = "SUB_TITLE", length = 200)
    public String getSubTitle() {
        return this.subTitle;
    }

    /**
     * @param subTitle
     *            null.
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    /** @return null. */
    @Column(name = "CONTENT", length = 65535)
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
    @Column(name = "SUMMARY", length = 200)
    public String getSummary() {
        return this.summary;
    }

    /**
     * @param summary
     *            null.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /** @return null. */
    @Column(name = "LOGO", length = 200)
    public String getLogo() {
        return this.logo;
    }

    /**
     * @param logo
     *            null.
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /** @return null. */
    @Column(name = "TAGS", length = 200)
    public String getTags() {
        return this.tags;
    }

    /**
     * @param tags
     *            null.
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /** @return null. */
    @Column(name = "ALLOW_COMMENT")
    public Integer getAllowComment() {
        return this.allowComment;
    }

    /**
     * @param allowComment
     *            null.
     */
    public void setAllowComment(Integer allowComment) {
        this.allowComment = allowComment;
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
    @Column(name = "PUBLISH_TIME", length = 26)
    public Date getPublishTime() {
        return this.publishTime;
    }

    /**
     * @param publishTime
     *            null.
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /** @return null. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CLOSE_TIME", length = 26)
    public Date getCloseTime() {
        return this.closeTime;
    }

    /**
     * @param closeTime
     *            null.
     */
    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    /** @return null. */
    @Column(name = "ARTICLETYPE")
    public Integer getArticleType() {
        return this.articleType;
    }

    /**
     * @param type
     *            null.
     */
    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }

    /** @return null. */
    @Column(name = "TOP")
    public Integer getTop() {
        return this.top;
    }

    /**
     * @param top
     *            null.
     */
    public void setTop(Integer top) {
        this.top = top;
    }

    /** @return null. */
    @Column(name = "WEIGHT")
    public Integer getWeight() {
        return this.weight;
    }

    /**
     * @param weight
     *            null.
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
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
    @Column(name = "VIEW_COUNT")
    public Integer getViewCount() {
        return this.viewCount;
    }

    /**
     * @param viewCount
     *            null.
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /** @return null. */
    @Column(name = "RECOMMEND_ID")
    public Long getRecommendId() {
        return this.recommendId;
    }

    /**
     * @param recommendId
     *            null.
     */
    public void setRecommendId(Long recommendId) {
        this.recommendId = recommendId;
    }

    /** @return null. */
    @Column(name = "RECOMMEND_STATUS")
    public Integer getRecommendStatus() {
        return this.recommendStatus;
    }

    /**
     * @param recommendStatus
     *            null.
     */
    public void setRecommendStatus(Integer recommendStatus) {
        this.recommendStatus = recommendStatus;
    }

    /** @return null. */
    @Column(name = "USER_ID", length = 200)
    public String getUserId() {
        return this.userId;
    }

    /**
     * @param userId
     *            null.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /** @return . */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cmsArticle")
    public Set<CmsComment> getCmsComments() {
        return this.cmsComments;
    }
    /**
     * @param cmsComments
     */
    public void setCmsComments(Set<CmsComment> cmsComments) {
        this.cmsComments = cmsComments;
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
