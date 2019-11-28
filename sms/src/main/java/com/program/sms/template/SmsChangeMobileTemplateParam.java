package com.program.sms.template;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 更换手机号模板参数
 **/
@Data
public class SmsChangeMobileTemplateParam implements Serializable {

    private static final long serialVersionUID = 4386649930781559183L;
    /**
     * 验证码
     */
    private String code;
}
