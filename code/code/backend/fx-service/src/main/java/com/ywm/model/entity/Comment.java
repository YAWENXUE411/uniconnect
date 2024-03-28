package com.ywm.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 功能描述：店铺评论信息
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="Comment 店铺评论信息",description="店铺评论信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "\"t_comment\"")
public class Comment {

    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty(value="评论id")
    private String id;

    @ApiModelProperty(value="用户id")
    private String userId;

    @ApiModelProperty(value="店铺id")
    private String shopId;

    @ApiModelProperty(value="标签")
    private String tags;

    @ApiModelProperty(value="评论内容")
    private String content;

    @ApiModelProperty(value="点赞数")
    private Long likeNum;

    @ApiModelProperty(value="标识(1：用户评论 2：商家回复)")
    private String flag;

    @ApiModelProperty(value="创建时间")
    private Date createTime;

    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    @ApiModelProperty(value="评分")
    private Double score;

}
