package com.ibusiness.workbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 工作日志
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_WORKBOOK")
public class WorkbookEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**id*/
	private java.lang.String id;
	/**creattime*/
	private java.lang.String creattime;
	/**userid*/
	private java.lang.String userid;
	/**workcontent*/
	private java.lang.String workcontent;
	/**worktitle*/
	private java.lang.String worktitle;
	/**worktype*/
	private java.lang.String worktype;
	/**mealamount*/
	private java.lang.String mealamount;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=64)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  creattime
	 */
	@Column(name ="CREATTIME",nullable=true,length=16)
	public java.lang.String getCreattime(){
		return this.creattime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  creattime
	 */
	public void setCreattime(java.lang.String creattime){
		this.creattime = creattime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  userid
	 */
	@Column(name ="USERID",nullable=true,length=64)
	public java.lang.String getUserid(){
		return this.userid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  userid
	 */
	public void setUserid(java.lang.String userid){
		this.userid = userid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  workcontent
	 */
	@Column(name ="WORKCONTENT",nullable=true,length=2000)
	public java.lang.String getWorkcontent(){
		return this.workcontent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  workcontent
	 */
	public void setWorkcontent(java.lang.String workcontent){
		this.workcontent = workcontent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  worktitle
	 */
	@Column(name ="WORKTITLE",nullable=true,length=128)
	public java.lang.String getWorktitle(){
		return this.worktitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  worktitle
	 */
	public void setWorktitle(java.lang.String worktitle){
		this.worktitle = worktitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  worktype
	 */
	@Column(name ="WORKTYPE",nullable=true,length=64)
	public java.lang.String getWorktype(){
		return this.worktype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  worktype
	 */
	public void setWorktype(java.lang.String worktype){
		this.worktype = worktype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  mealamount
	 */
	@Column(name ="MEALAMOUNT",nullable=true,length=32)
	public java.lang.String getMealamount(){
		return this.mealamount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  mealamount
	 */
	public void setMealamount(java.lang.String mealamount){
		this.mealamount = mealamount;
	}
}
