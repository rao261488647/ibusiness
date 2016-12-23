package com.ibusiness.workbook.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 *	工作日志 
 */
@Entity
@Table(name="IB_WORKLOG")
@GenericGenerator(name="idGenerator", strategy="uuid")
public class WorkBook implements java.io.Serializable {

	//序列号
	private String serialNo;
	//类型
	private String type;
	//天气
	private String weather;
	//用户编号
	private String userId;
	//任务修改日期
	private Date updateTime;
	//工作开始时间
	private String beginTime;
	//工作结束时间
	private String endTime;
	//名称
	private String title;
	//内容
	private String content;
	
	public WorkBook(){}
	
	public WorkBook(String content,String beginTime) {
		super();
		this.beginTime = beginTime;
		this.content = content;
	}



	@Id
	@GeneratedValue(generator="idGenerator")
	//@GeneratedValue
	@Column(name="SERIALNO")
	public String getSerialNo() {
		return serialNo;
	}
	public WorkBook setSerialNo(String serialNo) {
		this.serialNo = serialNo;
		return this;
	}
		
	@Column(name="TYPE")
	public String getType() {
		return type;
	}
	public WorkBook setType(String type) {
		this.type = type;
		return this;
	}
	
	@Column(name="WEATHER")
	public String getWeather() {
		return weather;
	}
	public WorkBook setWeather(String weather) {
		this.weather = weather;
		return this;
	}
	
	@Column(name="USERID")
	public String getUserId() {
		return userId;
	}
	public WorkBook setUserId(String userId) {
		this.userId = userId;
		return this;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATETIME")
	public Date getUpdateTime() {
		return updateTime;
	}
	public WorkBook setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	@Column(name="BEGINTIME")
	public String getBeginTime() {
		return beginTime;
	}
	public WorkBook setBeginTime(String beginTime) {
		this.beginTime = beginTime;
		return this;
	}
	
	@Column(name="ENDTIME")
	public String getEndTime() {
		return endTime;
	}
	public WorkBook setEndTime(String endTime) {
		this.endTime = endTime;
		return this;
	}
	
	@Column(name="TITLE")
	public String getTitle() {
		return title;
	}
	public WorkBook setTitle(String title) {
		this.title = title;
		return this;
	}
	
	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}
	public WorkBook setContent(String content) {
		this.content = content;
		return this;
	}	
}
