package com.ibusiness.common.service;

import java.util.List;
import java.util.UUID;

import com.ibusiness.base.menu.dao.MenuDao;
import com.ibusiness.base.menu.dao.MenuRoleDefDao;
import com.ibusiness.base.menu.entity.Menu;
import com.ibusiness.base.menu.entity.MenuRoleDef;
import com.ibusiness.component.portal.dao.ComponentDao;
import com.ibusiness.component.portal.entity.ConfComponent;
import com.ibusiness.core.spring.ApplicationContextHelper;

/**
 * BPM流程共用类
 * 
 * @author JiangBo
 *
 */
public class MenuCommon {
    /**
     * 生成菜单
     * 
     * @param formId
     * @param menuUrl
     */
    @SuppressWarnings({"unchecked"})
    public void createMenu(String packageName, String formTitle, String menuUrl) {
        // 取得业务模块组件管理信息,取得菜单名
        String hql = "from ConfComponent where packagename=?";
        List<ConfComponent> list = getComponentDao().find(hql, packageName);
        if (null != list && list.size() > 0) {
            // 菜单名称
            String menuName = list.get(0).getModulename();
            // 一级菜单
            Menu menu = saveMenu(menuName, "#", "1", null);
            // 二级菜单
            Menu menu2 = saveMenu(list.get(0).getModulename(), "#", "2", menu);
            // 三级菜单
            saveMenu(formTitle, menuUrl, "3", menu2);
        }
    }
    /**
     * 保存菜单和菜单授权信息
     * 
     * @param confForm
     * @param menuUrl
     * @param parentMenu
     */
    @SuppressWarnings("unchecked")
    private Menu saveMenu(String menuName, String menuUrl, String menuLevel, Menu parentMenu) {
        String menuHql = "from Menu where menuName=? ";
        List<Menu> menuList= null;
        if ("1".equals(menuLevel)) {
            menuHql = menuHql + " AND menuLevel=? ";
            menuList= getMenuDao().find(menuHql, menuName, menuLevel);
        } else {
            menuHql = menuHql + " AND ibMenu.id=?  AND menuLevel=? ";
            menuList= getMenuDao().find(menuHql, menuName, parentMenu.getId(), menuLevel);
        }
        Menu menu = new Menu();
        if (null != menuList && menuList.size() > 0) {
            menu = menuList.get(0);
        } else {
            menu.setId(UUID.randomUUID().toString());//菜单编号
            menu.setMenuName(menuName);//菜单名称
            menu.setMenuLevel(menuLevel);//菜单等级
            menu.setMenuUrl(menuUrl);//菜单地址
            menu.setMenuIframe("URL");//菜单地址打开方式
            menu.setMenuOrder(9);//菜单排序
            menu.setDesktopIcon("0");//是否桌面显示
            if ("1".equals(menuLevel)) {
                Menu ibMenu = new Menu();
                ibMenu.setId("0");
                menu.setIbMenu(ibMenu);//父菜单
            } else {
                menu.setIbMenu(parentMenu);//父菜单
            }
            getMenuDao().save(menu);
        }
        // 自动授权
        String menuRoleDefHql="from MenuRoleDef where menuId=? AND roleDefId=2";
        List<MenuRoleDef> menuRoleDefList = getMenuRoleDefDao().find(menuRoleDefHql, menu.getId());
        if(null == menuRoleDefList || menuRoleDefList.size() < 1) {
            MenuRoleDef menuRoleDef = new MenuRoleDef();
            menuRoleDef.setMenuId(menu.getId());
            menuRoleDef.setRoleDefId("2");// 系统管理角色
            getMenuRoleDefDao().saveInsert(menuRoleDef);
        }
        return menu;
    }
    // ======================================================================
    public ComponentDao getComponentDao() {
        return ApplicationContextHelper.getBean(ComponentDao.class);
    }
    public MenuDao getMenuDao() {
        return ApplicationContextHelper.getBean(MenuDao.class);
    }
    public MenuRoleDefDao getMenuRoleDefDao() {
        return ApplicationContextHelper.getBean(MenuRoleDefDao.class);
    }
}
