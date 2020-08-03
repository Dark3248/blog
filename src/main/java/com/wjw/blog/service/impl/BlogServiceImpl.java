package com.wjw.blog.service.impl;

import com.wjw.blog.dao.BlogDao;
import com.wjw.blog.dto.*;
import com.wjw.blog.entity.Blog;
import com.wjw.blog.entity.BlogAndTag;
import com.wjw.blog.service.BlogService;
import com.wjw.blog.util.MarkdownUtils;
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
        List<Long> tagIds = TagServiceImpl.convertToList(blog.getTagIds());
        for(Long tagId : tagIds) {
            blogDao.saveBlogAndTag(new BlogAndTag(tagId, blog.getId()));
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
        blogDao.deleteBlogAndTag(blogUpdate.getId());
        blogUpdate.setUpdateTime(new Date());
        List<Long> tags = TagServiceImpl.convertToList(blogUpdate.getTagIds());
        for(Long tagid : tags) {
            blogDao.saveBlogAndTag(new BlogAndTag(tagid, blogUpdate.getId()));
        }
        return blogDao.updateBlog(blogUpdate);
    }

    @Override
    public List<BlogRecommend> getRecommendedBlog() {
        return blogDao.getAllRecommendBlog();
    }

    @Override
    public List<BlogShow> getAllBlogShow() {
        return blogDao.getAllBlogShow();
    }

    @Override
    public List<BlogShow> getAllByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }

    @Override
    public List<BlogShow> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    @Override
    public BlogDetail getBlogDetail(Long id) {
        BlogDetail blog = blogDao.getBlogDetail(id);
        String content = MarkdownUtils.markdownToHtmlExtensions(blog.getContent());
        blog.setContent(content);
        return blog;
    }
}
