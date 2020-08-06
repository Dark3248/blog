package com.wjw.blog.service.impl;

import com.wjw.blog.dao.UserDao;
import com.wjw.blog.entity.User;
import com.wjw.blog.service.UserService;
import com.wjw.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkAdmin(String username, String password) {
        return userDao.findAdminByUsernameAndPassword(username, MD5Utils.code(password));
    }

    @Override
    public User checkUser(String username, String password) {
        return userDao.findUser(username, MD5Utils.code(password));
    }

    @Override
    public int addUser(User user) {
        if(userDao.findUserByUsername(user.getUsername()) != null)
            return 0;
        user.setAvatar("https://picsum.photos/id/" + ((int)(Math.random() * 1080 + 1)) + "/100/100");
        user.setCreateTime(new Date());
        user.setNickname(user.getUsername());
        user.setUpdateTime(new Date());
        user.setPassword(MD5Utils.code(user.getPassword()));
        user.setType(0);
        return userDao.addUser(user);
    }
}
