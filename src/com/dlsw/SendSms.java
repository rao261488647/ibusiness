package com.dlsw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 短信
 * 
 * @author JiangBo
 * 
 */
public class SendSms {
	// 短信供应商提供--账号
	private String nameByMessage = "13828778461";
	// 短信供应商提供--密码
	private String passwordByMessage= "379102";
	// 短信供应商提供--签名
	private String signByMessage= "【通途汽贸】";
	
	/**
	 * 发送短信
	 */
	public String sendSms(String mobies, String content) {
		sendsmsPrivate(nameByMessage, passwordByMessage, mobies, content + signByMessage);
		return null;
	}
	/**
	 * 发送短信
	 */
	private String sendsmsPrivate(String accName, String accPwd, String mobies, String content) {
		StringBuffer sb = new StringBuffer("http://www.lx198.com/sdk/send?");
		try {
			sb.append("&accName=" + accName);
			sb.append("&accPwd=" + MD5.getMd5String(accPwd));
			sb.append("&aimcodes=" + mobies);
			sb.append("&content=" + URLEncoder.encode(content, "UTF-8"));
			sb.append("&bizId=" + BizNumberUtil.createBizId());
			sb.append("&dataType=string");
			URL url = new URL(sb.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			return in.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 */
	public static void main(String[] args) {
		// 签名【坤坤】
		new SendSms().sendSms("18245124726", "下午好【坤坤】");
	}
}
