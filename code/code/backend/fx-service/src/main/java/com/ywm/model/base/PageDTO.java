/**
 *
 */
package com.ywm.model.base;

import com.ywm.utils.PageUtils;
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
public class PageDTO<T> extends BaseData {

    /**
     * 参数对象
     */
    @Valid
    private T param;

    /**
     * 分页的起始页
     */
    private Integer pageNo;

    /**
     * 每页的记录数
     */
    private Integer pageSize;

    /**
     * 分页的起始记录行号（pg库）
     */
    private Integer offset;


    /**
     * 半参构造
     * @param pageNo 当前页码
     * @param pageSize 每页记录数
     */
    public PageDTO(int pageNo, int pageSize) {
        this.offset = PageUtils.getOffset(pageNo, pageSize) - 1;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    /**
     * 全参构造
     * @param pageNo 当前页码
     * @param pageSize 每页记录数
     * @param param 参数对象
     */
    public PageDTO(int pageNo, int pageSize, T param) {
        this.param = param;
        this.offset = PageUtils.getOffset(pageNo, pageSize) - 1;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

}
