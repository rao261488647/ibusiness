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
 * @Description: 专车司机信息页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_SPECIAL_DRIVER_INFO")
public class Special_driver_infoEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**账号*/
	private java.lang.String account;
	/**司机姓名*/
	private java.lang.String name;
	/**司机电话*/
	private java.lang.String cellphone;
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
	/**身份证附件*/
	private java.lang.String eidurl;
	/**司机驾驶证编号*/
	private java.lang.String drivernum;
	/**司机驾驶证档案号*/
	private java.lang.String driverfilenum;
	/**驾照初领日期*/
	private java.util.Date predriverdate;
	/**驾照类别*/
	private java.lang.String precartype;
	/**有效起始日期*/
	private java.util.Date validstartdate;
	/**有效期限*/
	private java.lang.String validtrem;
	/**驾驶证扫描附件*/
	private java.lang.String drivernumurl;
	/**车型名称*/
	private java.lang.String carname;
	/**车牌号码*/
	private java.lang.String carnum;
	/**车架号*/
	private java.lang.String carframenum;
	/**颜色*/
	private java.lang.String carcolor;
	/**司机签约类型*/
	private java.lang.String drivertype;
	/**合同编号*/
	private java.lang.String contractid;
	/**合同期限*/
	private java.lang.String contractterm;
	/**合同总押金*/
	private java.lang.String contractsumdeposit;
	/**合同生效日*/
	private java.util.Date contractvalidday;
	/**合同终止日期*/
	private java.util.Date contractoverdate;
	/**签订租金*/
	private java.lang.String permonthrent;
	/**每月交租日*/
	private java.lang.String permonthday;
	/**车辆押金*/
	private java.lang.String cardeposit;
	/**合同扫描件上传*/
	private java.lang.String contractcopyupload;
	/**状态*/
	private java.lang.String status;
	/**加入平台*/
	private java.lang.String platformto;
	/**登录手机号*/
	private java.lang.String logintel;
	/**来源*/
	private java.lang.String tosource;
	/**推荐人*/
	private java.lang.String recommended;
	/**洽谈人*/
	private java.lang.String busipeople;
	/**业务员*/
	private java.lang.String salesman;
	/**备注*/
	private java.lang.String remarks;
	/**UUID主键*/
	private java.lang.String id;
	/**范围*/
	private java.lang.String scopeid;
	/**公司银行账号*/
	private java.lang.String companybankact;
	/**公司银行开户行*/
	private java.lang.String companybank;
	/**工资卡银行账号*/
	private java.lang.String payrollbankact;
	/**工资卡开户行*/
	private java.lang.String payrollbank;
	/**U盾号码*/
	private java.lang.String udnumber;
	/**U盾用户名*/
	private java.lang.String udusername;
	/**Ubername*/
	private java.lang.String ubername;
	/**Uberpassword*/
	private java.lang.String uberpassword;
	/**Uber上线状态*/
	private java.lang.String uberonline;
	/**滴滴出行上线状态*/
	private java.lang.String ddonline;
	/**工资(合作/合同)方案*/
	private java.lang.String wageplan;
	/**通途电卡卡号*/
	private java.lang.String ttcard;
	/**BYD电卡编号*/
	private java.lang.String bydcard;
	/**普天电卡卡号*/
	private java.lang.String ptdkcard;
	/**社保*/
	private java.lang.String sbcard;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账号
	 */
	@Column(name ="ACCOUNT",nullable=true,length=16)
	public java.lang.String getAccount(){
		return this.account;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账号
	 */
	public void setAccount(java.lang.String account){
		this.account = account;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司机姓名
	 */
	@Column(name ="NAME",nullable=true,length=8)
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
	 *@return: java.lang.String  司机电话
	 */
	@Column(name ="CELLPHONE",nullable=true,length=16)
	public java.lang.String getCellphone(){
		return this.cellphone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  司机电话
	 */
	public void setCellphone(java.lang.String cellphone){
		this.cellphone = cellphone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司机身份证号码
	 */
	@Column(name ="EID",nullable=true,length=18)
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
	@Column(name ="ADDRESS",nullable=true,length=128)
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
	@Column(name ="NATION",nullable=true,length=16)
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
	 *@return: java.lang.String  身份证附件
	 */
	@Column(name ="EIDURL",nullable=true,length=128)
	public java.lang.String getEidurl(){
		return this.eidurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证附件
	 */
	public void setEidurl(java.lang.String eidurl){
		this.eidurl = eidurl;
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
	@Column(name ="DRIVERFILENUM",nullable=true,length=32)
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
	 *@return: java.lang.String  驾照类别
	 */
	@Column(name ="PRECARTYPE",nullable=true,length=8)
	public java.lang.String getPrecartype(){
		return this.precartype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  驾照类别
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
	@Column(name ="VALIDTREM",nullable=true,length=8)
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
	 *@return: java.lang.String  驾驶证扫描附件
	 */
	@Column(name ="DRIVERNUMURL",nullable=true,length=128)
	public java.lang.String getDrivernumurl(){
		return this.drivernumurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  驾驶证扫描附件
	 */
	public void setDrivernumurl(java.lang.String drivernumurl){
		this.drivernumurl = drivernumurl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车型名称
	 */
	@Column(name ="CARNAME",nullable=true,length=32)
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
	 *@return: java.lang.String  车牌号码
	 */
	@Column(name ="CARNUM",nullable=true,length=16)
	public java.lang.String getCarnum(){
		return this.carnum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车牌号码
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  颜色
	 */
	@Column(name ="CARCOLOR",nullable=true,length=16)
	public java.lang.String getCarcolor(){
		return this.carcolor;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  颜色
	 */
	public void setCarcolor(java.lang.String carcolor){
		this.carcolor = carcolor;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司机签约类型
	 */
	@Column(name ="DRIVERTYPE",nullable=true,length=16)
	public java.lang.String getDrivertype(){
		return this.drivertype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  司机签约类型
	 */
	public void setDrivertype(java.lang.String drivertype){
		this.drivertype = drivertype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同编号
	 */
	@Column(name ="CONTRACTID",nullable=true,length=32)
	public java.lang.String getContractid(){
		return this.contractid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同编号
	 */
	public void setContractid(java.lang.String contractid){
		this.contractid = contractid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同期限
	 */
	@Column(name ="CONTRACTTERM",nullable=true,length=16)
	public java.lang.String getContractterm(){
		return this.contractterm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同期限
	 */
	public void setContractterm(java.lang.String contractterm){
		this.contractterm = contractterm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同总押金
	 */
	@Column(name ="CONTRACTSUMDEPOSIT",nullable=true,length=16)
	public java.lang.String getContractsumdeposit(){
		return this.contractsumdeposit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同总押金
	 */
	public void setContractsumdeposit(java.lang.String contractsumdeposit){
		this.contractsumdeposit = contractsumdeposit;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  合同生效日
	 */
	@Column(name ="CONTRACTVALIDDAY",nullable=true)
	public java.util.Date getContractvalidday(){
		return this.contractvalidday;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  合同生效日
	 */
	public void setContractvalidday(java.util.Date contractvalidday){
		this.contractvalidday = contractvalidday;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  合同终止日期
	 */
	@Column(name ="CONTRACTOVERDATE",nullable=true)
	public java.util.Date getContractoverdate(){
		return this.contractoverdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  合同终止日期
	 */
	public void setContractoverdate(java.util.Date contractoverdate){
		this.contractoverdate = contractoverdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  签订租金
	 */
	@Column(name ="PERMONTHRENT",nullable=true,length=8)
	public java.lang.String getPermonthrent(){
		return this.permonthrent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  签订租金
	 */
	public void setPermonthrent(java.lang.String permonthrent){
		this.permonthrent = permonthrent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  每月交租日
	 */
	@Column(name ="PERMONTHDAY",nullable=true,length=8)
	public java.lang.String getPermonthday(){
		return this.permonthday;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  每月交租日
	 */
	public void setPermonthday(java.lang.String permonthday){
		this.permonthday = permonthday;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车辆押金
	 */
	@Column(name ="CARDEPOSIT",nullable=true,length=18)
	public java.lang.String getCardeposit(){
		return this.cardeposit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车辆押金
	 */
	public void setCardeposit(java.lang.String cardeposit){
		this.cardeposit = cardeposit;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同扫描件上传
	 */
	@Column(name ="CONTRACTCOPYUPLOAD",nullable=true,length=128)
	public java.lang.String getContractcopyupload(){
		return this.contractcopyupload;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同扫描件上传
	 */
	public void setContractcopyupload(java.lang.String contractcopyupload){
		this.contractcopyupload = contractcopyupload;
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
	 *@return: java.lang.String  加入平台
	 */
	@Column(name ="PLATFORMTO",nullable=true,length=32)
	public java.lang.String getPlatformto(){
		return this.platformto;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  加入平台
	 */
	public void setPlatformto(java.lang.String platformto){
		this.platformto = platformto;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登录手机号
	 */
	@Column(name ="LOGINTEL",nullable=true,length=18)
	public java.lang.String getLogintel(){
		return this.logintel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登录手机号
	 */
	public void setLogintel(java.lang.String logintel){
		this.logintel = logintel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  来源
	 */
	@Column(name ="TOSOURCE",nullable=true,length=32)
	public java.lang.String getTosource(){
		return this.tosource;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  来源
	 */
	public void setTosource(java.lang.String tosource){
		this.tosource = tosource;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  推荐人
	 */
	@Column(name ="RECOMMENDED",nullable=true,length=16)
	public java.lang.String getRecommended(){
		return this.recommended;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  推荐人
	 */
	public void setRecommended(java.lang.String recommended){
		this.recommended = recommended;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  洽谈人
	 */
	@Column(name ="BUSIPEOPLE",nullable=true,length=16)
	public java.lang.String getBusipeople(){
		return this.busipeople;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  洽谈人
	 */
	public void setBusipeople(java.lang.String busipeople){
		this.busipeople = busipeople;
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
	@Column(name ="REMARKS",nullable=true,length=256)
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
	 *@return: java.lang.String  公司银行账号
	 */
	@Column(name ="COMPANYBANKACT",nullable=true,length=16)
	public java.lang.String getCompanybankact(){
		return this.companybankact;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司银行账号
	 */
	public void setCompanybankact(java.lang.String companybankact){
		this.companybankact = companybankact;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司银行开户行
	 */
	@Column(name ="COMPANYBANK",nullable=true,length=16)
	public java.lang.String getCompanybank(){
		return this.companybank;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司银行开户行
	 */
	public void setCompanybank(java.lang.String companybank){
		this.companybank = companybank;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工资卡银行账号
	 */
	@Column(name ="PAYROLLBANKACT",nullable=true,length=24)
	public java.lang.String getPayrollbankact(){
		return this.payrollbankact;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工资卡银行账号
	 */
	public void setPayrollbankact(java.lang.String payrollbankact){
		this.payrollbankact = payrollbankact;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工资卡开户行
	 */
	@Column(name ="PAYROLLBANK",nullable=true,length=24)
	public java.lang.String getPayrollbank(){
		return this.payrollbank;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工资卡开户行
	 */
	public void setPayrollbank(java.lang.String payrollbank){
		this.payrollbank = payrollbank;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  U盾号码
	 */
	@Column(name ="UDNUMBER",nullable=true,length=16)
	public java.lang.String getUdnumber(){
		return this.udnumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  U盾号码
	 */
	public void setUdnumber(java.lang.String udnumber){
		this.udnumber = udnumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  U盾用户名
	 */
	@Column(name ="UDUSERNAME",nullable=true,length=16)
	public java.lang.String getUdusername(){
		return this.udusername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  U盾用户名
	 */
	public void setUdusername(java.lang.String udusername){
		this.udusername = udusername;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  Ubername
	 */
	@Column(name ="UBERNAME",nullable=true,length=16)
	public java.lang.String getUbername(){
		return this.ubername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  Ubername
	 */
	public void setUbername(java.lang.String ubername){
		this.ubername = ubername;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  Uberpassword
	 */
	@Column(name ="UBERPASSWORD",nullable=true,length=16)
	public java.lang.String getUberpassword(){
		return this.uberpassword;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  Uberpassword
	 */
	public void setUberpassword(java.lang.String uberpassword){
		this.uberpassword = uberpassword;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  Uber上线状态
	 */
	@Column(name ="UBERONLINE",nullable=true,length=8)
	public java.lang.String getUberonline(){
		return this.uberonline;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  Uber上线状态
	 */
	public void setUberonline(java.lang.String uberonline){
		this.uberonline = uberonline;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  滴滴出行上线状态
	 */
	@Column(name ="DDONLINE",nullable=true,length=4)
	public java.lang.String getDdonline(){
		return this.ddonline;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  滴滴出行上线状态
	 */
	public void setDdonline(java.lang.String ddonline){
		this.ddonline = ddonline;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工资(合作/合同)方案
	 */
	@Column(name ="WAGEPLAN",nullable=true,length=32)
	public java.lang.String getWageplan(){
		return this.wageplan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工资(合作/合同)方案
	 */
	public void setWageplan(java.lang.String wageplan){
		this.wageplan = wageplan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  通途电卡卡号
	 */
	@Column(name ="TTCARD",nullable=true,length=16)
	public java.lang.String getTtcard(){
		return this.ttcard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  通途电卡卡号
	 */
	public void setTtcard(java.lang.String ttcard){
		this.ttcard = ttcard;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  BYD电卡编号
	 */
	@Column(name ="BYDCARD",nullable=true,length=16)
	public java.lang.String getBydcard(){
		return this.bydcard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  BYD电卡编号
	 */
	public void setBydcard(java.lang.String bydcard){
		this.bydcard = bydcard;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  普天电卡卡号
	 */
	@Column(name ="PTDKCARD",nullable=true,length=16)
	public java.lang.String getPtdkcard(){
		return this.ptdkcard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  普天电卡卡号
	 */
	public void setPtdkcard(java.lang.String ptdkcard){
		this.ptdkcard = ptdkcard;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  社保
	 */
	@Column(name ="SBCARD",nullable=true,length=16)
	public java.lang.String getSbcard(){
		return this.sbcard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  社保
	 */
	public void setSbcard(java.lang.String sbcard){
		this.sbcard = sbcard;
	}
}
