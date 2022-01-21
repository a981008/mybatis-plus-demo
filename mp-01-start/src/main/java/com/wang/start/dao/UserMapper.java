package com.wang.start.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.start.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Wang
 * @since 2022/1/20
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from mp_user ${ew.customSqlSegment}")
    List<User> selectAllByAnnotation(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    List<User> selectAllByXml(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    @Select("select * from mp_user ${ew.customSqlSegment}")
    Page<User> selectAll(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
