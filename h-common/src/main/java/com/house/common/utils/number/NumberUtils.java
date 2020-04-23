package com.house.common.utils.number;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author huangW
 * @Date 2020/4/23
 * @Version V1.0
 */
@Component
@Slf4j
public class NumberUtils {


    @Autowired
    private RedisTemplate redisTemplate;

    private final String tableName = "USE";


    /**获取编号
     *
     * @return
     */
    public String getNumber() {
        log.info("NumberUtils.tableName:{}",tableName);
        Long increment = redisTemplate.opsForValue().increment(tableName, 1);
        String s = String.valueOf(increment);
        int length = s.length();
        StringBuilder sb = new StringBuilder();
        sb.append(tableName);
        for (int i = 0; i < 6 - length; i++) {
            sb.append(0);
        }
        sb.append(s);
        log.info("NumberUtils.number:{}",sb);
        return sb.toString();
    }

    /**
     * 临时获取编号
     * @param tableName 表名首字母大写
     * @return
     */
    public String getNumberThree(String tableName) {
        log.info("NumberUtils.tableName:{}",tableName);
        Long increment = redisTemplate.opsForValue().increment(tableName, 1);
        String s = String.valueOf(increment);
        int length = s.length();
        StringBuilder sb = new StringBuilder();
        sb.append(tableName);
        for (int i = 0; i < 3 - length; i++) {
            sb.append(0);
        }
        sb.append(s);
        log.info("NumberUtils.number:{}",sb);
        return sb.toString();
    }
}
