package com.wang.advance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MyMapper<T> extends BaseMapper<T>  {
    /**
     * 删除表中所有数据
     *
     * @return 影响行数
     */
    int deleteAll();

    /**
     * 批量插入
     * 如果个别字段在 entity 里为 null 但是数据库中有配置默认值, insert 后数据库字段是为 null 而不是默认值
     *
     * @param entityList 要插入元素的列表
     * @return 插入成功行数
     */
    int insertBatchSomeColumn(List<T> entityList);

    /**
     * 根据元素 ID 删除
     *
     * @param entityList 要删除元素的列表
     * @return 删除成功行数
     */
    int testDeleteBatch(@Param(Constants.COLLECTION) List<T> entityList);

    /**
     * 根据 ID 更新固定的那几个字段(但是不包含逻辑删除)
     *
     * @param entity 要更新的元素
     * @return 更新成功行数
     */
    int alwaysUpdateSomeColumnById(@Param(Constants.ENTITY) T entity);
}
