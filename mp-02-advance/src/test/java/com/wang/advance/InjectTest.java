package com.wang.advance;

import com.wang.advance.dao.UserMapper;
import com.wang.advance.method.DeleteAll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 自定义 SQL 注入
 *
 * @author Wang
 * @since 2022/1/22
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class InjectTest {
    @Autowired
    private UserMapper userMap;

    /**
     * @see DeleteAll
     */
    @Test
    public void deleteAllTest() {
        int rows = userMap.deleteAll();
        System.out.println(rows);
    }
}
