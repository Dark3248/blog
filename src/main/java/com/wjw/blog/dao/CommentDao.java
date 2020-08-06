package com.wjw.blog.dao;

import com.wjw.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {

    List<Comment> findByBlogId(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);

    //添加一个评论
    int saveComment(Comment comment);

    String getNickname(Long id);

}
