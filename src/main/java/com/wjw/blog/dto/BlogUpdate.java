package com.wjw.blog.dto;

import java.util.Date;

/**
 * 博客修改页面填写的数据
 */
public class BlogUpdate {

        private Long id;
        private String flag;
        private String title;
        private String content;
        private Long typeId;
        private String tagIds;
        private String firstPicture;
        private String description;
        private boolean publish;
        private boolean recommend;
        private boolean comment;
        private Date updateTime;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public boolean isPublish() {
                return publish;
        }

        public void setPublish(boolean publish) {
                this.publish = publish;
        }

        public String getFlag() {
                return flag;
        }

        public void setFlag(String flag) {
                this.flag = flag;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public Long getTypeId() {
                return typeId;
        }

        public void setTypeId(Long typeId) {
                this.typeId = typeId;
        }

        public String getTagIds() {
                return tagIds;
        }

        public void setTagIds(String tagIds) {
                this.tagIds = tagIds;
        }

        public String getFirstPicture() {
                return firstPicture;
        }

        public void setFirstPicture(String firstPicture) {
                this.firstPicture = firstPicture;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public boolean isRecommend() {
                return recommend;
        }

        public void setRecommend(boolean recommend) {
                this.recommend = recommend;
        }

        public boolean isComment() {
                return comment;
        }

        public void setComment(boolean comment) {
                this.comment = comment;
        }

        public Date getUpdateTime() {
                return updateTime;
        }

        public void setUpdateTime(Date updateTime) {
                this.updateTime = updateTime;
        }

        @Override
        public String toString() {
                return "BlogUpdate{" +
                        "id=" + id +
                        ", publish=" + publish +
                        ", flag='" + flag + '\'' +
                        ", title='" + title + '\'' +
                        ", content='" + content + '\'' +
                        ", typeId=" + typeId +
                        ", tagIds=" + tagIds +
                        ", firstPicture='" + firstPicture + '\'' +
                        ", description='" + description + '\'' +
                        ", recommend=" + recommend +
                        ", comment=" + comment +
                        ", updateTime=" + updateTime +
                        '}';
        }
}
