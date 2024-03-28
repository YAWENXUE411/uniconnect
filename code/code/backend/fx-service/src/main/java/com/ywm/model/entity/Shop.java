package com.ywm.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 功能描述：店铺信息实体对象
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="Shop 店铺信息实体对象",description="店铺信息实体对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "\"t_shop\"")
public class Shop {

    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty(value="店铺id")
    private String id;

    @ApiModelProperty(value="店铺名称")
    @TableField("\"name\"")
    private String name;

    @ApiModelProperty(value="店铺类型id")
    private Integer typeId;

    @ApiModelProperty(value="创建人id")
    private String createUserId;

    @ApiModelProperty(value="状态(0:未审核 1:审核中 2:审核通过 -1:已注销 -2:审核不通过 )")
    private Integer status;

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

    @ApiModelProperty(value="描述")
    @TableField("\"desc\"")
    private String desc;

    @ApiModelProperty(value="创建时间")
    private Date createTime;

    @ApiModelProperty(value="更新时间")
    private Date updateTime;

}
