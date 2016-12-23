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
<#list subTab as sub>
import ${bussiPackage}.${sub.packageName}.entity.${sub.entityName}Entity;
import ${bussiPackage}.${sub.packageName}.service.${sub.entityName}Service;
</#list>

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
        // 实例化子表对象
        <#list subTab as sub>
        List<${sub.entityName}Entity> ${sub.entityName?uncap_first}List = null;
        </#list>
        if (!CommonUtils.isNull(id)) {
            entity = get${entityName}Service().get(id);
            // 取得子表信息
            <#list subTab as sub>
            ${sub.entityName?uncap_first}List = get${sub.entityName}Service().find("from ${sub.entityName}Entity where parentid=?", id);
            </#list>
        } else {
            entity = new ${entityName}Entity();
        }
        // 
    	JSONObject returnJson = new JSONObject();
        // 转换JSON对象
        JSONObject jsonObj = CommonUtils.getJsonFromBean(entity, null);
        returnJson.put("datas", jsonObj);
        // 设置子表JSON
        <#list subTab as sub>
        JSONArray ${sub.entityName?uncap_first}Array = CommonUtils.getJsonFromList(${sub.entityName?uncap_first}List, null);
        returnJson.put("${sub.entityName?uncap_first}Array", ${sub.entityName?uncap_first}Array);
        </#list>
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
    <#list subTab as sub>
    public ${sub.entityName}Service get${sub.entityName}Service() {
        return com.ibusiness.core.spring.ApplicationContextHelper.getBean(${sub.entityName}Service.class);
    }
    </#list>
}
