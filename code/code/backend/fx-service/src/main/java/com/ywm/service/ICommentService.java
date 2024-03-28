package com.ywm.service;

import com.ywm.model.base.BaseResult;
import com.ywm.model.base.PageParam;
import com.ywm.model.base.PageVO;
import com.ywm.model.comment.AddCommentParam;
import com.ywm.model.comment.CommentDTO;
import com.ywm.model.comment.CommentVO;
import com.ywm.model.comment.DeleteCommentParam;
import com.ywm.model.commentLike.CommentLikeParam;

/**
 * 功能描述：店铺评论信息
 *
 * @Author: ywm
 * @Date: 2023/10/23 20:25
 */
public interface ICommentService {

    /**
     * 分页查询店铺评论列表
     * @param pageParam
     * @return
     */
    BaseResult<PageVO<CommentVO>> commentPageList(PageParam<CommentDTO> pageParam);

    /**
     * 查询店铺评论详情
     * @param commentId
     * @return
     */
    BaseResult<CommentVO> commentDetail(String commentId);

    /**
     * 新增店铺评论
     * @param param
     * @return
     */
    BaseResult<String> addComment(AddCommentParam param);

    /**
     * 删除店铺评论信息
     * @param param
     * @return
     */
    BaseResult<String> deleteComment(DeleteCommentParam param);

    /**
     * 评论点赞
     * @param param
     * @return
     */
    BaseResult<String> commentLike(CommentLikeParam param);

}
