package com.wang.project.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.project.entity.Customer;
import com.wang.project.mapper.AccountMapper;
import com.wang.project.mapper.CustomerMapper;
import com.wang.project.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.project.vo.CustomerDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public CustomerDetailVO customerDetailById(Long customerId) {
        Customer customer = this.baseMapper.selectById(customerId);

        CustomerDetailVO customerDetailVO = new CustomerDetailVO();
        BeanUtils.copyProperties(customer, customerDetailVO);

        Long createAccountId = customer.getCreateAccountId();
        if (ObjectUtil.isNotNull(createAccountId)) {
            customerDetailVO.setCreateAccountName(accountMapper.selectById(createAccountId).getRealName());
        }

        Long modifiedAccountId = customer.getModifiedAccountId();
        if (ObjectUtil.isNotNull(modifiedAccountId)) {
            customerDetailVO.setModifiedAccountName(accountMapper.selectById(modifiedAccountId).getRealName());
        }

        return customerDetailVO;
    }
}
