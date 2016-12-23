package com.ibusiness.core.ext.cache;
/**
 * cache接口
 * 
 * @author JiangBo
 *
 */
public interface Cache {
    <T> T get(String key);

    void set(String key, Object value);

    void remove(String key);
}
