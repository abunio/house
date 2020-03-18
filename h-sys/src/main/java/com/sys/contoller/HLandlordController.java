package com.sys.contoller;

import com.common.entity.HLandlord;
import com.common.model.response.PageResult;
import com.common.model.response.Result;
import com.sys.service.HLandlordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author huangW
 * @Date 2020/3/17
 * @Version V1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/landlord")
public class HLandlordController {

    @Autowired
    private HLandlordService hLandlordService;

    /**
     * 分页
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list/{page}/{size}")
    public PageResult findPage(@PathVariable("page") int page, @PathVariable("size")int size){
        return hLandlordService.findPage(page,size);
    }

    /**
     * 保存
     * @param hLandlord
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody HLandlord hLandlord ){
        hLandlordService.add(hLandlord);
        return new Result(true,"保存成功");
    }
}
