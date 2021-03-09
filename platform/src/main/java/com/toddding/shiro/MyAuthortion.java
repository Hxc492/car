package com.toddding.shiro;

import com.toddding.common.CodeMsg;
import com.toddding.common.Result;
import com.toddding.domain.entity.SysUser;
import com.toddding.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 认证鉴权器
 * @Author: hxc
 * @Date: 2021/3/7 19:37
 */
public class MyAuthortion extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名和密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        char[] pwd = usernamePasswordToken.getPassword();
        String password=String.valueOf(pwd);
        //根据用户名和密码查询用户
        Result result=sysUserService.queryUser(username,password);
        //根据用户名和密码没有查询到数据，则直接返回null
        if (!result.getCode().equals(CodeMsg.SUCCESS.code)){
            return null;
        }
        SysUser sysUser= (SysUser) result.getData();
        String realname=sysUser.getRealname();
        //TODO 根据 用户ID 查询角色和权限
        ActiveUser activeUser=new ActiveUser();
        activeUser.setSysUser(sysUser);
        activeUser.setRealname(realname);
        activeUser.setRoles(null);
        activeUser.setPermissions(null);
        //shiro自己校验密码
        AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(activeUser,password,realname);
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

}
