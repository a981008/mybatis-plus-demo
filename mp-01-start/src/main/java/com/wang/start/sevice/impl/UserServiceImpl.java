package com.wang.start.sevice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.start.bean.User;
import com.wang.start.dao.UserMapper;
import com.wang.start.sevice.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Wang
 * @since 2022/1/21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
