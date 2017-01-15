package com.ibusiness.security.api.scope;

import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.util.CommonUtils;

/**
 * 应用范围管理
 * 
 * @author JiangBo
 *
 */
public class ScopeHolder {
    private static ThreadLocal<ScopeDTO> scopeThreadLocal = new ThreadLocal<ScopeDTO>();

    protected ScopeHolder() {
    }
    /**
     * 取得多租户范围ID--当前是公司ID
     * @return
     */
    public static String getScopeId() {
    	// 取得多租户范围
        return getScopeDto().getId();
    }

    
    public static String getScopeIdByUserName(String userName) {
    	// 取得多租户范围
        return getScopeDto().getId();
    }
//    public static String getScopeCode() {
//        return getScopeDto().getCode();
//    }

//    /**
//     * 登录方式
//     * @return
//     */
//    public static String getUserRepoRef() {
//        return getScopeDto().getUserRepoRef();
//    }

    /**
     * 取得多租户 用户范围
     * @return
     */
    public static ScopeDTO getScopeDto() {
    	ScopeDTO scopeDto = scopeThreadLocal.get();
    	if (null == scopeDto) {
    		scopeDto = new ScopeDTO();
    	}
    	// 取得当前用户的公司ID
    	String scopeId = CommonBusiness.getInstance().getCurrentUserScopeId();
    	// 如果未登录默认值为1
    	if (CommonUtils.isNull(scopeId)) {
    		scopeDto.setId("1");
    	} else {
    		scopeDto.setId(scopeId);
    	}
    	return scopeDto;
    }

    public static void setScopeDto(ScopeDTO scopeDto) {
        scopeThreadLocal.set(scopeDto);
    }

    /**
     * clear
     */
    public static void clear() {
        scopeThreadLocal.remove();
    }
}
