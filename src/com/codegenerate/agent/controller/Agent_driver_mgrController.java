package com.codegenerate.agent.controller;

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

import com.codegenerate.agent.entity.Agent_driver_mgrEntity;
import com.codegenerate.agent.service.Agent_driver_mgrService;

/**   
 * @Title: Controller
 * @Description: 代驾司机列表管理页面
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("agent_driver_mgr")
public class Agent_driver_mgrController {

    private MessageHelper messageHelper;
    private com.ibusiness.doc.store.StoreConnector storeConnector;
    private Agent_driver_mgrService agent_driver_mgrService;
   /**
     * 列表
     */
    @RequestMapping("agent_driver_mgr-list")
    public String list(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 添加当前公司(用户范围)ID查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        // 根据条件查询数据
        page = agent_driver_mgrService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "codegenerate/agent/agent_driver_mgr-list.jsp";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("agent_driver_mgr-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        Agent_driver_mgrEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = agent_driver_mgrService.get(id);
        } else {
            entity = new Agent_driver_mgrEntity();
        }
        
        // 默认值公式
        entity = (Agent_driver_mgrEntity) new FormulaCommon().defaultValue(entity, "IB_AGENT_DRIVER_MGR");
        
        model.addAttribute("model", entity);
        
        // 在controller中设置页面控件用的数据
                Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> sexFTCMap= CommonBusiness.getInstance().getFormTableColumnMap("IB_AGENT_DRIVER_MGR", "agentdrivermgr");List<com.ibusiness.common.model.ConfSelectItem> sexItems = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson(sexFTCMap.get("SEX").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);model.addAttribute("sexItems", sexItems);
        return "codegenerate/agent/agent_driver_mgr-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("agent_driver_mgr-save")
    public String save(@ModelAttribute Agent_driver_mgrEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            agent_driver_mgrService.insert(entity);
        } else {
            agent_driver_mgrService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/agent_driver_mgr/agent_driver_mgr-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("agent_driver_mgr-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<Agent_driver_mgrEntity> entitys = agent_driver_mgrService.findByIds(selectedItem);
        for (Agent_driver_mgrEntity entity : entitys) {
            agent_driver_mgrService.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/agent_driver_mgr/agent_driver_mgr-list.do";
    }
    /**
     * 控件添加的方法 ========
     */
    
    /**
     * excel导出
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("agent_driver_mgr-export")
    public void excelExport(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        // 根据当前公司(用户范围)ID进行查询
    	propertyFilters = CommonBusiness.getInstance().editPFByScopeId(propertyFilters);
        page = agent_driver_mgrService.pagedQuery(page, propertyFilters);
        List<Agent_driver_mgrEntity> beans = (List<Agent_driver_mgrEntity>) page.getResult();

        TableModel tableModel = new TableModel();
        // excel文件名
        tableModel.setExcelName("代驾司机列表管理页面"+CommonUtils.getInstance().getCurrentDateTime());
        // 列名
        tableModel.addHeaders("scopeid", "id", "num", "name", "workcell", "cell", "eid", "sex", "address", "publiahgrd", "birth", "nation", "drivernum", "driverfilenum", "predriverdate", "precartype", "validstartdate", "validtrem", "carname", "contracterm");
        tableModel.setTableName("IB_AGENT_DRIVER_MGR");
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
    @RequestMapping("agent_driver_mgr-importExcel")
    public String importExport(@RequestParam("attachment") MultipartFile attachment, HttpServletResponse response) {
        try {
            File file = new File("test.xls"); 
            attachment.transferTo(file);
            // 
            TableModel tableModel = new TableModel();
            // 列名
            tableModel.addHeaders("scopeid", "id", "num", "name", "workcell", "cell", "eid", "sex", "address", "publiahgrd", "birth", "nation", "drivernum", "driverfilenum", "predriverdate", "precartype", "validstartdate", "validtrem", "carname", "contracterm");
            // 导入
            new ExcelCommon().uploadExcel(file, tableModel, "com.codegenerate.agent.entity.Agent_driver_mgrEntity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/agent_driver_mgr/agent_driver_mgr-list.do";
    }
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
    @Resource
    public void setAgent_driver_mgrService(Agent_driver_mgrService agent_driver_mgrService) {
        this.agent_driver_mgrService = agent_driver_mgrService;
    }
    @Resource
	public void setStoreConnector(com.ibusiness.doc.store.StoreConnector storeConnector) {
	    this.storeConnector = storeConnector;
	}
}
