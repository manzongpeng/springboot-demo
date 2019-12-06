package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: xxx
 * @License: (C) Copyright 2019-2099, xxx Corporation Limited.
 * @Contact: xxx@xxx.com
 * @Date: 2019/12/4 19:50
 * @Version: 1.0
 * @Description: 用户DTO
 */
@Data
public class UserDTO implements Serializable {

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 描述
     */
    private String description;

    private static final long serialVersionUID = 9183239829645831521L;
}
