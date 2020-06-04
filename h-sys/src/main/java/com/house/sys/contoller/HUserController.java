//package com.house.sys.contoller;
//
//import com.house.common.entity.sys.HUser;
//import com.house.common.model.response.CommonCode;
//import com.house.common.model.response.ResponseResult;
//import com.house.sys.service.HUserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Description
// * @Author huangW
// * @Date 2020/4/23
// * @Version V1.0
// */
//@RestController
//@Slf4j
//public class HUserController {
//
//    @Autowired
//    private HUserService hUserService;
//
//    /**
//     * 注册
//     * @param hUser
//     * @return
//     */
//    @PostMapping("/register")
//    public ResponseResult register(@RequestBody(required=false) HUser hUser){
//        hUserService.register(hUser);
//        return new ResponseResult(CommonCode.SUCCESS);
//    }
//
//    /**
//     * 修改密码
//     * @param hUser
//     * @return
//     */
//    @PostMapping("/changePassword")
//    public ResponseResult changePassword(@RequestBody(required=false) HUser hUser){
//        hUserService.changePassword(hUser);
//        return new ResponseResult(CommonCode.SUCCESS);
//    }
//}
