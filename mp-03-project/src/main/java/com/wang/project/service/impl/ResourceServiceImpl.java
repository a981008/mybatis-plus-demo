package com.wang.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.project.entity.Resource;
import com.wang.project.mapper.ResourceMapper;
import com.wang.project.service.IResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.project.vo.ResourceVO;
import org.springframework.stereotype.Service;

import java.util.List;

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
        QueryWrapper<Resource> query = Wrappers.query();
        query.eq("rr.role_id", roleId).isNull("re.parent_id");
        List<ResourceVO> resourceVOS = this.baseMapper.listResource(query);

        resourceVOS.forEach(r -> {
            Long resourceId = r.getResourceId();
            QueryWrapper<Resource> subQuery = Wrappers.query();

            subQuery.eq("rr.role_id", roleId)
                    .eq("re.parent_id", resourceId);

            List<ResourceVO> subResourceVOS = baseMapper.listResource(subQuery);

            if (CollectionUtils.isNotEmpty(subResourceVOS)) {
                r.setSubs(subResourceVOS);
            }
        });

        return resourceVOS;
    }
}
