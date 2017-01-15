package com.ibusiness.bridge.scope;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

/**
 * 应用管理过滤器
 * 
 * @author JiangBo
 * 
 */
public class ScopeFilterFactoryBean implements FactoryBean {
    private static Logger logger = LoggerFactory.getLogger(ScopeFilterFactoryBean.class);
    private Filter scopeFilter;
    private String type = "prefix";
    private ScopeConnector scopeConnector;

    @PostConstruct
    public void afterPropertiesSet() {
        Assert.notNull(type, "type cannot be null");
        if ("mock".equals(type)) {
            processMock();
        } else if ("prefix".equals(type)) {
            processPrefix();
        } else if ("headerCode".equals(type)) {
            processHeaderCode();
        } else if ("headerRef".equals(type)) {
            processHeaderRef();
        } else {
            throw new IllegalArgumentException("unsupported type : " + type);
        }
    }

    public void processMock() {
        MockScopeFilter mockScopeFilter = new MockScopeFilter();
        mockScopeFilter.setScopeConnector(scopeConnector);
        scopeFilter = mockScopeFilter;
    }

    // 应用管理前缀过滤器
    public void processPrefix() {
        PrefixScopeFilter prefixScopeFilter = new PrefixScopeFilter();
        prefixScopeFilter.setScopeConnector(scopeConnector);
        scopeFilter = prefixScopeFilter;
    }

    /**
     * 应用编码过滤器
     */
    public void processHeaderCode() {
        HeaderCodeScopeFilter headerCodeScopeFilter = new HeaderCodeScopeFilter();
        headerCodeScopeFilter.setScopeConnector(scopeConnector);
        scopeFilter = headerCodeScopeFilter;
    }

    /**
     * 应用 引用 过滤器
     */
    public void processHeaderRef() {
        HeaderRefScopeFilter headerRefScopeFilter = new HeaderRefScopeFilter();
        headerRefScopeFilter.setScopeConnector(scopeConnector);
        scopeFilter = headerRefScopeFilter;
    }

    public Object getObject() {
        return scopeFilter;
    }

    public Class getObjectType() {
        return Filter.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setScopeConnector(ScopeConnector scopeConnector) {
        this.scopeConnector = scopeConnector;
    }
}
