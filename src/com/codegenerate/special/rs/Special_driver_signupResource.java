package com.codegenerate.special.rs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import com.ibusiness.common.page.Page;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.util.CommonUtils;

import com.codegenerate.special.entity.Special_driver_signupEntity;
import com.codegenerate.special.service.Special_driver_signupService;

/**   
 * @Title: 接口
 * @Description: 专车司机报名页面
 * @author JiangBo
 *
 */
@Controller
@Path("ws-special_driver_signup")
public class Special_driver_signupResource {

   /**
     * 列表接口
	 */
    @POST
    @GET
    @Path("special_driver_signup-list")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject special_driver_signupList(@QueryParam("jsonStr") String jsonStr) {
        JSONObject json = JSONObject.fromObject(jsonStr);
        int pageNo = json.getInt("pageNo");
        int pageSize = json.getInt("pageSize");
        // 
    	JSONObject returnJson = new JSONObject();
    	Page page = new Page(pageNo, pageSize, "", "ASC");
    	Map<String, Object> parameterMap = new HashMap<String, Object>();
    	List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
    	page = getSpecial_driver_signupService().pagedQuery(page, propertyFilters);
    	// 取得对象列表
    	List<Special_driver_signupEntity> list = (List<Special_driver_signupEntity>) page.getResult();
    	// 转换JSON对象
    	JSONArray jsonArray = CommonUtils.getJsonFromList(list, null);
    	returnJson.put("datas", jsonArray);
        return returnJson;
    }
    
    /**
     * 详情接口
	 */
    @POST
    @GET
    @Path("special_driver_signup-input")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject special_driver_signupInput(@QueryParam("jsonStr") String jsonStr) {
        JSONObject json = JSONObject.fromObject(jsonStr);
        // 根据ID查询,判断参数中是否有ID字段
        String id = null;
        if (json.containsKey("id")) {
            id = json.getString("id");
        }
        Special_driver_signupEntity entity;
        if (!CommonUtils.isNull(id)) {
            entity = getSpecial_driver_signupService().get(id);
        } else {
            entity = new Special_driver_signupEntity();
        }
        // 
    	JSONObject returnJson = new JSONObject();
        // 转换JSON对象
        JSONObject jsonObj = CommonUtils.getJsonFromBean(entity, null);
        returnJson.put("datas", jsonObj);
        return returnJson;
    }
    /**
     * 保存
     */
    @POST
    @GET
    @Path("special_driver_signup-save")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject special_driver_signupSave(@QueryParam("jsonStr") String jsonStr) {
        Special_driver_signupEntity entity = (Special_driver_signupEntity) CommonUtils.getBeanFromJson(jsonStr, Special_driver_signupEntity.class);
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            getSpecial_driver_signupService().insert(entity);
        } else {
            getSpecial_driver_signupService().update(entity);
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("stateCode", "200");
        return jsonObj;
    }
    /**
     * 删除
     */
    @POST
    @GET
    @Path("special_driver_signup-remove")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject special_driver_signupRemove(@QueryParam("jsonStr") String jsonStr) {
        JSONObject json = JSONObject.fromObject(jsonStr);
        String id = null;
        if (json.containsKey("id")) {
            id = json.getString("id");
        }
        JSONObject jsonObj = new JSONObject();
        if (CommonUtils.isNull(id)) {
            jsonObj.put("stateCode", "404");
        } else {
            Special_driver_signupEntity entity = getSpecial_driver_signupService().get(id);
            getSpecial_driver_signupService().remove(entity);
            jsonObj.put("stateCode", "200");
        }
        return jsonObj;
    }
    // ======================================================================
    // Service
    public Special_driver_signupService getSpecial_driver_signupService() {
        return com.ibusiness.core.spring.ApplicationContextHelper.getBean(Special_driver_signupService.class);
    }
}
