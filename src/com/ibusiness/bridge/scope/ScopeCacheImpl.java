package com.ibusiness.bridge.scope;


import com.ibusiness.core.ext.cache.Cache;
import com.ibusiness.core.ext.cache.CacheStrategy;
import com.ibusiness.security.api.scope.ScopeDTO;
/**
 * 应用管理缓存实现类 
 * 
 * @author JiangBo
 *
 */
public class ScopeCacheImpl implements ScopeCache {
    private CacheStrategy cacheStrategy;
    private Cache cache;

    public ScopeDTO findById(String id) {
        String key = "scopeInfoId:" + id;

        return cache.get(key);
    }

    public ScopeDTO findByRef(String ref) {
        String key = "scopeInfoRef:" + ref;

        return cache.get(key);
    }

    public ScopeDTO findByCode(String code) {
        String key = "scopeInfoCode:" + code;

        return cache.get(key);
    }

    public void updateScope(ScopeDTO scopeDto) {
        cache.set("scopeInfoId:" + scopeDto.getId(), scopeDto);
        cache.set("scopeInfoRef:" + scopeDto.getId(), scopeDto);
        cache.set("scopeInfoCode:" + scopeDto.getId(), scopeDto);
    }

    public void removeScope(ScopeDTO scopeDto) {
        cache.remove("scopeInfoId:" + scopeDto.getId());
        cache.remove("scopeInfoRef:" + scopeDto.getId());
        cache.remove("scopeInfoCode:" + scopeDto.getId());
    }

    public void setCacheStrategy(CacheStrategy cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
        this.cache = cacheStrategy.getCache("scope");
    }
}
