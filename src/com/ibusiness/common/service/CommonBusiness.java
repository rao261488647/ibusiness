package com.ibusiness.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibusiness.base.group.dao.OrgCompanyDao;
import com.ibusiness.base.group.dao.OrgDepartmentDao;
import com.ibusiness.base.group.entity.OrgCompany;
import com.ibusiness.base.group.entity.OrgDepartment;
import com.ibusiness.base.menu.dao.MenuDao;
import com.ibusiness.base.menu.dao.MenuRoleDefDao;
import com.ibusiness.base.menu.entity.Menu;
import com.ibusiness.base.menu.entity.MenuRoleDef;
import com.ibusiness.base.user.dao.UserBaseDao;
import com.ibusiness.base.user.entity.UserBase;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.component.form.dao.ConfFormDao;
import com.ibusiness.component.form.dao.ConfFormTableColumnDao;
import com.ibusiness.component.form.entity.ConfForm;
import com.ibusiness.component.form.entity.ConfFormTableColumn;
import com.ibusiness.component.table.dao.TableColumnsDao;
import com.ibusiness.component.table.dao.TableDao;
import com.ibusiness.component.table.entity.ConfTableColumns;
import com.ibusiness.core.spring.ApplicationContextHelper;
import com.ibusiness.security.api.scope.ScopeHolder;
import com.ibusiness.security.util.SpringSecurityUtils;

/**
 * CommonBusiness单例对象类
 * 
 * @author JiangBo
 *
 */
public class CommonBusiness {
    private CommonBusiness () {}
    private static CommonBusiness instance = new CommonBusiness();
    public static CommonBusiness getInstance() {
        return instance;
    }
    // 表结构管理List
    private List<ConfTableColumns> tableColumnsList = new ArrayList<ConfTableColumns>();
    // 表结构管理Map
    private Map<String, ConfTableColumns> tableColumnsMap = new HashMap<String, ConfTableColumns>();

    // 表单List
    private List<ConfForm> formList = new ArrayList<ConfForm>();
    // 表单对应表管理表List
    private List<ConfFormTableColumn> formTableColumnList = new ArrayList<ConfFormTableColumn>();
    private Map<String, ConfFormTableColumn> formTableColumnMap = new HashMap<String, ConfFormTableColumn>();
    
    // 公司信息Map
    private Map<String, OrgCompany> companyMap = new HashMap<String, OrgCompany>();
    // 部门信息Map
    private Map<String, OrgDepartment> departmentMap = new HashMap<String, OrgDepartment>();
    // 用户MAP
    private Map<String, UserBase> userBaseMap = new HashMap<String, UserBase>();
    
