package com.ywm.model.university;

import com.ywm.model.base.BaseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 功能描述：
 *
 * @Author: ywm
 * @Date: 2023/10/25 21:28
 */
@ApiModel(value="UniversityDTO 大学信息响应模型",description="大学信息响应模型")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UniversityDTO extends BaseData {

    @ApiModelProperty(value="学校名称")
    private String name;

    @ApiModelProperty(value="排名前N", example = "500")
    private Integer topN;

}
