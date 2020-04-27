package com.house.common.exception;

import com.house.common.model.response.ResultCode;

/**
 * @Description 自定义异常类型
 * @Author huangW
 * @Date 2020/4/27
 * @Version V1.0
 */
public class CustomException extends RuntimeException {

    //错误代码
    ResultCode resultCode;

    public CustomException(ResultCode resultCode){
        this.resultCode = resultCode;
    }
    public ResultCode getResultCode(){
        return resultCode;
    }
}
