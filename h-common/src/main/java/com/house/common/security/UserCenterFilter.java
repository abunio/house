package com.house.common.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.common.entity.auth.AuthenticationBean;
import com.house.common.exception.ExceptionCast;
import com.house.common.model.response.CommonCode;
import com.house.common.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.smartcardio.CommandAPDU;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;

/**
 * @Description 重写UsernamePasswordAuthenticationFilter过滤器 解析登录认证操作
 * @Author huangW
 * @Date 2020/4/22
 * @Version V1.0
 */
public class UserCenterFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


        //attempt Authentication when Content-Type is json
        String contentType = request.getContentType();
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {

            //use jackson to deserialize json
            ObjectMapper mapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authRequest = null;
            try (InputStream is = request.getInputStream()) {
                AuthenticationBean authenticationBean = mapper.readValue(is, AuthenticationBean.class);
                authRequest = new UsernamePasswordAuthenticationToken(
                        authenticationBean.getUsername(), authenticationBean.getPassword());
            } catch (IOException e) {
                e.printStackTrace();
                authRequest = new UsernamePasswordAuthenticationToken(
                        "", "");
            } finally {
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }

            /*UsernamePasswordAuthenticationToken authRequest = null;
            //取authenticationBean
            Map<String, String> authenticationBean = null;
            //用try with resource，方便自动释放资源
            try {
                InputStream is = request.getInputStream();
                authenticationBean = mapper.readValue(is, Map.class);
            } catch (IOException e) {
                //将异常放到自定义的异常类中
                throw new RuntimeException(e.getMessage());
            }
            try {
                if (!authenticationBean.isEmpty()) {
                    //获得账号、密码
                    String username = authenticationBean.get(SPRING_SECURITY_FORM_USERNAME_KEY);
                    String password = authenticationBean.get(SPRING_SECURITY_FORM_PASSWORD_KEY);
                    //验证码
                    String captcha = authenticationBean.get("captcha");
                    String key = "CAPTCHA_" + captcha;
                    Object code = redisUtil.get(key);
                    if (code != null || String.valueOf(code).equalsIgnoreCase(captcha)) {
                        //将账号、密码装入UsernamePasswordAuthenticationToken中
                        authRequest = new UsernamePasswordAuthenticationToken(username, password);
                        setDetails(request, authRequest);
                        return this.getAuthenticationManager().authenticate(authRequest);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            return null;*/
        } else {
            //transmit it to UsernamePasswordAuthenticationFilter
            return super.attemptAuthentication(request, response);
        }

    }
}
