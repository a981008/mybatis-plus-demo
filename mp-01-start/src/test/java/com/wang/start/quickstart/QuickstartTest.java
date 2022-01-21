package com.wang.start.quickstart;

import com.wang.start.bean.User;
import com.wang.start.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 快速开始
 *
 * @author Wang
 * @since 2022/1/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuickstartTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有记录
     */
    @Test
    public void selectTest() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 插入一条数据
     */
    @Test
    public void insertTest() {
        User user = new User();
        user.setAge(11);
        user.setName("卢本伟");
        user.setMagId(1087982257332887553L);
        user.setCreateTime(LocalDateTime.now());
        user.setRemark("卢本伟牛逼");

        int rows = userMapper.insert(user);

        System.out.println(rows);
    }

    /**
     * 查询总共的记录数
     */
    @Test
    public void countTest() {
        Long count = userMapper.selectCount(null);
        System.out.println(count);
    }
}
