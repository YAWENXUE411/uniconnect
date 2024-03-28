package com.ywm.service;

import com.ywm.model.base.BaseResult;
import com.ywm.model.base.PageDTO;
import com.ywm.model.base.PageParam;
import com.ywm.model.base.PageVO;
import com.ywm.model.shop.AddShopParam;
import com.ywm.model.shop.EditShopParam;
import com.ywm.model.shop.ShopDTO;
import com.ywm.model.shop.ShopVO;

import java.util.List;

/**
 * 功能描述：店铺信息
 *
 * @Author: ywm
 * @Date: 2022/4/12 15:38
 */
public interface IShopService {

    /**
     * 查询店铺点位列表
     * @return
     */
    BaseResult<List<ShopVO>> shopPoints();

    /**
     * 分页查询店铺列表
     * @param pageParam
     * @return
     */
    BaseResult<PageVO<ShopVO>> shopPageList(PageParam<ShopDTO> pageParam);

    /**
     * 查询店铺详情
     * @param shopId
     * @return
     */
    BaseResult<ShopVO> shopDetail(String shopId);

    /**
     * 新增店铺
     * @param param
     * @return
     */
    BaseResult<String> addShop(AddShopParam param);

    /**
     * 修改店铺信息
     * @param param
     * @return
     */
    BaseResult<String> editShop(EditShopParam param);

}
