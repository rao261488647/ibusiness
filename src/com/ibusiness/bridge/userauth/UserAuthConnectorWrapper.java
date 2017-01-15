package com.ibusiness.bridge.userauth;

import com.ibusiness.security.api.userauth.UserAuthConnector;
import com.ibusiness.security.api.userauth.UserAuthDTO;

public class UserAuthConnectorWrapper implements UserAuthConnector {
    private UserAuthConnector userAuthConnector;
    private UserAuthCache userAuthCache;

    public UserAuthDTO findByUsername(String username, String scopeId) {
        UserAuthDTO userAuthDto = userAuthCache.findByUsername(username,
                scopeId);

        if (userAuthDto == null) {
            synchronized (userAuthCache) {
                userAuthDto = userAuthCache.findByUsername(username, scopeId);

                if (userAuthDto == null) {
                    userAuthDto = userAuthConnector.findByUsername(username, scopeId);

                    if (userAuthDto != null) {
                        userAuthCache.updateUserAuth(userAuthDto);
                    }
                }
            }
        }

        return userAuthDto;
    }

    public UserAuthDTO findByRef(String ref, String scopeId) {
        UserAuthDTO userAuthDto = userAuthCache.findByRef(ref, scopeId);

        if (userAuthDto == null) {
            synchronized (userAuthCache) {
                userAuthDto = userAuthCache.findByRef(ref, scopeId);

                if (userAuthDto == null) {
                    userAuthDto = userAuthConnector.findByRef(ref, scopeId);

                    if (userAuthDto != null) {
                        userAuthCache.updateUserAuth(userAuthDto);
                    }
                }
            }
        }

        return userAuthDto;
    }

    public UserAuthDTO findById(String id, String scopeId) {
        UserAuthDTO userAuthDto = userAuthCache.findById(id, scopeId);

        if (userAuthDto == null) {
            synchronized (userAuthCache) {
                userAuthDto = userAuthCache.findById(id, scopeId);

                if (userAuthDto == null) {
                    userAuthDto = userAuthConnector.findById(id, scopeId);

                    if (userAuthDto != null) {
                        userAuthCache.updateUserAuth(userAuthDto);
                    }
                }
            }
        }

        return userAuthDto;
    }

    public void setUserAuthConnector(UserAuthConnector userAuthConnector) {
        this.userAuthConnector = userAuthConnector;
    }

    public void setUserAuthCache(UserAuthCache userAuthCache) {
        this.userAuthCache = userAuthCache;
    }
}
