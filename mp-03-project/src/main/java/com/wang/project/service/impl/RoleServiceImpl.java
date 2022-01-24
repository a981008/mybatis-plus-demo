package com.wang.project.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.project.entity.Account;
import com.wang.project.entity.Role;
import com.wang.project.entity.RoleResource;
import com.wang.project.mapper.AccountMapper;
import com.wang.project.mapper.RoleMapper;
import com.wang.project.mapper.RoleResourceMapper;
import com.wang.project.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.project.vo.RoleDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private AccountMapper accountMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveRole(Role role) {
        save(role);
        addRoleResourceRelation(role);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateRole(Role role) {
        updateById(role);
        // 先删除所有关系，再添加关系
        roleResourceMapper.delete(Wrappers.<RoleResource>lambdaQuery()
                .eq(RoleResource::getRoleId, role.getRoleId()));
        addRoleResourceRelation(role);
        return true;
    }

    @Override
    public RoleDetailVO roleDetailById(Long roleId) {
        Role role = getById(roleId);
        Long modifiedAccountId = role.getModifiedAccountId();
        Long createAccountId = role.getCreateAccountId();
        RoleDetailVO roleDetailVO = new RoleDetailVO();
        BeanUtils.copyProperties(role, roleDetailVO);

        if (ObjectUtil.isNotNull(createAccountId)) {
            String createName = accountMapper.selectById(createAccountId).getRealName();
            roleDetailVO.setCreateAccountName(createName);
        }

        if (ObjectUtil.isNotNull(modifiedAccountId)) {
            String modifiedName = accountMapper.selectById(modifiedAccountId).getRealName();
            roleDetailVO.setCreateAccountName(modifiedName);
        }
        return roleDetailVO;
    }

    /**
     * 增加角色和资源的关系
     */
    private void addRoleResourceRelation(Role role) {
        Long roleId = role.getRoleId();
        List<Long> resourceIds = role.getResourceIds();
        if (CollectionUtils.isNotEmpty(resourceIds)) {
            for (Long resourceId : resourceIds) {
                roleResourceMapper.insert(
                        new RoleResource().setRoleId(roleId).setResourceId(resourceId)
                );
            }
        }
    }
}
