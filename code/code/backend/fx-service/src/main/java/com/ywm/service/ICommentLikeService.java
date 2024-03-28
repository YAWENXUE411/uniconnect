package com.ywm.service;

import com.ywm.model.entity.CommentLike;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：店铺评论点赞信息
 *
 * @Author: ywm
 * @Date: 2023/10/23 20:25
 */
public interface ICommentLikeService {

    /**
     * 根据评论ids 获取评论点赞信息列表
     * @param commentIds
     * @return
     */
    Map<String, List<CommentLike>> getCommentLikeList(List<String> commentIds);

}
