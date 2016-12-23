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
 * @Description: 电卡管理页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_ELECTRICITYCARD")
public class ElectricitycardEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**范围*/
	private java.lang.String scopeid;
	/**UUID主键*/
	private java.lang.String id;
	/**电卡卡号*/
	private java.lang.String electriccardno;
	/**领用日期*/
	private java.util.Date eventdate;
	/**初始金额*/
	private java.lang.String initialamount;
	/**充值金额*/
	private java.lang.String chargeamount;
	/**充值日期*/
	private java.util.Date chargedate;
	/**退款金额*/
	private java.lang.String refundamount;
	/**退款日期*/
	private java.util.Date refunddate;
	/**电卡状态*/
	private java.lang.String electricstatus;
	/**使用人*/
	private java.lang.String drivername;
	/**卡面余额*/
	private java.lang.String cardbalance;
	
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
	 *@return: java.lang.String  电卡卡号
	 */
	@Column(name ="ELECTRICCARDNO",nullable=true,length=32)
	public java.lang.String getElectriccardno(){
		return this.electriccardno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电卡卡号
	 */
	public void setElectriccardno(java.lang.String electriccardno){
		this.electriccardno = electriccardno;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  领用日期
	 */
	@Column(name ="EVENTDATE",nullable=true)
	public java.util.Date getEventdate(){
		return this.eventdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  领用日期
	 */
	public void setEventdate(java.util.Date eventdate){
		this.eventdate = eventdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  初始金额
	 */
	@Column(name ="INITIALAMOUNT",nullable=true,length=16)
	public java.lang.String getInitialamount(){
		return this.initialamount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  初始金额
	 */
	public void setInitialamount(java.lang.String initialamount){
		this.initialamount = initialamount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  充值金额
	 */
	@Column(name ="CHARGEAMOUNT",nullable=true,length=16)
	public java.lang.String getChargeamount(){
		return this.chargeamount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  充值金额
	 */
	public void setChargeamount(java.lang.String chargeamount){
		this.chargeamount = chargeamount;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  充值日期
	 */
	@Column(name ="CHARGEDATE",nullable=true)
	public java.util.Date getChargedate(){
		return this.chargedate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  充值日期
	 */
	public void setChargedate(java.util.Date chargedate){
		this.chargedate = chargedate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  退款金额
	 */
	@Column(name ="REFUNDAMOUNT",nullable=true,length=16)
	public java.lang.String getRefundamount(){
		return this.refundamount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  退款金额
	 */
	public void setRefundamount(java.lang.String refundamount){
		this.refundamount = refundamount;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  退款日期
	 */
	@Column(name ="REFUNDDATE",nullable=true)
	public java.util.Date getRefunddate(){
		return this.refunddate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  退款日期
	 */
	public void setRefunddate(java.util.Date refunddate){
		this.refunddate = refunddate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电卡状态
	 */
	@Column(name ="ELECTRICSTATUS",nullable=true,length=8)
	public java.lang.String getElectricstatus(){
		return this.electricstatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电卡状态
	 */
	public void setElectricstatus(java.lang.String electricstatus){
		this.electricstatus = electricstatus;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  卡面余额
	 */
	@Column(name ="CARDBALANCE",nullable=true,length=16)
	public java.lang.String getCardbalance(){
		return this.cardbalance;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  卡面余额
	 */
	public void setCardbalance(java.lang.String cardbalance){
		this.cardbalance = cardbalance;
	}
}
