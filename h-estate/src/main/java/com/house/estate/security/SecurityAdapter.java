//package com.house.estate.security;
//
//import com.house.common.filter.TokenRequestFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsUtils;
//
///**
// * @Description 授权
// * @Author huangW
// * @Date 2020/4/23
// * @Version V1.0
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityAdapter extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private TokenRequestFilter tokenRequestFilter;
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        //第1步：解决跨域问题。cors 预检请求放行,让Spring security 放行所有preflight request（cors 预检请求）
//        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
//
//        //第2步：让Security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
//        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().headers().cacheControl();
//
//        http.antMatcher("/**").authorizeRequests()
//                .anyRequest().authenticated();
//        //这里必须要写formLogin()，不然原有的UsernamePasswordAuthenticationFilter不会出现，也就无法配置我们重新的UsernamePasswordAuthenticationFilter
//        //.and().formLogin().loginPage("/");
//
//        //拦截token，并检测。在 UsernamePasswordAuthenticationFilter 之前添加 JwtAuthenticationTokenFilter
//        http.addFilterBefore(tokenRequestFilter,UsernamePasswordAuthenticationFilter.class);
//    }
//
//}
