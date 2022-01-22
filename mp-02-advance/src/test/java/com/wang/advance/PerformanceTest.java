package com.wang.advance;

import com.wang.advance.bean.User;
import com.wang.advance.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 详细看 <a href="https://baomidou.com/pages/833fab/"> 官网 </a>
 *
 * @author Wang
 * @since 2022/1/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PerformanceTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void selectAllTest() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
