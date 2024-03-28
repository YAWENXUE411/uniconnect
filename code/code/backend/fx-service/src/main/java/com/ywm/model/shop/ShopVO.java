package com.ywm.model.shop;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ywm.model.base.BaseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述：店铺返回信息
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="ShopVO 店铺信息响应模型",description="店铺信息响应模型")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ShopVO extends BaseData {

    @ApiModelProperty(value="店铺id")
    private String id;

    @ApiModelProperty(value="店铺名称")
    private String name;

    @ApiModelProperty(value="店铺类型id")
    private Integer typeId;

    @ApiModelProperty(value="类型名称")
    private String typeName;

    @ApiModelProperty(value="店铺平均评分")
    private Double score;

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
    private String desc;

    @ApiModelProperty(value="更新时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date updateTime;

}
