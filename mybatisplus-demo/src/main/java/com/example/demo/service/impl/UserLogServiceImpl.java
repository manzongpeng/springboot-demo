package com.example.demo.service.impl;

import com.example.demo.entity.UserLog;
import com.example.demo.mapper.UserLogMapper;
import com.example.demo.service.UserLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录日志 服务实现类
 * </p>
 *
 * @author xxx
 * @since 2019-12-04
 */
@Service
public class UserLogServiceImpl extends ServiceImpl<UserLogMapper, UserLog> implements UserLogService {

}
