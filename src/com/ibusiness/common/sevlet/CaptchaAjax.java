package com.ibusiness.common.sevlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Ajax验证 输入的验证码 信息
 * 
 * @author JiangBo
 * 
 */
public class CaptchaAjax extends HttpServlet {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String webcode = request.getParameter("inputcode");
        HttpSession session = request.getSession();
        String sessioncode = (String) session.getAttribute("number");
        int result = 0;
        if (webcode.equalsIgnoreCase(sessioncode)) {
            result = 1;
        }
        out.println(result);

    }

}
