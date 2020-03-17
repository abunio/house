package com.sys;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description
 * @Author huangW
 * @Date 2020/3/16
 * @Version V1.0
 */
@MapperScan("com.sys.mapper")
@EntityScan("com.common.entity")
@SpringBootApplication
@EnableEurekaClient
public class SysApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysApplication.class, args);
    }


}
