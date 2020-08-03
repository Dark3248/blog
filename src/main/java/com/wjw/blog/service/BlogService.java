package com.wjw.blog.service;

import com.wjw.blog.dto.*;
import com.wjw.blog.entity.Blog;

import java.util.List;

public interface BlogService {

    BlogUpdate getBlog(Long id);

    List<BlogQuery> getAllBlog();

    int saveBlog(Blog blog);

    int deleteBlog(Long id);

    int updateBlog(BlogUpdate blogUpdate);

    List<BlogQuery> getBlogBySearch(BlogSearch condition);

    List<BlogShow> getAllBlogShow();

    List<BlogRecommend> getRecommendedBlog();

    List<BlogShow> getAllByTypeId(Long typeId);

}
