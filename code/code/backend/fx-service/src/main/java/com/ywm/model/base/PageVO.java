package com.ywm.model.base;

import com.ywm.utils.PageUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 功能描述：分页响应模型
 *
 * @Author: ywm
 * @Date: 2021/6/22 21:53
 */
@ApiModel(value="PageVO 分页响应模型",description="分页响应模型")
@Data
@NoArgsConstructor
public class PageVO<T> extends BaseData {

    /**
     * 当前页码
     */
    @ApiModelProperty(value="当前页码")
    private Integer pageNo;

    /**
     * 每页记录数
     */
    @ApiModelProperty(value="每页记录数")
    private Integer pageSize;

    /**
     * 总页数
     */
    @ApiModelProperty(value="总页数")
    private Long totalPage;

    /**
     * 总记录条数
     */
    @ApiModelProperty(value="总记录条数")
    private Long totalCount;

    /**
     * 返回数组数据模型
     */
    @ApiModelProperty(value="返回数组数据模型")
    private List<T> dataList;

    /**
     * 半参构造
     * @param pageNo 当前页码
     * @param pageSize 每页记录数
     * @param totalCount 总页数
     */
    public PageVO(int pageNo, int pageSize, long totalCount) {
    	this.pageNo = pageNo;
    	this.pageSize = pageSize;
    	this.totalCount = totalCount;
    	this.totalPage = PageUtils.getTotalPage(totalCount, pageSize);
    }

    /**
     * 全参构造
     * @param pageNo 当前页码
     * @param pageSize 每页记录数
     * @param totalCount 总页数
     * @param dataList 返回数组数据模型
     */
    public PageVO(int pageNo, int pageSize, long totalCount, List<T> dataList) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = PageUtils.getTotalPage(totalCount, pageSize);
        this.dataList = dataList;
    }

}
