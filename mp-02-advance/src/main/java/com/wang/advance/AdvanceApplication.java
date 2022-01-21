package com.wang.advance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Wang
 * @since 2022/1/21
 */
@SpringBootApplication
@MapperScan("com.wang.advance.dao")
public class AdvanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvanceApplication.class, args);
    }

}
