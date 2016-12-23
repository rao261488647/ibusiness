package com.ibusiness.common.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibusiness.doc.entity.DocInfo;
import com.ibusiness.doc.service.DocInfoService;

/**
 * 共用管理controller
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("quartz")
public class QuartzController {

    private DocInfoService docInfoService;
    /**
     * 定时任务
     */
    public String parserReportData() {
        List<DocInfo> list = docInfoService.getAll();
        System.out.println("========================================");
        return "common/memory/memory-info.jsp";
    }
    // ~ ======================================================================
    @Resource
    public void setDocInfoService(DocInfoService docInfoService) {
        this.docInfoService = docInfoService;
    }
}
