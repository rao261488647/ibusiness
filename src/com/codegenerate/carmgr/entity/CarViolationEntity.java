package com.codegenerate.carmgr.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @Title: Entity
 * @Description: 车辆违章录入
 * @author huangziwang
 *
 */
@Entity
@Table(name = "IB_CAR_VIOLATION")
public class CarViolationEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**UUID主键*/
	private java.lang.String id;

	private String remark;
	private String createdate;
	private String updatedate;
	private String creator;
	private String updater;
	
	
	private String carid;
	private Date violationdate;
	private String violationtype;
	private String violationproject;
	private String violationaddress;
	private String points;
	private String finemoney;
	private String isdispose;
	private Date disposedate;
	
	private String carnum;
	private String typename;
	@Id
	@Column(name ="ID",nullable=false,length=64)
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	
	public String getRemark() {
		return remark;
	}
	@Column(name ="REMARK",nullable=true,length=256)
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreatedate() {
		return createdate;
	}
	@Column(name ="CREATEDATE",nullable=true,length=256)
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	@Column(name ="UPDATEDATE",nullable=true,length=256)
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getCreator() {
		return creator;
	}
	@Column(name ="CREATOR",nullable=true,length=256)
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdater() {
		return updater;
	}
	@Column(name ="UPDATER",nullable=true,length=256)
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCarid() {
		return carid;
	}
	@Column(name ="CARID",nullable=true,length=256)
	public void setCarid(String carid) {
		this.carid = carid;
	}
	public Date getViolationdate() {
		return violationdate;
	}
	@Column(name ="VIOLATIONDATE",nullable=true,length=256)
	public void setViolationdate(Date violationdate) {
		this.violationdate = violationdate;
	}
	public String getViolationtype() {
		return violationtype;
	}
	@Column(name ="VIOLATIONDATE",nullable=true,length=256)
	public void setViolationtype(String violationtype) {
		this.violationtype = violationtype;
	}
	public String getViolationproject() {
		return violationproject;
	}
	@Column(name ="VIOLATIONPROJECT",nullable=true,length=256)
	public void setViolationproject(String violationproject) {
		this.violationproject = violationproject;
	}
	public String getViolationaddress() {
		return violationaddress;
	}
	@Column(name ="VIOLATIONADDRESS",nullable=true,length=256)
	public void setViolationaddress(String violationaddress) {
		this.violationaddress = violationaddress;
	}
	public String getPoints() {
		return points;
	}
	@Column(name ="POINTS",nullable=true,length=256)
	public void setPoints(String points) {
		this.points = points;
	}
	public String getFinemoney() {
		return finemoney;
	}
	@Column(name ="FINEMONEY",nullable=true,length=256)
	public void setFinemoney(String finemoney) {
		this.finemoney = finemoney;
	}
	public String getIsdispose() {
		return isdispose;
	}
	@Column(name ="ISDISPOSE",nullable=true,length=256)
	public void setIsdispose(String isdispose) {
		this.isdispose = isdispose;
	}
	public Date getDisposedate() {
		return disposedate;
	}
	@Column(name ="DISPOSEDATE")
	public void setDisposedate(Date disposedate) {
		this.disposedate = disposedate;
	}
	public String getCarnum() {
		return carnum;
	}
	@Column(name ="CARNUM")
	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}
	public String getTypename() {
		return typename;
	}
	@Column(name ="TYPENAME")
	public void setTypename(String typename) {
		this.typename = typename;
	}
}
