package com.ibusiness.cms.controller;

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

import com.ibusiness.cms.entity.CmsCatalog;
import com.ibusiness.cms.service.CmsCatalogService;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.security.api.scope.ScopeHolder;
/**
 * 公告栏目管理controller
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("/cms")
public class CmsCatalogController {
    private CmsCatalogService cmsCatalogService;
    private BeanMapper beanMapper = new BeanMapper();
    private MessageHelper messageHelper;

    /**
     * 列表查看
     * 
     * @param page
     * @param parameterMap
     * @param model
     * @return
     */
    @RequestMapping("cms-catalog-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = cmsCatalogService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);

        return "ibusiness/cms/cms-catalog-list.jsp";
    }

    /**
     * 新建/修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("cms-catalog-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        if (id != null) {
            CmsCatalog cmsCatalog = cmsCatalogService.get(id);
            model.addAttribute("model", cmsCatalog);
        }

        return "ibusiness/cms/cms-catalog-input.jsp";
    }

    /**
     * 保存
     * @param cmsCatalog
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("cms-catalog-save")
    public String save(@ModelAttribute CmsCatalog cmsCatalog, RedirectAttributes redirectAttributes) {
        String id = cmsCatalog.getId();
        CmsCatalog dest = null;

        if (id != null) {
            dest = cmsCatalogService.get(id);
            beanMapper.copy(cmsCatalog, dest);
            cmsCatalogService.save(dest);
        } else {
            dest = cmsCatalog;
            dest.setId(UUID.randomUUID().toString());
            dest.setScopeid(ScopeHolder.getScopeId());
            cmsCatalogService.saveInsert(dest);
        }
        
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");

        return "redirect:/cms/cms-catalog-list.do";
    }

    @RequestMapping("cms-catalog-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<CmsCatalog> cmsCatalogs = cmsCatalogService.findByIds(selectedItem);
        cmsCatalogService.removeAll(cmsCatalogs);
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/cms/cms-catalog-list.do";
    }

    @RequestMapping("cms-catalog-checkName")
    @ResponseBody
    public boolean checkName(@RequestParam("name") String name,
            @RequestParam(value = "id", required = false) String id)
            throws Exception {
        String hql = "from CmsCatalog where name=?";
        Object[] params = { name };

        if (id != null) {
            hql = "from CmsCatalog where name=? and id<>?";
            params = new Object[] { name, id };
        }

        CmsCatalog cmsCatalog = cmsCatalogService.findUnique(hql, params);

        boolean result = (cmsCatalog == null);

        return result;
    }

    // ~ ======================================================================
    @Resource
    public void setCmsCatalogService(CmsCatalogService cmsCatalogService) {
        this.cmsCatalogService = cmsCatalogService;
    }
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
