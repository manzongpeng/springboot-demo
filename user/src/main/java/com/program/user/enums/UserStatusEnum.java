package com.program.user.enums;

/**
 * @description: 管理员账号状态
 * @create: 2019-09-25 11:43
 **/
public enum UserStatusEnum {

    /**
     * 账号禁用
     */
    LOCKED(1, "账号已被锁定"),
    /**
     * 账号正常
     */
    NORMAL(0, "正常"),
    /**
     * 账号删除
     */
    DELETE(1, "账号已被删除");

    private int status;
    private String message;

    UserStatusEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
