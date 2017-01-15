package com.ibusiness.bridge.user;

import com.ibusiness.core.ext.cache.Cache;
import com.ibusiness.core.ext.cache.CacheStrategy;
/**
 * 帐号/用户缓存接口实现类 
 * @author JiangBo
 *
 */
public class UserCacheImpl implements UserCache {
    private CacheStrategy cacheStrategy;
    private Cache cache;

    public UserDTO findById(String id) {
        String key = "userId:" + id;

        return cache.get(key);
    }

    public UserDTO findByUsername(String username, String scopeId) {
        String key = "userUsername:" + username + ":" + scopeId;

        return cache.get(key);
    }

    public UserDTO findByRef(String ref, String scopeId) {
        String key = "userRef:" + ref + ":" + scopeId;

        return cache.get(key);
    }

    public void updateUser(UserDTO userDto) {
        cache.set("userId:" + userDto.getId(), userDto);
        cache.set(
                "userUsername:" + userDto.getUsername() + ":"
                        + userDto.getUserRepoRef(), userDto);
        cache.set(
                "userRef:" + userDto.getRef() + ":" + userDto.getUserRepoRef(),
                userDto);
    }

    public void removeUser(UserDTO userDto) {
        cache.remove("userId:" + userDto.getId());
        cache.remove("userUsername:" + userDto.getUsername() + ":"
                + userDto.getUserRepoRef());
        cache.remove("userRef:" + userDto.getRef() + ":"
                + userDto.getUserRepoRef());
    }

    public void setCacheStrategy(CacheStrategy cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
        this.cache = cacheStrategy.getCache("user");
    }
}
