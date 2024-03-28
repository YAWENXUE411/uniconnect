package com.ywm.model.student;

import com.ywm.model.base.BaseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 功能描述：学生信息
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="StudentDTO 学生信息",description="学生信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StudentDTO extends BaseData {

    @ApiModelProperty(value="姓名")
    private String name;

    @ApiModelProperty(value="性别(1-男  2-女)")
    private Integer sex;

    @ApiModelProperty(value="年龄最小值")
    @Min(value = 0, message = "年龄最小值为0")
    private Integer minAge;

    @ApiModelProperty(value="年龄最大值")
    private Integer maxAge;

    @ApiModelProperty(value="学校")
    private String university;

    @ApiModelProperty(value="学院")
    private String college;

    @ApiModelProperty(value="专业")
    private String specialized;

    @ApiModelProperty(value="成绩最小值")
    @Min(value = 0, message = "成绩最小值为0")
    private Double minGrades;

    @ApiModelProperty(value="成绩最大值")
    private Double maxGrades;

    @ApiModelProperty(value="gpa最小值")
    @Min(value = 0, message = "gpa最小值为0")
    private Double minGpa;

    @ApiModelProperty(value="gpa最大值")
    private Double maxGpa;

    @ApiModelProperty(value="学位")
    private String degree;

    @ApiModelProperty(value="offer")
    private String offer;

    @ApiModelProperty(value="研究生机构")
    private String institution;

    @ApiModelProperty(value="金额最小值")
    @Min(value = 0, message = "金额最小值为0")
    private Double minPrice;

    @ApiModelProperty(value="金额最大值")
    private Double maxPrice;

}
