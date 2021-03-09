package com.toddding.service;

import com.toddding.common.Result;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/8 16:16
 */
public interface SysUserService {
    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    Result queryUser(String username, String password);
}
