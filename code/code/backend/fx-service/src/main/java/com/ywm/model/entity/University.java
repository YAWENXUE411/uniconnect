package com.ywm.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 功能描述：学校信息实体对象
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="University 学校信息实体对象",description="学校信息实体对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "\"t_university\"")
public class University {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value="学校名称")
    @TableField("\"name\"")
    private String name;

    @ApiModelProperty(value="排名")
    private Integer ranking;

    @ApiModelProperty(value="经度")
    private String lng;

    @ApiModelProperty(value="纬度")
    private String lat;

    @ApiModelProperty(value="联系电话")
    private String phone;

    @ApiModelProperty(value="店铺logo")
    private String logo;

    @ApiModelProperty(value="地址")
    private String address;

    @ApiModelProperty(value="简介")
    @TableField("\"desc\"")
    private String desc;

    @ApiModelProperty(value="创建时间")
    private Date createTime;

    @ApiModelProperty(value="更新时间")
    private Date updateTime;

}
