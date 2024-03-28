package com.ywm.model.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 功能描述：店铺评论信息
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="AddCommentParam 店铺评论信息",description="店铺评论信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AddCommentParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="用户id")
    @NotBlank(message = "userId必填")
    private String userId;

    @ApiModelProperty(value="店铺id")
    @NotBlank(message = "shopId必填")
    private String shopId;

    @ApiModelProperty(value="标签")
    private String tags;

    @ApiModelProperty(value="评分")
    private Double score;

    @ApiModelProperty(value="评论内容")
    private String content;

}
