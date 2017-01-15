package com.report.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibusiness.common.model.ConfCountItem;
import com.ibusiness.common.model.ConfCountSumItem;
import com.ibusiness.common.model.CustomerCount;
import com.ibusiness.common.service.CommonBaseService;
import com.ibusiness.core.spring.ApplicationContextHelper;

public class SqlFormat {
	public static String selectDateFormat = "%Y-%m-%d";
	
	public static String selectDateFormat1 = "%Y-%m";
	
	/** 首页数据
	 * @param date 日期
	 * @return
	 */
	public static CustomerCount getBySql1_1 (String date) {
		String startDate = DateUtil.convertWeekByMonday(date);
		String endDate = DateUtil.convertWeekBySunday(date);
		// 昨天日期
		String yesterDay = DateUtil.getYesterDay();
		
		// 获取本月
		String month = DateUtil.toString(new Date(), "yyyy-MM");
		String sql1 = null;
		String sql2 = null;
		String sql3 = null;
		String sql4 = null;
		String sql5 = null;
		String sql6 = null;
		String sql7 = null;
		String sql8 = null;
		String sql9 = null;
		String sql10 = null;
		String sql11 = null;
		String sql12 = null;
		
		// 昨日司机提车数(合同生效的司机数)
		sql1 = SqlFormat.getSql3_1(null, null, selectDateFormat,yesterDay, yesterDay, null, null,false,null);
		// 当日司机提车数(合同生效的司机数)
		sql2 = SqlFormat.getSql3_1(null, null, selectDateFormat,date, date, null, null,false,null);
		// 当周司机提车数(合同生效的司机数)
		sql3 = SqlFormat.getSql3_1(null, null, selectDateFormat,startDate, endDate, null, null,false,null);
		// 当月司机提车数(合同生效的司机数)
		sql4 = SqlFormat.getSql3_1(null, null, selectDateFormat1,month, month, null, null,false,null);
		
		// 昨日来店司机数
		sql5 = getSql1(selectDateFormat, null, yesterDay, yesterDay, null, null);
		// 今日来店司机数
		sql6 = getSql1(selectDateFormat, null, date, date, null, null);
		// 本周来店司机数
		sql7 = getSql1(selectDateFormat, null, startDate, endDate, null, null);
		// 本月来店司机数
		sql8 = getSql1(selectDateFormat1, null, month, month, null, null);
		
		// 昨日签约司机数
		sql9 = getSql1(selectDateFormat, "已签约", yesterDay, yesterDay, yesterDay, yesterDay);
		// 今日签约司机数
		sql10 = getSql1(selectDateFormat, "已签约", date, date, date, date);
		// 本周签约司机数
		sql11 = getSql1(selectDateFormat, "已签约", startDate, endDate, startDate, endDate);
		// 本月签约司机数
		sql12 = getSql1(selectDateFormat1, "已签约", month, month, month, month);
		
		CustomerCount customerCount = new CustomerCount();
		customerCount.setCount1(String.valueOf(ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql1).get("count")));
		customerCount.setCount2(String.valueOf(ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql2).get("count")));
		customerCount.setCount3(String.valueOf(ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql3).get("count")));
		customerCount.setCount4(String.valueOf(ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql4).get("count")));
		customerCount.setCount5(String.valueOf(ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql5).get("count")));
		customerCount.setCount6(String.valueOf(ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql6).get("count")));
		customerCount.setCount7(String.valueOf(ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql7).get("count")));
		customerCount.setCount8(String.valueOf(ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql8).get("count")));
		customerCount.setCount9(String.valueOf(ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql9).get("count")));
		customerCount.setCount10(String.valueOf(ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql10).get("count")));
		customerCount.setCount11(String.valueOf(ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql11).get("count")));
		customerCount.setCount12(String.valueOf(ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql12).get("count")));
		return customerCount;
	}
	/** 车辆出入库情况统计
	 * @param selectDateFormat 时间格式
	 * @param violationdateStartDate 违章开始时间
	 * @param violationdatedateEndDate 违章结束时间
	 * @return
	 */
	public static CustomerCount getBySql1 (String date) {
		String startDate = DateUtil.convertWeekByMonday(date);
		String endDate = DateUtil.convertWeekBySunday(date);
		
		// 获取本月
		String month = DateUtil.toString(new Date(), "yyyy-MM");
		
		// 今日来店司机数
		String sql1 = getSql1(selectDateFormat, null, date, date, null, null);
		Map<String,Object> map1 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql1);
		// 今日签约司机数
		String sql2 = getSql1(selectDateFormat, "已签约", date, date, date, date);
		Map<String,Object> map2 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql2);
		// 今日签约率
		String num1 = DateUtil.numFormat(map1.get("count"), map2.get("count"));
		
		// 本周来店司机数
		String sql3 = getSql1(selectDateFormat, null, startDate, endDate, null, null);
		Map<String,Object> map3 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql3);
		// 本周签约司机数
		String sql4 = getSql1(selectDateFormat, "已签约", startDate, endDate, startDate, endDate);
		Map<String,Object> map4 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql4);
		// 本周签约率
		String num2 = DateUtil.numFormat(map3.get("count"), map4.get("count"));
		
		// 本月来店司机数
		String sql5 = getSql1(selectDateFormat1, null, month, month, null, null);
		Map<String,Object> map5 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql5);
		// 本月签约司机数
		String sql6 = getSql1(selectDateFormat1, "已签约", month, month, month, month);
		Map<String,Object> map6 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql6);
		// 本月签约率
		String num3 = DateUtil.numFormat(map5.get("count"), map6.get("count"));
		
		// 累计来店司机数
		String sql7 = getSql1(selectDateFormat1, null, null, null, null, null);
		Map<String,Object> map7 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql7);
		// 累计签约司机数
		String sql8 = getSql1(selectDateFormat1, "已签约", null, null, null, null);
		Map<String,Object> map8 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql8);
		// 累计签约率
		String num4 = DateUtil.numFormat(map7.get("count"), map8.get("count"));
		
		CustomerCount customerCount = new CustomerCount();
		customerCount.setCount1(String.valueOf(map1.get("count")));
		customerCount.setCount2(String.valueOf(map2.get("count")));
		customerCount.setCount3(String.valueOf(num1));
		customerCount.setCount4(String.valueOf(map3.get("count")));
		customerCount.setCount5(String.valueOf(map4.get("count")));
		customerCount.setCount6(String.valueOf(num2));
		customerCount.setCount7(String.valueOf(map5.get("count")));
		customerCount.setCount8(String.valueOf(map6.get("count")));
		customerCount.setCount9(String.valueOf(num3));
		customerCount.setCount10(String.valueOf(map7.get("count")));
		customerCount.setCount11(String.valueOf(map8.get("count")));
		customerCount.setCount12(String.valueOf(num4));
		return customerCount;
	}
	
	/** 来店客户统计分析
	 * @param selectDateFormat
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static String getSql1 (String selectDateFormat,String issign,String createdatetimeStartDate,String createdatetimeEndDate
			,String signdateStartDate,String signdateEndDate) {
		String sql = "SELECT COUNT(1) as count FROM ib_customer_mgr WHERE 1=1 ";
		if (StringUtils.isNotBlank(createdatetimeStartDate)) {
			sql+= "AND DATE_FORMAT(CREATEDATETIME, '"+selectDateFormat+"') >= '"+createdatetimeStartDate +"' ";
		}
		if (StringUtils.isNotBlank(createdatetimeEndDate)) {
			sql+= "AND DATE_FORMAT(CREATEDATETIME, '"+selectDateFormat+"') <= '"+createdatetimeEndDate +"' ";
		}
		if (StringUtils.isNotBlank(signdateStartDate)) {
			sql+= "AND DATE_FORMAT(SIGNDATE, '"+selectDateFormat+"') >= '"+signdateStartDate +"' ";
		}
		if (StringUtils.isNotBlank(signdateEndDate)) {
			sql+= "AND DATE_FORMAT(SIGNDATE, '"+selectDateFormat+"') <= '"+signdateEndDate +"' ";
		}
		if (StringUtils.isNotBlank(issign)) {
			sql+= "AND issign= '"+ issign +"' ";
		}
		return sql;
	}
	
	/** 司机情况数据分析
	 * @param date 查询时间
	 * @param typename 车辆型号
	 * @return
	 */
	public static CustomerCount getBySql2 (String date,String typename) {
		String startDate = DateUtil.convertWeekByMonday(date);
		String endDate = DateUtil.convertWeekBySunday(date);
		// 判断查询是否是今天
		boolean isToDay = date.equals(DateUtil.toString(new Date()));
		
		// 获取本月
		String month = DateUtil.toString(new Date(), "yyyy-MM");
		String sql1 = null;
		String sql2 = null;
		String sql3 = null;
		String sql4 = null;
		String sql5 = null;
		String sql6 = null;
		String sql7 = null;
		String sql8 = null;
		String sql9 = null;
		String sql10 = null;
		String sql11 = null;
		
		if (isToDay) {
			// 当日在职司机
			sql1 = SqlFormat.getSql3_1("正常", null, null, null, null, null, null,false,typename);
			
			// 当日在职自营司机
			sql2 = SqlFormat.getSql3_1("正常", "自营司机", null, null, null, null, null,false,typename);
			// 当日在职以租代购
			sql3 = SqlFormat.getSql3_1("正常", "以租代购", null, null, null, null, null,false,typename);
			// 当日司机提车数(合同生效的司机数)
			sql4 = SqlFormat.getSql3_1(null, null, selectDateFormat,date, date, null, null,false,typename);
			
			// 当周司机提车数(合同生效的司机数)
			sql5 = SqlFormat.getSql3_1(null, null, selectDateFormat,startDate, endDate, null, null,false,typename);
			
			// 当月司机提车数(合同生效的司机数)
			sql6 = SqlFormat.getSql3_1(null, null, selectDateFormat1,month, month, null, null,false,typename);
			
			// 累计总提车数(统计合同为正常的数量)
			sql7 = SqlFormat.getSql3_1("正常", null, null,null, null, null, null,false,typename);
			
			// 当日离职司机数(当日解约的司机)
			sql8 = SqlFormat.getSql3_1(null, null, selectDateFormat, null, null,date, date,false,typename);
			
			// 当周离职司机数(当周解约的司机)
			sql9 = SqlFormat.getSql3_1(null, null, selectDateFormat, null, null,startDate, endDate,false,typename);
			// 当月离职司机数(当月解约的司机)
			sql10 = SqlFormat.getSql3_1(null, null, selectDateFormat1,null, null, month, month,false,typename);
			
			// 累计总离职数(统计合同为已解约的数量)
			sql11 = SqlFormat.getSql3_1("已解约", null, null,null, null, null, null,false,typename);
		} else {
			month = DateUtil.toString(DateUtil.toDate(date), "yyyy-MM");
			
			// 当日在职司机
			sql1 = SqlFormat.getSql7(selectDateFormat, date, date, "1", typename, false);
			
			// 当日在职自营司机
			sql2 = SqlFormat.getSql7(selectDateFormat, date, date, "2", typename, false);
			// 当日在职以租代购
			sql3 = SqlFormat.getSql7(selectDateFormat, date, date, "3", typename, false);
			
			// 当日司机提车数(合同生效的司机数)
			sql4 = SqlFormat.getSql7(selectDateFormat, date, date, "4", typename, false);
			
			// 当周司机提车数(合同生效的司机数)
			sql5 = SqlFormat.getSql7(selectDateFormat, startDate, endDate, "4", typename, false);
			
			// 当月司机提车数(合同生效的司机数)
			sql6 = SqlFormat.getSql7( selectDateFormat1,month, month, "4", typename,false);
			
			// 累计总提车数(统计合同为正常的数量)
			sql7 = SqlFormat.getSql3_1("正常", null, null,null, null, null, null,false,typename);
			
			// 当日离职司机数(当日解约的司机)
			sql8 = SqlFormat.getSql7(selectDateFormat, date, date, "5", typename, false);
			
			// 当周离职司机数(当周解约的司机)
			sql9 = SqlFormat.getSql7(selectDateFormat, startDate, endDate, "5", typename, false);
			// 当月离职司机数(当月解约的司机)
			sql10 = SqlFormat.getSql7( selectDateFormat1,month, month, "5", typename,false);
			
			// 累计总离职数(统计合同为已解约的数量)
			sql11 = SqlFormat.getSql3_1("已解约", null, null,null, null, null, null,false,typename);
		}
		
		
		// 当日在职司机
		Map<String,Object> map1 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql1);
		
		// 当日在职自营司机
		Map<String,Object> map2 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql2);
		// 当日在职以租代购
		Map<String,Object> map3 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql3);
		
		// 当日司机提车数(合同生效的司机数)
		Map<String,Object> map4 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql4);
		
		// 当周司机提车数(合同生效的司机数)
		Map<String,Object> map5 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql5);
		
		// 当月司机提车数(合同生效的司机数)
		Map<String,Object> map6 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql6);
		
		// 累计总提车数(统计合同为正常的数量)
		Map<String,Object> map7 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql7);
		
		// 当日离职司机数(当日解约的司机)
		Map<String,Object> map8 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql8);
		
		// 当周离职司机数(当周解约的司机)
		Map<String,Object> map9 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql9);
		// 当月离职司机数(当月解约的司机)
		Map<String,Object> map10 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql10);
			
		// 累计总离职数(统计合同为已解约的数量)
		Map<String,Object> map11 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForMap(sql11);
		
		CustomerCount customerCount = new CustomerCount();
		customerCount.setCount1(String.valueOf(map1.get("count")));
		customerCount.setCount2(String.valueOf(map2.get("count")));
		customerCount.setCount3(String.valueOf(map3.get("count")));
		customerCount.setCount4(String.valueOf(map4.get("count")));
		customerCount.setCount5(String.valueOf(map5.get("count")));
		customerCount.setCount6(String.valueOf(map6.get("count")));
		customerCount.setCount7(String.valueOf(map7.get("count")));
		customerCount.setCount8(String.valueOf(map8.get("count")));
		customerCount.setCount9(String.valueOf(map9.get("count")));
		customerCount.setCount10(String.valueOf(map10.get("count")));
		customerCount.setCount11(String.valueOf(map11.get("count")));
		return customerCount;
	}
	
	
