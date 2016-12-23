package com.codegenerate.customermgr.entity;

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
 * @Description: 租车预订信息页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_CAR_RENTAL")
public class Car_rentalEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**范围*/
	private java.lang.String scopeid;
	/**UUID主键*/
	private java.lang.String id;
	/**姓名*/
	private java.lang.String name;
	/**电话*/
	private java.lang.String telephone;
	/**预约类型*/
	private java.lang.String rentaltype;
	/**提交日期*/
	private java.util.Date submitdate;
	/**用车日期时间*/
	private java.util.Date cardate;
	/**行程*/
	private java.lang.String tripinfo;
	/**处理人*/
	private java.lang.String dealperson;
	/**处理结果*/
	private java.lang.String dealresult;
	/**处理时间*/
	private java.util.Date dealtime;
	/**租车平台*/
	private java.lang.String platform;
	/**合同期*/
	private java.util.Date termdate;
	/**预约上门签约时间*/
	private java.util.Date signdate;
	/**到场注册时间*/
	private java.util.Date regtime;
	
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
	 *@return: java.lang.String  姓名
	 */
	@Column(name ="NAME",nullable=true,length=16)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电话
	 */
	@Column(name ="TELEPHONE",nullable=true,length=16)
	public java.lang.String getTelephone(){
		return this.telephone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setTelephone(java.lang.String telephone){
		this.telephone = telephone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预约类型
	 */
	@Column(name ="RENTALTYPE",nullable=true,length=8)
	public java.lang.String getRentaltype(){
		return this.rentaltype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预约类型
	 */
	public void setRentaltype(java.lang.String rentaltype){
		this.rentaltype = rentaltype;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  提交日期
	 */
	@Column(name ="SUBMITDATE",nullable=true)
	public java.util.Date getSubmitdate(){
		return this.submitdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  提交日期
	 */
	public void setSubmitdate(java.util.Date submitdate){
		this.submitdate = submitdate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  用车日期时间
	 */
	@Column(name ="CARDATE",nullable=true)
	public java.util.Date getCardate(){
		return this.cardate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  用车日期时间
	 */
	public void setCardate(java.util.Date cardate){
		this.cardate = cardate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  行程
	 */
	@Column(name ="TRIPINFO",nullable=true,length=128)
	public java.lang.String getTripinfo(){
		return this.tripinfo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  行程
	 */
	public void setTripinfo(java.lang.String tripinfo){
		this.tripinfo = tripinfo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  处理人
	 */
	@Column(name ="DEALPERSON",nullable=true,length=16)
	public java.lang.String getDealperson(){
		return this.dealperson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  处理人
	 */
	public void setDealperson(java.lang.String dealperson){
		this.dealperson = dealperson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  处理结果
	 */
	@Column(name ="DEALRESULT",nullable=true,length=32)
	public java.lang.String getDealresult(){
		return this.dealresult;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  处理结果
	 */
	public void setDealresult(java.lang.String dealresult){
		this.dealresult = dealresult;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  处理时间
	 */
	@Column(name ="DEALTIME",nullable=true)
	public java.util.Date getDealtime(){
		return this.dealtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  处理时间
	 */
	public void setDealtime(java.util.Date dealtime){
		this.dealtime = dealtime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  租车平台
	 */
	@Column(name ="PLATFORM",nullable=true,length=64)
	public java.lang.String getPlatform(){
		return this.platform;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  租车平台
	 */
	public void setPlatform(java.lang.String platform){
		this.platform = platform;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  合同期
	 */
	@Column(name ="TERMDATE",nullable=true)
	public java.util.Date getTermdate(){
		return this.termdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  合同期
	 */
	public void setTermdate(java.util.Date termdate){
		this.termdate = termdate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  预约上门签约时间
	 */
	@Column(name ="SIGNDATE",nullable=true)
	public java.util.Date getSigndate(){
		return this.signdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  预约上门签约时间
	 */
	public void setSigndate(java.util.Date signdate){
		this.signdate = signdate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  到场注册时间
	 */
	@Column(name ="REGTIME",nullable=true)
	public java.util.Date getRegtime(){
		return this.regtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  到场注册时间
	 */
	public void setRegtime(java.util.Date regtime){
		this.regtime = regtime;
	}
}
