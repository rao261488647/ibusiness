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
 * @Description: 违章管理页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_ILLEGAL")
public class IllegalEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**范围*/
	private java.lang.String scopeid;
	/**UUID主键*/
	private java.lang.String id;
	/**车牌号码*/
	private java.lang.String carnum;
	/**使用人*/
	private java.lang.String drivername;
	/**违章时间*/
	private java.util.Date eventdate;
	/**罚款金额*/
	private java.lang.String amount;
	/**处理方式*/
	private java.lang.String procestype;
	/**费用方式*/
	private java.lang.String amounttype;
	/**是否扣分*/
	private java.lang.String ispoints;
	/**扣分*/
	private java.lang.String points;
	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  使用人
	 */
	@Column(name ="DRIVERNAME",nullable=true,length=8)
	public java.lang.String getDrivername(){
		return this.drivername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  使用人
	 */
	public void setDrivername(java.lang.String drivername){
		this.drivername = drivername;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  违章时间
	 */
	@Column(name ="EVENTDATE",nullable=true)
	public java.util.Date getEventdate(){
		return this.eventdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  违章时间
	 */
	public void setEventdate(java.util.Date eventdate){
		this.eventdate = eventdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  罚款金额
	 */
	@Column(name ="AMOUNT",nullable=true,length=16)
	public java.lang.String getAmount(){
		return this.amount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  罚款金额
	 */
	public void setAmount(java.lang.String amount){
		this.amount = amount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  处理方式
	 */
	@Column(name ="PROCESTYPE",nullable=true,length=8)
	public java.lang.String getProcestype(){
		return this.procestype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  处理方式
	 */
	public void setProcestype(java.lang.String procestype){
		this.procestype = procestype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  费用方式
	 */
	@Column(name ="AMOUNTTYPE",nullable=true,length=8)
	public java.lang.String getAmounttype(){
		return this.amounttype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  费用方式
	 */
	public void setAmounttype(java.lang.String amounttype){
		this.amounttype = amounttype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否扣分
	 */
	@Column(name ="ISPOINTS",nullable=true,length=8)
	public java.lang.String getIspoints(){
		return this.ispoints;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否扣分
	 */
	public void setIspoints(java.lang.String ispoints){
		this.ispoints = ispoints;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扣分
	 */
	@Column(name ="POINTS",nullable=true,length=8)
	public java.lang.String getPoints(){
		return this.points;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扣分
	 */
	public void setPoints(java.lang.String points){
		this.points = points;
	}
}
