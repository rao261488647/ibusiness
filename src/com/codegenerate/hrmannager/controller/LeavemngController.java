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

import com.codegenerate.hrmannager.entity.LeavemngEntity;
import com.codegenerate.hrmannager.service.LeavemngService;

/**   
 * @Title: Controller
 * @Description: 请假管理页面
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("leavemng")
public class LeavemngController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private LeavemngService leavemngService;
   /**
     * 列表
     */
    @RequestMapping("leavemng-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
        page = leavemngService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "codegenerate/hrmannager/leavemng-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("leavemng-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        LeavemngEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = leavemngService.get(id);
        } else {
            entity = new LeavemngEntity();
        }
        
        // 默认值公式
        entity = (LeavemngEntity) new FormulaCommon().defaultValue(entity, "IB_LEAVEMNG");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> carflagFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_LEAVEMNG", "LEAVEMNG");List<com.ibusiness.common.model.ConfSelectItem> carflagItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(carflagFTCMap.get("CARFLAG").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("carflagItems", carflagItems);
        return "codegenerate/hrmannager/leavemng-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("leavemng-save")
    public String save(@ModelAttribute LeavemngEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            leavemngService.insert(entity);
        } else {
            leavemngService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/leavemng/leavemng-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("leavemng-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<LeavemngEntity> entitys = leavemngService.findByIds(selectedItem);
        for (LeavemngEntity entity : entitys) {
            leavemngService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/leavemng/leavemng-list.do";
    }
    /**
     * 控件添加的方法 ========
     */
                @ResponseBody @RequestMapping("name-list")public String ajaxroomlist(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap) {List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);page = com.ibusiness.core.spring.ApplicationContextHelper.getBean(com.codegenerate.special.service.Special_driver_infoService.class).pagedQuery(page, propertyFilters);List list = (java.util.ArrayList)page.getResult();return CommonUtils.getJsonFromList(list, null).toString();}
    
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("leavemng-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = leavemngService.pagedQuery(page, propertyFilters);
        List<LeavemngEntity> beans = (List<LeavemngEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("请假管理页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("drivername", "driverphone", "dayss", "begindate", "enddate", "carflag", "id", "scopeid");
        tableModel.setTableName("IB_LEAVEMNG");
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
    @RequestMapping("leavemng-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("drivername", "driverphone", "dayss", "begindate", "enddate", "carflag", "id", "scopeid");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.hrmannager.entity.LeavemngEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/leavemng/leavemng-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setLeavemngService(LeavemngService leavemngService) {
        this.leavemngService = leavemngService;
    }
    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
}
