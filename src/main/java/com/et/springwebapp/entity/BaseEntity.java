package com.et.springwebapp.entity;

import com.et.springwebapp.exception.ServiceException;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tony on 17/10/20.
 */
abstract public class BaseEntity implements Serializable {

    protected Long id; //主键
    protected Integer status;
    protected Long createTime;
    protected Long updateTime;

    //非数据库字段，用于查询对象的若干字段，值如："id, status, name"
    protected String queryFields;

    /***** 公共字段常量定义 ******/
    //status记录状态, 0禁用，1启用，2删除
    public static final Integer StatusInactive = 0;
    public static final Integer StatusActive = 1;
    public static final Integer StatusDelete = 2;
    //0或1的定义
    public static final Integer No = 0;
    public static final Integer Yes = 1;

    abstract public String getTableName();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getQueryFields() {
        return queryFields;
    }

    public void setQueryFields(String queryFields) {
        this.queryFields = queryFields;
    }

    /**
     * 实体在数据表里的非null字段转为map对象
     * @return
     * @throws ServiceException
     */
    public Map<String, Object> toDbData() {
        try {
            Map<String, Object> data = new HashMap<>();
            BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤非数据表字段
                if (!key.equals("class")
                        && !key.equals("tableName")
                        && !key.equals("serialVersionUID")
                        && !key.equals("queryFields")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(this);
                    if (value != null) {
                        data.put(key, value);
                    }
                }
            }
            return data;
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
            //throw new ServiceException("对象属性转为dataMap出错：" + e.getMessage());
            return null;
        }
    }

}
