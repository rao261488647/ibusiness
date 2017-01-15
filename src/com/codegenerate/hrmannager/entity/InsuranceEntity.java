package com.codegenerate.hrmannager.entity;

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
 * @Description: 出险管理页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_INSURANCE")
public class InsuranceEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**司机姓名*/
	private java.lang.String drivername;
	/**联系方式*/
	private java.lang.String driverphone;
	/**车型名称*/
	private java.lang.String carname;
	/**车牌号码*/
	private java.lang.String carnum;
	/**出险日期*/
	private java.util.Date eventdate;
	/**责任*/
	private java.lang.String responsibility;
	/**停运天数*/
	private java.lang.String mainttime;
	/**替换车使用*/
	private java.lang.String carflag;
	/**替换车型*/
	private java.lang.String newcarname;
	/**替换车牌*/
	private java.lang.String newcarnum;
	/**UUID主键*/
	private java.lang.String id;
	/**范围*/
	private java.lang.String scopeid;
	/**出险金额*/
	private java.lang.Double insuranceamount;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司机姓名
	 */
	@Column(name ="DRIVERNAME",nullable=true,length=8)
	public java.lang.String getDrivername(){
		return this.drivername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  司机姓名
	 */
	public void setDrivername(java.lang.String drivername){
		this.drivername = drivername;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系方式
	 */
	@Column(name ="DRIVERPHONE",nullable=true,length=16)
	public java.lang.String getDriverphone(){
		return this.driverphone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系方式
	 */
	public void setDriverphone(java.lang.String driverphone){
		this.driverphone = driverphone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车型名称
	 */
	@Column(name ="CARNAME",nullable=true,length=32)
	public java.lang.String getCarname(){
		return this.carname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车型名称
	 */
	public void setCarname(java.lang.String carname){
		this.carname = carname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车牌号码
	 */
	@Column(name ="CARNUM",nullable=true,length=16)
	public java.lang.String getCarnum(){
		return this.carnum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车牌号码
	 */
	public void setCarnum(java.lang.String carnum){
		this.carnum = carnum;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  出险日期
	 */
	@Column(name ="EVENTDATE",nullable=true)
	public java.util.Date getEventdate(){
		return this.eventdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  出险日期
	 */
	public void setEventdate(java.util.Date eventdate){
		this.eventdate = eventdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  责任
	 */
	@Column(name ="RESPONSIBILITY",nullable=true,length=4)
	public java.lang.String getResponsibility(){
		return this.responsibility;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  责任
	 */
	public void setResponsibility(java.lang.String responsibility){
		this.responsibility = responsibility;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  停运天数
	 */
	@Column(name ="MAINTTIME",nullable=true,length=8)
	public java.lang.String getMainttime(){
		return this.mainttime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  停运天数
	 */
	public void setMainttime(java.lang.String mainttime){
		this.mainttime = mainttime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  替换车使用
	 */
	@Column(name ="CARFLAG",nullable=true,length=8)
	public java.lang.String getCarflag(){
		return this.carflag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  替换车使用
	 */
	public void setCarflag(java.lang.String carflag){
		this.carflag = carflag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  替换车型
	 */
	@Column(name ="NEWCARNAME",nullable=true,length=32)
	public java.lang.String getNewcarname(){
		return this.newcarname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  替换车型
	 */
	public void setNewcarname(java.lang.String newcarname){
		this.newcarname = newcarname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  替换车牌
	 */
	@Column(name ="NEWCARNUM",nullable=true,length=16)
	public java.lang.String getNewcarnum(){
		return this.newcarnum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  替换车牌
	 */
	public void setNewcarnum(java.lang.String newcarnum){
		this.newcarnum = newcarnum;
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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  出险金额
	 */
	@Column(name ="INSURANCEAMOUNT",nullable=true,precision=10,scale=2)
	public java.lang.Double getInsuranceamount(){
		return this.insuranceamount;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  出险金额
	 */
	public void setInsuranceamount(java.lang.Double insuranceamount){
		this.insuranceamount = insuranceamount;
	}
}
