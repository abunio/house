//package com.house.common.security;
//
//import com.house.common.model.response.CommonCode;
//import com.house.common.model.response.ResponseResult;
//import com.house.common.utils.auth.WriteToJson;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Description 登录失败操作
// * @Author huangW
// * @Date 2020/4/23
// * @Version V1.0
// */
//@Component
//@Slf4j
//public class FailureHandler extends WriteToJson implements AuthenticationFailureHandler {
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
//        Map<String,Object> map = new HashMap<>();
//
//        if(e.getMessage().equalsIgnoreCase("Bad credentials")){
//            map.put("msg","密码错误");
//        }else {
//            map.put("msg",e.getMessage());
//        }
//        log.info("msg -> " + e.getMessage());
//        ResponseResult data = new ResponseResult(CommonCode.FAILURE,map);
//        //输出
//        this.WriteJSON(request, response, data);
//    }
//}
