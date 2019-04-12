package com.swan.service.impl;

import com.swan.bean.User;
import com.swan.mapper.UserMapper;
import com.swan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }

}
