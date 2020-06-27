package com.coolwen.experimentplatform.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CoolWen
 * @version 2020-05-06 21:46
 */
@Controller
//@RequestMapping("/admin")
public class HelloController {
    @RequestMapping("/")
//    @RequiresRoles("Admin")
    public String hello() {
        return "redirect:/newsinfo/newslist";
    }

    @GetMapping("/405")
    public String quanxian(){
        return "405";
    }
}
