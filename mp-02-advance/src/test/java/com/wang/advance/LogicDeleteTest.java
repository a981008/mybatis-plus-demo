package com.wang.advance;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.advance.bean.User;
import com.wang.advance.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 逻辑删除演示
 *
 * @author Wang
 * @since 2022/1/22
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogicDeleteTest {
    @Autowired
    private UserMapper userMap;

    /**
     * delete 变为了 update
     */
    @Test
    public void deleteByIdTest() {
        int row = userMap.deleteById(1088248166370832385L);
        System.out.println(row);
    }

    /**
     * 查询不到被逻辑删除的记录
     */
    @Test
    public void selectTest() {
        List<User> users = userMap.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 不会更新被逻辑删除的记录
     */
    @Test
    public void updateByIdTest() {
        User user = new User();
        user.setId(1088248166370832385L);
        user.setName("GG");

        int row = userMap.updateById(user);

        System.out.println(row);
    }

    /**
     * 逻辑删除不会对自定义 SQL 生效，需要人为加上
     */
    @Test
    public void mySelectTest() {
        LambdaQueryWrapper<User> query = Wrappers.<User>lambdaQuery()
                .eq(User::getDeleted, 0);
        List<User> users = userMap.mySelectAll(query);
        users.forEach(System.out::println);
    }
}
