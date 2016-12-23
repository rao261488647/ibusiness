package com.ibusiness.cms.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.cms.entity.CmsComment;
import com.ibusiness.cms.service.CmsCommentService;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.security.api.scope.ScopeHolder;
/**
 * 公告评论管理
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("/cms")
public class CmsCommentController {
    private CmsCommentService cmsCommentService;
    private BeanMapper beanMapper = new BeanMapper();
    private MessageHelper messageHelper;

    /**
     * 
     * @param page
     * @param parameterMap
     * @param model
     * @return
     */
    @RequestMapping("cms-comment-list")
    public String list(@ModelAttribute
    Page page, @RequestParam
    Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = cmsCommentService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);

        return "cms/cms-comment-list";
    }

    @RequestMapping("cms-comment-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        if (id != null) {
            CmsComment cmsComment = cmsCommentService.get(id);
            model.addAttribute("model", cmsComment);
        }

        return "cms/cms-comment-input";
    }

    /**
     * 保存
     * @param cmsComment
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("cms-comment-save")
    public String save(@ModelAttribute CmsComment cmsComment, RedirectAttributes redirectAttributes) {
        String id = cmsComment.getId();
        CmsComment dest = null;

        if (id != null) {
            dest = cmsCommentService.get(id);
            beanMapper.copy(cmsComment, dest);
        } else {
            dest = cmsComment;
            dest.setScopeid(ScopeHolder.getScopeId());
        }

        cmsCommentService.save(dest);
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");

        return "redirect:/cms/cms-comment-list.do";
    }

    @RequestMapping("cms-comment-remove")
    public String remove(@RequestParam("selectedItem")
    List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<CmsComment> cmsComments = cmsCommentService.findByIds(selectedItem);
        cmsCommentService.removeAll(cmsComments);
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/cms/cms-comment-list.do";
    }

    @RequestMapping("cms-comment-checkName")
    @ResponseBody
    public boolean checkName(@RequestParam("name")
    String name, @RequestParam(value = "id", required = false)
    String id) throws Exception {
        String hql = "from CmsComment where name=?";
        Object[] params = { name };

        if (id != null) {
            hql = "from CmsComment where name=? and id<>?";
            params = new Object[] {
                    name, id };
        }

        CmsComment cmsComment = cmsCommentService.findUnique(hql, params);

        boolean result = (cmsComment == null);

        return result;
    }

    // ~ ======================================================================
    @Resource
    public void setCmsCommentService(CmsCommentService cmsCommentService) {
        this.cmsCommentService = cmsCommentService;
    }

    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
