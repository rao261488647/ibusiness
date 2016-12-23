package com.ibusiness.bpm.cmd;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.ibusiness.bpm.graph.ActivitiGraphBuilder;
import com.ibusiness.bpm.graph.Graph;
/**
 * 查询流程图表Command命令模式类
 * 
 * @author JiangBo
 *
 */
public class FindGraphCmd implements Command<Graph> {
    private String processDefinitionId;

    public FindGraphCmd(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public Graph execute(CommandContext commandContext) {
        // 实例化一个ACTIVITI图形实体对象
        return new ActivitiGraphBuilder(processDefinitionId).build();
    }
}
