package com.ibusiness.core.ext.cache;

/**
 * 远程cache策略
 * 
 * @author JiangBo
 *
 */
public class RemoteCacheStrategy implements CacheStrategy {
    private Cache cache = new MapCache();

    public Cache getCache(String name) {
        return cache;
    }
}
