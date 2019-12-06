package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Users;
import com.example.demo.mapper.UsersMapper;
import com.example.demo.model.UserDTO;
import com.example.demo.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xxx
 * @since 2019-12-04
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {


    @Override
    public void testMp() {
        Users users = new Users();
        this.baseMapper.delete(new QueryWrapper<>(users));
    }
}
