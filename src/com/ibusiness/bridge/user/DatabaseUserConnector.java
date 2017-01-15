package com.ibusiness.bridge.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

/**
 * 用户数据连接器
 * 
 * @author JiangBo
 * 
 */
public class DatabaseUserConnector implements UserConnector {
    private static Logger logger = LoggerFactory.getLogger(DatabaseUserConnector.class);
    private JdbcTemplate jdbcTemplate;
    private Map<String, String> aliasMap = new HashMap<String, String>();

    // 用户表-通过ID查询
    private String sqlFindById = "select id as id,username as username,status as status,"
            + "display_name as display_name,email as email,mobile as mobile,user_repo_id as user_repo_ref"
            + " from IB_USER_BASE where id=?";
    // 用户表-通过帐号, 用户库列表ID查询
    private String sqlFindByUsername = "select ub.id as id,ub.username as username,ub.status as status,"
            + "display_name as display_name,email as email,mobile as mobile,user_repo_id as user_repo_ref"
            + " from IB_USER_BASE ub where ub.username=? and ub.scope_id=?";
    // 用户表-通过 引用, 用户库列表ID查询
    private String sqlFindByRef = "select ub.id as id,ub.username as username,ub.status as status,"
            + "display_name as display_name,email as email,mobile as mobile,user_repo_id as user_repo_ref"
            + " from IB_USER_BASE ub where ub.ref=? and ub.scope_id=?";
    // 用户表-总行数
    private String sqlPagedQueryCount = "select count(*) from IB_USER_BASE";
    // 用户表-全部数据
    private String sqlPagedQuerySelect = "select id as id,username as username,status as status,"
            + "display_name as display_name,email as email,mobile as mobile,user_repo_id as user_repo_ref"
            + " from IB_USER_BASE";

    public UserDTO findById(String id) {
        Assert.notNull(id, "user id should not be null");

        try {
            Map<String, Object> map = jdbcTemplate.queryForMap(sqlFindById, id);

            return convertUserDTO(map);
        } catch (EmptyResultDataAccessException ex) {
            logger.debug(ex.getMessage(), ex);
            logger.info("user[{}] is not exists.", id);

            return null;
        }
    }

    /**
     * 通过用户登录名查询
     */
    public UserDTO findByUsername(String username, String scopeId) {
        try {
            Map<String, Object> map = jdbcTemplate.queryForMap(sqlFindByUsername, username, scopeId);

            return convertUserDTO(map);
        } catch (EmptyResultDataAccessException ex) {
            logger.debug(ex.getMessage(), ex);
            logger.info("user[{}, {}] is not exists.", username, scopeId);

            return null;
        }
    }

    public UserDTO findByRef(String ref, String scopeId) {
        try {
            Map<String, Object> map = jdbcTemplate.queryForMap(sqlFindByRef, ref, scopeId);

            return convertUserDTO(map);
        } catch (EmptyResultDataAccessException ex) {
            logger.debug(ex.getMessage(), ex);
            logger.info("user[{}, {}] is not exists.", ref, scopeId);

            return null;
        }
    }

    protected UserDTO convertUserDTO(Map<String, Object> map) {
        if ((map == null) || map.isEmpty()) {
            logger.info("user[{}] is null.", map);

            return null;
        }

        logger.debug("{}", map);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(convertString(map.get("id")));
        userDTO.setUsername(convertString(map.get("username")));
        userDTO.setDisplayName(convertString(map.get("display_name")));
        userDTO.setEmail(convertString(map.get("email")));
        userDTO.setMobile(convertString(map.get("mobile")));
        userDTO.setUserRepoRef(convertString(map.get("user_repo_ref")));
        userDTO.setStatus(convertInt(map.get("status"), 1));

        return userDTO;
    }

    public String convertString(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            return (String) value;
        }

        return value.toString();
    }

    public Integer convertInt(Object value, Integer defaultValue) {
        if (value == null) {
            return defaultValue;
        }

        if (value instanceof Number) {
            return ((Number) value).intValue();
        }

        return Integer.parseInt(value.toString());
    }

    protected Map<String, Object> convertAlias(Map<String, Object> parameters) {
        logger.debug("parameters : {}", parameters);

        Map<String, Object> parameterMap = new HashMap<String, Object>();

        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            String key = entry.getKey();

            for (Map.Entry<String, String> aliasEntry : aliasMap.entrySet()) {
                String aliasKey = "_" + aliasEntry.getKey();
                String aliasValue = "_" + aliasEntry.getValue();

                if (key.indexOf(aliasKey) != -1) {
                    key = key.replace(aliasKey, aliasValue);

                    break;
                }
            }

            parameterMap.put(key, entry.getValue());
        }

        logger.debug("parameterMap : {}", parameterMap);

        return parameterMap;
    }

    @Resource
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setSqlFindById(String sqlFindById) {
        this.sqlFindById = sqlFindById;
    }

    public void setSqlFindByUsername(String sqlFindByUsername) {
        this.sqlFindByUsername = sqlFindByUsername;
    }

    public void setSqlFindByRef(String sqlFindByRef) {
        this.sqlFindByRef = sqlFindByRef;
    }

    public void setAliasMap(Map<String, String> aliasMap) {
        this.aliasMap = aliasMap;
    }

    public void setSqlPagedQuerySelect(String sqlPagedQuerySelect) {
        this.sqlPagedQuerySelect = sqlPagedQuerySelect;
    }

    public void setSqlPagedQueryCount(String sqlPagedQueryCount) {
        this.sqlPagedQueryCount = sqlPagedQueryCount;
    }
}
