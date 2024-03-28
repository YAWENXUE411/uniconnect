package com.ywm.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ywm.model.entity.SysUserRole;
import com.ywm.model.user.UserRoleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 功能描述：用户角色信息Mapper
 *
 * @Author: ywm
 * @Date: 2023/9/18 14:13
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 查询用户角色信息
     * @param userId
     * @return
     */
    List<UserRoleDTO> getRoleByUserId(@Param("userId") String userId);
}
