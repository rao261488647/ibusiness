package com.webservice.rs;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.codegenerate.carmgr.entity.Car_mgrEntity;
import com.codegenerate.carmgr.entity.Car_type_infoEntity;
import com.codegenerate.carmgr.service.Car_mgrService;
import com.codegenerate.carmgr.service.Car_type_infoService;
import com.codegenerate.customermgr.entity.Car_rentalEntity;
import com.codegenerate.customermgr.entity.Customer_mgrEntity;
import com.codegenerate.customermgr.service.Car_rentalService;
import com.codegenerate.customermgr.service.Customer_mgrService;
import com.codegenerate.financemgr.entity.Financial_settlementEntity;
import com.codegenerate.financemgr.entity.Flow_driver_financialEntity;
import com.codegenerate.financemgr.service.Financial_settlementService;
import com.codegenerate.financemgr.service.Flow_driver_financialService;
import com.codegenerate.special.entity.Special_driver_infoEntity;
import com.codegenerate.special.entity.Special_driver_signupEntity;
import com.codegenerate.special.entity.Special_record_mgrEntity;
import com.codegenerate.special.entity.Specialcar_use_type_mgrEntity;
import com.codegenerate.special.service.Special_driver_infoService;
import com.codegenerate.special.service.Special_driver_signupService;
import com.codegenerate.special.service.Special_record_mgrService;
import com.codegenerate.special.service.Specialcar_use_type_mgrService;
import com.dlsw.SendSms;
import com.ibusiness.base.group.dao.OrgCompanyDao;
import com.ibusiness.cms.entity.CmsArticle;
import com.ibusiness.cms.entity.IndeximgbyworksEntity;
import com.ibusiness.cms.service.CmsArticleService;
import com.ibusiness.cms.service.IndeximgbyworksService;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.spring.ApplicationContextHelper;
import com.ibusiness.security.util.SimplePasswordEncoder;
import com.webservice.alipay.Pay;
import com.webservice.weixin.controller.WxPayController;
import com.webservice.weixin.util.SystemConfig;
/**
 * webService通用接口
 * URL地址：http://localhost:8080/iBusiness/rs/cws/ + 方法名 + 参数
 * 
 * @author JiangBo
 *
 */
@Component
@Path("cws")
public class WebServiceResource {

	/**
	 * 短信下发验证码
	 * http://localhost:8080/iBusiness/rs/cws/getVerifyMessage?phoneNum=18245124726
	 */
    @SuppressWarnings("unchecked")
	@POST
    @Path("getVerifyMessage")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getVerifyMessage(@FormParam("phoneNum") String phoneNum) {
    	String checkName = "regUser";
    	JSONObject jsonObject = new JSONObject();
    	SendSms sendSms = new SendSms();
    	// 验证手机号是否非法
        if (validMobile(phoneNum) == false) {
        	jsonObject.put("status", "202");
        	jsonObject.put("errormsg", "手机号码错误");
        	return jsonObject;
        }
    	// 判断手机号是否已注册
    	List<Customer_mgrEntity> list = getCustomer_mgrService().find("from Customer_mgrEntity where customercell=?", phoneNum);
    	if (null != list && list.size() > 0) {
    		jsonObject.put("status", "201");
        	jsonObject.put("errormsg", "手机号已注册");
        	return jsonObject;
    	}
    	try {
    		// 生成6位随机数后发送短信验证码,发送失败返回错误信息
            String randomNum = getRandom6();
            String content = "您好，欢迎您注册滴滴APP，您的验证码是"+randomNum+"，请注意保存";
    		// 发送短信
        	sendSms.sendSms(phoneNum, content);
        	jsonObject.put("verifyCode", randomNum);
        	jsonObject.put("status", "200");
        	jsonObject.put("errormsg", "操作成功");
        	// 发送成功后将验证码存入到session 并将时间一同存入由","分隔.由此来控制一分钟只能发送一次
        	MemoryCommon.getInstance().verifyCodeList.put(checkName + phoneNum, randomNum);
        	MemoryCommon.getInstance().verifyCodeList.put(checkName + "sendTime", new Date().getTime());
    	} catch (Exception e) {
        	jsonObject.put("status", "500");
        	jsonObject.put("errormsg", "系统内部错误");
    	}
        return jsonObject;
    }
    /**
	 * 正则表达式验证手机号
	 * 1打头共11位数字
	 */
	private static boolean validMobile(String mobile){
		if (mobile == null) return false;
		Matcher matcher = Pattern.compile("^1\\d{10}$").matcher(mobile);
		return matcher.find();
	}
	/**
	 * 生成6位随机数
	 */
	private static String getRandom6(){
		return String.valueOf((int)(((Math.random() * 9) + 1) * 100000));
	}
	
    /**
     * 用户注册
     * http://www.99lc.com:8087/iBusiness/rs/cws/register?phoneNum=18245124726&password=1&device=1&imei=1&appSysVersion=v1.1&verifyCode=123456
	 */
    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject register(@FormParam("phoneNum") String phoneNum, @FormParam("password") String password, @FormParam("verifyCode") String verifyCode,
    		@FormParam("device") String device, @FormParam("imei") String imei, @FormParam("appSysVersion") String appSysVersion, @FormParam("token") String token) {
    	
    	Customer_mgrEntity customer = new Customer_mgrEntity();
    	// 判断验证码是否正确
    	JSONObject json = checkCode("regUser", phoneNum, verifyCode);
    	if (!"200".equals(json.getString("status"))) {
    		return json;
    	}
        
    	// 先进行校验
        if (!CommonUtils.isNull(password)) {
            if (getSimplePasswordEncoder() != null) {
                customer.setPassword(getSimplePasswordEncoder().encode(password));
            }
        } else {
        	json.put("uid", "");
            json.put("status", "500");
            json.put("errormsg", "系统内部错误");
        }
        // UUID
        customer.setId(UUID.randomUUID().toString());
        // 显示名
        customer.setCustomername(phoneNum);
        // 客户电话
        customer.setCustomercell(phoneNum);
    	// 设备型号
    	customer.setDevice(device);
    	// 设备唯一标识
    	customer.setImei(imei);
    	// 移动token
    	customer.setToken(token);
        // UUID
        customer.setId(UUID.randomUUID().toString());
        // 版本
        customer.setAppsysversion(appSysVersion);
        // 客户类别就是客户的来源
        customer.setCustomertype("手机注册");
        // 
        getCustomer_mgrService().insert(customer);

        json.put("uid", customer.getId());
        json.put("status", "200");
        json.put("errormsg", "操作成功");
        return json;
    }
    /**
     * Token上报
     * http://www.99lc.com:8087/iBusiness/rs/cws/uploadToken?phoneNum=18245124726&token=
	 */
	@SuppressWarnings("unchecked")
	@POST
    @Path("uploadToken")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject uploadToken(@FormParam("phoneNum") String phoneNum, @FormParam("token") String token) {
    	JSONObject json = new JSONObject();
    	try {
    		List<Customer_mgrEntity> list = getCustomer_mgrService().find("from Customer_mgrEntity where customercell=? ", phoneNum);
    		if (null != list && list.size() > 0) {
    			for (Customer_mgrEntity customer : list) {
    				customer.setToken(token);
    				getCustomer_mgrService().update(customer);
    			}
    			json.put("status", "200");
                json.put("errormsg", "操作成功");
    		} else {
    			json.put("status", "201");
                json.put("errormsg", "根据电话号码,为找到对应用户。");
    		}
    	} catch (Exception e) {
    		json.put("status", "500");
            json.put("errormsg", "系统异常");
    	}
        return json;
    }
    /**
     * 公用验证用户输入验证码
     * 
     * @param checkName
     *            校验名 验证注册为 "regUser" 验证重置密码为"resetPwd"
     * @param mobile
     *            手机号
     */
    private JSONObject checkCode(String checkName, String mobile, String checkCode) {
        JSONObject jsonObject = new JSONObject();
        /** 取得短信验证码发送时间,判断是否超时,2分钟内有效 **/
        Object obj = MemoryCommon.getInstance().verifyCodeList.get(checkName + "sendTime");
        if (obj == null) {
        	jsonObject.put("status", "204");
        	jsonObject.put("errormsg", "短信验证码失败未发送验证码");
        	return jsonObject;
        }
        Long oldTime = Long.valueOf(obj.toString());
        Long newtime = new Date().getTime();
        if (((newtime - oldTime) / 1000) > 120) {
        	MemoryCommon.getInstance().verifyCodeList.remove(checkName + mobile);
        	MemoryCommon.getInstance().verifyCodeList.remove(checkName + "sendTime");
            jsonObject.put("status", "205");
        	jsonObject.put("errormsg", "短信验证码失效,超过2分钟");
        	return jsonObject;
        }

        /** 比对验证码 **/
        String serverCode = MemoryCommon.getInstance().verifyCodeList.get(checkName + mobile).toString();
        if (checkCode.equals(serverCode)) {
        	MemoryCommon.getInstance().verifyCodeList.remove(checkName + mobile);
        	MemoryCommon.getInstance().verifyCodeList.remove(checkName + "sendTime");
            jsonObject.put("status", "200");
            jsonObject.put("errormsg", "操作成功");
            return jsonObject;
        } else {
        	jsonObject.put("status", "206");
        	jsonObject.put("errormsg", "验证码不匹配");
        	return jsonObject;
        }
    }
    
