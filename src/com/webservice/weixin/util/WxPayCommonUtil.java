package com.webservice.weixin.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.webservice.weixin.model.WxPayDto;
import com.webservice.weixin.model.WxPayRes;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 微信支付 通用工具类
 * 
 * @author JiangBo
 */
public class WxPayCommonUtil {
    private static Logger log = LoggerFactory.getLogger(WxPayCommonUtil.class);

    /**
     * 调用统一下订单接口,取得prepayId等信息
     * body 商品名称信息
     * outTradeNo 订单号
     */
    public String wxPayOrder(String total_fee, String body, String outTradeNo, String openid, String notify_url) {
        // 创建参数对象
        SortedMap<Object, Object> parameters = createParameters(total_fee, body, outTradeNo, openid, notify_url);
        // 将请求参数转换为XML格式的string
        String requestXML = getRequestXml(parameters);
        // 4.调用统一下单接口,返回prepayId等信息
        String response = httpsRequest(Configure.PAY_API, "POST", requestXML);
        // 将从API返回的XML数据映射到Java对象
        System.out.println("支付API返回的数据如下：");
        System.out.println(response);
        // prepayId
        String prepayid = "";
        try {
            // 将从API返回的XML数据映射到Java对象
            if (!"".equals(response) || response != null) {
                // 将从API返回的XML数据映射到Java对象
                WxPayRes scanPayResData = (WxPayRes) getObjectFromXML(response, WxPayRes.class);
                prepayid = scanPayResData.getPrepay_id();
            }
        } catch (Exception e) {
            System.out.println("==============调用统一下订单接口,取得prepayId信息异常: " + e.toString());
        }
        return prepayid;
    }
    
