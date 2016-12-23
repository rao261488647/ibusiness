package com.ibusiness.common.sevlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibusiness.bpm.service.BpmComBusiness;


/**
 * 系统初始化类
 * 
 * @author JiangBo
 *
 */
public class InitServlet extends HttpServlet {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 实例化log4J对象
     */
    private static Logger logger = LoggerFactory.getLogger(InitServlet.class);
    /**
     * 初始化方法
     */
    public void init() throws ServletException {
        super.init();
        
        // 启动平台时候初始化所有流程
      //  new BpmComBusiness().initBpmFlow();
        
     // 取得构造器
//        // 通过构造器取得spring中的 CommonService 对象
//        ApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext( this.getServletContext() );
//        //初始化CommonBusiness单例对象
//        CommonBusiness commonBusiness = CommonBusiness.getInstance();
//        //注入到CommonBusiness单例对象中
        

        logger.error("============= Initialize servlet start success. =============");
    }
    
    private int height = 30;
    private int width = 80;

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 一、绘图
        // step1, 画布(内存映像对象)
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // step2,画笔 (Graphics对象)
        Graphics g = image.getGraphics();
        // step3, 给笔上颜色
        Random r = new Random();
        g.setColor(new Color((int)(100+Math.random()*(255-99)), (int)(100+Math.random()*(255-99)), (int)(100+Math.random()*(255-99))));
        // step4, 给画布设置一个背景颜色
        g.fillRect(0, 0, width, height);
        // step5,绘图
        // String number = r.nextInt(10000) + "";
        String number = getNumber(4);
        // 将number绑订到session对象上
        HttpSession session = request.getSession();
        session.setAttribute("number", number);
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font(null, Font.BOLD, 24));
        g.drawString(number, 5, 25);
        // step6,加一些干扰线
        for (int i = 0; i < 5; i++) {
            g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
        }
        // 二、将图片压缩，然后输出到客户端。
        response.setContentType("image/jpeg");
        OutputStream output = response.getOutputStream();
        // write方法：将原始图片(image)按照
        // 指定的压缩算法(jpeg)进行压缩，然后，
        // 将压缩之后的数据缓存到response对象上。
        javax.imageio.ImageIO.write(image, "jpeg", output);

    }
    // A~Z,0~9
    private String getNumber(int size) {
//        String chars = "ABCDEFGHIJKLMNOPQ" + "RSTUVWXYZ0123456789";
    	String chars = "0123456789";
        String number = "";
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            number += chars.charAt(r.nextInt(chars.length()));
        }
        return number;
    }
}
