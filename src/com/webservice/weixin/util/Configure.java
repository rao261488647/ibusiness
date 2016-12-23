package com.webservice.weixin.util;

import java.io.File;

/**
 * 微信支付 各种配置数据
 * 
 * @author Administrator
 *
 */
public class Configure {
	
	// API密钥key 自己要保管好的私有Key（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
	private static String key = "TTtongtu123456789012345678901234";

	//微信分配的公众号ID（开通公众号之后可以获取到）
	private static String appID = "wxb1a56b0e36de95bb";
	// AppSecret(应用密钥)
	private static String appSecret = "96764440ed1a9e52878909588f79b615";

	//微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	private static String mchID = "1311514101";

	//受理模式下给子商户分配的子商户号
	private static String subMchID = "";

	//HTTPS证书的本地路径
	private static String certLocalPath = SystemConfig.UPLOAD_IMG_PATH + File.separator + "apiclient_cert.p12";

	//HTTPS证书密码，默认密码等于商户号MCHID
	private static String certPassword = "1311514101";

	//是否使用异步线程的方式来上报API测速，默认为异步模式
	private static boolean useThreadToDoReport = true;

	//机器IP
	private static String ip = "192.168.50.211";

	//以下是几个API的路径：
	//1）支付API
	public static String PAY_API = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	//2）支付查询API
	public static String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";

	//3）退款API
	public static String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	//4）退款查询API
	public static String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";

	//5）撤销API
	public static String REVERSE_API = "https://api.mch.weixin.qq.com/secapi/pay/reverse";

	//6）下载对账单API
	public static String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";

	//7) 统计上报API
	public static String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";

	//8) 关闭订单API
	public static String CLOSE_API = "https://api.mch.weixin.qq.com/pay/closeorder";
	
	public static boolean isUseThreadToDoReport() {
		return useThreadToDoReport;
	}

	public static void setUseThreadToDoReport(boolean useThreadToDoReport) {
		Configure.useThreadToDoReport = useThreadToDoReport;
	}

	public static String HttpsRequestClassName = "com.jumi.webservice.weixin.util.HttpsRequest";

	public static void setKey(String key) {
		Configure.key = key;
	}

	public static void setAppID(String appID) {
		Configure.appID = appID;
	}

	public static void setMchID(String mchID) {
		Configure.mchID = mchID;
	}

	public static void setSubMchID(String subMchID) {
		Configure.subMchID = subMchID;
	}

	public static void setCertLocalPath(String certLocalPath) {
		Configure.certLocalPath = certLocalPath;
	}

	public static void setCertPassword(String certPassword) {
		Configure.certPassword = certPassword;
	}

	public static void setIp(String ip) {
		Configure.ip = ip;
	}

	public static String getKey(){
		return key;
	}
	
	public static String getAppid(){
		return appID;
	}
	
	public static String getMchid(){
		return mchID;
	}

	public static String getSubMchid(){
		return subMchID;
	}
	
	public static String getCertLocalPath(){
		return certLocalPath;
	}
	
	public static String getCertPassword(){
		return certPassword;
	}

	public static String getIP(){
		return ip;
	}

	public static void setHttpsRequestClassName(String name){
		HttpsRequestClassName = name;
	}

	public static String getAppSecret() {
		return appSecret;
	}

	public static void setAppSecret(String appSecret) {
		Configure.appSecret = appSecret;
	}
}
