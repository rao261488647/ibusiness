package com.ibusiness.bpm.graph;

import java.util.HashSet;
import java.util.Set;

import org.activiti.engine.impl.cmd.GetDeploymentProcessDefinitionCmd;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
/**
 * 创建ACTIVITI图形实体对象类
 * 
 * @author JiangBo
 *
 */
public class ActivitiGraphBuilder {
    private String processDefinitionId;
    private ProcessDefinitionEntity processDefinitionEntity;
    private Set<String> visitedNodeIds = new HashSet<String>();

    public ActivitiGraphBuilder(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    /**
     * 创建一个流程定义图形实体
     * @return
     */
    public Graph build() {
        // 获得流程定义实体
        this.fetchProcessDefinitionEntity();
        // 配置取得一个流程节点对象
        Node initial = visitNode(processDefinitionEntity.getInitial());
        Graph graph = new Graph();
        graph.setInitial(initial);

        return graph;
    }
    // 获得流程定义实体
    public void fetchProcessDefinitionEntity() {
        GetDeploymentProcessDefinitionCmd cmd = new GetDeploymentProcessDefinitionCmd(processDefinitionId);
        processDefinitionEntity = cmd.execute(Context.getCommandContext());
    }

    /**
     * 配置取得一个流程节点对象
     * 
     * @param pvmActivity
     * @return
     */
    public Node visitNode(PvmActivity pvmActivity) {
        if (null == pvmActivity || visitedNodeIds.contains(pvmActivity.getId())) {
            return null;
        }
        visitedNodeIds.add(pvmActivity.getId());
        // 创建一个流程任务节点
        Node currentNode = new Node();
        currentNode.setId(pvmActivity.getId());
        currentNode.setName(getString(pvmActivity.getProperty("name")));
        currentNode.setType(getString(pvmActivity.getProperty("type")));

        // getOutgoingTransitions()获取从某个节点出来的所有线路
        for (PvmTransition pvmTransition : pvmActivity.getOutgoingTransitions()) {
            PvmActivity destination = pvmTransition.getDestination();
            // 递归取得线路上的节点
            Node targetNode = visitNode(destination);
            if (targetNode == null) {
                continue;
            }
            // 设置节点元素之间的信息
            Edge edge = new Edge();
            edge.setId(pvmTransition.getId());
            edge.setSrc(currentNode);
            edge.setDest(targetNode);
            currentNode.getEdges().add(edge);
        }
        return currentNode;
    }

    /**
     * Object转换String对象
     * 
     * @param object
     * @return
     */
    private String getString(Object object) {
        if (object == null) {
            return null;
        } else if (object instanceof String) {
            return (String) object;
        } else {
            return object.toString();
        }
    }
}
