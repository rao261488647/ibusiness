package com.codegenerate.carmgr.controller;

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

import com.codegenerate.carmgr.entity.Target_compant_mgrEntity;
import com.codegenerate.carmgr.service.Target_compant_mgrService;

/**   
 * @Title: Controller
 * @Description: 指标公司管理页面
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("target_compant_mgr")
public class Target_compant_mgrController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private Target_compant_mgrService target_compant_mgrService;
   /**
     * 列表
     */
    @RequestMapping("target_compant_mgr-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
        page = target_compant_mgrService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "codegenerate/carmgr/target_compant_mgr-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("target_compant_mgr-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        Target_compant_mgrEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = target_compant_mgrService.get(id);
        } else {
            entity = new Target_compant_mgrEntity();
        }
        
        // 默认值公式
        entity = (Target_compant_mgrEntity) new FormulaCommon().defaultValue(entity, "IB_TARGET_COMPANT_MGR");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> isreturnFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_TARGET_COMPANT_MGR", "targetcompantmgr");List<com.ibusiness.common.model.ConfSelectItem> isreturnItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(isreturnFTCMap.get("ISRETURN").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("isreturnItems", isreturnItems);
        return "codegenerate/carmgr/target_compant_mgr-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("target_compant_mgr-save")
    public String save(@ModelAttribute Target_compant_mgrEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            target_compant_mgrService.insert(entity);
        } else {
            target_compant_mgrService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/target_compant_mgr/target_compant_mgr-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("target_compant_mgr-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<Target_compant_mgrEntity> entitys = target_compant_mgrService.findByIds(selectedItem);
        for (Target_compant_mgrEntity entity : entitys) {
            target_compant_mgrService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/target_compant_mgr/target_compant_mgr-list.do";
    }
    /**
     * 控件添加的方法 ========
     */
    
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("target_compant_mgr-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = target_compant_mgrService.pagedQuery(page, propertyFilters);
        List<Target_compant_mgrEntity> beans = (List<Target_compant_mgrEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("指标公司管理页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("companyname", "companyexecutor", "executorcell", "lottarytarget", "updatetargetcount", "othertargetcount", "registerdate", "isreturn", "remark", "id", "scopeid");
        tableModel.setTableName("IB_TARGET_COMPANT_MGR");
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
    @RequestMapping("target_compant_mgr-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("companyname", "companyexecutor", "executorcell", "lottarytarget", "updatetargetcount", "othertargetcount", "registerdate", "isreturn", "remark", "id", "scopeid");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.carmgr.entity.Target_compant_mgrEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/target_compant_mgr/target_compant_mgr-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setTarget_compant_mgrService(Target_compant_mgrService target_compant_mgrService) {
        this.target_compant_mgrService = target_compant_mgrService;
    }
    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
}
