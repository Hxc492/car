package com.toddding.common;

/**
 * @Description: 业务码 业务消息枚举
 * @Author: hxc
 * @Date: 2021/3/7 22:32
 */
public enum CodeMsg {
    SUCCESS(200,"执行成功")
    ,ERROR(110,"出错啦")

    //用户管理
    ,USER_USERNAME_PASSWORD_ERROR(5001,"用户名或密码错误")
    ,USER_LOGIN_NAME_EXIST_ERROR(5002,"用户登录名已被使用")
    ,USER_PHONE_EXIST_ERROR(5003,"用户手机号已被使用")
    ,USER_ID_CARD_EXIST_ERROR(5004,"用户身份证号已被使用")

    //客户管理
    ,CUSTOMER_ID_CARD_EXIST_ERROR(6001,"客户身份证号已被使用")
    ,CUSTOMER_PHONE_EXIST_ERROR(6002,"客户手机号已被使用")

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
