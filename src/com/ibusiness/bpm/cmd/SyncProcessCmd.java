package com.ibusiness.bpm.cmd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.impl.cmd.GetBpmnModelCmd;
import org.activiti.engine.impl.cmd.GetDeploymentProcessDefinitionCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;

import com.ibusiness.base.menu.dao.MenuDao;
import com.ibusiness.bpm.dao.BpmFlowNodeDao;
import com.ibusiness.bpm.dao.BpmNodeCountersignDao;
import com.ibusiness.bpm.dao.BpmNodeFormDao;
import com.ibusiness.bpm.dao.BpmNodeListenerDao;
import com.ibusiness.bpm.dao.BpmProcessDao;
import com.ibusiness.bpm.dao.BpmProcessVersionDao;
import com.ibusiness.bpm.entity.BpmFlowNode;
import com.ibusiness.bpm.entity.BpmNodeColums;
import com.ibusiness.bpm.entity.BpmNodeCountersign;
import com.ibusiness.bpm.entity.BpmNodeForm;
import com.ibusiness.bpm.entity.BpmNodeListener;
import com.ibusiness.bpm.entity.BpmNodeUser;
import com.ibusiness.bpm.entity.BpmProcess;
import com.ibusiness.bpm.entity.BpmProcessVersion;
import com.ibusiness.bpm.graph.Graph;
import com.ibusiness.bpm.graph.Node;
import com.ibusiness.bpm.service.BpmNodeColumsService;
import com.ibusiness.bpm.service.BpmNodeUserService;
import com.ibusiness.component.form.dao.ConfFormDao;
import com.ibusiness.component.form.dao.ConfFormTableColumnDao;
import com.ibusiness.component.form.entity.ConfForm;
import com.ibusiness.component.form.entity.ConfFormTableColumn;
import com.ibusiness.core.spring.ApplicationContextHelper;

/**
 * 把xml解析的内存模型保存到数据库里.
 */
public class SyncProcessCmd implements Command<Void> {
    /** 流程定义id. */
    private String processDefinitionId;

