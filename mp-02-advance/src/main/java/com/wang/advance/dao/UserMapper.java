package com.wang.advance.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wang.advance.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Wang
 * @since 2022/1/22
 */
public interface UserMapper extends MyMapper<User> {
    @Select("select * from user ${ew.customSqlSegment}")
    List<User> mySelectAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
