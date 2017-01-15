package com.ibusiness.security.perm;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

/**
 * 许可-投票器。参与授权的投票。 permission获取 许可 信息投票通过，或拒绝，或弃权
 * 
 * @author JiangBo
 * 
 */
public class PermissionVoter implements AccessDecisionVoter<Object> {
    private PermissionChecker permissionChecker;

    public boolean supports(ConfigAttribute attribute) {
        return attribute.getAttribute() != null;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * Authentication里面获取的权限信息
     * 不同的认证请求的安全配置，需要使用配置参数。配置参数使用ConfigAttribute接口
     */
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) {
        int result = ACCESS_ABSTAIN;

        for (ConfigAttribute configAttribute : configAttributes) {
            if (this.supports(configAttribute)) {
                result = ACCESS_DENIED;

                String text = getPermission(configAttribute);
                boolean authorized = permissionChecker.isAuthorized(text);

                if (authorized) {
                    return ACCESS_GRANTED;
                }
            }
        }

        return result;
    }

    private String getPermission(ConfigAttribute configAttribute) {
        return configAttribute.getAttribute();
    }

    public void setPermissionChecker(PermissionChecker permissionChecker) {
        this.permissionChecker = permissionChecker;
    }
}
