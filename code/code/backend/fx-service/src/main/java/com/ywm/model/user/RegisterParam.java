package com.ywm.model.user;

import com.ywm.model.base.BaseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 功能描述：用户注册请求参数信息
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="RegisterRequest 请求参数模型",description="模型")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterParam extends BaseData {

    @ApiModelProperty(value="登录名",example = "1234567@qq.com",required = true)
    @NotBlank(message = "username必填")
    @Size(min = 2, max = 20, message = "登录名长度必须在2到20个字符之间")
    private String username;

    @ApiModelProperty(value="密码",example = "",required = true)
    @NotBlank(message = "password必填")
    @Size(min = 6, max = 20, message = "密码长度必须在6到20个字符之间")
    private String password;

    @ApiModelProperty(value="昵称",example = "fx",required = false)
    //@NotBlank(message = "nickname必填")
    //@Size(min = 2, max = 20, message = "昵称长度必须在2到20个字符之间")
    private String nickname;

}
