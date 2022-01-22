package com.wang.project.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.wang.project.dto.LoginDTO;
import com.wang.project.entity.Account;
import com.wang.project.mapper.AccountMapper;
import com.wang.project.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号表 服务实现类
 * </p>
 *
 * @author Wang
 * @since 2022-01-22
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Override
    public LoginDTO login(String username, String password) {
        String path = "redirect:/";
        String error = null;
        Account account = this.lambdaQuery().eq(Account::getUsername, username).one();

        if (account == null) {
            error = "用户名不存在";
        } else {
            MD5 md5 = new MD5(account.getSalt().getBytes());
            String s = md5.digestHex(password);
            if (s.equals(account.getPassword())) { // 登陆成功
                path = "login/main";
            } else {
                error = "密码错误";
            }
        }

        return new LoginDTO().setPath(path).setError(error).setAccount(account);
    }
}
