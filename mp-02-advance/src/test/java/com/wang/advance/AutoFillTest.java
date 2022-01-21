package com.wang.advance;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.advance.bean.User;
import com.wang.advance.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * 自动填充演示
 *
 * 使用自动填充
 * 1. 在所需自动填充字段上加 @TableField(fill = FieldFill.?)
 * 2. 实现自动填充处理器
 *
 * @author Wang
 * @since 2022/1/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoFillTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 插入数据，自动填充
     */
    @Test
    public void insertTest() {
        User user = new User();
        user.setName("卢本伟");
        user.setAge(18);
        int row = userMapper.insert(user);
        System.out.println(row);
    }

    /**
     * 更新数据，自动填充
     */
    @Test
    public void updateTest() {
        LambdaUpdateWrapper<User> update = Wrappers.lambdaUpdate();
        update.eq(User::getName, "卢本伟").set(User::getAge, 22);

        User user = new User();
        user.setAge(22);
        // user.setUpdateTime(LocalDateTime.parse("2011-01-12T02:44:23"));

       int rows = userMapper.update(user, update);

        System.out.println(rows);
    }

}
