package com.ywm.controller;

import com.ywm.model.base.BaseResult;
import com.ywm.model.user.LoginParam;
import com.ywm.model.user.LoginVO;
import com.ywm.model.user.RegisterParam;
import com.ywm.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能描述：系统用户信息
 *
 * @Author: ywm
 * @Date: 2022/4/12 15:38
 */
@Api(value = "系统用户信息", tags = "系统用户信息")
@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/sys")
public class SysUserController {

    @Resource
    private IUserService userService;

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public BaseResult register (
            @Validated @RequestBody @ApiParam(name="请求参数",value="传入json格式",required=true) RegisterParam param){
        return userService.register(param);
    }

    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login")
    public BaseResult<LoginVO> login (
            @Validated @RequestBody @ApiParam(name="请求参数",value="传入json格式",required=true) LoginParam param){
        return userService.login(param);
    }

}
