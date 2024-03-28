package com.ywm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ywm.model.base.PageDTO;
import com.ywm.model.entity.Shop;
import com.ywm.model.shop.ShopDTO;
import com.ywm.model.shop.ShopVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 功能描述：店铺信息Mapper
 *
 * @Author: ywm
 * @Date: 2023/9/18 14:13
 */
@Mapper
public interface ShopMapper extends BaseMapper<Shop> {

    /**
     * 条件查询 店铺信息分页列表
     * @param pageDTO
     * @return
     */
    List<ShopVO> getShopPageList(PageDTO<ShopDTO> pageDTO);

    /**
     * 条件查询 店铺信息数量
     * @param pageDTO
     * @return
     */
    Long getShopCount(PageDTO<ShopDTO> pageDTO);

}