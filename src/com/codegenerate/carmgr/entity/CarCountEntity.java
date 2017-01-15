package com.codegenerate.carmgr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @Title: Entity
 * @Description: 车辆库存统计
 * @author huangziwang
 *
 */
@Entity
@Table(name = "IB_CAR_COUNT")
public class CarCountEntity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	/**UUID主键*/
	private java.lang.String id;

	private String createdate;
	private String updatedate;
	private String creator;
	private String updater;
	
	
	private String typename;
	private String count;
	private String date;
	private String type;
	
	private String cartypeid;
	
	@Id
	@Column(name ="ID",nullable=false,length=64)
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
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
	public String getTypename() {
		return typename;
	}
	@Column(name ="TYPENAME")
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getCount() {
		return count;
	}
	@Column(name ="COUNT")
	public void setCount(String count) {
		this.count = count;
	}
	public String getDate() {
		return date;
	}
	@Column(name ="DATE")
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	@Column(name ="TYPE")
	public void setType(String type) {
		this.type = type;
	}
	public String getCartypeid() {
		return cartypeid;
	}
	@Column(name ="CARTYPEID")
	public void setCartypeid(String cartypeid) {
		this.cartypeid = cartypeid;
	}
	
	
}
