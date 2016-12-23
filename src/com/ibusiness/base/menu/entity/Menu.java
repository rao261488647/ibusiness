package com.ibusiness.base.menu.entity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ibusiness.base.auth.entity.RoleDef;

/**
 *菜单权限表
 * @author 
 */
@Entity
@Table(name = "IB_MENU")
public class Menu implements java.io.Serializable {
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private Menu ibMenu;//父菜单
    private String id;//菜单编号
	private String menuName;//菜单名称
	private String menuLevel;//菜单等级
	private String menuUrl;//菜单地址
	private String menuIframe;//菜单地址打开方式
	private Integer menuOrder;//菜单排序
	private String desktopIcon;//是否桌面显示
	private String iconUrl;//对应图标
	private String dataCount;// 数据条数(主页面显示用)
	
	// 一个父菜单项目对应多个叶子菜单项目
	private List<Menu> chiledItems = new ArrayList<Menu>();
	/** 角色. */
    private Set<RoleDef> roleDefs = new HashSet<RoleDef>(0);
    
	// 
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentid")
	public Menu getIbMenu() {
		return this.ibMenu;
	}

	public void setIbMenu(Menu ibMenu) {
		this.ibMenu = ibMenu;
	}
	@Id
	@Column(name = "id", nullable = false)
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
	@Column(name = "menuname", nullable = false, length = 50)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "menulevel")
	public String getMenuLevel() {
		return this.menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	@Column(name = "menuurl", length = 100)
	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	@Column(name = "menuorder")
	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}
	@Column(name = "menuiframe")
	public String getMenuIframe() {
		return menuIframe;
	}
	public void setMenuIframe(String menuIframe) {
		this.menuIframe = menuIframe;
	}
    @Column(name = "DESKTOPICON")
    public String getDesktopIcon() {
        return desktopIcon;
    }
    @Column(name = "ICONURL")
    public void setDesktopIcon(String desktopIcon) {
        this.desktopIcon = desktopIcon;
    }
    public String getIconUrl() {
        return iconUrl;
    }
    /**
     * @param iconUrl the iconUrl to set
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @OneToMany(mappedBy="ibMenu")
    public List<Menu> getChiledItems() {
        return this.chiledItems;
    }
    public void setChiledItems(List<Menu> chiledItems) {
        this.chiledItems = chiledItems;
    }
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "IB_MENU_ROLE_DEF", joinColumns = { @JoinColumn(name = "MENU_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ROLE_DEF_ID", nullable = false, updatable = false) })
    public Set<RoleDef> getRoleDefs() {
        return this.roleDefs;
    }
    public void setRoleDefs(Set<RoleDef> roleDefs) {
        this.roleDefs = roleDefs;
    }
    /**
     * 临时
     */
    @Transient
    public String getDataCount() {
        return dataCount;
    }
    public void setDataCount(String dataCount) {
        this.dataCount = dataCount;
    }
}