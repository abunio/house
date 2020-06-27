package com.house.estate.contoller;

import com.house.estate.config.MQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Description mq提供方
 * @Author huangW
 * @Date 2020/6/27
 * @Version V1.0
 */
@RestController
@RequestMapping("/rabbit")
@Slf4j
public class ProvideController {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.dev-exchange}")
    private String exchange;

    @GetMapping("/get")
    public String hello() {
        String context = "hello " + new Date();
        this.rabbitTemplate.convertAndSend(exchange, MQConstant.routing, context);
        return context;
    }




}
