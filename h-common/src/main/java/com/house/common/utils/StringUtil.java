package com.house.common.utils;

import java.util.UUID;

/**
 * @Description
 * @Author huangW
 * @Date 2020/3/18
 * @Version V1.0
 */
public class StringUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
