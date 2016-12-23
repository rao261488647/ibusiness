package com.ibusiness.bpm.service;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.ibusiness.bpm.cmd.RollbackTaskCmd;
import com.ibusiness.bpm.cmd.SyncProcessCmd;
import com.ibusiness.bpm.dao.BpmFlowNodeDao;
import com.ibusiness.bpm.dao.BpmProcessDao;
import com.ibusiness.bpm.dao.BpmProcessVersionDao;
import com.ibusiness.bpm.entity.BpmFlowNode;
import com.ibusiness.bpm.entity.BpmNodeColums;
import com.ibusiness.bpm.entity.BpmProcess;
import com.ibusiness.bpm.entity.BpmProcessVersion;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.spring.ApplicationContextHelper;
import com.ibusiness.flowchart.entity.ConfFlowChart;
import com.ibusiness.flowchart.service.FlowChartService;
import com.ibusiness.security.util.SpringSecurityUtils;

/**
 * BPM流程共用类
 * 
 * @author JiangBo
 *
 */
public class BpmComBusiness {
    /**
     * 启动项目时候初始化所有流程
     */
    @SuppressWarnings("unchecked")
    public void initBpmFlow() {
        // 1.取得所有已经发布流程,存在Map中
        Map<String, String> processMap = new HashMap<String, String>();
        for (ProcessDefinition processDefinition : getProcessEngine().getRepositoryService().createProcessDefinitionQuery().list()) {
            processMap.put(processDefinition.getKey(), processDefinition.getName());
        }
        // 2.取得所有流程
        List<BpmProcess> bpmProcessList = getBpmProcessDao().getAll();
        // 3.判断流程是否以发布，如果未发布则根据流程曲线存储的信息发布流程。
        for (BpmProcess bean : bpmProcessList) {
            if (processMap.containsKey(bean.getFlowName())) {
                continue;
            }
            // 创建一个BPMN XML数据
            String flowchartSql = "from ConfFlowChart where flowId=?";
            List<ConfFlowChart> confFlowCharts = getFlowChartService().find(flowchartSql, bean.getId());
            String xmlStr = createBpmnXML(bean, confFlowCharts);
            // 发布
            deployFlow(xmlStr, bean.getFlowName());
        }
    }
    /**
     * 启动流程
     */
    public String flowStart(String flowId, String userId) {
        // 先设置登录用户
        IdentityService identityService = getProcessEngine().getIdentityService();
        identityService.setAuthenticatedUserId(SpringSecurityUtils.getCurrentUserId());
        // Get Activiti services
        RuntimeService runtimeService = getProcessEngine().getRuntimeService();
        // Start a process instance
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("assignee", userId);
        BpmProcessVersion bpmProcessVersion = getBpmProcessVersion(flowId);
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(bpmProcessVersion.getBpmProsessId(), params);
        return processInstance.getId();
    }
    /**
     * 办理流程
     */
    public void completeTask(String taskId, Map<String, Object> map) {
        // Get Activiti services
        TaskService taskService = getProcessEngine().getTaskService();
        // complete a task
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("assignee", userId);
        taskService.complete(taskId, map);
    }
    /**
     * 判断task流程是否结束
     */
    public Boolean isFinishedByTask(String prosessId) {
        RuntimeService runtimeService = getProcessEngine().getRuntimeService();
        if (null == runtimeService.createProcessInstanceQuery().processInstanceId(prosessId).singleResult()) {
            return true;
        }
        return false;
    }
    /**
     * 待办任务（个人任务）---指定用户ID的
     * 
     * @return
     */
    public List<Task> listPersonalTasks(String userId) {
        TaskService taskService = getProcessEngine().getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).active().list();
        return tasks;
    }
    /**
     * 已办任务（历史任务）---指定用户ID的
     * 
     * @return
     */
    public List<HistoricTaskInstance> listHistoryTasks(String userId) {
        HistoryService historyService = getProcessEngine().getHistoryService();
        List<HistoricTaskInstance> historicTasks = historyService.createHistoricTaskInstanceQuery().taskAssignee(userId).finished().list();
        return historicTasks;
    }
    /**
     * 根据流程和节点信息取得 流程指定节点的字段信息
     * 
     * @param flowId
     * @param executionid
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public JSONObject getNodeColumsInfo(String flowId, String executionid, String nodeCode, Class clazz) {
        if (CommonUtils.isNull(flowId)) {
            return new JSONObject();
        }
        // 反射类取得类中各个属性字段名
        Map<String, String> clazzMap = new HashMap<String, String>();
        Field field[] = clazz.getDeclaredFields();
        for (Field f : field) {
            String key = CommonUtils.toUpperCase(f.getName());
            clazzMap.put(key, f.getName());
        }
        // 取得节点信息
        String sql = " from BpmFlowNode where flowId=? AND nodeCode=?";
        List<BpmFlowNode> flowNodeList = getBpmFlowNodeDao().find(sql, flowId, nodeCode);
        BpmFlowNode bpmFlowNode = null;
        if (null == flowNodeList || flowNodeList.size() < 1) {
            return new JSONObject();
        } else {
            bpmFlowNode = flowNodeList.get(0);
        }
        // 取得指定节点的字段信息
        String nodeColumsSql = " from BpmNodeColums where flowId=? AND nodeId=?";
        List<BpmNodeColums> bpmNodeColumsList = getBpmNodeColumsService().find(nodeColumsSql, flowId, bpmFlowNode.getId());
        Map<String, BpmNodeColums> nodeColumsMap = new HashMap<String, BpmNodeColums>();
        for (BpmNodeColums bpmNodeColums : bpmNodeColumsList) {
            if (clazzMap.containsKey(bpmNodeColums.getTableColumn())) {
                nodeColumsMap.put(clazzMap.get(bpmNodeColums.getTableColumn()), bpmNodeColums);
            }
        }
        JSONObject json = JSONObject.fromObject(nodeColumsMap);
        return json;
    }
    // ~ ==================================国内特色流程====================================
    /**
     * 回退任务
     * 
     * @return
     */
    public void rollback(String taskId) {
        Command<Integer> cmd = new RollbackTaskCmd(taskId);
        getProcessEngine().getManagementService().executeCommand(cmd);
    }
    /**
     * 根据执行实例ID查询taskID
     * @param flowId
     * @return
     */
    public Task getTaskIdByExecutionId(String executionId) {
        // 取得TaskId
        TaskService taskservice = getProcessEngine().getTaskService();
        Task task = taskservice.createTaskQuery().executionId(executionId).singleResult();
        return task;
    }
    /**
     * 取得BPM流程版本管理信息
     * @param flowId
     * @return
     */
    private BpmProcessVersion getBpmProcessVersion(String flowId) {
        BpmProcess bpmProcess = getBpmProcessDao().get(flowId);
        if (null == bpmProcess) {
            return null;
        }
        BpmProcessVersion bpmProcessVersion = getBpmProcessVersionDao().get(bpmProcess.getVersionId());
        return bpmProcessVersion;
    }
    // ==============================================================
    /**
     * 删除部署到activiti中的流程定义
     * @param xml
     * @param xmlName
     */
    public void deleteDeployment(String deploymentId) {
        // 删除发布信息
        RepositoryService repositoryService = getProcessEngine().getRepositoryService();
        // 级联删除,会删除和当前规则相关的所有信息，包括历史
        repositoryService.deleteDeployment(deploymentId, true);
    }
    
    /**
     * 发布流程  --- 根据XML信息和流程名
     * 
     * @param xml
     * @param xmlName
     */
    public Deployment deployFlow(String xml, String xmlName) {
        ProcessEngine processEngine = getProcessEngine();
        // Get Activiti services
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // Deploy the process definition 部署
        try {
            DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addInputStream(xmlName+".bpmn20.xml", new ByteArrayInputStream(xml.getBytes("UTF-8")));
            Deployment deployment = deploymentBuilder.deploy();
            
            // 根据deployment取得
            for (ProcessDefinition processDefinition : repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list()) {
                // 执行命令
                syncProcessDefinition(processDefinition.getId());
            }
            return deployment;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    /**
     * 执行一个命令--创建流程用数据
     * @param processDefinitionId
     */
    private void syncProcessDefinition(String processDefinitionId) {
        getProcessEngine().getManagementService().executeCommand(new SyncProcessCmd(processDefinitionId));
    }
    /**
     * 创建一个BPMN XML数据
     */
    public String createBpmnXML(BpmProcess bpmProcess, List<ConfFlowChart> confFlowCharts) {
        // 流程信息
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        xml = xml + "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.iBusiness.com\">";
        xml = xml + "<process id=\""+bpmProcess.getFlowName()+"\" name=\""+bpmProcess.getFlowTitle()+"\" isExecutable=\"true\">";
        
        // 根据流程图List信息实例化一个流程图Map。方便递归创建XML时调用
        Map<String, ConfFlowChart> confFlowChartMap = new HashMap<String, ConfFlowChart>();
        ConfFlowChart startNode = null;
        for (ConfFlowChart confFlowChart : confFlowCharts) {
            confFlowChartMap.put(confFlowChart.getItemId(), confFlowChart);
            JSONObject flowChartObj= JSONObject.fromObject(confFlowChart.getContext());
            if ("StartNode".equals(flowChartObj.get("type"))) {
                startNode = confFlowChart;
            }
        }
        // 递归创建XML
        String xmlStr = "";
        xmlStr = editXmlByFlowChart(xmlStr, startNode, confFlowChartMap);
        xml = xml + xmlStr;
        
        xml = xml + "</process>";
        // 图信息
        xml = xml + "<bpmndi:BPMNDiagram id=\"BPMNDiagram_"+bpmProcess.getFlowName()+"\">";
        xml = xml + "<bpmndi:BPMNPlane bpmnElement=\""+bpmProcess.getFlowName()+"\" id=\"BPMNPlane_"+bpmProcess.getFlowName()+"\">";
        
        // 递归创建画图相关XML
        String xmlDiagramStr = "";
        xmlDiagramStr = editDiagramXmlByFlowChart(xmlDiagramStr, startNode, confFlowChartMap);
        xml = xml + xmlDiagramStr;

        xml = xml + "</bpmndi:BPMNPlane>";
        xml = xml + "</bpmndi:BPMNDiagram>";
        //
        xml = xml + "</definitions>";
        
        System.out.println("=======BpmnXml:"+xml);
        return xml;
    }
    /**
     * 递归创建XML
     * @param startNode
     * @return
     */
    private String editXmlByFlowChart(String xml, ConfFlowChart nodeBean, Map<String, ConfFlowChart> confFlowChartMap) {
    	if (null == nodeBean) {
    		return "";
    	}
        JSONObject flowChartObj= JSONObject.fromObject(nodeBean.getContext());
        // 开始节点
        if ("StartNode".equals(flowChartObj.get("type"))) {
            // TODO
            // activiti:assignee=\"${assignee}\" 设置下一节点用户 
            xml = xml + "<startEvent id=\"sid-"+flowChartObj.get("id")+"\" activiti:assignee=\"${assignee}\" ></startEvent>";
            String nextNodesStr = flowChartObj.get("afterLineIds").toString();
            String[] nextNodes = nextNodesStr.split(",");
            for (String nextNodeStr : nextNodes) {
                ConfFlowChart bean = confFlowChartMap.get(nextNodeStr);
                if (null != bean) {
                    xml = editXmlByFlowChart(xml, bean, confFlowChartMap);
                }
            }
        } else if ("TaskNode".equals(flowChartObj.get("type"))) {
            // 任务节点
            xml = xml + "<userTask id=\"sid-"+flowChartObj.get("id")+"\" activiti:assignee=\"${assignee}\" name=\""+flowChartObj.get("title")+"\"></userTask>";
            String nextNodesStr = flowChartObj.get("afterLineIds").toString();
            String[] nextNodes = nextNodesStr.split(",");
            for (String nextNodeStr : nextNodes) {
                ConfFlowChart bean = confFlowChartMap.get(nextNodeStr);
                if (null != bean) {
                    xml = editXmlByFlowChart(xml, bean, confFlowChartMap);
                }
            }
        } else if ("Gateway".equals(flowChartObj.get("type"))) {
            // 判断节点
            xml = xml + "<exclusiveGateway id=\"sid-"+flowChartObj.get("id")+"\" name=\"Exclusive Gateway\"></exclusiveGateway>";
            String nextNodesStr = flowChartObj.get("afterLineIds").toString();
            String[] nextNodes = nextNodesStr.split(",");
            for (String nextNodeStr : nextNodes) {
                ConfFlowChart bean = confFlowChartMap.get(nextNodeStr);
                if (null != bean) {
                    xml = editXmlByFlowChart(xml, bean, confFlowChartMap);
                }
            }
        } else if ("Line".equals(flowChartObj.get("type"))) {
            // 线
            xml = xml + "<sequenceFlow id=\"sid-"+flowChartObj.get("id")+"\" sourceRef=\"sid-"+flowChartObj.get("startElmId")+"\" targetRef=\"sid-"+flowChartObj.get("endElmId")+"\">";
            // 如果前一个节点是判断节点,则生成判断条件
            String sourceRefStr = ""+flowChartObj.get("startElmId");
            ConfFlowChart sourceRefbean = confFlowChartMap.get(sourceRefStr);
            if ("Gateway".equals(JSONObject.fromObject(sourceRefbean.getContext()).get("type"))) {
                xml = xml + "<conditionExpression xsi:type=\"tFormalExpression\">";
                xml = xml + "${"+ (flowChartObj.containsKey("gatewayInfo") ? flowChartObj.get("gatewayInfo") : "") +"}";
                xml = xml + "</conditionExpression>";
            }
            // 
            xml = xml + "</sequenceFlow>";
            String nextNodesStr = flowChartObj.get("endElmId").toString();
            String[] nextNodes = nextNodesStr.split(",");
            for (String nextNodeStr : nextNodes) {
                ConfFlowChart bean = confFlowChartMap.get(nextNodeStr);
                if (null != bean) {
                    xml = editXmlByFlowChart(xml, bean, confFlowChartMap);
                }
            }
        } else if ("EndNode".equals(flowChartObj.get("type"))) {
            // 结束节点
            xml = xml + "<endEvent id=\"sid-"+flowChartObj.get("id")+"\" name= \"End\" ></endEvent>";
        }
        return xml;
    }
    /**
     * 递归创建图形XML
     * @param xml
     * @param startNode
     * @param confFlowChartMap
     * @return
     */
    private String editDiagramXmlByFlowChart(String xml, ConfFlowChart confFlowChart,
            Map<String, ConfFlowChart> confFlowChartMap) {
    	if (null == confFlowChart) {
    		return "";
    	}
        JSONObject flowChartObj= JSONObject.fromObject(confFlowChart.getContext());
        // 开始节点
        if ("StartNode".equals(flowChartObj.get("type"))) {
            xml = xml + "<bpmndi:BPMNShape bpmnElement=\"sid-"+flowChartObj.get("id")+"\" id=\"BPMNShape_sid-"+flowChartObj.get("id")+"\">";
            xml = xml + "<omgdc:Bounds height=\""+flowChartObj.get("height")+"\" width=\""+flowChartObj.get("width")+"\" x=\""+flowChartObj.get("x")+"\" y=\""+flowChartObj.get("y")+"\"></omgdc:Bounds>";
            xml = xml + "</bpmndi:BPMNShape>";
            String nextNodesStr = flowChartObj.get("afterLineIds").toString();
            String[] nextNodes = nextNodesStr.split(",");
            for (String nextNodeStr : nextNodes) {
                ConfFlowChart bean = confFlowChartMap.get(nextNodeStr);
                if (null != bean) {
                    xml = editDiagramXmlByFlowChart(xml, bean, confFlowChartMap);
                }
            }
        }
        // 任务节点
        if ("TaskNode".equals(flowChartObj.get("type"))) {
            xml = xml + "<bpmndi:BPMNShape bpmnElement=\"sid-"+flowChartObj.get("id")+"\" id=\"BPMNShape_sid-"+flowChartObj.get("id")+"\">";
            xml = xml + "<omgdc:Bounds height=\""+flowChartObj.get("height")+"\" width=\""+flowChartObj.get("width")+"\" x=\""+flowChartObj.get("x")+"\" y=\""+flowChartObj.get("y")+"\"></omgdc:Bounds>";
            xml = xml + "</bpmndi:BPMNShape>";
            String nextNodesStr = flowChartObj.get("afterLineIds").toString();
            String[] nextNodes = nextNodesStr.split(",");
            for (String nextNodeStr : nextNodes) {
                ConfFlowChart bean = confFlowChartMap.get(nextNodeStr);
                if (null != bean) {
                    xml = editDiagramXmlByFlowChart(xml, bean, confFlowChartMap);
                }
            }
        }
        // 判断节点
        if ("Gateway".equals(flowChartObj.get("type"))) {
            xml = xml + "<bpmndi:BPMNShape bpmnElement=\"sid-"+flowChartObj.get("id")+"\" id=\"BPMNShape_sid-"+flowChartObj.get("id")+"\">";
            xml = xml + "<omgdc:Bounds height=\""+flowChartObj.get("height")+"\" width=\""+flowChartObj.get("width")+"\" x=\""+flowChartObj.get("x")+"\" y=\""+flowChartObj.get("y")+"\"></omgdc:Bounds>";
            xml = xml + "</bpmndi:BPMNShape>";
            String nextNodesStr = flowChartObj.get("afterLineIds").toString();
            String[] nextNodes = nextNodesStr.split(",");
            for (String nextNodeStr : nextNodes) {
                ConfFlowChart bean = confFlowChartMap.get(nextNodeStr);
                if (null != bean) {
                    xml = editDiagramXmlByFlowChart(xml, bean, confFlowChartMap);
                }
            }
        }
        
        // 线
        if ("Line".equals(flowChartObj.get("type"))) {
            xml = xml + "<bpmndi:BPMNEdge bpmnElement=\"sid-"+flowChartObj.get("id")+"\" id=\"BPMNEdge_sid-"+flowChartObj.get("id")+"\">";
            String realPointStr =flowChartObj.get("realPoints").toString();
            String[] realPoints = realPointStr.split(",");
            for (int i=0; i<realPoints.length; i=i+2) {
                xml = xml + "<omgdi:waypoint x=\""+realPoints[i]+"\" y=\""+realPoints[i+1]+"\"></omgdi:waypoint>";
            }
            xml = xml + "</bpmndi:BPMNEdge>";
            String nextNodesStr = flowChartObj.get("endElmId").toString();
            String[] nextNodes = nextNodesStr.split(",");
            for (String nextNodeStr : nextNodes) {
                ConfFlowChart bean = confFlowChartMap.get(nextNodeStr);
                if (null != bean) {
                    xml = editDiagramXmlByFlowChart(xml, bean, confFlowChartMap);
                }
            }
        }
        // 结束节点
        if ("EndNode".equals(flowChartObj.get("type"))) {
            xml = xml + "<bpmndi:BPMNShape bpmnElement=\"sid-"+flowChartObj.get("id")+"\" id=\"BPMNShape_sid-"+flowChartObj.get("id")+"\">";
            xml = xml + "<omgdc:Bounds height=\""+flowChartObj.get("height")+"\" width=\""+flowChartObj.get("width")+"\" x=\""+flowChartObj.get("x")+"\" y=\""+flowChartObj.get("y")+"\"></omgdc:Bounds>";
            xml = xml + "</bpmndi:BPMNShape>";
        }
        return xml;
    }
    /**
     * 删除未结束流程
     */
    public void deleteProcessInstance(String processInstanceId) {
        getProcessEngine().getRuntimeService().deleteProcessInstance(processInstanceId,"");
        // (顺序不能换) 
        getProcessEngine().getHistoryService().deleteHistoricProcessInstance(processInstanceId);
    }
    /**
     * 删除已结束流程
     */
    public void deleteHistoricProcessInstance(String processInstanceId) {
        getProcessEngine().getHistoryService().deleteHistoricProcessInstance(processInstanceId);
    }
    // ==============================================================
    /**
     * 取得流程管理表DAO对象
     * @return
     */
    public BpmProcessDao getBpmProcessDao() {
        return ApplicationContextHelper.getBean(BpmProcessDao.class);
    }
    public FlowChartService getFlowChartService() {
        return ApplicationContextHelper.getBean(FlowChartService.class);
    }
    public BpmProcessVersionDao getBpmProcessVersionDao() {
        return ApplicationContextHelper.getBean(BpmProcessVersionDao.class);
    }
    public ProcessEngine getProcessEngine() {
        return ApplicationContextHelper.getBean(ProcessEngine.class);
    }
    public IdentityService getIdentityService() {
        return ApplicationContextHelper.getBean(IdentityService.class);
    }
    public RuntimeService getRuntimeService() {
        return ApplicationContextHelper.getBean(RuntimeService.class);
    }
    public BpmFlowNodeDao getBpmFlowNodeDao() {
        return ApplicationContextHelper.getBean(BpmFlowNodeDao.class);
    }
    public BpmNodeColumsService getBpmNodeColumsService() {
        return ApplicationContextHelper.getBean(BpmNodeColumsService.class);
    }
}
