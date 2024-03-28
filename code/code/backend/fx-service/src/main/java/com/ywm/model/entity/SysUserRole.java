package com.ywm.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ywm.model.base.BaseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 功能描述：用户角色实体对象
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="SysUserRole 用户角色实体",description="用户角色实体")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "\"t_sys_user_role\"")
public class SysUserRole extends BaseData {

  @ApiModelProperty(value="id")
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  @ApiModelProperty(value="用户id")
  private String userId;

  @ApiModelProperty(value="角色编码")
  private Integer roleId;

  @ApiModelProperty(value="修改时间")
  private Date updateTime;


}
