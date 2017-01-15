package com.codegenerate.carmgr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**   
 * @Title: Entity
 * @Description: 维修保养录入
 * @author huangziwang
 *
 */
@Entity
@Table(name = "IB_CAR_MAINTAIN_INPUT")
public class CarMaintainInputEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**UUID主键*/
	private java.lang.String id;

	private String remark;
	private String createdate;
	private String updatedate;
	private String creator;
	private String updater;
	
	private String carid;
	private java.util.Date enterfactorydate;
	private String enterfactorytype;
	private String maintainid;
	private String maintaincontent;
	private String isreplacecar;
	private java.util.Date leavefactorydate;
	
	private String carnum;
	private String typename;
	
	private String maintainname;
	
	private String maintainstatus;
	
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
	public java.util.Date getEnterfactorydate() {
		return enterfactorydate;
	}
	@Column(name ="ENTERFACTORYDATE",nullable=true,length=256)
	public void setEnterfactorydate(java.util.Date enterfactorydate) {
		this.enterfactorydate = enterfactorydate;
	}
	public String getEnterfactorytype() {
		return enterfactorytype;
	}
	@Column(name ="ENTERFACTORYTYPE",nullable=true,length=256)
	public void setEnterfactorytype(String enterfactorytype) {
		this.enterfactorytype = enterfactorytype;
	}
	public String getMaintainid() {
		return maintainid;
	}
	@Column(name ="MAINTAINID",nullable=true,length=256)
	public void setMaintainid(String maintainid) {
		this.maintainid = maintainid;
	}
	public String getMaintaincontent() {
		return maintaincontent;
	}
	@Column(name ="MAINTAINCONTENT",nullable=true,length=256)
	public void setMaintaincontent(String maintaincontent) {
		this.maintaincontent = maintaincontent;
	}
	public String getIsreplacecar() {
		return isreplacecar;
	}
	@Column(name ="ISREPLACECAR",nullable=true,length=256)
	public void setIsreplacecar(String isreplacecar) {
		this.isreplacecar = isreplacecar;
	}
	public java.util.Date getLeavefactorydate() {
		return leavefactorydate;
	}
	@Column(name ="LEAVEFACTORYDATE",nullable=true,length=256)
	public void setLeavefactorydate(java.util.Date leavefactorydate) {
		this.leavefactorydate = leavefactorydate;
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
	@Transient
	public String getMaintainname() {
		return maintainname;
	}
	public void setMaintainname(String maintainname) {
		this.maintainname = maintainname;
	}
	public String getMaintainstatus() {
		return maintainstatus;
	}
	@Column(name ="MAINTAINSTATUS",nullable=true,length=256)
	public void setMaintainstatus(String maintainstatus) {
		this.maintainstatus = maintainstatus;
	}

}
