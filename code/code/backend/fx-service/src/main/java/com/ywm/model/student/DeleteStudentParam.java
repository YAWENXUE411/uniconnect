package com.ywm.model.student;

import com.ywm.model.base.BaseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 功能描述：删除学生信息参数对象
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="删除学生信息参数对象",description="删除学生信息参数对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DeleteStudentParam extends BaseData {

    @ApiModelProperty(value="用户id")
    @NotBlank(message = "userId必填")
    private String userId;

    @ApiModelProperty(value="学生id，多个以英文逗号隔开，如：a,b,c")
    @NotBlank(message = "studentIds必填")
    private String studentIds;

}
