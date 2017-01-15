package com.report.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.codegenerate.carmgr.entity.CarCountEntity;
import com.codegenerate.carmgr.service.CarCountService;
import com.ibusiness.common.service.CommonBaseService;
import com.ibusiness.core.spring.ApplicationContextHelper;

/**
 * DateUtil
 * 
 * @author jiangbo
 * 
 */
@Component
public class CarCountQuartzUtil {

	private static Logger log = Logger.getLogger(CarCountQuartzUtil.class);
	public static String selectDateFormat = "%Y-%m-%d";
	@Autowired
	private CarCountService carCountService;
	/**
	 * 车辆库存统计
	 * 
	 * @param strDate
	 * @return
	 */
	@Scheduled(cron="0 50 23 * * ? ")
	public void carCount() {
		log.info("定时任务开启--------------------------");
		try {
			String date = DateUtil.toString(new Date());
			
//			String startDate = DateUtil.convertWeekByMonday(date);
//			String endDate = DateUtil.convertWeekBySunday(date);
			String sql0 = SqlFormat.getSql8();
			List<Map<String,Object>> list0 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql0);
			Map<String,String> typeMap = new HashMap<String, String>();
			for (Map<String,Object> map :list0 ) {
				typeMap.put(map.get("name").toString(), map.get("id").toString());
			}
			
			// 当日在职司机
			String sql1 = SqlFormat.getSql3_1("正常", null, null, null, null, null, null,true,null);
			List<Map<String,Object>> list1 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql1);
			insertCarCount(list1, "1",typeMap);
			// 当日在职自营司机
			String sql2 = SqlFormat.getSql3_1("正常", "自营司机", null, null, null, null, null,true,null);
			List<Map<String,Object>> list2 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql2);
			insertCarCount(list2, "2",typeMap);
			// 当日在职以租代购
			String sql3 = SqlFormat.getSql3_1("正常", "以租代购", null, null, null, null, null,true,null);
			List<Map<String,Object>> list3 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql3);
			insertCarCount(list3, "3",typeMap);
			// 当日司机提车数(合同生效的司机数)
			String sql4 = SqlFormat.getSql3_1(null, null, selectDateFormat,date, date, null, null,true,null);
			List<Map<String,Object>> list4 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql4);
			insertCarCount(list4, "4",typeMap);
			// 当日离职司机数(当日解约的司机)
			String sql5 = SqlFormat.getSql3_1(null, null, selectDateFormat, null, null,date, date,true,null);
			List<Map<String,Object>> list5 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql5);
			insertCarCount(list5, "5",typeMap);
			
			// 当日车辆库存
			String sql6 = SqlFormat.getSql4_3(null, "在库", null, null, null, null, null);
			List<Map<String,Object>> list6 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql6);
			insertCarCount(list6, "6",typeMap);
			
			// 当日车型数量
			String sql7 = SqlFormat.getSql4_3(null, null, null, null, null, null, null);
			List<Map<String,Object>> list7 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql7);
			insertCarCount(list7, "7",typeMap);
			
			// 当日已出库数量
			String sql8 = SqlFormat.getSql4_3(null, "已出库", null, null, null, null, null);
					//SqlFormat.getSql3_2();
			List<Map<String,Object>> list8 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql8);
			insertCarCount(list8, "8",typeMap);
			
			// 当日待出库数量
			String sql9 = SqlFormat.getSql4_3(null, "在库", null, null, null, null, null);
					//SqlFormat.getSql3_3();
			List<Map<String,Object>> list9 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql9);
			insertCarCount(list9, "9",typeMap);
			
			// 在维修厂的数量
			String sql10 = SqlFormat.getSql3_4();
			List<Map<String,Object>> list10 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql10);
			insertCarCount(list10, "10",typeMap);
			
			// 外借的数量
			String sql11 = SqlFormat.getSql4_3(null, "借出中", null, null, null, null, null);
					//SqlFormat.getSql3_4();
			List<Map<String,Object>> list11 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql11);
			insertCarCount(list11, "11",typeMap);
			
			// 出车率
			String sql12 = SqlFormat.getSql3_6();
			List<Map<String,Object>> list12 = ApplicationContextHelper.getBean(CommonBaseService.class).getJdbcTemplate().queryForList(sql12);
			insertCarCount(list12, "12",typeMap);
			
			log.info("定时任务结束--------------------------");
		} catch (Exception e) {
			log.error("error",e);
		}
	}
	
	private void insertCarCount (List<Map<String,Object>> list1,String type,Map<String,String> typeMap) {
		CarCountEntity carCountEntity = new CarCountEntity();
		carCountEntity.setDate(DateUtil.toString(new Date()));
		for (Map<String,Object> map : list1) {
			carCountEntity.setCount(map.get("count").toString());
			carCountEntity.setTypename(map.get("name").toString());
			carCountEntity.setType(type);
			carCountEntity.setCartypeid(typeMap.get(map.get("name").toString()));
			carCountEntity.setId(UUID.randomUUID().toString());
			carCountService.insert(carCountEntity);
		}
	}


}
