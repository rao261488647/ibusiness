package com.ibusiness.bridge.scope;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibusiness.security.api.scope.ScopeDTO;
/**
 * 应用连接器实现类
 * 
 * @author JiangBo
 *
 */
public class MockScopeConnector implements ScopeConnector {
    private static Logger logger = LoggerFactory.getLogger(MockScopeConnector.class);
    private ScopeDTO scopeDto;

    public MockScopeConnector() {
        scopeDto = new ScopeDTO();
        scopeDto.setId("1");
//        scopeDto.setCode("default");
        scopeDto.setName("default单位");
//        scopeDto.setUserRepoRef("1");
        scopeDto.setShared(true);
        scopeDto.setType(0);
    }

    public ScopeDTO findById(String id) {
        return scopeDto;
    }

    public ScopeDTO findByCode(String code) {
        return scopeDto;
    }

    public ScopeDTO findByRef(String ref) {
        return scopeDto;
    }

    public List<ScopeDTO> findAll() {
        return Collections.singletonList(scopeDto);
    }

    public List<ScopeDTO> findSharedScopes() {
        return Collections.singletonList(scopeDto);
    }
}
