package com.house.common.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户信息
 * @author: huangW
 * @createDate: 2020/3/16 20:42
 * @version: 1.0
 */

@Data
@TableName("h_landlord")
public class HUser implements Serializable {


    @TableId(type = IdType.INPUT)
    private String id;

    private String mobile;

    private String username;

    private String password;

    private String userNumber;

    private String role;

    private Integer enableState;

    @JsonFormat(pattern = "yyyy-MM-dd",locale = "zh",timezone = "GMT+8")
    private Date createTime;

}
