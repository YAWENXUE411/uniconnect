package com.ywm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ywm.model.base.PageDTO;
import com.ywm.model.entity.Comment;
import com.ywm.model.comment.CommentDTO;
import com.ywm.model.comment.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 功能描述：店铺评论信息Mapper
 *
 * @Author: ywm
 * @Date: 2023/9/18 14:13
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 条件查询 店铺评论信息分页列表
     *
     * @param pageDTO
     * @return
     */
    List<CommentVO> getCommentPageList(PageDTO<CommentDTO> pageDTO);

    /**
     * 条件查询 店铺评论信息数量
     *
     * @param pageDTO
     * @return
     */
    Long getCommentCount(PageDTO<CommentDTO> pageDTO);

    /**
     * 评论点赞减1
     * @param commentId
     * @return
     */
    boolean subLikeNum(@Param("commentId") String commentId);

    /**
     * 评论点赞加1
     * @param commentId
     * @return
     */
    boolean addLikeNum(@Param("commentId") String commentId);
}