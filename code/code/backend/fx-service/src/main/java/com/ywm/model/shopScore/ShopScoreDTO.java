package com.ywm.model.shopScore;

import com.ywm.model.base.BaseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * 功能描述：店铺有效评分
 *
 * @Author: ywm
 * @Date: 2023/10/27 11:11
 */
@ApiModel(value="ShopScoreDTO 店铺评分信息",description="店铺评分信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ShopScoreDTO extends BaseData {

    @ApiModelProperty(value="店铺id")
    private String shopId;

    @ApiModelProperty(value="用户id")
    private String userId;

    @ApiModelProperty(value="评分")
    private Double score;

}
