package com.ywm.model.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 功能描述：用户登录返回信息
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="LoginVO 用户登录返回信息响应模型",description="用户登录返回信息响应模型")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class LoginVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="用户id")
    private String userId;

    @ApiModelProperty(value="用户名称")
    private String username;

    @ApiModelProperty(value="昵称")
    private String nickname;

}
