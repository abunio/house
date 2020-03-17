package com.common.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: TODO
 * @author: huangW
 * @createDate: 2020/3/16 20:42
 * @version: 1.0
 */
@Entity
@Table(name = "h_user")
@Data
public class HUser implements Serializable {

    @Id
    private String id;
    private String mobile;
    private String username;
    private String password;
    private String role;
    private Integer enableState;
    private Date createTime;
}
