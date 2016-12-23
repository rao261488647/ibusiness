package ${bussiPackage}.${entityPackage}.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import org.springframework.web.multipart.MultipartFile;
import com.ibusiness.common.export.ExcelCommon;
import com.ibusiness.common.export.TableModel;
import com.ibusiness.security.util.SpringSecurityUtils;
import com.ibusiness.common.service.FormulaCommon;

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

import net.sf.json.JSONObject;

import com.ibusiness.common.model.ConfSelectItem;
import com.ibusiness.bpm.cmd.ProcessInstanceDiagramCmd;
import com.ibusiness.bpm.service.BpmComBusiness;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.security.util.SpringSecurityUtils;
import com.ibusiness.base.user.entity.UserBase;

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
    private ${entityName}Service ${entityName?uncap_first}Service;
    <#list subTab as sub>
        private ${sub.entityName}Service ${sub.entityName?uncap_first}Service;
    </#list>
   /**
     * 列表
     */
    @RequestMapping("${entityName?uncap_first}-list")
    public String list(@ModelAttribute Page page, @RequestParam(value = "flowId", required = false) String flowId, @RequestParam(value = "flowType", required = false) String flowType, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        propertyFilters.add(new PropertyFilter("EQI_doneflag", flowType));
        propertyFilters.add(new PropertyFilter("EQS_assigneeuser", SpringSecurityUtils.getCurrentUserId()));
        // 根据条件查询数据
        page.setOrderBy("createdatebpm");
        page.setOrder("DESC");
        page = ${entityName?uncap_first}Service.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        model.addAttribute("flowId", flowId);
        model.addAttribute("flowType", flowType);
        // 返回JSP
        return "codegenerate/${entityPackage}/${entityName?uncap_first}-list.jsp";
    }
    /**
     * 新建一条流程, 进入流程表单信息页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("${entityName?uncap_first}-input")
    public String input(@ModelAttribute Page page, @RequestParam(value = "flowId", required = false) String flowId, @RequestParam(value = "id", required = false) String id, Model model) {
        ${entityName}Entity entity = null;
        BpmComBusiness bpmComBusiness = new BpmComBusiness();
        if (!CommonUtils.isNull(id)) {
            entity = ${entityName?uncap_first}Service.get(id);
        } else {
            entity = new ${entityName}Entity();
            
            // 发起一个流程, 设置当前用户执行
            String userId = SpringSecurityUtils.getCurrentUserId();
            String executionId = bpmComBusiness.flowStart(flowId, userId);
            entity.setExecutionid(executionId);
            // 设置流程实例信息=========================
            Task task = bpmComBusiness.getTaskIdByExecutionId(entity.getExecutionid());
            entity.setCreatedatebpm(task.getCreateTime());
            entity.setNodename(task.getName());
            entity.setAssigneeuser(userId);
            entity.setUsername(CommonBusiness.getInstance().getUserBean(userId).getDisplayName());
            entity.setDoneflag(0);
            // 进行存储
            entity.setId(UUID.randomUUID().toString());
            entity.setDoneflag(0);
            // 流程标题
            entity.setTasktitle("${confForm.formTitle}");
            ${entityName?uncap_first}Service.insert(entity);
        }
        
        // 默认值公式
        entity = (${entityName}Entity) new FormulaCommon().defaultValue(entity, "${tableName}");
        
        model.addAttribute("model", entity);
        
        // 取得当前流程节点信息
        Task task = bpmComBusiness.getTaskIdByExecutionId(entity.getExecutionid());
        String nodeCode = task.getTaskDefinitionKey();
        // 根据流程和节点信息取得 流程指定节点的字段信息
        JSONObject json = bpmComBusiness.getNodeColumsInfo(flowId, entity.getExecutionid(), nodeCode, ${entityName}Entity.class);
        model.addAttribute("nodeColumsMap", json);
        
        // 子表信息
        Map<String, Object> map = new HashMap<String, Object>();
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(map);
        propertyFilters.add(new PropertyFilter("EQS_parentid", id));
        // 根据条件查询数据
        <#list subTab as sub>
	        page = ${sub.entityName?uncap_first}Service.pagedQuery(page, propertyFilters);
	        model.addAttribute("${sub.entityName?uncap_first}Page", page);
       </#list>
        
        // 流程ID
        model.addAttribute("flowId", flowId);
        // 取得用户
        List<UserBase> list = CommonBusiness.getInstance().getUserBaseList();
        model.addAttribute("userItems", list);
        // TODO
        model.addAttribute("userId", SpringSecurityUtils.getCurrentUserId());
        
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
     * 子表新建
     */
    @RequestMapping("${sub.entityName?uncap_first}-input")
    public String ${sub.entityName?uncap_first}Input(@RequestParam(value = "flowId", required = false) String flowId, @RequestParam("id") String id, @RequestParam("subId") String subId, Model model) {
        ${sub.entityName}Entity entity = ${sub.entityName?uncap_first}Service.get(subId);
        model.addAttribute("model", entity);
        model.addAttribute("parentid", id);
        model.addAttribute("flowId", flowId);
        
        // 取得主表中的 executionid
        ${entityName}Entity mainEntity = ${entityName?uncap_first}Service.get(id);
        BpmComBusiness bpmComBusiness = new BpmComBusiness();
        // 取得当前流程节点信息
        Task task = bpmComBusiness.getTaskIdByExecutionId(mainEntity.getExecutionid());
        String nodeCode = task.getTaskDefinitionKey();
        // 根据流程和节点信息取得 流程指定节点的字段信息
        JSONObject json = bpmComBusiness.getNodeColumsInfo(flowId, mainEntity.getExecutionid(), nodeCode, ${sub.entityName}Entity.class);
        model.addAttribute("nodeColumsMap", json);
        
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
     * 办理
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("${entityName?uncap_first}-complete")
    public String completeTask(@ModelAttribute ${entityName}Entity entity, @RequestParam(value = "flowId", required = false) String flowId, @RequestParam(value = "userId", required = false) String userId, RedirectAttributes redirectAttributes) throws Exception {
        BpmComBusiness bpmComBusiness = new BpmComBusiness();
        String executionId = null;
        // 
        if (CommonUtils.isNull(entity.getExecutionid())) {
            // 启动流程, 设置执行实例ID
            executionId = bpmComBusiness.flowStart(flowId, userId);
            entity.setExecutionid(executionId);
            // 设置流程实例信息=========================
            Task task = bpmComBusiness.getTaskIdByExecutionId(entity.getExecutionid());
            entity.setCreatedatebpm(task.getCreateTime());
            entity.setNodename(task.getName());
            entity.setAssigneeuser(userId);
            entity.setUsername(CommonBusiness.getInstance().getUserBean(userId).getDisplayName());
            entity.setDoneflag(0);
        } else {
            Task task = bpmComBusiness.getTaskIdByExecutionId(entity.getExecutionid());
            // 办理流程
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("assignee", userId);
            map.put("bean", entity);
            
            bpmComBusiness.completeTask(task.getId(), map);
            if (bpmComBusiness.isFinishedByTask(entity.getExecutionid())) {
                entity.setDoneflag(1);
            } else {
                entity.setDoneflag(0);
            }
            // 设置流程实例信息=========================
            entity.setCreatedatebpm(task.getCreateTime());
            entity.setNodename(task.getName());
            entity.setAssigneeuser(userId);
            entity.setUsername(CommonBusiness.getInstance().getUserBean(userId).getDisplayName());
        }
        // 再进行数据存储
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            entity.setDoneflag(0);
            id = entity.getId();
            ${entityName?uncap_first}Service.insert(entity);
        } else {
            ${entityName?uncap_first}Service.update(entity);
        }
        // 
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "办理成功");
        return "redirect:/${entityName?uncap_first}/${entityName?uncap_first}-list.do?flowType=0&flowId=" + flowId;
    }
    /**
     * 回退功能
     */
    @RequestMapping("${entityName?uncap_first}-rollback")
    public String rollback(@RequestParam("executionId") String executiond, @RequestParam("flowId") String flowId) {
        BpmComBusiness bpmComBusiness = new BpmComBusiness();
        Task task = bpmComBusiness.getTaskIdByExecutionId(executiond);
        
        new BpmComBusiness().rollback(task.getId());
        return "redirect:/${entityName?uncap_first}/${entityName?uncap_first}-list.do?flowType=0&flowId=" + flowId;
    }
    /**
     * 草稿
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("${entityName?uncap_first}-save-draft")
    public String saveDraft(@ModelAttribute ${entityName}Entity entity, @RequestParam(value = "flowId", required = false) String flowId, RedirectAttributes redirectAttributes) throws Exception {
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            entity.setDoneflag(0);
            ${entityName?uncap_first}Service.insert(entity);
            id = entity.getId();
        } else {
            ${entityName?uncap_first}Service.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/${entityName?uncap_first}/${entityName?uncap_first}-input.do?flowId=" + flowId + "&id=" + id;
    }
    /**
     * 删除一条流程信息
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("${entityName?uncap_first}-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, @RequestParam(value = "flowId", required = false) String flowId, RedirectAttributes redirectAttributes) {
        List<${entityName}Entity> entitys = ${entityName?uncap_first}Service.findByIds(selectedItem);
        // 实例化BPM流程共用类对象
        BpmComBusiness bpmComBusiness = new BpmComBusiness();
        for (${entityName}Entity entity : entitys) {
            ${entityName?uncap_first}Service.remove(entity);
            // 删除流程实例
            bpmComBusiness.deleteProcessInstance(entity.getExecutionid());
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/${entityName?uncap_first}/${entityName?uncap_first}-list.do?flowType=0&flowId=" + flowId;
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
        return "redirect:/${entityName?uncap_first}/${entityName?uncap_first}-input.do?flowId=" + flowId + "&id=" + entity.getParentid();
    }
    /**
     * 子表删除
     */
    @RequestMapping("${sub.entityName?uncap_first}-remove")
    public String ${sub.entityName?uncap_first}Remove(@RequestParam("${sub.entityName?uncap_first}SelectedItem") List<String> selectedItem, @RequestParam(value = "flowId", required = false) String flowId, RedirectAttributes redirectAttributes) throws Exception {
        List<${sub.entityName}Entity> entitys = ${sub.entityName?uncap_first}Service.findByIds(selectedItem);
        for (${sub.entityName}Entity entity : entitys) {
            ${sub.entityName?uncap_first}Service.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");
        return "redirect:/${entityName?uncap_first}/${entityName?uncap_first}-input.do?flowId=" + flowId + "&id=" + entitys.get(0).getParentid();
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
        tableModel.setTableName("${tableName}");
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
    public String import${sub.entityName}Export(@RequestParam("attachment") MultipartFile attachment, @RequestParam(value = "flowId", required = false) String flowId, @RequestParam(value = "parentid", required = false) String parentid, HttpServletResponse response) {
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
        return "redirect:/${entityName?uncap_first}/${entityName?uncap_first}-input.do?flowId=" + flowId + "&id=" + parentid;
    }
    </#list>

    /**
     * 流程定义图像
     * @param bpmProcessId
     * @param response
     * @throws Exception
     */
    @RequestMapping("${entityName?uncap_first}-graph")
    public void graphProcessDefinition(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
        ${entityName}Entity entity = ${entityName?uncap_first}Service.get(id);
        BpmComBusiness bpmComBusiness = new BpmComBusiness();
        if (CommonUtils.isNull(entity.getExecutionid())) {
            return;
        }
        Task task = bpmComBusiness.getTaskIdByExecutionId(entity.getExecutionid());
        Command<InputStream> cmd = new ProcessInstanceDiagramCmd(task.getProcessInstanceId());
        // 
        InputStream is = bpmComBusiness.getProcessEngine().getManagementService().executeCommand(cmd);
        response.setContentType("image/png");

        IOUtils.copy(is, response.getOutputStream());
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
    <#list subTab as sub>
        @Resource
	    public void set${sub.entityName}Service(${sub.entityName}Service ${sub.entityName?uncap_first}Service) {
	        this.${sub.entityName?uncap_first}Service = ${sub.entityName?uncap_first}Service;
	    }
    </#list>
}
