package com.house.common.entity.sys;

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
public class HUser implements Serializable {


    private String id;

    private String mobile;

    private String username;

    private String password;

    private String role;

    private Integer enableState;

    private Date createTime;
}
