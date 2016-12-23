package com.ibusiness.bpm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibusiness.bpm.FormInfo;
import com.ibusiness.bpm.cmd.FindStartFormCmd;
import com.ibusiness.bpm.dao.BpmFlowNodeDao;
import com.ibusiness.bpm.dao.BpmNodeFormDao;
import com.ibusiness.bpm.dao.BpmProcessDao;
import com.ibusiness.bpm.dao.BpmProcessVersionDao;
import com.ibusiness.bpm.entity.BpmFlowNode;
import com.ibusiness.bpm.entity.BpmNodeForm;
import com.ibusiness.bpm.entity.BpmProcess;
import com.ibusiness.bpm.entity.BpmProcessVersion;

/**
 * 流程表单相关配置
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("bpm-process")
public class BpmFormController {

    private BpmProcessDao bpmProcessDao;
    private BpmProcessVersionDao bpmProcessVersionDao;
    private BpmNodeFormDao bpmNodeFormDao;
    private BpmFlowNodeDao bpmFlowNodeDao;
    private ProcessEngine processEngine;

    /**
     * 显示启动流程的表单.
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("form-viewStartForm")
    public String viewStartForm(@RequestParam("bpmProcessId")
    String bpmProcessId, @RequestParam(value = "businessKey", required = false)
    String businessKey, Model model) throws Exception {
        model.addAttribute("bpmProcessId", bpmProcessId);
        model.addAttribute("businessKey", businessKey);
        // 流程管理
        BpmProcess bpmProcess = bpmProcessDao.get(bpmProcessId);
        BpmProcessVersion bpmProcessVersion = bpmProcessVersionDao.get(bpmProcess.getVersionId());
        String bpmProsessId = bpmProcessVersion.getBpmProsessId();
        // 取得表单信息
        FormInfo formInfo = processEngine.getManagementService().executeCommand(new FindStartFormCmd(bpmProsessId));
        model.addAttribute("formInfo", formInfo);

        String nextStep = null;
        if (formInfo.isFormExists()) {
            // 如果找到了form，就显示表单
            if (Integer.valueOf(1).equals(bpmProcess.getUseTaskConf())) {
                // 如果需要配置负责人
                nextStep = "taskConf";
            } else {
                nextStep = "confirmStartProcess";
            }
            model.addAttribute("nextStep", nextStep);
            //
            String nodeHql = "from BpmFlowNode where nodeCode=? ";
            List<BpmFlowNode> bpmFlowNodes = bpmFlowNodeDao.find(nodeHql, formInfo.getActivityId());
            List<BpmNodeForm> bpmNodeForms = bpmNodeFormDao.find("from BpmNodeForm where flowId=? and nodeId=?",
                    formInfo.getProcessDefinitionId(), bpmFlowNodes.get(0).getId());

            if (!bpmNodeForms.isEmpty()) {
                if (Integer.valueOf(1).equals(bpmNodeForms.get(0).getFormType())) {
                    String redirectUrl = bpmNodeForms.get(0).getFormValue() + "?processDefinitionId=" + formInfo.getProcessDefinitionId();
                    return "redirect:" + redirectUrl;
                }
            }

//            FormTemplate formTemplate = formTemplateManager.get(Long.parseLong(formInfo.getFormKey()));
//            if (Integer.valueOf(1).equals(formTemplate.getType())) {
//                String redirectUrl = formTemplate.getContent() + "?processDefinitionId="
//                        + formInfo.getProcessDefinitionId();
//                return "redirect:" + redirectUrl;
//            }
//
//            model.addAttribute("formTemplate", formTemplate);
//
//            Record record = keyValue.findByCode(businessKey);
//            if (record != null) {
//                Map map = new HashMap();
//                for (Prop prop : record.getProps().values()) {
//                    map.put(prop.getCode(), prop.getValue());
//                }
//                String json = jsonMapper.toJson(map);
//                model.addAttribute("json", json);
//            }
//            return "form/form-viewStartForm";
            return "redirect:/testForm/testForm-list.do";
        } else {
            // 如果没找到form，就判断是否配置负责人
//            return taskConf(new LinkedMultiValueMap(), bpmProcessId, businessKey, nextStep, model);
            return "redirect:/testForm/testForm-list.do";
        }
        
    }

    /**
     * 配置每个任务的参与人.
     */
//    @RequestMapping("form-taskConf")
//    public String taskConf(@RequestParam MultiValueMap<String, String> multiValueMap, 
//            @RequestParam("bpmProcessId") String bpmProcessId,
//            @RequestParam(value = "businessKey", required = false) String businessKey,
//            @RequestParam(value = "nextStep", required = false) String nextStep, Model model) {
//        model.addAttribute("bpmProcessId", bpmProcessId);
//
//        Map<String, String[]> parameterMap = new HashMap<String, String[]>();
//        for (Map.Entry<String, List<String>> entry : multiValueMap.entrySet()) {
//            parameterMap.put(entry.getKey(), entry.getValue().toArray(new String[0]));
//        }
//
//        businessKey = new SaveDraftOperation().execute(parameterMap);
//        model.addAttribute("businessKey", businessKey);
//
//        BpmProcess bpmProcess = bpmProcessDao.get(bpmProcessId);
//        String processDefinitionId = bpmProcess.getBpmConfBase().getProcessDefinitionId();
//
//        if (Integer.valueOf(1).equals(bpmProcess.getUseTaskConf())) {
//            // 如果需要配置负责人
//            nextStep = "confirmStartProcess";
//            model.addAttribute("nextStep", nextStep);
//
//            FindTaskDefinitionsCmd cmd = new FindTaskDefinitionsCmd(processDefinitionId);
//            List<TaskDefinition> taskDefinitions = processEngine.getManagementService().executeCommand(cmd);
//            model.addAttribute("taskDefinitions", taskDefinitions);
//
//            return "form/form-taskConf";
//        } else {
//            // 如果不需要配置负责人，就进入确认发起流程的页面
//            return confirmStartProcess(bpmProcessId, multiValueMap, model);
//        }
//    }

    // ======================================================================
    @Resource
    public void setBpmProcessDao(BpmProcessDao bpmProcessDao) {
        this.bpmProcessDao = bpmProcessDao;
    }
    @Resource
    public void setBpmProcessVersionDao(BpmProcessVersionDao bpmProcessVersionDao) {
        this.bpmProcessVersionDao = bpmProcessVersionDao;
    }
    @Resource
    public void setBpmNodeFormDao(BpmNodeFormDao bpmNodeFormDao) {
        this.bpmNodeFormDao = bpmNodeFormDao;
    }
    @Resource
    public void setBpmFlowNodeDao(BpmFlowNodeDao bpmFlowNodeDao) {
        this.bpmFlowNodeDao = bpmFlowNodeDao;
    }
    @Resource
    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }
}
