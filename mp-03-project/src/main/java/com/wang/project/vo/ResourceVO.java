package com.wang.project.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Wang
 * @since 2022/1/22
 */
@Accessors(chain = true)
@Data
public class ResourceVO {
    /**
     * 主键
     */
    private Long resourceId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 下级资源
     */
    private List<ResourceVO> subs;
}
