package com.wang.project.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Wang
 * @since 2022/1/24
 */
@Accessors(chain = true)
@Data
public class TreeVO {
    private String title;
    private Long id;
    private List<TreeVO> children;
    private Boolean checked;
}
