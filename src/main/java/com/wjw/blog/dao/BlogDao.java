package com.wjw.blog.dao;

import com.wjw.blog.dto.*;
import com.wjw.blog.entity.Blog;
import com.wjw.blog.entity.BlogAndTag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogDao {

    BlogUpdate getBlogById(Long id);

    List<BlogQuery> getAllBlogQuery();

    List<BlogQuery> getBlogBySearch(BlogSearch condition);

    int saveBlog(Blog blog);

    int deleteBlog(Long id);

    int updateBlog(BlogUpdate blogUpdate);

    int saveBlogAndTag(BlogAndTag blogAndTag);

    int deleteBlogAndTag(Long blogId);

    List<BlogShow> getAllBlogShow();

    List<BlogRecommend> getAllRecommendBlog();

    List<BlogShow> getByTypeId(Long typeId);

}
