package com.wang.project.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Wang
 * @since 2022/1/23
 */
@Accessors(chain = true)
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String CREATE_TIME = "createTime";
    public static final String MODIFIED_TIME = "modifiedTime";
    public static final String CREATE_ACCOUNT_ID = "createAccountId";
    public static final String MODIFIED_ACCOUNT_ID = "modifiedAccountId";


    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime modifiedTime;

    /**
     * 创建人 ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createAccountId;

    /**
     * 修改人 ID
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long modifiedAccountId;
    
    /**
     * 逻辑删除标识(0、否 1、是)
     */
    @TableLogic
    private Integer deleted;
}
