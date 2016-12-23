package com.ibusiness.flowchart.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibusiness.bpm.dao.BpmFlowNodeDao;
import com.ibusiness.bpm.dao.BpmProcessDao;
import com.ibusiness.bpm.dao.BpmProcessVersionDao;
import com.ibusiness.bpm.entity.BpmFlowNode;
import com.ibusiness.bpm.entity.BpmNodeColums;
import com.ibusiness.bpm.entity.BpmNodeTable;
import com.ibusiness.bpm.entity.BpmProcess;
import com.ibusiness.bpm.entity.BpmProcessVersion;
import com.ibusiness.bpm.service.BpmComBusiness;
import com.ibusiness.bpm.service.BpmNodeColumsService;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.flowchart.entity.ConfFlowChart;
import com.ibusiness.flowchart.service.FlowChartService;

/**
 * 流程表单相关配置
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("flowchart")
public class FlowChartController {

    private FlowChartService flowChartService;
    private BpmNodeColumsService bpmNodeColumsService;
    private BpmFlowNodeDao bpmFlowNodeDao;
    private BpmProcessDao bpmProcessDao;
    private BpmProcessVersionDao bpmProcessVersionDao;
    private ProcessEngine processEngine;
    
    /**
     * 流程图信息初始化
     * 
     * @param flowId
     * @return
     */
    @RequestMapping("init-flow-chart")
    public String initFlowChart(@RequestParam(value = "packageName", required = false) String packageName, @RequestParam(value = "bpmId", required = false) String bpmId, Model model) {
        //
        model.addAttribute("packageName", packageName);
        model.addAttribute("bpmId", bpmId);
        
        // 包名
        model.addAttribute("packageName", packageName);
        // 关联流程
        BpmProcess bpmProcess= bpmProcessDao.get(bpmId);
        model.addAttribute("bpmProcess", bpmProcess);
        
        return "ibusiness/flowchart/flowchart-draw.jsp";
    }
    
    /**
     * 查询
     * @param flowId
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("query-flow-chart")
    @ResponseBody
    public String queryFlowChart(@RequestParam(value = "flowId") String flowId) {
        String hql = "from ConfFlowChart where flowId=? ";
        List<ConfFlowChart> list = flowChartService.find(hql, flowId);
        // 查询PLC图像数据
        return CommonUtils.getJsonFromList(list, null).toString();
    }
    /**
     * 取得流程名list
     * @return
     */
    @RequestMapping("flow-list")
    @ResponseBody
    public String getFlowList(@RequestParam(value = "flowId") String flowId, Model model) {
        List<BpmProcess> bpmProcessList= bpmProcessDao.getAll();
        if(bpmProcessList != null && bpmProcessList.size() > 0){
            // 
            StringBuffer sb = new StringBuffer();
            String selected;
            //遍历数据，构建select列表
            for(BpmProcess bean : bpmProcessList){
                selected = bean.getId().equals(flowId) ? "selected" : "";
                sb.append("<option value='"+bean.getId()+"' "+selected+">"+bean.getFlowTitle()+"</option>");
            }
            model.addAttribute("bpmFlowList", bpmProcessList);
            return sb.toString();
        }
        return "<option value=''>请选择...</option>";
    }

    /**
     * 保存
     * @return
     */
    @ResponseBody
    @RequestMapping("save-flow-chart")
    public String saveFlowChart(@RequestParam(value = "contexts") String contexts, @RequestParam(value = "flowId") String flowId) {
    	try {
    		// 保存在线画图信息流程图数据
            saveConfFlowChart(contexts, flowId);
            
            // 保存修改流程相关BPMN.XML信息
            saveBpmnXmlInfo(flowId);
            return flowId;
    	} catch (Exception e) {
    		return "failure";
    	}
//        return "/ibusiness/flowchart/draw.jsp";
    }

    /**
     * 保存修改流程相关BPMN XML信息
     */
    @SuppressWarnings("unchecked")
    private void saveBpmnXmlInfo(String flowId) {
        BpmProcess entity = bpmProcessDao.get(flowId);
        // 发布流程  --- 根据XML信息和流程名
        BpmComBusiness bpmComBusiness = new BpmComBusiness();
        // 创建一个BPMN XML数据
        String flowchartSql = "from ConfFlowChart where flowId=?";
        List<ConfFlowChart> confFlowCharts = flowChartService.find(flowchartSql, entity.getId());
        
        // 
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
        
        // 
        String xmlStr = bpmComBusiness.createBpmnXML(entity, confFlowCharts);
        // 发布
        Deployment deployment = bpmComBusiness.deployFlow(xmlStr, entity.getFlowName());
        // 根据deployment取得
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 
        String bpmProsessId = null;
        for (ProcessDefinition processDefinition : repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list()) {
            // 执行命令
            bpmProsessId = processDefinition.getId();
            break;
        }
        // 
        BpmProcessVersion bpmProcessVersion = bpmProcessVersionDao.get(entity.getVersionId());
        bpmProcessVersion.setBpmProsessId(bpmProsessId);
        bpmProcessVersionDao.save(bpmProcessVersion);
    }

    /**
     * 保存在线画图信息流程图数据
     * @param contexts
     * @param flowId
     */
    @SuppressWarnings("unchecked")
    private void saveConfFlowChart(String contexts, String flowId) {
        // 设置插入对象
        JSONArray jsonArray = JSONArray.fromObject(contexts);
        ConfFlowChart bean = null;
        // 第一步 先删除
        String hql = "from ConfFlowChart where flowId=?";
        List<ConfFlowChart> list = flowChartService.find(hql, flowId);
        flowChartService.removeAll(list);
        // 第二步 插入
        // 解析前台传递的JSONString
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jo = (JSONObject) jsonArray.get(i);
            if (!jo.containsKey("id")) {
                continue;
            }
            bean = new ConfFlowChart();
            bean.setId(UUID.randomUUID().toString());
            bean.setFlowId(flowId);
            bean.setItemId(jo.getString("id"));
            bean.setContext(jo.toString());
            // 插入
            flowChartService.saveInsert(bean);
        }
    }
    /**
     * 删除
     * @return
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("delete-flow-chart")
    public String deleteFlowChart(@RequestParam(value = "flowId") String flowId) {
        String hql = "from ConfFlowChart where flowId=?";
        List<ConfFlowChart> list = flowChartService.find(hql, flowId);
        if (null != list && list.size() > 0) {
            flowChartService.removeAll(list);
        }
//        return "redirect:/flowchart/query-flow-chart.do?flowId="+flowId;
        return "success";
    }
    
    /**
     * 显示启动流程的表单.
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("pop-conf-taskNode")
    public String confTaskNode(@RequestParam(value = "packageName", required = false) String packageName,
            @RequestParam("flowId") String flowId, @RequestParam("id") String id, Model model) throws Exception {
        String flowNodeSql = "from BpmFlowNode where flowId=? AND nodeCode=?";
        List<BpmFlowNode> bpmFlowNodeList = bpmFlowNodeDao.find(flowNodeSql, flowId, "sid-"+id);
        String nodeId = "";
        if (null != bpmFlowNodeList && bpmFlowNodeList.size() > 0) {
            nodeId = bpmFlowNodeList.get(0).getId();
        }
        // 实例化一个Map
        Map<String, List<BpmNodeColums>> map = new HashMap<String, List<BpmNodeColums>>();
        // 根据流程 和节点信息进行查询
        String bncsSql = "from BpmNodeColums where flowId=? AND nodeId=?";
        List<BpmNodeColums> bpmNodeColumsList = bpmNodeColumsService.find(bncsSql, flowId, nodeId);
        for (BpmNodeColums bpmNodeColums : bpmNodeColumsList) {
            if (map.containsKey(bpmNodeColums.getTableName())) {
                map.get(bpmNodeColums.getTableName()).add(bpmNodeColums);
            } else {
                List<BpmNodeColums> bncList = new ArrayList<BpmNodeColums>();
                bncList.add(bpmNodeColums);
                map.put(bpmNodeColums.getTableName(), bncList);
            }
        }
        // 
        List<BpmNodeTable> bpmNodeTables = new ArrayList<BpmNodeTable>();
        for (Map.Entry<String, List<BpmNodeColums>> entry : map.entrySet()) {
            BpmNodeTable bpmNodeTable = new BpmNodeTable();
            bpmNodeTable.setPackageName(packageName);
            bpmNodeTable.setFlowId(flowId);
            bpmNodeTable.setBpmNodeColumsList(entry.getValue());
            bpmNodeTables.add(bpmNodeTable);
        }
        model.addAttribute("bpmNodeTables", bpmNodeTables);
        model.addAttribute("packageName", packageName);
        model.addAttribute("flowId", flowId);
        model.addAttribute("nodeId", nodeId);
        return "ibusiness/flowchart/pop-conf-tasknode.jsp";
    }
    /**
     * 节点信息保存
     * @return
     */
    @RequestMapping("save-conf-node-colums")
    @ResponseBody
    public String saveConfNodeColums(@ModelAttribute BpmNodeTable bpmNodeTables, @RequestParam(value = "packageName", required = false) String packageName, 
            @RequestParam("flowId") String flowId, @RequestParam("nodeId") String nodeId) {
        // 
        List<BpmNodeColums> nodeColumsList = bpmNodeTables.getBpmNodeColumsList();
        if (null != nodeColumsList) {
            for (BpmNodeColums bpmNodeColums : nodeColumsList) {
                BpmNodeColums bean = bpmNodeColumsService.get(bpmNodeColums.getId());
                bean.setFcDisplay(bpmNodeColums.getFcDisplay());
                bean.setFcEdit(bpmNodeColums.getFcEdit());
                bpmNodeColumsService.update(bean);
            }
        }
        return "success";
    }

    // ======================================================================
    @Resource
    public void setFlowChartService(FlowChartService flowChartService) {
        this.flowChartService = flowChartService;
    }
    @Resource
    public void setBpmNodeColumsService(BpmNodeColumsService bpmNodeColumsService) {
        this.bpmNodeColumsService = bpmNodeColumsService;
    }
    @Resource
    public void setBpmFlowNodeDao(BpmFlowNodeDao bpmFlowNodeDao) {
        this.bpmFlowNodeDao = bpmFlowNodeDao;
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
    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }
}
