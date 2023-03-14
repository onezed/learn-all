package com.onezed.learn.security.common;

/**
 * <p/>
 * Describe
 * <p/>
 *
 * @author lianwenjie
 */
public enum ResponseEnum {

    OK(200, "OK"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final Integer code;

    private final String description;

    private ResponseEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer code() {
        return this.code;
    }

    public String getDescription() {
        return description;
    }
}
