package com.ibusiness.component.portal.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 业务模块组件用controller。
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("manage")
public class ManageController {
    /**
     * 后台管理首页
     * @param parameterMap
     * @param model
     * @return
     */
    @RequestMapping("manage-show")
    public String show(@RequestParam Map<String, Object> parameterMap, Model model) {
        model.addAttribute("parentId", 0);
        // 返回JSP
        return "ibusiness/component/manage/manage-index.jsp";
    }
    // ======================================================================
}
