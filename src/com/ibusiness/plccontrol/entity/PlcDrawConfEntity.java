package com.ibusiness.plccontrol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * PLC绘图页面 设备对象配置管理
 * 
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_CONF_PLCDRAW")
public class PlcDrawConfEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**id*/
	private java.lang.String id;
	/**remark*/
	private java.lang.String remark;
	/**itemid*/
	private java.lang.String itemid;
	/**itemname*/
	private java.lang.String itemname;
	/**itemtype*/
	private java.lang.String itemtype;
	/**itemtitle*/
	private java.lang.String itemtitle;
	/**imghurl*/
	private java.lang.String imghurl;
	/**imgvurl*/
	private java.lang.String imgvurl;
	
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
	 *@return: java.lang.String  remark
	 */
	@Column(name ="REMARK",nullable=true,length=1024)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  remark
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  itemid
	 */
	@Column(name ="ITEMID",nullable=true,length=64)
	public java.lang.String getItemid(){
		return this.itemid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  itemid
	 */
	public void setItemid(java.lang.String itemid){
		this.itemid = itemid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  itemname
	 */
	@Column(name ="ITEMNAME",nullable=true,length=64)
	public java.lang.String getItemname(){
		return this.itemname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  itemname
	 */
	public void setItemname(java.lang.String itemname){
		this.itemname = itemname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  itemtype
	 */
	@Column(name ="ITEMTYPE",nullable=true,length=32)
	public java.lang.String getItemtype(){
		return this.itemtype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  itemtype
	 */
	public void setItemtype(java.lang.String itemtype){
		this.itemtype = itemtype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  itemtitle
	 */
	@Column(name ="ITEMTITLE",nullable=true,length=32)
	public java.lang.String getItemtitle(){
		return this.itemtitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  itemtitle
	 */
	public void setItemtitle(java.lang.String itemtitle){
		this.itemtitle = itemtitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  imghurl
	 */
	@Column(name ="IMGHURL",nullable=true,length=64)
	public java.lang.String getImghurl(){
		return this.imghurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  imghurl
	 */
	public void setImghurl(java.lang.String imghurl){
		this.imghurl = imghurl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  imgvurl
	 */
	@Column(name ="IMGVURL",nullable=true,length=64)
	public java.lang.String getImgvurl(){
		return this.imgvurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  imgvurl
	 */
	public void setImgvurl(java.lang.String imgvurl){
		this.imgvurl = imgvurl;
	}
}
