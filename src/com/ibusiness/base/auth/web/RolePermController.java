package com.ibusiness.base.auth.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.base.auth.component.CheckRoleException;
import com.ibusiness.base.auth.component.RoleDefChecker;
import com.ibusiness.base.auth.dao.AccessDao;
import com.ibusiness.base.auth.dao.PermDao;
import com.ibusiness.base.auth.dao.PermTypeDao;
import com.ibusiness.base.auth.dao.RoleDefDao;
import com.ibusiness.base.auth.entity.Access;
import com.ibusiness.base.auth.entity.Perm;
import com.ibusiness.base.auth.entity.PermType;
import com.ibusiness.base.auth.entity.RoleDef;
import com.ibusiness.base.menu.dao.MenuDao;
import com.ibusiness.base.menu.entity.Menu;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.security.api.scope.ScopeHolder;
import com.ibusiness.security.client.ResourcePublisher;

/**
 * 权限设置
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("auth")
public class RolePermController {
    private static Logger logger = LoggerFactory.getLogger(RolePermController.class);
    
    private PermDao permDao;
    private RoleDefDao roleDefDao;
    private PermTypeDao permTypeDao;
    private MessageHelper messageHelper;
    private RoleDefChecker roleDefChecker;
    private MenuDao menuDao;
    // 权限相关
    private AccessDao accessDao;
    private ResourcePublisher resourcePublisher;

    /**
     * 标签权限保存
     * 
     * @param id
     * @param model
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("role-perm-save")
    public String save(@RequestParam("id") String id, Model model, @RequestParam(value = "selectedItem", required = false)
    List<String> selectedItem, RedirectAttributes redirectAttributes) {
        if (selectedItem == null) {
            selectedItem = Collections.emptyList();
        }

        try {
            RoleDef roleDef = roleDefDao.get(id);
            roleDefChecker.check(roleDef);
            // 清空非menu类型的,类型编号!=7
            List<Perm> permList = permDao.find("from Perm where permType.id != ?", "7");
            for (Perm perm : permList) {
                roleDef.getPerms().remove(perm);
            }

            for (String permId : selectedItem) {
                Perm perm = permDao.get(permId);
                roleDef.getPerms().add(perm);
            }

            roleDefDao.save(roleDef);
            messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        } catch (CheckRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            messageHelper.addFlashMessage(redirectAttributes, ex.getMessage());

            return input(id, model);
        }

        return "redirect:/auth/role-perm-input.do?id=" + id;
    }

    /**
     * 标签权限设置
     * 
     * @param id
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("role-perm-input")
    public String input(@RequestParam("id") String id, Model model) {
        RoleDef roleDef = roleDefDao.get(id);
        List<String> selectedItem = new ArrayList<String>();

        for (Perm perm : roleDef.getPerms()) {
            selectedItem.add(perm.getId());
        }

        String hql = "from PermType where id!=7 and type=0 and scopeid=?";
        List<PermType> permTypes = permTypeDao.find(hql, ScopeHolder.getScopeId());
        
        model.addAttribute("permTypes", permTypes);
        model.addAttribute("selectedItem", selectedItem);
        model.addAttribute("id", id);

        return "common/auth/role-perm-input.jsp";
    }
    
    /**
     * 菜单权限设置
     * 
     * @param id
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("role-menu-input")
    public String menuinput(@RequestParam("id") String id, Model model) {
        RoleDef roleDef = roleDefDao.get(id);
        List<String> selectedItem = new ArrayList<String>();
        for (Menu menu : roleDef.getMenus()) {
            selectedItem.add(menu.getId());
        }
        String hql = "from Menu where menuLevel='1'";
        List<Menu> menus = menuDao.find(hql);
        model.addAttribute("menus", menus);
        model.addAttribute("selectedItem", selectedItem);
        model.addAttribute("id", id);
        return "common/auth/role-menu-input.jsp";
    }
    /**
     * 菜单权限保存
     * 
     * @param id
     * @param model
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("role-menu-save")
    public String menuSave(@RequestParam("id") String id, Model model, @RequestParam(value = "selectedItem", required = false)
    List<String> selectedItem, RedirectAttributes redirectAttributes) {
        if (selectedItem == null) {
            selectedItem = Collections.emptyList();
        }

        try {
            RoleDef roleDef = roleDefDao.get(id);
            roleDefChecker.check(roleDef);
            // 只清空menu类型
            roleDef.getMenus().clear();
            // 清空menu类型的,类型编号=7
            List<Perm> permList = permDao.find("from Perm where permType.id = ?", "7");
            for (Perm perm : permList) {
                roleDef.getPerms().remove(perm);
            }
            // 保存数据
            for (String menuId : selectedItem) {
                Menu menu = menuDao.get(menuId);
                roleDef.getMenus().add(menu);
                List<Perm> permAddList = permDao.find("from Perm where permType.id = '7' AND code=?", menuId);
                if (null!=permAddList && permAddList.size() > 0) {
                    roleDef.getPerms().add(permAddList.get(0));
                }
                // TODO 保存菜单关联权限
//                saveMenuPerm(menu);
            }
            roleDefDao.saveInsert(roleDef);
            messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        } catch (CheckRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            messageHelper.addFlashMessage(redirectAttributes, ex.getMessage());

            return input(id, model);
        }
        // 返回角色列表
        return "redirect:/auth/role-def-list.do";
    }
    /**
     * 保存菜单关联权限
     * 
     * @param menu
     */
    @SuppressWarnings("unchecked")
    private void saveMenuPerm(Menu menu) {
        // 如果菜单没有具体的URL那么不进行URL权限控制
        if (menu.getMenuUrl().indexOf(".") < 0) {
            return;
        }
        Access dest = new Access();
        List<Access> accessList = accessDao.find("from Access where menuId = ?", menu.getId());
        if (accessList != null && accessList.size() > 0) {
            dest = accessList.get(0);
        } else {
            dest.setId(UUID.randomUUID().toString());
            dest.setScopeid(ScopeHolder.getScopeId());
            dest.setMenuId(menu.getId());
        }
        // 资源value设置为菜单URL的.do之前的部分 + *
        String urlValue = menu.getMenuUrl().substring(0,menu.getMenuUrl().indexOf("."));
        dest.setValue(urlValue + "*");
        dest.setType("URL");
        dest.setPriority(10);
        // 权限代码 设置为菜单UUID主键,防止重复
        String permCode = menu.getId();
        // foreign 授权管理
        List<Perm> permList = permDao.find("from Perm where code = ?", permCode);
        Perm perm = new Perm();
        if (null != permList && permList.size() > 0) {
            perm = permList.get(0);
            perm.setCode(permCode);
            perm.setName(menu.getMenuName());
        } else {
            // 新建
            perm.setCode(permCode);
            perm.setName(menu.getMenuName());
            perm.setPermType(permTypeDao.get("7"));
            // 优先级
            perm.setScopeid("1");
        }
        //
        permDao.save(perm);
        dest.setPerm(perm);
        // save
        accessDao.save(dest);
        // 发布资源包
        resourcePublisher.publish();
    }
    // ======================================================================
    @Resource
    public void setPermDao(PermDao permDao) {
        this.permDao = permDao;
    }
    @Resource
    public void setRoleDefDao(RoleDefDao roleDefDao) {
        this.roleDefDao = roleDefDao;
    }
    @Resource
    public void setRoleDefChecker(RoleDefChecker roleDefChecker) {
        this.roleDefChecker = roleDefChecker;
    }
    @Resource
    public void setPermTypeDao(PermTypeDao permTypeDao) {
        this.permTypeDao = permTypeDao;
    }
    @Resource
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    // 权限相关
    @Resource
    public void setAccessDao(AccessDao accessDao) {
        this.accessDao = accessDao;
    }
    @Resource
    public void setResourcePublisher(ResourcePublisher resourcePublisher) {
        this.resourcePublisher = resourcePublisher;
    }
}
