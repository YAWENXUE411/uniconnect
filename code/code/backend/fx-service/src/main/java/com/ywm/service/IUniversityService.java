package com.ywm.service;

import com.ywm.model.base.BaseResult;
import com.ywm.model.base.PageParam;
import com.ywm.model.base.PageVO;
import com.ywm.model.university.UniversityDTO;
import com.ywm.model.university.UniversityVO;

import java.util.List;

/**
 * 功能描述：学校信息
 *
 * @Author: ywm
 * @Date: 2023/10/23 20:25
 */
public interface IUniversityService {

    /**
     * 查询大学列表
     * @return
     */
    BaseResult<List<String>> universityList();

    /**
     * 分页查询大学列表
     * @param pageParam
     * @return
     */
    BaseResult<PageVO<UniversityVO>> universityPageList(PageParam<UniversityDTO> pageParam);


}
