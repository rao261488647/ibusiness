package com.ibusiness.bridge.userauth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import com.ibusiness.bridge.scope.ScopeConnector;
import com.ibusiness.bridge.user.UserConnector;
import com.ibusiness.bridge.user.UserDTO;
import com.ibusiness.security.api.scope.ScopeDTO;
import com.ibusiness.security.api.userauth.UserAuthConnector;
import com.ibusiness.security.api.userauth.UserAuthDTO;
/**
 * 用户权限数据连接器
 * 
 * @author JiangBo
 *
 */
public class DatabaseUserAuthConnector implements UserAuthConnector {
    private JdbcTemplate jdbcTemplate;
    private ScopeConnector scopeConnector;
    private UserConnector userConnector;

    //
    private String sqlFindPassword = "select password from IB_USER_BASE where id=?";
    // 权限和角色和用户资源关联
    private String sqlFindPermissions = "select p.code as permission"
            + " from IB_USER_BASE us,IB_AUTH_ROLE_DEF r,IB_AUTH_PERM_ROLE_DEF pr,IB_AUTH_PERM p"
            + " where us.ROLE_DEF_ID=r.id and r.id=pr.role_def_id and pr.perm_id=p.id"
            + " and us.ref=? and us.scope_id=?";
    // 用户权限查询语句
    private String sqlFindRoles = "select r.name as role" + " from IB_USER_BASE ub, IB_AUTH_ROLE_DEF r"
            + " where ub.role_def_id=r.id" + " and ub.ref=? and ub.scope_id=?";

    public UserAuthDTO findByUsername(String username, String scopeId) {
        ScopeDTO scopeDto = scopeConnector.findById(scopeId);
        UserDTO userDto = userConnector.findByUsername(username, scopeId);
        Assert.notNull(userDto, "cannot find user by (" + username + "," + scopeId + ")");

        return process(userDto, scopeDto, true);
    }

    public UserAuthDTO findByRef(String ref, String scopeId) {
        ScopeDTO scopeDto = scopeConnector.findById(scopeId);
        UserDTO userDto = userConnector.findByRef(ref, scopeId);

        return process(userDto, scopeDto, false);
    }

    public UserAuthDTO findById(String id, String scopeId) {
        ScopeDTO scopeDto = scopeConnector.findById(scopeId);
        UserDTO userDto = userConnector.findById(id);

        return process(userDto, scopeDto, false);
    }

    public UserAuthDTO process(UserDTO userDto, ScopeDTO scopeDto, boolean needPassword) {
        UserAuthDTO userAuthDto = new UserAuthDTO();
        userAuthDto.setId(userDto.getId());
        userAuthDto.setScopeId(scopeDto.getId());
        userAuthDto.setUsername(userDto.getUsername());
        userAuthDto.setRef(userDto.getRef());
        userAuthDto.setDisplayName(userDto.getDisplayName());
        userAuthDto.setStatus(Integer.toString(userDto.getStatus()));

        // password
        if (needPassword) {
            String password = jdbcTemplate.queryForObject(sqlFindPassword, String.class, userDto.getId());
            userAuthDto.setPassword(password);
        }

        // permissions
        List<Map<String, Object>> permissions = jdbcTemplate.queryForList(sqlFindPermissions, userDto.getId(),
                scopeDto.getId());
        userAuthDto.setPermissions(this.convertMapListToStringList(permissions, "permission"));

        // roles
        List<Map<String, Object>> roles = jdbcTemplate.queryForList(sqlFindRoles, userDto.getId(), scopeDto.getId());
        userAuthDto.setRoles(this.convertMapListToStringList(roles, "role"));

        return userAuthDto;
    }

    public List<String> convertMapListToStringList(List<Map<String, Object>> mapList, String name) {
        List<String> stringList = new ArrayList<String>();

        for (Map<String, Object> map : mapList) {
            Object value = map.get(name);

            if (value != null) {
                stringList.add(value.toString());
            }
        }

        return stringList;
    }

    public void setScopeConnector(ScopeConnector scopeConnector) {
        this.scopeConnector = scopeConnector;
    }

    public void setUserConnector(UserConnector userConnector) {
        this.userConnector = userConnector;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setSqlFindPassword(String sqlFindPassword) {
        this.sqlFindPassword = sqlFindPassword;
    }

    public void setSqlFindPermission(String sqlFindPermissions) {
        this.sqlFindPermissions = sqlFindPermissions;
    }

    public void setSqlFindRole(String sqlFindRoles) {
        this.sqlFindRoles = sqlFindRoles;
    }
}
