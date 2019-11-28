package com.program.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.program.user.common.ApiException;
import com.program.user.common.Contents;
import com.program.user.common.PageModel;
import com.program.user.common.PageRequest;
import com.program.user.entity.User;
import com.program.user.entity.UserExample;
import com.program.user.entity.UserExample.Criteria;
import com.program.user.entity.UserLog;
import com.program.user.enums.UserLogTypeEnum;
import com.program.user.enums.UserStatusEnum;
import com.program.user.enums.UserErrorCodeEnum;
import com.program.user.mapper.UserLogMapper;
import com.program.user.mapper.UserMapper;
import com.program.user.model.LoginRequestDTO;
import com.program.user.model.LoginSuccessDTO;
import com.program.user.model.UserDTO;
import com.program.user.model.UserQueryFormDTO;
import com.program.user.model.UserQueryResDTO;
import com.program.user.service.UserService;
import com.program.user.utils.CopyBeanUtil;
import com.program.user.utils.GenerateIdUtil;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 用户service实现
 * @create: 2019-11-11 14:55
 **/
@Service
public class UserServiceImpl implements UserService {


    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserLogMapper userLogMapper;

    @Override
    public User queryByUserName(String userName) {
        UserExample userExample = new UserExample();
        Criteria criteria = userExample.createCriteria().andUserNameEqualTo(userName);
        List<User> users = userMapper.selectByExample(userExample);
        User user = null;
        if (CollectionUtil.isNotEmpty(users)) {
            user = users.get(NumberUtils.INTEGER_ZERO);
        }
        return user;
    }

    @Override
    public LoginSuccessDTO login(LoginRequestDTO loginRequestDTO, Integer sourceCode, String ip)
        throws ApiException {
        User user = this.queryByUserName(loginRequestDTO.getUserName());
        logger.info("{} ===> {} ===> user: {}", "UserServiceImpl", "login", user);
        if (null == user || user.getIsDel().equals(UserStatusEnum.DELETE.getStatus())) {
            logger.error("{} ===> {} ===>  {}", "UserServiceImpl", "login",
                UserErrorCodeEnum.SYS_USER_NOT_FOUND.getMessage());
            throw new ApiException(UserErrorCodeEnum.SYS_USER_NOT_FOUND.getCode(),
                UserErrorCodeEnum.SYS_USER_NOT_FOUND.getMessage());
        } else if (user.getState().equals(UserStatusEnum.LOCKED.getStatus())) {
            logger.error("{} ===> {} ===>  {}", "UserServiceImpl", "login",
                UserErrorCodeEnum.SYS_USER_LOCKED.getMessage());
            throw new ApiException(UserErrorCodeEnum.SYS_USER_LOCKED.getCode(),
                UserErrorCodeEnum.SYS_USER_LOCKED.getMessage());
        } else if (!user.getPassword()
            .equals(new Sha256Hash(loginRequestDTO.getPassword(), user.getSalt()).toHex())) {
            logger.error("{} ===> {} ===>  {}", "UserServiceImpl", "login",
                UserErrorCodeEnum.SYS_USER_PASSWORD_ERROR.getMessage());
            throw new ApiException(UserErrorCodeEnum.SYS_USER_PASSWORD_ERROR.getCode(),
                UserErrorCodeEnum.SYS_USER_PASSWORD_ERROR.getMessage());
        }
        // 修改登录时间
        user.setLastLoginTime(new Date());
        userMapper.updateByPrimaryKey(user);
        // 操作日志
        UserLog userLog = new UserLog();
        userLog.setId(GenerateIdUtil.nextId());
        userLog.setUserId(user.getId());
        userLog.setTypeId(UserLogTypeEnum.LOGIN.getCode());
        userLog.setSourceId(sourceCode);
        userLog.setIp(ip);
        userLog.setOperator(user.getId());
        userLogMapper.insertSelective(userLog);
        logger.info("{} ===> {} ===> insert login log: {}", "UserServiceImpl", "login", userLog);
        LoginSuccessDTO loginSuccessDTO = new LoginSuccessDTO();
        loginSuccessDTO.setUserDTO(CopyBeanUtil.convert(user, UserDTO.class));
        return loginSuccessDTO;
    }

    @Override
    public PageModel<UserQueryResDTO> queryUserList(PageRequest<UserQueryFormDTO> pageRequest)
        throws ApiException {
        UserQueryFormDTO userQueryFormDTO = pageRequest.getSearch();
        UserExample userExample = new UserExample();
        Criteria criteria = userExample.createCriteria();
        if (userQueryFormDTO != null) {
            logger.info("{} ===> {} ===> pageRequest: {}", "UserServiceImpl", "queryAdminList",
                pageRequest);
            String userName = userQueryFormDTO.getUserName();
            if (!StringUtils.isEmpty(userName)) {
                criteria.andUserNameLike(
                    Contents.SYMBOL_PERCENT + userName + Contents.SYMBOL_PERCENT);
            }
            Integer gender = userQueryFormDTO.getGender();
            if (gender != null) {
                criteria.andGenderEqualTo(gender);
            }
        }
        userExample.setOrderByClause("user_name ASC");
        Long total = userMapper.countByExample(userExample);
        // 设置分页值
        Integer pageNum = pageRequest.getPageNum();
        Integer size = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, size);
        List<User> adminList = userMapper.selectByExample(userExample);
        // 封装为分页结果
        PageModel<UserQueryResDTO> pageMode = new PageModel<UserQueryResDTO>(pageNum, size);
        if (CollectionUtil.isEmpty(adminList)) {
            logger.warn("{} ===> {} ===> {}", "UserServiceImpl", "queryAdminList",
                "AdminList is empty");
        } else {
            // 转换DTO
            List<UserQueryResDTO> userQueryResDTOList =
                CopyBeanUtil.convertList(adminList, UserQueryResDTO.class);
            logger.info("{} ===> {} ===> userQueryResDTOList: {}", "AdminServiceImpl",
                "queryAdminList", userQueryResDTOList);
            pageMode.setData(userQueryResDTOList);
            pageMode.setTotalNum(Math.toIntExact(total));
        }
        return pageMode;
    }
}
