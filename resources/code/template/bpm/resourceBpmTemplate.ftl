package ${bussiPackage}.${entityPackage}.rs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     *列表
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
    
    // ======================================================================
    // Service
    public ${entityName}Service get${entityName}Service() {
        return com.ibusiness.core.spring.ApplicationContextHelper.getBean(${entityName}Service.class);
    }
}
