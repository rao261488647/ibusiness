package com.ibusiness.common.page;

import java.util.Date;

/**
 * property type.字段类型枚举类
 * 
 * @author JiangBo
 */
public enum PropertyType {
    /** String. */
    S(String.class),
    /** Integer. */
    I(Integer.class),
    /** Long. */
    L(Long.class),
    /** Double. */
    N(Double.class),
    /** Date. */
    D(Date.class),
    /** Boolean. */
    B(Boolean.class);
    /** class. */
    private Class<?> clazz;

    /**
     * constructor.
     * 
     * @param clazz
     *            Class
     */
    private PropertyType(Class<?> clazz) {
        this.clazz = clazz;
    }

    /** @return get value. */
    public Class<?> getValue() {
        return clazz;
    }
}
