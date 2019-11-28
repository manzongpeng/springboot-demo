package com.program.user.enums;

/**
 * @description: 错误码
 * @create: 2019-10-10 17:01
 **/
public enum LoginErrorCodeEnum {

    /**
     * 用户不存在
     */
    SYS_USER_NOT_FOUND(20011, "用户不存在"),
    /**
     * Token过期
     */
    TOKEN_JWT_EXPIRE(20012, "JWT校验过期"),
    /**
     * 没有Token
     */
    TOKEN_NOT_EXIST(20013, "无token，请重新登录"),
    /**
     * 路径错误
     */
    PATH_ERROR(20014, "路径错误"),
    /**
     * 用户不存在
     */
    GET_TOKEN_FAIL(20015, "获取token失败"),
    /**
     *
     */
    TOKEN_FORMAT_ERROR(20016, "token格式错误");

    private String message;
    private int code;

    LoginErrorCodeEnum(int code, String message) {
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
