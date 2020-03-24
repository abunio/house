package com.common.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author huangW
 * @Date 2020/3/20
 * @Version V1.0
 */
@Data
public class QueryPageBean implements Serializable {

    private Integer currentPage;//页码
    private Integer pageSize;//每页记录数
    private String queryString;//查询条件
    private String status;

}
