package com.wang.project.mapper;

import com.wang.project.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.project.vo.RoleDetailVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author Wang
 * @since 2022-01-22
 */
public interface RoleMapper extends BaseMapper<Role> {
}
