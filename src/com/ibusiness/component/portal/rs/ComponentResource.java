package com.ibusiness.component.portal.rs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ibusiness.bpm.dao.BpmProcessDao;
import com.ibusiness.bpm.entity.BpmProcess;
import com.ibusiness.component.form.dao.ConfFormDao;
import com.ibusiness.component.form.entity.ConfForm;
import com.ibusiness.component.portal.dao.ComponentDao;
import com.ibusiness.component.portal.entity.ConfComponent;
import com.ibusiness.component.table.dao.TableDao;
import com.ibusiness.component.table.entity.ConfTable;
/**
 * 业务模块组件 左边树
 * @author JiangBo
 *
 */
@Component
@Path("component")
public class ComponentResource {
    private static Logger logger = LoggerFactory.getLogger(ComponentResource.class);
    private ComponentDao componentDao;
    private TableDao tableDao;
    private ConfFormDao confFormDao;
    private BpmProcessDao cpmProcessDao;

//    @POST
//    @Path("left-test")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String leftTest(@QueryParam("packageName") String packageName, @QueryParam("typeId") String strTypeId,
//            @QueryParam("parentId") String parentId) {
//        // 制造一个根节点,用于对业务模块进行 增删改
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("id", 0);
//        map.put("name", "业务模块(增删改)");
//        map.put("packageName", "root");
//        map.put("typeId", "root");
//        map.put("open", "true");  // 展开
//        map.put("icon", "../plugin/ztree/zTreeStyle/img/diy/1_open.png");
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        list.add(map);
//        return list.toString();
//    }
    
