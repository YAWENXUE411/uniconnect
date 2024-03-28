package com.ywm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ywm.mapper.CommentLikeMapper;
import com.ywm.mapper.CommentMapper;
import com.ywm.mapper.ShopScoreMapper;
import com.ywm.enums.ResultEnum;
import com.ywm.model.base.BaseResult;
import com.ywm.model.base.PageDTO;
import com.ywm.model.base.PageParam;
import com.ywm.model.base.PageVO;
import com.ywm.model.comment.AddCommentParam;
import com.ywm.model.comment.CommentDTO;
import com.ywm.model.comment.CommentVO;
import com.ywm.model.comment.DeleteCommentParam;
import com.ywm.model.commentLike.CommentLikeParam;
import com.ywm.model.entity.Comment;
import com.ywm.model.entity.CommentLike;
import com.ywm.model.entity.ShopScore;
import com.ywm.model.shopScore.ShopScoreDTO;
import com.ywm.service.ICommentLikeService;
import com.ywm.service.ICommentService;
import com.ywm.service.IShopScoreService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能描述：店铺评论信息
 *
 * @Author: ywm
 * @Date: 2023/10/23 20:25
 */
@Slf4j
@Service
public class CommentServiceImpl implements ICommentService {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private CommentLikeMapper commentLikeMapper;
    @Resource
    private ShopScoreMapper shopScoreMapper;
    @Resource
    private IShopScoreService shopScoreService;
    @Resource
    private ICommentLikeService commentLikeService;

    @Override
    public BaseResult<PageVO<CommentVO>> commentPageList(PageParam<CommentDTO> pageParam) {
        PageDTO<CommentDTO> pageDTO = new PageDTO<>(pageParam.getPageNo(), pageParam.getPageSize(), pageParam.getParam());
        Long totalCount = commentMapper.getCommentCount(pageDTO);
        if (totalCount == null || totalCount < 1) {
            return BaseResult.success("没有符合条件的数据！", null);
        }
        List<CommentVO> dataList = commentMapper.getCommentPageList(pageDTO);
        // 查询当前用户是否点赞
        List<String> commentIds = dataList.stream().map(CommentVO::getId).distinct().collect(Collectors.toList());
        Map<String, List<CommentLike>> likeMap = commentLikeService.getCommentLikeList(commentIds);
        for (CommentVO vo : dataList) {
            boolean userLikeFlag = judgeCurrentUserLikeFlag(likeMap, pageParam.getParam().getUserId(), vo.getId().trim());
            vo.setUserLikeFlag(userLikeFlag);
        }
        PageVO<CommentVO> resData = new PageVO<>(pageParam.getPageNo(), pageParam.getPageSize(), totalCount, dataList);
        return BaseResult.success(resData);
    }

    /**
     * 判断当前用户是否点赞当前评论
     *
     * @param likeMap   评论点赞集合
     * @param userId    评论的用户id
     * @param commentId 评论id
     * @return
     */
    private boolean judgeCurrentUserLikeFlag(Map<String, List<CommentLike>> likeMap, String userId, String commentId) {
        if (ObjectUtil.isEmpty(likeMap) || StringUtils.isBlank(userId)) {
            return false;
        }
        List<CommentLike> commentLikes = likeMap.get(commentId);
        if (CollUtil.isEmpty(commentLikes)) {
            return false;
        }
        CommentLike commentLike = commentLikes.stream()
                .filter(s -> s.getUserId().trim().equals(userId.trim()))
                .findAny()
                .orElse(null);
        if (ObjectUtil.isEmpty(commentLike)) {
            return false;
        }
        return true;
    }

