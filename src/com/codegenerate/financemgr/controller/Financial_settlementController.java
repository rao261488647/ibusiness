package com.codegenerate.financemgr.controller;

import java.io.File;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codegenerate.financemgr.entity.Financial_settlementEntity;
import com.codegenerate.financemgr.service.Financial_settlementService;
import com.ibusiness.common.export.ExcelCommon;
import com.ibusiness.common.export.TableModel;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.service.FormulaCommon;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.security.util.SpringSecurityUtils;

/**   
 * @Title: Controller
 * @Description: 财务结算页面页面
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("financial_settlement")
public class Financial_settlementController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private Financial_settlementService financial_settlementService;
   /**
     * 列表
     */
    @RequestMapping("financial_settlement-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
    	page.setOrderBy("eventdate");
    	page.setOrder("DESC");
        page = financial_settlementService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        //
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> setbusinesstypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_FINANCIAL_SETTLEMENT", "financialsettlement");List<com.ibusiness.common.model.ConfSelectItem> setbusinesstypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(setbusinesstypeFTCMap.get("SETBUSINESSTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);
        model.addAttribute("setbusinesstypeItems", setbusinesstypeItems);
        
        // 返回JSP
        return "codegenerate/financemgr/financial_settlement-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("financial_settlement-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        Financial_settlementEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = financial_settlementService.get(id);
        } else {
            entity = new Financial_settlementEntity();
        }
        
        // 默认值公式
        entity = (Financial_settlementEntity) new FormulaCommon().defaultValue(entity, "IB_FINANCIAL_SETTLEMENT");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> settlementtypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_FINANCIAL_SETTLEMENT", "financialsettlement");List<com.ibusiness.common.model.ConfSelectItem> settlementtypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(settlementtypeFTCMap.get("SETTLEMENTTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("settlementtypeItems", settlementtypeItems);
        
        Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> setbusinesstypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_FINANCIAL_SETTLEMENT", "financialsettlement");List<com.ibusiness.common.model.ConfSelectItem> setbusinesstypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(setbusinesstypeFTCMap.get("SETBUSINESSTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("setbusinesstypeItems", setbusinesstypeItems);
        return "codegenerate/financemgr/financial_settlement-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("financial_settlement-save")
    public String save(@ModelAttribute Financial_settlementEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            entity.setOrderformid(SpringSecurityUtils.getCurrentUsername() + CommonUtils.formatTime("yyyyMMddHHmmss", new Date()).toString());
            financial_settlementService.insert(entity);
        } else {
        	if (CommonUtils.isNull(entity.getOrderformid())) {
        		entity.setOrderformid(SpringSecurityUtils.getCurrentUsername() + CommonUtils.formatTime("yyyyMMddHHmmss", new Date()).toString());
        	}
            financial_settlementService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/financial_settlement/financial_settlement-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("financial_settlement-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<Financial_settlementEntity> entitys = financial_settlementService.findByIds(selectedItem);
        for (Financial_settlementEntity entity : entitys) {
            financial_settlementService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/financial_settlement/financial_settlement-list.do";
    }
    /**
     * 控件添加的方法 ========
     */
                @ResponseBody @RequestMapping("name-list")public String ajaxroomlist(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap) {List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);page = com.ibusiness.core.spring.ApplicationContextHelper.getBean(com.codegenerate.special.service.Special_driver_infoService.class).pagedQuery(page, propertyFilters);List list = (java.util.ArrayList)page.getResult();return CommonUtils.getJsonFromList(list, null).toString();}
    
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("financial_settlement-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = financial_settlementService.pagedQuery(page, propertyFilters);
        List<Financial_settlementEntity> beans = (List<Financial_settlementEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("财务结算页面页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("customername", "telephone", "settlementtype", "setbusinesstype", "transactionno", "eventdate", "amount", "id", "scopeid", "carnum", "remark", "monthdate", "documentnum", "ispay", "paymsg", "orderformid", "driverclass", "carmodel");
        tableModel.setTableName("IB_FINANCIAL_SETTLEMENT");
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
    @RequestMapping("financial_settlement-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("customername", "telephone", "settlementtype", "setbusinesstype", "transactionno", "eventdate", "amount", "id", "scopeid", "carnum", "remark", "monthdate", "documentnum", "ispay", "paymsg", "orderformid", "driverclass", "carmodel");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.financemgr.entity.Financial_settlementEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/financial_settlement/financial_settlement-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setFinancial_settlementService(Financial_settlementService financial_settlementService) {
        this.financial_settlementService = financial_settlementService;
    }
    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
}