/*	*//** 今日在职日期
	 * @param selectDateFormat
	 * @param startDate
	 * @param endDate
	 * @return
	 *//*
	public static String getSql2_1 (String status,String DRIVERTYPE) {
		String sql = "SELECT count(1) as count from ib_special_driver_info where 1=1 ";
		if (StringUtils.isNotBlank(status)) {
			sql+= "AND status= '"+ status +"' ";
		}
		if (StringUtils.isNotBlank(DRIVERTYPE)) {
			sql+= "AND DRIVERTYPE= '"+ DRIVERTYPE +"' ";
		}
		return sql;
	}*/
	
	
/*	*//** 今日司机离职数
	 * @param status
	 * @param DRIVERTYPE
	 * @param selectDateFormat
	 * @param dimissiondateStartDate
	 * @param dimissiondateEndDate
	 * @return
	 *//*
	public static String getSql2_3 (String status,String DRIVERTYPE,String selectDateFormat ,String dimissiondateStartDate,String dimissiondateEndDate) {
		String sql = "SELECT count(1) as count from ib_special_driver_info where 1=1 ";
		if (StringUtils.isNotBlank(status)) {
			sql+= "AND status= '"+ status +"' ";
		}
		if (StringUtils.isNotBlank(DRIVERTYPE)) {
			sql+= "AND DRIVERTYPE= '"+ DRIVERTYPE +"' ";
		}
		if (StringUtils.isNotBlank(dimissiondateStartDate)) {
			sql+= "AND DATE_FORMAT(DIMISSIONDATE, '"+selectDateFormat+"') >= '"+ dimissiondateStartDate +"' ";
		}
		if (StringUtils.isNotBlank(dimissiondateEndDate)) {
			sql+= "AND DATE_FORMAT(DIMISSIONDATE, '"+selectDateFormat+"') <= '"+ dimissiondateEndDate +"' ";
		}
		return sql;
	}*/
	
	
	/** 车型库存分析
	 * @param date 查询时间
	 * @return
	 */
	public static List<CustomerCount> getBySql3 (String date) {
		String startDate = DateUtil.convertWeekByMonday(date);
		String endDate = DateUtil.convertWeekBySunday(date);
		// 判断查询是否是今天
		boolean isToDay = date.equals(DateUtil.toString(new Date()));
		
		String sql1 = null;
		String sql2 = null;
		String sql3 = null;
		String sql4 = null;
		String sql5 = null;
		String sql6 = null;
		
		String sql = getSql8();
		List<Map<String,Object>> list = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql);
		List<CustomerCount> customerCountList = new ArrayList<CustomerCount>();
		for (Map<String,Object> tmpMap : list) {
			CustomerCount customerCount = new CustomerCount();
			customerCount.setName(tmpMap.get("name").toString());
			customerCount.setId(tmpMap.get("id").toString());
			customerCountList.add(customerCount);
		}
		
		
		if (isToDay) {
			// 车型库存
			sql1 = SqlFormat.getSql4_3(null, null, null, null, null, null, null);
			
			// 当日已出库数量
			sql2 = SqlFormat.getSql4_3(null, "已出库", null, null, null, null, null);
					//SqlFormat.getSql3_2();
			
			// 当日待出库数量
			sql3 = SqlFormat.getSql4_3(null, "在库", null, null, null, null, null);
					//SqlFormat.getSql3_3();
			
			// 在维修厂的数量
			sql4 = SqlFormat.getSql3_4();
			
			// 外借的数量
			sql5 = SqlFormat.getSql4_3(null, "借出中", null, null, null, null, null);
					//SqlFormat.getSql3_4();
			
			// 出车率
			sql6 = SqlFormat.getSql3_6();
			
		} else {
			// 车型库存
			sql1 = SqlFormat.getSql7_1(selectDateFormat, date, date, "7", null, true);
			
			// 当日已出库数量
			sql2 = SqlFormat.getSql7_1(selectDateFormat, date, date, "8", null, true);
			
			// 当日待出库数量
			sql3 = SqlFormat.getSql7_1(selectDateFormat, date, date, "9", null, true);
			
			// 在维修厂的数量
			sql4 = SqlFormat.getSql7_1(selectDateFormat, date, date, "10", null, true);
			
			// 外借的数量
			sql5 = SqlFormat.getSql7_1(selectDateFormat, date, date, "11", null, true);
			
			// 出车率
			sql6 = SqlFormat.getSql7_1(selectDateFormat, date, date, "12", null, true);
			
		}
		// 车型库存
		List<Map<String,Object>> list1 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql1);
		
		// 当日已出库数量
		List<Map<String,Object>> list2 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql2);
		
		// 当日待出库数量
		List<Map<String,Object>> list3 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql3);
		
		// 在维修厂的数量
		List<Map<String,Object>> list4 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql4);
		
		// 外借的数量
		List<Map<String,Object>> list5 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql5);
		
		// 出车率
		List<Map<String,Object>> list6 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql6);
		CustomerCount.getValue(customerCountList, list1, 1);
		CustomerCount.getValue(customerCountList, list2, 2);
		CustomerCount.getValue(customerCountList, list3, 3);
		CustomerCount.getValue(customerCountList, list4, 4);
		CustomerCount.getValue(customerCountList, list5, 5);
		CustomerCount.getValue(customerCountList, list6, 6);
		
		return customerCountList;
	}
	
	/** 专车司机
	 * @param status 状态
	 * @param DRIVERTYPE 运营类型
	 * @param selectDateFormat 时间格式
	 * @param contractvaliddayStartDate 合同生效开始日期
	 * @param contractvaliddayEndDate 合同生效结束日期
	 * @param dimissiondateStartDate 解约开始日期
	 * @param dimissiondateEndDate 解约结束日期
	 * @return
	 */
	public static String getSql3_1 (String status,String DRIVERTYPE,String selectDateFormat ,
			String contractvaliddayStartDate,String contractvaliddayEndDate ,String dimissiondateStartDate,String dimissiondateEndDate
			,boolean isgroup,String carname) {
		String sql = "SELECT count(1) as count , CARNAME as name from ib_special_driver_info where 1=1 ";
		if (StringUtils.isNotBlank(status)) {
			sql+= "AND status= '"+ status +"' ";
		}
		if (StringUtils.isNotBlank(DRIVERTYPE)) {
			sql+= "AND DRIVERTYPE= '"+ DRIVERTYPE +"' ";
		}
		// 合同生效日期
		if (StringUtils.isNotBlank(contractvaliddayStartDate)) {
			sql+= "AND DATE_FORMAT(CONTRACTVALIDDAY, '"+selectDateFormat+"') >= '"+ contractvaliddayStartDate +"' ";
		}
		if (StringUtils.isNotBlank(contractvaliddayEndDate)) {
			sql+= "AND DATE_FORMAT(CONTRACTVALIDDAY, '"+selectDateFormat+"') <= '"+ contractvaliddayEndDate +"' ";
		}
		
		// 解约日期
		if (StringUtils.isNotBlank(dimissiondateStartDate)) {
			sql+= "AND DATE_FORMAT(DIMISSIONDATE, '"+selectDateFormat+"') >= '"+ dimissiondateStartDate +"' ";
		}
		if (StringUtils.isNotBlank(dimissiondateEndDate)) {
			sql+= "AND DATE_FORMAT(DIMISSIONDATE, '"+selectDateFormat+"') <= '"+ dimissiondateEndDate +"' ";
		}
		if (StringUtils.isNotBlank(carname)) {
			sql+= "AND CARNAME = '"+ carname +"' ";
		}
		if (isgroup) {
			sql+= " GROUP BY CARNAME ORDER BY CARNAME";
		}
		
		return sql;
	}
	
	/** 已出库：此车型已被绑定司机的总数（含在维修厂但已绑定司机的车辆数）
	 * 
	 * @return
	 */
	public static String getSql3_2 () {
		String sql = "SELECT count(1) as count,cti.TYPENAME as name FROM ib_car_type_info cti,ib_special_driver_info sdi where 1=1 "
				+ " AND cti.TYPENAME=sdi.CARNAME";
		sql+= " GROUP BY cti.TYPENAME ORDER BY cti.TYPENAME";
		return sql;
	}
	
	/** 待出库：此车型未绑定司机的总数（含在维修厂未绑定司机的车辆数量）
	 * 
	 * @return
	 */
	public static String getSql3_3 () {
		String sql = "SELECT count(1) as count,cti.TYPENAME as name FROM ib_car_type_info cti LEFT JOIN ib_special_driver_info sdi ON  cti.TYPENAME=sdi.CARNAME "
				+ " where 1=1  AND sdi.CARNAME is null";
		sql+= " GROUP BY cti.TYPENAME ORDER BY cti.TYPENAME";
		return sql;
	}
	
	/** 维修厂：此车型在维修厂的数量
	 * 
	 * @return
	 */
	public static String getSql3_4 () {
		String sql = "SELECT count(1) as count,cti.TYPENAME as name FROM ib_car_type_info cti ,ib_car_maintain_input cmi "
				+ " where 1=1  AND cti.TYPENAME=cmi.TYPENAME " + " AND cmi.LEAVEFACTORYDATE < cmi.ENTERFACTORYDATE";
		sql+= " GROUP BY cti.TYPENAME ORDER BY cti.TYPENAME";
		return sql;
	}
	
	/** 外借：此车型外借的数量
	 * 
	 * @return
	 */
	public static String getSql3_5 () {
		String sql = "SELECT count(1) as count,cti.TYPENAME as name FROM ib_car_type_info cti ,ib_car_loan cl "
				+ " where 1=1  AND cti.TYPENAME=cl.TYPENAME " + "  where 1=1  AND cti.TYPENAME=cl.TYPENAME ";
		sql+= " GROUP BY cti.TYPENAME ORDER BY cti.TYPENAME";
		return sql;
	}
	
	/** 出车率
	 * 
	 * @return
	 */
	public static String getSql3_6 () {
		String sql = "SELECT IFNULL(ROUND((COUNT(1)/ (SELECT COUNT(1) FROM IB_SPECIAL_DRIVER_INFO SDI WHERE 1=1  AND CTI.TYPENAME=SDI.CARNAME))*100,2),0) as count"
				+ ",CTI.TYPENAME AS NAME FROM IB_CAR_TYPE_INFO CTI WHERE 1=1 " ;
		sql+= " GROUP BY CTI.TYPENAME ORDER BY CTI.TYPENAME";
		return sql;
	}
	
	/** 车辆出入库情况统计
	 * @param selectDateFormat 时间格式
	 * @param violationdateStartDate 违章开始时间
	 * @param violationdatedateEndDate 违章结束时间
	 * @return
	 */
	public static List<CustomerCount> getBySql4 (String date) {
		String startDate = DateUtil.convertWeekByMonday(date);
		String endDate = DateUtil.convertWeekBySunday(date);
		// 上个星期一
		String lastWeekMonday = DateUtil.getLastWeekMonday(date);
		// 上个星期日
		String lastWeekSunday = DateUtil.getLastWeekSunday(date);
		
		// 获取全部车型
		String sql = getSql8();
		List<Map<String,Object>> list = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql);
		List<CustomerCount> customerCountList = new ArrayList<CustomerCount>();
		for (Map<String,Object> tmpMap : list) {
			CustomerCount customerCount = new CustomerCount();
			customerCount.setName(tmpMap.get("name").toString());
			customerCount.setId(tmpMap.get("id").toString());
			customerCountList.add(customerCount);
		}
		
		// 上周库存
		String sql1 = SqlFormat.getSql7_1(selectDateFormat, lastWeekSunday, lastWeekSunday, "6", null, true);
		List<Map<String,Object>> list1 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql1);
		ConfCountSumItem confCountSumItem1 = new ConfCountSumItem();
		confCountSumItem1.setList(getItem(list1));
		// 退车入库
		String sql2 = getSql4_2(selectDateFormat, startDate,endDate);
		List<Map<String,Object>> list2 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql2);
		ConfCountSumItem confCountSumItem2 = new ConfCountSumItem();
		confCountSumItem1.setList(getItem(list2));
		// 新车入库
		String sql3 = getSql4_3(selectDateFormat, null, "新车入库",startDate,endDate, null, null);
		List<Map<String,Object>> list3 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql3);
		ConfCountSumItem confCountSumItem3 = new ConfCountSumItem();
		confCountSumItem1.setList(getItem(list3));
		// 车辆出库
		String sql4 = getSql4_4(selectDateFormat, startDate,endDate, startDate,endDate);
		List<Map<String,Object>> list4 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql4);
		ConfCountSumItem confCountSumItem4 = new ConfCountSumItem();
		confCountSumItem1.setList(getItem(list4));
		
		// 判断查询是否是今天
		boolean isToDay = date.equals(DateUtil.toString(new Date()));
		String sql5 = null;
		if (isToDay) {
			// 当日车辆库存
			sql5 = SqlFormat.getSql4_3(null, "在库", null, null, null, null, null);
		} else {
			// 本周日库存
			sql5 = SqlFormat.getSql7_1(selectDateFormat, endDate, endDate, "6", null, true);
		}
		List<Map<String,Object>> list5 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql5);
