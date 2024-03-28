package com.ywm.service;

import com.ywm.model.base.BaseResult;
import com.ywm.model.user.LoginParam;
import com.ywm.model.user.RegisterParam;
import com.ywm.model.user.LoginVO;

/**
 * 功能描述：用户信息
 *
 * @Author: ywm
 * @Date: 2022/4/12 15:38
 */
public interface IUserService {

    /**
     * 用户注册
     * @param registerRequest
     * @return
     */
    BaseResult register(RegisterParam registerRequest);

    /**
     * 用户登录
     * @param loginRequest
     * @return
     */
    BaseResult<LoginVO> login(LoginParam loginRequest);

    /**
     * 判断用户数据操作权限
     * @param userId
     * @return
     */
    BaseResult<String> judgeUserAuthority(String userId);

}
