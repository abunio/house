package com.common.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description 房东房屋信息
 * @Author huangW
 * @Date 2020/3/17
 * @Version V1.0
 */
@Entity
@Table(name = "h_landlord")
@Data
public class HLandlord implements Serializable {

    @Id
    private String id;

    private String landlord;

    private String phone;

    private String address;

    private String community;

    private Integer status;

    private Integer del;
}
