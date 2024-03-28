package com.ywm.model.shopType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 功能描述：店铺类型返回信息
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="ShopTypeVO 店铺类型信息响应模型",description="店铺类型信息响应模型")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ShopTypeDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="店铺类型id")
    private Integer id;

    @ApiModelProperty(value="类型名称")
    private String name;

    //@ApiModelProperty(value="上级分类id")
    //private String parentId;

    @ApiModelProperty(value="图标")
    private String icon;

}
