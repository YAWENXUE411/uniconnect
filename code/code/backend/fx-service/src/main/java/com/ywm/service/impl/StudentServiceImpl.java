package com.ywm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ywm.mapper.StudentMapper;
import com.ywm.enums.ListTypeEnum;
import com.ywm.enums.ResultEnum;
import com.ywm.model.base.BaseResult;
import com.ywm.model.base.PageDTO;
import com.ywm.model.base.PageParam;
import com.ywm.model.base.PageVO;
import com.ywm.model.entity.Student;
import com.ywm.model.student.*;
import com.ywm.service.IStudentService;
import com.ywm.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 功能描述：学生信息
 *
 * @Author: ywm
 * @Date: 2023/10/23 20:25
 */
@Slf4j
@Service
public class StudentServiceImpl implements IStudentService {

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private IUserService userService;


    @Override
    public BaseResult<List<String>> selectList(Integer type) {
        ListTypeEnum typeEnum = ListTypeEnum.getEnumByType(type);
        if (typeEnum == null) {
            return BaseResult.validatedException("没有符合条件的数据，请检查参数！");
        }
        QueryWrapper<Student> queryWrapper = new QueryWrapper<Student>()
                .select(typeEnum.getName())
                .groupBy(typeEnum.getName())
                .orderByAsc(typeEnum.getName());
        List<String> students = studentMapper.selectObjs(queryWrapper)
                .stream()
                .filter(Objects::nonNull)
                .map(o -> (String) o)
                .collect(Collectors.toList());
        return BaseResult.success(students);
    }

    @Override
    public BaseResult<PageVO<StudentVO>> studentPageList(PageParam<StudentDTO> pageParam) {
        StudentDTO param = pageParam.getParam();
        if (param != null) {
            if (param.getMaxAge() != null && param.getMinAge() != null && param.getMaxAge() < param.getMinAge()) {
                return BaseResult.validatedException("年龄最小值不能大于最大值！");
            }
            if (param.getMaxGrades() != null && param.getMinGrades() != null && param.getMaxGrades() < param.getMinGrades()) {
                return BaseResult.validatedException("成绩最小值不能大于最大值！");
            }
            if (param.getMaxGpa() != null && param.getMinGpa() != null && param.getMaxGpa() < param.getMinGpa()) {
                return BaseResult.validatedException("gpa最小值不能大于最大值！");
            }
            if (param.getMaxPrice() != null && param.getMinPrice() != null && param.getMaxPrice() < param.getMinPrice()) {
                return BaseResult.validatedException("金额最小值不能大于最大值！");
            }
        }
        PageDTO<StudentDTO> pageDTO = new PageDTO<>(pageParam.getPageNo(), pageParam.getPageSize(), param);
        Long totalCount = studentMapper.getStudentCount(pageDTO);
        if (totalCount == null || totalCount < 1) {
            return BaseResult.success("没有符合条件的数据！", null);
        }
        List<StudentVO> dataList = studentMapper.getStudentPageList(pageDTO);
        PageVO<StudentVO> resData = new PageVO<>(pageParam.getPageNo(), pageParam.getPageSize(), totalCount, dataList);
        return BaseResult.success(resData);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult<String> addStudent(AddStudentParam param) {
        try {
            BaseResult<String> baseResult = userService.judgeUserAuthority(param.getUserId().trim());
            if (ResultEnum.SUCCESS.getCode() != baseResult.getCode()) {
                return baseResult;
            }
            Student student = new Student();
            BeanUtil.copyProperties(param, student);
            student.setCreateTime(DateUtil.date());
            int result = studentMapper.insert(student);
            if (result < 1) {
                return BaseResult.failure("新增学生信息失败！");
            }
            return BaseResult.success("新增学生信息成功！", String.valueOf(student.getId()));
        } catch (Exception e) {
            log.error("新增学生信息异常：{}", e.getMessage(), e);
            return BaseResult.failure(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult<String> editStudent(EditStudentParam param) {
        try {
            BaseResult<String> baseResult = userService.judgeUserAuthority(param.getUserId().trim());
            if (ResultEnum.SUCCESS.getCode() != baseResult.getCode()) {
                return baseResult;
            }
            Student student = new Student();
            BeanUtil.copyProperties(param, student);
            student.setUpdateTime(DateUtil.date());
            int result = studentMapper.updateById(student);
            if (result < 1) {
                return BaseResult.failure("修改学生信息失败！");
            }
            return BaseResult.success("修改学生信息成功！", String.valueOf(student.getId()));
        } catch (Exception e) {
            log.error("修改学生信息异常：{}", e.getMessage(), e);
            return BaseResult.failure(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult<String> deleteStudent(DeleteStudentParam param) {
        BaseResult<String> baseResult = userService.judgeUserAuthority(param.getUserId().trim());
        if (ResultEnum.SUCCESS.getCode() != baseResult.getCode()) {
            return baseResult;
        }
        List<Long> studentIds = Arrays.stream(param.getStudentIds()
                .split(","))
                .map(s -> Long.parseLong(s.trim()))
                .collect(Collectors.toList());
        int result = studentMapper.deleteBatchIds(studentIds);
        if (result < 1) {
            return BaseResult.failure("删除学生信息失败！");
        }
        return BaseResult.success("删除学生信息成功！", null);
    }


}
