package com.et.springwebapp.service.impl;

import com.et.springwebapp.entity.User;
import com.et.springwebapp.mapper.UserMapper;
import com.et.springwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 必须有@Service("userService") 这个注解才能在其它地方如控制器用自动载入@Autowired
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserMapper getMapper() {
        return userMapper;
    }

}
