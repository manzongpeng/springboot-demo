package com.program.user.model;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 登陆成功返回信息
 * @create: 2019-10-08 11:38
 **/
@Data
public class LoginSuccessDTO implements Serializable {

    private static final long serialVersionUID = 4742122317558444827L;
    /**
     * 系统token
     */
    private String token;
    /**
     * 登录账号信息
     */
    private UserDTO userDTO;

}
