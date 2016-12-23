package com.ibusiness.base.group.web;

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

import com.ibusiness.base.group.dao.JobTitleDao;
import com.ibusiness.base.group.entity.JobTitle;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.security.api.scope.ScopeHolder;

/**
 * 职务名称
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("group")
public class JobTitleController {
    private JobTitleDao jobTitleDao;
    private MessageHelper messageHelper;
    private BeanMapper beanMapper = new BeanMapper();

    /**
     * 职务列表
     */
    @RequestMapping("job-title-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
        propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 设置排序信息
        page.setOrderBy("orders");
        page.setOrder("ASC");
        page = jobTitleDao.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);

        return "common/group/job-title-list.jsp";
    }

    /**
     * 职务编辑
     */
    @RequestMapping("job-title-input")
    public String input(@RequestParam(value = "id", required = false)
    String id, Model model) {
        if (id != null) {
            JobTitle jobTitle = jobTitleDao.get(id);
            model.addAttribute("model", jobTitle);
        }
        return "common/group/job-title-input.jsp";
    }

    /**
     * 职务保存
     */
    @RequestMapping("job-title-save")
    public String save(@ModelAttribute JobTitle jobTitle, RedirectAttributes redirectAttributes) {
        JobTitle dest = null;
        String id = jobTitle.getId();

        if (!CommonUtils.isNull(id)) {
            dest = jobTitleDao.get(id);
            beanMapper.copy(jobTitle, dest);
        } else {
            dest = jobTitle;
            dest.setScopeid(ScopeHolder.getScopeId());
            dest.setId(UUID.randomUUID().toString());
        }

        jobTitleDao.save(dest);

        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");

        return "redirect:/group/job-title-list.do";
    }

    /**
     * 职务删除
     */
    @RequestMapping("job-title-remove")
    public String remove(@RequestParam("selectedItem")
    List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<JobTitle> jobTitles = jobTitleDao.findByIds(selectedItem);

        for (JobTitle jobTitle : jobTitles) {
            jobTitleDao.remove(jobTitle);
        }

        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/group/job-title-list.do";
    }

    // ~ ======================================================================
    @Resource
    public void setJobTitleDao(JobTitleDao jobTitleDao) {
        this.jobTitleDao = jobTitleDao;
    }

    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
