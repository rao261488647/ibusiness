package com.ibusiness.bridge.scope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ibusiness.security.api.scope.ScopeDTO;

/**
 * 应用管理数据连接器
 * 
 * @author JiangBo
 * 
 */
public class DatabaseScopeConnector implements ScopeConnector {
    private static Logger logger = LoggerFactory.getLogger(DatabaseScopeConnector.class);
    private JdbcTemplate jdbcTemplate;

    // 通过 ID 查询应用管理表
    private String sqlFindById = "select id as id,code as code,name as name,ref as ref,"
            + " shared as shared,user_repo_ref as userRepoRef,type as type" + " from IB_SCOPE_INFO where id=?";
    // 通过 编码 查询应用管理表
    private String sqlFindByCode = "select id as id,code as code,name as name,ref as ref,"
            + " shared as shared,user_repo_ref as userRepoRef,type as type" + " from IB_SCOPE_INFO where code=?";
    // 通过 引用 查询应用管理表
    private String sqlFindByRef = "select id as id,code as code,name as name,ref as ref,"
            + " shared as shared,user_repo_ref as userRepoRef,type as type" + " from IB_SCOPE_INFO where ref=?";
    // 查询应用管理表所有数据
    private String sqlFindAll = "select id as id,code as code,name as name,ref as ref,"
            + " shared as shared,user_repo_ref as userRepoRef,type as type" + " from IB_SCOPE_INFO";
    // 查询应用管理表 共享=1 的数据
    private String sqlFindSharedScopes = "select id as id,code as code,name as name,ref as ref,"
            + " shared as shared,user_repo_ref as userRepoRef,type as type" + " from IB_SCOPE_INFO where shared=1";

    public ScopeDTO findById(String id) {
        try {
            Map<String, Object> map = jdbcTemplate.queryForMap(sqlFindById, id);

            return convertScopeDto(map);
        } catch (EmptyResultDataAccessException ex) {
            logger.info("scope[{}] is not exists.", id, ex);

            return null;
        }
    }

    /**
     * 根据传入的应用编码查询，不合法的编码不与以加载--权限控制
     */
    public ScopeDTO findByCode(String code) {
        try {
            Map<String, Object> map = jdbcTemplate.queryForMap(sqlFindByCode, code);

            return convertScopeDto(map);
        } catch (EmptyResultDataAccessException ex) {
            logger.debug(ex.getMessage(), ex);
            logger.info("scope[{}] is not exists.", code);

            return null;
        }
    }

    public ScopeDTO findByRef(String ref) {
        try {
            Map<String, Object> map = jdbcTemplate.queryForMap(sqlFindByRef, ref);

            return convertScopeDto(map);
        } catch (EmptyResultDataAccessException ex) {
            logger.debug(ex.getMessage(), ex);
            logger.info("scope[{}] is not exists.", ref);

            return null;
        }
    }

    public List<ScopeDTO> findAll() {
        List<ScopeDTO> scopeDtos = new ArrayList<ScopeDTO>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlFindAll);

        for (Map<String, Object> map : list) {
            ScopeDTO scopeDto = convertScopeDto(map);
            scopeDtos.add(scopeDto);
        }

        return scopeDtos;
    }

    public List<ScopeDTO> findSharedScopes() {
        List<ScopeDTO> scopeDtos = new ArrayList<ScopeDTO>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlFindSharedScopes);

        for (Map<String, Object> map : list) {
            ScopeDTO scopeDto = convertScopeDto(map);
            scopeDtos.add(scopeDto);
        }

        return scopeDtos;
    }

    protected ScopeDTO convertScopeDto(Map<String, Object> map) {
        if ((map == null) || map.isEmpty()) {
            logger.info("scope[{}] is null.", map);

            return null;
        }

        logger.debug("{}", map);

        ScopeDTO scopeDto = new ScopeDTO();
        scopeDto.setId(this.getString(map.get("id")));
//        scopeDto.setCode(this.getString(map.get("code")));
        scopeDto.setName(this.getString(map.get("name")));
//        scopeDto.setUserRepoRef(this.getString(map.get("userRepoRef")));
        scopeDto.setShared(Integer.valueOf(1).equals(map.get("shared")));
        scopeDto.setType(this.getInt(map.get("type")));

        return scopeDto;
    }

    private String getString(Object value) {
        if (value == null) {
            return null;
        }

        return value.toString();
    }

    private int getInt(Object value) {
        String str = this.getString(value);

        if (str == null) {
            return 0;
        }

        try {
            return Integer.parseInt(str);
        } catch (Exception ex) {
            return 0;
        }
    }

    @Resource
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setSqlFindById(String sqlFindById) {
        this.sqlFindById = sqlFindById;
    }

    public void setSqlFindByCode(String sqlFindByCode) {
        this.sqlFindByCode = sqlFindByCode;
    }

    public void setSqlFindByRef(String sqlFindByRef) {
        this.sqlFindByRef = sqlFindByRef;
    }

    public void setSqlFindAll(String sqlFindAll) {
        this.sqlFindAll = sqlFindAll;
    }

    public void setSqlFindSharedScopes(String sqlFindSharedScopes) {
        this.sqlFindSharedScopes = sqlFindSharedScopes;
    }
}
