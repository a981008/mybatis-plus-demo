package com.wang.start.select;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.start.bean.User;
import com.wang.start.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用 Wrapper 完成查询
 *
 * Wrappers.query() 与 直接 new Wrapper() 相同
 *
 * @author Wang
 * @since 2022/1/20
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class WrapperSelectTest {
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
        QueryWrapper<User> query = Wrappers.query();

        query
                .like("name", "雨")
                .lt("age", 40);

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
        QueryWrapper<User> query = Wrappers.query();

        query
                .like("name", "雨")
                .between("age", 20, 40)
                .isNotNull("email");

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
        QueryWrapper<User> query = Wrappers.query();

        query
                .likeRight("name", "王")
                .or()
                .ge("age", 25)
                .orderByDesc("age")
                .orderByAsc("user_id");

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 创建日期为 2019-02-14 并且直属上级为名字为王姓
     *
     * select * from mp_user
     * where date_format(create_time, '%Y-%m-%d') = '2019-02-14'
     * and manager_id in (select user_id from mp_user where name like '王%')
     */
    @Test
    public void test04() {
        QueryWrapper<User> query = Wrappers.query();

        query
                .apply("date_format(create_time, '%Y-%m-%d') = {0}", "2019-02-14")
                .inSql("manager_id", "select user_id from mp_user where name like '王%'");

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
        QueryWrapper<User> query = Wrappers.query();

        query
                .likeRight("name", "王")
                .and(wq -> wq.lt("age", 40).or().isNotNull("email"));

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
        QueryWrapper<User> query = Wrappers.query();

        query
                .likeRight("name", "王")
                .or(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));

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
        QueryWrapper<User> query = Wrappers.query();

        query
                .nested(wq -> wq.lt("age", 40).or().isNotNull("email"))
                .likeRight("name", "王");

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
        QueryWrapper<User> query = Wrappers.query();

        query
                .in("age", 30, 31, 34, 35)
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
        QueryWrapper<User> query = Wrappers.query();

        query
                .select("user_id, name")
                .like("name", "雨")
                .lt("age", 40);

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 名字中包含雨并且年龄小于 40，除 create_time 字段均返回
     */
    @Test
    public void test10() {
        QueryWrapper<User> query = Wrappers.query();

        query
                .select(User.class, info -> !info.getColumn().equals("create_time"))
                .like("name", "雨")
                .lt("age", 40);

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 测试 selectUser
     */
    @Test
    public void test11() {
        List<User> users = selectUser("卢本伟", null);
        users.forEach(System.out::println);
    }

    /**
     * 使用传入的 name，email 查询
     * condition 实现条件渲染
     *
     * @param name 可能为空
     * @param email 可能为空
     */
    public List<User> selectUser(String name, String email) {
        QueryWrapper<User> query = Wrappers.query();

        query
                .eq(StringUtils.isNotEmpty(name), "name", name)
                .eq(StringUtils.isNotEmpty(email), "email", email);

        return userMapper.selectList(query);
    }

    /**
     * Wrappers.query(entity) 根据字段上的 @TableField(condition = ?) 选择比对方式
     * 默认是等值比对，这里对 User#name 上标记了 @TableField(condition = SqlCondition.LIKE_RIGHT)
     *
     * @see TableField
     * @see User
     */
    @Test
    public void test12() {
        User user = new User();
        user.setName("王");

        QueryWrapper<User> query = Wrappers.query(user);

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * allEq 最基本用法，使用 k:v 进行逐一匹配
     * k 是需要匹配的表中字段，v 是匹配满足的值
     * 默认情况下，k 对应的 v 为 null 时会对该字段加 is null
     */
    @Test
    public void test13() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "卢本伟");
        map.put("email", null);

        QueryWrapper<User> query = Wrappers.query();
        query.allEq(map);

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄。
     * 并且只取年龄总和小于 500 的组。
     *
     *  select avg(age) as avg_age, max(age) as max_age, min(age) as min_age
     *  from mp_user
     *  group by manager_id
     *  having sum(age) < 500;
     */
    @Test
    public void test14() {
        QueryWrapper<User> query = Wrappers.query();

        query
                .select("avg(age) as avg_age", "max(age) as max_age", "min(age) as min_age")
                .groupBy("manager_id")
                .having("sum(age) < {0}", 500);

        List<Map<String, Object>> maps = userMapper.selectMaps(query);
        maps.forEach(System.out::println);
    }

}
