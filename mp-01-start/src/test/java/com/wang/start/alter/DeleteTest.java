package com.wang.start.alter;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.start.bean.User;
import com.wang.start.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 删除数据
 *
 * @author Wang
 * @since 2022/1/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据主键删除
     */
    @Test
    public void deleteByIdTest() {
        int row = userMapper.deleteById(1484112380493766657L);
        System.out.println(row);
    }

    /**
     * 根据 k:v 删除
     */
    @Test
    public void deleteByMapTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 32);
        int rows = userMapper.deleteByMap(map);
        System.out.println(rows);
    }

    /**
     * 使用 Wrapper 删除
     * QueryWrapper 用于生成 where 子句
     * LambdaQueryWrapper 同上
     *
     * 删除名字中包含雨并且年龄小于 40
     *
     * delete from mp_user
     * where name like '%雨%' and age < 40;
     */
    @Test
    public void deleteByWrapperTest() {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();
        query.like(User::getName, "雨").lt(User::getAge, 40);
        int rows = userMapper.delete(query);
        System.out.println(rows);
    }

    /**
     * 根据主键批量删除
     */
    @Test
    public void deleteBatchIdsTest() {
        int rows = userMapper.deleteBatchIds(Arrays.asList(1088248166370832385L, 1088250446457389058L));
        System.out.println(rows);
    }

}
