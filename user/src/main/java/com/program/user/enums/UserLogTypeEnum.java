package com.program.user.enums;

/**
 * @description: 操作日志类型
 * @create: 2019-09-25 13:20
 **/
public enum UserLogTypeEnum {

    /**
     * 登录系统
     */
    LOGIN(1, "登录系统");

    private String message;
    private int code;

    UserLogTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }


    public static UserLogTypeEnum getByValue(int value) {
        for (UserLogTypeEnum code : values()) {
            if (code.getCode() == value) {
                return code;
            }
        }
        return null;
    }
}
