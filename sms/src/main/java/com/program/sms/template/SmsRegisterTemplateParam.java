package com.program.sms.template;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 短信注册模板参数
 **/
@Data
public class SmsRegisterTemplateParam implements Serializable {

    private static final long serialVersionUID = 2510067617334621942L;
    /**
     * 验证码
     */
    private String code;
}
