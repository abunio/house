package com.house.sys.contoller;

import com.house.common.entity.estate.HLandlord;
import com.house.common.model.request.QueryPageBean;
import com.house.common.model.response.PageResult;
import com.house.common.model.response.Result;
import com.house.sys.service.HLandlordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author huangW
 * @Date 2020/3/17
 * @Version V1.0
 */

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
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        Object username = request.getAttribute("username");
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
