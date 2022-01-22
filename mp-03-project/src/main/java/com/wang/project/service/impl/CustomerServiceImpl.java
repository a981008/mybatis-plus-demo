package com.wang.project.service.impl;

import com.wang.project.entity.Customer;
import com.wang.project.mapper.CustomerMapper;
import com.wang.project.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
