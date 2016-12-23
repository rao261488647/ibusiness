package ${bussiPackage}.${entityPackage}.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import org.springframework.web.multipart.MultipartFile;
import com.ibusiness.common.export.ExcelCommon;
import com.ibusiness.common.export.TableModel;
import com.ibusiness.common.service.FormulaCommon;
import com.ibusiness.common.service.CommonBusiness;

import com.ibusiness.security.util.SpringSecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;

import com.ibusiness.common.model.ConfSelectItem;
import com.ibusiness.bpm.cmd.ProcessInstanceDiagramCmd;
import com.ibusiness.bpm.service.BpmComBusiness;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.security.util.SpringSecurityUtils;

import ${bussiPackage}.${entityPackage}.entity.${entityName}Entity;
import ${bussiPackage}.${entityPackage}.service.${entityName}Service;
<#list subTab as sub>
import ${bussiPackage}.${sub.packageName}.entity.${sub.entityName}Entity;
import ${bussiPackage}.${sub.packageName}.service.${sub.entityName}Service;
</#list>

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
    <#list subTab as sub>
        private ${sub.entityName}Service ${sub.entityName?uncap_first}Service;
    </#list>
   /**
     * 列表
     */
    @RequestMapping("${entityName?uncap_first}-list")
    public String list(@ModelAttribute Page page,  @RequestParam Map<String, Object> parameterMap, Model model) {
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
     * 进入主表表单编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("${entityName?uncap_first}-input")
    public String input(@ModelAttribute Page page,  @RequestParam(value = "id", required = false) String id, Model model) {
        ${entityName}Entity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = ${entityName?uncap_first}Service.get(id);
        } else {
            entity = new ${entityName}Entity();
        }
        // 默认值公式
        entity = (${entityName}Entity) new FormulaCommon().defaultValue(entity, "${tableName}");
        
        model.addAttribute("model", entity);
        // 子表信息
        Map<String, Object> map = new HashMap<String, Object>();
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(map);
        propertyFilters.add(new PropertyFilter("EQS_parentid", id));
        // 根据条件查询数据
        <#list subTab as sub>
	        page = ${sub.entityName?uncap_first}Service.pagedQuery(page, propertyFilters);
	        model.addAttribute("${sub.entityName?uncap_first}Page", page);
       </#list>
        
        // 在controller中设置页面控件用的数据
        <#list columns as po>
            <#list po.modelAttributeList as ma>
                ${ma}
            </#list>
        </#list>
        return "codegenerate/${entityPackage}/${entityName?uncap_first}-input.jsp";
    }
    /**
     * 控件添加的方法 ========
     */
    <#list columns as po>
            <#list po.methodList as me>
                ${me}
            </#list>
    </#list>
        
    <#list subTab as sub>
    /**
     * 添加子表控件的方法 ========
     */
    <#list sub.columns as po>
        <#list po.methodList as me>
            ${me}
        </#list>
    </#list>
    /**
     * 子表新建
     */
    @RequestMapping("${sub.entityName?uncap_first}-input")
    public String ${sub.entityName?uncap_first}Input(@RequestParam(value = "flowId", required = false) String flowId, @RequestParam("id") String id, @RequestParam("subId") String subId, Model model) {
        ${sub.entityName}Entity entity = ${sub.entityName?uncap_first}Service.get(subId);
        model.addAttribute("model", entity);
        model.addAttribute("parentid", id);
        model.addAttribute("flowId", flowId);
        // 在controller中设置页面控件用的数据
        <#list sub.columns as po>
            <#list po.modelAttributeList as ma>
                ${ma}
            </#list>
        </#list>
        return "codegenerate/${entityPackage}/${sub.entityName?uncap_first}-input.jsp";
    }
    </#list>
    /**
     * 保存主表
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("${entityName?uncap_first}-save")
    public String saveDraft(@ModelAttribute ${entityName}Entity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            ${entityName?uncap_first}Service.insert(entity);
            id = entity.getId();
        } else {
            ${entityName?uncap_first}Service.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/${entityName?uncap_first}/${entityName?uncap_first}-input.do?id=" + id;
    }
    <#list subTab as sub>
    /**
     * 子表保存
     */
    @RequestMapping("${sub.entityName?uncap_first}-save")
    public String subSave(@ModelAttribute ${sub.entityName}Entity entity, @RequestParam(value = "flowId", required = false) String flowId, @RequestParam(value = "parentid", required = false) String parentid, RedirectAttributes redirectAttributes) throws Exception {
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            id = UUID.randomUUID().toString();
            entity.setId(id);
            entity.setParentid(parentid);
            ${sub.entityName?uncap_first}Service.insert(entity);
        } else {
            ${sub.entityName?uncap_first}Service.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/${entityName?uncap_first}/${entityName?uncap_first}-input.do?id=" + entity.getParentid();
    }
    /**
     * 子表 excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("${sub.entityName?uncap_first}-export")
    public void excel${sub.entityName}Export(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = ${sub.entityName?uncap_first}Service.pagedQuery(page, propertyFilters);
        List<${sub.entityName}Entity> beans = (List<${sub.entityName}Entity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("${ftl_description}"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders(<#list sub.originalColumns as subItem><#if subItem_index!=0>, </#if>"${subItem.fieldName}"</#list>);
        tableModel.setTableName("${sub.tableName}");
        tableModel.setData(beans);
        try {
            new ExcelCommon().exportExcel(response, tableModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 子表 excel导入
     */
    @RequestMapping("${sub.entityName?uncap_first}-importExcel")
    public String import${sub.entityName}Export(@RequestParam("attachment") MultipartFile attachment, @RequestParam(value = "parentid", required = false) String parentid, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders(<#list sub.originalColumns as subItem><#if subItem_index!=0>, </#if>"${subItem.fieldName}"</#list>);
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "${bussiPackage}.${entityPackage}.entity.${sub.entityName}Entity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/${entityName?uncap_first}/${entityName?uncap_first}-input.do?id=" + parentid;
    }
    /**
     * 删除子表信息
     */
    @RequestMapping("${sub.entityName?uncap_first}-remove")
    public String ${sub.entityName?uncap_first}Remove(@RequestParam("${sub.entityName?uncap_first}SelectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<${sub.entityName}Entity> entitys = ${sub.entityName?uncap_first}Service.findByIds(selectedItem);
        String parentid = null;
        for (${sub.entityName}Entity entity : entitys) {
            parentid = entity.getParentid();
            ${sub.entityName?uncap_first}Service.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");
        
        return "redirect:/${entityName?uncap_first}/${entityName?uncap_first}-input.do?id=" + parentid;
    }
    </#list>
    /**
     * 删除一条主表信息
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("${entityName?uncap_first}-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, @RequestParam(value = "flowId", required = false) String flowId, RedirectAttributes redirectAttributes) {
        List<${entityName}Entity> entitys = ${entityName?uncap_first}Service.findByIds(selectedItem);
        for (${entityName}Entity entity : entitys) {
            ${entityName?uncap_first}Service.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/${entityName?uncap_first}/${entityName?uncap_first}-list.do?flowId=" + flowId;
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
    <#list subTab as sub>
        @Resource
	    public void set${sub.entityName}Service(${sub.entityName}Service ${sub.entityName?uncap_first}Service) {
	        this.${sub.entityName?uncap_first}Service = ${sub.entityName?uncap_first}Service;
	    }
    </#list>
}
