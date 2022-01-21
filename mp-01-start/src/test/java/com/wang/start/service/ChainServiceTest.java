package com.wang.start.service;

import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.wang.start.bean.User;
import com.wang.start.select.ChainWrapperSelectTest;
import com.wang.start.sevice.UserService;
import com.wang.start.sevice.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.wang.start.alter.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * service 链式调用演示
 *
 * service 的链式查询无需像 {@link ChainWrapperSelectTest} 传入 mapper
 *
 * @author Wang
 * @since 2022/1/21
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChainServiceTest {
    @Autowired
    UserService userService;

    /**
     * @see ChainWrapperSelectTest#selectByLambdaChainWrapperTest()
     */
    @Test
    public void selectByLambdaChainWrapperTest() {
        List<User> users = userService.lambdaQuery().lt(User::getAge, 40).list();
        users.forEach(System.out::println);
    }

    /**
     * @see UpdateTest#updateByLambdaChainWrapperTest
     */
    @Test
    public void updateByLambdaChainWrapperTest() {
        boolean isUpdate = userService.lambdaUpdate()
                .likeRight(User::getName, "卢")
                .set(User::getCreateTime, LocalDateTime.now())
                .update();

        System.out.println(isUpdate);
    }
}
