package com.webservice.weixin.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.webservice.weixin.util.Configure;
import com.webservice.weixin.util.Signature;

public class WxPayToApp {

	private String appid;

	private String partnerid;

	private String prepayid;

	private String packages;

	private String noncestr;

	private String timestamp;

	private String sign;

	public WxPayToApp(String appId, String partnerId, String prepayId,
			String packages, String noncestr, String timestamp) {

		// 微信分配的公众号ID（开通公众号之后可以获取到）
		setAppid(Configure.getAppid());
		
		setPartnerid(partnerId);
		
		setPrepayid(prepayId);
		
		setPackages(packages);
		
		setNoncestr(noncestr);
		
		setTimestamp(timestamp);

		// 根据API给的签名规则进行签名
		String sign = Signature.getSign(toMap());
		setSign(sign);// 把签名数据设置到Sign这个属性中
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object obj;
			try {
				obj = field.get(this);
				if (obj != null) {
					map.put(field.getName(), obj);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		map.put("package", map.get("packages"));
		map.remove("packages");
		
		return map;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPartnerid() {
		return partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
