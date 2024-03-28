package com.ywm.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ywm.mapper.ShopScoreMapper;
import com.ywm.model.base.BaseResult;
import com.ywm.model.entity.ShopScore;
import com.ywm.model.shopScore.ShopScoreDTO;
import com.ywm.service.IShopScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 功能描述：店铺有效评分
 *
 * @Author: ywm
 * @Date: 2023/10/27 11:08
 */
@Slf4j
@Service
public class ShopScoreServiceImpl implements IShopScoreService {

    @Resource
    private ShopScoreMapper shopScoreMapper;

    @Override
    public Double getScoreByShopId(String shopId) {
        QueryWrapper<ShopScore> queryWrapper = new QueryWrapper<ShopScore>()
                .select("avg(score) as score")
                .eq("shop_id", shopId.trim());
        List<BigDecimal> list = shopScoreMapper.selectObjs(queryWrapper)
                .stream()
                .filter(Objects::nonNull)
                .map(o -> (BigDecimal) o)
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(list)) {
            return 0d;
        }
        return list.stream().collect(Collectors.averagingDouble(i -> i.doubleValue()));
    }

    @Override
    public Map<String, ShopScore> getScoreGroupByShop(List<String> shopIds) {
        QueryWrapper<ShopScore> queryWrapper = new QueryWrapper<ShopScore>()
                .select("shop_id", "avg(score) as score")
                .in("shop_id", shopIds)
                .groupBy("shop_id");
        List<ShopScore> list = shopScoreMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(ShopScore::getShopId, Function.identity(), (key1, key2) -> key2));
        }
        return null;
    }

    @Override
    public BaseResult<String> saveShopScore(ShopScoreDTO scoreDTO) {
        int saveResult = 0;
        ShopScoreServiceImpl proxy = (ShopScoreServiceImpl) AopContext.currentProxy();
        LambdaQueryWrapper<ShopScore> queryWrapper = new QueryWrapper<ShopScore>().lambda()
                .eq(ShopScore::getShopId, scoreDTO.getShopId().trim())
                .eq(ShopScore::getUserId, scoreDTO.getUserId().trim());
        ShopScore shopScore = shopScoreMapper.selectOne(queryWrapper);
        if (shopScore != null) {
            saveResult = proxy.updateShopScore(scoreDTO);
        } else {
            saveResult = proxy.addShopScore(scoreDTO);
        }
        if (saveResult < 1) {
            return BaseResult.failure("保存评分信息失败！");
        }
        return BaseResult.success("保存评分信息成功！");
    }

    @Transactional(rollbackFor = Exception.class)
    public int addShopScore(ShopScoreDTO scoreDTO) {
        try {
            ShopScore shopScore = new ShopScore()
                    .setShopId(scoreDTO.getShopId())
                    .setUserId(scoreDTO.getUserId())
                    .setScore(scoreDTO.getScore());
            return shopScoreMapper.insert(shopScore);
        } catch (Exception e) {
            log.error("新增店铺评分信息异常：{}", e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateShopScore(ShopScoreDTO scoreDTO) {
        try {
            LambdaUpdateWrapper<ShopScore> updateWrapper = new UpdateWrapper<ShopScore>().lambda()
                    .eq(ShopScore::getShopId, scoreDTO.getShopId())
                    .eq(ShopScore::getUserId, scoreDTO.getUserId())
                    .set(ShopScore::getScore, scoreDTO.getScore());
            return shopScoreMapper.update(null, updateWrapper);
        } catch (Exception e) {
            log.error("修改店铺评分信息异常：{}", e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

}
