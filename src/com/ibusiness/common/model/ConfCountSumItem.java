package com.ibusiness.common.model;

import java.util.List;

/**
 * 统计单列总数
 * 
 * @author huangziwang
 *
 */
public class ConfCountSumItem {
    private List<ConfCountItem> list;
    // 总计
    private Integer sum=0;
	public List<ConfCountItem> getList() {
		return list;
	}
	public void setList(List<ConfCountItem> list) {
		this.list = list;
		// 总计
		for (ConfCountItem confCountItem : list) {
			this.sum+=Integer.valueOf(confCountItem.getCount());
		}
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
    
}
