package com.wang.project;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.project.entity.Account;
import com.wang.project.mapper.AccountMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Wang
 * @since 2022/1/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {
    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void test01() {
        IPage<Account> page = accountMapper.accountPage(new Page<>(1, 10), Wrappers.emptyWrapper());
        page.getRecords().forEach(System.out::println);
    }
}
