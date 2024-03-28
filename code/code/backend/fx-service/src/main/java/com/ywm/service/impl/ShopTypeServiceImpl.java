package com.ywm.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ywm.mapper.ShopTypeMapper;
import com.ywm.enums.ResultEnum;
import com.ywm.enums.ShopTypeStatusEnum;
import com.ywm.model.base.BaseResult;
import com.ywm.model.entity.ShopType;
import com.ywm.service.IShopTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述：店铺分类信息
 *
 * @Author: ywm
 * @Date: 2022/4/10 18:53
 */
@Slf4j
@Service
public class ShopTypeServiceImpl implements IShopTypeService {

    @Resource
    private ShopTypeMapper shopTypeMapper;


    @Cacheable(cacheNames = "shopTypeTree", sync = true)
    @Override
    public BaseResult<List<Tree<Integer>>> shopTypeTree() {
        try {
            LambdaQueryWrapper<ShopType> queryWrapper = new QueryWrapper<ShopType>().lambda()
                    .select(ShopType::getId, ShopType::getName, ShopType::getParentId, ShopType::getSorter, ShopType::getIcon)
                    .eq(ShopType::getStatus, ShopTypeStatusEnum.ENABLE.getStatus())
                    .orderByAsc(ShopType::getSorter);
            List<ShopType> types = shopTypeMapper.selectList(queryWrapper);
            if (CollUtil.isEmpty(types)) {
                return BaseResult.failure("获取店铺类型数据为null");
            }
            TreeNodeConfig config = new TreeNodeConfig();
            //config可以配置属性字段名和排序等等
            config.setParentIdKey("parentId");
            //最大递归深度  默认无限制
            //config.setDeep(20);
            List<Tree<Integer>> treeNodes = TreeUtil.build(types, 0, config, (object, tree) -> {
                tree.setId(object.getId());
                tree.setParentId(object.getParentId());
                tree.setName(object.getName());
                //更新一波,weight为排序字段,不过测试时子级好像并没有生效
                tree.setWeight(object.getSorter());
                // 扩展属性 ...
                tree.putExtra("icon", object.getIcon());
            });
            return BaseResult.success(treeNodes);
        } catch (Exception e) {
            log.error("查询店铺分类列表异常：{}", e.getMessage(), e);
            return BaseResult.failure(ResultEnum.SERVER_EXCEPTION);
        }
    }

    @Override
    public ShopType getShopType(Integer typeId) {
        LambdaQueryWrapper<ShopType> queryWrapper = new QueryWrapper<ShopType>().lambda()
                .eq(ShopType::getId, typeId);
        return shopTypeMapper.selectOne(queryWrapper);
    }

}
