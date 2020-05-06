package com.house.common.entity.auth;

import lombok.Data;

/**
 * @Description
 * @Author huangW
 * @Date 2020/4/22
 * @Version V1.0
 */
@Data
public class AuthenticationBean {
    private String username;
    private String password;
    private String uuid;
    private String captcha;
}
