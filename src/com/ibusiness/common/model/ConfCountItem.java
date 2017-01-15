package com.ibusiness.common.model;
/**
 * 统计 count和name通用bean
 * 
 * @author huangziwang
 *
 */
public class ConfCountItem {
    // 统计数量
    private String count;
    // 统计名称
    private String name;
    // 总计
    private Integer sum;
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
}
