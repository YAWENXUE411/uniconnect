package com.ywm.model.student;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ywm.model.base.BaseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述：学生信息
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="Student 学生信息",description="学生信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StudentVO extends BaseData {

    @ApiModelProperty(value="id")
    private Long id;

    @ApiModelProperty(value="姓名")
    private String name;

    @ApiModelProperty(value="性别(1-男  2-女)")
    private Integer sex;

    @ApiModelProperty(value="年龄")
    private Integer age;

    @ApiModelProperty(value="学校")
    private String university;

    @ApiModelProperty(value="学院")
    private String college;

    @ApiModelProperty(value="专业")
    private String specialized;

    @ApiModelProperty(value="成绩")
    private Double grades;

    @ApiModelProperty(value="gpa")
    private Double gpa;

    @ApiModelProperty(value="学位")
    private String degree;

    @ApiModelProperty(value="offer")
    private String offer;

    @ApiModelProperty(value="研究生机构")
    private String institution;

    @ApiModelProperty(value="金额")
    private BigDecimal price;

    @ApiModelProperty(value="更新时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date updateTime;

}
