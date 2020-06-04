//package com.house.common.security;
//
//import com.house.common.entity.auth.AuthUser;
//import com.house.common.model.response.CommonCode;
//import com.house.common.model.response.ResponseResult;
//import com.house.common.utils.auth.JwtTokenUtil;
//import com.house.common.utils.auth.TokenCache;
//import com.house.common.utils.auth.WriteToJson;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
// * @Description 登录成功操作
// * @Author huangW
// * @Date 2020/4/22
// * @Version V1.0
// */
//@Component
//@Slf4j
//public class SuccessHandler extends WriteToJson implements AuthenticationSuccessHandler {
//
//    @Autowired
//    JwtTokenUtil jwtTokenUtil;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//
//        //取得账号信息
//        AuthUser authUser = (AuthUser) authentication.getPrincipal();
//        log.info("userDetails -> " + authUser);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = TokenCache.getTokenFromCache(authUser.getUsername());
//        if(token ==null) {
//            log.info("初次登录，token还没有，生成新token。。。。。。");
//            //如果token为空，则去创建一个新的token
//            token = jwtTokenUtil.generateToken(authUser);
//            //把新的token存储到缓存中
//            TokenCache.setToken(authUser.getUsername(),token);
//        }
//
//        log.info("token -> " + token);
//        Map<String,Object> map = new HashMap<>();
//        map.put("userName",authUser.getUsername());
//        map.put("userNumber",authUser.getUserNumber());
//        map.put("token",token);
//
//        ResponseResult data = new ResponseResult(CommonCode.LOGIN,map);
//        //输出
//        this.WriteJSON(req, resp, data);
//
//    }
//
//}
