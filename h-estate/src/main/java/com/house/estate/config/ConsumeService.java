//package com.house.estate.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * @Description mq消费
// * @Author huangW
// * @Date 2020/6/27
// * @Version V1.0
// */
//@Slf4j
//@Component
//public class ConsumeService {
//
//    @RabbitListener(queues = MQConstant.queue)
//    public void process(String hello) {
//
//        log.info("msg -->" + hello);
//    }
//
//}
