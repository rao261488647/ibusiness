package com.ibusiness.bpm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibusiness.bpm.dao.BpmFlowNodeDao;
import com.ibusiness.bpm.entity.BpmFlowNode;
import com.ibusiness.bpm.entity.BpmNodeColums;
import com.ibusiness.bpm.entity.BpmNodeTable;
import com.ibusiness.bpm.service.BpmNodeColumsService;

/**
 * 流程节点配置
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("bpm-process")
public class BpmConfNodeController {
    private BpmFlowNodeDao bpmFlowNodeDao;
    private BpmNodeColumsService bpmNodeColumsService;

    /**
     * 配置列表
     * 
     * @param flowVersionId
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("bpm-conf-node-list")
    public String list(@RequestParam("flowVersionId") String flowVersionId, 
            @RequestParam(value = "packageName", required = false) String packageName, 
            @RequestParam(value = "bpmId", required = false) String bpmId, Model model) {
        String hql = "from BpmFlowNode where flowVersionId=? order by priority";
        List<BpmFlowNode> bpmFlowNodes = bpmFlowNodeDao.find(hql, flowVersionId);
        
        model.addAttribute("packageName", packageName);
        model.addAttribute("bpmId", bpmId);
        model.addAttribute("bpmFlowNodes", bpmFlowNodes);
        model.addAttribute("tabType", "confNode");
        return "ibusiness/bpm/bpm-process-input.jsp";
    }
    /**
     * 显示启动流程的表单.
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("conf-node-colums")
    public String confNodeColums(@RequestParam(value = "packageName", required = false) String packageName, 
            @RequestParam("flowId") String flowId, @RequestParam("nodeId") String nodeId, Model model) throws Exception {
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
        //
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
        
        return "ibusiness/bpm/bpm-conf-node-colums.jsp";
    }
    /**
     * 保存
     * @return
     */
    @RequestMapping("save-conf-node-colums")
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
        
        return "redirect:/bpm-process/conf-node-colums.do?packageName="+packageName+"&flowId="+flowId+"&nodeId="+nodeId;
    }
    // ======================================================================
    @Resource
    public void setBpmFlowNodeDao(BpmFlowNodeDao bpmFlowNodeDao) {
        this.bpmFlowNodeDao = bpmFlowNodeDao;
    }
    @Resource
    public void setBpmNodeColumsService(BpmNodeColumsService bpmNodeColumsService) {
        this.bpmNodeColumsService = bpmNodeColumsService;
    }
}
