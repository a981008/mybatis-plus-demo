package com.wang.project.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 带分页的账户查询对象
 *
 * @author Wang
 * @since 2022/1/23
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class AccountPageQuery extends PageQuery {
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 创建时间范围
     */
    private String createTimeRange;
}
