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

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述：学生信息实体对象
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@ApiModel(value="Student 学生信息实体对象",description="学生信息实体对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "\"t_student\"")
public class Student {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value="姓名")
    @TableField("\"name\"")
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
    private Double price;

    @ApiModelProperty(value="创建时间")
    private Date createTime;

    @ApiModelProperty(value="更新时间")
    private Date updateTime;

}
