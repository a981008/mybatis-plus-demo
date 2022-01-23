package com.wang.project.service;

import com.wang.project.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.project.vo.CustomerDetailVO;

/**
 * <p>
 * 客户表 服务类
 * </p>
 *
 * @author Wang
 * @since 2022-01-22
 */
public interface ICustomerService extends IService<Customer> {
    /**
     * 根据用户ID查询用户详情
     *
     * @param customerId 用户ID
     * @return 用户详情
     */
    CustomerDetailVO customerDetailById(Long customerId);
}