    /**
     * 用户登录
     * http://www.99lc.com:8087/iBusiness/rs/cws/login?phoneNum=18245124726&password=1&device=1&imei=1&appSysVersion=v1.1&token=1233211234567
	 */
    @SuppressWarnings("unchecked")
	@POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject login(@FormParam("phoneNum") String phoneNum, @FormParam("password") String password, @FormParam("device") String device,
    		@FormParam("imei") String imei, @FormParam("appSysVersion") String appSysVersion, @FormParam("token") String token) {
    	
    	JSONObject json = new JSONObject();
    	List<Customer_mgrEntity> list = getCustomer_mgrService().find("from Customer_mgrEntity where customercell=?",phoneNum);
    	if (null != list && list.size() > 0) {
    		Customer_mgrEntity bean = list.get(0);
    		if (bean.getPassword().equals(getSimplePasswordEncoder().encode(password))) {
    			
    			// 推送token
    			bean.setToken(token);
    			bean.setAppsysversion(appSysVersion);
    			bean.setImei(imei);
    			bean.setDevice(device);
    			getCustomer_mgrService().update(bean);
    			
    			json.put("uid", bean.getId());
    	        json.put("status", "200");
    	        json.put("errormsg", "操作成功");
    		} else {
    			json.put("uid", "");
                json.put("status", "201");
                json.put("errormsg", "密码无效");
    		}
    	} else {
    		json.put("uid", "");
            json.put("status", "202");
            json.put("errormsg", "用户名无效");
    	}
        return json;
    }
    /**
     * 密码修改
     * http://www.99lc.com:8087/iBusiness/rs/cws/modifyPassword?phoneNum=18245124726&oldPassword=1&newPassword=123456
	 */
    @SuppressWarnings("unchecked")
	@POST
    @Path("modifyPassword")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject modifyPassword(@FormParam("phoneNum") String phoneNum, @FormParam("oldPassword") String oldPassword, @FormParam("newPassword") String newPassword) {
    	JSONObject json = new JSONObject();
    	List<Customer_mgrEntity> list = getCustomer_mgrService().find("from Customer_mgrEntity where customercell=?",phoneNum);
    	if (null != list && list.size() > 0) {
    		Customer_mgrEntity bean = list.get(0);
    		if (bean.getPassword().equals(getSimplePasswordEncoder().encode(oldPassword))) {
    			bean.setPassword(getSimplePasswordEncoder().encode(newPassword));
    			getCustomer_mgrService().update(bean);
    			
    			json.put("uid", bean.getId());
    	        json.put("status", "200");
    	        json.put("errormsg", "操作成功");
    		} else {
    			json.put("uid", "");
                json.put("status", "201");
                json.put("errormsg", "密码无效");
    		}
    	} else {
    		json.put("uid", "");
            json.put("status", "202");
            json.put("errormsg", "用户名无效");
    	}
        return json;
    }
    // =============================================================================
    /**
	 * 专车预约报名
	 * http://localhost:8080/iBusiness/rs/cws/specCarPerRegister?cell=18245124726&name=姜博&carType=aaa&carPhoto=&selfCarUsrdYears=1&aplatForMto=滴滴&perRegisterDate=2015-02-01&perRegisterTime=3点&payRentMonth=2015-02&rentCarId=abcdefg&loginTel=13813894138
	 */
    @SuppressWarnings("unchecked")
	@POST
    @Path("specCarPerRegister")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject specCarPerRegister(@FormParam("cell") String cell, @FormParam("name") String name,
    		@FormParam("platForMto") String platForMto, @FormParam("contractTerm") String contractTerm,
    		@FormParam("perRegisterDate") String perRegisterDate, @FormParam("boardRegisterTime") String boardRegisterTime,
    		@FormParam("payRentMonth") String payRentMonth, @FormParam("rentCarId") String rentCarId, @FormParam("loginTel") String loginTel) {
    	JSONObject json = new JSONObject();
    	try {
    		List<Special_driver_signupEntity> list = getSpecial_driver_signupService().find("from Special_driver_signupEntity where cell=?",cell);
    		if (null != list && list.size() > 0) {
    			json.put("status", "201");
    	        json.put("errormsg", "该手机号已预约，请更换其他手机号！");
    	        return json;
    		}
    		
//        	// 租车列表ID, 这个id查车的信息，判断库存等的校验
//        	Specialcar_use_type_mgrEntity sutmEntity = getSpecialcar_use_type_mgrService().get(rentCarId);
//        	if (null == sutmEntity) {
//        		json.put("status", "202");
//    	        json.put("errormsg", "无此车辆");
//        	} else if ("On".equals(sutmEntity.getStatus())) {
//        		// Off:以租     On:未租 
//    	        sutmEntity.setStatus("Off");
//    	        getSpecialcar_use_type_mgrService().update(sutmEntity);
    	        
    	        // ======= 保存租车信息 =============
    	        Special_driver_signupEntity sdsEntity = new Special_driver_signupEntity();
            	sdsEntity.setId(UUID.randomUUID().toString());
            	/**姓名*/
            	sdsEntity.setName(name);
            	/**电话*/
            	sdsEntity.setCell(cell);
            	/**提交日期*/
            	sdsEntity.setSubmitdate(new Date());
//            	/**预约类型*/
//            	sdsEntity.setOrdertype(ordertype);
//            	/**车辆型号*/
//            	sdsEntity.setCartype(carType);
//            	/**自有车已使用年限*/
//            	sdsEntity.setSelfcaruseyear(selfCarUsrdYears);
//            	/**车辆图片*/
//            	sdsEntity.setCarphote(carPhoto);
            	/**想加入的平台*/
            	sdsEntity.setPlatformto(platForMto);
//            	/**欲签合同期*/
//            	sdsEntity.setPrecontractdate(precontractdate);
            	/**期望注册日期*/
            	if (!CommonUtils.isNull(perRegisterDate)) {
            		if (perRegisterDate.length() > 15) {
                		sdsEntity.setPreregisterdate(CommonUtils.getInstance().getYmdhms().parse(perRegisterDate));
                	} else {
                		sdsEntity.setPreregisterdate(CommonUtils.getInstance().getYmd().parse(perRegisterDate));
                	}
            	}
            	/**期望注册的时间*/
            	sdsEntity.setPreregistertime(boardRegisterTime);
//            	/**处理人*/
//            	sdsEntity.setDealperson(dealperson);
//            	/**处理时间*/
//            	sdsEntity.setDealtime(dealtime);
//            	/**处理结果*/
//            	sdsEntity.setDealresult(dealresult);
            	// 每月租金
            	sdsEntity.setPayrentmonth(payRentMonth);
            	sdsEntity.setLogintel(loginTel);
            	// 入口数据类型
            	sdsEntity.setInputtype("移动端");
            	getSpecial_driver_signupService().insert(sdsEntity);
            	
    	        json.put("status", "200");
    	        json.put("errormsg", "操作成功");
//        	} else if ("Off".equals(sutmEntity.getStatus())) {
//        		json.put("status", "201");
//    	        json.put("errormsg", "操作失败，所选车辆已被租用");
//        	} else {
//        		json.put("status", "203");
//    	        json.put("errormsg", "状态字段为空,无法判断是否以租");
//        	}
    	} catch (Exception e) {
            json.put("status", "500");
            json.put("errormsg", "系统内部错误");
    	}
        return json;
    }
    /**
     * 专车报名
     * http://localhost:8080/iBusiness/rs/cws/specCarRegister?cell=18245124726&name=姜博&carType=aaa&carPhoto=&selfCarUsrdYears=1&aplatForMto=滴滴&perRegisterDate=2015-02-01&perRegisterTime=3点
	 */
    @SuppressWarnings("unchecked")
	@POST
    @Path("specCarRegister")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject specCarRegister(@FormParam("cell") String cell, @FormParam("name") String name,
    		@FormParam("carType") String carType, @FormParam("carPhoto") String carPhoto,
    		@FormParam("selfCarUsrdYears") String selfCarUsrdYears, @FormParam("platForMto") String platForMto,
    		@FormParam("perRegisterDate") String perRegisterDate,@FormParam("perRegisterTime") String perRegisterTime) {
    	JSONObject json = new JSONObject();
        if (CommonUtils.isNull(cell)) {
	        json.put("status", "500");
	        json.put("errormsg", "系统内部错误");
	        return json;
		}
    	try {
    		// 
    		List<Special_driver_signupEntity> list = getSpecial_driver_signupService().find("from Special_driver_signupEntity where cell=?",cell);
    		if (null != list && list.size() > 0) {
    			json.put("status", "201");
    	        json.put("errormsg", "代表该手机号已绑定报名");
    	        return json;
    		}
    		
        	Special_driver_signupEntity sdsEntity = new Special_driver_signupEntity();
        	sdsEntity.setId(UUID.randomUUID().toString());
        	/**姓名*/
        	sdsEntity.setName(name);
        	/**电话*/
        	sdsEntity.setCell(cell);
        	/**提交日期*/
        	sdsEntity.setSubmitdate(new Date());
//        	/**预约类型*/
//        	sdsEntity.setOrdertype(ordertype);
        	/**车辆型号*/
        	sdsEntity.setCartype(carType);
        	/**自有车已使用年限*/
        	sdsEntity.setSelfcaruseyear(selfCarUsrdYears);
        	/**车辆图片*/
        	sdsEntity.setCarphote(carPhoto);
        	/**想加入的平台*/
        	sdsEntity.setPlatformto(platForMto);
//        	/**欲签合同期*/
//        	sdsEntity.setPrecontractdate(precontractdate);
        	/**期望注册日期*/
        	sdsEntity.setPreregisterdate(CommonUtils.getInstance().getYmd().parse(perRegisterDate));
        	/**期望注册的时间*/
        	sdsEntity.setPreregistertime(perRegisterTime);
//        	/**处理人*/
//        	sdsEntity.setDealperson(dealperson);
//        	/**处理时间*/
//        	sdsEntity.setDealtime(dealtime);
//        	/**处理结果*/
//        	sdsEntity.setDealresult(dealresult);
        	
        	getSpecial_driver_signupService().insert(sdsEntity);
        	
        	// 判断是否已经注册如果未注册系统帮忙注册
    		List<Customer_mgrEntity> cm_list = getCustomer_mgrService().find("from Customer_mgrEntity where customercell=?", cell);
    		if (null == cm_list || cm_list.size() < 1) {
    			Customer_mgrEntity cm_entity = new Customer_mgrEntity();
    			cm_entity.setId(UUID.randomUUID().toString());
    			//专车报名
    			/**客户姓名*/
    			cm_entity.setCustomername(sdsEntity.getName());
    			/**客户电话*/
    			cm_entity.setCustomercell(sdsEntity.getCell());
    			/**客户类别*/
    			cm_entity.setCustomertype("专车报名");
    			/**客户归属*/
    			cm_entity.setCustomertobe("专车司机");
    			/**密码*/
    			cm_entity.setPassword(getSimplePasswordEncoder().encode("123456"));
    			// 推荐人
    			cm_entity.setRecommendman("专车报名");
    			// 插入
    			getCustomer_mgrService().insert(cm_entity);
    		}
    		
	        json.put("status", "200");
	        json.put("errormsg", "操作成功");
    	} catch (Exception e) {
            json.put("status", "500");
            json.put("errormsg", "系统内部错误");
    	}
        return json;
    }
    /**
	 * 租车列表
	 * http://localhost:8080/iBusiness/rs/cws/rentCarList?cell=18245124726&currentPage=1&loadCount=10
	 */
	@SuppressWarnings("unchecked")
	@GET
	@POST
    @Path("rentCarList")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject rentCarList(@FormParam("cell") String cell, @FormParam("currentPage") String currentPage, @FormParam("loadCount") String loadCount) {
		JSONObject json = new JSONObject();
		// 翻页
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
		Page page = new Page();
		page.setPageNo(Integer.parseInt(currentPage));
		page.setPageSize(Integer.parseInt(loadCount));
		page = getCar_type_infoService().pagedQuery(page, propertyFilters);
		
    	List<Car_type_infoEntity> car_type_infoList = (List<Car_type_infoEntity>) page.getResult();
    	// 
    	json.put("totoalRecord", page.getTotalCount());
    	json.put("status", "200");
        json.put("errormsg", "操作成功");
        JSONArray array = new JSONArray();
    	for (Car_type_infoEntity ctiEntity : car_type_infoList) {
//    		// 入库类型
//    		String neworold = null; TODO
//    		if ("新车入库".equals(carmgrEntity.getIntype())) {
//    			neworold = "全新";
//    		} else {
//    			neworold = "旧车";
//    		}
//    		// 型号名称 
    		List<Specialcar_use_type_mgrEntity> sutmlist = getSpecialcar_use_type_mgrService().find("from Specialcar_use_type_mgrEntity where cartype=? AND status !='Off' ",ctiEntity.getTypename());
    		if (null!=sutmlist && sutmlist.size() > 0) {
    			Specialcar_use_type_mgrEntity sutmEntity = sutmlist.get(0);
    			JSONObject sjson = new JSONObject();
    			//
    			sjson.put("rentCarId", sutmEntity.getId());
    			// 图片URL
    			if (CommonUtils.isNull(ctiEntity.getUploadurl())) {
    				sjson.put("url", "");
    			} else {
    				sjson.put("url", SystemConfig.LOCAL_SERVICE + "/ibresources/ibimg/" + ctiEntity.getUploadurl());
    			}
    			// 车型名称
    			sjson.put("carType", ctiEntity.getTypename());
    			// deposit 汽车押金
        		sjson.put("deposit", sutmEntity.getCardeposit());
    			// contractTerm
        		sjson.put("contractTerm", "{\"threeMon\":\""+sutmEntity.getContrforthreemth()+"元\",\"sixMon\":\""+sutmEntity.getContrforsixmth()+"元\",\"oneYear\":\""+sutmEntity.getContrforsoneyear()+"元\",\"twoMon\":\""+sutmEntity.getContrforstwoyear()+"元\"}");
        		// 使用平台
        		sjson.put("plat", sutmEntity.getPlat()); 
        		array.add(sjson);
    		}
    	}
        
    	json.put("data", array);
        return json;
    }
	/**
	 * 租车预约司机报名
	 * http://www.99lc.com:8087/iBusiness/rs/cws/orderRentToBeDriver?cell=18245124726&name=博博&platform=啊啊&termDate=2016-01-15&signDate=2016-01-15&regTime=2016-01-15
	 */
	@SuppressWarnings("unchecked")
	@POST
    @Path("orderRentToBeDriver")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject orderRentToBeDriver(@FormParam("cell") String cell, @FormParam("name") String name, @FormParam("platform") String platform,
    		@FormParam("termDate") String termDate, @FormParam("signDate") String signDate, @FormParam("regTime") String regTime) {
		JSONObject json = new JSONObject();
		if (CommonUtils.isNull(cell)) {
			json.put("status", "500");
            json.put("errormsg", "系统内部错误");
			return json;
		}
		List<Car_rentalEntity> list = getCar_rentalService().find("from Car_rentalEntity where telephone = ?",cell);
		if (null != list && list.size() > 0) {
			json.put("status", "201");
            json.put("errormsg", "代表该手机号已预约");
			return json;
		} else {
			Car_rentalEntity crEntity = new Car_rentalEntity();
			crEntity.setId(UUID.randomUUID().toString());
			crEntity.setName(name);
			crEntity.setTelephone(cell);
			// 
			crEntity.setPlatform(platform);
			try {
				crEntity.setTermdate(CommonUtils.getInstance().getYmd().parse(termDate));
				crEntity.setSigndate(CommonUtils.getInstance().getYmd().parse(signDate));
				crEntity.setRegtime(CommonUtils.getInstance().getYmd().parse(regTime));
			} catch (ParseException e) {
				json.put("status", "500");
	            json.put("errormsg", "系统内部错误");
			}
			// 插入
			getCar_rentalService().insert(crEntity);
			json.put("status", "200");
            json.put("errormsg", "操作成功");
			return json;
		}
    }
	// ==================================================
	/**
	 * 我的专车信息
	 * http://localhost:8080/iBusiness/rs/cws/mySpecCarInfo?cell=18245124726
	 */
    @SuppressWarnings("unchecked")
	@POST
    @Path("mySpecCarInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject mySpecCarInfo(@FormParam("cell") String cell) {
    	JSONObject json = new JSONObject();
    	List<Special_driver_infoEntity> list = getSpecial_driver_infoService().find("from Special_driver_infoEntity where account=?",cell);
    	if (null != list && list.size() > 0) {
    		Special_driver_infoEntity bean = list.get(0);
    		json.put("status", "200");
	        json.put("errormsg", "操作成功");
    		json.put("name", bean.getName());
    		// 驾照初领日期
    		json.put("firstGetCarProfileDate", bean.getPredriverdate());
    		// 驾驶证档案号
    		json.put("carProfileNum", bean.getDriverfilenum());
    		// 是否培训
    		json.put("isTrainning", "是");
//    		// 专车平台
//    		json.put("specCarNature", "是");
    		// 合同生效日
    		json.put("registerDate", bean.getContractvalidday());
    	} else {
            json.put("status", "201");
            json.put("errormsg", "用户名无效,未注册专车");
    	}
        return json;
    }
    
