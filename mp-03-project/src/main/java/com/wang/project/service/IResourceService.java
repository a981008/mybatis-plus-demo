package com.wang.project.service;

import com.wang.project.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.project.vo.ResourceVO;

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
}
