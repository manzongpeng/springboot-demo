package com.program.sms.template;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 短信登录模板参数
 **/
@Data
public class SmsLoginTemplateParam implements Serializable {

    private static final long serialVersionUID = 7435006119758372991L;
    /**
     * 验证码
     */
    private String code;
}
