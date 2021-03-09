package com.toddding.service.impl;

import com.toddding.common.CodeMsg;
import com.toddding.common.Result;
import com.toddding.domain.entity.SysUser;
import com.toddding.mapper.SysUserMapper;
import com.toddding.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/8 16:17
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public Result queryUser(String username, String password) {
        SysUser sysUser=sysUserMapper.selectUserByUsernameAndPassword(username,password);
        if (sysUser==null){
            return new Result(CodeMsg.USER_USERNAME_PASSWORD_ERROR);
        }
        return new Result(sysUser);
    }
}
