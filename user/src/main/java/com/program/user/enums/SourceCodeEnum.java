package com.program.user.enums;

/**
 * @description: 设备来源
 * @create: 2019-09-25 20:19
 **/
public enum SourceCodeEnum {
    /**
     * 电脑端
     */
    PC(1, "电脑端");

    /**
     * 信息描述
     */
    private String message;
    /**
     * 状态码
     */
    private int code;

    SourceCodeEnum(int code, String message) {
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
