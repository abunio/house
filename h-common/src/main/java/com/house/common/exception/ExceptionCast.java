package com.house.common.exception;

import com.house.common.model.response.ResultCode;

/**
 * @Description 抛出异常静态方法
 * @Author huangW
 * @Date 2020/4/27
 * @Version V1.0
 */
public class ExceptionCast {

    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
