package com.codegenerate.special.controller;

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

import com.codegenerate.special.entity.Specialcar_use_type_mgrEntity;
import com.codegenerate.special.service.Specialcar_use_type_mgrService;

/**   
 * @Title: Controller
 * @Description: 专车使用车型管理页面
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("specialcar_use_type_mgr")
public class Specialcar_use_type_mgrController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private Specialcar_use_type_mgrService specialcar_use_type_mgrService;
   /**
     * 列表
     */
    @RequestMapping("specialcar_use_type_mgr-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
        page = specialcar_use_type_mgrService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "codegenerate/special/specialcar_use_type_mgr-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("specialcar_use_type_mgr-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        Specialcar_use_type_mgrEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = specialcar_use_type_mgrService.get(id);
        } else {
            entity = new Specialcar_use_type_mgrEntity();
        }
        
        // 默认值公式
        entity = (Specialcar_use_type_mgrEntity) new FormulaCommon().defaultValue(entity, "IB_SPECIALCAR_USE_TYPE_MGR");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> cartypeFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIALCAR_USE_TYPE_MGR", "specialcarusetypemgr");JSONObject cartypeJsonObj = JSONObject.fromObject(cartypeFTCMap.get("CARTYPE").getConfSelectInfo());String cartypeSql = cartypeJsonObj.getString("sql");List<Map<String,Object>> cartypeList = com.ibusiness.core.spring.ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForList(cartypeSql);List<ConfSelectItem> cartypeItems = new java.util.ArrayList<ConfSelectItem>();for (Map<String,Object> mapBean : cartypeList) {    ConfSelectItem confSelectItem = new ConfSelectItem();    confSelectItem.setKey(mapBean.get("vKey").toString());    confSelectItem.setValue(mapBean.get("vValue").toString());    cartypeItems.add(confSelectItem);}model.addAttribute("cartypeItems", cartypeItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> neworoldFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIALCAR_USE_TYPE_MGR", "specialcarusetypemgr");List<com.ibusiness.common.model.ConfSelectItem> neworoldItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(neworoldFTCMap.get("NEWOROLD").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("neworoldItems", neworoldItems);
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> statusFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_SPECIALCAR_USE_TYPE_MGR", "specialcarusetypemgr");List<com.ibusiness.common.model.ConfSelectItem> statusItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(statusFTCMap.get("STATUS").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("statusItems", statusItems);
        return "codegenerate/special/specialcar_use_type_mgr-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("specialcar_use_type_mgr-save")
    public String save(@ModelAttribute Specialcar_use_type_mgrEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            specialcar_use_type_mgrService.insert(entity);
        } else {
            specialcar_use_type_mgrService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/specialcar_use_type_mgr/specialcar_use_type_mgr-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("specialcar_use_type_mgr-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<Specialcar_use_type_mgrEntity> entitys = specialcar_use_type_mgrService.findByIds(selectedItem);
        for (Specialcar_use_type_mgrEntity entity : entitys) {
            specialcar_use_type_mgrService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/specialcar_use_type_mgr/specialcar_use_type_mgr-list.do";
    }
    /**
     * 控件添加的方法 ========
     */
    
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("specialcar_use_type_mgr-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = specialcar_use_type_mgrService.pagedQuery(page, propertyFilters);
        List<Specialcar_use_type_mgrEntity> beans = (List<Specialcar_use_type_mgrEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("专车使用车型管理页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("cartype", "neworold", "contrforthreemth", "contrforsixmth", "contrforsoneyear", "contrforstwoyear", "cardeposit", "illegaldeposit", "status", "id", "scopeid", "plat");
        tableModel.setTableName("IB_SPECIALCAR_USE_TYPE_MGR");
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
    @RequestMapping("specialcar_use_type_mgr-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("cartype", "neworold", "contrforthreemth", "contrforsixmth", "contrforsoneyear", "contrforstwoyear", "cardeposit", "illegaldeposit", "status", "id", "scopeid", "plat");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.special.entity.Specialcar_use_type_mgrEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/specialcar_use_type_mgr/specialcar_use_type_mgr-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setSpecialcar_use_type_mgrService(Specialcar_use_type_mgrService specialcar_use_type_mgrService) {
        this.specialcar_use_type_mgrService = specialcar_use_type_mgrService;
    }
    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
}
