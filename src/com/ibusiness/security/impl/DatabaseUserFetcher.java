package com.ibusiness.security.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ibusiness.security.api.UserFetcher;
import com.ibusiness.security.api.UserInfo;
import com.ibusiness.security.api.scope.ScopeHolder;
/**
 * 取得用户URL访问权限
 * 
 * @author JiangBo
 *
 */
public class DatabaseUserFetcher implements UserFetcher {
    private static Logger logger = LoggerFactory
            .getLogger(DatabaseUserFetcher.class);
    private String defaultUserRepoRef;
    private JdbcTemplate jdbcTemplate;

    public UserInfo getUserInfo(String username) {
        return getUserInfo(username, ScopeHolder.getScopeId());
    }

    public UserInfo getUserInfo(String username, String scopeId) {
        logger.debug("username : {}", username);
        logger.debug("scopeId : {}", scopeId);

        String processedUsername = null;

        if (username != null) {
            processedUsername = username.toLowerCase();
        }

        Map<String, Object> userMap = this.fetchUserMap(processedUsername, scopeId);
        List<Map<String, Object>> authorityList = this.fetchAuthoritieList(processedUsername, scopeId);

        List<Map<String, Object>> attributeList = this.fetchAttributeList(processedUsername, scopeId);

        List<String> authorities = new ArrayList<String>();

        for (Map<String, Object> item : authorityList) {
            authorities.add((String) item.get("authority"));
        }

        List<String> attributes = new ArrayList<String>();

        for (Map<String, Object> item : attributeList) {
            attributes.add("ROLE_" + (String) item.get("attribute"));
        }

        logger.debug("userMap : {}", userMap);
        logger.debug("authorities : {}", authorities);
        logger.debug("attributes : {}", attributes);

        UserInfoImpl userInfo = new UserInfoImpl();
        userInfo.setId(userMap.get("id").toString());
        userInfo.setUsername(processedUsername);
        userInfo.setDisplayName((String) userMap.get("display_name"));
        userInfo.setPassword((String) userMap.get("password"));
        userInfo.setScopeId(scopeId);
        userInfo.setAuthorities(authorities);
        userInfo.setAttributes(attributes);

        return userInfo;
    }

    /**
     * 
     * @param username
     * @param scopeId
     * @return
     */
    public Map<String, Object> fetchUserMap(String username, String scopeId) {
        String sqlUser = "select id,username,password,status,display_name from IB_USER_BASE"
                + " where username=? and scope_id=?";

        try {
            Map<String, Object> userMap = null;

            userMap = jdbcTemplate.queryForMap(sqlUser, username, scopeId);

            return userMap;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new UsernameNotFoundException(username, ex);
        }
    }

    /**
     * 权限列表
     * @param username
     * @param userRepoRef
     * @param scopeId
     * @return
     */
    public List<Map<String, Object>> fetchAuthoritieList(String username, String scopeId) {
        String sqlAuthority = "SELECT p.code AS authority FROM IB_USER_BASE us,IB_AUTH_ROLE_DEF r,IB_AUTH_PERM_ROLE_DEF pr,IB_AUTH_PERM p "
                + " WHERE us.role_def_id=r.id AND r.id=pr.role_def_id AND pr.perm_id=p.id"
                + " and username=? and us.scope_id=? and r.scope_id=?";

        List<Map<String, Object>> authorityList = null;

        try {
            authorityList = jdbcTemplate.queryForList(sqlAuthority, username, scopeId, scopeId);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            authorityList = new ArrayList<Map<String, Object>>();
        }

        return authorityList;
    }

    /**
     * 角色列表
     * 
     * @param username
     * @param userRepoRef
     * @param scopeId
     * @return
     */
    public List<Map<String, Object>> fetchAttributeList(String username, String scopeId) {
        List<Map<String, Object>> attributeList = null;

        try {
            String sqlAttribute = "SELECT r.name AS attribute FROM IB_USER_BASE us,IB_AUTH_ROLE_DEF r  WHERE us.role_def_id=r.id"
                    + " and username=? and us.scope_id=? and r.scope_id=?";
            attributeList = jdbcTemplate.queryForList(sqlAttribute, username, scopeId, scopeId);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            attributeList = new ArrayList<Map<String, Object>>();
        }

        return attributeList;
    }

    public void setDefaultUserRepoRef(String defaultUserRepoRef) {
        this.defaultUserRepoRef = defaultUserRepoRef;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
