package com.house.estate;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description
 * @Author huangW
 * @Date 2020/3/16
 * @Version V1.0
 */
@EnableRabbit
@EnableCaching
@MapperScan("com.house.estate.mapper")
@EntityScan("com.house.common.entity")
@SpringBootApplication(scanBasePackages="com.house")
@EnableEurekaClient
public class EstateApplication {
    public static void main(String[] args) {
        SpringApplication.run(EstateApplication.class, args);
    }


}
