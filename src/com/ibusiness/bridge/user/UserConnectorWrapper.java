package com.ibusiness.bridge.user;

/**
 * 
 * @author Administrator
 *
 */
public class UserConnectorWrapper implements UserConnector {
    private UserConnector userConnector;
    private UserCache userCache;

    public UserDTO findById(String id) {
        UserDTO userDto = userCache.findById(id);

        if (userDto == null) {
            synchronized (userCache) {
                userDto = userCache.findById(id);

                if (userDto == null) {
                    userDto = userConnector.findById(id);

                    if (userDto != null) {
                        userCache.updateUser(userDto);
                    }
                }
            }
        }

        return userDto;
    }

    public UserDTO findByUsername(String username, String scopeId) {
        UserDTO userDto = userCache.findByUsername(username, scopeId);

        if (userDto == null) {
            synchronized (userCache) {
                userDto = userCache.findByUsername(username, scopeId);

                if (userDto == null) {
                    userDto = userConnector.findByUsername(username, scopeId);

                    if (userDto != null) {
                        userCache.updateUser(userDto);
                    }
                }
            }
        }

        return userDto;
    }

    public UserDTO findByRef(String ref, String scopeId) {
        UserDTO userDto = userCache.findByRef(ref, scopeId);

        if (userDto == null) {
            synchronized (userCache) {
                userDto = userCache.findByRef(ref, scopeId);

                if (userDto == null) {
                    userDto = userConnector.findByRef(ref, scopeId);

                    if (userDto != null) {
                        userCache.updateUser(userDto);
                    }
                }
            }
        }

        return userDto;
    }

    public void setUserConnector(UserConnector userConnector) {
        this.userConnector = userConnector;
    }

    public void setUserCache(UserCache userCache) {
        this.userCache = userCache;
    }
}
