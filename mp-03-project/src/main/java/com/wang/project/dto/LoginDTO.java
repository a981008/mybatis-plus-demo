package com.wang.project.dto;

import com.wang.project.entity.Account;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Wang
 * @since 2022/1/22
 */
@Accessors(chain = true)
@Data
public class LoginDTO {
    /**
     * 错误信息
     */
    private String error;

    /**
     * 重定向或跳转路径
     */
    private String path;

    /**
     * 登陆用户信息
     */
    private Account account;
}
