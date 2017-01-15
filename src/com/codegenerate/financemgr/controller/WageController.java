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

import com.codegenerate.financemgr.entity.WageEntity;
import com.codegenerate.financemgr.service.WageService;

/**   
 * @Title: Controller
 * @Description: 工资方案页面
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("wage")
public class WageController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private WageService wageService;
   /**
     * 列表
     */
    @RequestMapping("wage-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
        page = wageService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "codegenerate/financemgr/wage-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("wage-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        WageEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = wageService.get(id);
        } else {
            entity = new WageEntity();
        }
        
        // 默认值公式
        entity = (WageEntity) new FormulaCommon().defaultValue(entity, "IB_WAGE");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
        return "codegenerate/financemgr/wage-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("wage-save")
    public String save(@ModelAttribute WageEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            wageService.insert(entity);
        } else {
            wageService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/wage/wage-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("wage-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<WageEntity> entitys = wageService.findByIds(selectedItem);
        for (WageEntity entity : entitys) {
            wageService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/wage/wage-list.do";
    }
    /**
     * 控件添加的方法 ========
     */
                @ResponseBody @RequestMapping("name-list")public String ajaxnamelist(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap) {List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);page = com.ibusiness.core.spring.ApplicationContextHelper.getBean(com.codegenerate.special.service.Special_driver_infoService.class).pagedQuery(page, propertyFilters);List list = (java.util.ArrayList)page.getResult();return CommonUtils.getJsonFromList(list, null).toString();}
    
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("wage-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = wageService.pagedQuery(page, propertyFilters);
        List<WageEntity> beans = (List<WageEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("工资方案页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("scopeid", "id", "name", "wagescheme", "carname", "carnum", "getcardate", "getcardays", "initialkm", "endmonthkm", "turnover1", "expenses1", "turnover2", "expenses2", "turnover3", "expenses3", "turnover4", "expenses4", "turnover5", "expenses5", "airportamount", "totalturnover", "turnoverscore", "servicescore", "safetyscore", "coordscore", "referralscore", "excessscore", "totalscore", "ddturnover", "uberturnover", "qnwturnover", "monthexpenses", "qnwsubsidyamount", "shouldpaid", "ltcard", "utilitiescosts", "bydelectriccard", "ttelectriccard", "smhcelectriccard", "insurancefee", "monthsocial", "monthloan", "illegalfee", "qnwfine", "otherexpenses", "deductedele", "totaldeducted", "elesubsidies", "realwage", "bankaccount", "bank", "remark", "eventdate");
        tableModel.setTableName("IB_WAGE");
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
    @RequestMapping("wage-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("scopeid", "id", "name", "wagescheme", "carname", "carnum", "getcardate", "getcardays", "initialkm", "endmonthkm", "turnover1", "expenses1", "turnover2", "expenses2", "turnover3", "expenses3", "turnover4", "expenses4", "turnover5", "expenses5", "airportamount", "totalturnover", "turnoverscore", "servicescore", "safetyscore", "coordscore", "referralscore", "excessscore", "totalscore", "ddturnover", "uberturnover", "qnwturnover", "monthexpenses", "qnwsubsidyamount", "shouldpaid", "ltcard", "utilitiescosts", "bydelectriccard", "ttelectriccard", "smhcelectriccard", "insurancefee", "monthsocial", "monthloan", "illegalfee", "qnwfine", "otherexpenses", "deductedele", "totaldeducted", "elesubsidies", "realwage", "bankaccount", "bank", "remark", "eventdate");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.financemgr.entity.WageEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/wage/wage-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setWageService(WageService wageService) {
        this.wageService = wageService;
    }
    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
}
