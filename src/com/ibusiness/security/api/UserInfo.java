package com.ibusiness.security.api;

import java.util.List;
import java.util.Map;
/**
 * 用户信息接口
 * 
 * @author JiangBo
 *
 */
public interface UserInfo {
    // 编号
    String getId();
    // 帐号
    String getUsername();
    // 显示名
    String getDisplayName();
    // 密码
    String getPassword();
    // 范围
    String getScopeId();
    // 权限
    List<String> getAuthorities();
    // 属性
    List<String> getAttributes();

    Map<String, Object> getExtra();
}
