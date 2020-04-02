package com.house.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Description
 * @Author huangW
 * @Date 2020/4/2
 * @Version V1.0
 */
@SpringBootApplication
@EnableZuulProxy//此工程是一个zuul网关
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
