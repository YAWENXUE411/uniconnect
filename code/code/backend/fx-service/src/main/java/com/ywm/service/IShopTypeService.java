package com.ywm.service;

import cn.hutool.core.lang.tree.Tree;
import com.ywm.model.base.BaseResult;
import com.ywm.model.entity.ShopType;

import java.util.List;

/**
 * 功能描述：店铺分类信息
 *
 * @Author: ywm
 * @Date: 2022/4/12 15:38
 */
public interface IShopTypeService {

    /**
     * 查询店铺类型树
     * @return
     */
    BaseResult<List<Tree<Integer>>> shopTypeTree();

    /**
     * 查询店铺类型信息
     * @param typeId
     * @return
     */
    ShopType getShopType(Integer typeId);


}