    /**
     * 构造方法.
     */
    public SyncProcessCmd(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    /**
     * 执行
     */
    @SuppressWarnings("unchecked")
    public Void execute(CommandContext commandContext) {
        // 取得流程定义实体对象
        ProcessDefinitionEntity processDefinitionEntity = new GetDeploymentProcessDefinitionCmd(processDefinitionId).execute(commandContext);
        String processDefinitionKey = processDefinitionEntity.getKey();
        int processDefinitionVersion = processDefinitionEntity.getVersion();
        // 取得DAO
        BpmProcessVersionDao bpmProcessVersionDao = getBpmProcessVersionDao();
        BpmProcessVersion bpmProcessVersion = bpmProcessVersionDao.findUnique(
                        "from BpmProcessVersion where bpmProsessKey=? and bpmProsessVersion=?",
                        processDefinitionKey, processDefinitionVersion);
        if (bpmProcessVersion == null) {
            bpmProcessVersion = new BpmProcessVersion();
            bpmProcessVersion.setId(UUID.randomUUID().toString());
            bpmProcessVersion.setBpmProsessId(processDefinitionId);
            bpmProcessVersion.setBpmProsessKey(processDefinitionKey);
            bpmProcessVersion.setBpmProsessVersion(processDefinitionVersion);
            bpmProcessVersionDao.save(bpmProcessVersion);
        } else if (bpmProcessVersion.getBpmProsessId() == null) {
            bpmProcessVersion.setBpmProsessId(processDefinitionId);
            // 保存流程版本管理表
            bpmProcessVersionDao.save(bpmProcessVersion);
        }
        // 存储流程管理数据
        BpmProcessDao bpmProcessDao = getBpmProcessDao();
        String bpmFlowHql = "from BpmProcess Where flowName=? ";
        List<BpmProcess> bpmProcessList = bpmProcessDao.find(bpmFlowHql, bpmProcessVersion.getBpmProsessKey());
        BpmProcess bpmProcess = null;
        if (null != bpmProcessList && bpmProcessList.size() > 0) {
            bpmProcess = bpmProcessList.get(0);
        }
        if (null == bpmProcess) {
            return null;
        } else {
            bpmProcess.setVersionId(bpmProcessVersion.getId());
            bpmProcessDao.save(bpmProcess);
        }
        //
        BpmnModel bpmnModel = new GetBpmnModelCmd(processDefinitionId).execute(commandContext);
        // 全局配置
        this.processGlobal(bpmnModel, 1, bpmProcessVersion);
        // 取得流程图表实体
        Graph graph = new FindGraphCmd(processDefinitionId).execute(commandContext);
        int priority = 2;
        for (Node node : graph.getNodes()) {
            if ("exclusiveGateway".equals(node.getType())) {
                continue;
            } else if ("userTask".equals(node.getType())) {
                // 配置用户(参与者)任务
                this.processUserTask(node, bpmnModel, priority++, bpmProcess, bpmProcessVersion);
            } else if ("startEvent".equals(node.getType())) {
                // 配置开始事件
                this.processStartEvent(node, bpmnModel, priority++, bpmProcess, bpmProcessVersion);
            } else if ("endEvent".equals(node.getType())) {
                // 配置结束事件
                this.processEndEvent(node, bpmnModel, priority++, bpmProcess, bpmProcessVersion);
            }
        }
        return null;
    }

    /**
     * 全局配置.
     */
    @SuppressWarnings("unchecked")
    public void processGlobal(BpmnModel bpmnModel, int priority, BpmProcessVersion bpmProcessVersion) {
        // 
        Process process = bpmnModel.getMainProcess();
        // 取得流程管理信息
        BpmProcessDao bpmProcessDao = getBpmProcessDao();
        String hql = "from BpmProcess WHERE versionId=? ";
        List<BpmProcess> bpmProcessList = bpmProcessDao.find(hql, bpmProcessVersion.getId());
        if (null != bpmProcessList && bpmProcessList.size() > 0) {
            BpmProcess bpmProcess = bpmProcessList.get(0);
            BpmFlowNodeDao bpmFlowNodeDao = getBpmFlowNodeDao();
            BpmFlowNode bpmFlowNode = bpmFlowNodeDao.findUnique("from BpmFlowNode where nodeCode=? and flowVersionId=?",
                    process.getId(), bpmProcessVersion.getId());
            if (bpmFlowNode == null) {
                bpmFlowNode = new BpmFlowNode();
                bpmFlowNode.setId(UUID.randomUUID().toString());
                bpmFlowNode.setPackageName(bpmProcess.getPackageName());
                bpmFlowNode.setFlowId(bpmProcess.getId());
                bpmFlowNode.setFlowVersionId(bpmProcess.getVersionId());
                bpmFlowNode.setNodeCode(process.getId());
                bpmFlowNode.setNodeName("全局");
                bpmFlowNode.setNodeType("process");
                bpmFlowNode.setConfUser("2");
                bpmFlowNode.setConfListener("0");
                bpmFlowNode.setConfRule("2");
                bpmFlowNode.setConfForm("0");
                bpmFlowNode.setConfOperation("2");
                bpmFlowNode.setConfNotice("2");
                bpmFlowNode.setPriority(priority);
                bpmFlowNodeDao.save(bpmFlowNode);
                
                // 插入 流程节点字段配置表
                insertBpmFlowNode(bpmProcess.getFormId(), bpmFlowNode);
                
            }
            // 配置监听器
            processListener(process.getExecutionListeners(), bpmFlowNode);
        }
        
    }

    /**
     * 插入 流程节点字段配置表
     * @param bpmProcess
     * @param bpmFlowNode
     */
    @SuppressWarnings("unchecked")
    private void insertBpmFlowNode(String formId, BpmFlowNode bpmFlowNode) {
        // 取得表单信息,如果表单信息为空则返回
        ConfForm confForm = getConfFormDao().get(formId);
        if (null == confForm) {
        	return;
        }
        String sql = "from ConfFormTableColumn where formName=?";
        List<ConfFormTableColumn> confFormTableColumns = getConfFormTableColumnDao().find(sql, confForm.getFormName());
        // 
        for (ConfFormTableColumn confFormTableColumn : confFormTableColumns) {
            BpmNodeColums bpmNodeColums = new BpmNodeColums();
            bpmNodeColums.setId(UUID.randomUUID().toString());
            bpmNodeColums.setPackageName(confFormTableColumn.getPackageName());
            bpmNodeColums.setFlowId(bpmFlowNode.getFlowId());
            bpmNodeColums.setFormName(confFormTableColumn.getFormName());
            bpmNodeColums.setNodeId(bpmFlowNode.getId());
            bpmNodeColums.setFormColumn(confFormTableColumn.getFormColumn());
            bpmNodeColums.setFormColumnTitle(confFormTableColumn.getFormColumnTitle());
            bpmNodeColums.setTableName(confFormTableColumn.getTableName());
            bpmNodeColums.setTableColumn(confFormTableColumn.getTableColumn());
            bpmNodeColums.setFcDisplay("1");
            bpmNodeColums.setFcEdit("1");
            getBpmNodeColumsService().saveInsert(bpmNodeColums);
        }
    }

    /**
     * 配置用户(参与者)任务
     * 
     * @param node
     * @param bpmnModel
     * @param priority
     * @param bpmProcessVersion
     */
    public void processUserTask(Node node, BpmnModel bpmnModel, int priority, BpmProcess bpmProcess,
            BpmProcessVersion bpmProcessVersion) {
        BpmFlowNodeDao bpmFlowNodeDao = getBpmFlowNodeDao();
        BpmFlowNode bpmFlowNode = bpmFlowNodeDao.findUnique(
                "from BpmFlowNode where nodeCode=? and flowVersionId=?",
                node.getId(), bpmProcessVersion.getId());

        // 设置流程节点信息
        if (bpmFlowNode == null) {
            bpmFlowNode = new BpmFlowNode();
            bpmFlowNode.setId(UUID.randomUUID().toString());
            bpmFlowNode.setPackageName(bpmProcess.getPackageName());
            bpmFlowNode.setFlowId(bpmProcess.getId());
            bpmFlowNode.setFlowVersionId(bpmProcess.getVersionId());
            bpmFlowNode.setNodeCode(node.getId());
            bpmFlowNode.setNodeName(node.getName());
            bpmFlowNode.setNodeType(node.getType());
            bpmFlowNode.setConfUser("0");
            bpmFlowNode.setConfListener("0");
            bpmFlowNode.setConfRule("0");
            bpmFlowNode.setConfForm("0");
            bpmFlowNode.setConfOperation("0");
            bpmFlowNode.setConfNotice("0");
            bpmFlowNode.setPriority(priority);
            bpmFlowNodeDao.save(bpmFlowNode);
            
            // 插入 流程节点字段配置表
            insertBpmFlowNode(bpmProcess.getFormId(), bpmFlowNode);
        }

        // 配置参与者
        UserTask userTask = (UserTask) bpmnModel.getFlowElement(node.getId());
        int index = 1;
        index = this.processUserTaskConf(bpmFlowNode, userTask.getAssignee(), "0", index);
        for (String candidateUser : userTask.getCandidateUsers()) {
            index = this.processUserTaskConf(bpmFlowNode, candidateUser, "1", index);
        }
        for (String candidateGroup : userTask.getCandidateGroups()) {
            this.processUserTaskConf(bpmFlowNode, candidateGroup, "2", index);
        }

        // 配置监听器
        this.processListener(userTask.getExecutionListeners(), bpmFlowNode);
        this.processListener(userTask.getTaskListeners(), bpmFlowNode);
        // 配置表单
        this.processForm(userTask, bpmFlowNode);

        // 会签
        if (userTask.getLoopCharacteristics() != null) {
            BpmNodeCountersign bpmNodeCountersign = new BpmNodeCountersign();
            bpmNodeCountersign.setId(UUID.randomUUID().toString());
            bpmNodeCountersign.setPackageName(bpmProcess.getPackageName());
            bpmNodeCountersign.setFlowId(bpmProcess.getId());
            bpmNodeCountersign.setFlowVersionId(bpmProcess.getVersionId());
            bpmNodeCountersign.setNodeId(bpmFlowNode.getId());
            bpmNodeCountersign.setCountersignType("0");
            bpmNodeCountersign.setCountersignRate(100);
            bpmNodeCountersign.setSequential(userTask.getLoopCharacteristics().isSequential() ? 1 : 0);
            getBpmNodeCountersignDao().save(bpmNodeCountersign);
        }
    }

    /**
     * 配置参与者.
     */
    public int processUserTaskConf(BpmFlowNode bpmFlowNode, String value,
            String type, int priority) {
        if (value == null) {
            return priority;
        }
                
        // 取得流程节点关联用户数据
        BpmNodeUserService bpmNodeUserService = getBpmNodeUserService();
        BpmNodeUser bpmNodeUser = bpmNodeUserService.findUnique(
                        "from BpmNodeUser where userValue=? and userType=? and priority=? and userStatus=0 and nodeId=?",
                        value, type, priority, bpmFlowNode.getId());

        if (bpmNodeUser == null) {
            bpmNodeUser = new BpmNodeUser();
            bpmNodeUser.setId(UUID.randomUUID().toString());
            bpmNodeUser.setPackageName(bpmFlowNode.getPackageName());
            bpmNodeUser.setFlowId(bpmFlowNode.getId());
            bpmNodeUser.setFlowVersionId(bpmFlowNode.getFlowVersionId());
            bpmNodeUser.setNodeId(bpmFlowNode.getId());
            bpmNodeUser.setUserValue(value);
            bpmNodeUser.setUserType(type);
            bpmNodeUser.setUserStatus("0");
            bpmNodeUser.setPriority(priority);
            
            bpmNodeUserService.save(bpmNodeUser);
        }

        return priority + 1;
    }
    /**
     * 配置开始事件.
     */
    public void processStartEvent(Node node, BpmnModel bpmnModel, int priority, BpmProcess bpmProcess,
            BpmProcessVersion bpmProcessVersion) {
        BpmFlowNodeDao bpmFlowNodeDao = getBpmFlowNodeDao();
        BpmFlowNode bpmFlowNode = bpmFlowNodeDao.findUnique(
                "from BpmFlowNode where nodeCode=? and flowVersionId=?",
                node.getId(), bpmProcessVersion.getId());

        // 设置流程节点数据
        if (bpmFlowNode == null) {
            bpmFlowNode = new BpmFlowNode();
            bpmFlowNode.setId(UUID.randomUUID().toString());
            bpmFlowNode.setPackageName(bpmProcess.getPackageName());
            bpmFlowNode.setFlowId(bpmProcess.getId());
            bpmFlowNode.setFlowVersionId(bpmProcess.getVersionId());
            bpmFlowNode.setNodeCode(node.getId());
            bpmFlowNode.setNodeName(node.getName());
            bpmFlowNode.setNodeType(node.getType());
            bpmFlowNode.setConfUser("2");
            bpmFlowNode.setConfListener("0");
            bpmFlowNode.setConfRule("2");
            bpmFlowNode.setConfForm("2");
            bpmFlowNode.setConfOperation("2");
            bpmFlowNode.setConfNotice("0");
            bpmFlowNode.setPriority(priority);
            
            bpmFlowNodeDao.save(bpmFlowNode);
            
            // 插入 流程节点字段配置表
            insertBpmFlowNode(bpmProcess.getFormId(), bpmFlowNode);
        }
        //
        FlowElement flowElement = bpmnModel.getFlowElement(node.getId());
        // 配置监听器
        processListener(flowElement.getExecutionListeners(), bpmFlowNode);
    }

    /**
     * 配置结束事件.
     */
    public void processEndEvent(Node node, BpmnModel bpmnModel, int priority,BpmProcess bpmProcess,
            BpmProcessVersion bpmProcessVersion) {
        BpmFlowNodeDao bpmFlowNodeDao = getBpmFlowNodeDao();
        BpmFlowNode bpmFlowNode = bpmFlowNodeDao.findUnique(
                "from BpmFlowNode where nodeCode=? and flowVersionId=?",
                node.getId(), bpmProcessVersion.getId());

        if (bpmFlowNode == null) {
            bpmFlowNode = new BpmFlowNode();
            bpmFlowNode.setId(UUID.randomUUID().toString());
            bpmFlowNode.setPackageName(bpmProcess.getPackageName());
            bpmFlowNode.setFlowId(bpmProcess.getId());
            bpmFlowNode.setFlowVersionId(bpmProcess.getVersionId());
            bpmFlowNode.setNodeCode(node.getId());
            bpmFlowNode.setNodeName(node.getName());
            bpmFlowNode.setNodeType(node.getType());
            bpmFlowNode.setConfUser("2");
            bpmFlowNode.setConfListener("0");
            bpmFlowNode.setConfRule("2");
            bpmFlowNode.setConfForm("2");
            bpmFlowNode.setConfOperation("2");
            bpmFlowNode.setConfNotice("0");
            bpmFlowNode.setPriority(priority);
            bpmFlowNodeDao.save(bpmFlowNode);
            
            // 插入 流程节点字段配置表
            insertBpmFlowNode(bpmProcess.getFormId(), bpmFlowNode);
        }

        FlowElement flowElement = bpmnModel.getFlowElement(node.getId());
        // 配置监听器
        processListener(flowElement.getExecutionListeners(), bpmFlowNode);
    }

    /**
     * 配置监听器.
     */
    public void processListener(List<ActivitiListener> activitiListeners, BpmFlowNode bpmFlowNode) {
        Map<String, Integer> eventTypeMap = new HashMap<String, Integer>();
        eventTypeMap.put("start", 0);
        eventTypeMap.put("end", 1);
        eventTypeMap.put("take", 2);
        eventTypeMap.put("create", 3);
        eventTypeMap.put("assignment", 4);
        eventTypeMap.put("complete", 5);
        eventTypeMap.put("delete", 6);
        BpmNodeListenerDao bpmNodeListenerDao = getBpmNodeListenerDao();
        for (ActivitiListener activitiListener : activitiListeners) {
            String value = activitiListener.getImplementation();
            int type = eventTypeMap.get(activitiListener.getEvent());
            BpmNodeListener bpmNodeListener = bpmNodeListenerDao.findUnique(
                            "from BpmNodeListener where listenerValue=? and listenerType=? and listenerStatus=0 and nodeId=?",
                            value, type, bpmFlowNode.getId());

            if (bpmNodeListener == null) {
                bpmNodeListener = new BpmNodeListener();
                bpmNodeListener.setId(UUID.randomUUID().toString());
                bpmNodeListener.setPackageName(bpmFlowNode.getPackageName());
                bpmNodeListener.setFlowId(bpmFlowNode.getId());
                bpmNodeListener.setFlowVersionId(bpmFlowNode.getFlowVersionId());
                bpmNodeListener.setListenerValue(value);
                bpmNodeListener.setListenerType(type);
                bpmNodeListenerDao.save(bpmNodeListener);
            }
        }
    }

    /**
     * 配置表单.
     */
    public void processForm(UserTask userTask, BpmFlowNode bpmFlowNode) {
        if (userTask.getFormKey() == null) {
            return;
        }

        BpmNodeFormDao bpmNodeFormDao = getBpmNodeFormDao();
        BpmNodeForm bpmNodeForm = bpmNodeFormDao.findUnique("from BpmNodeForm where nodeId=?", bpmFlowNode.getId());

        if (bpmNodeForm == null) {
            bpmNodeForm = new BpmNodeForm();
            bpmNodeForm.setId(UUID.randomUUID().toString());
            bpmNodeForm.setPackageName(bpmFlowNode.getPackageName());
            bpmNodeForm.setFlowId(bpmFlowNode.getFlowId());
            bpmNodeForm.setFlowVersionId(bpmFlowNode.getFlowVersionId());
            bpmNodeForm.setNodeId(bpmFlowNode.getId());
            bpmNodeForm.setFormValue(userTask.getFormKey());
            bpmNodeForm.setFormType(0);
            bpmNodeForm.setOriginFormValue(userTask.getFormKey());
            bpmNodeForm.setOriginFormType("0");
            bpmNodeForm.setFormStatus("0");
            bpmNodeFormDao.save(bpmNodeForm);
        }
    }
    
    // ==================================================================================
    // 表单管理DAO
    public ConfFormDao getConfFormDao() {
        return ApplicationContextHelper.getBean(ConfFormDao.class);
    }
    // 表单对应表管理表DAO
    public ConfFormTableColumnDao getConfFormTableColumnDao() {
        return ApplicationContextHelper.getBean(ConfFormTableColumnDao.class);
    }
    // 流程分类定义配置表单表DAO
    public BpmNodeColumsService getBpmNodeColumsService() {
        return ApplicationContextHelper.getBean(BpmNodeColumsService.class);
    }
    //流程管理DAO
    public BpmProcessDao getBpmProcessDao() {
        return ApplicationContextHelper.getBean(BpmProcessDao.class);
    }
    //流程版本管理DAO
    public BpmProcessVersionDao getBpmProcessVersionDao() {
        return ApplicationContextHelper.getBean(BpmProcessVersionDao.class);
    }
    // 流程节点配置表DAO
    public BpmFlowNodeDao getBpmFlowNodeDao() {
        return ApplicationContextHelper.getBean(BpmFlowNodeDao.class);
    }
    // 流程定义配置人员表DAO
    public BpmNodeUserService getBpmNodeUserService() {
        return ApplicationContextHelper.getBean(BpmNodeUserService.class);
    }
    // 流程节点事件配置表DAO
    public BpmNodeListenerDao getBpmNodeListenerDao() {
        return ApplicationContextHelper.getBean(BpmNodeListenerDao.class);
    }
    // 流程节点表单配置表DAO
    public BpmNodeFormDao getBpmNodeFormDao() {
        return ApplicationContextHelper.getBean(BpmNodeFormDao.class);
    }
    // 流程节点(会签)关联配置表DAO
    public BpmNodeCountersignDao getBpmNodeCountersignDao() {
        return ApplicationContextHelper.getBean(BpmNodeCountersignDao.class);
    }
    // 菜单DAO
    public MenuDao getMenuDaoDao() {
        return ApplicationContextHelper.getBean(MenuDao.class);
    }
}
