package com.ibusiness.base.auth.web;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.base.auth.dao.PermTypeDao;
import com.ibusiness.base.auth.entity.PermType;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.security.api.scope.ScopeHolder;
/**
 * 授权分类
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("auth")
public class PermTypeController {
    private PermTypeDao permTypeDao;
    private MessageHelper messageHelper;
    private BeanMapper beanMapper = new BeanMapper();

    /**
     * 授权分类列表
     * 
     * @param page
     * @param parameterMap
     * @param model
     * @return
     */
    @RequestMapping("perm-type-list")
    public String list(@ModelAttribute
    Page page, @RequestParam
    Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = permTypeDao.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);

        return "common/auth/perm-type-list.jsp";
    }

    @RequestMapping("perm-type-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        if (id != null) {
            PermType permType = permTypeDao.get(id);
            model.addAttribute("model", permType);
        }
        return "common/auth/perm-type-input.jsp";
    }

    @RequestMapping("perm-type-save")
    public String save(@ModelAttribute PermType permType, RedirectAttributes redirectAttributes) {
        // copy
        PermType dest = null;
        String id = permType.getId();

        if (!CommonUtils.isNull(id)) {
            dest = permTypeDao.get(id);
            beanMapper.copy(permType, dest);
            // save
            permTypeDao.save(dest);
        } else {
            dest = permType;
            dest.setScopeid(ScopeHolder.getScopeId());
            dest.setId(UUID.randomUUID().toString());
            // save
            permTypeDao.saveInsert(dest);
        }

        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");

        return "redirect:/auth/perm-type-list.do";
    }

    @RequestMapping("perm-type-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<PermType> permTypes = permTypeDao.findByIds(selectedItem);
        permTypeDao.removeAll(permTypes);
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/auth/perm-type-list.do";
    }

    // ======================================================================
    @Resource
    public void setPermTypeDao(PermTypeDao permTypeDao) {
        this.permTypeDao = permTypeDao;
    }

    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
