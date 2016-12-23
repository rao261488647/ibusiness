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

import com.codegenerate.hrmannager.entity.IllegalEntity;
import com.codegenerate.hrmannager.service.IllegalService;

/**   
 * @Title: Controller
 * @Description: 违章管理页面
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("illegal")
public class IllegalController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private IllegalService illegalService;
   /**
     * 列表
     */
    @RequestMapping("illegal-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
        page = illegalService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "codegenerate/hrmannager/illegal-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("illegal-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        IllegalEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = illegalService.get(id);
        } else {
            entity = new IllegalEntity();
        }
        
        // 默认值公式
        entity = (IllegalEntity) new FormulaCommon().defaultValue(entity, "IB_ILLEGAL");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> procestypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_ILLEGAL", "ILLEGAL");List<com.ibusiness.common.model.ConfSelectItem> procestypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(procestypeFTCMap.get("PROCESTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("procestypeItems", procestypeItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> amounttypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_ILLEGAL", "ILLEGAL");List<com.ibusiness.common.model.ConfSelectItem> amounttypeItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(amounttypeFTCMap.get("AMOUNTTYPE").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("amounttypeItems", amounttypeItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> ispointsFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_ILLEGAL", "ILLEGAL");List<com.ibusiness.common.model.ConfSelectItem> ispointsItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(ispointsFTCMap.get("ISPOINTS").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("ispointsItems", ispointsItems);
        return "codegenerate/hrmannager/illegal-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("illegal-save")
    public String save(@ModelAttribute IllegalEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            illegalService.insert(entity);
        } else {
            illegalService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/illegal/illegal-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("illegal-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<IllegalEntity> entitys = illegalService.findByIds(selectedItem);
        for (IllegalEntity entity : entitys) {
            illegalService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/illegal/illegal-list.do";
    }
    /**
     * 控件添加的方法 ========
     */
                @ResponseBody @RequestMapping("carnum-list")public String ajaxcarnumlist(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap) {List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);page = com.ibusiness.core.spring.ApplicationContextHelper.getBean(com.codegenerate.special.service.Special_driver_infoService.class).pagedQuery(page, propertyFilters);List list = (java.util.ArrayList)page.getResult();return CommonUtils.getJsonFromList(list, null).toString();}
    
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("illegal-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = illegalService.pagedQuery(page, propertyFilters);
        List<IllegalEntity> beans = (List<IllegalEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("违章管理页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("scopeid", "id", "carnum", "drivername", "eventdate", "amount", "procestype", "amounttype", "ispoints", "points");
        tableModel.setTableName("IB_ILLEGAL");
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
    @RequestMapping("illegal-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("scopeid", "id", "carnum", "drivername", "eventdate", "amount", "procestype", "amounttype", "ispoints", "points");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.hrmannager.entity.IllegalEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/illegal/illegal-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setIllegalService(IllegalService illegalService) {
        this.illegalService = illegalService;
    }
    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
}
