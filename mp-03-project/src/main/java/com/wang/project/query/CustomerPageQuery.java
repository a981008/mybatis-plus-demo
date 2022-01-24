package com.wang.project.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 带分页的用户查询对象
 *
 * @author Wang
 * @since 2022/1/23
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class CustomerPageQuery extends PageQuery {
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 电话号码
     */
    private String phone;
}
