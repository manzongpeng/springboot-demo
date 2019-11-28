package com.program.user.enums;

/**
 * @author
 * @program: saic-cloud
 * @description: 错误码
 * @create: 2019-10-10 17:01
 **/
public enum UploadErrorCodeEnum {

    /**
     * 用户不存在
     */
    FILE_IS_NULL(20021, "文件为空"),
    /**
     * Token过期
     */
    FILE_IS_TOO_LARGE(20022, "文件太大");

    private String message;
    private int code;

    UploadErrorCodeEnum(int code, String message) {
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
