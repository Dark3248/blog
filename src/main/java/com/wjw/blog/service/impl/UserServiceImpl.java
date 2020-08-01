package com.wjw.blog.service.impl;

import com.wjw.blog.dao.UserDao;
import com.wjw.blog.entity.User;
import com.wjw.blog.service.UserService;
import com.wjw.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
