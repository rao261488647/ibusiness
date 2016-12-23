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

import com.ibusiness.base.group.dao.JobInfoDao;
import com.ibusiness.base.group.dao.JobTitleDao;
import com.ibusiness.base.group.dao.JobTypeDao;
import com.ibusiness.base.group.entity.JobInfo;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.security.api.scope.ScopeHolder;

/**
 * 职务管理
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("group")
public class JobInfoController {
    private JobInfoDao jobInfoDao;
    private MessageHelper messageHelper;
    private BeanMapper beanMapper = new BeanMapper();
    private JobTypeDao jobTypeDao;
    private JobTitleDao jobTitleDao;

    /**
     * 职务管理查询
     * 
     * @param page
     * @param parameterMap
     * @param model
     * @return
     */
    @RequestMapping("job-info-list")
    public String list(@ModelAttribute Page page, @RequestParam
    Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
    	// 设置排序信息
        page.setOrderBy("jobType");
        page.setOrder("ASC");
        page = jobInfoDao.pagedQuery(page, propertyFilters);

        model.addAttribute("page", page);

        return "common/group/job-info-list.jsp";
    }

    @RequestMapping("job-info-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        if (id != null) {
            JobInfo jobInfo = jobInfoDao.get(id);
            model.addAttribute("model", jobInfo);
        }

        model.addAttribute("jobTitles", jobTitleDao.getAll());
        model.addAttribute("jobTypes", jobTypeDao.getAll());

        return "common/group/job-info-input.jsp";
    }

    @RequestMapping("job-info-save")
    public String save(@ModelAttribute JobInfo jobInfo, @RequestParam("jobTitleId") String jobTitleId, 
            @RequestParam("jobTypeId") String jobTypeId, RedirectAttributes redirectAttributes) {
        JobInfo dest = null;
        String id = jobInfo.getId();

        if (id != null) {
            dest = jobInfoDao.get(id);
            beanMapper.copy(jobInfo, dest);
        } else {
            dest = jobInfo;
            dest.setScopeid(ScopeHolder.getScopeId());
            dest.setId(UUID.randomUUID().toString());
        }

        dest.setJobTitle(jobTitleDao.get(jobTitleId));
        dest.setJobType(jobTypeDao.get(jobTypeId));

        jobInfoDao.save(dest);

        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");

        return "redirect:/group/job-info-list.do";
    }

    @RequestMapping("job-info-remove")
    public String remove(@RequestParam("selectedItem")
    List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<JobInfo> jobInfos = jobInfoDao.findByIds(selectedItem);

        for (JobInfo jobInfo : jobInfos) {
            jobInfoDao.remove(jobInfo);
        }

        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/group/job-info-list.do";
    }

    // ~ ======================================================================
    @Resource
    public void setJobInfoDao(JobInfoDao jobInfoDao) {
        this.jobInfoDao = jobInfoDao;
    }

    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    @Resource
    public void setJobTypeDao(JobTypeDao jobTypeDao) {
        this.jobTypeDao = jobTypeDao;
    }

    @Resource
    public void setJobTitleDao(JobTitleDao jobTitleDao) {
        this.jobTitleDao = jobTitleDao;
    }
}
