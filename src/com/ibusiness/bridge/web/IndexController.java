package com.ibusiness.bridge.web;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录成功后跳转页面控制器
 * 
 * @author JiangBo
 * 
 */
@Controller
public class IndexController {
    @RequestMapping("index")
    public String index() {
        return "/index.jsp";
    }
}
