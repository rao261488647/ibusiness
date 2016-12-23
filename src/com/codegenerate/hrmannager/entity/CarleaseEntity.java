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
 * @Description: 以租代购页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_CARLEASE")
public class CarleaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**范围*/
	private java.lang.String scopeid;
	/**UUID主键*/
	private java.lang.String id;
	/**司机姓名*/
	private java.lang.String drivername;
	/**车型*/
	private java.lang.String carname;
	/**签约日期*/
	private java.util.Date startday;
	/**首付金额*/
	private java.lang.String paymentamount;
	/**签约期数*/
	private java.lang.String contractterm;
	/**第一次月供*/
	private java.lang.String firstmonth;
	/**第二次月供*/
	private java.lang.String secondmonth;
	
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
	 *@return: java.lang.String  车型
	 */
	@Column(name ="CARNAME",nullable=true,length=32)
	public java.lang.String getCarname(){
		return this.carname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车型
	 */
	public void setCarname(java.lang.String carname){
		this.carname = carname;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  签约日期
	 */
	@Column(name ="STARTDAY",nullable=true)
	public java.util.Date getStartday(){
		return this.startday;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  签约日期
	 */
	public void setStartday(java.util.Date startday){
		this.startday = startday;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  首付金额
	 */
	@Column(name ="PAYMENTAMOUNT",nullable=true,length=16)
	public java.lang.String getPaymentamount(){
		return this.paymentamount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  首付金额
	 */
	public void setPaymentamount(java.lang.String paymentamount){
		this.paymentamount = paymentamount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  签约期数
	 */
	@Column(name ="CONTRACTTERM",nullable=true,length=16)
	public java.lang.String getContractterm(){
		return this.contractterm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  签约期数
	 */
	public void setContractterm(java.lang.String contractterm){
		this.contractterm = contractterm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  第一次月供
	 */
	@Column(name ="FIRSTMONTH",nullable=true,length=16)
	public java.lang.String getFirstmonth(){
		return this.firstmonth;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  第一次月供
	 */
	public void setFirstmonth(java.lang.String firstmonth){
		this.firstmonth = firstmonth;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  第二次月供
	 */
	@Column(name ="SECONDMONTH",nullable=true,length=16)
	public java.lang.String getSecondmonth(){
		return this.secondmonth;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  第二次月供
	 */
	public void setSecondmonth(java.lang.String secondmonth){
		this.secondmonth = secondmonth;
	}
}
