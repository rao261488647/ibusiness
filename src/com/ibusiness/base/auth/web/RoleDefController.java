package com.ibusiness.base.auth.web;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.base.auth.component.CheckRoleException;
import com.ibusiness.base.auth.component.RoleDefChecker;
import com.ibusiness.base.auth.dao.RoleDefDao;
import com.ibusiness.base.auth.entity.RoleDef;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.security.api.scope.ScopeHolder;

/**
 * 角色管理
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("auth")
public class RoleDefController {
    private static Logger logger = LoggerFactory.getLogger(RoleDefController.class);
    private RoleDefDao roleDefDao;
    private MessageHelper messageHelper;
    private RoleDefChecker roleDefChecker;
    private BeanMapper beanMapper = new BeanMapper();

    /**
     * 角色列表
     * 
     * @param page
     * @param parameterMap
     * @param model
     * @return
     */
    @RequestMapping("role-def-list")
    public String list(@ModelAttribute Page page, @RequestParam
    Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = roleDefDao.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);

        return "common/auth/role-def-list.jsp";
    }

    @RequestMapping("role-def-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        if (id != null) {
            RoleDef roleDef = roleDefDao.get(id);
            model.addAttribute("model", roleDef);
        }

        return "common/auth/role-def-input.jsp";
    }

    /**
     * 保存
     * 
     * @param roleDef
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("role-def-save")
    public String save(@ModelAttribute
    RoleDef roleDef, RedirectAttributes redirectAttributes) {
        try {
            // before check
            roleDefChecker.check(roleDef);
            // after invoke
            RoleDef dest = null;
            String id = roleDef.getId();
            if (!CommonUtils.isNull(id)) {
                dest = roleDefDao.get(id);
                roleDef.setPerms(null);
                beanMapper.copy(roleDef, dest);
                roleDefDao.save(dest);
            } else {
                dest = roleDef;
                dest.setScopeid(ScopeHolder.getScopeId());
                dest.setId(UUID.randomUUID().toString());
                roleDefDao.saveInsert(dest);
            }
            messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        } catch (CheckRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return "common/auth/role-def-input.jsp";
        }
        return "redirect:/auth/role-def-list.do";
    }

    @RequestMapping("role-def-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        try {
            List<RoleDef> roleDefs = roleDefDao.findByIds(selectedItem);

            for (RoleDef roleDef : roleDefs) {
                roleDefChecker.check(roleDef);
            }

            roleDefDao.removeAll(roleDefs);
            messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");
        } catch (CheckRoleException ex) {
            logger.warn(ex.getMessage(), ex);

            messageHelper.addFlashMessage(redirectAttributes, ex.getMessage());
        }

        return "redirect:/auth/role-def-list.do";
    }

    /**
     * 后台check角色名是否重复
     * @param name
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("role-def-checkName")
    @ResponseBody
    public boolean checkName(@RequestParam("name")
    String name, @RequestParam(value = "id", required = false) String id) throws Exception {
        String hql = "from RoleDef where scopeid='" + ScopeHolder.getScopeId() + "' and name=?";
		Object[] params = { name };

        if (id != null) {
            hql = "from RoleDef where scopeid='" + ScopeHolder.getScopeId() + "' and name=? and id<>?";
			params = new Object[] { name, id };
        }

        boolean result = roleDefDao.findUnique(hql, params) == null;

        return result;
    }

    // ======================================================================
    @Resource
    public void setRoleDefDao(RoleDefDao roleDefDao) {
        this.roleDefDao = roleDefDao;
    }
    @Resource
    public void setRoleDefChecker(RoleDefChecker roleDefChecker) {
        this.roleDefChecker = roleDefChecker;
    }
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
