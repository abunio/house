package com.house.sys.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author huangW
 * @Date 17:19 2020/6/29
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiniuConfig {

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String httpBase;
    private String pathPicture;
    private String pathVideo;


}
