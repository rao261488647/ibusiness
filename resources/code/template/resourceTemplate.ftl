package ${bussiPackage}.${entityPackage}.rs;

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

import ${bussiPackage}.${entityPackage}.entity.${entityName}Entity;
import ${bussiPackage}.${entityPackage}.service.${entityName}Service;

/**   
 * @Title: 接口
 * @Description: ${ftl_description}
 * @author JiangBo
 *
 */
@Controller
@Path("ws-${entityName?uncap_first}")
public class ${entityName}Resource {

   /**
     * 列表接口
	 */
    @POST
    @GET
    @Path("${entityName?uncap_first}-list")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject ${entityName?uncap_first}List(@QueryParam("jsonStr") String jsonStr) {
        JSONObject json = JSONObject.fromObject(jsonStr);
        int pageNo = json.getInt("pageNo");
        int pageSize = json.getInt("pageSize");
        // 
    	JSONObject returnJson = new JSONObject();
    	Page page = new Page(pageNo, pageSize, "", "ASC");
    	Map<String, Object> parameterMap = new HashMap<String, Object>();
    	List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
    	page = get${entityName}Service().pagedQuery(page, propertyFilters);
    	// 取得对象列表
    	List<${entityName}Entity> list = (List<${entityName}Entity>) page.getResult();
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
    @Path("${entityName?uncap_first}-input")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject ${entityName?uncap_first}Input(@QueryParam("jsonStr") String jsonStr) {
        JSONObject json = JSONObject.fromObject(jsonStr);
        // 根据ID查询,判断参数中是否有ID字段
        String id = null;
        if (json.containsKey("id")) {
            id = json.getString("id");
        }
        ${entityName}Entity entity;
        if (!CommonUtils.isNull(id)) {
            entity = get${entityName}Service().get(id);
        } else {
            entity = new ${entityName}Entity();
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
    @Path("${entityName?uncap_first}-save")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject ${entityName?uncap_first}Save(@QueryParam("jsonStr") String jsonStr) {
        ${entityName}Entity entity = (${entityName}Entity) CommonUtils.getBeanFromJson(jsonStr, ${entityName}Entity.class);
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            get${entityName}Service().insert(entity);
        } else {
            get${entityName}Service().update(entity);
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
    @Path("${entityName?uncap_first}-remove")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject ${entityName?uncap_first}Remove(@QueryParam("jsonStr") String jsonStr) {
        JSONObject json = JSONObject.fromObject(jsonStr);
        String id = null;
        if (json.containsKey("id")) {
            id = json.getString("id");
        }
        JSONObject jsonObj = new JSONObject();
        if (CommonUtils.isNull(id)) {
            jsonObj.put("stateCode", "404");
        } else {
            ${entityName}Entity entity = get${entityName}Service().get(id);
            get${entityName}Service().remove(entity);
            jsonObj.put("stateCode", "200");
        }
        return jsonObj;
    }
    // ======================================================================
    // Service
    public ${entityName}Service get${entityName}Service() {
        return com.ibusiness.core.spring.ApplicationContextHelper.getBean(${entityName}Service.class);
    }
}
