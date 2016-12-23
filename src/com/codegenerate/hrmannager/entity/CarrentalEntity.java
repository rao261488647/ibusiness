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
 * @Description: 纯组页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_CARRENTAL")
public class CarrentalEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**范围*/
	private java.lang.String scopeid;
	/**UUID主键*/
	private java.lang.String id;
	/**司机姓名*/
	private java.lang.String drivername;
	/**车型*/
	private java.lang.String carname;
	/**车牌*/
	private java.lang.String carnum;
	/**固定值*/
	private java.lang.String fixedvalue;
	/**提车日期*/
	private java.util.Date eventdate;
	/**初始公里数*/
	private java.lang.String initialkm;
	/**月末公里数*/
	private java.lang.String endmonthkm;
	/**超出公里数*/
	private java.lang.String thankm;
	/**扣款金额*/
	private java.lang.String amount;
	/**方案*/
	private java.lang.String optionplan;
	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车牌
	 */
	@Column(name ="CARNUM",nullable=true,length=16)
	public java.lang.String getCarnum(){
		return this.carnum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车牌
	 */
	public void setCarnum(java.lang.String carnum){
		this.carnum = carnum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  固定值
	 */
	@Column(name ="FIXEDVALUE",nullable=true,length=16)
	public java.lang.String getFixedvalue(){
		return this.fixedvalue;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  固定值
	 */
	public void setFixedvalue(java.lang.String fixedvalue){
		this.fixedvalue = fixedvalue;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  提车日期
	 */
	@Column(name ="EVENTDATE",nullable=true)
	public java.util.Date getEventdate(){
		return this.eventdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  提车日期
	 */
	public void setEventdate(java.util.Date eventdate){
		this.eventdate = eventdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  初始公里数
	 */
	@Column(name ="INITIALKM",nullable=true,length=16)
	public java.lang.String getInitialkm(){
		return this.initialkm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  初始公里数
	 */
	public void setInitialkm(java.lang.String initialkm){
		this.initialkm = initialkm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  月末公里数
	 */
	@Column(name ="ENDMONTHKM",nullable=true,length=16)
	public java.lang.String getEndmonthkm(){
		return this.endmonthkm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  月末公里数
	 */
	public void setEndmonthkm(java.lang.String endmonthkm){
		this.endmonthkm = endmonthkm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  超出公里数
	 */
	@Column(name ="THANKM",nullable=true,length=16)
	public java.lang.String getThankm(){
		return this.thankm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  超出公里数
	 */
	public void setThankm(java.lang.String thankm){
		this.thankm = thankm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扣款金额
	 */
	@Column(name ="AMOUNT",nullable=true,length=16)
	public java.lang.String getAmount(){
		return this.amount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扣款金额
	 */
	public void setAmount(java.lang.String amount){
		this.amount = amount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  方案
	 */
	@Column(name ="OPTIONPLAN",nullable=true,length=16)
	public java.lang.String getOptionplan(){
		return this.optionplan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  方案
	 */
	public void setOptionplan(java.lang.String optionplan){
		this.optionplan = optionplan;
	}
}
