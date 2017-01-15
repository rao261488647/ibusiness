package com.ibusiness.bridge.userauth;

import com.ibusiness.security.api.userauth.UserAuthDTO;

/**
 * 用户权限缓存接口
 * 
 * @author JiangBo
 *
 */
public interface UserAuthCache {
    UserAuthDTO findByUsername(String username, String scopeId);

    UserAuthDTO findByRef(String ref, String scopeId);

    UserAuthDTO findById(String id, String scopeId);

    void updateUserAuth(UserAuthDTO userAuthDto);

    void removeUserAuth(UserAuthDTO userAuthDto);
}
