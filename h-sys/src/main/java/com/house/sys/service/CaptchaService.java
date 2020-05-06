package com.house.sys.service;

import com.house.common.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.code.kaptcha.Producer;
import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * @Description
 * @Author huangW
 * @Date 2020/5/1
 * @Version V1.0
 */
@Service
@Slf4j
public class CaptchaService {

    @Autowired
    private Producer producer;

    @Autowired
    private RedisUtil redisUtil;

    public BufferedImage getCaptcha(String uuid) {

        //生成文字验证码
        String code = producer.createText();
        log.info("生成验证码 -> " + code);
        String key = "CAPTCHA_" + code;
        redisUtil.set(key,code,300);
        return producer.createImage(code);
    }
}
