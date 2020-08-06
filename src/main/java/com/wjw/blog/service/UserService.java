package com.wjw.blog.service;

import com.wjw.blog.entity.User;

public interface UserService {

    User checkAdmin(String username, String password);

    User checkUser(String username, String password);

    int addUser(User user);

}
