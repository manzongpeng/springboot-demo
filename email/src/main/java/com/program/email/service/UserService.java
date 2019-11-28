package com.program.email.service;

import com.program.email.common.ApiException;
import com.program.email.entity.User;

/**
 * @description: 用户service
 **/
public interface UserService {

    /**
     * 通过邮箱查询数据
     */
    User queryUsersByEmail(String email) throws ApiException;


    User selectById(Long userId);

    void updateById(User user);
}
