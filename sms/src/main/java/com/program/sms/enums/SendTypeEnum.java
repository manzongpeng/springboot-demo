package com.program.sms.enums;


import com.program.sms.template.SmsChangeMobileTemplateParam;
import com.program.sms.template.SmsForgetPwdTemplateParam;
import com.program.sms.template.SmsLoginTemplateParam;
import com.program.sms.template.SmsRegisterTemplateParam;
import com.program.sms.template.SmsResetSecurityCodeTemplateParam;

/**
 * @description: 发送短信类型
 **/
public enum SendTypeEnum {
    /**
     * 登录类型
     */
    SIGN_IN(1, "登录", new SmsLoginTemplateParam()),
    /**
     * 注册类型
     */
    REGISTER(2, "注册", new SmsRegisterTemplateParam()),
    /**
     * 忘记密码类型
     */
    FORGET_PASSWORD(3, "忘记密码", new SmsForgetPwdTemplateParam()),
    /**
     * 重置安全码
     */
    RESET_SECURITY_CODE(4, "重置安全码", new SmsResetSecurityCodeTemplateParam()),
    /**
     * 更换手机号
     */
    CHANGE_MOBILE(5, "更换手机号", new SmsChangeMobileTemplateParam());

    private Integer type;
    private String desc;
    private Object templateParam;

    SendTypeEnum(Integer type, String desc, Object templateParam) {
        this.type = type;
        this.desc = desc;
        this.templateParam = templateParam;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(Object templateParam) {
        this.templateParam = templateParam;
    }
}


