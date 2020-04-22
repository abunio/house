package com.house.sys.config;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import com.house.common.entity.auth.AuthUser;
import com.house.common.model.response.CommonCode;
import com.house.common.model.response.ResponseResult;
import com.house.common.model.response.Result;
import com.house.common.utils.auth.JwtTokenUtil;
import com.house.common.utils.auth.TokenCache;
import com.house.common.utils.auth.WriteToJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author huangW
 * @Date 2020/4/22
 * @Version V1.0
 */
@Component
@Slf4j
public class SuccessHandler extends WriteToJson implements AuthenticationSuccessHandler {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//        User user = (User)authentication.getPrincipal();
//        log.info("username ->" + user.getUsername());
//        resp.setContentType("application/json;charset=utf-8");
//        Result result = new Result(true,"登录成功",user);
//        log.info("result ->" + JSON.toJSONString(result));
//        resp.getWriter().write(JSON.toJSONString(result));

        //取得账号信息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = TokenCache.getTokenFromCache(userDetails.getUsername());
        if(token ==null) {
            System.out.println("初次登录，token还没有，生成新token。。。。。。");
            //如果token为空，则去创建一个新的token
            //jwtTokenUtil = new JwtTokenUtil();
            token = jwtTokenUtil.generateToken(userDetails);
            //把新的token存储到缓存中
            TokenCache.setToken(userDetails.getUsername(),token);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("username",userDetails.getUsername());
        map.put("token",token);

        ResponseResult data = new ResponseResult(CommonCode.LOGIN,map);
        //输出
        this.WriteJSON(req, resp, data);

    }
}
