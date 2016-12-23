package com.ibusiness.component.portal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ConfServiceModuleEntity 业务模块组件管理表entity
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_CONF_COMPONENT")
public class ConfComponent implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
    /**id*/
    private java.lang.String id;
    /**packagename*/
    private java.lang.String packagename;
    /**modulename*/
    private java.lang.String modulename;
    /**parentid*/
    private java.lang.String parentid;
    /**subname*/
    private java.lang.String subname;
    /**typeid*/
    private java.lang.String typeid;
    
    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  id
     */
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name ="ID",nullable=false,length=64)
    public java.lang.String getId(){
        return this.id;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  id
     */
    public void setId(java.lang.String id){
        this.id = id;
    }
    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  packagename
     */
    @Column(name ="PACKAGENAME",nullable=true,length=128)
    public java.lang.String getPackagename(){
        return this.packagename;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  packagename
     */
    public void setPackagename(java.lang.String packagename){
        this.packagename = packagename;
    }
    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  modulename
     */
    @Column(name ="MODULENAME",nullable=true,length=128)
    public java.lang.String getModulename(){
        return this.modulename;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  modulename
     */
    public void setModulename(java.lang.String modulename){
        this.modulename = modulename;
    }
    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  typeid
     */
    @Column(name ="TYPEID",nullable=true,length=8)
    public java.lang.String getTypeid(){
        return this.typeid;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  typeid
     */
    public void setTypeid(java.lang.String typeid){
        this.typeid = typeid;
    }
    /**
     * @return the parentid
     */
    @Column(name ="PARENTID",nullable=true,length=64)
    public java.lang.String getParentid() {
        return parentid;
    }

    /**
     * @param parentid the parentid to set
     */
    public void setParentid(java.lang.String parentid) {
        this.parentid = parentid;
    }

    /**
     * @return the subname
     */
    @Column(name ="SUBNAME")
    public java.lang.String getSubname() {
        return subname;
    }

    /**
     * @param subname the subname to set
     */
    public void setSubname(java.lang.String subname) {
        this.subname = subname;
    }
}
