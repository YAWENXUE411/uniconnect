package com.ywm.service;

import com.ywm.model.base.BaseResult;
import com.ywm.model.base.PageParam;
import com.ywm.model.base.PageVO;
import com.ywm.model.student.*;

import java.util.List;

/**
 * 功能描述：学生信息
 *
 * @Author: ywm
 * @Date: 2023/10/23 20:25
 */
public interface IStudentService {

    /**
     * 下拉框类型数据列表
     * @return
     */
    BaseResult<List<String>> selectList(Integer type);

    /**
     * 分页查询学生列表
     * @param pageParam
     * @return
     */
    BaseResult<PageVO<StudentVO>> studentPageList(PageParam<StudentDTO> pageParam);

    /**
     * 新增学生信息
     * @param param
     * @return
     */
    BaseResult<String> addStudent(AddStudentParam param);

    /**
     * 修改学生信息
     * @param param
     * @return
     */
    BaseResult<String> editStudent(EditStudentParam param);

    /**
     * 删除学生信息
     * @param param
     * @return
     */
    BaseResult<String> deleteStudent(DeleteStudentParam param);

}
