package com.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description 房东房屋信息
 * @Author huangW
 * @Date 2020/3/17
 * @Version V1.0
 */
//@Entity
//@Table(name = "h_landlord")
@Data
public class HLandlord implements Serializable {

    @Id
    private String id;

    private String landlord;

    private String phone;

    private String address;

    private String community;

    private String status;

    private Integer del;

    @JsonFormat(pattern = "yyyy-MM-dd",locale = "zh",timezone = "GMT+8")
    private Date modifiedDate;
}