    /**
     * 判断当前用户是否有菜单权限
     */
    @SuppressWarnings("unchecked")
	public String isPermByMenu(String menuUrl) {
    	String currentUserId = SpringSecurityUtils.getCurrentUserId();
    	if (CommonUtils.isNull(currentUserId)) {
    		return "false";
    	}
    	UserBase userBase = getUserBaseDao().get(currentUserId);
    	// 角色
    	userBase.getRoleDef();
    	// 菜单
    	List<Menu> menuList = getMenuDao().find("from Menu where menuUrl=? ",menuUrl);
    	if (null!=menuList && menuList.size() > 0) {
    		Menu menu = menuList.get(0);
    		// 菜单和角色模板关联表
    		List<MenuRoleDef> menuRoleDefList = getMenuRoleDefDao().find("from MenuRoleDef where menuId=? AND roleDefId=?",menu.getId(), userBase.getRoleDef().getId());
    		if (null != menuRoleDefList && menuRoleDefList.size() > 0) {
    			return "true";
    		}
    	}
    	return "false";
    }
    /**
     * 取得用户表所有用户
     */
    public List<UserBase> getUserBaseList() {
        List<UserBase> list = getUserBaseDao().getAll();
        return list;
    }
    /**
     * 取得用户表所有用户根据Map并存在内存中
     */
    public Map<String, UserBase> getUserBaseMap() {
    	if (null == userBaseMap || userBaseMap.size() < 1) {
    		List<UserBase> list = getUserBaseDao().getAll();
        	for (UserBase bean : list) {
        		userBaseMap.put(bean.getId(), bean);
        	}
    	}
        return userBaseMap;
    }
    /**
     * 设置内存中的用户信息
     */
	public void setUserBaseMap(Map<String, UserBase> userBaseMap) {
		this.userBaseMap = userBaseMap;
	}
	/**
     * 取得用户表的用户信息
     */
    public UserBase getUserBean(String id) {
        UserBase bean = getUserBaseDao().get(id);
        return bean;
    }
    /**
     * 取得流水表表结构管理List
     * @return the tableColumnsList
     */
    @SuppressWarnings("unchecked")
    public List<ConfTableColumns> getTableColumnsList(String tableName) {
        String hql = "from ConfTableColumns where tableName=? order by columnNo";
        tableColumnsList = getTableColumnsDao().find(hql, tableName);
        return tableColumnsList;
    }
    /**
     * 取得业务表字段结构管理Map
     * @return the tableColumnsMap
     */
    public Map<String, ConfTableColumns> getTableColumnsMap(String tableName) {
        List<ConfTableColumns> tableColumnsList = getTableColumnsList(tableName);
        for (ConfTableColumns bean : tableColumnsList) {
            tableColumnsMap.put(bean.getColumnValue(), bean);
        }
        return tableColumnsMap;
    }
    /**
     * 取得表单list
     * @return the formList
     */
    @SuppressWarnings("unchecked")
    public List<ConfForm> getFormList(String formName) {
        String hql = "from ConfForm where formName=?";
        this.formList = getConfFormDao().find(hql, formName);
        return this.formList;
    }
    /**
     * 取得表单对应表管理表List
     * @return the tableColumnsList
     */
    @SuppressWarnings("unchecked")
    public List<ConfFormTableColumn> getFormTableColumnList(String tableName, String formName) {
        if (CommonUtils.isNull(tableName) || CommonUtils.isNull(formName)) {
            String hql = "from ConfFormTableColumn ";
            this.formTableColumnList = getConfFormTableColumnDao().find(hql);
        } else {
            String hql = "from ConfFormTableColumn where tableName=? AND formName=?";
            this.formTableColumnList = getConfFormTableColumnDao().find(hql, tableName, formName);
        }
        return this.formTableColumnList;
    }
    /**
     * 取得表单对应表管理表List
     * @return the formTableColumnMap
     */
    public Map<String, ConfFormTableColumn> getFormTableColumnMap(String tableName, String formName) {
        this.formTableColumnMap = new HashMap<String, ConfFormTableColumn>();
        for(ConfFormTableColumn bean :getFormTableColumnList(tableName, formName)) {
            this.formTableColumnMap.put(bean.getTableColumn(), bean);
        }
        return this.formTableColumnMap;
    }
    /**
     * 取得表单对应表管理表Map key为表单字段
     */
    public Map<String, ConfFormTableColumn> getFormTableColumnMapByFormColumn() {
        Map<String, ConfFormTableColumn> formTableColumnMap = new HashMap<String, ConfFormTableColumn>();
        for(ConfFormTableColumn bean :getFormTableColumnList(null, null)) {
            formTableColumnMap.put(bean.getFormColumn(), bean);
        }
        return formTableColumnMap;
    }
    /**
     * 公司信息Map
     */
    public Map<String, OrgCompany> getCompanyMap() {
    	// 查询公司信息
    	List<OrgCompany> companyList = getOrgCompanyDao().getAll();
    	this.companyMap.clear();
        for (OrgCompany bean : companyList) {
        	this.companyMap.put(bean.getId(), bean);
        }
        return this.companyMap;
    }
    /**
     * 部门信息Map
     */
    public Map<String, OrgDepartment> getDepartmentMap() {
    	// 查询部门信息
    	List<OrgDepartment> list = getOrgDepartmentDao().getAll();
    	this.departmentMap.clear();
        for (OrgDepartment bean : list) {
        	this.departmentMap.put(bean.getId(), bean);
        }
        return this.departmentMap;
    }
    /**
     *  取得当前登录用户所属范围
     * @return
     */
    public String getCurrentUserScopeId() {
    	String currentUserId = SpringSecurityUtils.getCurrentUserId();
    	if (CommonUtils.isNull(currentUserId)) {
    		return "";
    	} else {
    		return getUserBaseMap().get(currentUserId).getScopeid();
    	}
    }
    /**
     * 通过账号 查询  用户所属范围
     * @param userName
     * @return
     */
    @SuppressWarnings("unchecked")
	public String getCurrentUserScopeIdByUserName(String userName) {
    	List<UserBase> list = getUserBaseDao().find("from UserBase where username=?", userName);
    	if (null == list || list.size() < 1) {
    		return "";
    	} else {
    		return list.get(0).getScopeid();
    	}
    }
    /**
     * 编辑查询参数--关于公司ID
     * @return
     */
    public List<PropertyFilter> editPFByScopeId(List<PropertyFilter> propertyFilters) {
    	if (!"admin".equals(SpringSecurityUtils.getCurrentUsername())) {
//    		propertyFilters.add(new PropertyFilter("EQS_scopeid", ScopeHolder.getScopeId()));
    	}
    	return propertyFilters;
    }
    // ======================================================================
    // 用户
    public UserBaseDao getUserBaseDao() {
        return ApplicationContextHelper.getBean(UserBaseDao.class);
    }
    public TableColumnsDao getTableColumnsDao() {
        return ApplicationContextHelper.getBean(TableColumnsDao.class);
    }
    public ConfFormDao getConfFormDao() {
        return ApplicationContextHelper.getBean(ConfFormDao.class);
    }
    public ConfFormTableColumnDao getConfFormTableColumnDao() {
        return ApplicationContextHelper.getBean(ConfFormTableColumnDao.class);
    }
    // 公司
    public OrgCompanyDao getOrgCompanyDao() {
        return ApplicationContextHelper.getBean(OrgCompanyDao.class);
    }
    // 部门
    public OrgDepartmentDao getOrgDepartmentDao() {
        return ApplicationContextHelper.getBean(OrgDepartmentDao.class);
    }
    // 菜单和角色模板关联表
    public MenuRoleDefDao getMenuRoleDefDao() {
        return ApplicationContextHelper.getBean(MenuRoleDefDao.class);
    }
    // 菜单
    public MenuDao getMenuDao() {
        return ApplicationContextHelper.getBean(MenuDao.class);
    }
    // 表结构
    public TableDao getTableDao() {
        return ApplicationContextHelper.getBean(TableDao.class);
    }
}
