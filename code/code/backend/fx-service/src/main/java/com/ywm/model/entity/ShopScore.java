package com.ywm.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ywm.model.base.BaseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 功能描述：店铺有效评分
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="ShopScore 店铺评分",description="店铺评分")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "\"t_shop_score\"")
public class ShopScore extends BaseData {

    @ApiModelProperty(value="用户id")
    private String userId;

    @ApiModelProperty(value="店铺id")
    private String shopId;

    @ApiModelProperty(value="评分")
    private Double score;

    @ApiModelProperty(value="更新时间")
    private Date updateTime;

}
