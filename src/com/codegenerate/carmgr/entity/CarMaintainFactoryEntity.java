package com.codegenerate.carmgr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @Title: Entity
 * @Description: 维修厂管理
 * @author huangziwang
 *
 */
@Entity
@Table(name = "IB_CAR_MAINTAIN_FACTORY")
public class CarMaintainFactoryEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**UUID主键*/
	private java.lang.String id;

	private String maintainname;
	private String type;
	private String address;
	private String phone;
	private String linkman;
	private String linkmanphone;
	private String maintainproject;
	private String remark;
	private String createdate;
	private String updatedate;
	private String creator;
	private String updater;
	@Id
	@Column(name ="ID",nullable=false,length=64)
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public String getMaintainname() {
		return maintainname;
	}
	@Column(name ="MAINTAINNAME",nullable=true,length=256)
	public void setMaintainname(String maintainname) {
		this.maintainname = maintainname;
	}
	public String getType() {
		return type;
	}
	@Column(name ="TYPE",nullable=true,length=256)
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	@Column(name ="ADDRESS",nullable=true,length=256)
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	@Column(name ="PHONE",nullable=true,length=256)
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLinkman() {
		return linkman;
	}
	@Column(name ="LINKMAN",nullable=true,length=256)
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getLinkmanphone() {
		return linkmanphone;
	}
	@Column(name ="LINKMANPHONE",nullable=true,length=256)
	public void setLinkmanphone(String linkmanphone) {
		this.linkmanphone = linkmanphone;
	}
	public String getMaintainproject() {
		return maintainproject;
	}
	@Column(name ="MAINTAINPROJECT",nullable=true,length=256)
	public void setMaintainproject(String maintainproject) {
		this.maintainproject = maintainproject;
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

}
