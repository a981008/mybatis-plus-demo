package com.wang.project.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.project.dto.LoginDTO;
import com.wang.project.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.project.vo.AccountDetailVO;
import com.wang.project.vo.CustomerDetailVO;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author Wang
 * @since 2022-01-22
 */
public interface IAccountService extends IService<Account> {
    /**
     * 登陆
     * @param username 用户名
     * @param password 密码
     */
    LoginDTO login(String username, String password);

    /**
     * 分页查询账号
     *
     * @param page 分页
     * @param wrapper 条件构造器
     */
    IPage<Account> accountPage(Page<Account> page, Wrapper<Account> wrapper);

    /**
     * 根据 ID 获取账户
     */
    Account getById(Long id);

    /**
     * 根据账户ID查询账户详情
     *
     * @param accountId 账户ID
     * @return 账户详情
     */
    AccountDetailVO accountDetailById(Long accountId);
}
