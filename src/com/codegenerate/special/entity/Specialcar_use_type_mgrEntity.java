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
 * @Description: 专车使用车型管理页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_SPECIALCAR_USE_TYPE_MGR")
public class Specialcar_use_type_mgrEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
    /**车型编号*/
	private java.lang.String cartypeid;
	/**车型名称*/
	private java.lang.String cartype;
	/**新旧程度*/
	private java.lang.String neworold;
	/**3个月合同每月租金*/
	private java.lang.String contrforthreemth;
	/**6个月合同月租金*/
	private java.lang.String contrforsixmth;
	/**1年合同月租金租金*/
	private java.lang.String contrforsoneyear;
	/**2年合同月租金*/
	private java.lang.String contrforstwoyear;
	/**汽车押金*/
	private java.lang.String cardeposit;
	/**违章押金*/
	private java.lang.String illegaldeposit;
	/**状态*/
	private java.lang.String status;
	/**UUID主键*/
	private java.lang.String id;
	/**范围*/
	private java.lang.String scopeid;
	/**专车使用平台*/
	private java.lang.String plat;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车型名称
	 */
	@Column(name ="CARTYPE",nullable=true,length=16)
	public java.lang.String getCartype(){
		return this.cartype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车型名称
	 */
	public void setCartype(java.lang.String cartype){
		this.cartype = cartype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  新旧程度
	 */
	@Column(name ="NEWOROLD",nullable=true,length=16)
	public java.lang.String getNeworold(){
		return this.neworold;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  新旧程度
	 */
	public void setNeworold(java.lang.String neworold){
		this.neworold = neworold;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  3个月合同每月租金
	 */
	@Column(name ="CONTRFORTHREEMTH",nullable=true,length=16)
	public java.lang.String getContrforthreemth(){
		return this.contrforthreemth;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  3个月合同每月租金
	 */
	public void setContrforthreemth(java.lang.String contrforthreemth){
		this.contrforthreemth = contrforthreemth;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  6个月合同月租金
	 */
	@Column(name ="CONTRFORSIXMTH",nullable=true,length=16)
	public java.lang.String getContrforsixmth(){
		return this.contrforsixmth;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  6个月合同月租金
	 */
	public void setContrforsixmth(java.lang.String contrforsixmth){
		this.contrforsixmth = contrforsixmth;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  1年合同月租金租金
	 */
	@Column(name ="CONTRFORSONEYEAR",nullable=true,length=16)
	public java.lang.String getContrforsoneyear(){
		return this.contrforsoneyear;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  1年合同月租金租金
	 */
	public void setContrforsoneyear(java.lang.String contrforsoneyear){
		this.contrforsoneyear = contrforsoneyear;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  2年合同月租金
	 */
	@Column(name ="CONTRFORSTWOYEAR",nullable=true,length=16)
	public java.lang.String getContrforstwoyear(){
		return this.contrforstwoyear;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  2年合同月租金
	 */
	public void setContrforstwoyear(java.lang.String contrforstwoyear){
		this.contrforstwoyear = contrforstwoyear;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  汽车押金
	 */
	@Column(name ="CARDEPOSIT",nullable=true,length=16)
	public java.lang.String getCardeposit(){
		return this.cardeposit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  汽车押金
	 */
	public void setCardeposit(java.lang.String cardeposit){
		this.cardeposit = cardeposit;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  违章押金
	 */
	@Column(name ="ILLEGALDEPOSIT",nullable=true,length=16)
	public java.lang.String getIllegaldeposit(){
		return this.illegaldeposit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  违章押金
	 */
	public void setIllegaldeposit(java.lang.String illegaldeposit){
		this.illegaldeposit = illegaldeposit;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATUS",nullable=true,length=8)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
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
	 *@return: java.lang.String  专车使用平台
	 */
	@Column(name ="PLAT",nullable=true,length=64)
	public java.lang.String getPlat(){
		return this.plat;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  专车使用平台
	 */
	public void setPlat(java.lang.String plat){
		this.plat = plat;
	}
}
