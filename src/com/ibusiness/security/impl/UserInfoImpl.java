package com.ibusiness.security.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibusiness.security.api.UserInfo;

/**
 * 用户信息实体
 * 
 * @author JiangBo
 * 
 */
public class UserInfoImpl implements UserInfo {
    // 编号
    private String id;
    // 帐号
    private String username;
    // 显示名
    private String displayName;
    // 密码
    private String password;
    // 范围
    private String scopeId;
    // 权限
    private List<String> authorities;
    // 属性
    private List<String> attributes;
    // 
    private Map<String, Object> extra = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }

    public Object getExtraItem(String key) {
        return extra.get(key);
    }

    public void putExtraItem(String key, Object value) {
        extra.put(key, value);
    }
}
