package com.wang.start.select;

import com.baomidou.mybatisplus.extension.conditions.query.ChainQuery;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.wang.start.bean.User;
import com.wang.start.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 链式 Wrapper 查询
 *
 * @author Wang
 * @since 2022/1/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChainWrapperSelectTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * Wrapper 的链式查询
     */
    @Test
    public void selectByChainWrapperTest() {
        List<User> users = new QueryChainWrapper<>(userMapper)
                .lt("age", 40)
                .list();

        users.forEach(System.out::println);
    }


    /**
     * LambdaWrapper 的链式查询
     */
    @Test
    public void selectByLambdaChainWrapperTest() {
        List<User> users = new LambdaQueryChainWrapper<>(userMapper)
                .lt(User::getAge, 40)
                .list();

        users.forEach(System.out::println);
    }
}