	// ==================================================
	/**
	 * 公告列表信息
	 * cell 手机号
	 * http://localhost:8080/iBusiness/rs/cws/getAnnouncementList?cell=18245124726&currentPage=2&loadCount=3
	 */
	@SuppressWarnings("unchecked")
	@POST
    @Path("getAnnouncementList")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getAnnouncementList(@FormParam("cell") String cell, @FormParam("currentPage") String currentPage, @FormParam("loadCount") String loadCount) {
		
		JSONObject json = new JSONObject();
		// 公告文章管理
		// 翻页
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
		Page page = new Page();
		page.setPageNo(Integer.parseInt(currentPage));
		page.setPageSize(Integer.parseInt(loadCount));
		page = getCmsArticleService().pagedQuery(page, propertyFilters);
    	List<CmsArticle> cmsArticleList = (List<CmsArticle>) page.getResult();
    	
    	json.put("totoalRecord", page.getTotalCount());
		json.put("status", "200");
        json.put("errormsg", "操作成功");
        JSONArray jsonArray = new JSONArray();
		for (CmsArticle cmsArticle : cmsArticleList) {
			JSONObject cmsjson = new JSONObject();
			cmsjson.put("name", cmsArticle.getTitle());
			cmsjson.put("detail", cmsArticle.getContent());
			cmsjson.put("date", CommonUtils.getInstance().getYmdhms().format(cmsArticle.getCreateTime()));
			jsonArray.add(cmsjson);
		}
		json.put("data", jsonArray);
    	// 
    	List<IndeximgbyworksEntity> list = getIndeximgbyworksService().find("from IndeximgbyworksEntity where showflag!='否'");
    	JSONArray imgArray = new JSONArray();
    	if (null != list && list.size() > 0) {
    		IndeximgbyworksEntity bean = list.get(0);
    		// 图片1
    		JSONObject img1Json = new JSONObject();
    		if (!CommonUtils.isNull(bean.getImgurl())) {
        		img1Json.put("url", SystemConfig.LOCAL_SERVICE + "/ibresources/imgworks/" + bean.getImgurl());
    		} else {
    			img1Json.put("url", "");
    		}
    		imgArray.add(img1Json);
    		// 图片2
    		JSONObject img2Json = new JSONObject();
    		if (!CommonUtils.isNull(bean.getImgurl2())) {
        		img2Json.put("url", SystemConfig.LOCAL_SERVICE + "/ibresources/imgworks/" + bean.getImgurl2());
    		} else {
    			img2Json.put("url", "");
    		}
    		imgArray.add(img2Json);
    		// 图片3
    		JSONObject img3Json = new JSONObject();
    		if (!CommonUtils.isNull(bean.getImgurl3())) {
        		img3Json.put("url", SystemConfig.LOCAL_SERVICE + "/ibresources/imgworks/" + bean.getImgurl3());
    		} else {
    			img3Json.put("url", "");
    		}
    		imgArray.add(img3Json);
    		// 图片4
    		JSONObject img4Json = new JSONObject();
    		if (!CommonUtils.isNull(bean.getImgurl4())) {
        		img4Json.put("url", SystemConfig.LOCAL_SERVICE + "/ibresources/imgworks/" + bean.getImgurl4());
    		} else {
    			img4Json.put("url", "");
    		}
    		imgArray.add(img4Json);
    		// 图片5
    		JSONObject img5Json = new JSONObject();
    		if (!CommonUtils.isNull(bean.getImgurl5())) {
        		img5Json.put("url", SystemConfig.LOCAL_SERVICE + "/ibresources/imgworks/" + bean.getImgurl5());
    		} else {
    			img5Json.put("url", "");
    		}
    		imgArray.add(img5Json);
    	}
    	// 首页轮播图片
		json.put("url", imgArray);
        return json;
    }
    
