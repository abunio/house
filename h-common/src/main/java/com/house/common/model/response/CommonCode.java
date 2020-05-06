package com.house.common.model.response;

import lombok.ToString;

/**
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 18:33.
 * @Modified By:
 */

@ToString
public enum CommonCode implements ResultCode{

    SUCCESS(true,10000,"操作成功！"),
    FAIL(false,10001,"操作失败！"),
    UNAUTHENTICATED(false,10002,"此操作需要登陆系统！"),
    INVALID_PARAM(false,10003,"非法参数！"),
    UNAUTHORISE(false,10004,"权限不足，无权操作！"),
    LOGIN(true,10005,"登录成功！"),
    FAILURE(false,10006,"登录失败！"),
    USER_EXISTS(false,10007,"用户已存在"),
    SERVER_ERROR(false,10008,"抱歉，系统繁忙，请稍后重试！"),
    CODE_EXPIRED(false,10009,"验证码过期！"),
    CODE_FAIL(false,10010,"生成验证码失败！"),
    CODE_ERROR(false,10010,"验证码错误！");

//    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private CommonCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }
    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


}
