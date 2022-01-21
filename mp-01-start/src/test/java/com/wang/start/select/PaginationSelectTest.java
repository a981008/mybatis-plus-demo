package com.wang.start.select;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.start.bean.User;
import com.wang.start.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 使用分页插件
 *
 * @author Wang
 * @since 2022/1/21
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaginationSelectTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 使用分页
     */
    @Test
    public void test01() {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();
        query.gt(User::getAge, 30).orderByAsc(User::getAge);

        // 第一页，两条一页
        // searchCount = false，不会查总记录数（默认 true）
        Page<User> page = new Page<>(1, 2, false);

        Page<User> userPage = userMapper.selectPage(page, query);

        List<User> records = userPage.getRecords();
        records.forEach(System.out::println);
    }

    /**
     * 自定义查询使用分页
     */
    @Test
    public void test02() {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();
        query.gt(User::getAge, 30).orderByAsc(User::getAge);

        Page<User> page = new Page<>(1, 2);
        Page<User> userPage = userMapper.selectAll(page, query);

        List<User> records = userPage.getRecords();
        records.forEach(System.out::println);
    }
}
