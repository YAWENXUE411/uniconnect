package com.ywm.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ywm.mapper.CommentLikeMapper;
import com.ywm.model.entity.CommentLike;
import com.ywm.service.ICommentLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能描述：店铺评论点赞信息
 *
 * @Author: ywm
 * @Date: 2023/10/23 20:25
 */
@Slf4j
@Service
public class CommentLikeServiceImpl implements ICommentLikeService {

    @Resource
    private CommentLikeMapper commentLikeMapper;


    @Override
    public Map<String, List<CommentLike>> getCommentLikeList(List<String> commentIds) {
        QueryWrapper<CommentLike> queryWrapper = new QueryWrapper<CommentLike>()
                .select("comment_id", "user_id")
                .in("comment_id", commentIds);
        List<CommentLike> list = commentLikeMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            return list.stream().collect(Collectors.groupingBy(CommentLike::getCommentId));
        }
        return null;
    }

}
