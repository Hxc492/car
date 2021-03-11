package com.toddding.service.impl;

import com.toddding.common.Constant;
import com.toddding.common.Result;
import com.toddding.domain.vo.SysPermissionVo;
import com.toddding.mapper.SysPermissionMapper;
import com.toddding.service.SysPermissionService;
import com.toddding.shiro.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 16:28
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    private SysPermissionMapper permissionMapper;

    @Override
    public Result currentLeftMenu() {
        //获取当前用户id
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser= (ActiveUser) subject.getPrincipal();
        Integer userId=activeUser.getSysUser().getId();
        //根据用户id查询用户左侧菜单
        List<SysPermissionVo> permissionVoList=permissionMapper.selectUserPermission(userId, Constant.PERMISSION_TYPE_MENU);
        //将数组转化为父子关系
        Map<Integer,SysPermissionVo> menu=new HashMap<>();
        //遍历查找一级菜单
        for (SysPermissionVo sysPermissionVo : permissionVoList) {
            //获取其菜单的父菜单，若值为0，则说明菜单为一级菜单
            Integer parentId=sysPermissionVo.getParentId();
            if (parentId.equals(Constant.MENU_LV1)){
                //初始化一级菜单及其子菜单容器
                sysPermissionVo.setChildren(new ArrayList<SysPermissionVo>());
                menu.put(sysPermissionVo.getId(),sysPermissionVo);
            }
        }
        //遍历为一级菜单设置二级菜单
        for (SysPermissionVo sysPermissionVo : permissionVoList) {
            //获取菜单的父菜单
            Integer parentId=sysPermissionVo.getParentId();
            //判断一级菜单容器中是否有当前菜单的父菜单
            if (menu.containsKey(parentId)){
                //获取对应的父菜单
                SysPermissionVo vo=menu.get(parentId);
                //将当前菜单放入父菜单的子菜单容器
                vo.getChildren().add(sysPermissionVo);
            }
        }
        //获取map中所有的一级菜单的集合
        Collection<SysPermissionVo> values=menu.values();
        //返回数据
        return new Result(values);
    }
}
