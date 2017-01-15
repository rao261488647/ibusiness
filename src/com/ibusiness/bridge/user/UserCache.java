package com.ibusiness.bridge.user;
/**
 * 帐号/用户缓存接口
 * 
 * @author JiangBo
 *
 */
public interface UserCache {
    UserDTO findById(String id);

    UserDTO findByUsername(String username, String scopeId);

    UserDTO findByRef(String ref, String scopeId);

    void updateUser(UserDTO userDto);

    void removeUser(UserDTO userDto);
}
