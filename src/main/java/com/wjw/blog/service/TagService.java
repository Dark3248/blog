package com.wjw.blog.service;

import com.wjw.blog.entity.Tag;

import java.util.List;

public interface TagService {

    int saveTag(Tag tag);

    int deleteTag(Long id);

    int updateTag(Tag tag);

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    List<Tag> getAdminTag();

    List<Tag> getTagByString(String str);

    List<Tag> getAllTag();
}
