package com.ywm.controller;

import com.ywm.model.base.BaseResult;
import com.ywm.model.base.PageParam;
import com.ywm.model.base.PageVO;
import com.ywm.model.student.*;
import com.ywm.service.IStudentService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述：学生信息
 *
 * @Author: ywm
 * @Date: 2023/10/25 21:46
 */
@Api(value = "学生信息", tags = "学生信息")
@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private IStudentService studentService;


    @ApiOperation(value = "下拉框类型数据列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型(1-学院 2-专业 3-学位 4-研究生机构)", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("/selectList")
    public BaseResult<List<String>> selectList(@RequestParam(value = "type", defaultValue = "1") Integer type) {
        return studentService.selectList(type);
    }


    @ApiOperation(value = "分页查询学生列表")
    @PostMapping("/pageList")
    public BaseResult<PageVO<StudentVO>> pageList(
            @Validated @RequestBody @ApiParam(name="请求参数",value="传入json格式",required=true) PageParam<StudentDTO> pageParam) {
        return studentService.studentPageList(pageParam);
    }

    @ApiOperation(value = "新增学生信息")
    @PostMapping(value = "/add")
    public BaseResult<String> add(
            @Validated @RequestBody @ApiParam(name = "添加参数", value = "传入json格式", required = true) AddStudentParam param) {
        return studentService.addStudent(param);
    }

    @ApiOperation(value = "修改学生信息")
    @PutMapping(value = "/edit")
    public BaseResult<String> edit(
            @Validated @RequestBody @ApiParam(name = "添加参数", value = "传入json格式", required = true) EditStudentParam param) {
        return studentService.editStudent(param);
    }

    @ApiOperation(value = "批量删除学生信息")
    @PostMapping(value = "/delete")
    public BaseResult<String> delete(
            @Validated @RequestBody @ApiParam(name = "添加参数", value = "传入json格式", required = true) DeleteStudentParam param) {
        return studentService.deleteStudent(param);
    }

}
