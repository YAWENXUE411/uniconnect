package com.ywm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ywm.model.base.PageDTO;
import com.ywm.model.entity.Student;
import com.ywm.model.student.StudentDTO;
import com.ywm.model.student.StudentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 功能描述：学生信息Mapper
 *
 * @Author: ywm
 * @Date: 2023/9/18 14:13
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    /**
     * 条件查询 学生信息分页列表
     * @param pageDTO
     * @return
     */
    List<StudentVO> getStudentPageList(PageDTO<StudentDTO> pageDTO);

    /**
     * 条件查询 学生信息数量
     * @param pageDTO
     * @return
     */
    Long getStudentCount(PageDTO<StudentDTO> pageDTO);

}