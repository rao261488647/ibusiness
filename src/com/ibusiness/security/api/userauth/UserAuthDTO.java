package com.ibusiness.security.api.userauth;

import java.util.List;
/**
 * 用户权限DTO
 * 
 * @author JiangBo
 *
 */
public class UserAuthDTO {
    // 编号
    private String id;
    // 多租户id
    private String scopeId;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 引用排序
    private String ref;
    // 用户显示名
    private String displayName;
    // 状态
    private String status;
    // ���
    private List<String> permissions;
    // Ȩ��
    private List<String> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
