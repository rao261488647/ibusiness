package com.ibusiness.boss.entity;

import java.util.Date;

/**   
 * 生产进度曲线用Bean
 * @author JiangBo
 *
 */
public class ProduceScheduleChart implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
    // 时间
    private Date eventTime;
    // 项目ID
    private String produceId;
    // 生产数量
    private String produceNum;
    // 计划生产数
	private String planNum;
	// 产品名
    private String productName;
    // 总金额
    private String totalAmount;
    /**
     * @return the eventTime
     */
    public Date getEventTime() {
        return eventTime;
    }
    /**
     * @param eventTime the eventTime to set
     */
    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }
    /**
     * @return the produceId
     */
    public String getProduceId() {
        return produceId;
    }
    /**
     * @param produceId the produceId to set
     */
    public void setProduceId(String produceId) {
        this.produceId = produceId;
    }
    /**
     * @return the produceNum
     */
    public String getProduceNum() {
        return produceNum;
    }
    /**
     * @param produceNum the produceNum to set
     */
    public void setProduceNum(String produceNum) {
        this.produceNum = produceNum;
    }
    /**
     * @return the planNum
     */
    public String getPlanNum() {
        return planNum;
    }
    /**
     * @param planNum the planNum to set
     */
    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
}
