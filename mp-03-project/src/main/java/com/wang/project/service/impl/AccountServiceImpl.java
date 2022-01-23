package com.wang.project.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.project.dto.LoginDTO;
import com.wang.project.entity.Account;
import com.wang.project.mapper.AccountMapper;
import com.wang.project.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.project.vo.AccountDetailVO;
import org.springframework.beans.BeanUtils;
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


    @Override
    public IPage<Account> accountPage(Page<Account> page, Wrapper<Account> wrapper) {
        return this.baseMapper.accountPage(page, wrapper);
    }

    @Override
    public Account getById(Long id) {
        LambdaQueryWrapper<Account> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Account::getAccountId, id);
        IPage<Account> page = this.baseMapper.accountPage(new Page<>(1,1), wrapper);
        return page.getRecords().get(0);
    }

    @Override
    public AccountDetailVO accountDetailById(Long accountId) {
        AccountDetailVO accountDetailVO = new AccountDetailVO();

        Account account = this.getById(accountId);
        BeanUtils.copyProperties(account, accountDetailVO);

        Long createAccountId = account.getCreateAccountId();
        if (ObjectUtil.isNotNull(createAccountId)) {
            accountDetailVO.setCreateAccountName(this.baseMapper.selectById(createAccountId).getRealName());
        }

        Long modifiedAccountId = account.getModifiedAccountId();
        if (ObjectUtil.isNotNull(modifiedAccountId)) {
            accountDetailVO.setModifiedAccountName(this.baseMapper.selectById(modifiedAccountId).getRealName());
        }

        return accountDetailVO;
    }
}
