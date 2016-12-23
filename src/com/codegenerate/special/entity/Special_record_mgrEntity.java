package com.codegenerate.special.entity;

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
 * @Description: 专车司机记录管理页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_SPECIAL_RECORD_MGR")
public class Special_record_mgrEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**范围*/
	private java.lang.String scopeid;
	/**UUID主键*/
	private java.lang.String id;
	/**日期*/
	private java.util.Date eventdate;
	/**发送方*/
	private java.lang.String sentperson;
	/**信息类别*/
	private java.lang.String infotype;
	/**司机姓名*/
	private java.lang.String drivername;
	/**内容*/
	private java.lang.String content;
	/**金额*/
	private java.lang.String account;
	/**处理人*/
	private java.lang.String dealperson;
	/**处理内容*/
	private java.lang.String dealcontent;
	/**司机电话*/
	private java.lang.String cellphone;
	/**年月*/
	private java.lang.String monthdate;
	/**发送标识*/
	private java.lang.String sendflag;
	// 是否已读
	private java.lang.String isReaded;
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
	 *@return: java.lang.String  发送方
	 */
	@Column(name ="SENTPERSON",nullable=true,length=16)
	public java.lang.String getSentperson(){
		return this.sentperson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发送方
	 */
	public void setSentperson(java.lang.String sentperson){
		this.sentperson = sentperson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  信息类别
	 */
	@Column(name ="INFOTYPE",nullable=true,length=16)
	public java.lang.String getInfotype(){
		return this.infotype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  信息类别
	 */
	public void setInfotype(java.lang.String infotype){
		this.infotype = infotype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司机姓名
	 */
	@Column(name ="DRIVERNAME",nullable=true,length=16)
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
	 *@return: java.lang.String  内容
	 */
	@Column(name ="CONTENT",nullable=true,length=256)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  内容
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  金额
	 */
	@Column(name ="ACCOUNT",nullable=true,length=18)
	public java.lang.String getAccount(){
		return this.account;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  金额
	 */
	public void setAccount(java.lang.String account){
		this.account = account;
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
	 *@return: java.lang.String  处理内容
	 */
	@Column(name ="DEALCONTENT",nullable=true,length=128)
	public java.lang.String getDealcontent(){
		return this.dealcontent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  处理内容
	 */
	public void setDealcontent(java.lang.String dealcontent){
		this.dealcontent = dealcontent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司机电话
	 */
	@Column(name ="CELLPHONE",nullable=true,length=16)
	public java.lang.String getCellphone(){
		return this.cellphone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  司机电话
	 */
	public void setCellphone(java.lang.String cellphone){
		this.cellphone = cellphone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  年月
	 */
	@Column(name ="MONTHDATE",nullable=true,length=8)
	public java.lang.String getMonthdate(){
		return this.monthdate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  年月
	 */
	public void setMonthdate(java.lang.String monthdate){
		this.monthdate = monthdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发送标识
	 */
	@Column(name ="SENDFLAG",nullable=true,length=8)
	public java.lang.String getSendflag(){
		return this.sendflag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发送标识
	 */
	public void setSendflag(java.lang.String sendflag){
		this.sendflag = sendflag;
	}

	public java.lang.String getIsReaded() {
		return isReaded;
	}
	public void setIsReaded(java.lang.String isReaded) {
		this.isReaded = isReaded;
	}
}
