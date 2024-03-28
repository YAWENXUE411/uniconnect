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
@ApiModel(value="DeleteCommentParam 店铺评论信息",description="店铺评论信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DeleteCommentParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="用户id")
    @NotBlank(message = "userId必填")
    private String userId;

    @ApiModelProperty(value="评论id")
    @NotBlank(message = "commentId必填")
    private String commentId;

}
