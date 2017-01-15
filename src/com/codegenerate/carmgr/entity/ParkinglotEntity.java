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
 * @Description: 停车场出入库管理页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_PARKINGLOT")
public class ParkinglotEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**范围*/
	private java.lang.String scopeid;
	/**UUID主键*/
	private java.lang.String id;
	/**型号名称*/
	private java.lang.String typename;
	/**车牌号*/
	private java.lang.String carnum;
	/**入库原因*/
	private java.lang.String ininfo;
	/**入库时间*/
	private java.util.Date intime;
	/**入库里程*/
	private java.lang.String inmil;
	/**出库原因*/
	private java.lang.String outinfo;
	/**出库时间*/
	private java.util.Date outime;
	/**出库里程*/
	private java.lang.String outmil;
	
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
	 *@return: java.lang.String  型号名称
	 */
	@Column(name ="TYPENAME",nullable=true,length=32)
	public java.lang.String getTypename(){
		return this.typename;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  型号名称
	 */
	public void setTypename(java.lang.String typename){
		this.typename = typename;
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
	 *@return: java.lang.String  入库原因
	 */
	@Column(name ="ININFO",nullable=true,length=64)
	public java.lang.String getIninfo(){
		return this.ininfo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入库原因
	 */
	public void setIninfo(java.lang.String ininfo){
		this.ininfo = ininfo;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  入库时间
	 */
	@Column(name ="INTIME",nullable=true)
	public java.util.Date getIntime(){
		return this.intime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  入库时间
	 */
	public void setIntime(java.util.Date intime){
		this.intime = intime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入库里程
	 */
	@Column(name ="INMIL",nullable=true,length=16)
	public java.lang.String getInmil(){
		return this.inmil;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入库里程
	 */
	public void setInmil(java.lang.String inmil){
		this.inmil = inmil;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出库原因
	 */
	@Column(name ="OUTINFO",nullable=true,length=64)
	public java.lang.String getOutinfo(){
		return this.outinfo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出库原因
	 */
	public void setOutinfo(java.lang.String outinfo){
		this.outinfo = outinfo;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  出库时间
	 */
	@Column(name ="OUTIME",nullable=true)
	public java.util.Date getOutime(){
		return this.outime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  出库时间
	 */
	public void setOutime(java.util.Date outime){
		this.outime = outime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出库里程
	 */
	@Column(name ="OUTMIL",nullable=true,length=16)
	public java.lang.String getOutmil(){
		return this.outmil;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出库里程
	 */
	public void setOutmil(java.lang.String outmil){
		this.outmil = outmil;
	}
}
