package com.ibusiness.security.api;

import java.util.Map;
/**
 * 获取资源信息接口类
 * DatabaseMethodSourceFetcher实现类的接口类
 * 
 * @author JiangBo
 *
 */
public interface MethodSourceFetcher {
    Map<String, String> getSource(String type);
}
