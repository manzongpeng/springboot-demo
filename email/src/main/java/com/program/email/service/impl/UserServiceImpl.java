package com.program.email.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.program.email.common.ApiException;
import com.program.email.entity.User;
import com.program.email.entity.UserExample;
import com.program.email.mapper.UserMapper;
import com.program.email.service.UserService;
import java.util.List;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 用户实现层
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUsersByEmail(String email) throws ApiException {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(email);
        List<User> users = userMapper.selectByExample(userExample);
        User user = null;
        if (CollectionUtil.isNotEmpty(users)) {
            user = users.get(NumberUtils.INTEGER_ZERO);
        }
        return user;
    }

    @Override
    public User selectById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void updateById(User user) {
         userMapper.updateByPrimaryKeySelective(user);
    }

}
