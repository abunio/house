package com.house.common.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.common.entity.auth.AuthUser;
import com.house.common.model.response.CommonCode;
import com.house.common.model.response.ResponseResult;
import com.house.common.utils.auth.JwtTokenUtil;
import com.house.common.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description Token拦截器 所有请求验证token进行授权
 * @Author huangW
 * @Date 2020/4/22
 * @Version V1.0
 */
@Component
@Slf4j
public class TokenRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

//    @Autowired
//    private RedisUtil redisUtil;

    private final String header = "Authorization";
    private final String userNumberStart = "USE";


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
//        //验证码
//        if ("POST".equals(request.getMethod()) && "/login".equals(request.getServletPath())) {
//            //登录请求
//            String captcha = request.getParameter("code");
//            String key = "CAPTCHA_" + captcha;
//            Object code = redisUtil.get(key);
//            if (code != null || String.valueOf(code).equalsIgnoreCase(captcha)) {
//
//            }else {
//                response.setContentType("application/json;charset=UTF-8");
//                response.setHeader("Access-Control-Allow-Origin", "*");
//                response.setHeader("Access-Control-Allow-Method", "POST,GET");
//                //输出JSON
//                PrintWriter out = response.getWriter();
//                ResponseResult data = new ResponseResult(CommonCode.CODE_ERROR);
//                out.write(new ObjectMapper().writeValueAsString(data));
//                out.flush();
//                out.close();
//                return;
//            }
//        }

        //检验token
        String headerToken = request.getHeader(header);
        if (StringUtils.isNotBlank(headerToken)) {
            String token = headerToken.replace("Bearer", "").trim();

            //判断令牌是否过期，默认是一周
            //比较好的解决方案是：
            //登录成功获得token后，将token存储到数据库（redis）
            //将数据库版本的token设置过期时间为15~30分钟
            //如果数据库中的token版本过期，重新刷新获取新的token
            //注意：刷新获得新token是在token过期时间内有效。
            //如果token本身的过期（1周），强制登录，生成新token。
            boolean check = false;
            try {
                check = this.jwtTokenUtil.isTokenExpired(token);
            } catch (Exception e) {
                new Throwable("令牌已过期，请重新登录。" + e.getMessage());
            }
            if (!check) {
                //通过令牌获取用户名称
                String username = jwtTokenUtil.getUsernameFromToken(token);
                log.info("username -> " + username);
                String userNumber = jwtTokenUtil.getUserNumberFromToken(token);
                log.info("userNumber -> " + userNumber);

                //判断用户不为空，且SecurityContextHolder授权信息还是空的
                if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(userNumber)
                        && userNumber.startsWith(userNumberStart) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    request.setAttribute("username", username);
                    AuthUser user = new AuthUser();
                    user.setUsername(username);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, null);
                    //
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }

}
