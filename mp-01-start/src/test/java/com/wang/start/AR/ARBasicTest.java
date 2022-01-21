package com.wang.start.AR;

import com.wang.start.bean.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * AR 演示
 * 同前面的增删改查操作一样
 * 只要 Bean 继承 Model 可直接从 Bean 实现 CRUD，无需用 mapper
 *
 * @author Wang
 * @since 2022/1/21
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ARBasicTest {
    /**
     * 增加一条记录
     */
    @Test
    public void insertTest() {
        Student student = new Student();
        student.setName("张三");
        student.setAge(11);
        boolean isInsert = student.insert();
        System.out.println(isInsert);
    }


    /**
     * 根据主键删除
     */
    @Test
    public void deleteByIdTest() {
        Student student = new Student();
        student.setId(1088248166370832385L);
        boolean isDelete = student.deleteById();
        System.out.println(isDelete);
    }

    /**
     * 根据主键更新
     */
    @Test
    public void updateByIdTest() {
        Student student = new Student();
        student.setId(1088248166370832385L);
        student.setName("GG");
        boolean isUpdate = student.updateById();
        System.out.println(isUpdate);
    }

    /**
     * 根据主键查询
     */
    @Test
    public void selectByIdTest() {
        Student student = new Student();
        student.setId(1088248166370832385L);
        Student select = student.selectById();
        System.out.println(select);
    }

    /**
     * 先查询，查询的记录存在则更新，不存在则插入
     */
    @Test
    public void insertOrUpdateTest() {
        Student student = new Student();
        student.setId(1L);
        student.setName("QAQ");
        student.insertOrUpdate();
    }
}
