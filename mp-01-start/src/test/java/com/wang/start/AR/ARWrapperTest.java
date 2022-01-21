package com.wang.start.AR;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.start.bean.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 同样可用 LambdaWrapper、Wrapper 过滤记录
 *
 *
 * @author Wang
 * @since 2022/1/21
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ARWrapperTest {
    /**
     * 查询名字以伟结尾并且年龄小于 40
     *
     * select * from student
     * where name like '%伟' and age < 40;
     */
    @Test
    public void selectTest() {
        QueryWrapper<Student> query = Wrappers.query();
        query
                .likeLeft("name", "伟")
                .lt("age", 40);

        Student student = new Student();
        List<Student> students = student.selectList(query);

        students.forEach(System.out::println);
    }

    /**
     * 删除名字中包含雨并且年龄小于 40
     *
     * delete from student
     * where name like '%雨%' and age < 40;
     */
    @Test
    public void deleteTest() {
        LambdaQueryWrapper<Student> delete = Wrappers.lambdaQuery();
        delete
                .like(Student::getName, "雨")
                .lt(Student::getAge, 40);
        Student student = new Student();
        boolean isDelete = student.delete(delete);
        System.out.println(isDelete);
    }

    /**
     * 将名字是卢本伟的变为 MM
     *
     * update student
     * set name = 'MM'
     * where name = '卢本伟';
     */
    @Test
    public void updateTest() {
        LambdaUpdateWrapper<Student> update = Wrappers.lambdaUpdate();
        update.eq(Student::getName, "卢本伟");

        Student student = new Student();
        student.setName("MM");

        boolean isUpdate = student.update(update);
        System.out.println(isUpdate);
    }
}
