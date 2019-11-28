package com.program.user.model;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @description: 登录表单
 * @create: 2019-09-25 09:24
 **/
@Data
public class LoginRequestDTO implements Serializable {

    private static final long serialVersionUID = -7353601264137916724L;
    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

}
