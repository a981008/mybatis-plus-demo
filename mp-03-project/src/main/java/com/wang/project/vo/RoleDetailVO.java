package com.wang.project.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 角色详情
 *
 * @author Wang
 * @since 2022/1/24
 */
@Accessors(chain = true)
@Data
public class RoleDetailVO {
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifiedTime;

    /**
     * 修改人
     */
    private String createAccountName;

    /**
     * 创建人
     */
    private String modifiedAccountName;
}
