package com.ibusiness.panorama.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @Title: Entity
 * @Description: 全景图管理表页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_PANORAMA")
public class PanoramaEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**scopeid*/
	private java.lang.String scopeid;
	/**id*/
	private java.lang.String id;
	/**name*/
	private java.lang.String name;
	/**panoramaaddress*/
	private java.lang.String panoramaaddress;
	/**panoramaurl*/
	private java.lang.String panoramaurl;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  scopeid
	 */
	@Column(name ="SCOPEID",nullable=true,length=64)
	public java.lang.String getScopeid(){
		return this.scopeid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  scopeid
	 */
	public void setScopeid(java.lang.String scopeid){
		this.scopeid = scopeid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
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
	 *@return: java.lang.String  name
	 */
	@Column(name ="NAME",nullable=true,length=64)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  name
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  panoramaaddress
	 */
	@Column(name ="PANORAMAADDRESS",nullable=true,length=128)
	public java.lang.String getPanoramaaddress(){
		return this.panoramaaddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  panoramaaddress
	 */
	public void setPanoramaaddress(java.lang.String panoramaaddress){
		this.panoramaaddress = panoramaaddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  panoramaurl
	 */
	@Column(name ="PANORAMAURL",nullable=true,length=256)
	public java.lang.String getPanoramaurl(){
		return this.panoramaurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  panoramaurl
	 */
	public void setPanoramaurl(java.lang.String panoramaurl){
		this.panoramaurl = panoramaurl;
	}
}
