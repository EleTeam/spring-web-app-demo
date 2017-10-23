package com.et.springwebapp.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * mybatis公用接口
 * @author 黄治华
 */
public interface BaseMapper<T> {

    /**
     * 新增记录, 字段为非null值, 需要设置主键id, 在SqlUtilMapper.xml实现
     * @param tableName 数据表
     * @param data 插入的数据
     * @return 受影响的行数
     */
    int add(@Param("tableName") String tableName, @Param("data") Map<String, Object> data);

    /**
     * 更新记录, 字段为非null值, 需要设置主键id, 在SqlUtilMapper.xml实现
     * @param tableName 数据表
     * @param data 更新的数据
     * @return 受影响的结果
     */
    int update(@Param("tableName") String tableName, @Param("data") Map<String, Object> data);

    /**
     * 判断对象是否存在
     * @param tableName 数据表
     * @param data 根据这些数据联合判断
     * @return
     */
    int isExist(@Param("tableName") String tableName, @Param("data") Map<String, Object> data);

    /**
     * 获取一个对象
     * @param entity 参数
     * @return 返回对象【与mapper文件相同】
     */
    T find(T entity);

    /**
     * 获取一个对象的若干字段
     * @param entity 参数
     * @return 返回对象【与mapper文件相同】
     */
    T findWithQueryFields(T entity);

//    /**
//     * <p>返回list</p>
//     * <p>备注：具体list对象 由 mapper 声明决定</p>
//     *
//     * @param entity
//     * @return
//     */
//    List<T> findList(T entity);
//
//    /**
//     * 翻页
//     * @return
//     */
//    List<T> findPage(PageModel pager);
//    int findPageCount(PageModel pager);
}