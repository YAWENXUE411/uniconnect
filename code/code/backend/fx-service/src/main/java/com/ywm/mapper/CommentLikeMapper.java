package com.ywm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ywm.model.entity.CommentLike;
import org.apache.ibatis.annotations.Mapper;

/**
 * 功能描述：店铺评论点赞信息Mapper
 *
 * @Author: ywm
 * @Date: 2023/9/18 14:13
 */
@Mapper
public interface CommentLikeMapper extends BaseMapper<CommentLike> {


}