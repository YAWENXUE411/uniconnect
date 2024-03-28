package com.ywm.model.user;

import com.ywm.model.base.BaseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 功能描述：用户登录请求参数信息
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="LoginRequest 请求参数模型",description="模型")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginParam extends BaseData {

    @ApiModelProperty(value="登录名")
    @NotBlank(message = "username必填")
    private String username;

    @ApiModelProperty(value="密码")
    @NotBlank(message = "password必填")
    private String password;

}
