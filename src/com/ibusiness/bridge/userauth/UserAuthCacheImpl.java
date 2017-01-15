package com.ibusiness.bridge.userauth;

import com.ibusiness.core.ext.cache.Cache;
import com.ibusiness.core.ext.cache.CacheStrategy;
import com.ibusiness.security.api.userauth.UserAuthDTO;
/**
 * 用户权限缓存实现类
 * 
 * @author JiangBo
 *
 */
public class UserAuthCacheImpl implements UserAuthCache {
    private CacheStrategy cacheStrategy;
    private Cache cache;

    public UserAuthDTO findByUsername(String username, String scopeId) {
        String key = "userAuthUsername:" + username + ":" + scopeId;

        return cache.get(key);
    }

    public UserAuthDTO findByRef(String ref, String scopeId) {
        String key = "userAuthRef:" + ref + ":" + scopeId;

        return cache.get(key);
    }

    public UserAuthDTO findById(String id, String scopeId) {
        String key = "userAuthId:" + id + ":" + scopeId;

        return cache.get(key);
    }

    public void updateUserAuth(UserAuthDTO userAuthDto) {
        cache.set(
                "userAuthUsername:" + userAuthDto.getId() + ":"
                        + userAuthDto.getScopeId(), userAuthDto);
        cache.set(
                "userAuthRef:" + userAuthDto.getRef() + ":"
                        + userAuthDto.getScopeId(), userAuthDto);
        cache.set(
                "userAuthId:" + userAuthDto.getId() + ":"
                        + userAuthDto.getScopeId(), userAuthDto);
    }

    public void removeUserAuth(UserAuthDTO userAuthDto) {
        cache.remove("userAuthUsername:" + userAuthDto.getId() + ":"
                + userAuthDto.getScopeId());
        cache.remove("userAuthRef:" + userAuthDto.getRef() + ":"
                + userAuthDto.getScopeId());
        cache.remove("userAuthId:" + userAuthDto.getId() + ":"
                + userAuthDto.getScopeId());
    }

    public void setCacheStrategy(CacheStrategy cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
        this.cache = cacheStrategy.getCache("userauth");
    }
}
