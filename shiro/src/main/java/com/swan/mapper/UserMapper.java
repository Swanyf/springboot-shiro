package com.swan.mapper;

import com.swan.bean.User;

public interface UserMapper {

    User getUserByName(String username);

    User getUserById(String id);
}
