package com.program.sms.enums;

/**
 * @description: 短信状态，是否删除
 **/
public enum SmsStatusEnum {
    /**
     * 短信状态 已删除 1
     */
    IS_DELETE(1, "已删除"),
    /**
     * 短信状态 未删除 0
     */
    NOT_DELETE(0, "未删除");
    /**
     * 短信状态
     */
    private Integer status;
    /**
     * 状态描述
     */
    private String desc;

    SmsStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
