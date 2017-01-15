package com.codegenerate.financemgr.entity;

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
 * @Description: 工资方案页面
 * @author JiangBo
 *
 */
@Entity
@Table(name = "IB_WAGE")
public class WageEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**范围*/
	private java.lang.String scopeid;
	/**UUID主键*/
	private java.lang.String id;
	/**姓名*/
	private java.lang.String name;
	/**工资方案*/
	private java.lang.String wagescheme;
	/**车型*/
	private java.lang.String carname;
	/**车牌号码*/
	private java.lang.String carnum;
	/**提车日期*/
	private java.util.Date getcardate;
	/**提车天数*/
	private java.lang.String getcardays;
	/**月初公里数*/
	private java.lang.String initialkm;
	/**月末公里数*/
	private java.lang.String endmonthkm;
	/**当月第一周营业额*/
	private java.lang.String turnover1;
	/**当月第一周路费*/
	private java.lang.String expenses1;
	/**当月第2周营业额*/
	private java.lang.String turnover2;
	/**当月第2周路费*/
	private java.lang.String expenses2;
	/**当月第3周营业额*/
	private java.lang.String turnover3;
	/**当月第3周路费*/
	private java.lang.String expenses3;
	/**当月第4周营业额*/
	private java.lang.String turnover4;
	/**当月第4周路费*/
	private java.lang.String expenses4;
	/**当月第5周营业额*/
	private java.lang.String turnover5;
	/**当月第5周路费*/
	private java.lang.String expenses5;
	/**机场单金额*/
	private java.lang.String airportamount;
	/**总营业额*/
	private java.lang.String totalturnover;
	/**营业额评分*/
	private java.lang.String turnoverscore;
	/**服务评分*/
	private java.lang.String servicescore;
	/**安全评分*/
	private java.lang.String safetyscore;
	/**配合度评分*/
	private java.lang.String coordscore;
	/**转介绍评分*/
	private java.lang.String referralscore;
	/**超额业绩评分*/
	private java.lang.String excessscore;
	/**评分合计*/
	private java.lang.String totalscore;
	/**DD总营业额*/
	private java.lang.String ddturnover;
	/**UBer总实际营业额*/
	private java.lang.String uberturnover;
	/**去哪儿网总营业额*/
	private java.lang.String qnwturnover;
	/**月总路费*/
	private java.lang.String monthexpenses;
	/**去哪儿网爽约补贴金额*/
	private java.lang.String qnwsubsidyamount;
	/**应发工资*/
	private java.lang.String shouldpaid;
	/**联通流量卡套餐费*/
	private java.lang.String ltcard;
	/**司机住宿和水电费用*/
	private java.lang.String utilitiescosts;
	/**BYD电卡金额*/
	private java.lang.String bydelectriccard;
	/**通途电卡金额*/
	private java.lang.String ttelectriccard;
	/**水木华程电卡金额*/
	private java.lang.String smhcelectriccard;
	/**出险扣款*/
	private java.lang.String insurancefee;
	/**月社保扣款*/
	private java.lang.String monthsocial;
	/**月借款金额*/
	private java.lang.String monthloan;
	/**违章扣款*/
	private java.lang.String illegalfee;
	/**去哪儿网罚款金额*/
	private java.lang.String qnwfine;
	/**其他费用*/
	private java.lang.String otherexpenses;
	/**应扣电费*/
	private java.lang.String deductedele;
	/**应扣费用合计*/
	private java.lang.String totaldeducted;
	/**电费补贴*/
	private java.lang.String elesubsidies;
	/**实发工资*/
	private java.lang.String realwage;
	/**银行账号*/
	private java.lang.String bankaccount;
	/**开户银行及所在支行*/
	private java.lang.String bank;
	/**备注*/
	private java.lang.String remark;
	/**工资年月*/
	private java.util.Date eventdate;
	
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
	 *@return: java.lang.String  工资方案
	 */
	@Column(name ="WAGESCHEME",nullable=true,length=8)
	public java.lang.String getWagescheme(){
		return this.wagescheme;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工资方案
	 */
	public void setWagescheme(java.lang.String wagescheme){
		this.wagescheme = wagescheme;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车型
	 */
	@Column(name ="CARNAME",nullable=true,length=32)
	public java.lang.String getCarname(){
		return this.carname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车型
	 */
	public void setCarname(java.lang.String carname){
		this.carname = carname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车牌号码
	 */
	@Column(name ="CARNUM",nullable=true,length=10)
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  提车日期
	 */
	@Column(name ="GETCARDATE",nullable=true)
	public java.util.Date getGetcardate(){
		return this.getcardate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  提车日期
	 */
	public void setGetcardate(java.util.Date getcardate){
		this.getcardate = getcardate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  提车天数
	 */
	@Column(name ="GETCARDAYS",nullable=true,length=8)
	public java.lang.String getGetcardays(){
		return this.getcardays;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提车天数
	 */
	public void setGetcardays(java.lang.String getcardays){
		this.getcardays = getcardays;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  月初公里数
	 */
	@Column(name ="INITIALKM",nullable=true,length=16)
	public java.lang.String getInitialkm(){
		return this.initialkm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  月初公里数
	 */
	public void setInitialkm(java.lang.String initialkm){
		this.initialkm = initialkm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  月末公里数
	 */
	@Column(name ="ENDMONTHKM",nullable=true,length=16)
	public java.lang.String getEndmonthkm(){
		return this.endmonthkm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  月末公里数
	 */
	public void setEndmonthkm(java.lang.String endmonthkm){
		this.endmonthkm = endmonthkm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  当月第一周营业额
	 */
	@Column(name ="TURNOVER1",nullable=true,length=16)
	public java.lang.String getTurnover1(){
		return this.turnover1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当月第一周营业额
	 */
	public void setTurnover1(java.lang.String turnover1){
		this.turnover1 = turnover1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  当月第一周路费
	 */
	@Column(name ="EXPENSES1",nullable=true,length=16)
	public java.lang.String getExpenses1(){
		return this.expenses1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当月第一周路费
	 */
	public void setExpenses1(java.lang.String expenses1){
		this.expenses1 = expenses1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  当月第2周营业额
	 */
	@Column(name ="TURNOVER2",nullable=true,length=16)
	public java.lang.String getTurnover2(){
		return this.turnover2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当月第2周营业额
	 */
	public void setTurnover2(java.lang.String turnover2){
		this.turnover2 = turnover2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  当月第2周路费
	 */
	@Column(name ="EXPENSES2",nullable=true,length=16)
	public java.lang.String getExpenses2(){
		return this.expenses2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当月第2周路费
	 */
	public void setExpenses2(java.lang.String expenses2){
		this.expenses2 = expenses2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  当月第3周营业额
	 */
	@Column(name ="TURNOVER3",nullable=true,length=16)
	public java.lang.String getTurnover3(){
		return this.turnover3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当月第3周营业额
	 */
	public void setTurnover3(java.lang.String turnover3){
		this.turnover3 = turnover3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  当月第3周路费
	 */
	@Column(name ="EXPENSES3",nullable=true,length=16)
	public java.lang.String getExpenses3(){
		return this.expenses3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当月第3周路费
	 */
	public void setExpenses3(java.lang.String expenses3){
		this.expenses3 = expenses3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  当月第4周营业额
	 */
	@Column(name ="TURNOVER4",nullable=true,length=16)
	public java.lang.String getTurnover4(){
		return this.turnover4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当月第4周营业额
	 */
	public void setTurnover4(java.lang.String turnover4){
		this.turnover4 = turnover4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  当月第4周路费
	 */
	@Column(name ="EXPENSES4",nullable=true,length=16)
	public java.lang.String getExpenses4(){
		return this.expenses4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当月第4周路费
	 */
	public void setExpenses4(java.lang.String expenses4){
		this.expenses4 = expenses4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  当月第5周营业额
	 */
	@Column(name ="TURNOVER5",nullable=true,length=16)
	public java.lang.String getTurnover5(){
		return this.turnover5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当月第5周营业额
	 */
	public void setTurnover5(java.lang.String turnover5){
		this.turnover5 = turnover5;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  当月第5周路费
	 */
	@Column(name ="EXPENSES5",nullable=true,length=16)
	public java.lang.String getExpenses5(){
		return this.expenses5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当月第5周路费
	 */
	public void setExpenses5(java.lang.String expenses5){
		this.expenses5 = expenses5;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机场单金额
	 */
	@Column(name ="AIRPORTAMOUNT",nullable=true,length=16)
	public java.lang.String getAirportamount(){
		return this.airportamount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机场单金额
	 */
	public void setAirportamount(java.lang.String airportamount){
		this.airportamount = airportamount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  总营业额
	 */
	@Column(name ="TOTALTURNOVER",nullable=true,length=16)
	public java.lang.String getTotalturnover(){
		return this.totalturnover;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  总营业额
	 */
	public void setTotalturnover(java.lang.String totalturnover){
		this.totalturnover = totalturnover;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  营业额评分
	 */
	@Column(name ="TURNOVERSCORE",nullable=true,length=8)
	public java.lang.String getTurnoverscore(){
		return this.turnoverscore;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  营业额评分
	 */
	public void setTurnoverscore(java.lang.String turnoverscore){
		this.turnoverscore = turnoverscore;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务评分
	 */
	@Column(name ="SERVICESCORE",nullable=true,length=8)
	public java.lang.String getServicescore(){
		return this.servicescore;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务评分
	 */
	public void setServicescore(java.lang.String servicescore){
		this.servicescore = servicescore;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  安全评分
	 */
	@Column(name ="SAFETYSCORE",nullable=true,length=8)
	public java.lang.String getSafetyscore(){
		return this.safetyscore;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  安全评分
	 */
	public void setSafetyscore(java.lang.String safetyscore){
		this.safetyscore = safetyscore;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  配合度评分
	 */
	@Column(name ="COORDSCORE",nullable=true,length=8)
	public java.lang.String getCoordscore(){
		return this.coordscore;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  配合度评分
	 */
	public void setCoordscore(java.lang.String coordscore){
		this.coordscore = coordscore;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  转介绍评分
	 */
	@Column(name ="REFERRALSCORE",nullable=true,length=8)
	public java.lang.String getReferralscore(){
		return this.referralscore;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  转介绍评分
	 */
	public void setReferralscore(java.lang.String referralscore){
		this.referralscore = referralscore;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  超额业绩评分
	 */
	@Column(name ="EXCESSSCORE",nullable=true,length=8)
	public java.lang.String getExcessscore(){
		return this.excessscore;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  超额业绩评分
	 */
	public void setExcessscore(java.lang.String excessscore){
		this.excessscore = excessscore;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  评分合计
	 */
	@Column(name ="TOTALSCORE",nullable=true,length=8)
	public java.lang.String getTotalscore(){
		return this.totalscore;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  评分合计
	 */
	public void setTotalscore(java.lang.String totalscore){
		this.totalscore = totalscore;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  DD总营业额
	 */
	@Column(name ="DDTURNOVER",nullable=true,length=16)
	public java.lang.String getDdturnover(){
		return this.ddturnover;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  DD总营业额
	 */
	public void setDdturnover(java.lang.String ddturnover){
		this.ddturnover = ddturnover;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  UBer总实际营业额
	 */
	@Column(name ="UBERTURNOVER",nullable=true,length=16)
	public java.lang.String getUberturnover(){
		return this.uberturnover;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  UBer总实际营业额
	 */
	public void setUberturnover(java.lang.String uberturnover){
		this.uberturnover = uberturnover;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  去哪儿网总营业额
	 */
	@Column(name ="QNWTURNOVER",nullable=true,length=16)
	public java.lang.String getQnwturnover(){
		return this.qnwturnover;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  去哪儿网总营业额
	 */
	public void setQnwturnover(java.lang.String qnwturnover){
		this.qnwturnover = qnwturnover;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  月总路费
	 */
	@Column(name ="MONTHEXPENSES",nullable=true,length=16)
	public java.lang.String getMonthexpenses(){
		return this.monthexpenses;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  月总路费
	 */
	public void setMonthexpenses(java.lang.String monthexpenses){
		this.monthexpenses = monthexpenses;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  去哪儿网爽约补贴金额
	 */
	@Column(name ="QNWSUBSIDYAMOUNT",nullable=true,length=16)
	public java.lang.String getQnwsubsidyamount(){
		return this.qnwsubsidyamount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  去哪儿网爽约补贴金额
	 */
	public void setQnwsubsidyamount(java.lang.String qnwsubsidyamount){
		this.qnwsubsidyamount = qnwsubsidyamount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  应发工资
	 */
	@Column(name ="SHOULDPAID",nullable=true,length=16)
	public java.lang.String getShouldpaid(){
		return this.shouldpaid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  应发工资
	 */
	public void setShouldpaid(java.lang.String shouldpaid){
		this.shouldpaid = shouldpaid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联通流量卡套餐费
	 */
	@Column(name ="LTCARD",nullable=true,length=16)
	public java.lang.String getLtcard(){
		return this.ltcard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联通流量卡套餐费
	 */
	public void setLtcard(java.lang.String ltcard){
		this.ltcard = ltcard;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司机住宿和水电费用
	 */
	@Column(name ="UTILITIESCOSTS",nullable=true,length=16)
	public java.lang.String getUtilitiescosts(){
		return this.utilitiescosts;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  司机住宿和水电费用
	 */
	public void setUtilitiescosts(java.lang.String utilitiescosts){
		this.utilitiescosts = utilitiescosts;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  BYD电卡金额
	 */
	@Column(name ="BYDELECTRICCARD",nullable=true,length=16)
	public java.lang.String getBydelectriccard(){
		return this.bydelectriccard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  BYD电卡金额
	 */
	public void setBydelectriccard(java.lang.String bydelectriccard){
		this.bydelectriccard = bydelectriccard;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  通途电卡金额
	 */
	@Column(name ="TTELECTRICCARD",nullable=true,length=16)
	public java.lang.String getTtelectriccard(){
		return this.ttelectriccard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  通途电卡金额
	 */
	public void setTtelectriccard(java.lang.String ttelectriccard){
		this.ttelectriccard = ttelectriccard;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  水木华程电卡金额
	 */
	@Column(name ="SMHCELECTRICCARD",nullable=true,length=16)
	public java.lang.String getSmhcelectriccard(){
		return this.smhcelectriccard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  水木华程电卡金额
	 */
	public void setSmhcelectriccard(java.lang.String smhcelectriccard){
		this.smhcelectriccard = smhcelectriccard;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出险扣款
	 */
	@Column(name ="INSURANCEFEE",nullable=true,length=16)
	public java.lang.String getInsurancefee(){
		return this.insurancefee;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出险扣款
	 */
	public void setInsurancefee(java.lang.String insurancefee){
		this.insurancefee = insurancefee;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  月社保扣款
	 */
	@Column(name ="MONTHSOCIAL",nullable=true,length=16)
	public java.lang.String getMonthsocial(){
		return this.monthsocial;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  月社保扣款
	 */
	public void setMonthsocial(java.lang.String monthsocial){
		this.monthsocial = monthsocial;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  月借款金额
	 */
	@Column(name ="MONTHLOAN",nullable=true,length=16)
	public java.lang.String getMonthloan(){
		return this.monthloan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  月借款金额
	 */
	public void setMonthloan(java.lang.String monthloan){
		this.monthloan = monthloan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  违章扣款
	 */
	@Column(name ="ILLEGALFEE",nullable=true,length=16)
	public java.lang.String getIllegalfee(){
		return this.illegalfee;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  违章扣款
	 */
	public void setIllegalfee(java.lang.String illegalfee){
		this.illegalfee = illegalfee;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  去哪儿网罚款金额
	 */
	@Column(name ="QNWFINE",nullable=true,length=16)
	public java.lang.String getQnwfine(){
		return this.qnwfine;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  去哪儿网罚款金额
	 */
	public void setQnwfine(java.lang.String qnwfine){
		this.qnwfine = qnwfine;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  其他费用
	 */
	@Column(name ="OTHEREXPENSES",nullable=true,length=16)
	public java.lang.String getOtherexpenses(){
		return this.otherexpenses;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  其他费用
	 */
	public void setOtherexpenses(java.lang.String otherexpenses){
		this.otherexpenses = otherexpenses;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  应扣电费
	 */
	@Column(name ="DEDUCTEDELE",nullable=true,length=16)
	public java.lang.String getDeductedele(){
		return this.deductedele;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  应扣电费
	 */
	public void setDeductedele(java.lang.String deductedele){
		this.deductedele = deductedele;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  应扣费用合计
	 */
	@Column(name ="TOTALDEDUCTED",nullable=true,length=16)
	public java.lang.String getTotaldeducted(){
		return this.totaldeducted;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  应扣费用合计
	 */
	public void setTotaldeducted(java.lang.String totaldeducted){
		this.totaldeducted = totaldeducted;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电费补贴
	 */
	@Column(name ="ELESUBSIDIES",nullable=true,length=16)
	public java.lang.String getElesubsidies(){
		return this.elesubsidies;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电费补贴
	 */
	public void setElesubsidies(java.lang.String elesubsidies){
		this.elesubsidies = elesubsidies;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实发工资
	 */
	@Column(name ="REALWAGE",nullable=true,length=16)
	public java.lang.String getRealwage(){
		return this.realwage;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实发工资
	 */
	public void setRealwage(java.lang.String realwage){
		this.realwage = realwage;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  银行账号
	 */
	@Column(name ="BANKACCOUNT",nullable=true,length=24)
	public java.lang.String getBankaccount(){
		return this.bankaccount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行账号
	 */
	public void setBankaccount(java.lang.String bankaccount){
		this.bankaccount = bankaccount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开户银行及所在支行
	 */
	@Column(name ="BANK",nullable=true,length=32)
	public java.lang.String getBank(){
		return this.bank;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开户银行及所在支行
	 */
	public void setBank(java.lang.String bank){
		this.bank = bank;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=128)
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  工资年月
	 */
	@Column(name ="EVENTDATE",nullable=true)
	public java.util.Date getEventdate(){
		return this.eventdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  工资年月
	 */
	public void setEventdate(java.util.Date eventdate){
		this.eventdate = eventdate;
	}
}
