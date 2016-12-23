package com.ibusiness.base.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Access 资源访问权限表.
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_AUTH_ACCESS")
public class Access implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private String id;

    /** null. */
    private Perm perm;

    /** null. */
    private String type;

    /** null. */
    private String value;

    /** null. */
    private Integer priority;

    /** null. */
    private String descn;

    /** null. */
    private String scopeid;
    /** 菜单 */
    private String menuId;

    public Access() {
    }

    public Access(Perm perm, String type, String value, Integer priority,
            String descn, String scopeId, String menuId) {
        this.perm = perm;
        this.type = type;
        this.value = value;
        this.priority = priority;
        this.descn = descn;
        this.scopeid = scopeId;
        this.menuId = menuId;
    }

    /** @return null. */
    @Id
    @GeneratedValue
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
    @JoinColumn(name = "PERM_ID")
    public Perm getPerm() {
        return this.perm;
    }

    /**
     * @param perm
     *            null.
     */
    public void setPerm(Perm perm) {
        this.perm = perm;
    }

    /** @return null. */
    @Column(name = "TYPE", length = 50)
    public String getType() {
        return this.type;
    }

    /**
     * @param type
     *            null.
     */
    public void setType(String type) {
        this.type = type;
    }

    /** @return null. */
    @Column(name = "VALUE", length = 200)
    public String getValue() {
        return this.value;
    }

    /**
     * @param value
     *            null.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /** @return null. */
    @Column(name = "PRIORITY")
    public Integer getPriority() {
        return this.priority;
    }

    /**
     * @param priority
     *            null.
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
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
     * @return the menuId
     */
    @Column(name = "MENU_ID")
    public String getMenuId() {
        return menuId;
    }

    /**
     * @param menuId the menuId to set
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
