package com.wjw.blog.service.impl;

import com.wjw.blog.dao.TypeDao;
import com.wjw.blog.entity.Type;
import com.wjw.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeDao.getTypeById(id);
    }

    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    @Override
    public List<Type> getAdminType() {
        return typeDao.getAdminType();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
        return typeDao.deleteType(id);
    }
}
