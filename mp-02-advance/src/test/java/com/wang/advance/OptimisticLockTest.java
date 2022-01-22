package com.wang.advance;

import com.wang.advance.bean.User;
import com.wang.advance.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 乐观锁演示
 *
 * 使用方法
 * 1. config 配置 OptimisticLockerInnerInterceptor
 * 2. 对 version 字段标记 @Version
 *
 * 乐观锁利用版本号实现，适合读多写少的并发场景
 * update user
 * set age = ?, version = 2
 * where id = ? and version = 1;
 *
 * @author Wang
 * @since 2022/1/22
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OptimisticLockTest {
    @Autowired
    UserMapper userMapper;

    /**
     * 支持的数据类型只有:int,Integer,long,Long,Date,Timestamp,LocalDateTime
     * 整数类型下 newVersion = oldVersion + 1
     * newVersion 会回写到 entity 中
     * 仅支持 updateById(id) 与 update(entity, wrapper) 方法
     * 在 update(entity, wrapper) 方法下, wrapper 不能复用!!!
     */
    @Test
    public void updateByIdTest() {
        Integer version = 1; // 假设这个是从数据库里得到的

        User user = new User();
        user.setId(1088250446457389058L);
        user.setEmail("lyw@qq.com");
        user.setVersion(version);

        userMapper.updateById(user);
    }
}
