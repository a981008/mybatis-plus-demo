package com.wang.project.service;

import com.wang.project.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.project.vo.ResourceVO;
import com.wang.project.vo.TreeVO;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author Wang
 * @since 2022-01-22
 */
public interface IResourceService extends IService<Resource> {
    /**
     * 根据角色 id 查询所持有的资源
     * @return 资源列表
     */
    List<ResourceVO> listResourceByRoleId(Long roleId);

    /**
     * 列表，查询系统资源，给前端渲染
     */
    List<TreeVO> listResource();

    /**
     * 详情或修改时，查询系统资源，给前端渲染
     */
    List<TreeVO> updateResource(Long roleId);

    List<TreeVO> detailResource(Long roleId);
}
