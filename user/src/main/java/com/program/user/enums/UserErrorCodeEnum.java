package com.program.user.enums;

/**
 * @description: 错误码
 * @create: 2019-10-10 17:01
 **/
public enum UserErrorCodeEnum {
    /**
     * 参数错误
     */
    SYSTEM_ARGUMENT_ERROR(21101, "参数错误"),
    /**
     * 用户不存在
     */
    SYS_USER_NOT_FOUND(21201, "用户不存在"),
    /**
     * 用户已存在
     */
    SYS_USER_NAME_EXISTED(21202, "用户名已存在"),
    /**
     * 用户名或密码错误
     */
    SYS_USER_PASSWORD_ERROR(21203, "用户名或密码错误"),
    /**
     * 账号已被锁定，请联系管理员
     */
    SYS_USER_LOCKED(21204, "账号已被锁定，请联系管理员"),
    /**
     * 不满足添加用户的权限要求
     */
    SYS_USER_ADD_UNAUTHORIZED(21205, "不满足添加用户的权限要求"),
    /**
     * 系统管理员不能删除
     */
    SYS_ADMIN_DELETE_ERROR(21206, "系统管理员不能删除"),
    /**
     * 系统管理员不能修改
     */
    SYS_ADMIN_UPDATE_ERROR(21207, "系统管理员不能修改"),
    /**
     * 当前用户不能删除
     */
    SYS_CURRENT_USER_DELETE_ERROR(21208, "当前用户不能删除"),
    /**
     * 用户未分配角色
     */
    SYS_USER_NOT_ROLE(21209, "用户未分配角色");

    private String message;
    private int code;

    UserErrorCodeEnum(int code, String message) {
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
