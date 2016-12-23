package com.ibusiness.boss.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibusiness.boss.entity.ProduceScheduleChart;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.util.CommonUtils;

/**   
 * 生产进度曲线 controller
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("produce-schedule-chart")
public class ProduceScheduleChartController {

    /**
     * 曲线列表
     */
    @RequestMapping("list-chart")
    public String chartList(Model model) {
        // 返回JSP
        return "ibusiness/boss/produceScheduleCharts.jsp";
    }
    /**
     * 曲线
     */
    @SuppressWarnings("deprecation")
    @ResponseBody
    @RequestMapping("show-chart")
    public String showChart(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
        List<ProduceScheduleChart> list = new ArrayList<ProduceScheduleChart>();
        for (int i=0; i < 20; i++) {
            ProduceScheduleChart bean = new ProduceScheduleChart();
            bean.setEventTime(new Date(2014-1900, 11, i));
            bean.setProduceId("1");
            if(i==15||i==16) {
                bean.setProduceNum(""+(28));
            } else {
                bean.setProduceNum(""+(i*2));
            }
            
            bean.setPlanNum(""+(50));
            list.add(bean);
        }
        // 返回JSON
        return CommonUtils.getJsonFromList(list, null).toString();
    }
    // ======================================================================
    /**
     * 柱形图列表
     */
    @RequestMapping("list-productSell")
    public String productSellList(Model model) {
        // 返回JSP
        return "ibusiness/boss/productSellHistogram.jsp";
    }
    /**
     * 柱状图
     */
    @ResponseBody
    @RequestMapping("show-histogram")
    public String showproductSell(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
    	// 产品列表
        List<ProduceScheduleChart> list = new ArrayList<ProduceScheduleChart>();
        for (int i = 0; i < 10; i++) {
            ProduceScheduleChart bean = new ProduceScheduleChart();
            bean.setEventTime(new Date());
            bean.setProductName("name"+i);
            //  
            bean.setProduceNum(""+Math.round(Math.random()*200+100));
            list.add(bean);
        }
        // 返回JSON
        return CommonUtils.getJsonFromList(list, null).toString();
    }
    // ======================================================================
    /**
     * 饼图列表
     */
    @RequestMapping("list-pieDonut")
    public String productSellPieDonutList(Model model) {
        // 返回JSP
        return "ibusiness/boss/productSellPicDonut.jsp";
    }
    /**
     * 饼图
     */
    @ResponseBody
    @RequestMapping("show-pieDonut")
    public String showPieDonut(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap, Model model) {
    	// 产品列表
        List<ProduceScheduleChart> list = new ArrayList<ProduceScheduleChart>();
    	for (int i=0; i < 10; i++) {
            ProduceScheduleChart bean = new ProduceScheduleChart();
            bean.setEventTime(new Date());
            bean.setProductName("name"+i);
            //  
            bean.setTotalAmount(""+Math.round(Math.random()*200+100));
            list.add(bean);
        }
        // 返回JSON
        return CommonUtils.getJsonFromList(list, null).toString();
    }
    // ======================================================================
    
}
