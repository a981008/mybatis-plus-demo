package com.wang.project.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 带分页的角色查询对象
 *
 * @author Wang
 * @since 2022/1/24
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class RolePageQuery extends PageQuery {
    /**
     * 角色名
     */
    private String roleName;
}
