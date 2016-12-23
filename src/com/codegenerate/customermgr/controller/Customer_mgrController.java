package com.codegenerate.customermgr.controller;

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

import com.codegenerate.customermgr.entity.Customer_mgrEntity;
import com.codegenerate.customermgr.service.Customer_mgrService;

/**   
 * @Title: Controller
 * @Description: 客户管理页面
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("customer_mgr")
public class Customer_mgrController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private Customer_mgrService customer_mgrService;
   /**
     * 列表
     */
    @RequestMapping("customer_mgr-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
    	page.setOrderBy("createdatetime");
        page.setOrder("DESC");
        page = customer_mgrService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "codegenerate/customermgr/customer_mgr-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("customer_mgr-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        Customer_mgrEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = customer_mgrService.get(id);
        } else {
            entity = new Customer_mgrEntity();
        }
        
        // 默认值公式
        entity = (Customer_mgrEntity) new FormulaCommon().defaultValue(entity, "IB_CUSTOMER_MGR");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> customertypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CUSTOMER_MGR", "customermgr");List<com.ibusiness.common.model.ConfSelectItem> customertypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(customertypeFTCMap.get("CUSTOMERTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("customertypeItems", customertypeItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> customertobeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CUSTOMER_MGR", "customermgr");List<com.ibusiness.common.model.ConfSelectItem> customertobeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(customertobeFTCMap.get("CUSTOMERTOBE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("customertobeItems", customertobeItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> tosourceFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CUSTOMER_MGR", "customermgr");List<com.ibusiness.common.model.ConfSelectItem> tosourceItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(tosourceFTCMap.get("TOSOURCE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("tosourceItems", tosourceItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> userstatusFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CUSTOMER_MGR", "customermgr");List<com.ibusiness.common.model.ConfSelectItem> userstatusItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(userstatusFTCMap.get("USERSTATUS").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("userstatusItems", userstatusItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> sexFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CUSTOMER_MGR", "customermgr");List<com.ibusiness.common.model.ConfSelectItem> sexItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(sexFTCMap.get("SEX").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("sexItems", sexItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> contractstatusFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CUSTOMER_MGR", "customermgr");List<com.ibusiness.common.model.ConfSelectItem> contractstatusItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(contractstatusFTCMap.get("CONTRACTSTATUS").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("contractstatusItems", contractstatusItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> intentionFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_CUSTOMER_MGR", "customermgr");List<com.ibusiness.common.model.ConfSelectItem> intentionItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(intentionFTCMap.get("INTENTION").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("intentionItems", intentionItems);
        return "codegenerate/customermgr/customer_mgr-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("customer_mgr-save")
    public String save(@ModelAttribute Customer_mgrEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            customer_mgrService.insert(entity);
        } else {
            customer_mgrService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/customer_mgr/customer_mgr-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("customer_mgr-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<Customer_mgrEntity> entitys = customer_mgrService.findByIds(selectedItem);
        for (Customer_mgrEntity entity : entitys) {
            customer_mgrService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/customer_mgr/customer_mgr-list.do";
    }
    /**
     * 控件添加的方法 ========
     */
    
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("customer_mgr-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = customer_mgrService.pagedQuery(page, propertyFilters);
        List<Customer_mgrEntity> beans = (List<Customer_mgrEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("客户管理页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("tosource", "customername", "customercell", "unitname", "post", "customeradd", "customertype", "customertobe", "password", "device", "imei", "appsysversion", "token", "recommendman", "salesman", "remarks", "createdatetime", "id", "scopeid", "userstatus", "sex", "origin", "occupation", "idcard", "iscrime", "contractstatus", "contractplan", "intention");
        tableModel.setTableName("IB_CUSTOMER_MGR");
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
    @RequestMapping("customer_mgr-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("tosource", "customername", "customercell", "unitname", "post", "customeradd", "customertype", "customertobe", "password", "device", "imei", "appsysversion", "token", "recommendman", "salesman", "remarks", "createdatetime", "id", "scopeid", "userstatus", "sex", "origin", "occupation", "idcard", "iscrime", "contractstatus", "contractplan", "intention");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.customermgr.entity.Customer_mgrEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/customer_mgr/customer_mgr-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setCustomer_mgrService(Customer_mgrService customer_mgrService) {
        this.customer_mgrService = customer_mgrService;
    }
    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
}
