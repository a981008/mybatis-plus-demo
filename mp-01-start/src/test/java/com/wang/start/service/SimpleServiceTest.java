package com.wang.start.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.start.bean.User;
import com.wang.start.sevice.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Service 演示
 *
 * @author Wang
 * @since 2022/1/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleServiceTest {
    @Autowired
    UserService userService;

    /**
     * 名字中包含雨并且年龄小于 40
     * <p>
     * throwEx = false  若有多条记录，返回第一个
     */
    @Test
    public void getOneTest() {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();
        query.like(User::getName, "雨").lt(User::getAge, 40);
        User user = userService.getOne(query, false);
        System.out.println(user);
    }

    /**
     * 批量插入
     */
    @Test
    public void saveBatchTest() {
        User u1 = new User();
        User u2 = new User();

        u1.setName("A");
        u1.setCreateTime(LocalDateTime.now());

        u2.setName("B");
        u2.setCreateTime(LocalDateTime.now());

        userService.saveBatch(Arrays.asList(u1, u2));
    }

    /**
     * 若没设置主键或主键对应记录不存在，则插入
     * 若设置主键且对应记录存在，则更新
     */
    @Test
    public void saveOrUpdateBatchTest() {
        User u1 = new User();
        User u2 = new User();
        User u3 = new User();

        u1.setName("A");
        u1.setCreateTime(LocalDateTime.now());

        u2.setName("B");
        u2.setCreateTime(LocalDateTime.now());

        u3.setUserId(1087982257332887553L);
        u3.setCreateTime(LocalDateTime.now());

        userService.saveOrUpdateBatch(Arrays.asList(u1, u2, u3));
    }
}
