package com.wang.project.service.impl;

import com.wang.project.entity.Role;
import com.wang.project.mapper.RoleMapper;
import com.wang.project.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Wang
 * @since 2022-01-22
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
