package com.ywm.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ywm.mapper.UniversityMapper;
import com.ywm.model.base.BaseResult;
import com.ywm.model.base.PageDTO;
import com.ywm.model.base.PageParam;
import com.ywm.model.base.PageVO;
import com.ywm.model.entity.University;
import com.ywm.model.university.UniversityDTO;
import com.ywm.model.university.UniversityVO;
import com.ywm.service.IUniversityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述：学校信息
 *
 * @Author: ywm
 * @Date: 2023/10/23 20:25
 */
@Slf4j
@Service
public class UniversityServiceImpl implements IUniversityService {

    @Resource
    private UniversityMapper universityMapper;


    @Override
    public BaseResult<List<String>> universityList() {
        LambdaQueryWrapper<University> queryWrapper = Wrappers.lambdaQuery(University.class)
                .select(University::getName)
                .groupBy(University::getName, University::getRanking)
                .orderByAsc(University::getRanking);
        List<University> dataList = universityMapper.selectList(queryWrapper);
        if (CollUtil.isEmpty(dataList)) {
            return BaseResult.success(new ArrayList<>());
        }
        List<String> collect = dataList.stream().map(University::getName).collect(Collectors.toList());
        return BaseResult.success(collect);
    }

    @Override
    public BaseResult<PageVO<UniversityVO>> universityPageList(PageParam<UniversityDTO> pageParam) {
        PageDTO<UniversityDTO> pageDTO = new PageDTO<>(pageParam.getPageNo(), pageParam.getPageSize(), pageParam.getParam());
        Long totalCount = universityMapper.getUniversityCount(pageDTO);
        if(totalCount == null || totalCount < 1){
            return BaseResult.success("没有符合条件的数据！", null);
        }
        List<UniversityVO> dataList = universityMapper.getUniversityPageList(pageDTO);
        PageVO<UniversityVO> resData = new PageVO<>(pageParam.getPageNo(), pageParam.getPageSize(), totalCount, dataList);
        return BaseResult.success(resData);
    }

}
