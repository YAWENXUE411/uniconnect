package com.ywm.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 功能描述：店铺类型返回信息
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="ShopType 店铺类型信息响应模型",description="店铺类型信息响应模型")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "\"t_shop_type\"")
public class ShopType {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="店铺类型id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value="类型名称")
    @TableField("\"name\"")
    private String name;

    @ApiModelProperty(value="上级分类id")
    private Integer parentId;

    @ApiModelProperty(value="图标")
    private String icon;

    @ApiModelProperty(value="排序")
    private Integer sorter;

    @ApiModelProperty(value="状态(0:启用 -1:已删除)")
    private Integer status;

    @ApiModelProperty(value="描述")
    @TableField("\"desc\"")
    private String desc;

    @ApiModelProperty(value="创建时间")
    private Date createTime;

    @ApiModelProperty(value="更新时间")
    private Date updateTime;

}
