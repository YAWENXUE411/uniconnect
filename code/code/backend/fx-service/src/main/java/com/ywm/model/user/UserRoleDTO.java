package com.ywm.model.user;

import com.ywm.model.base.BaseData;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述：用户角色信息
 *
 * @Author: ywm
 * @Date: 2023/10/26 10:46
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDTO extends BaseData {

    @ApiModelProperty(value="用户id")
    private String userId;

    @ApiModelProperty(value="用户名称")
    private String username;

    @ApiModelProperty(value="角色id")
    private Integer roleId;

    @ApiModelProperty(value="角色名")
    private String roleName;

}
