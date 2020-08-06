package com.wjw.blog.dao;

import com.wjw.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    User findAdminByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    User findUser(@Param("username") String username, @Param("password") String password);

    int addUser(User user);

    User findUserByUsername(String username);

}
