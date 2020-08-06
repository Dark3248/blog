package com.wjw.blog.service.impl;

import com.wjw.blog.dao.CommentDao;
import com.wjw.blog.entity.Comment;
import com.wjw.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplies = new ArrayList<>();

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        //拿到第一级的评论
        List<Comment> comments = commentDao.findByBlogId(blogId, (long)-1);
        for(Comment comment : comments) {
            List<Comment> childComments = commentDao.findByBlogId(blogId, comment.getId());
            for(Comment child : childComments) {
                recursively(child);
            }
            comment.setReplyComments(tempReplies);
            tempReplies = new ArrayList<>();
        }
        return comments;
    }

    private void recursively(Comment comment) {
        tempReplies.add(comment);
        comment.getParentComment().setNickname(commentDao.getNickname(comment.getParentComment().getId()));
        comment.setReplyComments(commentDao.findByBlogId(comment.getBlogId(), comment.getId()));
        if (comment.getReplyComments().size() > 0) {
            List<Comment> replies = comment.getReplyComments();
            for (Comment reply : replies) {
                recursively(reply);
            }
        }
    }

    @Transactional
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        return commentDao.saveComment(comment);
    }
}
