package com.ibusiness.bridge.user;

/**
 * 用户连接器实现类
 * 
 * @author JiangBo
 *
 */
public class MockUserConnector implements UserConnector {
    private UserDTO userDto;

    public MockUserConnector() {
        userDto = new UserDTO();
        userDto.setId("1");
        userDto.setUsername("jiangbo");
        userDto.setDisplayName("jiangbo");
        userDto.setEmail("jiangbo@ib.com");
        userDto.setMobile("13800000000");
        userDto.setUserRepoRef("1");
        userDto.setStatus(1);
    }

    public UserDTO findById(String id) {
        return userDto;
    }

    public UserDTO findByUsername(String username, String scopeId) {
        return userDto;
    }

    public UserDTO findByRef(String ref, String scopeId) {
        return userDto;
    }
}
