package com.wjw.blog.dto;

/**
 * 博客管理页面查询条件
 */

public class BlogSearch {

    private String title;
    private Long typeId;

    public BlogSearch() {
    }


    @Override
    public String toString() {
        return "BlogSearch{" +
                "title='" + title + '\'' +
                ", typeId=" + typeId +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}