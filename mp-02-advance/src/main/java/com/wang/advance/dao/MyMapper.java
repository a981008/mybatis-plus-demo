package com.wang.advance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.advance.bean.User;


public interface MyMapper<T> extends BaseMapper<T>  {
    /**
     * 删除表中所有数据
     * @return 影响行数
     */
    int deleteAll();
}
