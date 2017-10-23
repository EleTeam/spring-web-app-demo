package com.et.springwebapp.service.impl;

import com.et.springwebapp.entity.BaseEntity;
import com.et.springwebapp.exception.ServiceException;
import com.et.springwebapp.service.BaseService;
import com.et.springwebapp.util.SnowflakeIdWorkerFactory;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    @Override
    public int add(T entity) {
        //设置记录默认值
        long id = SnowflakeIdWorkerFactory.nextId();
        Long time = System.currentTimeMillis();
        if (ObjectUtils.isEmpty(entity.getId()))
            entity.setId(id);
        if (ObjectUtils.isEmpty(entity.getStatus()))
            entity.setStatus(T.StatusActive);
        if (ObjectUtils.isEmpty(entity.getCreateTime()))
            entity.setCreateTime(time);
        if (ObjectUtils.isEmpty(entity.getUpdateTime()))
            entity.setUpdateTime(time);
        if (ObjectUtils.isEmpty(entity.getId())) {
            //throw new ServiceException("新增实体缺少主键id");
            return 0;
        }
        return this.getMapper().add(entity.getTableName(), entity.toDbData());
    }

    @Override
    public int update(T entity) throws ServiceException {
        //设置记录默认值
        Long time = System.currentTimeMillis();
        if (ObjectUtils.isEmpty(entity.getUpdateTime()))
            entity.setUpdateTime(time);

        if (ObjectUtils.isEmpty(entity.getId())) {
            throw new ServiceException("更新实体缺少主键id");
        }
        return this.getMapper().update(entity.getTableName(), entity.toDbData());
    }

//    @Override
//    public int updateChanged(T entity) throws ServiceException {
//        if (ObjectUtils.isEmpty(entity.getId())) {
//            throw new ServiceException("【" + BaseServiceImpl.class.getName() + "】update(T entity)非安全更新【T 对象缺少id】");
//        }
//        return this.getMapper().updateChanged(entity);
//    }

    @Override
    public boolean isExist(T entity) {
        int rows = this.getMapper().isExist(entity.getTableName(), entity.toDbData());
        if (rows > 0)
            return true;
        else
            return false;
    }

    @Override
    public T find(T entity) {
        return (T) this.getMapper().find(entity);
    }

    @Override
    public T findWithQueryFields(T entity) {
        return (T) this.getMapper().findWithQueryFields(entity);
    }

//    @Override
//    public List<T> findList(T entity) {
//        return this.getMapper().findList(entity);
//    }
//
//    @Override
//    public <T> void fillPager(PageModel<T> pager) {
//        //设置totalPage和totalRows
//        Integer count = this.getMapper().findPageCount(pager);
//        pager.setTotalRows(count);
//
//        if(count == null || count == 0){
//            pager.setData(new ArrayList<T>());
//        } else {
//            pager.setData(this.getMapper().findPage(pager));
//        }
//    }
//
//    @Override
//    public List<T> findPage(PageModel pager) {
//        return null;
//    }
//
//    @Override
//    public int findPageCount(PageModel pager) {
//        return 0;
//    }
}
