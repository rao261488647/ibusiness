package com.ibusiness.base.auth.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.ibusiness.base.menu.entity.Menu;

/**
 * RoleDef 角色模板管理表.
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_AUTH_ROLE_DEF")
public class RoleDef implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private String id;

    /** null. */
    private String name;

    /** null. */
    private String descn;

    /** null. */
    private String scopeid;

    /** . */
    private Set<Perm> perms = new HashSet<Perm>(0);
    
    /** 菜单. */
    private Set<Menu> menus = new HashSet<Menu>(0);

    public RoleDef() {
    }

    public RoleDef(String name, String descn, String scopeId, Set<Perm> perms, Set<Menu> menus) {
        this.name = name;
        this.descn = descn;
        this.scopeid = scopeId;
        this.perms = perms;
        this.menus = menus;
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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "IB_AUTH_PERM_ROLE_DEF", joinColumns = { @JoinColumn(name = "ROLE_DEF_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "PERM_ID", nullable = false, updatable = false) })
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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "IB_MENU_ROLE_DEF", joinColumns = { @JoinColumn(name = "ROLE_DEF_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "MENU_ID", nullable = false, updatable = false) })
    public Set<Menu> getMenus() {
        return this.menus;
    }
    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
}
