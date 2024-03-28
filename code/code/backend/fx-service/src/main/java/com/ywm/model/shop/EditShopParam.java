package com.ywm.model.shop;

import com.ywm.model.base.BaseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 功能描述：修改店铺信息
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="EditShopRequest 请求参数模型",description="模型")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditShopParam extends BaseData {

    @ApiModelProperty(value="店铺id")
    @NotBlank(message = "id必填")
    private String id;

    @ApiModelProperty(value="店铺名称")
    @NotBlank(message = "name必填")
    private String name;

    @ApiModelProperty(value="店铺类型id")
    private Integer typeId;

    @ApiModelProperty(value="用户id")
    @NotBlank(message = "userId必填")
    private String userId;

    @ApiModelProperty(value="经度")
    @NotBlank(message = "lng必填")
    private String lng;

    @ApiModelProperty(value="纬度")
    @NotBlank(message = "lat必填")
    private String lat;

    @ApiModelProperty(value="联系电话")
    private String phone;

    @ApiModelProperty(value="店铺logo")
    private String logo;

    @ApiModelProperty(value="地址")
    private String address;

    @ApiModelProperty(value="描述")
    private String desc;

}
