package com.toddding.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.toddding.common.Result;
import com.toddding.domain.entity.SysRole;
import com.toddding.domain.form.SysRoleForm;
import com.toddding.domain.query.SysRoleQuery;
import com.toddding.mapper.SysRoleMapper;
import com.toddding.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/11 3:17
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public Result queryPage(SysRoleQuery query) {
        Page<Object> page = PageHelper.startPage(query.getPage(), query.getLimit());
        roleMapper.selectList(query);
        return new Result(page.toPageInfo());
    }

    @Override
    public Result add(SysRoleForm form) {
        roleMapper.insert(form);
        return new Result();
    }

    @Override
    public Result update(SysRoleForm form) {
        roleMapper.update(form);
        return new Result();
    }

    @Override
    public Result queryAll() {
        return new Result(roleMapper.selectList(new SysRoleQuery()));
    }

    @Override
    public Result queryUserRoles(Integer userId) {
        List<SysRole> roles = roleMapper.selectListByUserId(userId);
        return new Result(roles);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result insertUserRoles(Integer userId, List<Integer> roleIds) {
        //删除用户当前所有角色关系
        roleMapper.deleteUserRole(userId);
        //新增用户角色关系
        roleMapper.batchInsertUserRoles(userId,roleIds);
        return new Result();
    }
}
