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
 * @Description: 宿舍管理页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_DORMITORY")
public class DormitoryEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**范围*/
	private java.lang.String scopeid;
	/**UUID主键*/
	private java.lang.String id;
	/**姓名*/
	private java.lang.String name;
	/**联系方式*/
	private java.lang.String callphone;
	/**身份证号码*/
	private java.lang.String eid;
	/**入职时间*/
	private java.util.Date entrydate;
	/**入住时间*/
	private java.util.Date checkintime;
	/**宿舍地址*/
	private java.lang.String adderss;
	/**房号*/
	private java.lang.String roomno;
	/**使用方式*/
	private java.lang.String useflag;
	/**搬离日期*/
	private java.util.Date departuredate;
	
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
	 *@return: java.lang.String  联系方式
	 */
	@Column(name ="CALLPHONE",nullable=true,length=16)
	public java.lang.String getCallphone(){
		return this.callphone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系方式
	 */
	public void setCallphone(java.lang.String callphone){
		this.callphone = callphone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  身份证号码
	 */
	@Column(name ="EID",nullable=true,length=18)
	public java.lang.String getEid(){
		return this.eid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证号码
	 */
	public void setEid(java.lang.String eid){
		this.eid = eid;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  入职时间
	 */
	@Column(name ="ENTRYDATE",nullable=true)
	public java.util.Date getEntrydate(){
		return this.entrydate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  入职时间
	 */
	public void setEntrydate(java.util.Date entrydate){
		this.entrydate = entrydate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  入住时间
	 */
	@Column(name ="CHECKINTIME",nullable=true)
	public java.util.Date getCheckintime(){
		return this.checkintime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  入住时间
	 */
	public void setCheckintime(java.util.Date checkintime){
		this.checkintime = checkintime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  宿舍地址
	 */
	@Column(name ="ADDERSS",nullable=true,length=64)
	public java.lang.String getAdderss(){
		return this.adderss;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  宿舍地址
	 */
	public void setAdderss(java.lang.String adderss){
		this.adderss = adderss;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  房号
	 */
	@Column(name ="ROOMNO",nullable=true,length=8)
	public java.lang.String getRoomno(){
		return this.roomno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  房号
	 */
	public void setRoomno(java.lang.String roomno){
		this.roomno = roomno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  使用方式
	 */
	@Column(name ="USEFLAG",nullable=true,length=8)
	public java.lang.String getUseflag(){
		return this.useflag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  使用方式
	 */
	public void setUseflag(java.lang.String useflag){
		this.useflag = useflag;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  搬离日期
	 */
	@Column(name ="DEPARTUREDATE",nullable=true)
	public java.util.Date getDeparturedate(){
		return this.departuredate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  搬离日期
	 */
	public void setDeparturedate(java.util.Date departuredate){
		this.departuredate = departuredate;
	}
}
