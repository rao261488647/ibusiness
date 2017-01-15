package com.ibusiness.security.client;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.method.DelegatingMethodSecurityMetadataSource;
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.util.Assert;

import com.ibusiness.core.util.BeanUtils;
/**
 * 资源方法填充器
 * 资源访问权限类型为'METHOD'的数据的填充器
 * 
 * @author JiangBo
 *
 */
public class MethodResourcePopulator {
    private static Logger logger = LoggerFactory.getLogger(MethodResourcePopulator.class);

    /**
     * 登录DelegatingMethodSecurityMetadataSource,
     * 使用安全cookie设置为false并不实际防止cookie被标记为安全的,删除使用过时的方法在名称空间解析代码
     * 
     * @param delegatingMethodSecurityMetadataSource
     * @param resourceMap
     */
    public void execute(DelegatingMethodSecurityMetadataSource delegatingMethodSecurityMetadataSource, Map<String, String> resourceMap) {
        Assert.notNull(delegatingMethodSecurityMetadataSource);
        Assert.notNull(resourceMap);

        logger.info("refresh method resource");

        Map<String, List<ConfigAttribute>> methodMap = null;
        methodMap = new LinkedHashMap<String, List<ConfigAttribute>>();

        for (Map.Entry<String, String> entry : resourceMap.entrySet()) {
            methodMap.put(entry.getKey(), SecurityConfig.createListFromCommaDelimitedString(entry.getValue()));
        }

        MethodSecurityMetadataSource source = new MapBasedMethodSecurityMetadataSource(methodMap);
        List<MethodSecurityMetadataSource> sources = new ArrayList<MethodSecurityMetadataSource>();
        sources.add(source);

        List<MethodSecurityMetadataSource> methodSecurityMetadataSources = delegatingMethodSecurityMetadataSource
                .getMethodSecurityMetadataSources();
        methodSecurityMetadataSources.clear();
        methodSecurityMetadataSources.addAll(sources);

        Map attributeCache = (Map) BeanUtils.safeGetFieldValue(delegatingMethodSecurityMetadataSource, "attributeCache");
        attributeCache.clear();
    }
}
