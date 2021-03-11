package com.toddding.mapper;

import com.toddding.domain.entity.SysPermission;
import com.toddding.domain.vo.SysPermissionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    /**
     * 根据用户id查询用户所有的菜单
     * @param userId
     * @param type
     * @return
     */
    List<SysPermissionVo> selectUserPermission(@Param("userId") Integer userId, @Param("type")Integer type);
}