//		ConfCountSumItem confCountSumItem5 = new ConfCountSumItem();
//		confCountSumItem1.setList(getItem(list5));
		
		CustomerCount.getValue(customerCountList, list1, 1);
		CustomerCount.getValue(customerCountList, list2, 2);
		CustomerCount.getValue(customerCountList, list3, 3);
		CustomerCount.getValue(customerCountList, list4, 4);
		CustomerCount.getValue(customerCountList, list5, 5);
		
//		SumItem sumItem = new SumItem();
//		sumItem.setConfCountSumItem1(confCountSumItem1);
//		sumItem.setConfCountSumItem2(confCountSumItem2);
//		sumItem.setConfCountSumItem3(confCountSumItem3);
//		sumItem.setConfCountSumItem4(confCountSumItem4);
//		sumItem.setConfCountSumItem5(confCountSumItem5);
		return customerCountList;
	}
	
	/** 车辆出入库统计
	 * @param CARSTATUS 车辆出入库
	 * @param INTYPE 入库类型
	 * @param selectDateFormat 时间格式
	 * @param dnfactorydateStartDate 入库开始时间
	 * @param dnfactorydateEndDate 入库结束时间
	 * @param contractoverdateStartDate 合同终止开始时间
	 * @param contractoverdateEndDate 合同终止结束时间
	 * 
	 * @return
	 */
	public static String getSql4_2 (String selectDateFormat ,String contractoverdateStartDate,String contractoverdateEndDate) {
		String sql = "SELECT count(1) as count,CARNAME as name from ib_special_driver_info where 1=1 ";
		// 终止合同时间
		if (StringUtils.isNotBlank(contractoverdateStartDate)) {
			sql+= "AND DATE_FORMAT(CONTRACTOVERDATE, '"+selectDateFormat+"') >= '"+ contractoverdateStartDate +"' ";
		}
		if (StringUtils.isNotBlank(contractoverdateEndDate)) {
			sql+= "AND DATE_FORMAT(CONTRACTOVERDATE, '"+selectDateFormat+"') <= '"+ contractoverdateEndDate +"' ";
		}
		sql+= " GROUP BY CARNAME ORDER BY CARNAME";
		return sql;
	}
	
	/** 车辆出入库统计
	 * @param CARSTATUS 车辆出入库
	 * @param INTYPE 入库类型
	 * @param selectDateFormat 时间格式
	 * @param dnfactorydateStartDate 入库开始时间
	 * @param dnfactorydateEndDate 入库结束时间
	 * @param contractoverdateStartDate 合同终止开始时间
	 * @param contractoverdateEndDate 合同终止结束时间
	 * 
	 * @return
	 */
	public static String getSql4_3 (String selectDateFormat ,String CARSTATUS,String INTYPE,String infactorydateStartDate,String infactorydateEndDate
			,String contractoverdateStartDate,String contractoverdateEndDate) {
		String sql = "SELECT count(1) as count,TYPENAME as name from ib_car_mgr where 1=1 ";
		// 入库时间
		if (StringUtils.isNotBlank(infactorydateStartDate)) {
			sql+= "AND DATE_FORMAT(INFACTORYDATE, '"+selectDateFormat+"') >= '"+ infactorydateStartDate +"' ";
		}
		if (StringUtils.isNotBlank(infactorydateEndDate)) {
			sql+= "AND DATE_FORMAT(INFACTORYDATE, '"+selectDateFormat+"') <= '"+ infactorydateEndDate +"' ";
		}
//		// 终止合同时间
//		if (StringUtils.isNotBlank(contractoverdateStartDate)) {
//			sql+= "AND DATE_FORMAT(CONTRACTOVERDATE, '"+selectDateFormat+"') >= '"+ contractoverdateStartDate +"' ";
//		}
//		if (StringUtils.isNotBlank(contractoverdateEndDate)) {
//			sql+= "AND DATE_FORMAT(CONTRACTOVERDATE, '"+selectDateFormat+"') <= '"+ contractoverdateEndDate +"' ";
//		}
//		if (StringUtils.isNotBlank(INTYPE)) {
//			sql+= "AND INTYPE= '"+ INTYPE +"' ";
//		}
		if (StringUtils.isNotBlank(CARSTATUS)) {
			sql+= "AND CARSTATUS= '"+ CARSTATUS +"' ";
		}
		sql+= " GROUP BY TYPENAME ORDER BY TYPENAME";
		return sql;
	}
	
	/** 车辆出库统计
	 * @param CARSTATUS 车辆出入库
	 * @param INTYPE 入库类型
	 * @param selectDateFormat 时间格式
	 * @param dnfactorydateStartDate 入库开始时间
	 * @param dnfactorydateEndDate 入库结束时间
	 * @param contractoverdateStartDate 合同终止开始时间
	 * @param contractoverdateEndDate 合同终止结束时间
	 * 
	 * @return
	 */
	public static String getSql4_4 (String selectDateFormat ,String contractvaliddayStartDate,String contractvaliddayEndDate
			,String loandateStartDate,String loandateEndDate) {
		String sql = "SELECT count(1) as count,cti.TYPENAME as name FROM ib_car_type_info cti ,ib_car_loan cl ,ib_special_driver_info sdi where 1=1 "
				+ "AND cti.TYPENAME=cl.TYPENAME AND cti.TYPENAME=sdi.CARNAME ";
		// 合同生效日
		if (StringUtils.isNotBlank(contractvaliddayStartDate)) {
			sql+= " AND ( (DATE_FORMAT(sdi.CONTRACTVALIDDAY, '"+selectDateFormat+"') >= '"+ contractvaliddayStartDate +"' ";
		}
		if (StringUtils.isNotBlank(contractvaliddayEndDate)) {
			sql+= " AND DATE_FORMAT(sdi.CONTRACTVALIDDAY, '"+selectDateFormat+"') <= '"+ contractvaliddayEndDate +"' )";
		}
		
		// 车辆借出日
		if (StringUtils.isNotBlank(loandateStartDate)) {
			sql+= " or (DATE_FORMAT(cl.LOANDATE, '"+selectDateFormat+"') >= '"+ loandateStartDate +"' ";
		}
		if (StringUtils.isNotBlank(loandateEndDate)) {
			sql+= " AND DATE_FORMAT(cl.LOANDATE, '"+selectDateFormat+"') <= '"+ loandateEndDate +"' ))";
		}
		sql+= " GROUP BY cti.TYPENAME ORDER BY cti.TYPENAME";
		return sql;
	}
	
	/** 违章周统计
	 * @param selectDateFormat 时间格式
	 * @param violationdateStartDate 违章开始时间
	 * @param violationdatedateEndDate 违章结束时间
	 * @return
	 */
	public static List<CustomerCount> getBySql5 (Date date) {
		return getBySql5(DateUtil.toString(date));
	}
	/** 维修周统计
	 * @param selectDateFormat 时间格式
	 * @param violationdateStartDate 违章开始时间
	 * @param violationdatedateEndDate 违章结束时间
	 * @return
	 */
	public static List<CustomerCount> getBySql5 (String date) {
		String startDate = DateUtil.convertWeekByMonday(date);
		String endDate = DateUtil.convertWeekBySunday(date);
		
		String sql = getSql5_1();
		List<Map<String,Object>> list = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql);
		List<CustomerCount> customerCountList = new ArrayList<CustomerCount>();
		for (Map<String,Object> tmpMap : list) {
			CustomerCount customerCount = new CustomerCount();
			customerCount.setName(tmpMap.get("name").toString());
			customerCount.setId(tmpMap.get("id").toString());
			customerCountList.add(customerCount);
		}
		
		// 获取维修保养
		String sql1 = getSql5(null, "保养", startDate, endDate, null, null);
		List<Map<String,Object>> list1 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql1);
		ConfCountSumItem confCountSumItem1 = new ConfCountSumItem();
		confCountSumItem1.setList(getItem(list1));
		
		// 获取维修进厂
		String sql2 = getSql5(selectDateFormat,null,  startDate, endDate, null, null);
		List<Map<String,Object>> list2 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql2);
		ConfCountSumItem confCountSumItem2 = new ConfCountSumItem();
		confCountSumItem2.setList(getItem(list2));
		
		// 获取维修出厂
		String sql3 = getSql5(selectDateFormat,null,  null, null, startDate, endDate);
		List<Map<String,Object>> list3 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql3);
		ConfCountSumItem confCountSumItem3 = new ConfCountSumItem();
		confCountSumItem3.setList(getItem(list3));
		
		CustomerCount.getValue(customerCountList, list1, 1);
		CustomerCount.getValue(customerCountList, list2, 2);
		CustomerCount.getValue(customerCountList, list3, 3);
		