	/**
	 * 缴费信息列表
	 * http://localhost:8080/iBusiness/rs/cws/payFeeList?cell=13971345817
	 */
    @SuppressWarnings("unchecked")
	@POST
    @Path("payFeeList")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject payFeeList(@FormParam("cell") String cell) {
    	JSONObject json = new JSONObject();
    	// 查询合同
    	try {
    		List<Special_driver_infoEntity> list = getSpecial_driver_infoService().find("from Special_driver_infoEntity where account=?",cell);
        	if (null != list && list.size() > 0) {
        		Special_driver_infoEntity bean = list.get(0);
        		json.put("status", "200");
    	        json.put("errormsg", "操作成功");
    	        // 合同期限
    	        json.put("conTerm", bean.getContractterm());
    	        // 合同生效日期
    	        json.put("conValidDate", CommonUtils.getInstance().getYmd().format(bean.getContractvalidday()));
                // 每月租金
    	        json.put("perMonthRent", bean.getPermonthrent());
    	        // 本月应交费用
    	        json.put("curSdFee", bean.getPermonthrent());
    	        // 本月已交费用
    	        Double cerrPayMoney = 0.0;
    	        List<Financial_settlementEntity> fsmonthList = getFinancial_settlementService().find("from Financial_settlementEntity where telephone=? AND eventdate>? order by monthdate ", cell, CommonUtils.getInstance().getYmd().parse(CommonUtils.getInstance().getYm().format(new Date())+"-01"));
    	        if (null!=fsmonthList && fsmonthList.size() > 0) {
    	        	for (Financial_settlementEntity entity : fsmonthList) {
    	        		cerrPayMoney = cerrPayMoney + Double.parseDouble(entity.getAmount());
    	        	}
    	        }
                json.put("cerrPayMoney", cerrPayMoney);
    	        // 本月剩余费用
                if (!CommonUtils.isNull(bean.getPermonthrent())) {
                	json.put("curMonFee", Double.parseDouble(bean.getPermonthrent()) -cerrPayMoney);
                } else {
                	json.put("curMonFee","0");
                }
    	        // 已签押金总额
    	        json.put("signedCortAmount", bean.getContractsumdeposit());
    	        // 已交押金
    	        json.put("payCort", bean.getCardeposit());
    	        // 合同终止日期
    	        json.put("cortEndDate", CommonUtils.getInstance().getYmd().format(bean.getContractoverdate()));
    	        // 每月交租日
    	        json.put("perPayDay", bean.getPermonthday());
    	        // 取得财务结算信息
    	        List<Financial_settlementEntity> fsList = getFinancial_settlementService().find("from Financial_settlementEntity where telephone=? AND eventdate is not null order by eventdate DESC ", cell);
    	        JSONArray fsArray = new JSONArray();
    	        JSONArray fsMonthArray = new JSONArray();
    	        for (Financial_settlementEntity entity : fsList) {
    	        	JSONObject fsJson = new JSONObject();
    	        	// 缴费状态
    	        	fsJson.put("status", entity.getPaymsg());
        	        // 缴费日期
    	        	fsJson.put("payDate", CommonUtils.getInstance().getYmd().format(entity.getEventdate()));
        	        // 缴费金额
    	        	fsJson.put("payMoney", entity.getAmount());
        	        fsArray.add(fsJson);
        	        
        	        // 月缴费
        	        if (!CommonUtils.isNull(entity.getMonthdate())) {
        	        	JSONObject fsMonthJson = new JSONObject();
            	        // 缴费月份
            	        fsMonthJson.put("month", entity.getMonthdate());
            	        // 缴费金额
            	        fsMonthJson.put("amount", entity.getAmount());
            	        fsMonthArray.add(fsMonthJson);
        	        }
    	        }
    	        json.put("payList", fsArray);
    	        // 
    	        json.put("payMonth", fsMonthArray);
    	        
        	} else {
        		json.put("status", "201");
                json.put("errormsg", "用户名无效,未注册专车");
        	}
    	} catch (Exception e) {
    		json.put("status", "500");
            json.put("errormsg", "系统内部错误");
    	}
    	
        return json;
    }
             
