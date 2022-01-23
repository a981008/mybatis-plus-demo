package com.wang.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.project.entity.Customer;
import com.wang.project.mapper.CustomerMapper;
import com.wang.project.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.project.vo.CustomerDetailVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author Wang
 * @since 2022-01-22
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Override
    public CustomerDetailVO customerDetailById(Long customerId) {
        LambdaQueryWrapper<Customer> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Customer::getCustomerId, customerId);
        return this.baseMapper.listDetail(wrapper).get(0);
    }
}
