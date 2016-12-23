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
 * @Description: 财务结算页面页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_FINANCIAL_SETTLEMENT")
public class Financial_settlementEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**客户*/
	private java.lang.String customername;
	/**电话*/
	private java.lang.String telephone;
	/**结算类型*/
	private java.lang.String settlementtype;
	/**结算业务类型*/
	private java.lang.String setbusinesstype;
	/**交易编号*/
	private java.lang.String transactionno;
	/**日期*/
	private java.util.Date eventdate;
	/**金额*/
	private java.lang.String amount;
	/**UUID主键*/
	private java.lang.String id;
	/**范围*/
	private java.lang.String scopeid;
	/**车牌号*/
	private java.lang.String carnum;
	/**备注*/
	private java.lang.String remark;
	/**租金所属月份*/
	private java.lang.String monthdate;
	/**凭证号码*/
	private java.lang.String documentnum;
	/**是否支付*/
	private java.lang.String ispay;
	/**支付信息*/
	private java.lang.String paymsg;
	/**订单ID*/
	private java.lang.String orderformid;
	/**司机类别*/
	private java.lang.String driverclass;
	/**司机车辆型号*/
	private java.lang.String carmodel;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户
	 */
	@Column(name ="CUSTOMERNAME",nullable=true,length=32)
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
	 *@return: java.lang.String  结算类型
	 */
	@Column(name ="SETTLEMENTTYPE",nullable=true,length=16)
	public java.lang.String getSettlementtype(){
		return this.settlementtype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  结算类型
	 */
	public void setSettlementtype(java.lang.String settlementtype){
		this.settlementtype = settlementtype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  结算业务类型
	 */
	@Column(name ="SETBUSINESSTYPE",nullable=true,length=16)
	public java.lang.String getSetbusinesstype(){
		return this.setbusinesstype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  结算业务类型
	 */
	public void setSetbusinesstype(java.lang.String setbusinesstype){
		this.setbusinesstype = setbusinesstype;
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
	 *@return: java.lang.String  金额
	 */
	@Column(name ="AMOUNT",nullable=true,length=18)
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  租金所属月份
	 */
	@Column(name ="MONTHDATE",nullable=true,length=8)
	public java.lang.String getMonthdate(){
		return this.monthdate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  租金所属月份
	 */
	public void setMonthdate(java.lang.String monthdate){
		this.monthdate = monthdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  凭证号码
	 */
	@Column(name ="DOCUMENTNUM",nullable=true,length=32)
	public java.lang.String getDocumentnum(){
		return this.documentnum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  凭证号码
	 */
	public void setDocumentnum(java.lang.String documentnum){
		this.documentnum = documentnum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否支付
	 */
	@Column(name ="ISPAY",nullable=true,length=8)
	public java.lang.String getIspay(){
		return this.ispay;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否支付
	 */
	public void setIspay(java.lang.String ispay){
		this.ispay = ispay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付信息
	 */
	@Column(name ="PAYMSG",nullable=true,length=16)
	public java.lang.String getPaymsg(){
		return this.paymsg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付信息
	 */
	public void setPaymsg(java.lang.String paymsg){
		this.paymsg = paymsg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单ID
	 */
	@Column(name ="ORDERFORMID",nullable=true,length=32)
	public java.lang.String getOrderformid(){
		return this.orderformid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单ID
	 */
	public void setOrderformid(java.lang.String orderformid){
		this.orderformid = orderformid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司机类别
	 */
	@Column(name ="DRIVERCLASS",nullable=true,length=32)
	public java.lang.String getDriverclass(){
		return this.driverclass;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  司机类别
	 */
	public void setDriverclass(java.lang.String driverclass){
		this.driverclass = driverclass;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司机车辆型号
	 */
	@Column(name ="CARMODEL",nullable=true,length=32)
	public java.lang.String getCarmodel(){
		return this.carmodel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  司机车辆型号
	 */
	public void setCarmodel(java.lang.String carmodel){
		this.carmodel = carmodel;
	}
}
