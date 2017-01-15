package com.ibusiness.security.perm;

import com.ibusiness.security.util.SpringSecurityUtils;

/**
 * 许可 check
 * 
 * @author JiangBo
 * 
 */
public class PermissionChecker {
    private PermissionMatcher permissionMatcher = new PermissionMatcher();

    public boolean isAuthorized(String text) {
        String want = text;

        for (String have : SpringSecurityUtils.getAuthorities()) {
            if (permissionMatcher.match(want, have)) {
                return true;
            }
        }

        return false;
    }

    public void setReadOnly(boolean readOnly) {
        permissionMatcher.setReadOnly(readOnly);
    }

    public boolean isReadOnly() {
        return permissionMatcher.isReadOnly();
    }
}
