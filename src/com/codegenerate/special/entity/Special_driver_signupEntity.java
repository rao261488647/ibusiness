package com.codegenerate.special.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @Title: Entity
 * @Description: 专车司机报名页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_SPECIAL_DRIVER_SIGNUP")
public class Special_driver_signupEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**范围*/
	private java.lang.String scopeid;
	/**UUID主键*/
	private java.lang.String id;
	/**姓名*/
	private java.lang.String name;
	/**电话*/
	private java.lang.String cell;
	/**提交日期*/
	private java.util.Date submitdate;
	/**预约类型*/
	private java.lang.String ordertype;
	/**车辆型号*/
	private java.lang.String cartype;
	/**自有车已使用年限*/
	private java.lang.String selfcaruseyear;
	/**车辆图片*/
	private java.lang.String carphote;
	/**想加入的平台*/
	private java.lang.String platformto;
	/**欲签合同期*/
	private java.lang.String precontractdate;
	/**期望注册日期*/
	private java.util.Date preregisterdate;
	/**期望注册的时间*/
	private java.lang.String preregistertime;
	/**处理人*/
	private java.lang.String dealperson;
	/**处理时间*/
	private java.util.Date dealtime;
	/**处理结果*/
	private java.lang.String dealresult;
	// 每月租金
	private String payrentmonth;
	// 当前登录手机号
	private String logintel;
	// 入口数据类型： 移动端,PC端
	private String inputtype;
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
	@Column(name ="NAME",nullable=true,length=8)
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
	@Column(name ="CELL",nullable=true,length=16)
	public java.lang.String getCell(){
		return this.cell;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setCell(java.lang.String cell){
		this.cell = cell;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预约类型
	 */
	@Column(name ="ORDERTYPE",nullable=true,length=16)
	public java.lang.String getOrdertype(){
		return this.ordertype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预约类型
	 */
	public void setOrdertype(java.lang.String ordertype){
		this.ordertype = ordertype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车辆型号
	 */
	@Column(name ="CARTYPE",nullable=true,length=16)
	public java.lang.String getCartype(){
		return this.cartype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车辆型号
	 */
	public void setCartype(java.lang.String cartype){
		this.cartype = cartype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  自有车已使用年限
	 */
	@Column(name ="SELFCARUSEYEAR",nullable=true,length=8)
	public java.lang.String getSelfcaruseyear(){
		return this.selfcaruseyear;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  自有车已使用年限
	 */
	public void setSelfcaruseyear(java.lang.String selfcaruseyear){
		this.selfcaruseyear = selfcaruseyear;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车辆图片
	 */
	@Column(name ="CARPHOTE",nullable=true,length=128)
	public java.lang.String getCarphote(){
		return this.carphote;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车辆图片
	 */
	public void setCarphote(java.lang.String carphote){
		this.carphote = carphote;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  想加入的平台
	 */
	@Column(name ="PLATFORMTO",nullable=true,length=32)
	public java.lang.String getPlatformto(){
		return this.platformto;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  想加入的平台
	 */
	public void setPlatformto(java.lang.String platformto){
		this.platformto = platformto;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  欲签合同期
	 */
	@Column(name ="PRECONTRACTDATE",nullable=true,length=16)
	public java.lang.String getPrecontractdate(){
		return this.precontractdate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  欲签合同期
	 */
	public void setPrecontractdate(java.lang.String precontractdate){
		this.precontractdate = precontractdate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  期望注册日期
	 */
	@Column(name ="PREREGISTERDATE",nullable=true)
	public java.util.Date getPreregisterdate(){
		return this.preregisterdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  期望注册日期
	 */
	public void setPreregisterdate(java.util.Date preregisterdate){
		this.preregisterdate = preregisterdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  期望注册的时间
	 */
	@Column(name ="PREREGISTERTIME",nullable=true,length=16)
	public java.lang.String getPreregistertime(){
		return this.preregistertime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  期望注册的时间
	 */
	public void setPreregistertime(java.lang.String preregistertime){
		this.preregistertime = preregistertime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  处理人
	 */
	@Column(name ="DEALPERSON",nullable=true,length=8)
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
	@Column(name ="PAYRENTMONTH")
	public String getPayrentmonth() {
		return payrentmonth;
	}
	public void setPayrentmonth(String payrentmonth) {
		this.payrentmonth = payrentmonth;
	}
	@Column(name ="LOGINTEL")
	public String getLogintel() {
		return logintel;
	}
	public void setLogintel(String logintel) {
		this.logintel = logintel;
	}
	@Column(name ="INPUTTYPE")
	public String getInputtype() {
		return inputtype;
	}
	public void setInputtype(String inputtype) {
		this.inputtype = inputtype;
	}
}
