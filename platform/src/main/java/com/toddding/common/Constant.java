package com.toddding.common;

/**
 * @Description: 常量接口
 * @Author: hxc
 * @Date: 2021/3/7 22:36
 */
public interface Constant {
    /**
     * MD5加密的盐
     */
    String MD5_SALT="car rental";
    /**
     * 系统异常码 ：数据校验不通过
     */
    Integer PARAM_CHECK_ERROR=4001;
    /**
     * 默认的登录密码
     */
    String DEFAULT_PASSWORD="123456";
}
