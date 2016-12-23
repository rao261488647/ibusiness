package com.ibusiness.common.sevlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台管理servlet
 * 
 * @author JiangBo
 * 
 */
public class ManageServlet extends HttpServlet {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 执行
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用后台处理controller, 将前后台逻辑分开
        response.sendRedirect("manage/manage-show.do");  
    }

}
