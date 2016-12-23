package com.ibusiness.component.portal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.component.portal.dao.ComponentDao;
import com.ibusiness.component.portal.entity.ConfComponent;
import com.ibusiness.core.spring.MessageHelper;

/**
 * 业务模块组件用controller。业务模块下包括各个业务模块的内容。
 * 每个业务模块下都有1.存储控制器 2.表单设计器 3.流程设计器
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("component")
public class ComponentController {
    private MessageHelper messageHelper;
    private ComponentDao componentDao;
    /**
     * 后台管理首页
     * @param parameterMap
     * @param model
     * @return
     */
    @RequestMapping("component-show")
    public String show(@RequestParam Map<String, Object> parameterMap, Model model) {
        model.addAttribute("parentId", 0);
        // 返回JSP
        return "ibusiness/component/portal/component-show.jsp";
    }
    /**
     * 菜单跳转控制方法
     * 
     * @param parameterMap
     * @param model
     * @return
     */
    @RequestMapping("component-action")
    public String moduleAction(Model model, @RequestParam(value = "packageName", required = false) String packageName,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "tableName", required = false) String tableName, 
            @RequestParam(value = "formId", required = false) String formId,
            @RequestParam(value = "flowId", required = false) String flowId) {
        if ("root".equals(packageName)) {
            // 返回添加业务组件管理页面
            return "redirect:/component/component-list.do";
        }
        if ("Table".equals(typeId)) {
            // 跳转到表存储列表页面
            return "redirect:/table/conf-table-list.do?packageName=" + packageName;
        } else if ("tables".equals(typeId)) {
            // 跳转到指定表信息页面
            return "redirect:/table/conf-table-column-list.do?tableName=" + tableName + "&isBpmTable=2";
        }
        if ("Form".equals(typeId)) {
            // 跳转到表单列表页面
            return "redirect:/form/conf-form-list.do?packageName=" + packageName;
        } else if ("forms".equals(typeId)) {
            // 跳转到指定表单信息页面
            return "redirect:/form/conf-form-input.do?packageName=" + packageName + "&formId=" + formId + "&isBpmForm=2";
        }
        if ("BpmTable".equals(typeId)) {
            // 跳转到流程表存储列表页面
            return "redirect:/table/conf-bpmTable-list.do?packageName=" + packageName;
        } else if ("bpmTables".equals(typeId)) {
            // 跳转到指定表信息页面
            return "redirect:/table/conf-table-column-list.do?tableName=" + tableName + "&isBpmTable=1";
        }
        if ("BpmForm".equals(typeId)) {
            // 跳转到流程表单列表页面
            return "redirect:/form/conf-bpmForm-list.do?packageName=" + packageName;
        } else if ("bpmForms".equals(typeId)) {
            // 跳转到指定表单信息页面
            return "redirect:/form/conf-form-input.do?packageName=" + packageName + "&formId=" + formId + "&isBpmForm=1";
        }
        if ("Bpm".equals(typeId)) {
            // 跳转到流程页面
            return "redirect:/bpm-process/bpm-process-list.do?packageName=" + packageName;
        } else if ("flows".equals(typeId)) {
            // 跳转到指定流程信息页面
            return "redirect:/bpm-process/bpm-process-input.do?packageName=" + packageName + "&bpmId=" + flowId;
        }
        // 返回
        return "redirect:/component/component-list.do";
    }
    /**
     * 列表
     * @param page
     * @param parameterMap
     * @param model
     * @return
     */
    @RequestMapping("component-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 父结点ID为0的是业务分类模块
        parameterMap.put("filter_EQS_parentid", "0");
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据条件查询数据
        page = componentDao.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "ibusiness/component/portal/component-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("component-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        ConfComponent entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = componentDao.get(id);
        } else {
            entity = new ConfComponent();
        }
        model.addAttribute("model", entity);
        
        return "ibusiness/component/portal/component-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("serviceModule-save")
    public String save(@ModelAttribute ConfComponent entity, RedirectAttributes redirectAttributes) throws Exception {
        String id = entity.getId();
        // 转换全小写
        entity.setPackagename(CommonUtils.toLowerCase(entity.getPackagename()));
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            componentDao.insert(entity);
            // 表
            ConfComponent tableEntity = new ConfComponent();
            tableEntity.setId(UUID.randomUUID().toString());
            tableEntity.setParentid(entity.getId());
            tableEntity.setPackagename(entity.getPackagename());
            tableEntity.setModulename("表存储设计器");
            tableEntity.setTypeid("Table");
            // 表单
            ConfComponent formEntity = new ConfComponent();
            formEntity.setId(UUID.randomUUID().toString());
            formEntity.setParentid(entity.getId());
            formEntity.setPackagename(entity.getPackagename());
            formEntity.setModulename("表单设计器");
            formEntity.setTypeid("Form");
            // 流程表
            ConfComponent bpmTableEntity = new ConfComponent();
            bpmTableEntity.setId(UUID.randomUUID().toString());
            bpmTableEntity.setParentid(entity.getId());
            bpmTableEntity.setPackagename(entity.getPackagename());
            bpmTableEntity.setModulename("流程表设计器");
            bpmTableEntity.setTypeid("BpmTable");
            // 流程表单
            ConfComponent bpmFormEntity = new ConfComponent();
            bpmFormEntity.setId(UUID.randomUUID().toString());
            bpmFormEntity.setParentid(entity.getId());
            bpmFormEntity.setPackagename(entity.getPackagename());
            bpmFormEntity.setModulename("流程表单设计器");
            bpmFormEntity.setTypeid("BpmForm");
            // 流程
            ConfComponent bpmEntity = new ConfComponent();
            bpmEntity.setId(UUID.randomUUID().toString());
            bpmEntity.setParentid(entity.getId());
            bpmEntity.setPackagename(entity.getPackagename());
            bpmEntity.setModulename("流程设计器");
            bpmEntity.setTypeid("Bpm");
            // 插入
            componentDao.insert(tableEntity);
            componentDao.insert(formEntity);
            componentDao.insert(bpmTableEntity);
            componentDao.insert(bpmFormEntity);
            componentDao.insert(bpmEntity);
        } else {
            componentDao.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/component/component-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("serviceModule-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        // 删除指定业务模块
        serviceModuleRemoveList(selectedItem);
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");
        return "redirect:/component/component-list.do";
    }
    /**
     * 删除指定业务模块
     * 
     * @param selectedItem
     */
    @SuppressWarnings("unchecked")
    private void serviceModuleRemoveList(List<String> selectedItem) {
        List<String> items = new ArrayList<String>();
        List<ConfComponent> entitys = componentDao.findByIds(selectedItem);
        for (ConfComponent entity : entitys) {
            String hql = "from ConfComponent csm WHERE csm.parentid=?";
            List<ConfComponent> list = componentDao.find(hql, entity.getId());
            for (ConfComponent bean : list) {
                items.add(bean.getId());
            }
            componentDao.remove(entity);
        }
        if (items.size() > 0) {
            serviceModuleRemoveList(items);
        }
    }
    // ======================================================================
    @Resource
    public void setComponentDao(ComponentDao componentDao) {
        this.componentDao = componentDao;
    }
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
