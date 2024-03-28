package com.ywm.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 功能描述：用户信息实体对象
 *
 * @Author: ywm
 * @Date: 2023/10/22 15:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "\"t_sys_user\"")
public class SysUser {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String username;
    private String nickname;
    private String password;
    private Integer status;
    private Date createTime;
    private Date updateTime;

}
