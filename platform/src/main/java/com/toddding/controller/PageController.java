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
    @RequestMapping("main.do")
    public String main(){
        return "main.jsp";
    }
}
