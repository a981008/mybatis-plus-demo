package com.wang.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author Wang
 * @since 2022-01-22
 */
@Accessors(chain = true)
@Data
public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "resource_id", type = IdType.AUTO)
    private Long resourceId;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源类型(0、目录 1、菜单 2、按钮)
     */
    private Integer resourceType;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 权限标识码
     */
    private String code;

    /**
     * 排序
     */
    private Integer sort;

}
