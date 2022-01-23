package com.wang.project.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.project.commonn.Result;
import com.wang.project.commonn.ResultUtils;
import com.wang.project.entity.Customer;
import com.wang.project.service.ICustomerService;
import com.wang.project.vo.CustomerDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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

    /**
     * 列表数据
     */
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

    /**
     * 进入新增页
     */
    @GetMapping("/toAdd")
    public String toAdd() {
        return "customer/customerAdd";
    }

    /**
     * 新增用户
     */
    @PostMapping
    @ResponseBody
    public Result<String> add(@RequestBody Customer customer) {
        return ResultUtils.buildResult(customerService.save(customer));
    }

    /**
     * 进入更新页
     */
    @GetMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model) {
        Customer customer = customerService.getById(id);
        model.addAttribute("customer", customer);
        return "customer/customerUpdate";
    }

    /**
     * 修改用户
     */
    @PutMapping
    @ResponseBody
    public Result<String> update(@RequestBody Customer customer) {
        return ResultUtils.buildResult(customerService.updateById(customer));
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{customerId}")
    @ResponseBody
    public Result<String> delete(@PathVariable Long customerId) {
        return ResultUtils.buildResult(customerService.removeById(customerId));
    }

    /**
     * 进入详情页
     */
    @GetMapping("/toDetail/{id}")
    public String toDetail(@PathVariable Long id, Model model) {
        CustomerDetailVO customerDetailVO = customerService.customerDetailById(id);
        model.addAttribute("customer", customerDetailVO);
        return "customer/customerDetail";
    }
}
