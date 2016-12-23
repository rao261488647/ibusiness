package com.webservice.alipay;

import java.math.BigDecimal;

public class T {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println(new Pay().pay("2015041304241522", "测试111",
		// "200"));
		BigDecimal bd = new BigDecimal("3");
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println(bd);
	}

}
