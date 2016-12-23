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

import com.codegenerate.hrmannager.entity.ElectricitycardEntity;
import com.codegenerate.hrmannager.service.ElectricitycardService;

/**   
 * @Title: Controller
 * @Description: 电卡管理页面
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("electricitycard")
public class ElectricitycardController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private ElectricitycardService electricitycardService;
   /**
     * 列表
     */
    @RequestMapping("electricitycard-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
        page = electricitycardService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "codegenerate/hrmannager/electricitycard-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("electricitycard-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        ElectricitycardEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = electricitycardService.get(id);
        } else {
            entity = new ElectricitycardEntity();
        }
        
        // 默认值公式
        entity = (ElectricitycardEntity) new FormulaCommon().defaultValue(entity, "IB_ELECTRICITYCARD");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> electricstatusFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_ELECTRICITYCARD", "ELECTRICITYCARD");List<com.ibusiness.common.model.ConfSelectItem> electricstatusItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(electricstatusFTCMap.get("ELECTRICSTATUS").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("electricstatusItems", electricstatusItems);
        return "codegenerate/hrmannager/electricitycard-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("electricitycard-save")
    public String save(@ModelAttribute ElectricitycardEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            electricitycardService.insert(entity);
        } else {
            electricitycardService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/electricitycard/electricitycard-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("electricitycard-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<ElectricitycardEntity> entitys = electricitycardService.findByIds(selectedItem);
        for (ElectricitycardEntity entity : entitys) {
            electricitycardService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/electricitycard/electricitycard-list.do";
    }
    /**
     * 控件添加的方法 ========
     */
    
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("electricitycard-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = electricitycardService.pagedQuery(page, propertyFilters);
        List<ElectricitycardEntity> beans = (List<ElectricitycardEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("电卡管理页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("scopeid", "id", "electriccardno", "eventdate", "initialamount", "chargeamount", "chargedate", "refundamount", "refunddate", "electricstatus", "drivername", "cardbalance");
        tableModel.setTableName("IB_ELECTRICITYCARD");
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
    @RequestMapping("electricitycard-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("scopeid", "id", "electriccardno", "eventdate", "initialamount", "chargeamount", "chargedate", "refundamount", "refunddate", "electricstatus", "drivername", "cardbalance");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.hrmannager.entity.ElectricitycardEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/electricitycard/electricitycard-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setElectricitycardService(ElectricitycardService electricitycardService) {
        this.electricitycardService = electricitycardService;
    }
    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
}
