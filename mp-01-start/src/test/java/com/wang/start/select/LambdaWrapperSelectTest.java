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
 * 使用 LambdaWrapper 完成 {@link WrapperSelectTest} 中的题
 *
 * LambdaWrapper 与 Wrapper 一样，但可以使用 lambda 特性
 * 如：Wrapper 使用字符串获取字段，而 LambdaWrapper 使用方法引用获取字段
 *
 * 以下三者相同
 * new QueryWrapper<>().lambda()
 * new LambdaQueryWrapper<>()
 * Wrappers.lambdaQuery()
 *
 * @author Wang
 * @since 2022/1/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LambdaWrapperSelectTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 名字中包含雨并且年龄小于 40
     *
     * select * from mp_user
     * where name like '%雨%' and age < 40
     */
    @Test
    public void test01() {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();

        query
                .like(User::getName, "雨")
                .lt(User::getAge, 40);

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 名字中包含雨年并且龄大于等于 20 且小于等于 40 并且 email 不为空
     *
     * select * from mp_user
     * where name like '%雨%' and age between 20 and 40 and email is not null
     */
    @Test
    public void test02() {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();

        query
                .like(User::getName, "雨")
                .between(User::getAge, 20, 40)
                .isNotNull(User::getEmail);

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 名字为王姓或者年龄大于等于 25，按照年龄降序排列，年龄相同按照 user_id 升序排列
     *
     * select * from mp_user
     * where name like '王%' or age >= 25
     * order by age desc, user_id asc
     */
    @Test
    public void test03() {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();

        query
                .likeRight(User::getName, "王")
                .or()
                .ge(User::getAge, 25)
                .orderByDesc(User::getAge)
                .orderByAsc(User::getUserId);

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 名字为王姓并且（年龄小于 40 或邮箱不为空）
     *
     * select * from mp_user
     * where name like '王%'
     * and (age < 40 or email is not null)
     */
    @Test
    public void test05() {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();

        query
                .likeRight(User::getName, "王")
                .and(wq -> wq.lt(User::getAge, 40).or().isNotNull(User::getEmail));

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 名字为王姓或者（年龄小于 40 并且年龄大于 20 并且邮箱不为空）
     *
     * select * from mp_user
     * where name like '王%'
     * or (age < 40 and age > 20 and email is not null)
     */
    @Test
    public void test06() {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();

        query
                .likeRight(User::getName, "王")
                .or(wq -> wq.lt(User::getAge, 40).gt(User::getAge, 20).isNotNull(User::getEmail));

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * （年龄小于 40 或邮箱不为空）并且名字为王姓
     *
     * select * from mp_user
     * where (age < 40 or email is not null)
     * and name like '王%'
     */
    @Test
    public void test07() {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();

        query
                .nested(wq -> wq.lt(User::getAge, 40).or().isNotNull(User::getEmail))
                .likeRight(User::getName, "王");

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 年龄为 30、31、34、35，只返回满足条件的其中一条语句即可
     *
     * select * from mp_user where age in (30, 31, 34, 35) limit 1
     */
    @Test
    public void test08() {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();

        query
                .in(User::getAge, 30, 31, 34, 35)
                .last("limit 1");

        User user = userMapper.selectOne(query);
        System.out.println(user);
    }

    /**
     * 名字中包含雨并且年龄小于 40，只返回 user_id 和 name
     *
     * select user_id, name from mp_user
     * where name like '%雨%' and age < 40
     */
    @Test
    public void test09() {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();

        query
                .select(User::getUserId, User::getName)
                .like(User::getName, "雨")
                .lt(User::getAge, 40);

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 名字中包含雨并且年龄小于 40，除 create_time 字段均返回
     */
    @Test
    public void test10() {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();

        query
                .select(User.class, info -> !info.getColumn().equals("create_time"))
                .like(User::getName, "雨")
                .lt(User::getAge, 40);

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }
}
