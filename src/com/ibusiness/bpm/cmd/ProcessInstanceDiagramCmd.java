package com.ibusiness.bpm.cmd;

import java.io.InputStream;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.cmd.GetBpmnModelCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityManager;

/**
 * 流程实例Command命令类
 * 
 * @author JiangBo
 *
 */
public class ProcessInstanceDiagramCmd implements Command<InputStream> {
    protected String processInstanceId;

    public ProcessInstanceDiagramCmd(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    /**
     * 执行返回数据流
     */
    public InputStream execute(CommandContext commandContext) {
        ExecutionEntityManager executionEntityManager = commandContext.getExecutionEntityManager();
        ExecutionEntity executionEntity = executionEntityManager.findExecutionById(processInstanceId);
        List<String> activiityIds = executionEntity.findActiveActivityIds();
        String processDefinitionId = executionEntity.getProcessDefinitionId();

        GetBpmnModelCmd getBpmnModelCmd = new GetBpmnModelCmd(processDefinitionId);
        // BPMN的流程模型
        BpmnModel bpmnModel = getBpmnModelCmd.execute(commandContext);

        InputStream is = ProcessDiagramGenerator.generateDiagram(bpmnModel,"png", activiityIds);

        return is;
    }
}
