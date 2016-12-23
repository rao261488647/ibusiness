package com.codegenerate.financemgr.entity;

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
 * @Description: 司机流水页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_FLOW_DRIVER_FINANCIAL")
public class Flow_driver_financialEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**范围*/
	private java.lang.String scopeid;
	/**UUID主键*/
	private java.lang.String id;
	/**日期*/
	private java.util.Date eventdate;
	/**客户*/
	private java.lang.String customername;
	/**电话*/
	private java.lang.String telephone;
	/**交易编号*/
	private java.lang.String transactionno;
	/**平台*/
	private java.lang.String platform;
	/**金额*/
	private java.lang.String amount;
	/**车牌号*/
	private java.lang.String carnum;
	/**备注*/
	private java.lang.String remark;
	
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  日期
	 */
	@Column(name ="EVENTDATE",nullable=true)
	public java.util.Date getEventdate(){
		return this.eventdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  日期
	 */
	public void setEventdate(java.util.Date eventdate){
		this.eventdate = eventdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户
	 */
	@Column(name ="CUSTOMERNAME",nullable=true,length=16)
	public java.lang.String getCustomername(){
		return this.customername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户
	 */
	public void setCustomername(java.lang.String customername){
		this.customername = customername;
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
	 *@return: java.lang.String  交易编号
	 */
	@Column(name ="TRANSACTIONNO",nullable=true,length=32)
	public java.lang.String getTransactionno(){
		return this.transactionno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易编号
	 */
	public void setTransactionno(java.lang.String transactionno){
		this.transactionno = transactionno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  平台
	 */
	@Column(name ="PLATFORM",nullable=true,length=32)
	public java.lang.String getPlatform(){
		return this.platform;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  平台
	 */
	public void setPlatform(java.lang.String platform){
		this.platform = platform;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  金额
	 */
	@Column(name ="AMOUNT",nullable=true,length=16)
	public java.lang.String getAmount(){
		return this.amount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  金额
	 */
	public void setAmount(java.lang.String amount){
		this.amount = amount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车牌号
	 */
	@Column(name ="CARNUM",nullable=true,length=16)
	public java.lang.String getCarnum(){
		return this.carnum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车牌号
	 */
	public void setCarnum(java.lang.String carnum){
		this.carnum = carnum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=256)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
}
