package com.ibusiness.base.group.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.base.auth.entity.ScopeInfo;
import com.ibusiness.base.auth.service.ScopeInfoService;
import com.ibusiness.base.group.dao.OrgCompanyDao;
import com.ibusiness.base.group.entity.OrgCompany;
import com.ibusiness.common.export.ExcelCommon;
import com.ibusiness.common.export.TableModel;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.core.spring.MessageHelper;
/**
 * 公司
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("group")
public class OrgCompanyController {
    private OrgCompanyDao orgCompanyDao;
    private ScopeInfoService scopeInfoService;
    private MessageHelper messageHelper;
    private BeanMapper beanMapper = new BeanMapper();

    /**
     * 列表信息
     * 
     * @param page
     * @param parameterMap
     * @param model
     * @return
     */
    @RequestMapping("org-company-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = orgCompanyDao.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);

        return "common/group/org-company-list.jsp";
    }

    @RequestMapping("org-company-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        if (id != null) {
            OrgCompany orgCompany = orgCompanyDao.get(id);
            model.addAttribute("model", orgCompany);
        }

        return "common/group/org-company-input.jsp";
    }

    /**
     * 保存
     * @param orgCompany
     * @param parameterMap
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("org-company-save")
    public String save(@ModelAttribute OrgCompany orgCompany, @RequestParam Map<String, Object> parameterMap,
    		RedirectAttributes redirectAttributes) {
        OrgCompany dest = null;
        String id = orgCompany.getId();

        if (!CommonUtils.isNull(id)) {
            dest = orgCompanyDao.get(id);
            beanMapper.copy(orgCompany, dest);
            orgCompanyDao.save(dest);
            // 更新范围表，目前一个公司对应一个范围
            ScopeInfo scopeInfo = scopeInfoService.get(dest.getId());
            scopeInfo.setName(dest.getName());
            scopeInfoService.update(scopeInfo);
        } else {
            dest = orgCompany;
            dest.setId(UUID.randomUUID().toString());
            // 此信息范围 = 当前单位ID ScopeHolder.getScopeId()
            dest.setScopeid(dest.getId());
            orgCompanyDao.saveInsert(dest);
            // 更新范围表数据，目前一个公司对应一个范围
            ScopeInfo scopeInfo = new ScopeInfo();
            scopeInfo.setId(dest.getId());
            scopeInfo.setName(dest.getName());
            scopeInfo.setCode("");
            scopeInfo.setShared(0);
            scopeInfo.setUserRepoRef("1");
            scopeInfo.setType(0);
            scopeInfoService.saveInsert(scopeInfo);
        }

        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");

        return "redirect:/group/org-company-list.do";
    }

    /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("org-company-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<OrgCompany> orgCompanies = orgCompanyDao.findByIds(selectedItem);
        for (OrgCompany orgCompany : orgCompanies) {
            orgCompanyDao.remove(orgCompany);
            // 删除多租户范围
            if (null != scopeInfoService.get(orgCompany.getId())) {
            	scopeInfoService.removeById(orgCompany.getId());
            }
        }

        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/group/org-company-list.do";
    }
    /**
     * excel导出
     * 
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("org-company-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        page = orgCompanyDao.pagedQuery(page, propertyFilters);
        List<OrgCompany> beans = (List<OrgCompany>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("公司信息列表"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("公司id", "公司名", "公司状态", "范围");
        tableModel.setTableName("ib_company");
        tableModel.setData(beans);
        try {
            new ExcelCommon().exportExcel(response, tableModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * excel导入
     * 
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("org-company-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // excel文件名
            tableModel.setExcelName("org");
            // 列名
            tableModel.addHeaders("id", "name", "status", "scopeid");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.ibusiness.base.group.entity.OrgCompany");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/group/org-company-list.do";
    }
    // ~ ======================================================================
    @Resource
    public void setOrgCompanyDao(OrgCompanyDao orgCompanyDao) {
        this.orgCompanyDao = orgCompanyDao;
    }
    @Resource
    public void setScopeInfoService(ScopeInfoService scopeInfoService) {
        this.scopeInfoService = scopeInfoService;
    }
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
