package com.toddding.controller;

import com.toddding.common.Result;
import com.toddding.common.validator.ValidatorUtil;
import com.toddding.domain.form.SysUserForm;
import com.toddding.domain.query.SysUserQuery;
import com.toddding.service.SysUserService;
import com.toddding.utils.Md5HashUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "登录")
    @RequestMapping("login.do")
    public Result login(@RequestParam String loginName,@RequestParam String loginPassword) {
        // 对密码加密
        String passwordMd5= Md5HashUtil.encrypt(loginPassword);
        System.out.println(passwordMd5);
        // 使用登录名和密码换取token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginName, passwordMd5);
        // 获取认证主体
        Subject subject = SecurityUtils.getSubject();
        // 进行认证
        subject.login(usernamePasswordToken);
        return new Result();
    }

    @ApiOperation(value = "退出登录")
    @RequestMapping("logout.do")
    public Result logout() {
        // 获取认证主体
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            subject.logout();
        }
        //跳转到登录页面
        return new Result();
    }

    @ApiOperation(value = "用户列表分页查询")
    @RequestMapping("page.do")
    public Result page(SysUserQuery query) {
        return sysUserService.queryPage(query);
    }

    @ApiOperation(value = "新增用户")
    @RequestMapping("add.do")
    public Result add(SysUserForm form) {
        //进行数据格式校验
        ValidatorUtil.validator(form);
        return sysUserService.add(form);
    }

    @ApiOperation(value = "重置密码")
    @RequestMapping("reset.do")
    public Result reset(Integer id) {
        return sysUserService.resetPassword(id);
    }



    //TODO 删除用户 物理删除
}
