package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Contents;
import com.example.demo.common.CopyBeanUtil;
import com.example.demo.entity.Users;
import com.example.demo.model.UserDTO;
import com.example.demo.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xxx
 * @since 2019-12-04
 */
@RestController
@RequestMapping("/users")
@Api("用户控制层")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PutMapping("/addUser")
    @ApiOperation("添加用户")
    public void addUser(@Validated @RequestBody UserDTO userDTO) {
        Users users = CopyBeanUtil.convert(userDTO, Users.class);
        //生成盐，加密密码
        String salt = RandomStringUtils.randomAlphanumeric(Contents.SALT_RANDOM_ALPHANUMERIC);
        users.setPassword(new Sha256Hash(users.getPassword(), salt).toHex());
        users.setSalt(salt);
        users.setOperator(123456L);
        usersService.save(users);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestBody List ids) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("user_name", "string");
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        queryWrapper.in("gender", list);
        usersService.remove(queryWrapper);
    }

    @PostMapping("/updateUser")
    public void updateUser() {

    }


}
