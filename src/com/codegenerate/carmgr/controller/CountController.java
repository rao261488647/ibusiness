package com.codegenerate.carmgr.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codegenerate.carmgr.service.CarLoanService;
import com.codegenerate.carmgr.service.Car_mgrService;
import com.codegenerate.special.service.Special_driver_infoService;
import com.ibusiness.common.export.CountExcelCommon;
import com.ibusiness.common.model.CustomerCount;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.core.spring.ApplicationContextHelper;
import com.ibusiness.core.spring.MessageHelper;
import com.report.utils.DateUtil;
import com.report.utils.SqlFormat;

/**
 * @Title: Controller
 * @Description: 车辆借出管理
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("count")
public class CountController {

	private MessageHelper messageHelper;
	private com.ibusiness.doc.store.StoreConnector storeConnector;
	private CarLoanService carLoanService;

	private Car_mgrService car_mgrService;

	private Special_driver_infoService special_driver_infoService;

	/**
	 * 列表
	 */
	@RequestMapping("count")
	public String count(@ModelAttribute Page page,@RequestParam Map<String, Object> parameterMap, Model model) {

		
		String strDate = DateUtil.toString(new Date());
		// 来店客户统计分析
		CustomerCount customerCount1 = SqlFormat.getBySql1(strDate);
		// 司机情况数据分析
		CustomerCount customerCount2 = SqlFormat.getBySql2(strDate,null);
		// 各车型库存盘点
		List<CustomerCount> customerCountList1 = SqlFormat.getBySql3(strDate);
		// 各车型库存盘点
		CustomerCount customerCount10 = new CustomerCount();
		
		for (CustomerCount customerCountTmp :customerCountList1) {
			customerCount10.setCount1(Integer.valueOf(customerCountTmp.getCount1()) + Integer.valueOf(customerCount10.getCount1()) + "");
			customerCount10.setCount2(Integer.valueOf(customerCountTmp.getCount2()) + Integer.valueOf(customerCount10.getCount2()) + "");
			customerCount10.setCount3(Integer.valueOf(customerCountTmp.getCount3()) + Integer.valueOf(customerCount10.getCount3()) + "");
			customerCount10.setCount4(Integer.valueOf(customerCountTmp.getCount4()) + Integer.valueOf(customerCount10.getCount4()) + "");
			customerCount10.setCount5(Integer.valueOf(customerCountTmp.getCount5()) + Integer.valueOf(customerCount10.getCount5()) + "");
		}
		
		customerCount10.setCount6(Double.valueOf(DateUtil.numFormat(customerCount10.getCount1(), customerCount10.getCount2())) + "");
		
		// 车辆出入库情况周统计表
		List<CustomerCount> customerCountList2 =SqlFormat.getBySql4(strDate);
		CustomerCount customerCount11 = new CustomerCount();
		// 车辆出入库情况周统计表
		for (CustomerCount customerCountTmp :customerCountList2) {
			customerCount11.setCount1(Integer.valueOf(customerCountTmp.getCount1()) + Integer.valueOf(customerCount11.getCount1()) + "");
			customerCount11.setCount2(Integer.valueOf(customerCountTmp.getCount2()) + Integer.valueOf(customerCount11.getCount2()) + "");
			customerCount11.setCount3(Integer.valueOf(customerCountTmp.getCount3()) + Integer.valueOf(customerCount11.getCount3()) + "");
			customerCount11.setCount4(Integer.valueOf(customerCountTmp.getCount4()) + Integer.valueOf(customerCount11.getCount4()) + "");
			customerCount11.setCount5(Integer.valueOf(customerCountTmp.getCount5()) + Integer.valueOf(customerCount11.getCount5()) + "");
		}
		
		// 维修保养周统计表
		List<CustomerCount> customerCountList3 = SqlFormat.getBySql5(strDate);
		
		CustomerCount customerCount12 = new CustomerCount();
		// 车辆出入库情况周统计表
		for (CustomerCount customerCountTmp :customerCountList3) {
			customerCount12.setCount1(Integer.valueOf(customerCountTmp.getCount1()) + Integer.valueOf(customerCount12.getCount1()) + "");
			customerCount12.setCount2(Integer.valueOf(customerCountTmp.getCount2()) + Integer.valueOf(customerCount12.getCount2()) + "");
			customerCount12.setCount3(Integer.valueOf(customerCountTmp.getCount3()) + Integer.valueOf(customerCount12.getCount3()) + "");
		}

		String startDate = DateUtil.convertWeekByMonday(strDate);
		String endDate = DateUtil.convertWeekBySunday(strDate);
		
		// 违章车辆总数
		String sql1 = SqlFormat.getSql6_1(SqlFormat.selectDateFormat, startDate, endDate);
		// 违章车辆总数
		Map<String,Object> map1 = ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForMap(sql1);
		// 违章项目
		String sql2 = SqlFormat.getSql6_2(SqlFormat.selectDateFormat, startDate, endDate,true);
		// 违章总数
		List<Map<String,Object>> list1 = ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForList(sql2);
		// 违章总数
		String sql3 = SqlFormat.getSql6_2(SqlFormat.selectDateFormat, startDate, endDate,false);
		Map<String,Object> map2 = ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForMap(sql3);
		model.addAttribute("customerCount1", customerCount1);
		model.addAttribute("customerCount2", customerCount2);
		model.addAttribute("customerCountList1", customerCountList1);
		model.addAttribute("customerCount10", customerCount10);
		model.addAttribute("customerCountList2", customerCountList2);
		model.addAttribute("customerCount11", customerCount11);
		model.addAttribute("customerCountList3", customerCountList3);
		model.addAttribute("customerCount12", customerCount12);
		model.addAttribute("map1", map1);
		model.addAttribute("list1", list1);
		model.addAttribute("map2", map2);
		return "codegenerate/count/count.jsp";
	}

	/**
	 * 来店客户统计分析
	 */
	@RequestMapping("count1")
	public String count1(@ModelAttribute Page page,@RequestParam Map<String, Object> parameterMap, Model model) {
		String date = (String) parameterMap.get("filter_GED_date");
		if (StringUtils.isBlank(date)) {
			date = DateUtil.toString(new Date());
		}
		CustomerCount customerCount1 = SqlFormat.getBySql1(date);
		model.addAttribute("customerCount1", customerCount1);
		return "codegenerate/count/count1.jsp";
	}
	/**
	 * 来店客户统计分析
	 */
	@RequestMapping("exportcount1")
	public void exportcount1(@ModelAttribute Page page,@RequestParam Map<String, Object> parameterMap, Model model, HttpServletResponse response) {
		String date = (String) parameterMap.get("filter_GED_date");
		if (StringUtils.isBlank(date)) {
			date = DateUtil.toString(new Date());
		}
		CustomerCount customerCount1 = SqlFormat.getBySql1(date);
		try {
			new CountExcelCommon().exportExcel1(response, customerCount1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 司机情况数据分析
	 */
	@RequestMapping("count2")
	public String count2(@ModelAttribute Page page,@RequestParam Map<String, Object> parameterMap, Model model) {
		String date = (String) parameterMap.get("filter_GED_date");
		String typename = (String) parameterMap.get("filter_LIKES_typename");
		if (StringUtils.isBlank(date)) {
			date = DateUtil.toString(new Date());
		}
		// 司机情况数据分析
		CustomerCount customerCount2 = SqlFormat.getBySql2(date,typename);
		model.addAttribute("customerCount2", customerCount2);
        // 车辆星号
        model.addAttribute("typenameItems", CommonBusiness.getInstance().getListConfSelectItemSQL("IB_CAR_VIOLATION", "carviolation","TYPENAME"));
		return "codegenerate/count/count2.jsp";
	}
	
	/**
	 * 司机情况数据分析
	 */
	@RequestMapping("exportcount2")
	public void exportcount2(@ModelAttribute Page page,@RequestParam Map<String, Object> parameterMap, Model model, HttpServletResponse response) {
		String date = (String) parameterMap.get("filter_GED_date");
		String typename = (String) parameterMap.get("filter_LIKES_typename");
		if (StringUtils.isNotBlank(typename)) {
			try {
				typename = URLDecoder.decode(typename, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (StringUtils.isBlank(date)) {
			date = DateUtil.toString(new Date());
		}
		// 司机情况数据分析
		CustomerCount customerCount2 = SqlFormat.getBySql2(date,typename);
		model.addAttribute("customerCount2", customerCount2);
		try {
			new CountExcelCommon().exportExcel2(response, customerCount2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 各车型库存盘点
	 */
	@RequestMapping("count3")
	public String count3(@ModelAttribute Page page,@RequestParam Map<String, Object> parameterMap, Model model) {
		String date = (String) parameterMap.get("filter_GED_date");
		if (StringUtils.isBlank(date)) {
			date = DateUtil.toString(new Date());
		}
		
		// 各车型库存盘点
		List<CustomerCount> customerCountList1 = SqlFormat.getBySql3(date);
		CustomerCount customerCount10 = new CustomerCount();
		
		for (CustomerCount customerCountTmp :customerCountList1) {
			customerCount10.setCount1(Integer.valueOf(customerCountTmp.getCount1()) + Integer.valueOf(customerCount10.getCount1()) + "");
			customerCount10.setCount2(Integer.valueOf(customerCountTmp.getCount2()) + Integer.valueOf(customerCount10.getCount2()) + "");
			customerCount10.setCount3(Integer.valueOf(customerCountTmp.getCount3()) + Integer.valueOf(customerCount10.getCount3()) + "");
			customerCount10.setCount4(Integer.valueOf(customerCountTmp.getCount4()) + Integer.valueOf(customerCount10.getCount4()) + "");
			customerCount10.setCount5(Integer.valueOf(customerCountTmp.getCount5()) + Integer.valueOf(customerCount10.getCount5()) + "");
		}
		
		customerCount10.setCount6(Double.valueOf(DateUtil.numFormat(customerCount10.getCount1(), customerCount10.getCount2())) + "");
		model.addAttribute("customerCountList1", customerCountList1);
		model.addAttribute("customerCount10", customerCount10);
		return "codegenerate/count/count3.jsp";
	}
	
	/**
	 * 各车型库存盘点
	 */
	@RequestMapping("exportcount3")
	public void exportcount3(@ModelAttribute Page page,@RequestParam Map<String, Object> parameterMap, Model model, HttpServletResponse response) {
		String date = (String) parameterMap.get("filter_GED_date");
		if (StringUtils.isBlank(date)) {
			date = DateUtil.toString(new Date());
		}
		
		// 各车型库存盘点
		List<CustomerCount> customerCountList1 = SqlFormat.getBySql3(date);
		CustomerCount customerCount10 = new CustomerCount();
		
		for (CustomerCount customerCountTmp :customerCountList1) {
			customerCount10.setCount1(Integer.valueOf(customerCountTmp.getCount1()) + Integer.valueOf(customerCount10.getCount1()) + "");
			customerCount10.setCount2(Integer.valueOf(customerCountTmp.getCount2()) + Integer.valueOf(customerCount10.getCount2()) + "");
			customerCount10.setCount3(Integer.valueOf(customerCountTmp.getCount3()) + Integer.valueOf(customerCount10.getCount3()) + "");
			customerCount10.setCount4(Integer.valueOf(customerCountTmp.getCount4()) + Integer.valueOf(customerCount10.getCount4()) + "");
			customerCount10.setCount5(Integer.valueOf(customerCountTmp.getCount5()) + Integer.valueOf(customerCount10.getCount5()) + "");
		}
		
		customerCount10.setCount6(Double.valueOf(DateUtil.numFormat(customerCount10.getCount1(), customerCount10.getCount2())) + "");
		try {
			new CountExcelCommon().exportExcel3(response, customerCountList1, customerCount10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 车辆出入库情况周统计表
	 */
	@RequestMapping("count4")
	public String count4(@ModelAttribute Page page,@RequestParam Map<String, Object> parameterMap, Model model) {
		String date = (String) parameterMap.get("filter_GED_date");
		if (StringUtils.isBlank(date)) {
			date = DateUtil.toString(new Date());
		}
		CustomerCount customerCount11 = new CustomerCount();
		// 车辆出入库情况周统计表
		List<CustomerCount> customerCountList2 =SqlFormat.getBySql4(date);
		for (CustomerCount customerCountTmp :customerCountList2) {
			customerCount11.setCount1(Integer.valueOf(customerCountTmp.getCount1()) + Integer.valueOf(customerCount11.getCount1()) + "");
			customerCount11.setCount2(Integer.valueOf(customerCountTmp.getCount2()) + Integer.valueOf(customerCount11.getCount2()) + "");
			customerCount11.setCount3(Integer.valueOf(customerCountTmp.getCount3()) + Integer.valueOf(customerCount11.getCount3()) + "");
			customerCount11.setCount4(Integer.valueOf(customerCountTmp.getCount4()) + Integer.valueOf(customerCount11.getCount4()) + "");
			customerCount11.setCount5(Integer.valueOf(customerCountTmp.getCount5()) + Integer.valueOf(customerCount11.getCount5()) + "");
		}
		model.addAttribute("customerCountList2", customerCountList2);
		model.addAttribute("customerCount11", customerCount11);
		return "codegenerate/count/count4.jsp";
	}
	
	/**
	 * 车辆出入库情况周统计表
	 */
	@RequestMapping("exportcount4")
	public void exportcount4(@ModelAttribute Page page,@RequestParam Map<String, Object> parameterMap, Model model, HttpServletResponse response) {
		String date = (String) parameterMap.get("filter_GED_date");
		if (StringUtils.isBlank(date)) {
			date = DateUtil.toString(new Date());
		}
		CustomerCount customerCount11 = new CustomerCount();
		// 车辆出入库情况周统计表
		List<CustomerCount> customerCountList2 =SqlFormat.getBySql4(date);
		for (CustomerCount customerCountTmp :customerCountList2) {
			customerCount11.setCount1(Integer.valueOf(customerCountTmp.getCount1()) + Integer.valueOf(customerCount11.getCount1()) + "");
			customerCount11.setCount2(Integer.valueOf(customerCountTmp.getCount2()) + Integer.valueOf(customerCount11.getCount2()) + "");
			customerCount11.setCount3(Integer.valueOf(customerCountTmp.getCount3()) + Integer.valueOf(customerCount11.getCount3()) + "");
			customerCount11.setCount4(Integer.valueOf(customerCountTmp.getCount4()) + Integer.valueOf(customerCount11.getCount4()) + "");
			customerCount11.setCount5(Integer.valueOf(customerCountTmp.getCount5()) + Integer.valueOf(customerCount11.getCount5()) + "");
		}
		model.addAttribute("customerCountList2", customerCountList2);
		model.addAttribute("customerCount11", customerCount11);
		try {
			new CountExcelCommon().exportExcel4(response, customerCountList2, customerCount11);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 维修保养周统计表
	 */
	@RequestMapping("count5")
	public String count5(@ModelAttribute Page page,@RequestParam Map<String, Object> parameterMap, Model model) {
		String date = (String) parameterMap.get("filter_GED_date");
		if (StringUtils.isBlank(date)) {
			date = DateUtil.toString(new Date());
		}
		// 维修保养周统计表
		List<CustomerCount> customerCountList3 = SqlFormat.getBySql5(date);
		CustomerCount customerCount12 = new CustomerCount();
		// 车辆出入库情况周统计表
		for (CustomerCount customerCountTmp :customerCountList3) {
			customerCount12.setCount1(Integer.valueOf(customerCountTmp.getCount1()) + Integer.valueOf(customerCount12.getCount1()) + "");
			customerCount12.setCount2(Integer.valueOf(customerCountTmp.getCount2()) + Integer.valueOf(customerCount12.getCount2()) + "");
			customerCount12.setCount3(Integer.valueOf(customerCountTmp.getCount3()) + Integer.valueOf(customerCount12.getCount3()) + "");
		}
		model.addAttribute("customerCountList3", customerCountList3);
		model.addAttribute("customerCount12", customerCount12);
		return "codegenerate/count/count5.jsp";
	}
	
	/**
	 * 维修保养周统计表
	 */
	@RequestMapping("exportcount5")
	public void exportcount5(@ModelAttribute Page page,@RequestParam Map<String, Object> parameterMap, Model model, HttpServletResponse response) {
		String date = (String) parameterMap.get("filter_GED_date");
		if (StringUtils.isBlank(date)) {
			date = DateUtil.toString(new Date());
		}
		// 维修保养周统计表
		List<CustomerCount> customerCountList3 = SqlFormat.getBySql5(date);
		CustomerCount customerCount12 = new CustomerCount();
		// 车辆出入库情况周统计表
		for (CustomerCount customerCountTmp :customerCountList3) {
			customerCount12.setCount1(Integer.valueOf(customerCountTmp.getCount1()) + Integer.valueOf(customerCount12.getCount1()) + "");
			customerCount12.setCount2(Integer.valueOf(customerCountTmp.getCount2()) + Integer.valueOf(customerCount12.getCount2()) + "");
			customerCount12.setCount3(Integer.valueOf(customerCountTmp.getCount3()) + Integer.valueOf(customerCount12.getCount3()) + "");
		}
		
		try {
			new CountExcelCommon().exportExcel5(response, customerCountList3, customerCount12);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 违章周统计表
	 */
	@RequestMapping("count6")
	public String count6(@ModelAttribute Page page,@RequestParam Map<String, Object> parameterMap, Model model) {
		String date = (String) parameterMap.get("filter_GED_date");
		if (StringUtils.isBlank(date)) {
			date = DateUtil.toString(new Date());
		}

		String startDate = DateUtil.convertWeekByMonday(date);
		String endDate = DateUtil.convertWeekBySunday(date);
		
		// 违章车辆总数
		String sql1 = SqlFormat.getSql6_1(SqlFormat.selectDateFormat, startDate, endDate);
		// 违章车辆总数
		Map<String,Object> map1 = ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForMap(sql1);
		// 违章项目
		String sql2 = SqlFormat.getSql6_2(SqlFormat.selectDateFormat, startDate, endDate,true);
		// 违章总数
		List<Map<String,Object>> list1 = ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForList(sql2);
		// 违章总数
		String sql3 = SqlFormat.getSql6_2(SqlFormat.selectDateFormat, startDate, endDate,false);
		Map<String,Object> map2 = ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForMap(sql3);
		model.addAttribute("map1", map1);
		model.addAttribute("list1", list1);
		model.addAttribute("map2", map2);
		return "codegenerate/count/count6.jsp";
	}
	
	/**
	 * 违章周统计表
	 */
	@RequestMapping("exportcount6")
	public void exportcount6(@ModelAttribute Page page,@RequestParam Map<String, Object> parameterMap, Model model, HttpServletResponse response) {
		String date = (String) parameterMap.get("filter_GED_date");
		if (StringUtils.isBlank(date)) {
			date = DateUtil.toString(new Date());
		}

		String startDate = DateUtil.convertWeekByMonday(date);
		String endDate = DateUtil.convertWeekBySunday(date);
		
		// 违章车辆总数
		String sql1 = SqlFormat.getSql6_1(SqlFormat.selectDateFormat, startDate, endDate);
		// 违章车辆总数
		Map<String,Object> map1 = ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForMap(sql1);
		// 违章项目
		String sql2 = SqlFormat.getSql6_2(SqlFormat.selectDateFormat, startDate, endDate,true);
		// 违章总数
		List<Map<String,Object>> list1 = ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForList(sql2);
		// 违章总数
		String sql3 = SqlFormat.getSql6_2(SqlFormat.selectDateFormat, startDate, endDate,false);
		Map<String,Object> map2 = ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForMap(sql3);
		try {
			new CountExcelCommon().exportExcel6(response, map1, list1,map2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	// ======================================================================
	@Resource
	public void setMessageHelper(MessageHelper messageHelper) {
		this.messageHelper = messageHelper;
	}

	@Resource
	public void setCarLoanService(CarLoanService carLoanService) {
		this.carLoanService = carLoanService;
	}

	@Resource
	public void setStoreConnector(
			com.ibusiness.doc.store.StoreConnector storeConnector) {
		this.storeConnector = storeConnector;
	}

	@Resource
	public void setCar_mgrService(Car_mgrService car_mgrService) {
		this.car_mgrService = car_mgrService;
	}

	@Resource
	public void setSpecial_driver_infoService(
			Special_driver_infoService special_driver_infoService) {
		this.special_driver_infoService = special_driver_infoService;
	}

}
