package com.ibusiness.security.client;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import org.springframework.util.Assert;
/**
 * 资源方法填充器
 * 资源访问权限类型为'URL'的数据的填充器
 * 
 * @author JiangBo
 *
 */
public class UrlResourcePopulator {
	private static Logger logger = LoggerFactory.getLogger(UrlResourcePopulator.class);

    public void execute(FilterSecurityInterceptor filterSecurityInterceptor,
            Map<String, String> resourceMap) {
        Assert.notNull(filterSecurityInterceptor);
        Assert.notNull(resourceMap);

        logger.info("refresh url resource");

        // 所请求资源所需要的权限
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = null;
        requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();

        for (Map.Entry<String, String> entry : resourceMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            requestMap.put(new AntPathRequestMatcher(key),
                    SecurityConfig.createListFromCommaDelimitedString(value));
        }

        // 加载资源与权限的对应关系
        FilterInvocationSecurityMetadataSource source = new DefaultFilterInvocationSecurityMetadataSource(
                requestMap);
        filterSecurityInterceptor.setSecurityMetadataSource(source);
    }
}
