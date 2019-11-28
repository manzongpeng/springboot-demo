package com.program.user.service;

import com.program.user.common.ApiException;
import com.program.user.common.PageModel;
import com.program.user.common.PageRequest;
import com.program.user.entity.User;
import com.program.user.model.UserQueryFormDTO;
import com.program.user.model.UserQueryResDTO;
import com.program.user.model.LoginRequestDTO;
import com.program.user.model.LoginSuccessDTO;

/**
 * @author
 * @program: saic-cloud
 * @description: 用户service
 * @create: 2019-11-11 14:55
 **/
public interface UserService {

    User queryByUserName(String userName);

    /**
     * 登录
     */
    LoginSuccessDTO login(LoginRequestDTO loginRequestDTO, Integer sourceCode, String ip)
        throws ApiException;

    /**
     * 查询管理员列表
     */
    PageModel<UserQueryResDTO> queryUserList(PageRequest<UserQueryFormDTO> pageRequest) throws ApiException;

}
