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
 * @Description: 请假管理页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_LEAVEMNG")
public class LeavemngEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**司机姓名*/
	private java.lang.String drivername;
	/**联系方式*/
	private java.lang.String driverphone;
	/**请假天数*/
	private java.lang.String dayss;
	/**开始日期*/
	private java.util.Date begindate;
	/**结束日期*/
	private java.util.Date enddate;
	/**车辆是否回收*/
	private java.lang.String carflag;
	/**UUID主键*/
	private java.lang.String id;
	/**范围*/
	private java.lang.String scopeid;
	
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
	 *@return: java.lang.String  请假天数
	 */
	@Column(name ="DAYSS",nullable=true,length=8)
	public java.lang.String getDayss(){
		return this.dayss;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请假天数
	 */
	public void setDayss(java.lang.String dayss){
		this.dayss = dayss;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始日期
	 */
	@Column(name ="BEGINDATE",nullable=true)
	public java.util.Date getBegindate(){
		return this.begindate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始日期
	 */
	public void setBegindate(java.util.Date begindate){
		this.begindate = begindate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束日期
	 */
	@Column(name ="ENDDATE",nullable=true)
	public java.util.Date getEnddate(){
		return this.enddate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束日期
	 */
	public void setEnddate(java.util.Date enddate){
		this.enddate = enddate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车辆是否回收
	 */
	@Column(name ="CARFLAG",nullable=true,length=8)
	public java.lang.String getCarflag(){
		return this.carflag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车辆是否回收
	 */
	public void setCarflag(java.lang.String carflag){
		this.carflag = carflag;
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
}
