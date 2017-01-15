package com.codegenerate.carmgr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**   
 * @Title: Entity
 * @Description: 车辆借出管理
 * @author huangziwang
 *
 */
@Entity
@Table(name = "IB_CAR_LOAN")
public class CarLoanEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**UUID主键*/
	private java.lang.String id;

	private String remark;
	private String createdate;
	private String updatedate;
	private String creator;
	private String updater;
	
	
	private java.util.Date date;
	private String specialid;
	private String cartype;
	private String carid;
	private java.util.Date loandate;
	private String status;
	private java.util.Date backdate;
	private String carnum;
	private String typename;
	
	private String specialname;
	
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
	public java.util.Date getDate() {
		return date;
	}
	@Column(name ="DATE",nullable=true,length=256)
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public String getSpecialid() {
		return specialid;
	}
	@Column(name ="SPECIALID",nullable=true,length=256)
	public void setSpecialid(String specialid) {
		this.specialid = specialid;
	}
	public String getCartype() {
		return cartype;
	}
	@Column(name ="CARTYPE",nullable=true,length=256)
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	public String getCarid() {
		return carid;
	}
	@Column(name ="CARID",nullable=true,length=256)
	public void setCarid(String carid) {
		this.carid = carid;
	}
	public java.util.Date getLoandate() {
		return loandate;
	}
	@Column(name ="LOANDATE",nullable=true,length=256)
	public void setLoandate(java.util.Date loandate) {
		this.loandate = loandate;
	}
	public String getStatus() {
		return status;
	}
	@Column(name ="STATUS",nullable=true,length=256)
	public void setStatus(String status) {
		this.status = status;
	}
	public java.util.Date getBackdate() {
		return backdate;
	}
	@Column(name ="BACKDATE",nullable=true,length=256)
	public void setBackdate(java.util.Date backdate) {
		this.backdate = backdate;
	}
	public String getCarnum() {
		return carnum;
	}
	@Column(name ="CARNUM",nullable=true,length=256)
	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}
	public String getTypename() {
		return typename;
	}
	@Column(name ="TYPENAME",nullable=true,length=256)
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getSpecialname() {
		return specialname;
	}
	@Column(name ="SPECIALNAME",nullable=true,length=256)
	public void setSpecialname(String specialname) {
		this.specialname = specialname;
	}
	
	

}
