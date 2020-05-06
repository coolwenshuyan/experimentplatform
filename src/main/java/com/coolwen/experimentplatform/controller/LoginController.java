package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.kit.ShiroKit;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author CoolWen
 * @version 2018-11-01 7:16
 */
@Controller
public class LoginController {

    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);



    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(String username, String password, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        String emsg = null;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            emsg = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            emsg = "用户名/密码错误";
        } catch (ExcessiveAttemptsException e) {
            // TODO: handle exception
            emsg = "登录失败多次，账户锁定1小时!";
        } catch (AuthenticationException e) {
            emsg = e.getMessage();
            //           logger.info("登录信息MSG:" + msg);
        }
        model.addAttribute("emsg", emsg);
        logger.debug("登陆错误信息:" + emsg);
        if (ShiroKit.isEmpty(emsg)) {
            return "redirect:/admin/res/list";
        } else {
            return "/login";
        }
    }
}

