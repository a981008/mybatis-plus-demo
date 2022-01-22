package com.wang.advance;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.injector.methods.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.injector.methods.LogicDeleteBatchByIds;
import com.wang.advance.bean.User;
import com.wang.advance.dao.UserMapper;
import com.wang.advance.method.DeleteAll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;

/**
 * SQL 注入器演示案例
 *
 * @author Wang
 * @since 2022/1/22
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class InjectTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 自定义 SQL
     *
     * @see DeleteAll
     */
    @Test
    public void deleteAllTest() {
        int rows = userMapper.deleteAll();
        System.out.println(rows);
    }

    /**
     * 选装件：批量插入，只保证在 MySQL 使用
     * 如果个别字段在 entity 里为 null 但是数据库中有配置默认值, insert 后数据库字段是为 null 而不是默认值
     * 如这里的 version 数据库中默认值为 1，但是实际插入后为 null
     *
     * @see InsertBatchSomeColumn
     */
    @Test
    public void insertBatchTest() {
        User u1 = new User();
        u1.setName("A");
        u1.setAge(18);

        User u2 = new User();
        u2.setName("A");
        u2.setAge(18);

        int rows = userMapper.insertBatchSomeColumn(Arrays.asList(u1, u2));
        System.out.println(rows);
    }

    /**
     * 选装件：根据 ID 批量逻辑删除并填充字段
     * <p>
     * {@link BaseMapper#deleteBatchIds} 批量删除不会填充字段，
     * 注入后，并在填充字段上标记  @TableField(fill = FieldFill.UPDATE)
     * 使用 testDeleteBatch 方法对删除的记录也会填充
     *
     * @see LogicDeleteBatchByIds
     */
    @Test
    public void logicDeleteBatchTest() {
        User u = new User();
        u.setId(1088248166370832385L);
        u.setAge(99);

        int rows = userMapper.testDeleteBatch(Collections.singletonList(u));

        System.out.println(rows);
    }

    /**
     * 选装件：根据 ID 固定更新字段（不包含逻辑删除字段）
     * 为 null 的字段会更新表中字段也为 null
     * 这里因为排除了 name 字段，所以 name 字段不会置为 null
     *
     * @see AlwaysUpdateSomeColumnById
     */
    @Test
    public void alwaysUpdateSomeColumnTest() {
        User u = new User();
        u.setId(1094590409767661570L);
        u.setAge(11);

        int row = userMapper.alwaysUpdateSomeColumnById(u);
        System.out.println(row);
    }
}