//		SumItem sumItem = new SumItem();
//		sumItem.setConfCountSumItem1(confCountSumItem1);
//		sumItem.setConfCountSumItem2(confCountSumItem2);
//		sumItem.setConfCountSumItem3(confCountSumItem3);
		return customerCountList;
	}
	
	private static List<ConfCountItem> getItem (List<Map<String,Object>> list) {
		List<ConfCountItem> items = new ArrayList<ConfCountItem>();
		for (Map<String,Object> mapBean : list) {
			ConfCountItem confCountItem = new ConfCountItem();
			confCountItem.setCount(mapBean.get("count").toString());
			confCountItem.setName(mapBean.get("name").toString());
			items.add(confCountItem);
		}
		return items;
	}
	
	/** 维修保养周统计 
	 * @param ENTERFACTORYTYPE 进厂类型
	 * @param selectDateFormat 时间格式
	 * @param enterfactorydateStartDate 进厂开始时间
	 * @param enterfactorydateEndDate 进厂结束时间
	 * @param leavefactorydateStartDate 出厂开始时间
	 * @param leavefactorydateEndDate 出厂结束时间
	 * @return
	 */
	public static String getSql5 (String selectDateFormat ,String ENTERFACTORYTYPE,String enterfactorydateStartDate,String enterfactorydateEndDate
			,String leavefactorydateStartDate,String leavefactorydateEndDate) {
		String sql = "SELECT count(1) as count,mf.MAINTAINNAME as name from ib_car_maintain_factory mf ,ib_car_maintain_input mi where 1=1 AND mf.ID=mi.MAINTAINID ";
		
		if (StringUtils.isNotBlank(enterfactorydateStartDate)) {
			sql+= "AND DATE_FORMAT(mi.ENTERFACTORYDATE, '"+selectDateFormat+"') >= '"+ enterfactorydateStartDate +"' ";
		}
		if (StringUtils.isNotBlank(enterfactorydateEndDate)) {
			sql+= "AND DATE_FORMAT(mi.ENTERFACTORYDATE, '"+selectDateFormat+"') <= '"+ enterfactorydateEndDate +"' ";
		}
		
		if (StringUtils.isNotBlank(leavefactorydateStartDate)) {
			sql+= "AND DATE_FORMAT(mi.LEAVEFACTORYDATE, '"+selectDateFormat+"') >= '"+ leavefactorydateStartDate +"' ";
		}
		if (StringUtils.isNotBlank(leavefactorydateEndDate)) {
			sql+= "AND DATE_FORMAT(mi.LEAVEFACTORYDATE, '"+selectDateFormat+"') <= '"+ leavefactorydateEndDate +"' ";
		}
		if (StringUtils.isNotBlank(ENTERFACTORYTYPE)) {
			sql+= "AND mi.ENTERFACTORYTYPE= '"+ ENTERFACTORYTYPE +"' ";
		}
		sql+= " GROUP BY mi.MAINTAINID ORDER BY mi.MAINTAINID";
		return sql;
	}
	
	/** 维修厂分组
	 * @return
	 */
	public static String getSql5_1 () {
		String sql = "SELECT MAINTAINNAME as name ,ID as id FROM ib_car_maintain_factory GROUP BY ID";
		return sql;
	}
	
	
	/** 违章周统计
	 * @param selectDateFormat 时间格式
	 * @param violationdateStartDate 违章开始时间
	 * @param violationdatedateEndDate 违章结束时间
	 * @return
	 */
