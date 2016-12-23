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

import com.ibusiness.base.group.dao.OrgCompanyDao;
import com.ibusiness.base.group.dao.OrgDepartmentDao;
import com.ibusiness.base.group.dao.OrgGroupDao;
import com.ibusiness.base.group.entity.OrgCompany;
import com.ibusiness.base.group.entity.OrgDepartment;
import com.ibusiness.base.group.entity.OrgGroup;
import com.ibusiness.common.model.ConfSelectItem;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.security.api.scope.ScopeHolder;
/**
 * 小组
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("group")
public class OrgGroupController {
    private OrgGroupDao orgGroupDao;
    private OrgDepartmentDao orgDepartmentDao;
    private OrgCompanyDao orgCompanyDao;
    private MessageHelper messageHelper;
    private BeanMapper beanMapper = new BeanMapper();

    /**
     * 小组列表
     * @param page
     * @param parameterMap
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("org-group-list")
    public String list(@ModelAttribute
    Page page, @RequestParam
    Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = orgGroupDao.pagedQuery(page, propertyFilters);
        // 设置公司名称，部门名称
        List<OrgGroup> list = (List<OrgGroup>) page.getResult();
        Map<String, OrgCompany> companyMap = CommonBusiness.getInstance().getCompanyMap();
        Map<String, OrgDepartment> departmentMap = CommonBusiness.getInstance().getDepartmentMap();
        for (OrgGroup bean : list) {
        	if (companyMap.containsKey(bean.getCompanyid())) {
        		bean.setCompanyName(companyMap.get(bean.getCompanyid()).getName());
        		bean.setDeptName(departmentMap.get(bean.getDeptid()).getName());
        	}
        }
        model.addAttribute("page", page);

        return "common/group/org-group-list.jsp";
    }

    /**
     * 小组信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("org-group-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        if (id != null) {
            OrgGroup orgGroup = orgGroupDao.get(id);
            model.addAttribute("model", orgGroup);
        }
        // 在controller中设置页面控件用的数据
        // 公司信息下拉框
        String companySql = "select id vKey, name vValue from IB_COMPANY WHERE SCOPE_ID='"+ScopeHolder.getScopeId()+"'";
		List<Map<String, Object>> list = orgCompanyDao.getJdbcTemplate().queryForList(companySql);
		List<ConfSelectItem> companyidItems = new java.util.ArrayList<ConfSelectItem>();
		for (Map<String, Object> mapBean : list) {
			ConfSelectItem confSelectItem = new ConfSelectItem();
			confSelectItem.setKey(mapBean.get("vKey").toString());
			confSelectItem.setValue(mapBean.get("vValue").toString());
			companyidItems.add(confSelectItem);
		}
		model.addAttribute("companyidItems", companyidItems);
		// 部门下拉框
		String deptidSql = "select id vKey, name vValue from IB_DEPARTMENT WHERE SCOPE_ID='"+ScopeHolder.getScopeId()+"'";
		List<Map<String, Object>> deptidList = orgDepartmentDao.getJdbcTemplate().queryForList(deptidSql);
		List<ConfSelectItem> deptidItems = new java.util.ArrayList<ConfSelectItem>();
		for (Map<String, Object> mapBean : deptidList) {
			ConfSelectItem confSelectItem = new ConfSelectItem();
			confSelectItem.setKey(mapBean.get("vKey").toString());
			confSelectItem.setValue(mapBean.get("vValue").toString());
			deptidItems.add(confSelectItem);
		}
		model.addAttribute("deptidItems", deptidItems);

        return "common/group/org-group-input.jsp";
    }

    @RequestMapping("org-group-save")
    public String save(@ModelAttribute
    OrgGroup orgGroup, RedirectAttributes redirectAttributes) {
        OrgGroup dest = null;
        String id = orgGroup.getId();

        if (!CommonUtils.isNull(id)) {
            dest = orgGroupDao.get(id);
            beanMapper.copy(orgGroup, dest);
            orgGroupDao.save(dest);
        } else {
            dest = orgGroup;
            dest.setScopeid(ScopeHolder.getScopeId());
            dest.setId(UUID.randomUUID().toString());
            orgGroupDao.saveInsert(dest);
        }
        
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");

        return "redirect:/group/org-group-list.do";
    }

    @RequestMapping("org-group-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<OrgGroup> orgGroups = orgGroupDao.findByIds(selectedItem);

        for (OrgGroup orgGroup : orgGroups) {
            orgGroupDao.remove(orgGroup);
        }

        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/group/org-group-list.do";
    }

    // ~ ======================================================================
    @Resource
    public void setOrgGroupDao(OrgGroupDao orgGroupDao) {
        this.orgGroupDao = orgGroupDao;
    }
    @Resource
    public void setOrgDepartmentDao(OrgDepartmentDao orgDepartmentDao) {
        this.orgDepartmentDao = orgDepartmentDao;
    }
    @Resource
    public void setOrgCompanyDao(OrgCompanyDao orgCompanyDao) {
        this.orgCompanyDao = orgCompanyDao;
    }
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
