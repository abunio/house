/**
 * 
 */
package com.house.common.entity.estate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 张梓枫
 * @Description 区域级联选择器
 * @date:   2019年1月15日 上午11:38:59
 */
@Data
@ApiModel(description = "区域级联选择器")
public class AreaCascader implements Serializable{
    
    private static final long serialVersionUID = -2987006081567944433L;

    @ApiModelProperty(value = "显示的中文名称")
    private String label;
    
    @ApiModelProperty(value = "显示的value")
    private String value;
    
    @ApiModelProperty(value = "上级ID")
    private String parentId;

    @ApiModelProperty(value = "首字母")
    private String acronym;

    @ApiModelProperty(value = "下级区域")
    private List<AreaCascader> children = new ArrayList<>();

}
