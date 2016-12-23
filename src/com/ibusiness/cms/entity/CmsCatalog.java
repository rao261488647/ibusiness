package com.ibusiness.cms.entity;

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
 * CMS 公告栏目表.
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_CMS_CATALOG")
public class CmsCatalog implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private String id;

    /** null. */
    private CmsCatalog cmsCatalog;

    /** null. */
    private String name;

    /** null. */
    private String code;

    /** null. */
    private String logo;

    /** null. */
    private Integer catalogType;

    /** null. */
    private String templateIndex;

    /** null. */
    private String templateList;

    /** null. */
    private String templateDetail;

    /** null. */
    private String description;
    // 范围
    private String scopeid;

    /** . */
    private Set<CmsCatalog> cmsCatalogs = new HashSet<CmsCatalog>(0);

    /** . */
    private Set<CmsArticle> cmsArticles = new HashSet<CmsArticle>(0);

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
    @Column(name = "CODE", length = 200)
    public String getCode() {
        return this.code;
    }

    /**
     * @param code
     *            null.
     */
    public void setCode(String code) {
        this.code = code;
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
    @Column(name = "CATALOGTYPE")
    public Integer getCatalogType() {
        return this.catalogType;
    }

    /**
     * @param type
     *            null.
     */
    public void setCatalogType(Integer catalogType) {
        this.catalogType = catalogType;
    }

    /** @return null. */
    @Column(name = "TEMPLATE_INDEX", length = 200)
    public String getTemplateIndex() {
        return this.templateIndex;
    }

    /**
     * @param templateIndex
     *            null.
     */
    public void setTemplateIndex(String templateIndex) {
        this.templateIndex = templateIndex;
    }

    /** @return null. */
    @Column(name = "TEMPLATE_LIST", length = 200)
    public String getTemplateList() {
        return this.templateList;
    }

    /**
     * @param templateList
     *            null.
     */
    public void setTemplateList(String templateList) {
        this.templateList = templateList;
    }

    /** @return null. */
    @Column(name = "TEMPLATE_DETAIL", length = 200)
    public String getTemplateDetail() {
        return this.templateDetail;
    }

    /**
     * @param templateDetail
     *            null.
     */
    public void setTemplateDetail(String templateDetail) {
        this.templateDetail = templateDetail;
    }

    /** @return null. */
    @Column(name = "DESCRIPTION", length = 200)
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description
     *            null.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /** @return . */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cmsCatalog")
    public Set<CmsCatalog> getCmsCatalogs() {
        return this.cmsCatalogs;
    }

    /**
     * @param cmsCatalogs
     *            .
     */
    public void setCmsCatalogs(Set<CmsCatalog> cmsCatalogs) {
        this.cmsCatalogs = cmsCatalogs;
    }

    /** @return . */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cmsCatalog")
    public Set<CmsArticle> getCmsArticles() {
        return this.cmsArticles;
    }
    /**
     * @param cmsArticles
     */
    public void setCmsArticles(Set<CmsArticle> cmsArticles) {
        this.cmsArticles = cmsArticles;
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
