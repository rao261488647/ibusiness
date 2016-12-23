package com.ibusiness.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @Title: Entity
 * @Description: 作品展示表页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_INDEXIMGBYWORKS")
public class IndeximgbyworksEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**scopeid*/
	private java.lang.String scopeid;
	/**id*/
	private java.lang.String id;
	// 图片1
	private java.lang.String imgurl;
	// 图片2
	private java.lang.String imgurl2;
	// 图片3
	private java.lang.String imgurl3;
	// 图片4
	private java.lang.String imgurl4;
	// 图片5
	private java.lang.String imgurl5;
	/**imginfo*/
	private java.lang.String imginfo;
	/**showflag*/
	private java.lang.String showflag;
	
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
	 *@return: java.lang.String  imgurl
	 */
	@Column(name ="IMGURL",nullable=true,length=256)
	public java.lang.String getImgurl(){
		return this.imgurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  imgurl
	 */
	public void setImgurl(java.lang.String imgurl){
		this.imgurl = imgurl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  imginfo
	 */
	@Column(name ="IMGINFO",nullable=true,length=128)
	public java.lang.String getImginfo(){
		return this.imginfo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  imginfo
	 */
	public void setImginfo(java.lang.String imginfo){
		this.imginfo = imginfo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  showflag
	 */
	@Column(name ="SHOWFLAG",nullable=true,length=8)
	public java.lang.String getShowflag(){
		return this.showflag;
	}
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  showflag
	 */
	public void setShowflag(java.lang.String showflag){
		this.showflag = showflag;
	}
	@Column(name ="IMGURL2")
	public java.lang.String getImgurl2() {
		return imgurl2;
	}
	public void setImgurl2(java.lang.String imgurl2) {
		this.imgurl2 = imgurl2;
	}
	@Column(name ="IMGURL3")
	public java.lang.String getImgurl3() {
		return imgurl3;
	}
	public void setImgurl3(java.lang.String imgurl3) {
		this.imgurl3 = imgurl3;
	}
	@Column(name ="IMGURL4")
	public java.lang.String getImgurl4() {
		return imgurl4;
	}
	public void setImgurl4(java.lang.String imgurl4) {
		this.imgurl4 = imgurl4;
	}
	@Column(name ="IMGURL5")
	public java.lang.String getImgurl5() {
		return imgurl5;
	}
	public void setImgurl5(java.lang.String imgurl5) {
		this.imgurl5 = imgurl5;
	}
}
