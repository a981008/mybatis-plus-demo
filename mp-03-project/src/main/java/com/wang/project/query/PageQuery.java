package com.wang.project.query;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 需要分页的查询
 *
 * @author Wang
 * @since 2022/1/24
 */

@Accessors(chain = true)
@Data
public class PageQuery {
    /**
     * 页数
     */
    private Long page;
    /**
     * 每页条数
     */
    private Long limit;
}
