package com.wang.project.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.project.entity.Account;
import com.wang.project.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.project.vo.CustomerDetailVO;
import com.wang.project.vo.RoleDetailVO;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Wang
 * @since 2022-01-22
 */
public interface IRoleService extends IService<Role> {
    /**
     * 新增角色以及具有的资源
     */
    Boolean saveRole(Role role);

    /**
     * 修改角色以及具有的资源
     */
    Boolean updateRole(Role role);

    /**
     * 根据角色ID查询角色详情
     *
     * @param roleId 用户ID
     * @return 角色详情
     */
    RoleDetailVO roleDetailById(Long roleId);
}