    /**
     * 后台管理页面  左边树列表信息
     * @param packageName
     * @param strTypeId
     * @param parentId
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    @Path("left-menu-tree")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> tree(@QueryParam("packageName") String packageName, @QueryParam("typeId") String strTypeId,
            @QueryParam("parentId") String parentId) {
        String hql = "from ConfComponent where parentid = '0' order by modulename ";
        List<ConfComponent> entities = componentDao.find(hql);
        // 制造一个根节点,用于对业务模块进行 增删改
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 0);
        map.put("name", "业务模块(增删改)");
        map.put("packageName", "root");
        map.put("typeId", "root");
        map.put("open", "true");  // 展开
        map.put("icon", "../plugin/ztree/zTreeStyle/img/diy/1_open.png");
        map.put("children",generateEntities(packageName, strTypeId, entities));
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list.add(map);
        return list;
    }
    /**
     * 循环插入节点
     * @param entities
     * @param partyStructTypeId
     * @return
     */
    private List<Map<String, Object>> generateEntities(String packageName, String strTypeId, List<ConfComponent> entities) {
        if (entities == null) {
            return null;
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            for (ConfComponent entity : entities) {
                list.add(generateEntity(packageName, strTypeId, entity));
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return list;
    }

    /**
     * 配置节点,递归调用子节点
     * 
     * @param component
     * @param partyStructTypeId
     * @return
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> generateEntity(String packageName, String strTypeId, ConfComponent component) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("id", component.getId());
            map.put("name", component.getModulename());
            map.put("packageName", component.getPackagename());
            map.put("typeId", component.getTypeid());
            // 展开
            if (packageName.equals(component.getPackagename())) {
                if (strTypeId.equals(component.getTypeid()) || "sModule".equals(component.getTypeid())) {
                    map.put("open", "true");
                }
                // 表
                if ("Table".equals(strTypeId) && "tables".equals(component.getTypeid())) {
                    map.put("open", "true");
                }
                // 流程表
                if ("BpmTable".equals(strTypeId) && "bpmTables".equals(component.getTypeid())) {
                    map.put("open", "true");
                }
                // 表单
                if ("Form".equals(strTypeId) && "forms".equals(component.getTypeid())) {
                    map.put("open", "true");
                }
                // 流程表单
                if ("BpmForm".equals(strTypeId) && "bpmForms".equals(component.getTypeid())) {
                    map.put("open", "true");
                }
                // 流程表单
                if ("Bpm".equals(strTypeId) && "flows".equals(component.getTypeid())) {
                    map.put("open", "true");
                }
            }
            if(component.getParentid().equals("0")){
            	map.put("icon", "../plugin/ztree/zTreeStyle/img/diy/3.png");
            }else{
            	map.put("icon", "../plugin/ztree/zTreeStyle/img/diy/5.png");
            }
            // 子节点--表
            if ("Table".equals(component.getTypeid()) || "BpmTable".equals(component.getTypeid())) {
                String isBpmTable = "1";
                String typeId = "tables";
                if ("Table".equals(component.getTypeid())) {
                    isBpmTable = "2";
                    typeId = "tables";
                } else {
                    isBpmTable = "1";
                    typeId = "bpmTables";
                }
                String hql = "from ConfTable where packageName = ? AND isBpmTable=" + isBpmTable + " order by tableNameComment ";
                List<ConfTable> tables = tableDao.find(hql, component.getPackagename());
                // 循环
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                for (ConfTable confTable : tables) {
                    Map<String, Object> tableMap = new HashMap<String, Object>();
                    tableMap.put("id", confTable.getId());
                    tableMap.put("name", confTable.getTableNameComment());
                    tableMap.put("packageName", confTable.getPackageName());
                    tableMap.put("typeId", typeId);
                    if (strTypeId.equals(typeId)) {
                        tableMap.put("open", "true"); // 展开
                    }
                    tableMap.put("tableName", confTable.getTableName());
                    tableMap.put("icon", "../plugin/ztree/zTreeStyle/img/diy/2.png");
                    list.add(tableMap);
                }
                map.put("children", list);
            } else if ("Form".equals(component.getTypeid()) || "BpmForm".equals(component.getTypeid())) {
                String isBpmForm = "1";
                String typeId = "forms";
                if ("Form".equals(component.getTypeid())) {
                    isBpmForm = "2";
                    typeId = "forms";
                } else {
                    isBpmForm = "1";
                    typeId = "bpmForms";
                }
                // 子节点--表单
                String hql = "from ConfForm where packageName =? AND isBpmForm=" + isBpmForm + " order by formTitle ";
                List<ConfForm> forms = confFormDao.find(hql, component.getPackagename());
                // 循环
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                for (ConfForm confForm : forms) {
                    Map<String, Object> formMap = new HashMap<String, Object>();
                    formMap.put("id", confForm.getId());
                    formMap.put("name", confForm.getFormTitle());
                    formMap.put("packageName", confForm.getPackageName());
                    formMap.put("typeId", typeId);
                    if (strTypeId.equals(typeId)) {
                        formMap.put("open", "true"); // 展开
                    }
                    formMap.put("formId", confForm.getId());
                    formMap.put("icon", "../plugin/ztree/zTreeStyle/img/diy/8.png");
                    list.add(formMap);
                }
                map.put("children", list);
            } else if ("Bpm".equals(component.getTypeid())) {
                String hql = "from BpmProcess where packageName = ? order by flowTitle ";
                List<BpmProcess> flows = cpmProcessDao.find(hql, component.getPackagename());
                // 循环
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                for (BpmProcess bpmFlow : flows) {
                    Map<String, Object> bpmMap = new HashMap<String, Object>();
                    bpmMap.put("id", bpmFlow.getId());
                    bpmMap.put("name", bpmFlow.getFlowTitle());
                    bpmMap.put("packageName", bpmFlow.getPackageName());
                    bpmMap.put("typeId", "flows");
                    if (strTypeId.equals("Bpm")) {
                        bpmMap.put("open", "true"); // 展开
                    }
                    bpmMap.put("flowId", bpmFlow.getId());
                    bpmMap.put("icon", "../plugin/ztree/zTreeStyle/img/diy/9.png");
                    list.add(bpmMap);
                }
                map.put("children", list);
            } else {
                // 查询子节点
                String hql = "from ConfComponent where parentid = ? ";
                List<ConfComponent> entities = componentDao.find(hql, component.getId());
                // 循环
                map.put("children", generateEntities(packageName, strTypeId, entities));
            }
            return map;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return map;
        }
    }

    // ==================================================
    @Resource
    public void setComponentDao(ComponentDao componentDao) {
        this.componentDao = componentDao;
    }
    @Resource
    public void setTableDao(TableDao tableDao) {
        this.tableDao = tableDao;
    }
    @Resource
    public void setConfFormDao(ConfFormDao confFormDao) {
        this.confFormDao = confFormDao;
    }
    @Resource
    public void setBpmProcessDao(BpmProcessDao cpmProcessDao) {
        this.cpmProcessDao = cpmProcessDao;
    }
}
