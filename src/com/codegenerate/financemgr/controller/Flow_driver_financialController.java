package com.codegenerate.financemgr.controller;

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

import com.codegenerate.financemgr.entity.Flow_driver_financialEntity;
import com.codegenerate.financemgr.service.Flow_driver_financialService;

/**   
 * @Title: Controller
 * @Description: 司机流水页面
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("flow_driver_financial")
public class Flow_driver_financialController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private Flow_driver_financialService flow_driver_financialService;
   /**
     * 列表
     */
    @RequestMapping("flow_driver_financial-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
    	page.setOrderBy("eventdate");
    	page.setOrder("DESC");
        page = flow_driver_financialService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        List<Flow_driver_financialEntity> fdfList = (List<Flow_driver_financialEntity>) page.getResult();
        Double pagetotalAmount = 0.0;
        for (Flow_driver_financialEntity bean : fdfList) {
        	pagetotalAmount = pagetotalAmount + Double.parseDouble(CommonUtils.isNull(bean.getAmount())?"0":bean.getAmount());
        }
        model.addAttribute("pagetotalAmount", pagetotalAmount.intValue());
        List<Flow_driver_financialEntity> allfdfList = flow_driver_financialService.find(propertyFilters);
        Double totalAmount = 0.0;
        for (Flow_driver_financialEntity bean : allfdfList) {
        	totalAmount = totalAmount + Double.parseDouble(CommonUtils.isNull(bean.getAmount())?"0":bean.getAmount());
        }
        model.addAttribute("totalAmount", totalAmount.intValue());
        // 返回JSP
        return "codegenerate/financemgr/flow_driver_financial-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("flow_driver_financial-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        Flow_driver_financialEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = flow_driver_financialService.get(id);
        } else {
            entity = new Flow_driver_financialEntity();
        }
        
        // 默认值公式
        entity = (Flow_driver_financialEntity) new FormulaCommon().defaultValue(entity, "IB_FLOW_DRIVER_FINANCIAL");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
        return "codegenerate/financemgr/flow_driver_financial-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("flow_driver_financial-save")
    public String save(@ModelAttribute Flow_driver_financialEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            flow_driver_financialService.insert(entity);
        } else {
            flow_driver_financialService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/flow_driver_financial/flow_driver_financial-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("flow_driver_financial-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<Flow_driver_financialEntity> entitys = flow_driver_financialService.findByIds(selectedItem);
        for (Flow_driver_financialEntity entity : entitys) {
            flow_driver_financialService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/flow_driver_financial/flow_driver_financial-list.do";
    }
    /**
     * 控件添加的方法 ========
     */
                @ResponseBody @RequestMapping("customername-list")public String ajaxroomlist(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap) {List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);page = com.ibusiness.core.spring.ApplicationContextHelper.getBean(com.codegenerate.customermgr.service.Customer_mgrService.class).pagedQuery(page, propertyFilters);List list = (java.util.ArrayList)page.getResult();return CommonUtils.getJsonFromList(list, null).toString();}
    
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("flow_driver_financial-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = flow_driver_financialService.pagedQuery(page, propertyFilters);
        List<Flow_driver_financialEntity> beans = (List<Flow_driver_financialEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("司机流水页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("scopeid", "id", "eventdate", "customername", "telephone", "transactionno", "platform", "amount", "carnum", "remark");
        tableModel.setTableName("IB_FLOW_DRIVER_FINANCIAL");
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
    @RequestMapping("flow_driver_financial-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("scopeid", "id", "eventdate", "customername", "telephone", "transactionno", "platform", "amount", "carnum", "remark");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.financemgr.entity.Flow_driver_financialEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/flow_driver_financial/flow_driver_financial-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setFlow_driver_financialService(Flow_driver_financialService flow_driver_financialService) {
        this.flow_driver_financialService = flow_driver_financialService;
    }
    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
}
