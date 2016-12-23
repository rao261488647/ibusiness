package com.webservice.weixin.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codegenerate.financemgr.entity.Financial_settlementEntity;
import com.codegenerate.financemgr.service.Financial_settlementService;
import com.ibusiness.core.spring.ApplicationContextHelper;
import com.webservice.weixin.model.WxPayReq;
import com.webservice.weixin.model.WxPayRes;
import com.webservice.weixin.model.WxPayToApp;
import com.webservice.weixin.service.IServiceRequest;
import com.webservice.weixin.util.Configure;
import com.webservice.weixin.util.ServiceRequestWx;
import com.webservice.weixin.util.SystemConfig;
import com.webservice.weixin.util.Util;
import com.webservice.weixin.util.WxPayCommonUtil;

/**
 * 微信controller
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("wxpay")
public class WxPayController {
	// 
	public String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
				Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}
	
    /**
     * 取得openId
     * 
     * 第一步：用户同意授权，获取code
     * 第二步：通过code换取网页授权access_token 
     */
    @RequestMapping("getcode_request")
    public JSONObject getCodeRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String code = request.getParameter("code");
        // 
        String userid = request.getParameter("state");
        System.out.println("===============================code:" + code + "  ======= state:"+userid);
        
        String openId = "";
        if (!"authdeny".equals(code)) {
            // 第二步：通过code换取网页授权access_token
            String codeurl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
            codeurl = codeurl.replace("APPID", Configure.getAppid());
            codeurl = codeurl.replace("SECRET", Configure.getAppSecret());
            codeurl = codeurl.replace("CODE", code);
            JSONObject jsonObject = JSONObject.fromObject(WxPayCommonUtil.httpsRequest(codeurl, "GET", null));
            openId = jsonObject.getString("openid");
        }
        request.getSession().setAttribute("openid", openId);
        // 
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("openid", openId);
        return jsonObject;
    }
    /**
     * 微信回调接口--PC版
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("wx_return_app")
    public void weixin_return(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("===========微信回调接口===========");
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
        outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息
        System.out.println("back:"+result);
        // 将XML解析成Map
        Map<String,Object> resultMap = WxPayCommonUtil.getMapFromXML(result);
        // 
        // 查询订单
        List<Financial_settlementEntity> orders = getFinancial_settlementService().find("from Financial_settlementEntity where orderformid=?", resultMap.get("out_trade_no"));
        if (orders != null && orders.size() > 0) {
        	Financial_settlementEntity order = orders.get(0);
            System.out.println("  order!= NULL ===========:"+order.getId());
            // 微信请求支付查询服务
            if(new WxPayCommonUtil().queryWxService(resultMap.get("out_trade_no").toString())){
                // 判断是否已支付
                if (!"1".equals(order.getIspay())) {
                    order.setIspay("1");
                    order.setPaymsg("已支付");
//                    order.setPayTime(new Date());
                    // 更新订单管理表
                    getFinancial_settlementService().update(order);
//                    // 发送短信
//                    SendSmsUtil.getInstance().sendAddOrderForm(String.valueOf(order.getAddr().getId()), String.valueOf(order.getStore().getId()),first_goods_id);
                }
            }
        } else {
//            mv.addObject("op_title", "微信支付回调失败！");
        }
        System.out.println("===========微信回调接口结束==========="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return;
    }
	
    /**
     * 微信回调接口--移动HTML5版
     */
    @RequestMapping({"wx_return_jsapi.htm" })
    public void weixinReturnJsapi(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        System.out.println("==========jsapi版微信回调接口===========");
//        InputStream inStream = request.getInputStream();
//        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int len = 0;
//        while ((len = inStream.read(buffer)) != -1) {
//        outSteam.write(buffer, 0, len);
//        }
//        outSteam.close();
//        inStream.close();
//        String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息
//        System.out.println("back:"+result);
//        // 将XML解析成Map
//        Map<String,Object> resultMap = WxPayCommonUtil.getMapFromXML(result);
//        // 
//        OrderForm order = null;
//        // 查询订单
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("order_id", resultMap.get("out_trade_no"));
//        List<OrderForm> orders = getOrderFormService().query("from OrderForm where order_id=:order_id", params, -1, -1);
//        if (orders != null && orders.size() > 0) {
//            order = orders.get(0);
//            System.out.println("  order!= NULL ===========:"+order.getId());
//            // 微信请求支付查询服务
//            if(new WxPayCommonUtil().queryWxService(resultMap.get("out_trade_no").toString())){
//                System.out.println("orderFormId====="+order.getId()+"-----order.getOrder_status()："+order.getOrder_status());
//                // 
//                if (order.getOrder_status() != 20 && order.getOrder_status() != 40 && !"已支付".equals(order.getPay_msg())) {
//                    order.setOrder_status(20);
//                    order.setPay_msg("已支付");
//                    order.setPayTime(new Date());
//                    // 更新订单管理表
//                    getOrderFormService().update(order);
////                    // 发送短信
////                    SendSmsUtil.getInstance().sendAddOrderForm(String.valueOf(order.getAddr().getId()), String.valueOf(order.getStore().getId()),first_goods_id);
//                    // 减少库存
//                    update_goods_inventory(order);
//                    // log
//                    OrderFormLog ofl = new OrderFormLog();
//                    ofl.setAddTime(new Date());
//                    ofl.setLog_info("微信在线支付");
//                    ofl.setLog_user(order.getUser());
//                    ofl.setOf(order);
//                    getOrderFormLogService().save(ofl);
//                }
//            }
//            System.out.println("===========微信回调接口结束==========="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        } else {
//            System.out.println("===========微信支付回调失败！==========="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        }
        return;
    }
    
    /**
     * 请求支付服务
     * @return API返回的数据
     */
    public String requestScanPayService(String outTradeNo, String total_fee, String body) {
		String notify_url = SystemConfig.LOCAL_SERVICE + "/wxpay/wx_return_app"; // 回调地址
		BigDecimal a1 = new BigDecimal(total_fee);
		BigDecimal b1 = new BigDecimal(100);
		BigDecimal result1 = a1.multiply(b1);
		int fee = result1.intValue();
		
		// 组织请求参数对象
		WxPayReq scanPayReqData = new WxPayReq(body, "微信支付",outTradeNo,
				fee, "WEB", Configure.getIP(), "WXG", "APP", notify_url);

		JSONObject jsonObject = new JSONObject();
		String response = ""; // API返回
		try {
			IServiceRequest httpReq = new ServiceRequestWx();
			response = httpReq.sendPost(Configure.PAY_API, scanPayReqData);
            if(!"".equals(response) || response != null){
				//将从API返回的XML数据映射到Java对象
		        WxPayRes scanPayResData = (WxPayRes) Util.getObjectFromXML(response, WxPayRes.class);
		        jsonObject.put("return_msg", scanPayResData.getReturn_msg());
		        if (!"OK".equals(scanPayResData.getReturn_msg())) {
		        	jsonObject.put("dates", "");
		        	return jsonObject.toString();
		        }
		        String prepayid = scanPayResData.getPrepay_id();
		        
				Map<String,String> map = new HashMap<String, String>();
				map.put("appid", scanPayResData.getAppid());
				map.put("partnerid", scanPayResData.getMch_id());
				map.put("prepayid", prepayid);
				map.put("packages", "Sign=WXPay");
				map.put("noncestr", scanPayResData.getNonce_str());
				
				DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				Date date = new Date();
				String timestamp = df.format(date);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				long millionSeconds = sdf.parse(timestamp).getTime();//毫秒
				timestamp = (millionSeconds + "").substring(0, 10);
				map.put("timestamp", timestamp);
				
				WxPayToApp wpta = new WxPayToApp(scanPayResData.getAppid(),
							scanPayResData.getMch_id(), prepayid, "Sign=WXPay",
							scanPayResData.getNonce_str(), timestamp);
				map.put("sign", wpta.getSign());
				// 设置JSON
				jsonObject.put("dates", map);
			} else {
				jsonObject.put("return_msg", "response为null");
				jsonObject.put("dates", "");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
    }
    
	// =================================================================================
	// 财务结算
    public Financial_settlementService getFinancial_settlementService() {
        return ApplicationContextHelper.getBean(Financial_settlementService.class);
    }
//    // 系统文件
//    public ISysConfigService getISysConfigService() {
//        ApplicationContext applicationContext = CommonBusiness.getInstance().getApplicationContext();
//        return (ISysConfigService) applicationContext.getBean("sysConfigService");
//    }
//    // 订单
//    public IOrderFormService getOrderFormService() {
//        ApplicationContext applicationContext = CommonBusiness.getInstance().getApplicationContext();
//        return (IOrderFormService) applicationContext.getBean("orderFormServiceImpl");
//    }
//    // 用户配置信息
//    public IUserConfigService getUserConfigService() {
//        ApplicationContext applicationContext = CommonBusiness.getInstance().getApplicationContext();
//        return (IUserConfigService) applicationContext.getBean("userConfigServiceImpl");
//    }
//    // 取得jdbcTemplate对象
//    public JdbcTemplate getJdbcTemplate() {
//        ApplicationContext applicationContext = CommonBusiness.getInstance().getApplicationContext();
//        return (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
//    }
}
