package com.wang.project.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.project.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.project.entity.Customer;
import com.wang.project.vo.AccountDetailVO;
import com.wang.project.vo.CustomerDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 账号表 Mapper 接口
 * </p>
 *
 * @author Wang
 * @since 2022-01-22
 */
public interface AccountMapper extends BaseMapper<Account> {
    /**
     * 分页查询账号
     * @param page 分页
     * @param wrapper 条件构造器
     * @return 分页
     */
    IPage<Account> accountPage(Page<Account> page, @Param(Constants.WRAPPER) Wrapper<Account> wrapper);
}
