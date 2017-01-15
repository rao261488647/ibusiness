package com.ibusiness.bridge.scope;

import java.util.List;

import com.ibusiness.security.api.scope.ScopeDTO;
/**
 * 应用连接器接口
 * 
 * @author JiangBo
 *
 */
public interface ScopeConnector {
    ScopeDTO findById(String id);

    ScopeDTO findByRef(String ref);

    ScopeDTO findByCode(String code);

    List<ScopeDTO> findAll();

    List<ScopeDTO> findSharedScopes();
}
