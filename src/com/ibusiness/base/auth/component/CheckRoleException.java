package com.ibusiness.base.auth.component;

public class CheckRoleException extends RuntimeException {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public CheckRoleException(String message) {
        super(message);
    }
}
