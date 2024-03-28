package com.ywm.model.comment;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ywm.model.base.BaseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 功能描述：店铺评论信息
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="CommentVO 店铺评论信息",description="店铺评论信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CommentVO extends BaseData {

    @ApiModelProperty(value="评论id")
    private String id;

    @ApiModelProperty(value="用户id")
    private String userId;

    @ApiModelProperty(value="用户名")
    private String userName;

    @ApiModelProperty(value="用户昵称")
    private String nickname;

    @ApiModelProperty(value="店铺id")
    private String shopId;

    @ApiModelProperty(value="标签")
    private String tags;

    @ApiModelProperty(value="评论内容")
    private String content;

    @ApiModelProperty(value="点赞数")
    private Long likeNum;

    @ApiModelProperty(value="当前用户是否点赞(false：否 true：是)")
    private Boolean userLikeFlag;

    @ApiModelProperty(value="标识(1：用户评论 2：商家回复)")
    private String flag;

    @ApiModelProperty(value="更新时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty(value="评分")
    private Double score;

}