    /**
     * 微信请求支付查询服务
     * API返回的数据
     */
    public Boolean queryWxService(String out_trade_no) {
        // 组织请求参数对象
        SortedMap<Object, Object> parameters = getParamsByOutTradeNo(out_trade_no);
        Boolean result=false;
        //解决XStream对出现双下划线的bug
        //将要提交给API的数据对象转换成XML格式数据Post给API
        String response = ""; // API返回
        try {
            // 将请求参数转换为XML格式的string
            String requestXML = getRequestXml(parameters);
            response = httpsRequest(Configure.PAY_QUERY_API, "POST", requestXML);
            System.out.println("微信支付查询服务返回的数据如下：");
            System.out.println(response);
            // 将从API返回的XML数据映射到Java对象
            if(!"".equals(response) || response != null){
                //将从API返回的XML数据映射到Java对象
                WxPayRes scanPayResData = (WxPayRes) getObjectFromXML(response, WxPayRes.class);
                if("SUCCESS".equals(scanPayResData.getResult_code())){
                    result=true;
                }
            }
        }catch (Exception e) {
            System.out.println("=============微信请求支付查询服务异常:"+e.toString());
        }
        return result;
    }
    /**
     * 根据 商户订单号生产参数
     */
    private SortedMap<Object,Object> getParamsByOutTradeNo(String outTradeNo){
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        // 微信分配的公众号ID（开通公众号之后可以获取到）
        parameters.put("appid", Configure.getAppid());
        // 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        parameters.put("mch_id", Configure.getMchid());
        // 随机字符串，不长于32 位
        parameters.put("nonce_str", createNoncestr());
        // 商户系统自己生成的唯一的订单号
        parameters.put("out_trade_no", outTradeNo);
        // 根据API的签名规则进行签名
        String sign = createSign("UTF-8", parameters);
        parameters.put("sign", sign);
        return parameters;
    }
    /**
     * 移动h5用HTML用的JSON
     */
    public JSONObject createJsonByHtml(String prepayid) {
        JSONObject jsonObject = new JSONObject();
        // 签名
        SortedMap<Object, Object> params = new TreeMap<Object, Object>();
        // 微信分配的公众号ID（开通公众号之后可以获取到）
        params.put("appId", Configure.getAppid());
        String timeStamp = Long.toString(new Date().getTime());
        params.put("timeStamp", timeStamp);
        // 随机字符串，不长于32 位
        String nonceStr = createNoncestr();
        params.put("nonceStr", nonceStr);
        params.put("package", "prepay_id=" + prepayid);
        params.put("signType", "MD5");
        // 根据API的签名规则进行签名
        String paySign = createSign("UTF-8", params);
        params.put("sign", paySign);
        // 签名
        jsonObject.put("paySign", paySign);
        jsonObject.put("timeStamp", timeStamp);
        // 随即串
        jsonObject.put("nonceStr", nonceStr);
        jsonObject.put("prepay_id", "prepay_id=" + prepayid);
        return jsonObject;
    }
    /**
     * 创建参数对象
     */
    private SortedMap<Object,Object> createParameters(String total_fee, String body, String outTradeNo, String open_id, String notify_url) {
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        // 微信分配的公众号ID（开通公众号之后可以获取到）
        parameters.put("appid", Configure.getAppid());
        // 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        parameters.put("mch_id", Configure.getMchid());
        // 随机字符串，不长于32 位
        parameters.put("nonce_str", createNoncestr());
        parameters.put("body", body);
        // 商户系统自己生成的唯一的订单号
        parameters.put("out_trade_no", outTradeNo);
        // 支付金额
        parameters.put("total_fee", getMoney(total_fee));
        // IP
        parameters.put("spbill_create_ip", Configure.getIP());
        parameters.put("notify_url", notify_url);
        parameters.put("trade_type", "JSAPI");
        parameters.put("openid", open_id);
        // 根据API的签名规则进行签名
        String sign = createSign("UTF-8", parameters);
        parameters.put("sign", sign);
        return parameters;
    }
    /**
     * 发送HTTPS请求
     * 
     * @param requestUrl 请求地址
     * @param requestMethod
     *            请求方式（GET、POST）
     * @param outputStr
     *            提交的数据
     * @return 返回微信服务器响应的信息
     */
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
            log.error("连接超时：{}", ce);
        } catch (Exception e) {
            log.error("https请求异常：{}", e);
        }
        return null;
    }
    /**
     * 随机串
     */
    public static String createNoncestr() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";
        for (int i = 0; i < 16; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }
    
    /**
     * 将请求参数转换为XML格式的string
     * 
     * @param 请求参数
     */
    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        for (Map.Entry<Object, Object> entry : parameters.entrySet()) {
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }
    
    /**
     * sign签名
     * 
     * @param 编码格式
     * @param 请求参数
     */
    public static String createSign(String characterEncoding, SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<Object, Object> entry : parameters.entrySet()) {
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + Configure.getKey());
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }
    /**
     * 将从API返回的XML数据映射到Java对象
     */
    @SuppressWarnings("rawtypes")
    public static Object getObjectFromXML(String xml, Class tClass) {
        //将从API返回的XML数据映射到Java对象
        XStream xStreamForResponseData = new XStream(new DomDriver());
        xStreamForResponseData.alias("xml", tClass);
        xStreamForResponseData.ignoreUnknownElements();//暂时忽略掉一些新增的字段
        return xStreamForResponseData.fromXML(xml);
    }
    // ========================================================================
    /**
     * 获取微信扫码支付二维码连接
     */
    public static String getCodeurl(WxPayDto tpWxPayDto){
        
        // 1 参数
        // 订单号
        String orderId = tpWxPayDto.getOrderId();
        // 附加数据 原样返回
        String attach = "";
        // 总金额以分为单位，不带小数点
        String totalFee = getMoney(tpWxPayDto.getTotalFee());
        
        // 订单生成的机器 IP
        String spbill_create_ip = tpWxPayDto.getSpbillCreateIp();
        // 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
        String notify_url = SystemConfig.LOCAL_SERVICE + "/wxpay/wx_return_app.htm";
        String trade_type = "NATIVE";

        // 商户号
        String mch_id = Configure.getMchid();
        // 随机字符串
        String nonce_str = createNoncestr();

        // 商品描述根据情况修改
        String body = tpWxPayDto.getBody();

        // 商户订单号
        String out_trade_no = orderId;

        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", Configure.getAppid());
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("attach", attach);
        packageParams.put("out_trade_no", out_trade_no);

        // 这里写的金额为1 分到时修改
        packageParams.put("total_fee", totalFee);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);

        packageParams.put("trade_type", trade_type);

        RequestHandler reqHandler = new RequestHandler(null, null);
        reqHandler.init(Configure.getAppid(), Configure.getAppSecret(), Configure.getKey());

        String sign = reqHandler.createSign(packageParams);
        String xml = "<xml>" + "<appid>" + Configure.getAppid() + "</appid>" + "<mch_id>"
                + mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
                + "</nonce_str>" + "<sign>" + sign + "</sign>"
                + "<body><![CDATA[" + body + "]]></body>" 
                + "<out_trade_no>" + out_trade_no
                + "</out_trade_no>" + "<attach>" + attach + "</attach>"
                + "<total_fee>" + totalFee + "</total_fee>"
                + "<spbill_create_ip>" + spbill_create_ip
                + "</spbill_create_ip>" + "<notify_url>" + notify_url
                + "</notify_url>" + "<trade_type>" + trade_type
                + "</trade_type>" + "</xml>";
        String code_url = "";
        String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        
        code_url = GetWxOrderno.getCodeUrl(createOrderURL, xml);
        System.out.println("code_url----------------"+code_url);
        return code_url;
    }
    
    /**
     * 元转换成分
     */
    public static String getMoney(String amount) {
        if(amount==null){
            return "";
        }
        // 金额转化为分为单位
        String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额  
        int index = currency.indexOf(".");  
        int length = currency.length();  
        Long amLong = 0l;  
        if(index == -1){  
            amLong = Long.valueOf(currency+"00");  
        }else if(length - index >= 3){  
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));  
        }else if(length - index == 2){  
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);  
        }else{  
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");  
        }  
        return amLong.toString(); 
    }
    
    /**
     * 获取编码字符集
     */
    public static String getCharacterEncoding(HttpServletRequest request, HttpServletResponse response) {
        if(null == request || null == response) {
            return "gbk";
        }
        
        String enc = request.getCharacterEncoding();
        if(null == enc || "".equals(enc)) {
            enc = response.getCharacterEncoding();
        }
        
        if(null == enc || "".equals(enc)) {
            enc = "gbk";
        }
        return enc;
    }
    /**
     * 将XML解析成Map
     */
    public static Map<String,Object> getMapFromXML(String xmlString) throws ParserConfigurationException, IOException, SAXException {
        //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is =  getStringStream(xmlString);
        Document document = builder.parse(is);

        //获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        Map<String, Object> map = new HashMap<String, Object>();
        int i=0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if(node instanceof Element){
                map.put(node.getNodeName(),node.getTextContent());
            }
            i++;
        }
        return map;
    }
    /**
     * XML字符串解析成流
     */
    public static InputStream getStringStream(String sInputString) {
        ByteArrayInputStream tInputStringStream = null;
        if (sInputString != null && !sInputString.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
        }
        return tInputStringStream;
    }

    /**
     * 获取微信授权CODE URL
     */
    public static String getWxCodeUrl(String url, String userId) {
        String codeurl = GetWeiXinCode.getCodeRequest(url, userId);
        return codeurl;
    }
}