package com.toddding.controller;

import com.toddding.common.Result;
import com.toddding.service.SysPermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 16:29
 */
@RestController
@RequestMapping("permission")
public class SysPermissionController {
    @Autowired
    private SysPermissionService permissionService;

    @ApiOperation(value = "获取当前用户的左侧菜单")
    @RequestMapping("leftMenu")
    public Result getUserMenu(){
        return permissionService.currentLeftMenu();
    }
}
