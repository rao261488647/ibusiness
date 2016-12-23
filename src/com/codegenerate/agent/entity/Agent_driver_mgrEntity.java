package com.codegenerate.agent.entity;

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
 * @Description: 代驾司机列表管理页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_AGENT_DRIVER_MGR")
public class Agent_driver_mgrEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**范围*/
	private java.lang.String scopeid;
	/**UUID主键*/
	private java.lang.String id;
	/**司机编号*/
	private java.lang.String num;
	/**司机姓名*/
	private java.lang.String name;
	/**司机工作手机*/
	private java.lang.String workcell;
	/**司机手机*/
	private java.lang.String cell;
	/**司机身份证号码*/
	private java.lang.String eid;
	/**性别*/
	private java.lang.String sex;
	/**地址*/
	private java.lang.String address;
	/**发证机关*/
	private java.lang.String publiahgrd;
	/**出生日期*/
	private java.util.Date birth;
	/**民族*/
	private java.lang.String nation;
	/**司机驾驶证编号*/
	private java.lang.String drivernum;
	/**司机驾驶证档案号*/
	private java.lang.String driverfilenum;
	/**驾照初领日期*/
	private java.util.Date predriverdate;
	/**准驾车型*/
	private java.lang.String precartype;
	/**有效起始日期*/
	private java.util.Date validstartdate;
	/**有效期限*/
	private java.lang.String validtrem;
	/**车型名称*/
	private java.lang.String carname;
	/**合同期限*/
	private java.lang.String contracterm;
	
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
	 *@return: java.lang.String  司机编号
	 */
	@Column(name ="NUM",nullable=true,length=32)
	public java.lang.String getNum(){
		return this.num;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  司机编号
	 */
	public void setNum(java.lang.String num){
		this.num = num;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司机姓名
	 */
	@Column(name ="NAME",nullable=true,length=16)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  司机姓名
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司机工作手机
	 */
	@Column(name ="WORKCELL",nullable=true,length=18)
	public java.lang.String getWorkcell(){
		return this.workcell;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  司机工作手机
	 */
	public void setWorkcell(java.lang.String workcell){
		this.workcell = workcell;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司机手机
	 */
	@Column(name ="CELL",nullable=true,length=18)
	public java.lang.String getCell(){
		return this.cell;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  司机手机
	 */
	public void setCell(java.lang.String cell){
		this.cell = cell;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司机身份证号码
	 */
	@Column(name ="EID",nullable=true,length=24)
	public java.lang.String getEid(){
		return this.eid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  司机身份证号码
	 */
	public void setEid(java.lang.String eid){
		this.eid = eid;
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
	 *@return: java.lang.String  地址
	 */
	@Column(name ="ADDRESS",nullable=true,length=64)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发证机关
	 */
	@Column(name ="PUBLIAHGRD",nullable=true,length=32)
	public java.lang.String getPubliahgrd(){
		return this.publiahgrd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发证机关
	 */
	public void setPubliahgrd(java.lang.String publiahgrd){
		this.publiahgrd = publiahgrd;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  出生日期
	 */
	@Column(name ="BIRTH",nullable=true)
	public java.util.Date getBirth(){
		return this.birth;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  出生日期
	 */
	public void setBirth(java.util.Date birth){
		this.birth = birth;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  民族
	 */
	@Column(name ="NATION",nullable=true,length=8)
	public java.lang.String getNation(){
		return this.nation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  民族
	 */
	public void setNation(java.lang.String nation){
		this.nation = nation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司机驾驶证编号
	 */
	@Column(name ="DRIVERNUM",nullable=true,length=32)
	public java.lang.String getDrivernum(){
		return this.drivernum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  司机驾驶证编号
	 */
	public void setDrivernum(java.lang.String drivernum){
		this.drivernum = drivernum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司机驾驶证档案号
	 */
	@Column(name ="DRIVERFILENUM",nullable=true,length=18)
	public java.lang.String getDriverfilenum(){
		return this.driverfilenum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  司机驾驶证档案号
	 */
	public void setDriverfilenum(java.lang.String driverfilenum){
		this.driverfilenum = driverfilenum;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  驾照初领日期
	 */
	@Column(name ="PREDRIVERDATE",nullable=true)
	public java.util.Date getPredriverdate(){
		return this.predriverdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  驾照初领日期
	 */
	public void setPredriverdate(java.util.Date predriverdate){
		this.predriverdate = predriverdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  准驾车型
	 */
	@Column(name ="PRECARTYPE",nullable=true,length=18)
	public java.lang.String getPrecartype(){
		return this.precartype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  准驾车型
	 */
	public void setPrecartype(java.lang.String precartype){
		this.precartype = precartype;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  有效起始日期
	 */
	@Column(name ="VALIDSTARTDATE",nullable=true)
	public java.util.Date getValidstartdate(){
		return this.validstartdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  有效起始日期
	 */
	public void setValidstartdate(java.util.Date validstartdate){
		this.validstartdate = validstartdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  有效期限
	 */
	@Column(name ="VALIDTREM",nullable=true,length=16)
	public java.lang.String getValidtrem(){
		return this.validtrem;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  有效期限
	 */
	public void setValidtrem(java.lang.String validtrem){
		this.validtrem = validtrem;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车型名称
	 */
	@Column(name ="CARNAME",nullable=true,length=16)
	public java.lang.String getCarname(){
		return this.carname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车型名称
	 */
	public void setCarname(java.lang.String carname){
		this.carname = carname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同期限
	 */
	@Column(name ="CONTRACTERM",nullable=true,length=8)
	public java.lang.String getContracterm(){
		return this.contracterm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同期限
	 */
	public void setContracterm(java.lang.String contracterm){
		this.contracterm = contracterm;
	}
}