    /**
 	 * 缴费
 	 * http://localhost:8080/iBusiness/rs/cws/payFeeSubmit?cell=18245124726&month=2016-01&type=买车&payType=支付宝支付&money=500.2&remark=备注信息111
 	 */
 	 @SuppressWarnings("unchecked")
	 @POST
     @Path("payFeeSubmit")
     @Produces(MediaType.APPLICATION_JSON)
     public JSONObject payFeeSubmit(@FormParam("cell") String cell, @FormParam("month") String month, @FormParam("type") String type, @FormParam("payType") String payType, @FormParam("money") String money, @FormParam("remark") String remark) {
     	JSONObject json = new JSONObject();
     	if (CommonUtils.isNull(payType)) {
     		json.put("status", "202");
            json.put("errormsg", "缴费失败,payType支付类型为空.");
            return json;
     	}
     	// 
        try {
        	Financial_settlementEntity entity = new Financial_settlementEntity();
        	entity.setId(UUID.randomUUID().toString());
	        entity.setOrderformid(cell + CommonUtils.formatTime("yyyyMMddHHmmss", new Date()).toString());
	        entity.setAmount(money);
	        entity.setTelephone(cell);
	        List<Customer_mgrEntity> cmlist = getCustomer_mgrService().find("from Customer_mgrEntity where customercell=?", cell);
	        if (null != cmlist && cmlist.size() > 0) {
	        	entity.setCustomername(cmlist.get(0).getCustomername());
	        } else {
	        	entity.setCustomername(cell);
	        }
	        entity.setMonthdate(month);
	        entity.setSetbusinesstype(type);
	        if ("2".equals(payType) || payType.indexOf("微信") > -1) {
	     	    // 微信
	     		entity.setPaymsg("微信");
	     	} else {
	     	    // 支付宝
	     		entity.setPaymsg("支付宝");
	     	}
	        entity.setRemark(remark);
	        
	        entity.setEventdate(new Date());
	     	// 插入
	     	getFinancial_settlementService().insert(entity);
        	// 调用支付接口
	     	JSONObject payinfo = new JSONObject();
     		if ("2".equals(payType) || payType.indexOf("微信") > -1) {
	     	    // 微信
	     		payinfo = getPay("2", entity.getOrderformid());
	     	} else {
	     	    // 支付宝
	     		payinfo = getPay("1", entity.getOrderformid());
	     	}
     		if ("0000".equals(payinfo.get("stateCode"))) {
    	     	json.put("status", "200");
    	        json.put("errormsg", payinfo.get("errMsg"));
    	        // 后台拼接支付参数
    	     	json.put("payinfo", payinfo.get("datas"));
     		} else {
     			json.put("status", payinfo.get("stateCode"));
    	        json.put("errormsg", payinfo.get("errMsg"));
     		}
        } catch (Exception e) {
        	json.put("status", "201");
            json.put("errormsg", "缴费失败");
        }
        return json;
    }
   /**
 	 * 微信、支付宝支付接口
 	 * @param payMethod			为1时 支付宝  为2时微信
 	 * @param orderFormId		订单id
 	 * http://localhost:8080/iBusiness/rs/cws/getPay?payMethod=1&orderFormId=admin20160114134504
 	 */
 	@SuppressWarnings("unchecked")
 	@Path("getPay")
 	@POST
 	@Produces(MediaType.APPLICATION_JSON)
     public JSONObject getPay(@FormParam("payMethod") String payMethod, @FormParam("orderFormId") String orderFormId) {
 		JSONObject jsonObject = new JSONObject();
 		// 判断之前是否支付过
 		List<Financial_settlementEntity> orderformList = getFinancial_settlementService().find("from Financial_settlementEntity where orderformid=?", orderFormId);
 		// 
 		String msg = "";
 		if(null != orderformList && orderformList.size() == 1) {
 			if("1".equals(orderformList.get(0).getIspay())){
 				jsonObject.put("stateCode", "1003");
 				jsonObject.put("errMsg", "该订单已支付过!");
 				return jsonObject;
 			}
 			Financial_settlementEntity fsEntity = orderformList.get(0);
 			// 支付金额
 			String totalPrice = fsEntity.getAmount();
 			// 支付宝
 			if("1".equals(payMethod)){
 				String pay = new Pay().pay(orderFormId, msg, totalPrice);
 				jsonObject.put("datas", pay);
 				jsonObject.put("stateCode", "0000");
 				jsonObject.put("errMsg", "支付宝支付");
 				return jsonObject;
 			}else if("2".equals(payMethod)){
 				// 微信
 				WxPayController wxPayController = new WxPayController();
 				String out_trade_no = wxPayController.getOutTradeNo();
 				String returnstr = wxPayController.requestScanPayService(out_trade_no, totalPrice, orderFormId);
 				jsonObject.put("datas", returnstr);
 				jsonObject.put("stateCode", "0000");
 				jsonObject.put("errMsg", "微信支付");
 				return jsonObject;
 			}
 		} else {
 			jsonObject.put("stateCode", "1002");
 			jsonObject.put("errMsg", "payMethod传值错误,根据提供的订单ID未查询到此单。");
 		}
 		return jsonObject;
 	}
    // ============================================================
	/**
	 * 合同信息
	 * http://localhost:8080/iBusiness/rs/cws/contractInfo?tel=18245124726
	 */
	@SuppressWarnings("unchecked")
	@POST
	@Path("contractInfo")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject contractInfo(@FormParam("tel") String tel) {
		JSONObject json = new JSONObject();
		try {
			// 合同
			List<Special_driver_infoEntity> list = getSpecial_driver_infoService().find("from Special_driver_infoEntity where cellphone=?", tel);
			if (null != list && list.size() > 0) {
				Special_driver_infoEntity bean = list.get(0);
				// 签订人
				json.put("signPerson", bean.getName());
				// 签订日期
				json.put("signDate", CommonUtils.getInstance().getYmd().format(bean.getContractvalidday()));
				// 车辆品牌
				json.put("carBrand", bean.getCarname());
				// 型号
				json.put("model", bean.getCarname());
				// 车牌号
				json.put("carNum", bean.getCarnum());
				// 绑定手机号
				json.put("bandTel", bean.getCellphone());
				// 每月租金
				json.put("perMonMoney", bean.getPermonthrent());
				// 押金金额
				json.put("depositMoney", bean.getContractsumdeposit());
				// 每月交租日
				json.put("perPayDay", bean.getPermonthday());
				// 已交押金
				json.put("dealDeposit", bean.getCardeposit());
				// 上期交租日
				json.put("lastPayDate", "");
				// 缴费状态
				json.put("status", bean.getStatus());
				// 滴滴专车
				String platformto = bean.getPlatformto();
				json.put("didi", (platformto.indexOf("滴滴")>-1 ? "滴滴":""));
				// 优步专车
				json.put("yobu", (platformto.indexOf("优步")>-1 ? "优步":""));
				// 一号专车
				json.put("yiho", (platformto.indexOf("一号")>-1 ? "一号专车":""));
				// 其他平台
				json.put("otherPlatform", "");
				// 专车状态
				json.put("specCarState", bean.getStatus());
				// 备注
				json.put("remark", "");
				
				json.put("status", "200");
				json.put("errormsg", "操作成功");
			} else {
				json.put("status", "201");
				json.put("errormsg", "未查到相关信息");
			}
		} catch (Exception e) {
			json.put("status", "500");
			json.put("errormsg", "系统异常");
		}
		return json;
	}
	/**
	 * 解除合同
	 * http://localhost:8080/iBusiness/rs/cws/dissContract?tel=18245124726
	 */
	@SuppressWarnings("unchecked")
	@POST
	@Path("dissContract")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject dissContract(@FormParam("tel") String tel) {
		JSONObject json = new JSONObject();
		try {
			// 合同
			List<Special_driver_infoEntity> list = getSpecial_driver_infoService().find("from Special_driver_infoEntity where cellphone=?", tel);
			if (null != list && list.size() > 0) {
				Special_driver_infoEntity bean = list.get(0);
				bean.setStatus("已解约");
				getSpecial_driver_infoService().update(bean);
				json.put("status", "200");
				json.put("errormsg", "操作成功");
				// 修改车辆状态为【在库】
		        List<Car_mgrEntity> carmgrlist = getCar_mgrService().find("from Car_mgrEntity where carnum=?", bean.getCarnum());
		        if (carmgrlist != null && carmgrlist.size() > 0) {
		        	Car_mgrEntity carMgrEntity = carmgrlist.get(0);
		        	carMgrEntity.setCarstatus("在库");
		        	getCar_mgrService().update(carMgrEntity);
		        }
			} else {
				json.put("status", "201");
				json.put("errormsg", "未查到相关信息");
			}
		} catch (Exception e) {
			json.put("status", "500");
			json.put("errormsg", "系统异常");
		}
		return json;
	}
    // ==================================================
 	/**
     * 个人信息
     * http://www.99lc.com:8087/iBusiness/rs/cws/profileInfo?tel=18245124726
	 */
    @SuppressWarnings("unchecked")
	@POST
    @Path("profileInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject profileInfo(@FormParam("tel") String tel) {
    	JSONObject json = new JSONObject();
    	try {
    		List<Special_driver_infoEntity> list = getSpecial_driver_infoService().find("from Special_driver_infoEntity where cellphone=? or logintel=?", tel, tel);
        	if (null != list && list.size() > 0) {
        		JSONArray jsonArray = new JSONArray();
        		for (Special_driver_infoEntity bean : list) {
        			JSONObject sdjson = new JSONObject();
        			// 姓名
        			sdjson.put("name", bean.getName());
                	// 电话
        			sdjson.put("tel", bean.getCellphone());
        			// 登录电话
                	sdjson.put("loginTel", bean.getLogintel());
                	// 首次领取驾照时间
        			sdjson.put("firstGetProDate", CommonUtils.getInstance().getYmd().format(bean.getPredriverdate()));
                	// 驾照号码
        			sdjson.put("driveNum", bean.getDrivernum());
                	// 专车性质
        			sdjson.put("speDeiverProperty", bean.getDrivertype());
                	// 注册时间
                	sdjson.put("regDate", CommonUtils.getInstance().getYmd().format(bean.getContractvalidday()));
                	jsonArray.add(sdjson);
        		}
            	json.put("profile", jsonArray);
        	}
        	json.put("status", "200");
            json.put("errormsg", "操作成功");
    	} catch (Exception e) {
    		json.put("status", "500");
            json.put("errormsg", "系统异常");
    	}
        return json;
    }
    /**
     * 流水列表
     * http://localhost:8080/iBusiness/rs/cws/statememtList?tel=18245124726&date=2016-01-18
	 */
    @SuppressWarnings("unchecked")
	@POST
    @Path("statememtList")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject statememtList(@FormParam("tel") String tel, @FormParam("date") String date, @FormParam("currentPage") String currentPage, @FormParam("loadCount") String loadCount) {
    	JSONObject json = new JSONObject();
//    	// 如果这个用户没有签合同就不能提交流水
//    	List<Special_driver_infoEntity> sdiList = getSpecial_driver_infoService().find("from Special_driver_infoEntity where status!='已解约' AND cellphone=?", tel);
//    	if (null == sdiList || sdiList.size() < 1) {
//    		json.put("status", "209");
//            json.put("errormsg", "成为通途专车司机，才可提交流水，谢谢！");
//            return json;
//    	}
    	// 
    	if (CommonUtils.isNull(date) || date.length() < 6) {
    		json.put("status", "201");
            json.put("errormsg", "月份参数传递异常:" + date);
            return json;
    	}
    	// 
		try {
			String monthStr = "";
			if (date.length() > 7) {
				monthStr = date.substring(0, 7);
			} else {
				monthStr = date;
			}
			Date monthDateBegin = CommonUtils.getInstance().getYm().parse(monthStr);
			Calendar calendar = Calendar.getInstance();//日历对象
	    	calendar.setTime(monthDateBegin);//设置当前日期
	    	calendar.add(Calendar.MONTH, 1);
	    	Date monthDateEnd = calendar.getTime();
	    	
	    	// 指定月份数据
	    	List<Flow_driver_financialEntity> monthList = getFlow_driver_financialService().find("from Flow_driver_financialEntity where telephone=? and eventdate >=? and eventdate < ? order by eventdate desc", tel, monthDateBegin, monthDateEnd);
	    	JSONArray mdArray = new JSONArray();
	    	for (Flow_driver_financialEntity bean : monthList) {
	    		JSONObject monthJson = new JSONObject();
	    		monthJson.put("money", bean.getAmount());
	    		// 平台
	    		monthJson.put("platform", bean.getPlatform());
	    		// 时间
	    		monthJson.put("date", CommonUtils.getInstance().getYmd().format(bean.getEventdate()));
	    		mdArray.add(monthJson);
	    	}
	    	// 月明细
			json.put("monthDetail", mdArray);
	    	
	    	JSONArray curMonthArray = new JSONArray();
	    	JSONArray curWeekArray = new JSONArray();
//	    	JSONArray recMonthArray = new JSONArray();
	    	// 
	    	calendar.setTime(getTimesMonthmorning());//设置当前日期
	    	calendar.add(Calendar.SECOND, -1);
	    	Date monthMorning = calendar.getTime();
	    	// 
	    	Map<String, Object> parameterMap = new HashMap<String, Object>();
			List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
	    	Page page = new Page();
	    	if (CommonUtils.isNull(currentPage)) {
	    		currentPage = "1";
	    	}
			page.setPageNo(Integer.parseInt(currentPage));
			if (CommonUtils.isNull(loadCount)) {
				loadCount = "10";
	    	}
			page.setPageSize(Integer.parseInt(loadCount));
			page = getFlow_driver_financialService().pagedQuery(page, propertyFilters);
	    	List<Car_mgrEntity> carListgetCar = (List<Car_mgrEntity>) page.getResult();
	    	
	    	List<Flow_driver_financialEntity> list = getFlow_driver_financialService().find("from Flow_driver_financialEntity where telephone=? and eventdate >=? order by eventdate desc", tel, monthMorning);
	    	// 本月流水合计
	    	Double curStatementSum = 0.0;
	    	// 本周流水合计
	    	Double curWeekStateSum = 0.0;
	    	for (Flow_driver_financialEntity bean : list) {
	    		// 本周
	    		if (bean.getEventdate().equals(getTimesWeekmorning()) || bean.getEventdate().after(getTimesWeekmorning())) {
	    			curWeekStateSum = curWeekStateSum + Double.parseDouble(CommonUtils.isNull(bean.getAmount())?"0":bean.getAmount());
	    			curStatementSum = curStatementSum + Double.parseDouble(CommonUtils.isNull(bean.getAmount())?"0":bean.getAmount());
	    			// 周
	    			JSONObject curWeekjson = new JSONObject();
	    			// 金额
	    			curWeekjson.put("money", bean.getAmount());
    	    		// 平台
	    			curWeekjson.put("platform", bean.getPlatform());
    	    		// 时间
	    			if (null!=bean.getEventdate()) {
	    				curWeekjson.put("date", CommonUtils.getInstance().getYmd().format(bean.getEventdate()));
	    			}
	    			curWeekArray.add(curWeekjson);
    				
	    			// 月
	    			JSONObject curMonthjson = new JSONObject();
	    			// 金额
    				curMonthjson.put("money", bean.getAmount());
    	    		// 平台
    				curMonthjson.put("platform", bean.getPlatform());
    	    		// 时间
    				if (null!=bean.getEventdate()) {
    					curMonthjson.put("date", CommonUtils.getInstance().getYmd().format(bean.getEventdate()));
	    			}
    				curMonthArray.add(curMonthjson);
	    		} else if (bean.getEventdate().after(monthMorning)) {
	    			// 月
    				JSONObject curMonthjson = new JSONObject();
    				// 金额
    				curMonthjson.put("money", bean.getAmount());
    	    		// 平台
    				curMonthjson.put("platform", bean.getPlatform());
    	    		// 时间
    				if (null!=bean.getEventdate()) {
    					curMonthjson.put("date", CommonUtils.getInstance().getYmd().format(bean.getEventdate()));
	    			}
    				curMonthArray.add(curMonthjson);
    				// 
    				curStatementSum = curStatementSum + Double.parseDouble(CommonUtils.isNull(bean.getAmount())?"0":bean.getAmount());
    			}
//	    		else {
//    				JSONObject recMonthJson = new JSONObject();
//    				// 金额
//    				recMonthJson.put("money", bean.getAmount());
//    	    		// 平台
//    				recMonthJson.put("platform", bean.getPlatform());
//    	    		// 时间
//    				recMonthJson.put("date", CommonUtils.getInstance().getYmd().format(bean.getEventdate()));
//    				recMonthArray.add(recMonthJson);
//    			}
	    		// 本月流水明细
	    		json.put("curMonthDeail", curMonthArray);
	    		// 本周流水明细
	    		json.put("curWeekStateDetail", curWeekArray);
//	    		// 最近月份流水
//	    		json.put("recMonthState", recMonthArray);
	    	}
	    	// 本月流水合计
    		json.put("curStatementSum", curStatementSum);
    		// 本周流水合计
    		json.put("curWeekStateSum", curWeekStateSum);
    		// 
        	json.put("status", "200");
            json.put("errormsg", "操作成功");
    	} catch (Exception e) {
    		json.put("status", "500");
            json.put("errormsg", "系统异常");
    	}
        return json;
    }
    /**
     * 提交流水
     * http://localhost:8080/iBusiness/rs/cws/statementSubmit?tel=18245124726&platform=滴滴,快滴&didiAmount=200.5&youbuAmount=200.5&date=2016-01-18 20:00:00
	 */
    @SuppressWarnings("unchecked")
	@POST
    @Path("statementSubmit")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject statementSubmit(@FormParam("tel") String tel, @FormParam("platform") String platform, @FormParam("didiAmount") String didiAmount, @FormParam("youbuAmount") String youbuAmount, @FormParam("date") String date) {
    	JSONObject json = new JSONObject();
    	// 如果这个用户没有签合同就不能提交流水
    	List<Special_driver_infoEntity> sdiList = getSpecial_driver_infoService().find("from Special_driver_infoEntity where status!='已解约' AND cellphone=?", tel);
    	if (null == sdiList || sdiList.size() < 1) {
    		json.put("status", "209");
            json.put("errormsg", "成为通途专车司机，才可提交流水，谢谢！");
            return json;
    	}
    	try {
    		// 查询今天是否上报过流水
    		List<Flow_driver_financialEntity> fdfList =  getFlow_driver_financialService().find("from Flow_driver_financialEntity where telephone=? AND eventdate=? ", tel , CommonUtils.getInstance().getYmd().parse(date));
    		if (null != fdfList && fdfList.size() > 0) {
    			// 更新
    			for (Flow_driver_financialEntity entity : fdfList) {
    				if ("滴滴".equals(entity.getPlatform())) {
    					entity.setAmount(didiAmount);
    					getFlow_driver_financialService().update(entity);
    				} else {
    					entity.setAmount(youbuAmount);
    					getFlow_driver_financialService().update(entity);
    				}
        		}
    		} else {
    			// 新建
    	    	List<Customer_mgrEntity> cmlist = getCustomer_mgrService().find("from Customer_mgrEntity where customercell=?", tel);
    	    	String customername="";
    	    	if (null != cmlist && cmlist.size() > 0) {
    	    		customername = cmlist.get(0).getCustomername();
    	        }
    	    	// ========滴滴平台金额============
    	    	Flow_driver_financialEntity entity1 = new Flow_driver_financialEntity();
    	    	entity1.setId(UUID.randomUUID().toString());
    	    	if (CommonUtils.isNull(customername)) {
    	        	entity1.setCustomername(tel);
    	        } else {
    	        	entity1.setCustomername(customername);
    	        }
    	        if (null != date && date.length() > 8) {
    	        	entity1.setEventdate(CommonUtils.getInstance().getYmd().parse(date));
    	        } else {
    	        	entity1.setEventdate(new Date());
    	        }
    	    	entity1.setTelephone(tel);
    	    	entity1.setPlatform("滴滴");
    	    	entity1.setAmount(didiAmount);
    	    	getFlow_driver_financialService().insert(entity1);
    	    	// ====优步平台金额==================================
    	    	Flow_driver_financialEntity entity2 = new Flow_driver_financialEntity();
    	    	entity2.setId(UUID.randomUUID().toString());
    	    	if (CommonUtils.isNull(customername)) {
    	    		entity2.setCustomername(tel);
    	        } else {
    	        	entity2.setCustomername(customername);
    	        }
    	        if (null != date && date.length() > 8) {
    	        	entity2.setEventdate(CommonUtils.getInstance().getYmd().parse(date));
    	        } else {
    	        	entity2.setEventdate(new Date());
    	        }
    	        entity2.setTelephone(tel);
    	        entity2.setPlatform("优步");
    	    	entity2.setAmount(youbuAmount);
    	    	getFlow_driver_financialService().insert(entity2);
    		}
	    	// ======================================================
        	json.put("status", "200");
            json.put("errormsg", "操作成功");
    	} catch (Exception e) {
    		json.put("status", "500");
            json.put("errormsg", "系统异常");
    	}
        return json;
    }
    // ===============================================
    /**
     * 通知列表
     * http://localhost:8080/iBusiness/rs/cws/noticeList?tel=18245124726
	 */
    @SuppressWarnings("unchecked")
	@POST
    @Path("noticeList")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject noticeList(@FormParam("tel") String tel) {
    	JSONObject json = new JSONObject();
    	try {
	    	List<Special_record_mgrEntity> srmlist = getSpecial_record_mgrService().find("from Special_record_mgrEntity where cellphone=?", tel);
	        if (null != srmlist && srmlist.size() > 0) {
	        	JSONArray jsonArray = new JSONArray();
	        	for (Special_record_mgrEntity bean : srmlist) {
	        		JSONObject srmjson = new JSONObject();
	        		srmjson.put("type", bean.getInfotype());
	        		srmjson.put("detail", bean.getContent());
	        		srmjson.put("amount", bean.getAccount());
	        		srmjson.put("sendDate", CommonUtils.getInstance().getYmd().format(bean.getEventdate()));
	        		srmjson.put("amountMonth", bean.getMonthdate());
	        		srmjson.put("noticeId", bean.getId());
	        		// 是否已读
	        		srmjson.put("isReaded", bean.getIsReaded());
	        		jsonArray.add(srmjson);
	        	}
	        	json.put("data", jsonArray);
	        	json.put("status", "200");
	            json.put("errormsg", "操作成功");
	        } else {
	        	json.put("data", "");
	        	json.put("status", "201");
	            json.put("errormsg", "无数据");
	        }
        	
    	} catch (Exception e) {
    		json.put("status", "500");
            json.put("errormsg", "系统异常");
    	}
        return json;
    }
    /**
     * 通知已读刷新
     * http://localhost:8080/iBusiness/rs/cws/noticeToRead?tel=18245124726&noticeId=4a8a9ffc-2f9c-4545-8fdf-64960aef2f73
	 */
	@POST
    @Path("noticeToRead")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject noticeToRead(@FormParam("tel") String tel, @FormParam("noticeId") String noticeId) {
    	// isReaded字段更新为yes,否则为no
    	JSONObject json = new JSONObject();
    	try {
    		Special_record_mgrEntity semEntity = getSpecial_record_mgrService().get(noticeId);
    		if (null != semEntity) {
    			// 是否支付
    			semEntity.setIsReaded("yes");
    			getSpecial_record_mgrService().update(semEntity);
    			json.put("status", "200");
                json.put("errormsg", "操作成功");
    		} else {
    			json.put("status", "201");
                json.put("errormsg", "ID不正确, 未找到指定通知");
    		}
    	} catch (Exception e) {
    		json.put("status", "500");
            json.put("errormsg", "系统异常");
    	}
        return json;
    }
    // ===============================================
    // 获得本周一0点时间
    private Date getTimesWeekmorning() {
    	Calendar cal = Calendar.getInstance();
    	cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
    	cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    	return cal.getTime();
	}
    // 获得本月第一天0点时间
    private Date getTimesMonthmorning() {
    	Calendar cal = Calendar.getInstance();
    	cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
    	cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
    	return cal.getTime();
    }
    // ==================================================
    // 用户
    public OrgCompanyDao getOrgCompanyDao() {
        return ApplicationContextHelper.getBean(OrgCompanyDao.class);
    }
    // 客户管理
    public Customer_mgrService getCustomer_mgrService() {
        return ApplicationContextHelper.getBean(Customer_mgrService.class);
    }
    public SimplePasswordEncoder getSimplePasswordEncoder() {
        return ApplicationContextHelper.getBean(SimplePasswordEncoder.class);
    }
    // 专车司机报名
    public Special_driver_signupService getSpecial_driver_signupService() {
        return ApplicationContextHelper.getBean(Special_driver_signupService.class);
    }
    // 专车使用车型管理
    public Specialcar_use_type_mgrService getSpecialcar_use_type_mgrService() {
        return ApplicationContextHelper.getBean(Specialcar_use_type_mgrService.class);
    }
    // 车辆库存管理
    public Car_mgrService getCar_mgrService() {
        return ApplicationContextHelper.getBean(Car_mgrService.class);
    }
    // 专车司机信息
    public Special_driver_infoService getSpecial_driver_infoService() {
        return ApplicationContextHelper.getBean(Special_driver_infoService.class);
    }
    // 财务结算
    public Financial_settlementService getFinancial_settlementService() {
        return ApplicationContextHelper.getBean(Financial_settlementService.class);
    }
    // 首页图片管理
    public IndeximgbyworksService getIndeximgbyworksService() {
        return ApplicationContextHelper.getBean(IndeximgbyworksService.class);
    }
    // 公告文章管理
    public CmsArticleService getCmsArticleService() {
        return ApplicationContextHelper.getBean(CmsArticleService.class);
    }
    // 租车预订信息
    public Car_rentalService getCar_rentalService() {
        return ApplicationContextHelper.getBean(Car_rentalService.class);
    }
    // 司机流水
    public Flow_driver_financialService getFlow_driver_financialService() {
        return ApplicationContextHelper.getBean(Flow_driver_financialService.class);
    }
    // 专车通知service
    public Special_record_mgrService getSpecial_record_mgrService() {
        return ApplicationContextHelper.getBean(Special_record_mgrService.class);
    }
    // 车型管理
    public Car_type_infoService getCar_type_infoService() {
        return ApplicationContextHelper.getBean(Car_type_infoService.class);
    }
}
