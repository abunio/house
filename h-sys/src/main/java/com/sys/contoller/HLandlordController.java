package com.sys.contoller;

import com.common.entity.HLandlord;
import com.common.model.request.QueryPageBean;
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
     * @param queryPageBean
     * @return
     */
    @PostMapping("/list")
    public PageResult findPage(@RequestBody(required=false) QueryPageBean queryPageBean){
        return hLandlordService.findPage(queryPageBean);
    }

    /**
     * 新增和编辑
     * @param hLandlord
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody HLandlord hLandlord ){
        String msg = hLandlordService.add(hLandlord);
        return new Result(true,msg);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("findById")
    public Result findById(@RequestParam("id") String id){
        return new Result(true,"查询成功",hLandlordService.findById(id));
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("delete")
    public Result delete(@RequestParam("id") String id){
        return new Result(true,hLandlordService.delete(id));
    }
}
