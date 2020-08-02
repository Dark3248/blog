package com.wjw.blog.dto;

public class BlogSearch {

    private String title;
    private Long typeId;
    private boolean publish;
    private boolean recommend;

    public BlogSearch() {
    }

    public boolean isPublish() {
        return publish;
    }

    @Override
    public String toString() {
        return "BlogSearch{" +
                "title='" + title + '\'' +
                ", typeId=" + typeId +
                ", publish=" + publish +
                ", recommend=" + recommend +
                '}';
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
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

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}