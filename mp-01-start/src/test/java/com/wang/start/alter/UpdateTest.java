package com.wang.start.alter;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.wang.start.bean.User;
import com.wang.start.dao.UserMapper;
import com.wang.start.select.ChainWrapperSelectTest;
import com.wang.start.select.LambdaWrapperSelectTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * 更新数据
 *
 * @author Wang
 * @since 2022/1/21
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据主键更新
     */
    @Test
    public void updateByIdTest() {
        User user = new User();
        user.setUserId(1484118030976262146L);
        user.setCreateTime(LocalDateTime.now());

        int rows = userMapper.updateById(user);

        System.out.println(rows);
    }

    /**
     * Wrapper 过滤记录更新
     */
    @Test
    public void updateByWrapperTest() {
        UpdateWrapper<User> update = Wrappers.update();
        update.likeRight("name", "卢");

        User user = new User();
        user.setCreateTime(LocalDateTime.now());

        int rows = userMapper.update(user, update);
        System.out.println(rows);
    }

    /**
     * 根据 Entity 更新
     * 默认是等值比对，这里对 User#name 上标记了 @TableField(condition = SqlCondition.LIKE_RIGHT)
     *
     * @see TableField
     * @see User
     */
    @Test
    public void updateByEntityTest() {
        User user = new User();
        user.setName("卢");

        UpdateWrapper<User> update = Wrappers.update(user);
        update.set("create_time", LocalDateTime.now());

        // 通过上面的 set，无需传新的 entity
        int rows = userMapper.update(null, update);
        System.out.println(rows);
    }

    /**
     * 同样有 LambdaUpdateWrapper
     *
     * @see LambdaWrapperSelectTest
     */
    @Test
    public void updateByLambdaWrapperTest() {
        User user = new User();
        user.setName("卢");

        LambdaUpdateWrapper<User> update = Wrappers.lambdaUpdate(user)
                .set(User::getCreateTime, LocalDateTime.now());

        int rows = userMapper.update(null, update);
        System.out.println(rows);
    }


    /**
     * 同样有 UpdateChainWrapper 和 LambdaUpdateChainWrapper
     *
     * @see ChainWrapperSelectTest
     */
    @Test
    public void updateByLambdaChainWrapperTest() {
        boolean isUpdate = new LambdaUpdateChainWrapper<>(userMapper)
                .likeRight(User::getName, "卢")
                .set(User::getCreateTime, LocalDateTime.now())
                .update();
        System.out.println(isUpdate);
    }
}