    @Override
    public BaseResult<CommentVO> commentDetail(String commentId) {
        LambdaQueryWrapper<Comment> queryWrapper = new QueryWrapper<Comment>().lambda()
                .eq(Comment::getId, commentId.trim());
        Comment comment = commentMapper.selectOne(queryWrapper);
        if (comment == null) {
            return BaseResult.validatedException("id为：" + commentId + " 的评论信息不存在!");
        }
        CommentVO commentVO = new CommentVO();
        BeanUtil.copyProperties(comment, commentVO);
        return BaseResult.success(commentVO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult<String> addComment(AddCommentParam param) {
        try {
            Comment comment = new Comment();
            BeanUtil.copyProperties(param, comment);
            comment.setId(IdUtil.simpleUUID());
            comment.setCreateTime(DateUtil.date());
            int result = commentMapper.insert(comment);
            if (result < 1) {
                return BaseResult.failure("新增评论信息失败！");
            }
            // 保存店铺有效评分
            ShopScoreDTO scoreDTO = new ShopScoreDTO()
                    .setShopId(param.getShopId().trim())
                    .setUserId(param.getUserId().trim())
                    .setScore(param.getScore());
            BaseResult<String> saveResult = shopScoreService.saveShopScore(scoreDTO);
            if (ResultEnum.SUCCESS.getCode() != saveResult.getCode()) {
                return BaseResult.failure("新增评论信息失败！");
            }
            return BaseResult.success("新增评论信息成功！");
        } catch (Exception e) {
            log.error("新增评论信息异常：{}", e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult<String> deleteComment(DeleteCommentParam param) {
        try {
            LambdaQueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<Comment>().lambda()
                    .eq(Comment::getId, param.getCommentId().trim())
                    .eq(Comment::getUserId, param.getUserId().trim());
            Comment comment = commentMapper.selectOne(commentQueryWrapper);
            if (comment == null || !StringUtils.equals(comment.getUserId().trim(), param.getUserId().trim())) {
                return BaseResult.failure("抱歉，您没有权限执行此操作！");
            }
            int result = commentMapper.deleteById(param.getCommentId().trim());
            if (result < 1) {
                return BaseResult.failure("删除评论信息失败！");
            }
            LambdaQueryWrapper<CommentLike> commentLikeQueryWrapper = new QueryWrapper<CommentLike>().lambda()
                    .eq(CommentLike::getCommentId, param.getCommentId().trim())
                    .eq(CommentLike::getUserId, param.getUserId().trim());
            int deleteCommentLike = commentLikeMapper.delete(commentLikeQueryWrapper);
            if (deleteCommentLike < 1) {
                return BaseResult.failure("删除评论信息失败！");
            }
            LambdaQueryWrapper<ShopScore> shopScoreQueryWrapper = new QueryWrapper<ShopScore>().lambda()
                    .eq(ShopScore::getShopId, comment.getShopId().trim())
                    .eq(ShopScore::getUserId, param.getUserId().trim());
            int deleteShopScore = shopScoreMapper.delete(shopScoreQueryWrapper);
            if (deleteShopScore < 1) {
                return BaseResult.failure("删除评论信息失败！");
            }
            return BaseResult.success("删除评论信息成功！", param.getCommentId());
        } catch (Exception e) {
            log.error("删除评论信息异常：{}", e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResult<String> commentLike(CommentLikeParam param) {
        CommentServiceImpl proxy = (CommentServiceImpl) AopContext.currentProxy();
        LambdaQueryWrapper<CommentLike> queryWrapper = new QueryWrapper<CommentLike>().lambda()
                .eq(CommentLike::getCommentId, param.getCommentId().trim())
                .eq(CommentLike::getUserId, param.getUserId().trim());
        CommentLike commentLike = commentLikeMapper.selectOne(queryWrapper);
        if (ObjectUtil.isEmpty(commentLike)) {
            return proxy.cancelLike(param, queryWrapper);
        } else {
            return proxy.addLike(param);
        }
    }

    /**
     * 已点赞，再次点击按钮则取消点赞
     *
     * @param param
     * @param queryWrapper
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<String> cancelLike(CommentLikeParam param, LambdaQueryWrapper<CommentLike> queryWrapper) {
        try {
            int result = commentLikeMapper.delete(queryWrapper);
            if (result < 1) {
                return BaseResult.failure("取消点赞失败！");
            }
            // 点赞数减1
            boolean subLikeNum = commentMapper.subLikeNum(param.getCommentId().trim());
            if (!subLikeNum) {
                return BaseResult.failure("取消点赞失败！");
            }
            return BaseResult.success("取消点赞成功！");
        } catch (Exception e) {
            log.error("取消点赞异常：{}", e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 未点赞，点击按钮则点赞
     *
     * @param param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<String> addLike(CommentLikeParam param) {
        try {
            CommentLike commentLike = new CommentLike();
            BeanUtil.copyProperties(param, commentLike);
            commentLike.setCreateTime(DateUtil.date());
            int result = commentLikeMapper.insert(commentLike);
            if (result < 1) {
                return BaseResult.failure("评论点赞失败！");
            }
            // 点赞数加1
            boolean addLikeNum = commentMapper.addLikeNum(param.getCommentId().trim());
            if (!addLikeNum) {
                return BaseResult.failure("评论点赞失败！");
            }
            return BaseResult.success("评论点赞成功！");
        } catch (Exception e) {
            log.error("评论点赞异常：{}", e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

}
