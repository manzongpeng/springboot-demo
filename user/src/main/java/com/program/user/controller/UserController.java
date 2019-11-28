package com.program.user.controller;

import com.program.user.annotation.CheckAuth;
import com.program.user.annotation.PassAuth;
import com.program.user.common.ApiException;
import com.program.user.common.PageModel;
import com.program.user.common.PageRequest;
import com.program.user.common.ResponseBody;
import com.program.user.controller.base.BaseController;
import com.program.user.enums.SourceCodeEnum;
import com.program.user.model.LoginRequestDTO;
import com.program.user.model.LoginSuccessDTO;
import com.program.user.model.UserQueryFormDTO;
import com.program.user.model.UserQueryResDTO;
import com.program.user.service.UserService;
import com.program.user.utils.IPUtils;
import com.program.user.utils.JWTUtil;
import com.program.user.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户控制层
 * @create: 2019-09-25 17:14
 **/
@Api("用户登录相关")
@RestController
@RequestMapping("/web/login/v1")
@Validated
public class UserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    @ApiOperation("登录")
    @PassAuth
    public ResponseBody login(@RequestBody @Validated LoginRequestDTO loginRequestDTO,
        HttpServletRequest request)
        throws ApiException {
        LoginSuccessDTO loginSuccessDTO = userService
            .login(loginRequestDTO, SourceCodeEnum.PC.getCode(),
                IPUtils.getIpAddr(request));
        String token = jwtUtil.generateToken(loginSuccessDTO.getUserDTO());
        loginSuccessDTO.setToken(token);
        return ResultUtil.getSuccessResponse(getCurrentReqId(), loginSuccessDTO);
    }

    @PostMapping("/logout")
    @ApiOperation("退出")
    @CheckAuth
    public ResponseBody logout() throws ApiException {
        return ResultUtil.getSuccessResponse("logout success");
    }

    @PostMapping("/userList")
    @ApiOperation("查询列表")
    @CheckAuth
    public ResponseBody queryUserList(
        @RequestBody @Validated PageRequest<UserQueryFormDTO> pageRequest) throws ApiException {
        PageModel<UserQueryResDTO> userQueryResDTOPageModel = userService
            .queryUserList(pageRequest);
        //返回数据
        return ResultUtil.getSuccessResponse(getCurrentReqId(), userQueryResDTOPageModel);
    }
}
