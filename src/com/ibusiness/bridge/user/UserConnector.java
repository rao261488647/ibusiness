package com.ibusiness.bridge.user;


/**
 * 用户连接器接口
 * 
 * @author JiangBo
 *
 */
public interface UserConnector {
    /**
     * 根据唯一标识获取用户信息.
     * 
     * @param id
     *            用户的唯一标识，即便是不同用户库的用户id也是唯一的
     */
    UserDTO findById(String id);

    /**
     * 根据username和userRepoRef获取用户.
     * 
     * @param username
     *            登录账号，每个用户库中的用户登录名都是唯一的
     * @param userRepoRef
     *            用户库
     */
    UserDTO findByUsername(String username, String scopeId);

    /**
     * 根据reference和userRepoRef获取用户.
     * 
     * @param ref
     *            针对某个用户库的用户的唯一标识
     * @param userRepoRef
     *            用户库
     */
    UserDTO findByRef(String ref, String scopeId);
}
