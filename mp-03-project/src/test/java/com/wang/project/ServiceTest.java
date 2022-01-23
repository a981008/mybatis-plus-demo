package com.wang.project;

import com.wang.project.service.ICustomerService;
import com.wang.project.vo.CustomerDetailVO;
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
public class ServiceTest {
    @Autowired
    private ICustomerService customerService;
    @Test
    public void test01() {
        CustomerDetailVO customerDetailVO = customerService.customerDetailById(1L);
        System.out.println(customerDetailVO);
    }
}
