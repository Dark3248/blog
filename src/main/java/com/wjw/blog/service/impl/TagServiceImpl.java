package com.wjw.blog.service.impl;

import com.wjw.blog.dao.TagDao;
import com.wjw.blog.entity.Tag;
import com.wjw.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Transactional
    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }

    @Transactional
    @Override
    public int deleteTag(Long id) {
        return tagDao.deleteTag(id);
    }

    @Transactional
    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public Tag getTagById(Long id) {
        return tagDao.getTagById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    @Override
    public List<Tag> getAdminTag() {
        return tagDao.getAdminTag();
    }

    @Override
    public List<Tag> getTagByString(String str) {
        List<Tag> tags = new ArrayList<>();
        List<Long> ids = convertToList(str);
        for(Long id : ids) {
            tags.add(tagDao.getTagById(id));
        }
        return tags;
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if(ids != null && !ids.equals("")) {
            String[] strings = ids.split(",");
            for(String str : strings) {
                list.add(new Long(str));
            }
        }
        return list;
    }
}
