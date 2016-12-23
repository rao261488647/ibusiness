package com.ibusiness.base.menu.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 菜单和角色模板关联表
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_MENU_ROLE_DEF")
public class MenuRoleDef implements java.io.Serializable {
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private String menuId;//菜单编号
	private String roleDefId;//角色模板编号
    /**
     * @return the menuId
     */
	@Id
    @Column(name = "MENU_ID", nullable = false)
    public String getMenuId() {
        return menuId;
    }
    /**
     * @param menuId the menuId to set
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    /**
     * @return the roleDefId
     */
    @Id
    @Column(name = "ROLE_DEF_ID")
    public String getRoleDefId() {
        return roleDefId;
    }
    /**
     * @param roleDefId the roleDefId to set
     */
    public void setRoleDefId(String roleDefId) {
        this.roleDefId = roleDefId;
    }
}