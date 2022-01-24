package com.wang.project.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.project.entity.Resource;
import com.wang.project.mapper.ResourceMapper;
import com.wang.project.service.IResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.project.vo.ResourceVO;
import com.wang.project.vo.TreeVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author Wang
 * @since 2022-01-22
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {


    @Override
    public List<ResourceVO> listResourceByRoleId(Long roleId) {
        QueryWrapper<Resource> query = Wrappers.<Resource>query()
                .eq("rr.role_id", roleId).isNull("re.parent_id").orderByAsc("re.sort");
        List<ResourceVO> resourceVOS = this.baseMapper.listResource(query);

        resourceVOS.forEach(r -> {
            Long resourceId = r.getResourceId();
            QueryWrapper<Resource> subQuery = Wrappers.<Resource>query()
                    .eq("rr.role_id", roleId)
                    .eq("re.parent_id", resourceId)
                    .orderByAsc("re.sort");

            List<ResourceVO> subResourceVOS = baseMapper.listResource(subQuery);

            if (CollectionUtils.isNotEmpty(subResourceVOS)) {
                r.setSubs(subResourceVOS);
            }
        });

        return resourceVOS;
    }

    @Override
    public List<TreeVO> listResource() {
        LambdaQueryWrapper<Resource> wrapper = Wrappers.<Resource>lambdaQuery()
                .isNull(Resource::getParentId).orderByAsc(Resource::getSort);
        List<Resource> resources = this.list(wrapper);

        return resources.stream().map(r -> {
            TreeVO treeVO = new TreeVO()
                    .setId(r.getResourceId()).setTitle(r.getResourceName());

            LambdaQueryWrapper<Resource> subWrapper = Wrappers.<Resource>lambdaQuery()
                    .eq(Resource::getParentId, r.getResourceId()).orderByAsc(Resource::getSort);

            List<Resource> subResource = this.list(subWrapper);

            if (CollectionUtils.isNotEmpty(subResource)) {
                List<TreeVO> children = subResource.stream().map(
                        sub -> new TreeVO().setId(sub.getResourceId()).setTitle(sub.getResourceName())
                ).collect(Collectors.toList());
                treeVO.setChildren(children);
            }
            return treeVO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<TreeVO> updateResource(Long roleId) {
        QueryWrapper<Resource> query = Wrappers.<Resource>query()
                .isNull("re.parent_id").orderByAsc("re.sort");
        List<TreeVO> treeVOS = baseMapper.listResourceByRoleId(query, roleId);
        treeVOS.forEach(t -> {
            t.setChecked(false);
            Long id = t.getId();
            QueryWrapper<Resource> subQuery = Wrappers.<Resource>query()
                    .eq("re.parent_id", id)
                    .orderByAsc("re.sort");
            List<TreeVO> children = baseMapper.listResourceByRoleId(subQuery, roleId);
            if (CollectionUtils.isNotEmpty(children)) {
                t.setChildren(children);
            }
        });
        return treeVOS;
    }

    @Override
    public List<TreeVO> detailResource(Long roleId) {
        QueryWrapper<Resource> query = Wrappers.<Resource>query().eq("rr.role_id", roleId)
                .isNull("re.parent_id").orderByAsc("re.sort");
        List<TreeVO> treeVOS = baseMapper.listResourceByRoleId(query, roleId);
        treeVOS.forEach(t -> {
            t.setChecked(false);
            Long id = t.getId();
            QueryWrapper<Resource> subQuery = Wrappers.<Resource>query().eq("rr.role_id", roleId)
                    .eq("re.parent_id", id)
                    .orderByAsc("re.sort");
            List<TreeVO> children = baseMapper.listResourceByRoleId(subQuery, roleId);
            if (CollectionUtils.isNotEmpty(children)) {
                t.setChildren(children);
            }
        });
        return treeVOS;
    }
}