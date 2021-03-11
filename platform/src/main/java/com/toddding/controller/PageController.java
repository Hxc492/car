package com.toddding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:负责页面跳转的controller
 *   因为所有的页面都在WEB-INF下面，是无法直接访问的
 *   必须使用服务器的内部转发访问
 * @Author: hxc
 * @Date: 2021/3/8 18:03
 */
@Controller
@RequestMapping("page")
public class PageController {
    /**
     * 跳转主页面
     * @return
     */
    @RequestMapping("main.do")
    public String main(){
        return "main.jsp";
    }

    /**
     * 跳转用户列表
     * @return
     */
    @RequestMapping("user/list.do")
    public String userList(){
        return "user/list.jsp";
    }

    /**
     * 跳转客户列表
     * @return
     */
    @RequestMapping("customer/list.do")
    public String customerList(){
        return "customer/list.jsp";
    }

    /**
     * 跳转汽车列表
     * @return
     */
    @RequestMapping("car/list.do")
    public String carList(){
        return "car/list.jsp";
    }

    /**
     * 跳转租车记录
     * @return
     */
    @RequestMapping("rent/list.do")
    public String rentList(){
        return "rent/list.jsp";
    }

    /**
     * 跳转还车记录
     * @return
     */
    @RequestMapping("return/list.do")
    public String returnList(){
        return "return/list.jsp";
    }

     /**
     * 跳转角色管理
     * @return
     */
    @RequestMapping("role/list.do")
    public String roleList(){
        return "role/list.jsp";
    }

     /**
     * 跳转权限管理
     * @return
     */
    @RequestMapping("perm/list.do")
    public String permList(){
        return "perm/list.jsp";
    }

    /**
     * 跳转新闻公告
     * @return
     */
    @RequestMapping("news/list.do")
    public String newsList(){
        return "news/list.jsp";
    }

}