//	public static SumItem getBySql6 (Date date) {
//		return getBySql6(DateUtil.toString(date));
//	}
	
	/** 违章周统计
	 * @param selectDateFormat 时间格式
	 * @param violationdateStartDate 违章开始时间
	 * @param violationdatedateEndDate 违章结束时间
	 * @return
	 */
	public static void getBySql6 (String date) {
		//String strDate = DateUtil.toString(date);
		String startDate = DateUtil.convertWeekByMonday(date);
		String endDate = DateUtil.convertWeekBySunday(date);
		
		// 违章车辆总数
		String sql1 = getSql6_1(selectDateFormat, startDate, endDate);
		// 违章车辆总数
		Map<String,Object> map = ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForMap(sql1);
		
		// 违章项目
		String sql2 = getSql6_2(selectDateFormat, startDate, endDate,true);
		// 违章项目
		List<Map<String,Object>> list2 = ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForList(sql2);
		//return sumItem;
	}
	
	/** 违章车辆总数周统计
	 * @param selectDateFormat 时间格式
	 * @param violationdateStartDate 违章开始时间
	 * @param violationdatedateEndDate 违章结束时间
	 * @return
	 */
	public static String getSql6_1 (String selectDateFormat ,String violationdateStartDate,String violationdatedateEndDate) {
		String sql = "SELECT COUNT(1) as count,VIOLATIONTYPE as name from ib_car_violation where 1=1 ";
		
		if (StringUtils.isNotBlank(violationdateStartDate)) {
			sql+= "AND DATE_FORMAT(VIOLATIONDATE, '"+selectDateFormat+"') >= '"+ violationdateStartDate +"' ";
		}
		if (StringUtils.isNotBlank(violationdatedateEndDate)) {
			sql+= "AND DATE_FORMAT(VIOLATIONDATE, '"+selectDateFormat+"') <= '"+ violationdatedateEndDate +"' ";
		}
		//sql+= " GROUP BY CARID ";
		return sql;
	}
	
	/** 违章项目总数周统计
	 * @param selectDateFormat 时间格式
	 * @param violationdateStartDate 违章开始时间
	 * @param violationdatedateEndDate 违章结束时间
	 * @return
	 */
	public static String getSql6_2 (String selectDateFormat ,String violationdateStartDate,String violationdatedateEndDate,
			boolean isgroup) {
		String sql = "SELECT COUNT(1) as count,VIOLATIONTYPE as name from ib_car_violation where 1=1 ";
		
		if (StringUtils.isNotBlank(violationdateStartDate)) {
			sql+= "AND DATE_FORMAT(VIOLATIONDATE, '"+selectDateFormat+"') >= '"+ violationdateStartDate +"' ";
		}
		if (StringUtils.isNotBlank(violationdatedateEndDate)) {
			sql+= "AND DATE_FORMAT(VIOLATIONDATE, '"+selectDateFormat+"') <= '"+ violationdatedateEndDate +"' ";
		}
		if (isgroup) {
			sql+= " GROUP BY VIOLATIONTYPE ORDER BY VIOLATIONTYPE";
		}
		return sql;
	}
	
	/** 获取车辆库存统计表的数据
	 * @param selectDateFormat 时间格式
	 * @param violationdateStartDate 违章开始时间
	 * @param violationdatedateEndDate 违章结束时间
	 * @return
	 */
	public static String getSql7 (String selectDateFormat ,String startDate,String endDate,String type,String typename
			,boolean isgroup) {
		String sql = "SELECT SUM(count) as count,typename as name from ib_car_count where 1=1 ";
		
		if (StringUtils.isNotBlank(startDate)) {
			sql+= "AND DATE_FORMAT(date, '"+selectDateFormat+"') >= '"+ startDate +"' ";
		}
		if (StringUtils.isNotBlank(endDate)) {
			sql+= "AND DATE_FORMAT(date, '"+selectDateFormat+"') <= '"+ endDate +"' ";
		}
		
		if (StringUtils.isNotBlank(type)) {
			sql+= "AND type= '"+ type +"' ";
		}
		
		if (StringUtils.isNotBlank(typename)) {
			sql+= "AND typename= '"+ typename +"' ";
		}
		if (isgroup) {
			sql+= " GROUP BY type ORDER BY type";
		}
		return sql;
	}
	
	/** 获取车辆库存统计表的数据
	 * @param selectDateFormat 时间格式
	 * @param violationdateStartDate 违章开始时间
	 * @param violationdatedateEndDate 违章结束时间
	 * @return
	 */
	public static String getSql7_1 (String selectDateFormat ,String startDate,String endDate,String type,String typename
			,boolean isgroup) {
		String sql = "SELECT IFNULL(count,0) as count,typename as name from ib_car_count where 1=1 ";
		
		if (StringUtils.isNotBlank(startDate)) {
			sql+= "AND DATE_FORMAT(date, '"+selectDateFormat+"') >= '"+ startDate +"' ";
		}
		if (StringUtils.isNotBlank(endDate)) {
			sql+= "AND DATE_FORMAT(date, '"+selectDateFormat+"') <= '"+ endDate +"' ";
		}
		
		if (StringUtils.isNotBlank(type)) {
			sql+= "AND type= '"+ type +"' ";
		}
		
		if (StringUtils.isNotBlank(typename)) {
			sql+= "AND typename= '"+ typename +"' ";
		}
		if (isgroup) {
			sql+= " GROUP BY typename ORDER BY typename";
		}
		return sql;
	}
	
	/** 获取全部车型
	 * 
	 * @return
	 */
	public static String getSql8 () {
		String sql = " SELECT TYPENAME as name ,ID as id FROM ib_car_type_info GROUP BY TYPENAME ";
		return sql;
	}
	
}
