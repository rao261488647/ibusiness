package com.ibusiness.doc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 文件类型页面
 * 
 * @author JiangBo
 * 
 */
@Entity
@Table(name = "IB_DOCTYPE")
public class DoctypeEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
    //
    private String scopeid;
    //
    private String id;
    // 文件类型名
    private String typename;
    // 排序编号
    private Integer orderno;

    /**
     * 方法: 取得String
     * 
     * @return: String scopeid
     */
    @Column(name = "SCOPEID", nullable = true, length = 64)
    public String getScopeid() {
        return this.scopeid;
    }

    /**
     * 方法: 设置String
     * 
     * @param: String scopeid
     */
    public void setScopeid(String scopeid) {
        this.scopeid = scopeid;
    }

    /**
     * 方法: 取得String
     * 
     * @return: String id
     */

    @Id
    @Column(name = "ID", nullable = false, length = 64)
    public String getId() {
        return this.id;
    }

    /**
     * 方法: 设置String
     * 
     * @param: String id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 方法: 取得String
     * 
     * @return: String typename
     */
    @Column(name = "TYPENAME", nullable = true, length = 32)
    public String getTypename() {
        return this.typename;
    }

    /**
     * 方法: 设置String
     * 
     * @param: String typename
     */
    public void setTypename(String typename) {
        this.typename = typename;
    }

    /**
     * 方法: 取得Integer
     * 
     * @return: Integer orderno
     */
    @Column(name = "ORDERNO", nullable = true, precision = 10, scale = 0)
    public Integer getOrderno() {
        return this.orderno;
    }

    /**
     * 方法: 设置Integer
     * 
     * @param: Integer orderno
     */
    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }
}
