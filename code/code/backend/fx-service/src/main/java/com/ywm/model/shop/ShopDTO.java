package com.ywm.model.shop;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 功能描述：店铺返回信息
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="ShopVO 店铺信息",description="店铺信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ShopDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="店铺id")
    private String id;

    @ApiModelProperty(value="关键字模糊查询")
    private String keyword;

}
