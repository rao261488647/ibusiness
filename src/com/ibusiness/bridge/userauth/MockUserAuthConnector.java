package com.ibusiness.bridge.userauth;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ibusiness.bridge.scope.ScopeConnector;
import com.ibusiness.bridge.user.UserConnector;
import com.ibusiness.security.api.userauth.UserAuthConnector;
import com.ibusiness.security.api.userauth.UserAuthDTO;

/**
 * 用户权限连接器实现类
 * 
 * @author JiangBo
 * 
 */
public class MockUserAuthConnector implements UserAuthConnector {
    private static Logger logger = LoggerFactory.getLogger(MockUserAuthConnector.class);
    private JdbcTemplate jdbcTemplate;
    private ScopeConnector scopeConnector;
    private UserConnector userConnector;
    private UserAuthDTO userAuthDto;

    public MockUserAuthConnector() {
        userAuthDto = new UserAuthDTO();
        userAuthDto.setId("1");
        userAuthDto.setScopeId("1");
        userAuthDto.setUsername("JiangBo");
        userAuthDto.setRef("1");
        userAuthDto.setDisplayName("JiangBo");
        userAuthDto.setStatus("1");
        userAuthDto.setPassword("1");

        userAuthDto.setPermissions(Collections.singletonList("*"));
        userAuthDto.setRoles(Collections.singletonList("ROLE_ADMIN"));
    }

    public UserAuthDTO findByUsername(String username, String scopeId) {
        return userAuthDto;
    }

    public UserAuthDTO findByRef(String ref, String scopeId) {
        return userAuthDto;
    }

    public UserAuthDTO findById(String id, String scopeId) {
        return userAuthDto;
    }
}
