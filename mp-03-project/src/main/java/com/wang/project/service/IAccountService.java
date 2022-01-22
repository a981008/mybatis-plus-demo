package com.wang.project.service;

import com.wang.project.dto.LoginDTO;
import com.wang.project.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author Wang
 * @since 2022-01-22
 */
public interface IAccountService extends IService<Account> {
    LoginDTO login(String username, String password);
}
