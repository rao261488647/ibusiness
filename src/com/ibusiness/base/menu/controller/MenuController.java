package com.ibusiness.base.menu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.base.auth.dao.AccessDao;
import com.ibusiness.base.auth.dao.PermDao;
import com.ibusiness.base.auth.dao.RoleDefDao;
import com.ibusiness.base.auth.entity.Access;
import com.ibusiness.base.auth.entity.Perm;
import com.ibusiness.base.auth.entity.RoleDef;
import com.ibusiness.base.menu.dao.MenuDao;
import com.ibusiness.base.menu.dao.MenuRoleDefDao;
import com.ibusiness.base.menu.entity.Menu;
import com.ibusiness.base.menu.entity.MenuRoleDef;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.core.spring.MessageHelper;

/**
 * 菜单控制器
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("menu")
public class MenuController {

    private MenuDao menuDao;
    private RoleDefDao roleDefDao;
    private MenuRoleDefDao menuRoleDefDao;
    private BeanMapper beanMapper = new BeanMapper();
    // 权限相关
    private AccessDao accessDao;
    private MessageHelper messageHelper;
    private PermDao permDao;
    
    /**
     * 菜单列表
     * 
     * @param page
     * @param parameterMap
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("menu-list")
    public String menuList(@ModelAttribute Page page, @RequestParam("menuLevel") String menuLevel, @RequestParam("menuLevelOne") String menuLevelOne, @RequestParam("menuLevelTwo") String menuLevelTwo, @RequestParam Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        propertyFilters.add(new PropertyFilter("EQS_menuLevel", menuLevel));
        if ("2".equals(menuLevel)) {
            propertyFilters.add(new PropertyFilter("EQS_ibMenu.id", menuLevelOne));
        } else if ("3".equals(menuLevel)) {
            propertyFilters.add(new PropertyFilter("EQS_ibMenu.id", menuLevelTwo));
        }
        // 设置排序信息
        page.setOrderBy("menuOrder");
        page.setOrder("ASC");
        // 查询
        page = menuDao.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        model.addAttribute("menuLevel", menuLevel);
        model.addAttribute("menuLevelOne", menuLevelOne);
        model.addAttribute("menuLevelTwo", menuLevelTwo);
        // 一级菜单下拉列表
        List<Menu> menuList = menuDao.find("from Menu where menuLevel = '1' order by menuOrder");
        model.addAttribute("levelOneInfos", menuList);
        // 二级菜单下拉列表
        List<Menu> menu2List = menuDao.find("from Menu where PARENTID = '" + menuLevelOne + "' and ID != '0' order by menuOrder");
        model.addAttribute("levelTwoInfos", menu2List);
        
        return "common/menu/menu-list.jsp";
    }
    
    /**
     * ajax二三级菜单联动
     * @param menuID
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("menu-getlevelTwo")
    @ResponseBody
    public String getlevelTwo(@RequestParam(value = "menuleveloneID") String menuleveloneID){
    	StringBuffer sb = new StringBuffer();
    	//根据上级select的ID,查找下级菜单的数据
    	List<Menu> menuList = menuDao.find("from Menu where PARENTID = '" + menuleveloneID + "'order by menuOrder");
    	if(menuList != null && menuList.size() > 0){
    		Menu menu;
    		String selected;
    		//遍历菜单项，构建select列表
    		for(int i = 0; i < menuList.size(); i++){
    			menu = menuList.get(i);
    			selected = menu.getId().equals(menuleveloneID) ? "selected" : "";
    			sb.append("<option value='"+menu.getId()+"' "+selected+">"+menu.getMenuName()+"</option>");
    		}
    		
    		return sb.toString();
    	}
    	//没数据显示“请选择”
    	return "<option value=''>请选择</option>";
    }
    
    /**
     * 新建/修改 菜单信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("menu-input")
    public String input(@RequestParam(value = "id", required = false) String id,
            @RequestParam("menuLevel") String menuLevel,@RequestParam("menuLevelOne") String menuLevelOne,@RequestParam("menuLevelTwo") String menuLevelTwo, Model model) {
        if (id != null) {
            Menu menu = menuDao.get(id);
            model.addAttribute("model", menu);
            model.addAttribute("parentId",menu.getIbMenu().getId());
        } else {
            // 新建
            Menu menu = new Menu();
            menu.setMenuLevel(menuLevel);
            // 二级菜单的父菜单ID是一级菜单的ID,三级是二级的,一级菜单的父菜单ID是0
            model.addAttribute("parentId", "2".equals(menuLevel) ? menuLevelOne : ("3".equals(menuLevel) ? menuLevelTwo: "0"));
            model.addAttribute("model", menu);
        }
        return "common/menu/menu-input.jsp";
    }
    
    /**
     * 保存
     * 
     * @param access
     * @param permId
     * @param redirectAttributes
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("menu-save")
    public String save(@ModelAttribute Menu menu, @RequestParam("parentId") String parentId, RedirectAttributes redirectAttributes) {
        // copy
        Menu dest = null;
        String id = menu.getId();
        // 更新
        if (id != null && !"".equals(id)) {
            dest = menuDao.get(id);
            List<MenuRoleDef> menuRoleDefList = menuRoleDefDao.find("from MenuRoleDef where menuId=?", dest.getId());
            List<RoleDef> roleDefList = new ArrayList<RoleDef>();
            for (MenuRoleDef menuRoleDef : menuRoleDefList) {
                RoleDef roleDef = roleDefDao.get(menuRoleDef.getRoleDefId());
                roleDefList.add(roleDef);
            }
            
            // 设置权限
            dest.getRoleDefs();
            beanMapper.copy(menu, dest);
            // 
            dest.setMenuIframe("URL");
            if (CommonUtils.isNull(dest.getIconUrl())) {
                dest.setIconUrl("imac/img/Appstore.png");
            }
            if (roleDefList.size() > 0) {
                for (RoleDef roleDef : roleDefList) {
                    dest.getRoleDefs().add(roleDef);
                }
            }
            menuDao.update(dest);
            
        } else {
            // 新建
            dest = menu;
            dest.setId(UUID.randomUUID().toString());
            dest.setIbMenu(menuDao.get(parentId));
            // 
            dest.setMenuIframe("URL");
            if (CommonUtils.isNull(dest.getIconUrl())) {
                dest.setIconUrl("imac/img/Appstore.png");
            }
            // save
            menuDao.save(dest);
        }
        
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        
        if ("3".equals(dest.getMenuLevel())) {
            String menuLevelOne = menuDao.get(parentId).getIbMenu().getId();
            return "redirect:/menu/menu-list.do?menuLevel=3&menuLevelOne=" + menuLevelOne + "&menuLevelTwo=" + dest.getIbMenu().getId();
        } else if ("2".equals(dest.getMenuLevel())) {
            return "redirect:/menu/menu-list.do?menuLevel=2&menuLevelOne=" + parentId + "&menuLevelTwo=0";
        } else {
            return "redirect:/menu/menu-list.do?menuLevel=1&menuLevelOne=0&menuLevelTwo=0";
        }
    }
    /**
     * 删除
     * 
     * @param selectedItem
     * @param menuLevel
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("menu-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<Menu> menus = menuDao.findByIds(selectedItem);
        // 删除菜单
        Menu menu = menus.get(0);
        removeMenu(menus);
        
        if ("3".equals(menu.getMenuLevel())) {
            String menuLevelOne = menuDao.get(menu.getIbMenu().getId()).getIbMenu().getId();
            return "redirect:/menu/menu-list.do?menuLevel=3&menuLevelOne=" + menuLevelOne + "&menuLevelTwo=" + menu.getIbMenu().getId();
        } else if ("2".equals(menu.getMenuLevel())) {
            return "redirect:/menu/menu-list.do?menuLevel=2&menuLevelOne=" + menu.getIbMenu().getId() + "&menuLevelTwo=0";
        } else {
            return "redirect:/menu/menu-list.do?menuLevel=1&menuLevelOne=0&menuLevelTwo=0";
        }
    }
    /**
     * 删除菜单
     * 
     * @param menus
     * @return
     */
    @SuppressWarnings("unchecked")
    private void removeMenu(List<Menu> menus) {
        menuDao.removeAll(menus);
        for (Menu bean : menus) {
            String hql = "from Menu Where ibMenu.id=?";
            List<Menu> chiledItems = menuDao.find(hql, bean.getId());
            if (null != chiledItems) {
                removeMenu(chiledItems);
            }
            // 资源
            List<Access> accesses = accessDao.find("from Access where menuId = ?", bean.getId());
            if (null != accesses) {
                accessDao.removeAll(accesses);
            }
            // 权限
            List<Perm> perms = permDao.find("from Perm where code = ?", bean.getId());
            accessDao.removeAll(perms);
        }
    }
    // ======================================================================
    // 菜单
    @Resource
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }
    // 菜单权限
    @Resource
    public void setMenuRoleDefDao(MenuRoleDefDao menuRoleDefDao) {
        this.menuRoleDefDao = menuRoleDefDao;
    }
    @Resource
    public void setRoleDefDao(RoleDefDao roleDefDao) {
        this.roleDefDao = roleDefDao;
    }
    // 权限相关
    @Resource
    public void setAccessDao(AccessDao accessDao) {
        this.accessDao = accessDao;
    }
    @Resource
    public void setPermDao(PermDao permDao) {
        this.permDao = permDao;
    }
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
