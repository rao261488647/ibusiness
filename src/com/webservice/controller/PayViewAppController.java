package com.webservice.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codegenerate.financemgr.entity.Financial_settlementEntity;
import com.codegenerate.financemgr.service.Financial_settlementService;
import com.ibusiness.common.util.CommonUtils;
import com.webservice.utils.AlipayCheck;
/**
 * 支付视图 action
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("payview")
public class PayViewAppController {
	// 财务结算表
	private Financial_settlementService fsService;
    /**
     * 商户同步通知处理页面。
     * 程序上自动进行重新构造URL地址链接，在用户当前页面上通过自动跳转的方式跳回商户在请求时设定好的页面路径地址
     */
    @RequestMapping({"aplipay_return_app" })
    public void aplipay_return(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	System.out.println("=========商户同步通知处理页面===========");
        String trade_no = request.getParameter("trade_no");
        // 商户网站唯一订单号
        String order_no = request.getParameter("out_trade_no");
        // 商品单价
        String total_fee = request.getParameter("price");
        // 商品名称
        String subject = request.getParameter("subject");
        // 商品描述
        String type = CommonUtils.null2String(request.getParameter("body")).trim();
        // 交易状态
        String trade_status = request.getParameter("trade_status");
        // 财务结算表
        Financial_settlementEntity orderForm = null;

        System.out.println(type.equals("goods")+"==========="+"goods".equals(type)+"-------app----------type为-----------------"+type);
        System.out.println("trade_no:"+trade_no+"==========order_no:"+order_no+"=========subject:"+subject);
        if (type.equals("goods")) {
        	orderForm = this.fsService.get(CommonUtils.null2String(subject));
            System.out.println("--------------------------"+orderForm);
        }

        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = valueStr + values[i] + ",";
            }
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        // 
        String notify_id = request.getParameter("notify_id");
        boolean verify_result = AlipayCheck.verify(notify_id);
        if (verify_result) {
        	System.out.println("----同步-----verify_result验证通过-----------");
            if ((type.equals("goods"))
                    && ((trade_status.equals("WAIT_SELLER_SEND_GOODS")) || (trade_status.equals("TRADE_FINISHED")) || (trade_status
                            .equals("TRADE_SUCCESS")))) {
                if (!"1".equals(orderForm.getIspay())) {
                	orderForm.setIspay("1");
                	orderForm.setPaymsg("已支付");
//                	orderForm.setPayTime(new Date());
                    this.fsService.update(orderForm);
                }
            }
        }
        return;
    }

    /**
     * 服务器异步通知处理页面,获取支付宝返回的结果数据。
     * 支付宝服务器主动发起通知，调用商户在请求时设定好的页面路径
     */
    @RequestMapping({"alipay_notify_app" })
    public void alipay_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	System.out.println(" 服务器异步通知处理页面,获取支付宝返回的结果数据===========");
        String trade_no = request.getParameter("trade_no");
        // 商户网站唯一订单号
        String order_no = request.getParameter("out_trade_no");
        // 商品单价
        String total_fee = request.getParameter("price");
        // 商品名称
        String subject = request.getParameter("subject");
        // 商品描述
        String type = CommonUtils.null2String(request.getParameter("body")).trim();
        // 交易状态
        String trade_status = request.getParameter("trade_status");
        // 
        Financial_settlementEntity orderForm = null;
        if (type.equals("goods")) {
        	orderForm = this.fsService.get(CommonUtils.null2String(subject));
        }

        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = valueStr + values[i] + ",";
            }

            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        
        // 支付宝通知
        String notify_id = request.getParameter("notify_id");
        boolean verify_result = AlipayCheck.verify(notify_id);
        // 如果获得的信息是true，则校验成功
        if (verify_result) {
        	System.out.println("-----异步-----verify_result验证通过-----------");
            if ((type.equals("goods"))
                    && ((trade_status.equals("WAIT_SELLER_SEND_GOODS")) || (trade_status.equals("TRADE_FINISHED")) || (trade_status
                            .equals("TRADE_SUCCESS")))) {
            	if(!"1".equals(orderForm.getIspay())){
	            	orderForm.setIspay("1");
                	orderForm.setPaymsg("已支付");
//                	orderForm.setPayTime(new Date()); TODO
                    this.fsService.update(orderForm);
            	}
            }
            response.setContentType("text/plain");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            try {
                PrintWriter writer = response.getWriter();
                writer.print("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            response.setContentType("text/plain");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            try {
                PrintWriter writer = response.getWriter();
                writer.print("fail");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // =================================
    @Resource
	public void setFinancial_settlementService(Financial_settlementService financial_settlementService) {
		this.fsService = financial_settlementService;
	}
}