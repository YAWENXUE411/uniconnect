package com.ywm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ywm.mapper.SysUserMapper;
import com.ywm.mapper.SysUserRoleMapper;
import com.ywm.enums.ResultEnum;
import com.ywm.enums.RoleIdEnum;
import com.ywm.enums.UserStatusEnum;
import com.ywm.model.base.BaseResult;
import com.ywm.model.entity.SysUser;
import com.ywm.model.entity.SysUserRole;
import com.ywm.model.user.LoginParam;
import com.ywm.model.user.LoginVO;
import com.ywm.model.user.RegisterParam;
import com.ywm.model.user.UserRoleDTO;
import com.ywm.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.ywm.constants.BaseConstant.ADMIN_ROLE_ID;

/**
 * 功能描述：用户信息
 *
 * @Author: ywm
 * @Date: 2022/4/10 18:53
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private SysUserMapper userMapper;
    @Resource
    private SysUserRoleMapper userRoleMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult register(RegisterParam param) {
        try {
            LambdaQueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>().lambda()
                    .eq(SysUser::getUsername, param.getUsername().trim());
            Integer exitResult = userMapper.selectCount(queryWrapper);
            if(exitResult != null && exitResult > 0){
                return BaseResult.failure("用户名 [" + param.getUsername() + "] 已被注册!");
            }
            SysUser user = new SysUser();
            BeanUtil.copyProperties(param, user);
            user.setId(IdUtil.simpleUUID());
            user.setStatus(UserStatusEnum.AUTHORIZED.getStatus());
            user.setCreateTime(new Date());
            int saveUser = userMapper.insert(user);
            //默认授予普通用户权限
            SysUserRole userRole = SysUserRole.builder()
                    .userId(user.getId())
                    .roleId(RoleIdEnum.GENERAL_USER.getType())
                    .build();
            int saveUserRole = userRoleMapper.insert(userRole);
            if(saveUser > 0 && saveUserRole > 0){
                return BaseResult.success(user.getId());
            }
            return BaseResult.failure("用户注册失败！");
        } catch (Exception e) {
            log.error("用户注册异常：{}", e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResult<LoginVO> login(LoginParam param) {
        try {
            LambdaQueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>().lambda()
                    .eq(SysUser::getUsername, param.getUsername().trim())
                    .eq(SysUser::getPassword, param.getPassword().trim());
            SysUser user = userMapper.selectOne(queryWrapper);
            if(user == null){
                return BaseResult.validatedException("用户名或密码错误!");
            }
            if(user.getStatus() == null || UserStatusEnum.UNAUTHORIZED.getStatus().equals(user.getStatus())){
                return BaseResult.failure("当前用户还未获得使用系统权限!");
            }
            LoginVO loginVO = new LoginVO()
                    .setUserId(user.getId())
                    .setUsername(user.getUsername())
                    .setNickname(user.getNickname());
            return BaseResult.success(loginVO);
        } catch (Exception e) {
            log.error("用户登录异常：{}", e.getMessage(), e);
            return BaseResult.failure(ResultEnum.SERVER_EXCEPTION);
        }
    }

    @Override
    public BaseResult<String> judgeUserAuthority(String userId) {
        List<UserRoleDTO> roles = userRoleMapper.getRoleByUserId(userId);
        if (CollUtil.isEmpty(roles)) {
            return BaseResult.failure("抱歉，您没有权限执行此操作！");
        }
        UserRoleDTO roleDTO = roles.stream().filter(s -> ADMIN_ROLE_ID.equals(s.getRoleId())).findAny().orElse(null);
        if (roleDTO == null) {
            return BaseResult.failure("抱歉，您没有权限执行此操作！");
        }
        return BaseResult.success( null);
    }


}
