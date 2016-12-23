package com.webservice.weixin.model;

public class WxOauth2Token {

	private String accessToken;
	private int expiresln;
	private String refeshToken;
	private String openId;
	private String scope;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresln() {
		return expiresln;
	}
	public void setExpiresln(int expiresln) {
		this.expiresln = expiresln;
	}
	public String getRefeshToken() {
		return refeshToken;
	}
	public void setRefeshToken(String refeshToken) {
		this.refeshToken = refeshToken;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
}
