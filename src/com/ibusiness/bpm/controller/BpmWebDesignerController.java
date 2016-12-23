package com.ibusiness.bpm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 在线画图用controller
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("bpm-web-designer")
public class BpmWebDesignerController {

    @RequestMapping("queryFlow")
    public void queryFlow(String flowId, @RequestParam("unitId") String unitIds) {
        String s="";
        flowId = s;
        
    }
}
