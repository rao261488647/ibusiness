package com.codegenerate.carmgr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 车型管理页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_CAR_TYPE_INFO")
public class Car_type_infoEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**车型名称*/
	private java.lang.String typename;
	/**动力类型*/
	private java.lang.String powertype;
	/**车辆类型*/
	private java.lang.String cartype;
	/**车辆箱数*/
	private java.lang.String carriagecount;
	/**变速箱*/
	private java.lang.String gearbox;
	/**座位数*/
	private java.lang.String seatcount;
	/**排量*/
	private java.lang.String swept;
	/**发动机功率*/
	private java.lang.String displayment;
	/**上市年份*/
	private java.lang.String listyear;
	/**UUID主键*/
	private java.lang.String id;
	/**范围*/
	private java.lang.String scopeid;
	/**图片URL*/
	private java.lang.String uploadurl;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车型名称
	 */
	@Column(name ="TYPENAME",nullable=true,length=32)
	public java.lang.String getTypename(){
		return this.typename;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车型名称
	 */
	public void setTypename(java.lang.String typename){
		this.typename = typename;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  动力类型
	 */
	@Column(name ="POWERTYPE",nullable=true,length=32)
	public java.lang.String getPowertype(){
		return this.powertype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  动力类型
	 */
	public void setPowertype(java.lang.String powertype){
		this.powertype = powertype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车辆类型
	 */
	@Column(name ="CARTYPE",nullable=true,length=16)
	public java.lang.String getCartype(){
		return this.cartype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车辆类型
	 */
	public void setCartype(java.lang.String cartype){
		this.cartype = cartype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车辆箱数
	 */
	@Column(name ="CARRIAGECOUNT",nullable=true,length=8)
	public java.lang.String getCarriagecount(){
		return this.carriagecount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车辆箱数
	 */
	public void setCarriagecount(java.lang.String carriagecount){
		this.carriagecount = carriagecount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  变速箱
	 */
	@Column(name ="GEARBOX",nullable=true,length=16)
	public java.lang.String getGearbox(){
		return this.gearbox;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  变速箱
	 */
	public void setGearbox(java.lang.String gearbox){
		this.gearbox = gearbox;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  座位数
	 */
	@Column(name ="SEATCOUNT",nullable=true,length=8)
	public java.lang.String getSeatcount(){
		return this.seatcount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  座位数
	 */
	public void setSeatcount(java.lang.String seatcount){
		this.seatcount = seatcount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  排量
	 */
	@Column(name ="SWEPT",nullable=true,length=8)
	public java.lang.String getSwept(){
		return this.swept;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  排量
	 */
	public void setSwept(java.lang.String swept){
		this.swept = swept;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发动机功率
	 */
	@Column(name ="DISPLAYMENT",nullable=true,length=16)
	public java.lang.String getDisplayment(){
		return this.displayment;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发动机功率
	 */
	public void setDisplayment(java.lang.String displayment){
		this.displayment = displayment;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上市年份
	 */
	@Column(name ="LISTYEAR",nullable=true,length=8)
	public java.lang.String getListyear(){
		return this.listyear;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上市年份
	 */
	public void setListyear(java.lang.String listyear){
		this.listyear = listyear;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  UUID主键
	 */
	
	@Id
	@Column(name ="ID",nullable=false,length=64)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  UUID主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  范围
	 */
	@Column(name ="SCOPEID",nullable=true,length=64)
	public java.lang.String getScopeid(){
		return this.scopeid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  范围
	 */
	public void setScopeid(java.lang.String scopeid){
		this.scopeid = scopeid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片URL
	 */
	@Column(name ="UPLOADURL",nullable=true,length=128)
	public java.lang.String getUploadurl(){
		return this.uploadurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片URL
	 */
	public void setUploadurl(java.lang.String uploadurl){
		this.uploadurl = uploadurl;
	}
}
