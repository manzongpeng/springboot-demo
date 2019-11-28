package com.program.user.enums;

/**
 * 错误参数枚举
 *
 * @author tssh
 */
public enum ErrorCodeEnum {

    SERVER_SUCCESS(0, "success"),

    SERVER_ERROR(500, "server error!"),

    PARAM_ERROR(10001, "param error !"),

    DUBBO_ERROR(10002, "Dubbo server error !");

    private String message;
    private int code;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }
}
