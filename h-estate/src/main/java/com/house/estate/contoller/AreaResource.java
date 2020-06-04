/**
 *
 */
package com.house.estate.contoller;


import com.house.common.entity.estate.AreaCascader;
import com.house.estate.service.AreaBlogic;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 张梓枫
 * @Description 区域信息资源api
 * @date: 2019年1月15日 下午3:41:06
 */
@RestController
@RequestMapping("area")
@Api(value = "区域信息资源api", tags = "AreaResource")
public class AreaResource {

    @Autowired
    private AreaBlogic areaBlogic;

    @GetMapping("/areas")
    @ApiOperation(value = "区域信息资源api", notes = "获取区域级联信息")
    public List<AreaCascader> queryAreaCascader() {
        List<AreaCascader> cascaders = areaBlogic.queryAreaCascader();
        return cascaders;
    }


}
