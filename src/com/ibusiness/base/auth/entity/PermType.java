package com.ibusiness.base.auth.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * PermType 授权类型表.
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_AUTH_PERM_TYPE")
public class PermType implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private String id;

    /** null. */
    private String name;

    /** null. */
    private Integer type;

    /** null. */
    private Integer priority;

    /** null. */
    private String descn;

    /** null. */
    private String scopeid;

    /** . */
    private Set<Perm> perms = new HashSet<Perm>(0);

    public PermType() {
    }

    public PermType(String name, Integer type, Integer priority, String descn,
            String scopeId, Set<Perm> perms) {
        this.name = name;
        this.type = type;
        this.priority = priority;
        this.descn = descn;
        this.scopeid = scopeId;
        this.perms = perms;
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
    @Column(name = "TYPE")
    public Integer getType() {
        return this.type;
    }

    /**
     * @param type
     *            null.
     */
    public void setType(Integer type) {
        this.type = type;
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

    /** @return . */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "permType")
    @OrderBy("priority")
    public Set<Perm> getPerms() {
        return this.perms;
    }

    /**
     * @param perms
     *            .
     */
    public void setPerms(Set<Perm> perms) {
        this.perms = perms;
    }
}
