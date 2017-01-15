package com.ibusiness.bridge.scope;

import com.ibusiness.security.api.scope.ScopeDTO;

/**
 * 应用管理缓存接口
 * 
 * @author JiangBo
 *
 */
public interface ScopeCache {
    ScopeDTO findById(String id);

    ScopeDTO findByRef(String ref);

    ScopeDTO findByCode(String code);

    void updateScope(ScopeDTO scopeDto);

    void removeScope(ScopeDTO scopeDto);
}
