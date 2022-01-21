package com.wang.start.bean;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Student 演示 AR
 *
 * @author Wang
 * @since 2022/1/21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Student extends Model<Student> {
    @TableId
    private Long id;
    private String name;
    private Integer age;
}
