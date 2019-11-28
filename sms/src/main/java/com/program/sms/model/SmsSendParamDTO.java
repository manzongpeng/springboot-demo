package com.program.sms.model;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @description: 短信发送参数DTO
 **/
@Data
public class SmsSendParamDTO<T> implements Serializable {

    private static final long serialVersionUID = -2983100497604598853L;
    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String phoneNumbers;
    /**
     * 发送类型 1：登录 2：注册 3：忘记密码 4：重置密码
     */
    @NotBlank(message = "发送类型不能为空")
    private Integer sendType;
    /**
     * 模板参数
     */
    @NotBlank(message = "模板参数不能为空")
    private T templateParam;
    /**
     * 操作者
     */
    private Long operator;
    /**
     * 描述
     */
    private String description;

}
