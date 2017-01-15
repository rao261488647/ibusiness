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
 * @Description: 车辆库存管理页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_CAR_MGR")
public class Car_mgrEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**范围*/
	private java.lang.String scopeid;
	/**UUID主键*/
	private java.lang.String id;
	/**入库类型*/
	private java.lang.String intype;
	/**型号名称*/
	private java.lang.String typename;
	/**车牌号*/
	private java.lang.String carnum;
	/**车架号*/
	private java.lang.String carframenum;
	/**车辆出厂日期*/
	private java.util.Date caroutfaydate;
	/**上牌日期*/
	private java.util.Date signdate;
	/**存放仓库*/
	private java.lang.String savefactory;
	/**车辆类型*/
	private java.lang.String cartype;
	/**指标公司*/
	private java.lang.String targetcompany;
	/**车辆颜色*/
	private java.lang.String carcolor;
	/**购置税*/
	private java.lang.String buyfex;
	/**上牌费*/
	private java.lang.String signnumfee;
	/**初次保险费*/
	private java.lang.String firstsecurefee;
	/**初次保险到期日*/
	private java.util.Date firstsecuredate;
	/**登记证号*/
	private java.lang.String registereid;
	/**年审到期日*/
	private java.util.Date yearcheckdate;
	/**入库日期*/
	private java.util.Date infactorydate;
	/**车辆状态*/
	private java.lang.String carstatus;
	/**备注*/
	private java.lang.String remark;
	/**上传图片*/
	private java.lang.String uploadprcture;
	
	/**发动机号码*/
	private java.lang.String engineno;
	
	/**是否借出*/
	private java.lang.String isloan;
	/**是否维修*/
	private java.lang.String ismaintain;
	
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
	 *@return: java.lang.String  入库类型
	 */
	@Column(name ="INTYPE",nullable=true,length=16)
	public java.lang.String getIntype(){
		return this.intype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入库类型
	 */
	public void setIntype(java.lang.String intype){
		this.intype = intype;
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
	 *@return: java.lang.String  车架号
	 */
	@Column(name ="CARFRAMENUM",nullable=true,length=32)
	public java.lang.String getCarframenum(){
		return this.carframenum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车架号
	 */
	public void setCarframenum(java.lang.String carframenum){
		this.carframenum = carframenum;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  车辆出厂日期
	 */
	@Column(name ="CAROUTFAYDATE",nullable=true)
	public java.util.Date getCaroutfaydate(){
		return this.caroutfaydate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  车辆出厂日期
	 */
	public void setCaroutfaydate(java.util.Date caroutfaydate){
		this.caroutfaydate = caroutfaydate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  上牌日期
	 */
	@Column(name ="SIGNDATE",nullable=true)
	public java.util.Date getSigndate(){
		return this.signdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  上牌日期
	 */
	public void setSigndate(java.util.Date signdate){
		this.signdate = signdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  存放仓库
	 */
	@Column(name ="SAVEFACTORY",nullable=true,length=16)
	public java.lang.String getSavefactory(){
		return this.savefactory;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  存放仓库
	 */
	public void setSavefactory(java.lang.String savefactory){
		this.savefactory = savefactory;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车辆类型
	 */
	@Column(name ="CARTYPE",nullable=true,length=16)
	public java.lang.String getCartype(){
		return this.cartype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车辆类型
	 */
	public void setCartype(java.lang.String cartype){
		this.cartype = cartype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  指标公司
	 */
	@Column(name ="TARGETCOMPANY",nullable=true,length=32)
	public java.lang.String getTargetcompany(){
		return this.targetcompany;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  指标公司
	 */
	public void setTargetcompany(java.lang.String targetcompany){
		this.targetcompany = targetcompany;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车辆颜色
	 */
	@Column(name ="CARCOLOR",nullable=true,length=16)
	public java.lang.String getCarcolor(){
		return this.carcolor;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车辆颜色
	 */
	public void setCarcolor(java.lang.String carcolor){
		this.carcolor = carcolor;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  购置税
	 */
	@Column(name ="BUYFEX",nullable=true,length=16)
	public java.lang.String getBuyfex(){
		return this.buyfex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  购置税
	 */
	public void setBuyfex(java.lang.String buyfex){
		this.buyfex = buyfex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上牌费
	 */
	@Column(name ="SIGNNUMFEE",nullable=true,length=16)
	public java.lang.String getSignnumfee(){
		return this.signnumfee;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上牌费
	 */
	public void setSignnumfee(java.lang.String signnumfee){
		this.signnumfee = signnumfee;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  初次保险费
	 */
	@Column(name ="FIRSTSECUREFEE",nullable=true,length=18)
	public java.lang.String getFirstsecurefee(){
		return this.firstsecurefee;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  初次保险费
	 */
	public void setFirstsecurefee(java.lang.String firstsecurefee){
		this.firstsecurefee = firstsecurefee;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  初次保险到期日
	 */
	@Column(name ="FIRSTSECUREDATE",nullable=true)
	public java.util.Date getFirstsecuredate(){
		return this.firstsecuredate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  初次保险到期日
	 */
	public void setFirstsecuredate(java.util.Date firstsecuredate){
		this.firstsecuredate = firstsecuredate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登记证号
	 */
	@Column(name ="REGISTEREID",nullable=true,length=32)
	public java.lang.String getRegistereid(){
		return this.registereid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登记证号
	 */
	public void setRegistereid(java.lang.String registereid){
		this.registereid = registereid;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  年审到期日
	 */
	@Column(name ="YEARCHECKDATE",nullable=true)
	public java.util.Date getYearcheckdate(){
		return this.yearcheckdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  年审到期日
	 */
	public void setYearcheckdate(java.util.Date yearcheckdate){
		this.yearcheckdate = yearcheckdate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  入库日期
	 */
	@Column(name ="INFACTORYDATE",nullable=true)
	public java.util.Date getInfactorydate(){
		return this.infactorydate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  入库日期
	 */
	public void setInfactorydate(java.util.Date infactorydate){
		this.infactorydate = infactorydate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车辆状态
	 */
	@Column(name ="CARSTATUS",nullable=true,length=16)
	public java.lang.String getCarstatus(){
		return this.carstatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车辆状态
	 */
	public void setCarstatus(java.lang.String carstatus){
		this.carstatus = carstatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=256)
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
	 *@return: java.lang.String  上传图片
	 */
	@Column(name ="UPLOADPRCTURE",nullable=true,length=128)
	public java.lang.String getUploadprcture(){
		return this.uploadprcture;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上传图片
	 */
	public void setUploadprcture(java.lang.String uploadprcture){
		this.uploadprcture = uploadprcture;
	}

	public java.lang.String getEngineno() {
		return engineno;
	}
	@Column(name ="ENGINENO",nullable=true,length=128)
	public void setEngineno(java.lang.String engineno) {
		this.engineno = engineno;
	}

	public java.lang.String getIsloan() {
		return isloan;
	}
	@Column(name ="ISLOAN",nullable=true,length=128)
	public void setIsloan(java.lang.String isloan) {
		this.isloan = isloan;
	}

	public java.lang.String getIsmaintain() {
		return ismaintain;
	}
	@Column(name ="ISMAINTAIN",nullable=true,length=128)
	public void setIsmaintain(java.lang.String ismaintain) {
		this.ismaintain = ismaintain;
	}
	
}
