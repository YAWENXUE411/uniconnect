/**
 *
 */
package com.ywm.model.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

/**
 * 功能描述：分页查询通用参数
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="PageParam 分页搜索参数模型",description="分页搜索参数模型")
@Data
@NoArgsConstructor
public class PageParam<T> extends BaseData {

    /**
     * 参数对象
     */
    @Valid
    private T param;

    /**
     * 分页的起始页
     */
    @ApiModelProperty(value="当前页码", example = "1")
    private Integer pageNo;

    /**
     * 每页的记录数
     */
    @ApiModelProperty(value="每页记录数", example = "20")
    private Integer pageSize;


}
