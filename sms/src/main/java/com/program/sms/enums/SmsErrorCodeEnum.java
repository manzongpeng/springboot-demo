package com.program.sms.enums;

/**
 * @description: 错误码
 **/
public enum SmsErrorCodeEnum {
    /**
     * 短信发送失败
     */
    SEND_SMS_FAIL(24001, "发送短信失败"),

    /**
     * 发送类型参数错误
     */
    SEND_TYPE_ERROR(24002, "发送类型参数错误");
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态描述
     */
    private String message;

    SmsErrorCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
