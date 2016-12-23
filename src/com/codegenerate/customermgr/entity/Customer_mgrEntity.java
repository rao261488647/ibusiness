package com.codegenerate.customermgr.entity;

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
 * @Description: 客户管理页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_CUSTOMER_MGR")
public class Customer_mgrEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**客户来源*/
	private java.lang.String tosource;
	/**客户姓名*/
	private java.lang.String customername;
	/**客户电话*/
	private java.lang.String customercell;
	/**单位名称*/
	private java.lang.String unitname;
	/**职务*/
	private java.lang.String post;
	/**客户地址*/
	private java.lang.String customeradd;
	/**客户类别*/
	private java.lang.String customertype;
	/**客户归属*/
	private java.lang.String customertobe;
	/**密码*/
	private java.lang.String password;
	/**设备型号*/
	private java.lang.String device;
	/**设备唯一标识*/
	private java.lang.String imei;
	/**系统版本*/
	private java.lang.String appsysversion;
	/**手机token*/
	private java.lang.String token;
	/**推荐人*/
	private java.lang.String recommendman;
	/**业务员*/
	private java.lang.String salesman;
	/**备注*/
	private java.lang.String remarks;
	/**新建日期*/
	private java.util.Date createdatetime;
	/**UUID主键*/
	private java.lang.String id;
	/**范围*/
	private java.lang.String scopeid;
	/**客户状态*/
	private java.lang.String userstatus;
	/**性别*/
	private java.lang.String sex;
	/**籍贯*/
	private java.lang.String origin;
	/**职业*/
	private java.lang.String occupation;
	/**身份证号码*/
	private java.lang.String idcard;
	/**有无犯罪记录*/
	private java.lang.String iscrime;
	/**签约状况*/
	private java.lang.String contractstatus;
	/**签约方案*/
	private java.lang.String contractplan;
	/**入职意向*/
	private java.lang.String intention;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户来源
	 */
	@Column(name ="TOSOURCE",nullable=true,length=64)
	public java.lang.String getTosource(){
		return this.tosource;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户来源
	 */
	public void setTosource(java.lang.String tosource){
		this.tosource = tosource;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户姓名
	 */
	@Column(name ="CUSTOMERNAME",nullable=true,length=18)
	public java.lang.String getCustomername(){
		return this.customername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户姓名
	 */
	public void setCustomername(java.lang.String customername){
		this.customername = customername;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户电话
	 */
	@Column(name ="CUSTOMERCELL",nullable=true,length=16)
	public java.lang.String getCustomercell(){
		return this.customercell;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户电话
	 */
	public void setCustomercell(java.lang.String customercell){
		this.customercell = customercell;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单位名称
	 */
	@Column(name ="UNITNAME",nullable=true,length=32)
	public java.lang.String getUnitname(){
		return this.unitname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单位名称
	 */
	public void setUnitname(java.lang.String unitname){
		this.unitname = unitname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职务
	 */
	@Column(name ="POST",nullable=true,length=16)
	public java.lang.String getPost(){
		return this.post;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职务
	 */
	public void setPost(java.lang.String post){
		this.post = post;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户地址
	 */
	@Column(name ="CUSTOMERADD",nullable=true,length=128)
	public java.lang.String getCustomeradd(){
		return this.customeradd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户地址
	 */
	public void setCustomeradd(java.lang.String customeradd){
		this.customeradd = customeradd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户类别
	 */
	@Column(name ="CUSTOMERTYPE",nullable=true,length=16)
	public java.lang.String getCustomertype(){
		return this.customertype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户类别
	 */
	public void setCustomertype(java.lang.String customertype){
		this.customertype = customertype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户归属
	 */
	@Column(name ="CUSTOMERTOBE",nullable=true,length=16)
	public java.lang.String getCustomertobe(){
		return this.customertobe;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户归属
	 */
	public void setCustomertobe(java.lang.String customertobe){
		this.customertobe = customertobe;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  密码
	 */
	@Column(name ="PASSWORD",nullable=true,length=32)
	public java.lang.String getPassword(){
		return this.password;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  密码
	 */
	public void setPassword(java.lang.String password){
		this.password = password;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备型号
	 */
	@Column(name ="DEVICE",nullable=true,length=64)
	public java.lang.String getDevice(){
		return this.device;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备型号
	 */
	public void setDevice(java.lang.String device){
		this.device = device;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备唯一标识
	 */
	@Column(name ="IMEI",nullable=true,length=64)
	public java.lang.String getImei(){
		return this.imei;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备唯一标识
	 */
	public void setImei(java.lang.String imei){
		this.imei = imei;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  系统版本
	 */
	@Column(name ="APPSYSVERSION",nullable=true,length=8)
	public java.lang.String getAppsysversion(){
		return this.appsysversion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  系统版本
	 */
	public void setAppsysversion(java.lang.String appsysversion){
		this.appsysversion = appsysversion;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机token
	 */
	@Column(name ="TOKEN",nullable=true,length=64)
	public java.lang.String getToken(){
		return this.token;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机token
	 */
	public void setToken(java.lang.String token){
		this.token = token;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  推荐人
	 */
	@Column(name ="RECOMMENDMAN",nullable=true,length=24)
	public java.lang.String getRecommendman(){
		return this.recommendman;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  推荐人
	 */
	public void setRecommendman(java.lang.String recommendman){
		this.recommendman = recommendman;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务员
	 */
	@Column(name ="SALESMAN",nullable=true,length=16)
	public java.lang.String getSalesman(){
		return this.salesman;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务员
	 */
	public void setSalesman(java.lang.String salesman){
		this.salesman = salesman;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARKS",nullable=true,length=512)
	public java.lang.String getRemarks(){
		return this.remarks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemarks(java.lang.String remarks){
		this.remarks = remarks;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  新建日期
	 */
	@Column(name ="CREATEDATETIME",nullable=true)
	public java.util.Date getCreatedatetime(){
		return this.createdatetime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  新建日期
	 */
	public void setCreatedatetime(java.util.Date createdatetime){
		this.createdatetime = createdatetime;
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
	 *@return: java.lang.String  客户状态
	 */
	@Column(name ="USERSTATUS",nullable=true,length=8)
	public java.lang.String getUserstatus(){
		return this.userstatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户状态
	 */
	public void setUserstatus(java.lang.String userstatus){
		this.userstatus = userstatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */
	@Column(name ="SEX",nullable=true,length=8)
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  籍贯
	 */
	@Column(name ="ORIGIN",nullable=true,length=8)
	public java.lang.String getOrigin(){
		return this.origin;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  籍贯
	 */
	public void setOrigin(java.lang.String origin){
		this.origin = origin;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职业
	 */
	@Column(name ="OCCUPATION",nullable=true,length=8)
	public java.lang.String getOccupation(){
		return this.occupation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职业
	 */
	public void setOccupation(java.lang.String occupation){
		this.occupation = occupation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  身份证号码
	 */
	@Column(name ="IDCARD",nullable=true,length=24)
	public java.lang.String getIdcard(){
		return this.idcard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证号码
	 */
	public void setIdcard(java.lang.String idcard){
		this.idcard = idcard;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  有无犯罪记录
	 */
	@Column(name ="ISCRIME",nullable=true,length=4)
	public java.lang.String getIscrime(){
		return this.iscrime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  有无犯罪记录
	 */
	public void setIscrime(java.lang.String iscrime){
		this.iscrime = iscrime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  签约状况
	 */
	@Column(name ="CONTRACTSTATUS",nullable=true,length=8)
	public java.lang.String getContractstatus(){
		return this.contractstatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  签约状况
	 */
	public void setContractstatus(java.lang.String contractstatus){
		this.contractstatus = contractstatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  签约方案
	 */
	@Column(name ="CONTRACTPLAN",nullable=true,length=16)
	public java.lang.String getContractplan(){
		return this.contractplan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  签约方案
	 */
	public void setContractplan(java.lang.String contractplan){
		this.contractplan = contractplan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入职意向
	 */
	@Column(name ="INTENTION",nullable=true,length=8)
	public java.lang.String getIntention(){
		return this.intention;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入职意向
	 */
	public void setIntention(java.lang.String intention){
		this.intention = intention;
	}
}
