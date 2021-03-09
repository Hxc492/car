package com.toddding.controller;

import com.toddding.common.CodeMsg;
import com.toddding.common.Constant;
import com.toddding.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/8 17:01
 */
@RestController
@RequestMapping("sysuser")
@Api(tags = "用户接口")
public class SysUserController {

    @ApiOperation(value = "登录")
    @RequestMapping("login.do")
    public Object login(@RequestParam String loginName,@RequestParam String loginPassword) {
        // 对密码加密
        Md5Hash md5Hash = new Md5Hash(loginPassword, Constant.MD5_SALT, 2);
        System.out.println(md5Hash.toString());
        // 使用登录名和密码换取token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginName, md5Hash.toString());
        // 获取认证主体
        Subject subject = SecurityUtils.getSubject();
        // 进行认证
        subject.login(usernamePasswordToken);
        return new Result();
    }

    @ApiOperation(value = "退出登录")
    @RequestMapping("logout.do")
    public Object logout() {
        // 获取认证主体
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            subject.logout();
        }
        return new Result();
    }
}
