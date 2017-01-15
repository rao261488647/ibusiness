package com.ibusiness.security.client;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.ibusiness.security.api.userauth.UserAuthConnector;
import com.ibusiness.security.api.userauth.UserAuthDTO;
import com.ibusiness.core.mapper.BeanMapper;
import com.ibusiness.security.impl.SpringSecurityUserAuth;
import com.ibusiness.security.util.SpringSecurityUtils;

/**
 * 负责从SecurityContextRepository获取或存储SecurityContext。 
 * SecurityContext代表了用户安全和认证过的session
 * 
 * @author JiangBo
 * 
 */
public class CachedSecurityContextRepository extends HttpSessionSecurityContextRepository {
    private UserAuthConnector userAuthConnector;
    private BeanMapper beanMapper = new BeanMapper();
    private boolean debug;

    /**
     * 获取SecurityContext
     */
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        SecurityContext securityContext = super.loadContext(requestResponseHolder);

        if (debug) {
            return securityContext;
        }

        if (securityContext != null) {
            SpringSecurityUserAuth userAuthInSession = SpringSecurityUtils.getCurrentUser(securityContext);

            if (userAuthInSession != null) {
                UserAuthDTO userAuthInCache = userAuthConnector.findById(userAuthInSession.getId(),
                        userAuthInSession.getScopeId());

                SpringSecurityUserAuth userAuthResult = new SpringSecurityUserAuth();
                beanMapper.copy(userAuthInCache, userAuthResult);

                SpringSecurityUtils.saveUserDetailsToContext(userAuthResult, null, securityContext);
            } else {
                logger.debug("userAuthInSession is null");
            }
        } else {
            logger.debug("securityContext is null");
        }

        return securityContext;
    }

    public void setUserAuthConnector(UserAuthConnector userAuthConnector) {
        this.userAuthConnector = userAuthConnector;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
