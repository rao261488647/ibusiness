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
 * @Description: 车库信息管理页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_GARAGE")
public class GarageEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**范围*/
	private java.lang.String scopeid;
	/**UUID主键*/
	private java.lang.String id;
	/**车库名称*/
	private java.lang.String name;
	/**车库位置*/
	private java.lang.String address;
	/**车库总容量*/
	private java.lang.String totalcapacity;
	/**已停车辆*/
	private java.lang.String parkedcar;
	/**添加人*/
	private java.lang.String adduser;
	/**添加日期*/
	private java.util.Date adddate;
	
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
	 *@return: java.lang.String  车库名称
	 */
	@Column(name ="NAME",nullable=true,length=32)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车库名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车库位置
	 */
	@Column(name ="ADDRESS",nullable=true,length=128)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车库位置
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车库总容量
	 */
	@Column(name ="TOTALCAPACITY",nullable=true,length=18)
	public java.lang.String getTotalcapacity(){
		return this.totalcapacity;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车库总容量
	 */
	public void setTotalcapacity(java.lang.String totalcapacity){
		this.totalcapacity = totalcapacity;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  已停车辆
	 */
	@Column(name ="PARKEDCAR",nullable=true,length=16)
	public java.lang.String getParkedcar(){
		return this.parkedcar;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  已停车辆
	 */
	public void setParkedcar(java.lang.String parkedcar){
		this.parkedcar = parkedcar;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  添加人
	 */
	@Column(name ="ADDUSER",nullable=true,length=8)
	public java.lang.String getAdduser(){
		return this.adduser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  添加人
	 */
	public void setAdduser(java.lang.String adduser){
		this.adduser = adduser;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  添加日期
	 */
	@Column(name ="ADDDATE",nullable=true)
	public java.util.Date getAdddate(){
		return this.adddate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  添加日期
	 */
	public void setAdddate(java.util.Date adddate){
		this.adddate = adddate;
	}
}
