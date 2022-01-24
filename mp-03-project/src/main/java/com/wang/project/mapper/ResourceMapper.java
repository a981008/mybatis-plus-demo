package com.wang.project.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wang.project.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.project.vo.ResourceVO;
import com.wang.project.vo.TreeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author Wang
 * @since 2022-01-22
 */
public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 查询登陆用户的资源
     * @param wrapper 条件构造器
     * @return 资源列表
     */
    List<ResourceVO> listResource(@Param(Constants.WRAPPER) Wrapper<Resource> wrapper);

    List<TreeVO> listResourceByRoleId(@Param(Constants.WRAPPER) Wrapper<Resource> wrapper, @Param("roleId")Long roleId);
}
