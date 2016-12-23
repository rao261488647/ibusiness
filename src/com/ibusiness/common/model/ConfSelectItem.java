package com.ibusiness.common.model;
/**
 * 下拉列表 Key Value 通用bean
 * 
 * @author JiangBo
 *
 */
public class ConfSelectItem {
    // 下拉列表key
    private String key;
    // 下拉别表value值
    private String value;
    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }
    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }
    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
}
