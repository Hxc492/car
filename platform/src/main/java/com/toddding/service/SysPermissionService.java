package com.toddding.service;

import com.toddding.common.Result;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 16:28
 */
public interface SysPermissionService {
    /**
     * 获取当前用户的左侧菜单
     * @return
     */
    Result currentLeftMenu();
}
