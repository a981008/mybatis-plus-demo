package com.wang.start.select;

import com.wang.start.bean.User;
import com.wang.start.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简单的查询演示
 *
 * @author Wang
 * @since 2022/1/20
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleSelectTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectByIdTest() {
        User user = userMapper.selectById(1094590409767661570L);
        System.out.println(user);
    }

    @Test
    public void selectBatchIdsTest() {
        List<Long> list = Arrays.asList(1088250446457389058L, 1094590409767661570L, 1094592041087729666L);
        List<User> users = userMapper.selectBatchIds(list);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByMapTest() {
        // key 表中字段，value 表中字段对应的数据
        Map<String, Object> map = new HashMap<>();
        map.put("name", "卢本伟");
        map.put("age", 11);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }
}
