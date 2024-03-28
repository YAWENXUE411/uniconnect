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
 * 功能描述：店铺评论点赞信息
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="CommentLike 店铺评论点赞信息",description="店铺评论点赞信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "\"t_comment_like\"")
public class CommentLike {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="评论id")
    private String commentId;

    @ApiModelProperty(value="用户id")
    private String userId;

    @ApiModelProperty(value="创建时间")
    private Date createTime;

}
