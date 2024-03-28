package com.ywm.controller;

import com.ywm.model.base.BaseResult;
import com.ywm.model.base.PageParam;
import com.ywm.model.base.PageVO;
import com.ywm.model.university.UniversityDTO;
import com.ywm.model.university.UniversityVO;
import com.ywm.service.IUniversityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述：大学信息
 *
 * @Author: ywm
 * @Date: 2023/10/25 21:46
 */
@Api(value = "大学信息", tags = "大学信息")
@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/university")
public class UniversityController {

    @Resource
    private IUniversityService universityService;

    @ApiOperation(value = "大学列表")
    @GetMapping("/universityList")
    public BaseResult<List<String>> universityList() {
        return universityService.universityList();
    }

    @ApiOperation(value = "分页查询大学列表")
    @PostMapping("/pageList")
    public BaseResult<PageVO<UniversityVO>> pageList(
            @Validated @RequestBody @ApiParam(name="请求参数",value="传入json格式",required=true) PageParam<UniversityDTO> pageParam) {
        return universityService.universityPageList(pageParam);
    }


}
