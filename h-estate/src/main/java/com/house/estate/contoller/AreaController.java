/**
 *
 */
package com.house.estate.contoller;


import com.house.common.entity.estate.AreaCascader;

import com.house.estate.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 区域信息资源
 * @Author huangW
 * @Date 2020/3/17
 * @Version V1.0
 */
@RestController
@RequestMapping("area")
@Api(value = "区域信息资源api", tags = "AreaResource")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("/areas")
    @ApiOperation(value = "区域信息资源api", notes = "获取区域级联信息")
    public List<AreaCascader> queryAreaCascader() {
        List<AreaCascader> cascaders = areaService.queryAreaCascader();
        return cascaders;
    }


}
