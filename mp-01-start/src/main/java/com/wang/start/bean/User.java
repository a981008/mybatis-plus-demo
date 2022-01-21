package com.wang.start.bean;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * User 演示 CRUD 基本操作
 *
 * 默认将 Bean 字段名为 id 标记为主键
 * 默认将表中字段映射为驼峰方式
 *
 * @author Wang
 * @since 2022/1/20
 */

@Data
@TableName("mp_user")
public class User {
    @TableId // 标记主键
    private Long userId;
    @TableField(condition = SqlCondition.LIKE_RIGHT)
    private String name;
    private Integer age;
    private String email;
    @TableField("manager_id") // 指定表中字段名
    private Long magId;
    private LocalDateTime createTime;

    // 排除非表中字段
    // 1. 标记 transient
    // 2. 标记 static
    // 3. @TableField
    @TableField(exist = false)
    private transient String remark;

}
