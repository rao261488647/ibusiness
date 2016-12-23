package com.codegenerate.carmgr.entity;

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
 * @Description: 指标公司管理页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_TARGET_COMPANT_MGR")
public class Target_compant_mgrEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**公司名称*/
	private java.lang.String companyname;
	/**公司法人*/
	private java.lang.String companyexecutor;
	/**法人联系方式*/
	private java.lang.String executorcell;
	/**摇号指标数*/
	private java.lang.String lottarytarget;
	/**更新指标数*/
	private java.lang.String updatetargetcount;
	/**其他指标数*/
	private java.lang.String othertargetcount;
	/**注册日期*/
	private java.util.Date registerdate;
	/**是否转让*/
	private java.lang.String isreturn;
	/**备注*/
	private java.lang.String remark;
	/**UUID主键*/
	private java.lang.String id;
	/**范围*/
	private java.lang.String scopeid;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司名称
	 */
	@Column(name ="COMPANYNAME",nullable=true,length=32)
	public java.lang.String getCompanyname(){
		return this.companyname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司名称
	 */
	public void setCompanyname(java.lang.String companyname){
		this.companyname = companyname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司法人
	 */
	@Column(name ="COMPANYEXECUTOR",nullable=true,length=8)
	public java.lang.String getCompanyexecutor(){
		return this.companyexecutor;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司法人
	 */
	public void setCompanyexecutor(java.lang.String companyexecutor){
		this.companyexecutor = companyexecutor;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  法人联系方式
	 */
	@Column(name ="EXECUTORCELL",nullable=true,length=16)
	public java.lang.String getExecutorcell(){
		return this.executorcell;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  法人联系方式
	 */
	public void setExecutorcell(java.lang.String executorcell){
		this.executorcell = executorcell;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  摇号指标数
	 */
	@Column(name ="LOTTARYTARGET",nullable=true,length=16)
	public java.lang.String getLottarytarget(){
		return this.lottarytarget;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  摇号指标数
	 */
	public void setLottarytarget(java.lang.String lottarytarget){
		this.lottarytarget = lottarytarget;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新指标数
	 */
	@Column(name ="UPDATETARGETCOUNT",nullable=true,length=16)
	public java.lang.String getUpdatetargetcount(){
		return this.updatetargetcount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新指标数
	 */
	public void setUpdatetargetcount(java.lang.String updatetargetcount){
		this.updatetargetcount = updatetargetcount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  其他指标数
	 */
	@Column(name ="OTHERTARGETCOUNT",nullable=true,length=16)
	public java.lang.String getOthertargetcount(){
		return this.othertargetcount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  其他指标数
	 */
	public void setOthertargetcount(java.lang.String othertargetcount){
		this.othertargetcount = othertargetcount;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  注册日期
	 */
	@Column(name ="REGISTERDATE",nullable=true)
	public java.util.Date getRegisterdate(){
		return this.registerdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  注册日期
	 */
	public void setRegisterdate(java.util.Date registerdate){
		this.registerdate = registerdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否转让
	 */
	@Column(name ="ISRETURN",nullable=true,length=8)
	public java.lang.String getIsreturn(){
		return this.isreturn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否转让
	 */
	public void setIsreturn(java.lang.String isreturn){
		this.isreturn = isreturn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=512)
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
