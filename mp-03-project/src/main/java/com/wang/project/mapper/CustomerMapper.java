package com.wang.project.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wang.project.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.project.entity.Resource;
import com.wang.project.vo.CustomerDetailVO;
import com.wang.project.vo.ResourceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 客户表 Mapper 接口
 * </p>
 *
 * @author Wang
 * @since 2022-01-22
 */
public interface CustomerMapper extends BaseMapper<Customer> {

}
