package com.ywm.controller;

import cn.hutool.core.lang.tree.Tree;
import com.ywm.model.base.BaseResult;
import com.ywm.model.base.PageVO;
import com.ywm.model.base.PageParam;
import com.ywm.model.shop.AddShopParam;
import com.ywm.model.shop.EditShopParam;
import com.ywm.model.shop.ShopDTO;
import com.ywm.model.shop.ShopVO;
import com.ywm.service.IShopService;
import com.ywm.service.IShopTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述：店铺信息
 *
 * @Author: ywm
 * @Date: 2022/4/12 15:38
 */
@Api(value = "店铺信息", tags = "店铺信息")
@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private IShopService shopService;
    @Resource
    private IShopTypeService shopTypeService;

    @ApiOperation(value = "查询店铺分类树")
    @GetMapping("/typeTree")
    public BaseResult<List<Tree<Integer>>> shopTypeTree() {
        return shopTypeService.shopTypeTree();
    }

    @ApiOperation(value = "查询店铺点位列表")
    @GetMapping("/points")
    public BaseResult<List<ShopVO>> shopPoints() {
        return shopService.shopPoints();
    }

    @ApiOperation(value = "分页查询店铺列表")
    @PostMapping("/pageList")
    public BaseResult<PageVO<ShopVO>> pageList(
            @Validated @RequestBody @ApiParam(name="请求参数",value="传入json格式",required=true) PageParam<ShopDTO> pageParam) {
        return shopService.shopPageList(pageParam);
    }

    @ApiOperation(value = "查询店铺详情")
    @GetMapping("/detail/{id}")
    public BaseResult<ShopVO> detail(
            @ApiParam(name = "id", value = "店铺id", required = true) @PathVariable("id") String id) {
        return shopService.shopDetail(id);
    }

    @ApiOperation(value = "新增店铺")
    @PostMapping(value = "/add")
    public BaseResult<String> add(
            @Validated @RequestBody @ApiParam(name = "添加参数", value = "传入json格式", required = true) AddShopParam param) {
        return shopService.addShop(param);
    }

    @ApiOperation(value = "修改店铺")
    @PutMapping(value = "/edit")
    public BaseResult<String> edit(
            @Validated @RequestBody @ApiParam(name = "修改参数", value = "传入json格式", required = true) EditShopParam param) {
        return shopService.editShop(param);
    }

}
