package com.program.user.model;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 查询参数表单
 * @create: 2019-09-25 10:15
 **/
@Data
public class UserQueryFormDTO implements Serializable {

    private static final long serialVersionUID = 987835836815396656L;

    /**
     * 管理员name
     */
    private String userName;
    /**
     * 性别 性别 0：男 1：女
     */
    private Integer gender;
}
