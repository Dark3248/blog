package com.wjw.blog.service.impl;

import com.wjw.blog.dao.BlogDao;
import com.wjw.blog.dto.BlogQuery;
import com.wjw.blog.dto.BlogSearch;
import com.wjw.blog.dto.BlogShow;
import com.wjw.blog.dto.BlogUpdate;
import com.wjw.blog.entity.Blog;
import com.wjw.blog.entity.BlogAndTag;
import com.wjw.blog.entity.Tag;
import com.wjw.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

    @Override
    public BlogUpdate getBlog(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public List<BlogQuery> getAllBlog() {
        return blogDao.getAllBlogQuery();
    }

    @Override
    public List<BlogQuery> getBlogBySearch(BlogSearch condition) {
        return blogDao.getBlogBySearch(condition);
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        blog.setViews(0);
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        List<Tag> tags = blog.getTags();
        BlogAndTag bat = null;
        for(Tag tag : tags) {
            bat = new BlogAndTag(tag.getId(), blog.getId());
            blogDao.saveBlogAndTag(bat);
        }
        return blogDao.saveBlog(blog);
    }

    @Transactional
    @Override
    public int deleteBlog(Long id) {
        blogDao.deleteBlog(id);
        blogDao.deleteBlogAndTag(id);
        return 1;
    }

    @Transactional
    @Override
    public int updateBlog(BlogUpdate blogUpdate) {
        blogUpdate.setUpdateTime(new Date());
        return blogDao.updateBlog(blogUpdate);
    }

    @Override
    public List<BlogShow> getAllBlogShow() {
        return blogDao.getAllBlogShow();
    }
}
