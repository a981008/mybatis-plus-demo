package com.wang.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.project.commonn.Result;
import com.wang.project.commonn.ResultUtils;
import com.wang.project.entity.Customer;
import com.wang.project.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author Wang
 * @since 2022-01-22
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    /**
     * 进入列表
     */
    @GetMapping("/toList")
    public String toList() {
        return "customer/customerList";
    }

    @GetMapping("/list")
    @ResponseBody
    public Result<Map<String, Object>> list(String realName, String phone, Long page, Long limit) {
        return ResultUtils.buildPageResult(
                customerService.lambdaQuery()
                        .like(StringUtils.isNotBlank(realName), Customer::getRealName, realName)
                        .like(StringUtils.isNotBlank(phone), Customer::getPhone, phone)
                        .orderByDesc(Customer::getCustomerId).page(new Page<>(page, limit))
        );
    }
}
