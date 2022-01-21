package com.wang.advance.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 逻辑删除字段需要加上 @TableLogic
 *
 * @author Wang
 * @since 2022/1/22
 */
@Data
public class User {
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 上司 id
     */
    private Long managerId;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 逻辑删除标识（0.未删除，1.已删除）
     */
    @TableLogic
    @TableField(select = false) // 不会被 select 查出来
    private Integer deleted;
}
