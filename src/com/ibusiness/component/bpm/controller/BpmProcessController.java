package com.ibusiness.component.bpm.controller;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.bpm.cmd.ProcessDefinitionDiagramCmd;
import com.ibusiness.bpm.dao.BpmFlowNodeDao;
import com.ibusiness.bpm.dao.BpmNodeCountersignDao;
import com.ibusiness.bpm.dao.BpmNodeFormDao;
import com.ibusiness.bpm.dao.BpmNodeListenerDao;
import com.ibusiness.bpm.dao.BpmProcessDao;
import com.ibusiness.bpm.dao.BpmProcessVersionDao;
import com.ibusiness.bpm.entity.BpmProcess;
import com.ibusiness.bpm.entity.BpmProcessVersion;
import com.ibusiness.bpm.service.BpmComBusiness;
import com.ibusiness.bpm.service.BpmNodeUserService;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.service.MenuCommon;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.component.form.dao.ConfFormDao;
import com.ibusiness.component.form.entity.ConfForm;
import com.ibusiness.core.spring.MessageHelper;
import com.ibusiness.flowchart.entity.ConfFlowChart;
import com.ibusiness.flowchart.service.FlowChartCalculate;
import com.ibusiness.flowchart.service.FlowChartService;

/**
 * 流程管理控制器
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("bpm-process")
public class BpmProcessController {

    private BpmProcessDao bpmProcessDao;
    private BpmProcessVersionDao bpmProcessVersionDao;
    private BpmFlowNodeDao bpmFlowNodeDao;
    private BpmNodeUserService bpmNodeUserService;
    private BpmNodeListenerDao bpmNodeListenerDao;
    private BpmNodeFormDao bpmNodeFormDao;
    private BpmNodeCountersignDao bpmNodeCountersignDao;
    private ProcessEngine processEngine;
    private ConfFormDao confFormDao;
    private FlowChartService flowChartService;
    private MessageHelper messageHelper;
    
    /**
     * 流程列表
     * 
     * @param page
     * @param parameterMap
     * @param model
     * @return
     */
    @RequestMapping("bpm-process-list")
    public String bpmFlowList(@ModelAttribute Page page, @RequestParam("packageName") String packageName, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 取得表结构信息。
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        propertyFilters.add(new PropertyFilter("EQS_packageName", packageName));
        page = bpmProcessDao.pagedQuery(page, propertyFilters);
        // 
        model.addAttribute("page", page);
        model.addAttribute("packageName", packageName);
        model.addAttribute("typeId", "Bpm");
        
        return "ibusiness/bpm/bpm-process-list.jsp";
    }
    
    /**
     * 流程基础信息
     * @param id
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("bpm-process-input")
    public String input(@RequestParam(value = "packageName", required = false) String packageName, @RequestParam(value = "bpmId", required = false) String bpmId, Model model) {
        BpmProcess entity = null;
        if (!CommonUtils.isNull(bpmId)) {
            entity = bpmProcessDao.get(bpmId);
        } else {
            entity = new BpmProcess();
            entity.setPackageName(packageName);
        }
        model.addAttribute("model", entity);
        model.addAttribute("packageName", entity.getPackageName());
        model.addAttribute("bpmId", bpmId);
        model.addAttribute("tabType", "bpmBase");
        // 流程表单下拉菜单,只查询流程表单
        String formHql = "from ConfForm where packageName=? AND isBpmForm=1";
        List<ConfForm> formList = confFormDao.find(formHql, packageName);
        model.addAttribute("formList", formList);
        model.addAttribute("typeId", "Bpm");
        
        return "ibusiness/bpm/bpm-process-input.jsp";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("bpm-process-save")
    public String save(@ModelAttribute BpmProcess entity, RedirectAttributes redirectAttributes) throws Exception {
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(entity.getFlowName() + "001");
            // 保存菜单URL
            if (CommonUtils.isNull(entity.getFlowUrl())) {
                ConfForm confForm = confFormDao.get(entity.getFormId());
                entity.setFlowUrl(confForm.getFormURL() + "?flowType=0&flowId=" + entity.getId());
            }
            bpmProcessDao.saveInsert(entity);
            // 根据 初始化节点(逗号,分割) 初始化流程节点数据。
            String initTaskNode = entity.getInitTaskNode();
            // 
            ConfFlowChart startElm = null;
            ConfFlowChart endElm;
            if (!CommonUtils.isNull(initTaskNode)) {
                String[] taskNodes= initTaskNode.split(",");
                int y = 40;
                int x = 200;
                for (int i=0; i<taskNodes.length; i++) {
                    // 画开始节点
                    ConfFlowChart startNode = null;
                    if (0==i) {
                        startNode = new ConfFlowChart();
                        startNode.setId(UUID.randomUUID().toString());
                        startNode.setFlowId(entity.getId());
                        startNode.setItemId(UUID.randomUUID().toString());
                        startNode.setContext("{\"id\":\""+startNode.getItemId()+"\",\"name\":\"开始节点\",\"type\":\"StartNode\",\"title\":\"开始节点\",\"x\":\"70\",\"y\":\"65\",\"width\":\"50\",\"height\":\"50\",\"afterLineIds\":\"\"}");
                        startElm = startNode;
                    }
                    // 画任务节点
                    ConfFlowChart taskNode = new ConfFlowChart();
                    taskNode.setId(UUID.randomUUID().toString());
                    taskNode.setFlowId(entity.getId());
                    taskNode.setItemId(UUID.randomUUID().toString());
                    taskNode.setContext("{\"id\":\""+taskNode.getItemId()+"\",\"name\":\""+taskNodes[i]+"\",\"type\":\"TaskNode\",\"title\":\""+taskNodes[i]+"\",\"x\":\""+x+"\",\"y\":\""+(y+25)+"\",\"width\":\"100\",\"height\":\"50\",\"headLineIds\":\"\",\"afterLineIds\":\"\"}");
                    endElm = taskNode;
                    // 画线
                    ConfFlowChart lineBean = new ConfFlowChart();
                    lineBean.setId(UUID.randomUUID().toString());
                    lineBean.setFlowId(entity.getId());
                    lineBean.setItemId(UUID.randomUUID().toString());
                    // 计算画线的开始坐标
                    String realPoints = FlowChartCalculate.getInstance().calculateStartEndPos(JSONObject.fromObject(startElm.getContext()), JSONObject.fromObject(endElm.getContext()));
                    lineBean.setContext("{\"id\":\""+lineBean.getItemId()+"\",\"name\":\"线\",\"type\":\"Line\",\"title\":\""+taskNodes[i]+"\",\"x\":\"0\",\"y\":\""+y+"\",\"realPoints\":\""+realPoints+"\",\"startElmId\":\""+startElm.getItemId()+"\",\"endElmId\":\""+endElm.getItemId()+"\"}");
                    if (null != startNode) {
                        startNode.setContext("{\"id\":\""+startNode.getItemId()+"\",\"name\":\"开始节点\",\"type\":\"StartNode\",\"title\":\"开始节点\",\"x\":\"70\",\"y\":\"65\",\"width\":\"50\",\"height\":\"50\",\"afterLineIds\":\""+lineBean.getItemId()+"\"}");
                    } else {
                        String context = startElm.getContext();
                        context = context.replace("afterLineIds\":\"\"", "afterLineIds\":\""+lineBean.getItemId()+"\"");
                        startElm.setContext(context);
                        flowChartService.update(startElm);
                    }
                    // 设置任务节点和线的关系
                    taskNode.setContext("{\"id\":\""+taskNode.getItemId()+"\",\"name\":\""+taskNodes[i]+"\",\"type\":\"TaskNode\",\"title\":\""+taskNodes[i]+"\",\"x\":\""+x+"\",\"y\":\""+(y+25)+"\",\"width\":\"100\",\"height\":\"50\",\"headLineIds\":\""+lineBean.getItemId()+"\",\"afterLineIds\":\"\"}");
                    
                    // 添加泳道
                    ConfFlowChart laneBean = new ConfFlowChart();
                    laneBean.setId(UUID.randomUUID().toString());
                    laneBean.setFlowId(entity.getId());
                    laneBean.setItemId(UUID.randomUUID().toString());
                    laneBean.setContext("{\"id\":\""+laneBean.getItemId()+"\",\"name\":\"泳道\",\"type\":\"Lane\",\"title\":\""+taskNodes[i]+"\",\"x\":\"0\",\"y\":\""+y+"\",\"width\":\"35\",\"height\":\"110\"}");
                    // 
                    startElm = endElm;
                    // 结束节点
                    ConfFlowChart endNode = null;
                    ConfFlowChart endNodeLine = null;
                    if ((taskNodes.length-1)==i) {
                        // 结束节点
                        endNode = new ConfFlowChart();
                        endNode.setId(UUID.randomUUID().toString());
                        endNode.setFlowId(entity.getId());
                        endNode.setItemId(UUID.randomUUID().toString());
                        endNode.setContext("{\"id\":\""+endNode.getItemId()+"\",\"name\":\"结束节点\",\"type\":\"EndNode\",\"title\":\"结束\",\"x\":\""+(x+150)+"\",\"y\":\""+(y+25)+"\",\"width\":\"50\",\"height\":\"50\",\"headLineIds\":\"\"}");
                        // 画线
                        endNodeLine = new ConfFlowChart();
                        endNodeLine.setId(UUID.randomUUID().toString());
                        endNodeLine.setFlowId(entity.getId());
                        endNodeLine.setItemId(UUID.randomUUID().toString());
                        // 计算画线的开始坐标
                        String realPoints2 = FlowChartCalculate.getInstance().calculateStartEndPos(JSONObject.fromObject(startElm.getContext()), JSONObject.fromObject(endNode.getContext()));
                        endNodeLine.setContext("{\"id\":\""+endNodeLine.getItemId()+"\",\"name\":\"线\",\"type\":\"Line\",\"title\":\""+taskNodes[i]+"\",\"x\":\"0\",\"y\":\""+y+"\",\"realPoints\":\""+realPoints2+"\",\"startElmId\":\""+startElm.getItemId()+"\",\"endElmId\":\""+endNode.getItemId()+"\"}");
                        // 结束节点和线关联
                        endNode.setContext("{\"id\":\""+endNode.getItemId()+"\",\"name\":\"结束节点\",\"type\":\"EndNode\",\"title\":\"结束\",\"x\":\""+(x+150)+"\",\"y\":\""+(y+25)+"\",\"width\":\"50\",\"height\":\"50\",\"headLineIds\":\""+endNodeLine.getItemId()+"\"}");
                        // 设置任务节点和线的关系
                        taskNode.setContext("{\"id\":\""+taskNode.getItemId()+"\",\"name\":\""+taskNodes[i]+"\",\"type\":\"TaskNode\",\"title\":\""+taskNodes[i]+"\",\"x\":\""+x+"\",\"y\":\""+(y+25)+"\",\"width\":\"100\",\"height\":\"50\",\"headLineIds\":\""+lineBean.getItemId()+"\",\"afterLineIds\":\""+endNodeLine.getItemId()+"\"}");
                    }
                    // ===================================================================
                    // 持久化数据
                    if (null!=startNode) {
                        flowChartService.saveInsert(startNode);
                    }
                    flowChartService.saveInsert(taskNode);
                    flowChartService.saveInsert(lineBean);
                    flowChartService.saveInsert(laneBean);
                    if (null != endNode) {
                        flowChartService.saveInsert(endNode);
                        flowChartService.saveInsert(endNodeLine);
                    }
                    // 计算x坐标
                    x = x +200;
                    // 计算y坐标
                    y = y + 110;
                }
            }
            // =========================================================================
            // 更新信息时不需要重新发布。修改流程图时才需要
            // =========================================================================
            // 发布流程  --- 根据XML信息和流程名
            BpmComBusiness bpmComBusiness = new BpmComBusiness();
            // 创建一个BPMN XML数据
            String flowchartSql = "from ConfFlowChart where flowId=?";
            List<ConfFlowChart> confFlowCharts = flowChartService.find(flowchartSql,entity.getId());
            String xmlStr = bpmComBusiness.createBpmnXML(entity, confFlowCharts);
            // 发布
            bpmComBusiness.deployFlow(xmlStr, entity.getFlowName());
            
        } else {
            BpmProcess bpmProcess = bpmProcessDao.get(id);
            bpmProcess.setFlowTitle(entity.getFlowTitle());
            bpmProcess.setFormId(entity.getFormId());
            // 菜单保存
            if (CommonUtils.isNull(entity.getFlowUrl())) {
                ConfForm confForm = confFormDao.get(entity.getFormId());
                bpmProcess.setFlowUrl(confForm.getFormURL() + "?flowType=0&flowId=" + id);
            } else {
                bpmProcess.setFlowUrl(entity.getFlowUrl());
            }
            bpmProcessDao.update(bpmProcess);
        }
        // 添加,生成菜单,并且向系统管理员添加权限
        new MenuCommon().createMenu(entity.getPackageName(), entity.getFlowTitle(), entity.getFlowUrl());
        
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/bpm-process/bpm-process-list.do?packageName="+entity.getPackageName();
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("bpm-process-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<BpmProcess> entitys = bpmProcessDao.findByIds(selectedItem);
        for (BpmProcess entity : entitys) {
            // 删除部署到activiti中的流程定义
            // 根据流程版本编号 取得 流程编号
            if (!CommonUtils.isNull(entity.getVersionId())) {
                BpmProcessVersion bpmProcessVersion = bpmProcessVersionDao.get(entity.getVersionId());
                // 
                if (null != bpmProcessVersion) {
                    List<ProcessDefinition> processDefinitions = processEngine.getRepositoryService().createProcessDefinitionQuery()
                            .processDefinitionKey(bpmProcessVersion.getBpmProsessKey()).list();
                    if (null != processDefinitions) {
                        for (ProcessDefinition processDefinition : processDefinitions) {
                            // 删除发布信息
                            new BpmComBusiness().deleteDeployment(processDefinition.getDeploymentId());
                        }
                    }
                }
            }
            
            // 删除流程管理表
            bpmProcessDao.remove(entity);
            // 删除流程版本管理
            bpmProcessVersionDao.remove(bpmProcessVersionDao.get(entity.getVersionId()));
            // 删除流程节点配置表
            String flowNodeSql = "from BpmFlowNode where flowId=?";
            bpmFlowNodeDao.removeAll(bpmFlowNodeDao.find(flowNodeSql, entity.getId()));
            // 删除流程节点人员(参与者)配置表
            String nodeUserSql = "from BpmNodeUser where flowId=?";
            bpmNodeUserService.removeAll(bpmNodeUserService.find(nodeUserSql, entity.getId()));
            // 删除流程节点事件配置表
            String nodeListenerSql = "from BpmNodeListener where flowId=?";
            bpmNodeListenerDao.removeAll(bpmNodeListenerDao.find(nodeListenerSql, entity.getId()));
            // 删除流程节点表单配置表
            String nodeFormSql = "from BpmNodeForm where flowId=?";
            bpmNodeFormDao.removeAll(bpmNodeFormDao.find(nodeFormSql, entity.getId()));
            // 删除流程节点(会签)关联配置表
            String nodeCountersignSql = "from BpmNodeCountersign where flowId=?";
            bpmNodeCountersignDao.removeAll(bpmNodeCountersignDao.find(nodeCountersignSql, entity.getId()));
            // 删除流程对应的流程图
            String flowChartSql = "from ConfFlowChart where flowId=?";
            flowChartService.removeAll(flowChartService.find(flowChartSql, entity.getId()));
        }
        
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/bpm-process/bpm-process-list.do?packageName="+entitys.get(0).getPackageName();
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 流程定义图像
     * @param bpmProcessId
     * @param response
     * @throws Exception
     */
    @RequestMapping("bpm-process-graph")
    public void graphProcessDefinition(@RequestParam("bpmProcessId") String bpmProcessId, HttpServletResponse response) throws Exception {
        BpmProcess bpmProcess = bpmProcessDao.get(bpmProcessId);
        String versionId = bpmProcess.getVersionId();
        // 
        BpmProcessVersion bpmProcessVersion= bpmProcessVersionDao.get(versionId);
        String bpmProsessId = bpmProcessVersion.getBpmProsessId();
        Command<InputStream> cmd = new ProcessDefinitionDiagramCmd(bpmProsessId);

        InputStream is = processEngine.getManagementService().executeCommand(cmd);
        response.setContentType("image/png");

        IOUtils.copy(is, response.getOutputStream());
    }
    
    // ======================================================================
    @Resource
    public void setFlowChartService(FlowChartService flowChartService) {
        this.flowChartService = flowChartService;
    }
    @Resource
    public void setBpmProcessDao(BpmProcessDao bpmProcessDao) {
        this.bpmProcessDao = bpmProcessDao;
    }
    @Resource
    public void setBpmProcessVersionDao(BpmProcessVersionDao bpmProcessVersionDao) {
        this.bpmProcessVersionDao = bpmProcessVersionDao;
    }
    @Resource
    public void setBpmFlowNodeDao(BpmFlowNodeDao bpmFlowNodeDao) {
        this.bpmFlowNodeDao = bpmFlowNodeDao;
    }
    @Resource
    public void setBpmNodeUserService(BpmNodeUserService bpmNodeUserService) {
        this.bpmNodeUserService = bpmNodeUserService;
    }
    @Resource
    public void setBpmNodeListenerDao(BpmNodeListenerDao bpmNodeListenerDao) {
        this.bpmNodeListenerDao = bpmNodeListenerDao;
    }
    @Resource
    public void setBpmNodeFormDao(BpmNodeFormDao bpmNodeFormDao) {
        this.bpmNodeFormDao = bpmNodeFormDao;
    }
    @Resource
    public void setBpmNodeCountersignDao(BpmNodeCountersignDao bpmNodeCountersignDao) {
        this.bpmNodeCountersignDao = bpmNodeCountersignDao;
    }
    @Resource
    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }
    @Resource
    public void setConfFormDao(ConfFormDao confFormDao) {
        this.confFormDao = confFormDao;
    }
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
