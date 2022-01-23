package com.wang.project.controller;


import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.project.commonn.Result;
import com.wang.project.commonn.ResultUtils;
import com.wang.project.entity.Account;
import com.wang.project.entity.Role;
import com.wang.project.query.AccountPageQuery;
import com.wang.project.service.IAccountService;
import com.wang.project.service.IRoleService;
import com.wang.project.vo.AccountDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 账号表 前端控制器
 * </p>
 *
 * @author Wang
 * @since 2022-01-22
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private IRoleService roleService;

    /**
     * 进入列表
     */
    @GetMapping("/toList")
    public String toList() {
        return "account/accountList";
    }

    /**
     * 查询账户列表
     */
    @GetMapping("/list")
    @ResponseBody
    public Result<Map<String, Object>> list(AccountPageQuery query) {
        QueryWrapper<Account> wrapper = Wrappers.query();

        String realName = query.getRealName();
        String email = query.getEmail();
        wrapper.like(StringUtils.isNotBlank(realName), "a.real_name", realName)
                .like(StringUtils.isNotBlank(email), "a.email", email)
                .eq("a.deleted", 0);

        String range = query.getCreateTimeRange();
        if (StringUtils.isNotBlank(range)) {
            String[] time = range.split(" - ");
            wrapper.between("a.create_time", time[0], time[1]);
        }

        return ResultUtils.buildPageResult(accountService.accountPage(new Page<>(query.getPage(), query.getLimit()), wrapper));
    }

    /**
     * 删除账户
     */
    @DeleteMapping("/{accountId}")
    @ResponseBody
    public Result<String> delete(@PathVariable Long accountId, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account.getAccountId().equals(accountId)) {
            return Result.failed("不能删除当前登陆账户");
        }
        return ResultUtils.buildResult(accountService.removeById(accountId));
    }

    /**
     * 进入详情页
     */
    @GetMapping("/toDetail/{id}")
    public String toDetail(@PathVariable Long id, Model model) {
        AccountDetailVO accountDetailVO = this.accountService.accountDetailById(id);
        model.addAttribute("account", accountDetailVO);
        return "account/accountDetail";
    }

    /**
     * 进入新增页
     */
    @GetMapping("/toAdd")
    public String toAdd(Model model) {
        List<Role> roles = roleService.list(Wrappers.<Role>lambdaQuery().orderByAsc(Role::getRoleId));
        model.addAttribute("roles", roles);
        return "account/accountAdd";
    }

    /**
     * 密码加密且设置盐
     *
     * @param account 账户
     */
    private void setPasswordAndSalt(Account account) {
        String password = account.getPassword();

        String salt = UUID.fastUUID().toString().replaceAll("-", "");
        MD5 md5 = new MD5(salt.getBytes());
        password = md5.digestHex(password.getBytes());

        account.setPassword(password).setSalt(salt);
    }

    /**
     * 新增账户
     */
    @PostMapping
    @ResponseBody
    public Result<String> add(@RequestBody Account account) {
        setPasswordAndSalt(account);
        return ResultUtils.buildResult(this.accountService.save(account));
    }

    /**
     * 进入更新页
     */
    @GetMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model) {
        Account account = this.accountService.getById(id);
        model.addAttribute("account", account);

        List<Role> roles = roleService.list(Wrappers.<Role>lambdaQuery().orderByAsc(Role::getRoleId));
        model.addAttribute("roles", roles);

        return "account/accountUpdate";
    }

    /**
     * 修改账户
     */
    @PutMapping
    @ResponseBody
    public Result<String> update(@RequestBody Account account) {
        if (StringUtils.isNotBlank(account.getPassword())) { // 提交的密码不为空才修改
            setPasswordAndSalt(account);
        } else {
            account.setPassword(null);
        }
        return ResultUtils.buildResult(this.accountService.updateById(account));
    }

    /**
     * 重名验证
     */
    @GetMapping({"/{username}", "/{username}/{accountId}"})
    @ResponseBody
    public Result<Long> checkUsername(@PathVariable String username, @PathVariable(required = false) Long accountId) {
        Long cnt = accountService.lambdaQuery()
                .eq(Account::getUsername, username)
                .ne(accountId != null, Account::getAccountId, accountId)
                .count();
        return Result.success(cnt);
    }
}
