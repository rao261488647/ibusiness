package com.webservice.weixin.util;

import java.net.URLEncoder;

/**
 * 
 * @author JiangBo
 * 
 */
public class GetWeiXinCode {
	public static String getCodeRequest(String redirectUri, String userId) {
		String getCodeRequest = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
		String result = null;

		getCodeRequest = getCodeRequest.replace("APPID", urlEnodeUTF8(Configure.getAppid()));

		getCodeRequest = getCodeRequest.replace("REDIRECT_URI", urlEnodeUTF8(redirectUri));

		getCodeRequest = getCodeRequest.replace("SCOPE", "snsapi_base");
		// 将当前用户ID传递到回调页面
		getCodeRequest = getCodeRequest.replace("STATE", userId);

		result = getCodeRequest;

		return result;

	}

	public static String urlEnodeUTF8(String str) {
		String result = str;
		try {
			result = URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
