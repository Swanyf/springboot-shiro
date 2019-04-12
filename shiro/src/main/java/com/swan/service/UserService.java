package com.swan.service;

import com.swan.bean.User;

public interface UserService {

    public User getUserByName(String username);

    public User getUserById(String id);
}
