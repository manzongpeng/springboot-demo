package com.program.sms.template;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 重置安全码模板参数
 **/
@Data
public class SmsResetSecurityCodeTemplateParam implements Serializable {

    private static final long serialVersionUID = 917657041867478495L;
    /**
     * 验证码
     */
    private String code;

}
