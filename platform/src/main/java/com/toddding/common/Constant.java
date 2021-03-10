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
    /**
     * 保存上传文件的文件夹
     */
    String UPLOAD_FOLDER="upload";
    /**
     * 车辆未出租状态
     */
    Integer CAR_RENT_NOT=1;
    /**
     * 车辆已出租状态
     */
    Integer CAR_RENT_ED=2;
    /**
     * 出租记录未还车状态
     */
    Integer RENT_RETURN_NOT=1;
    /**
     * 出租记录已还车状态
     */
    Integer RENT_RETURN_ED=2;
}
