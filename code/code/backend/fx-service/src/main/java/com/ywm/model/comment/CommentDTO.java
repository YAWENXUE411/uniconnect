package com.ywm.model.comment;

import com.ywm.model.base.BaseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 功能描述：店铺评论信息
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="CommentDTO 店铺评论信息",description="店铺评论信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CommentDTO extends BaseData {

    @ApiModelProperty(value="用户id")
    private String userId;

    @ApiModelProperty(value="店铺id")
    private String shopId;

}
