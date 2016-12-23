package com.webservice.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.webservice.weixin.util.SystemConfig;

public class AlipayCheck {
	public static boolean verify(String notify_id) {
        String responseTxt = "false";
        if (notify_id != null) {
            // 验证此次通知信息是否是支付宝服务器发来的信息
            responseTxt = verifyResponse( notify_id);
        }
        if (responseTxt.equals("true")) {
        	System.out.println("支付宝回调验证成功！");
            return true;
        }
        return false;
    }
	private static String verifyResponse(String notify_id) {
        String veryfy_url = "";
        veryfy_url = "http://notify.alipay.com/trade/notify_query.do?partner="+SystemConfig.PARTNER+"&notify_id=" + notify_id;

        String verifyFlag = checkUrl(veryfy_url);
        return verifyFlag;
    }
	private static String checkUrl(String urlvalue) {
        String inputLine = "";
        try {
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            inputLine = in.readLine().toString();
        } catch (Exception e) {
            e.printStackTrace();
            inputLine = "";
        }

        return inputLine;
    }
}
