package com.ywm.service;

import com.ywm.model.base.BaseResult;
import com.ywm.model.entity.ShopScore;
import com.ywm.model.shopScore.ShopScoreDTO;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：店铺有效评分
 *
 * @Author: ywm
 * @Date: 2023/10/27 11:08
 */
public interface IShopScoreService {

    /**
     * 获取店铺平均评分
     * @param shopId
     * @return
     */
    Double getScoreByShopId(String shopId);

    /**
     * 获取各店铺评分
     * @param shopIds
     * @return
     */
    Map<String, ShopScore> getScoreGroupByShop(List<String> shopIds);

    /**
     * 保存店铺评分信息
     * @param scoreDTO
     * @return
     */
    BaseResult<String> saveShopScore(ShopScoreDTO scoreDTO);

}
