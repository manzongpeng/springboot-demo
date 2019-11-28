package com.program.sms.template;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 短信忘记密码模板参数
 **/
@Data
public class SmsForgetPwdTemplateParam implements Serializable {

    private static final long serialVersionUID = -678399044328228658L;
    /**
     * 验证码
     */
    private String code;
}
