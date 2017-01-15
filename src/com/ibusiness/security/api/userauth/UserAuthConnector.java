package com.ibusiness.security.api.userauth;
/**
 * 用户权限连接器接口
 * 
 * @author JiangBo
 *
 */
public interface UserAuthConnector {
    UserAuthDTO findByUsername(String username, String scopeId);

    UserAuthDTO findByRef(String ref, String scopeId);

    UserAuthDTO findById(String id, String scopeId);
}
