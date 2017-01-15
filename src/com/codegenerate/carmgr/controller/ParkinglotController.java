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

import com.codegenerate.carmgr.entity.ParkinglotEntity;
import com.codegenerate.carmgr.service.ParkinglotService;

/**   
 * @Title: Controller
 * @Description: 停车场出入库管理页面
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("parkinglot")
public class ParkinglotController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private ParkinglotService parkinglotService;
   /**
     * 列表
     */
    @RequestMapping("parkinglot-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
        page = parkinglotService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "codegenerate/carmgr/parkinglot-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("parkinglot-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        ParkinglotEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = parkinglotService.get(id);
        } else {
            entity = new ParkinglotEntity();
        }
        
        // 默认值公式
        entity = (ParkinglotEntity) new FormulaCommon().defaultValue(entity, "IB_PARKINGLOT");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
        return "codegenerate/carmgr/parkinglot-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("parkinglot-save")
    public String save(@ModelAttribute ParkinglotEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            parkinglotService.insert(entity);
        } else {
            parkinglotService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/parkinglot/parkinglot-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("parkinglot-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<ParkinglotEntity> entitys = parkinglotService.findByIds(selectedItem);
        for (ParkinglotEntity entity : entitys) {
            parkinglotService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/parkinglot/parkinglot-list.do";
    }
    /**
     * 控件添加的方法 ========
     */
                @ResponseBody @RequestMapping("carnum-list")public String ajaxcarnumlist(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap) {List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);page = com.ibusiness.core.spring.ApplicationContextHelper.getBean(com.codegenerate.carmgr.service.Car_mgrService.class).pagedQuery(page, propertyFilters);List list = (java.util.ArrayList)page.getResult();return CommonUtils.getJsonFromList(list, null).toString();}
    
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("parkinglot-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = parkinglotService.pagedQuery(page, propertyFilters);
        List<ParkinglotEntity> beans = (List<ParkinglotEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("停车场出入库管理页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("scopeid", "id", "typename", "carnum", "ininfo", "intime", "inmil", "outinfo", "outime", "outmil");
        tableModel.setTableName("IB_PARKINGLOT");
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
    @RequestMapping("parkinglot-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("scopeid", "id", "typename", "carnum", "ininfo", "intime", "inmil", "outinfo", "outime", "outmil");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.carmgr.entity.ParkinglotEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/parkinglot/parkinglot-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setParkinglotService(ParkinglotService parkinglotService) {
        this.parkinglotService = parkinglotService;
    }
    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
}
