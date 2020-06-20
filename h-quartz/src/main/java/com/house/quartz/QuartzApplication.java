package com.house.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Description
 * @Author huangW
 * @Date 15:34 2020/6/18
 * @Version 1.0
 */
@MapperScan("com.house.quartz.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class QuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }
}
