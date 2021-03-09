package com.toddding.common;

/**
 * @Description: 业务码 业务消息枚举
 * @Author: hxc
 * @Date: 2021/3/7 22:32
 */
public enum CodeMsg {
    SUCCESS(200,"执行成功")
    ,ERROR(110,"出错啦")
    ,USER_USERNAME_PASSWORD_ERROR(5001,"用户名或密码错误")
    ;

    //业务码
    public Integer code;
    //业务消息
    public String msg;

    CodeMsg(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
