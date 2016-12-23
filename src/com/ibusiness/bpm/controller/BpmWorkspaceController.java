package com.ibusiness.bpm.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibusiness.bpm.cmd.HistoryProcessInstanceDiagramCmd;
import com.ibusiness.bpm.dao.BpmProcessDao;
import com.ibusiness.bpm.dao.BpmProcessVersionDao;
import com.ibusiness.bpm.entity.BpmProcess;
import com.ibusiness.bpm.entity.BpmProcessVersion;
import com.ibusiness.bpm.entity.BpmWorkspaceTask;
import com.ibusiness.bpm.service.BpmComBusiness;
import com.ibusiness.security.util.SpringSecurityUtils;

/**
 * 我的流程 待办流程 已办未结
 */
@Controller
@RequestMapping("bpm-workspace")
public class BpmWorkspaceController {
    private ProcessEngine processEngine;
    private BpmProcessVersionDao bpmProcessVersionDao;
    private BpmProcessDao bpmProcessDao;

    /**
     * 待办任务（个人任务）
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("workspace-listPersonalTasks")
    public String listPersonalTasks(Model model) {
        String userId = SpringSecurityUtils.getCurrentUserId();
        // 取得待办任务（个人任务）---指定用户ID的
        BpmComBusiness bpmComBusiness = new BpmComBusiness();
        List<Task> tasks = bpmComBusiness.listPersonalTasks(userId);
        // 页面显示用bean
        List<BpmWorkspaceTask> tasklist = new ArrayList<BpmWorkspaceTask>();
        for (Task task : tasks) {
            BpmWorkspaceTask taskBean = new BpmWorkspaceTask();
            taskBean.setId(task.getId());
            taskBean.setName(task.getName());
            taskBean.setCreateTime(task.getCreateTime());
            taskBean.setAssignee(task.getAssignee());
            taskBean.setSuspended(task.isSuspended());
            taskBean.setProcessInstanceId(task.getProcessInstanceId());
            // bpmURL设置
            String proceVersionHql = "from BpmProcessVersion where bpmProsessId=?";
            List<BpmProcessVersion> bpmProcessVersionList = bpmProcessVersionDao.find(proceVersionHql, task.getProcessDefinitionId());
            String flowTitle = "";
            String flowUrl = "";
            if (null != bpmProcessVersionList && bpmProcessVersionList.size() > 0) {
                String proceHql = "from BpmProcess where versionId=?";
                List<BpmProcess> bpmProcessList= bpmProcessDao.find(proceHql, bpmProcessVersionList.get(0).getId());
                if (null != bpmProcessList && bpmProcessList.size() > 0) {
                    flowUrl = bpmProcessList.get(0).getFlowUrl();
                    flowTitle = bpmProcessList.get(0).getFlowTitle();
                }
            }
            // 流程名设置
            taskBean.setFlowTitle(flowTitle);
            taskBean.setFlowUrl(flowUrl);
            tasklist.add(taskBean);
        }
        // 设置页面信息
        model.addAttribute("tasks", tasklist);
        
        return "ibusiness/bpm/workspace-listPersonalTasks.jsp";
    }

    /**
     * 查看历史【包含流程跟踪、任务列表（完成和未完成）、流程变量】
     * 
     * @return
     */
    @RequestMapping("workspace-viewHistory")
    public String viewHistory(@RequestParam("processInstanceId")
    String processInstanceId, Model model) {
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricTaskInstance> historicTasks = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId).list();
        List<HistoricVariableInstance> historicVariableInstances = historyService.createHistoricVariableInstanceQuery()
                .processInstanceId(processInstanceId).list();
        
        // 通过historicTasks取得流程页面显示信息列表
        List<BpmWorkspaceTask> tasklist = getBpmWorkspaceTaskList(historicTasks);
        //
        model.addAttribute("historicTasks", tasklist);
        model.addAttribute("historicVariableInstances", historicVariableInstances);

