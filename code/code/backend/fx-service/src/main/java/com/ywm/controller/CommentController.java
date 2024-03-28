package com.ywm.controller;

import com.ywm.model.base.BaseResult;
import com.ywm.model.base.PageParam;
import com.ywm.model.base.PageVO;
import com.ywm.model.comment.AddCommentParam;
import com.ywm.model.comment.CommentDTO;
import com.ywm.model.comment.CommentVO;
import com.ywm.model.comment.DeleteCommentParam;
import com.ywm.model.commentLike.CommentLikeParam;
import com.ywm.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能描述：店铺评论信息
 *
 * @Author: ywm
 * @Date: 2022/4/12 15:38
 */
@Api(value = "店铺评论信息", tags = "店铺评论信息")
@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private ICommentService shopCommentService;

    @ApiOperation(value = "分页查询店铺评论列表")
    @PostMapping("/pageList")
    public BaseResult<PageVO<CommentVO>> pageList(
            @Validated @RequestBody @ApiParam(name="请求参数",value="传入json格式",required=true) PageParam<CommentDTO> pageParam) {
        return shopCommentService.commentPageList(pageParam);
    }

    @ApiOperation(value = "查询店铺评论详情")
    @GetMapping("/detail/{id}")
    public BaseResult<CommentVO> commentDetail(
            @ApiParam(name = "id", value = "评论id", required = true) @PathVariable("id") String id) {
        return shopCommentService.commentDetail(id);
    }

    @ApiOperation(value = "新增店铺评论")
    @PostMapping(value = "/add")
    public BaseResult<String> addComment(
            @Validated @RequestBody @ApiParam(name = "添加参数", value = "传入json格式", required = true) AddCommentParam param) {
        return shopCommentService.addComment(param);
    }

    @ApiOperation(value = "删除店铺评论")
    @PostMapping(value = "/delete")
    public BaseResult<String> deleteComment(
            @Validated @RequestBody @ApiParam(name = "添加参数", value = "传入json格式", required = true) DeleteCommentParam param) {
        return shopCommentService.deleteComment(param);
    }

    @ApiOperation(value = "评论点赞/取消评论点赞")
    @PostMapping(value = "/like")
    public BaseResult<String> like(
            @Validated @RequestBody @ApiParam(name = "添加参数", value = "传入json格式", required = true) CommentLikeParam param) {
        return shopCommentService.commentLike(param);
    }

}
