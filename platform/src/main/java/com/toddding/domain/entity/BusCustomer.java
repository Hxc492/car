package com.toddding.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * bus_customer
 * @author 
 */
@ApiModel(value="客户表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusCustomer implements Serializable {
    /**
     * 客户ID
     */
    @ApiModelProperty(value="客户ID")
    private Integer id;

    /**
     * 客户名称
     */
    @ApiModelProperty(value="客户名称")
    private String name;

    /**
     * 客户电话
     */
    @ApiModelProperty(value="客户电话")
    private String phone;

    /**
     * 客户地址
     */
    @ApiModelProperty(value="客户地址")
    private String address;

    /**
     * 身份证号
     */
    @ApiModelProperty(value="身份证号")
    private String idCard;

    /**
     * 性别 1 男 2女
     */
    @ApiModelProperty(value="性别 1 男 2女")
    private Integer sex;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value="修改时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}