package ${bussiPackage}.${entityPackage}.controller;

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

import ${bussiPackage}.${entityPackage}.entity.${entityName}Entity;
import ${bussiPackage}.${entityPackage}.service.${entityName}Service;

/**   
 * @Title: Controller
 * @Description: ${ftl_description}
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("${entityName?uncap_first}")
public class ${entityName}Controller {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private ${entityName}Service ${entityName?uncap_first}Service;
   /**
     * 列表
     */
    @RequestMapping("${entityName?uncap_first}-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
        page = ${entityName?uncap_first}Service.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "codegenerate/${entityPackage}/${entityName?uncap_first}-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("${entityName?uncap_first}-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        ${entityName}Entity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = ${entityName?uncap_first}Service.get(id);
        } else {
            entity = new ${entityName}Entity();
        }
        
        // 默认值公式
        entity = (${entityName}Entity) new FormulaCommon().defaultValue(entity, "${tableName}");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
        <#list columns as po>
            <#list po.modelAttributeList as ma>
                ${ma}
            </#list>
        </#list>
        return "codegenerate/${entityPackage}/${entityName?uncap_first}-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("${entityName?uncap_first}-save")
    public String save(@ModelAttribute ${entityName}Entity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            ${entityName?uncap_first}Service.insert(entity);
        } else {
            ${entityName?uncap_first}Service.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/${entityName?uncap_first}/${entityName?uncap_first}-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("${entityName?uncap_first}-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<${entityName}Entity> entitys = ${entityName?uncap_first}Service.findByIds(selectedItem);
        for (${entityName}Entity entity : entitys) {
            ${entityName?uncap_first}Service.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/${entityName?uncap_first}/${entityName?uncap_first}-list.do";
    }
    /**
     * 控件添加的方法 ========
     */
    <#list columns as po>
            <#list po.methodList as me>
                ${me}
            </#list>
    </#list>
    
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("${entityName?uncap_first}-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = ${entityName?uncap_first}Service.pagedQuery(page, propertyFilters);
        List<${entityName}Entity> beans = (List<${entityName}Entity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("${ftl_description}"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders(<#list originalColumns as po><#if po_index!=0>, </#if>"${po.fieldName}"</#list>);
        tableModel.setTableName("${tableName}");
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
    @RequestMapping("${entityName?uncap_first}-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders(<#list originalColumns as po><#if po_index!=0>, </#if>"${po.fieldName}"</#list>);
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "${bussiPackage}.${entityPackage}.entity.${entityName}Entity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/${entityName?uncap_first}/${entityName?uncap_first}-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void set${entityName}Service(${entityName}Service ${entityName?uncap_first}Service) {
        this.${entityName?uncap_first}Service = ${entityName?uncap_first}Service;
    }
    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
}
