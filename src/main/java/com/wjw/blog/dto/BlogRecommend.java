package com.wjw.blog.dto;

/**
 * 首页右侧推荐展示
 */

public class BlogRecommend {

    private Long id;
    private String title;
    private boolean recommend;

    public BlogRecommend() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BlogRecommend{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", recommend=" + recommend +
                '}';
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}
