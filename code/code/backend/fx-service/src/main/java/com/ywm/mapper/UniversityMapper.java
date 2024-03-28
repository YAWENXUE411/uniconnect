package com.ywm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ywm.model.base.PageDTO;
import com.ywm.model.entity.University;
import com.ywm.model.university.UniversityDTO;
import com.ywm.model.university.UniversityVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 功能描述：学校信息Mapper
 *
 * @Author: ywm
 * @Date: 2023/9/18 14:13
 */
@Mapper
public interface UniversityMapper extends BaseMapper<University> {

    /**
     *  获取大学信息分页列表
     * @param pageDTO
     * @return
     */
    List<UniversityVO> getUniversityPageList(PageDTO<UniversityDTO> pageDTO);

    /**
     * 获取大学数量
     * @param pageDTO
     * @return
     */
    Long getUniversityCount(PageDTO<UniversityDTO> pageDTO);

}