package com.toddding.mapper;

import com.toddding.domain.entity.SysRole;
import com.toddding.domain.form.SysRoleForm;
import com.toddding.domain.query.SysRoleQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * 分页查询角色列表
     * @param query
     * @return
     */
    List<SysRole> selectList(SysRoleQuery query);

    /**
     * 新增角色
     * @param form
     * @return
     */
    int insert(SysRoleForm form);

    /**
     * 修改角色信息
     * @param form
     * @return
     */
    int update(SysRoleForm form);

    /**
     * 根据用户id查询所有对应的角色
     * @param userId
     * @return
     */
    List<SysRole> selectListByUserId(@Param("userId")Integer userId);

    /**
     * 删除用户角色关系
     * @param userId
     * @return
     */
    Integer deleteUserRole(@Param("userId") Integer userId);

    /**
     * 新增用户角色关系
     * @param userId
     * @param roleIds
     * @return
     */
    Integer batchInsertUserRoles(@Param("userId") Integer userId,@Param("roleIds") List<Integer> roleIds);
}