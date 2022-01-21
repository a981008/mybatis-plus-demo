package com.wang.start.select;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.start.bean.User;
import com.wang.start.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 自定义 SQL（MyBatis 怎么用的，这就怎么用）
 *
 * 可以使用注解方式
 * 也可使用 .xml 文件配置
 *
 * @see UserMapper
 * @author Wang
 * @since 2022/1/20
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomSelectTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 注解方式自定义 SQL，返回名字中包含雨的记录
     */
    @Test
    public void annotationTest() {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();
        query.like(User::getName, "雨");
        List<User> users = userMapper.selectAllByAnnotation(query);
        users.forEach(System.out::println);
    }

    /**
     * 配置 .xml 方式自定义 SQL，返回名字中包含雨的记录
     */
    @Test
    public void xmlTest() {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();
        query.like(User::getName, "雨");
        List<User> users = userMapper.selectAllByXml(query);
        users.forEach(System.out::println);
    }
}
