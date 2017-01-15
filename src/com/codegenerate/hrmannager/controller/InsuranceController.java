package com.codegenerate.hrmannager.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import java.io.File;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import com.ibusiness.common.export.ExcelCommon;
import com.ibusiness.common.export.TableModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.security.util.SpringSecurityUtils;
import com.ibusiness.common.model.ConfSelectItem;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.component.form.entity.ConfFormTableColumn;
import com.ibusiness.common.service.FormulaCommon;

import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.util.CommonUtils;

import com.codegenerate.hrmannager.entity.InsuranceEntity;
import com.codegenerate.hrmannager.service.InsuranceService;

/**   
 * @Title: Controller
 * @Description: 出险管理页面
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("insurance")
public class InsuranceController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private InsuranceService insuranceService;
   /**
     * 列表
     */
    @RequestMapping("insurance-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
        page = insuranceService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "codegenerate/hrmannager/insurance-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("insurance-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        InsuranceEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = insuranceService.get(id);
        } else {
            entity = new InsuranceEntity();
        }
        
        // 默认值公式
        entity = (InsuranceEntity) new FormulaCommon().defaultValue(entity, "IB_INSURANCE");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> responsibilityFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_INSURANCE", "INSURANCE");List<com.ibusiness.common.model.ConfSelectItem> responsibilityItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(responsibilityFTCMap.get("RESPONSIBILITY").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("responsibilityItems", responsibilityItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> carflagFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_INSURANCE", "INSURANCE");List<com.ibusiness.common.model.ConfSelectItem> carflagItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(carflagFTCMap.get("CARFLAG").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("carflagItems", carflagItems);
        return "codegenerate/hrmannager/insurance-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("insurance-save")
    public String save(@ModelAttribute InsuranceEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            insuranceService.insert(entity);
        } else {
            insuranceService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/insurance/insurance-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("insurance-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<InsuranceEntity> entitys = insuranceService.findByIds(selectedItem);
        for (InsuranceEntity entity : entitys) {
            insuranceService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/insurance/insurance-list.do";
    }
    /**
     * 控件添加的方法 ========
     */
                @ResponseBody @RequestMapping("name-list")public String ajaxnamelist(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap) {List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);page = com.ibusiness.core.spring.ApplicationContextHelper.getBean(com.codegenerate.special.service.Special_driver_infoService.class).pagedQuery(page, propertyFilters);List list = (java.util.ArrayList)page.getResult();return CommonUtils.getJsonFromList(list, null).toString();}
                @ResponseBody @RequestMapping("carnum-list")public String ajaxcarnumlist(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap) {List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);page = com.ibusiness.core.spring.ApplicationContextHelper.getBean(com.codegenerate.carmgr.service.Car_mgrService.class).pagedQuery(page, propertyFilters);List list = (java.util.ArrayList)page.getResult();return CommonUtils.getJsonFromList(list, null).toString();}
    
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("insurance-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = insuranceService.pagedQuery(page, propertyFilters);
        List<InsuranceEntity> beans = (List<InsuranceEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("出险管理页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("drivername", "driverphone", "carname", "carnum", "eventdate", "responsibility", "mainttime", "carflag", "newcarname", "newcarnum", "id", "scopeid", "insuranceamount");
        tableModel.setTableName("IB_INSURANCE");
        tableModel.setData(beans);
        try {
            new ExcelCommon().exportExcel(response, tableModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * excel导入
     */
    @RequestMapping("insurance-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("drivername", "driverphone", "carname", "carnum", "eventdate", "responsibility", "mainttime", "carflag", "newcarname", "newcarnum", "id", "scopeid", "insuranceamount");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.hrmannager.entity.InsuranceEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/insurance/insurance-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setInsuranceService(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }
    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
}
