package com.ibusiness.core.jdbc;
/**
 * 数据源信息接口
 * 
 * @author JiangBo
 *
 */
public interface DataSourceInfo {
    String getName();
    void setName(String name);
    void validate();
}
