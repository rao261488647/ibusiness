package com.ibusiness.security.api;

import java.util.Map;
/**
 * 从数据库取得AUTH_ACCESS表type类型为'URL'的数据接口类
 * 
 * @author JiangBo
 *
 */
public interface UrlSourceFetcher {
    Map<String, String> getSource(String type);
}
