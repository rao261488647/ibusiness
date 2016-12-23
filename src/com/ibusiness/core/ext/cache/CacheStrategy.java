package com.ibusiness.core.ext.cache;
/**
 * cache策略接口
 * @author JiangBo
 *
 */
public interface CacheStrategy {
    Cache getCache(String name);
}
