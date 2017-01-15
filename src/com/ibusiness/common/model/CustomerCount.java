package com.ibusiness.common.model;

import java.util.List;
import java.util.Map;

/** 来店客户统计分析
 * @author Administrator
 *
 */
public class CustomerCount {
	private String id;
	private String name;

	private String count1 = "0";
	private String count2 = "0";
	private String count3 = "0";
	private String count4 = "0";
	private String count5 = "0";
	private String count6 = "0";
	private String count7 = "0";
	private String count8 = "0";
	private String count9 = "0";
	private String count10 = "0";
	private String count11 = "0";
	private String count12 = "0";
	
	public static void getValue (List<CustomerCount> customerCountList,List<Map<String,Object> > list,int type) {
		for (CustomerCount customerCount :customerCountList) {
			for (Map<String,Object> map :list) {
				if (customerCount.getName().equals(map.get("name"))) {
					if (map.get("count") != null) {
						switch (type) {
						case 1:
							customerCount.setCount1(map.get("count").toString());
							break;
						case 2:
							customerCount.setCount2(map.get("count").toString());
							break;
						case 3:
							customerCount.setCount3(map.get("count").toString());
							break;
						case 4:
							customerCount.setCount4(map.get("count").toString());
							break;
						case 5:
							customerCount.setCount5(map.get("count").toString());
							break;
						case 6:
							customerCount.setCount6(map.get("count").toString());
							break;
						case 7:
							customerCount.setCount7(map.get("count").toString());
							break;
						case 8:
							customerCount.setCount8(map.get("count").toString());
							break;
						case 9:
							customerCount.setCount9(map.get("count").toString());
							break;
						case 10:
							customerCount.setCount10(map.get("count").toString());
							break;
						case 11:
							customerCount.setCount11(map.get("count").toString());
							break;
						case 12:
							customerCount.setCount12(map.get("count").toString());
							break;

						default:
							break;
						}
					}
				}
			}
		}
	}
	
	public String getCount1() {
		return count1;
	}
	public void setCount1(String count1) {
		this.count1 = count1;
	}
	public String getCount2() {
		return count2;
	}
	public void setCount2(String count2) {
		this.count2 = count2;
	}
	public String getCount3() {
		return count3;
	}
	public void setCount3(String count3) {
		this.count3 = count3;
	}
	public String getCount4() {
		return count4;
	}
	public void setCount4(String count4) {
		this.count4 = count4;
	}
	public String getCount5() {
		return count5;
	}
	public void setCount5(String count5) {
		this.count5 = count5;
	}
	public String getCount6() {
		return count6;
	}
	public void setCount6(String count6) {
		this.count6 = count6;
	}
	public String getCount7() {
		return count7;
	}
	public void setCount7(String count7) {
		this.count7 = count7;
	}
	public String getCount8() {
		return count8;
	}
	public void setCount8(String count8) {
		this.count8 = count8;
	}
	public String getCount9() {
		return count9;
	}
	public void setCount9(String count9) {
		this.count9 = count9;
	}
	public String getCount10() {
		return count10;
	}
	public void setCount10(String count10) {
		this.count10 = count10;
	}
	public String getCount11() {
		return count11;
	}
	public void setCount11(String count11) {
		this.count11 = count11;
	}
	public String getCount12() {
		return count12;
	}
	public void setCount12(String count12) {
		this.count12 = count12;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