        return "ibusiness/bpm/workspace-viewHistory.jsp";
    }

    /**
     * 流程历史跟踪图
     * 
     * @throws Exception
     */
    @RequestMapping("workspace-graphHistoryProcessInstance")
    public void graphHistoryProcessInstance(@RequestParam("processInstanceId")
    String processInstanceId, HttpServletResponse response) throws Exception {
        // 取得流程历史跟踪图command
        Command<InputStream> cmd = new HistoryProcessInstanceDiagramCmd(processInstanceId);
        InputStream is = processEngine.getManagementService().executeCommand(cmd);
        response.setContentType("image/png");
        int len = 0;
        byte[] b = new byte[1024];
        while ((len = is.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }

    /**
     * 已办任务（历史任务）
     * 
     * @return
     */
    @RequestMapping("workspace-listHistoryTasks")
    public String listHistoryTasks(Model model) {
        HistoryService historyService = processEngine.getHistoryService();
        String userId = SpringSecurityUtils.getCurrentUserId();
        List<HistoricTaskInstance> historicTasks = historyService.createHistoricTaskInstanceQuery().taskAssignee(userId).finished().list();
        
        // 通过historicTasks取得流程页面显示信息列表
        List<BpmWorkspaceTask> tasklist = getBpmWorkspaceTaskList(historicTasks);
            
        // 
        model.addAttribute("historicTasks", tasklist);

        return "ibusiness/bpm/workspace-listHistoryTasks.jsp";
    }

    // ~ ======================================================================
    /**
     * 已结流程.
     * 
     * @return
     */
    @RequestMapping("workspace-listCompletedProcessInstances")
    public String listCompletedProcessInstances(Model model) {
        HistoryService historyService = processEngine.getHistoryService();

        String userId = SpringSecurityUtils.getCurrentUserId();
        List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery()
                .startedBy(userId).finished().list();
        model.addAttribute("historicProcessInstances", historicProcessInstances);

        return "bpm/workspace-listCompletedProcessInstances";
    }

    /**
     * 用户参与的流程（包含已经完成和未完成）
     * 
     * @return
     */
    @RequestMapping("workspace-listInvolvedProcessInstances")
    public String listInvolvedProcessInstances(Model model) {
        HistoryService historyService = processEngine.getHistoryService();
        String userId = SpringSecurityUtils.getCurrentUserId();
        List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery()
                .involvedUser(userId).list();
        model.addAttribute("historicProcessInstances", historicProcessInstances);

        return "bpm/workspace-listInvolvedProcessInstances";
    }
    // ~ ======================================================================
    /**
     * 发起流程页面（启动一个流程实例）内置流程表单方式
     * 
     * @return
     */
    @RequestMapping("workspace-prepareStartProcessInstance")
    public String prepareStartProcessInstance(@RequestParam("processDefinitionId")
    String processDefinitionId, Model model) {
        FormService formService = processEngine.getFormService();
        StartFormData startFormData = formService.getStartFormData(processDefinitionId);
        model.addAttribute("startFormData", startFormData);

        return "bpm/workspace prepareStartProcessInstance";
    }

    // ~ ======================================================================
    /**
     * 完成任务页面
     * 
     * @return
     */
    @RequestMapping("workspace-prepareCompleteTask")
    public String prepareCompleteTask(@RequestParam("taskId")
    String taskId, Model model) {
        FormService formService = processEngine.getFormService();

        TaskFormData taskFormData = formService.getTaskFormData(taskId);

        model.addAttribute("taskFormData", taskFormData);

        return "bpm/workspace-prepareCompleteTask";
    }
    // ~ ==================================国内特色流程====================================
    /**
     * 回退任务
     * 
     * @return
     */
    @RequestMapping("workspace-rollback")
    public String rollback(@RequestParam("taskId") String taskId) {
        new BpmComBusiness().rollback(taskId);
        return "redirect:/bpm/workspace-listPersonalTasks.do";
    }
    
    /**
     * 通过historicTasks取得流程页面显示信息列表
     * @param historicTasks
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<BpmWorkspaceTask> getBpmWorkspaceTaskList(List<HistoricTaskInstance> historicTasks) {
        List<BpmWorkspaceTask> tasklist = new ArrayList<BpmWorkspaceTask>();
        for (HistoricTaskInstance task : historicTasks) {
            BpmWorkspaceTask taskBean = new BpmWorkspaceTask();
            taskBean.setId(task.getId());
            taskBean.setName(task.getName());
            taskBean.setStartTime(task.getStartTime());
            taskBean.setEndTime(task.getEndTime());
            taskBean.setAssignee(task.getAssignee());
            taskBean.setDeleteReason("completed".equals(task.getDeleteReason())?"办理" : task.getDeleteReason());
            taskBean.setProcessInstanceId(task.getProcessInstanceId());
            // 取得流程标题
            String proceVersionHql = "from BpmProcessVersion where bpmProsessId=?";
            List<BpmProcessVersion> bpmProcessVersionList = bpmProcessVersionDao.find(proceVersionHql, task.getProcessDefinitionId());
            String flowTitle = "";
            if (null != bpmProcessVersionList && bpmProcessVersionList.size() > 0) {
                String proceHql = "from BpmProcess where versionId=?";
                List<BpmProcess> bpmProcessList= bpmProcessDao.find(proceHql, bpmProcessVersionList.get(0).getId());
                if (null != bpmProcessList && bpmProcessList.size() > 0) {
                    flowTitle = bpmProcessList.get(0).getFlowTitle();
                }
            }
            // 流程标题设置
            taskBean.setFlowTitle(flowTitle);
            tasklist.add(taskBean);
        }
        return tasklist;
    }
    // ======================================================================
    @Resource
    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }
    @Resource
    public void setBpmProcessVersionDao(BpmProcessVersionDao bpmProcessVersionDao) {
        this.bpmProcessVersionDao = bpmProcessVersionDao;
    }
    @Resource
    public void setBpmProcessDao(BpmProcessDao bpmProcessDao) {
        this.bpmProcessDao = bpmProcessDao;
    }
}
