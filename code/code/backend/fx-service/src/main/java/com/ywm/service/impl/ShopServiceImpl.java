package com.ywm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ywm.mapper.ShopMapper;
import com.ywm.enums.ResultEnum;
import com.ywm.enums.ShopStatusEnum;
import com.ywm.model.base.BaseResult;
import com.ywm.model.base.PageDTO;
import com.ywm.model.base.PageParam;
import com.ywm.model.base.PageVO;
import com.ywm.model.entity.Shop;
import com.ywm.model.entity.ShopScore;
import com.ywm.model.entity.ShopType;
import com.ywm.model.shop.AddShopParam;
import com.ywm.model.shop.EditShopParam;
import com.ywm.model.shop.ShopDTO;
import com.ywm.model.shop.ShopVO;
import com.ywm.service.IShopScoreService;
import com.ywm.service.IShopService;
import com.ywm.service.IShopTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能描述：店铺信息
 *
 * @Author: ywm
 * @Date: 2022/4/10 18:53
 */
@Slf4j
@Service
public class ShopServiceImpl implements IShopService {

    @Resource
    private ShopMapper shopMapper;
    @Resource
    private IShopTypeService shopTypeService;
    @Resource
    private IShopScoreService shopScoreService;

    @Override
    public BaseResult<List<ShopVO>> shopPoints() {
        List<ShopVO> resultList = new ArrayList<>();
        LambdaQueryWrapper<Shop> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Shop::getId, Shop::getName, Shop::getLng, Shop::getLat);
        List<Shop> shops = shopMapper.selectList(queryWrapper);
        if (CollUtil.isEmpty(shops)) {
            return BaseResult.success(resultList);
        }
        for (Shop shop : shops) {
            ShopVO vo = new ShopVO();
            BeanUtil.copyProperties(shop, vo);
            resultList.add(vo);
        }
        return BaseResult.success(resultList);
    }

    @Override
    public BaseResult<PageVO<ShopVO>> shopPageList(PageParam<ShopDTO> pageParam) {
        try {
            PageDTO<ShopDTO> pageDTO = new PageDTO<>(pageParam.getPageNo(), pageParam.getPageSize(), pageParam.getParam());
            Long totalCount = shopMapper.getShopCount(pageDTO);
            if (totalCount == null || totalCount < 1) {
                return BaseResult.success("没有符合条件的数据！", null);
            }
            List<ShopVO> dataList = shopMapper.getShopPageList(pageDTO);
            // 查询店铺评分
            List<String> shopIds = dataList.stream().map(ShopVO::getId).distinct().collect(Collectors.toList());
            if (CollUtil.isNotEmpty(shopIds)) {
                Map<String, ShopScore> scoreMap = shopScoreService.getScoreGroupByShop(shopIds);
                for (ShopVO vo : dataList) {
                    vo.setScore(getShopScore(vo.getId(), scoreMap));
                }
            }
            PageVO<ShopVO> resData = new PageVO<>(pageParam.getPageNo(), pageParam.getPageSize(), totalCount, dataList);
            return BaseResult.success(resData);
        } catch (Exception e) {
            log.error("分页查询店铺列表异常：{}", e.getMessage(), e);
            return BaseResult.failure(ResultEnum.SERVER_EXCEPTION);
        }
    }

    /**
     * 获取店铺评分
     *
     * @param shopId
     * @param scoreMap
     * @return
     */
    private Double getShopScore(String shopId, Map<String, ShopScore> scoreMap) {
        if (ObjectUtil.isEmpty(scoreMap)) {
            return 0d;
        }
        ShopScore score = scoreMap.get(shopId);
        if (score != null) {
            return NumberUtil.round(score.getScore(), 1).doubleValue();
        }
        return 0d;
    }

    @Override
    public BaseResult<ShopVO> shopDetail(String shopId) {
        try {
            LambdaQueryWrapper<Shop> queryWrapper = new QueryWrapper<Shop>().lambda().eq(Shop::getId, shopId.trim());
            Shop shop = shopMapper.selectOne(queryWrapper);
            if (shop == null) {
                return BaseResult.validatedException("id为：" + shopId + " 的店铺信息不存在!");
            }
            ShopVO shopVO = new ShopVO();
            BeanUtil.copyProperties(shop, shopVO);
            ShopType shopType = shopTypeService.getShopType(shop.getTypeId());
            if (shopType != null) {
                shopVO.setTypeId(shopType.getId());
                shopVO.setTypeName(shopType.getName());
            }
            Double score = shopScoreService.getScoreByShopId(shopId);
            shopVO.setScore(NumberUtil.round(score, 1).doubleValue());
            return BaseResult.success(shopVO);
        } catch (Exception e) {
            log.error("查询店铺详情异常：{}", e.getMessage(), e);
            return BaseResult.failure(ResultEnum.SERVER_EXCEPTION);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult<String> addShop(AddShopParam param) {
        try {
            Shop shop = new Shop();
            BeanUtil.copyProperties(param, shop);
            shop.setId(IdUtil.simpleUUID());
            shop.setStatus(ShopStatusEnum.PASSED.getStatus());
            shop.setCreateUserId(param.getUserId());
            shop.setCreateTime(DateUtil.date());
            int result = shopMapper.insert(shop);
            if (result < 1) {
                return BaseResult.failure("新增店铺信息失败！");
            }
            return BaseResult.success("新增店铺信息成功！", shop.getId());
        } catch (Exception e) {
            log.error("新增店铺信息异常：{}", e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult<String> editShop(EditShopParam param) {
        try {
            Shop shop = shopMapper.selectById(param.getId().trim());
            if (shop == null) {
                return BaseResult.validatedException("id为：" + param.getId() + " 的店铺信息不存在!");
            }
            if (!StringUtils.equals(param.getUserId().trim(), shop.getCreateUserId().trim())) {
                return BaseResult.failure("抱歉，您无权修改当前店铺信息！");
            }
            BeanUtil.copyProperties(param, shop);
            shop.setUpdateTime(DateUtil.date());
            int result = shopMapper.updateById(shop);
            if (result < 1) {
                return BaseResult.failure("修改店铺信息失败！");
            }
            return BaseResult.success("修改店铺信息成功！", param.getId());
        } catch (Exception e) {
            log.error("修改店铺信息异常：{}", e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

}
