package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.kit.ShiroKit;
import com.coolwen.experimentplatform.model.Admin;
import com.coolwen.experimentplatform.model.Student;
import com.coolwen.experimentplatform.model.User;
import com.coolwen.experimentplatform.service.AdminService;
import com.coolwen.experimentplatform.service.StudentService;
import com.coolwen.experimentplatform.utils.LoginToken;
import com.coolwen.experimentplatform.utils.Message;
import com.coolwen.experimentplatform.utils.VerifyCode.IVerifyCodeGen;
import com.coolwen.experimentplatform.utils.VerifyCode.SimpleCharVerifyCodeGenImpl;
import com.coolwen.experimentplatform.utils.VerifyCode.VerifyCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CoolWen
 * @version 2018-11-01 7:16
 */
@Controller
@SessionAttributes("VerifyCode")
public class LoginController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private AdminService adminService;

    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = {"/verifyCode"}, method = RequestMethod.GET)
    public void Code(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            //将VerifyCode绑定session
            request.getSession().removeAttribute("VerifyCode");
            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            System.out.println("错误");
        }
    }



    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("account")String username,
                              @RequestParam("password") String password,
                              @RequestParam("type") String loginType,
                              @RequestParam("code") String loginCode) {
        //1:获取cookie里面的验证码信息
        ModelAndView model = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String code = ((String) session.getAttribute("VerifyCode")).toLowerCase();//转换成小写;
        loginCode = loginCode.toLowerCase();
        if (loginCode.equals(code)) {
            LoginToken token = new LoginToken(username, ShiroKit.md5(password,username),loginType);
            Message message = new Message();
            try {
                subject.login(token);
                User user = (User) subject.getPrincipal();
                session.setAttribute("username",user.getUsername());
                session.setAttribute("nickname",user.getNickname());
                model.addObject("name", user.getUsername());
            } catch (UnknownAccountException e) {
                //message.put("emsg","用户名/密码错误");
                model.addObject("msg", "用户名/密码错误");
            } catch (IncorrectCredentialsException e) {
                //message.put("emsg","用户名/密码错误");
                model.addObject("msg", "用户名/密码错误");
            } catch (ExcessiveAttemptsException e) {
                // TODO: handle exception
                //message.put("emsg","登录失败多次，账户锁定1小时!");
                model.addObject("msg", "登录失败多次，账户锁定1小时!");
            } catch (AuthenticationException e) {
                //message.put("emsg",e.getMessage());
                model.addObject("msg", e.getMessage());
                //           logger.info("登录信息MSG:" + msg);
            }
            model.addObject("msg", message);
            logger.debug("登陆错误信息:" + message.get("emsg"));
            if (ShiroKit.isEmpty(message.get("emsg"))) {
                model.setViewName("user/list");
            } else {
                model.setViewName("login");;
            }
        }else {
            model.addObject("msg", "验证码错误");
        }
        return model;
    }


    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public ModelAndView register(@RequestParam("account")String username,
                           @RequestParam("password") String password,
                           @RequestParam("type") String registType,
                           @RequestParam("tel") String tel) {
        ModelAndView model = new ModelAndView();
        if (registType.equals("student")){
            Student student  = new Student();
            student.setStuUname(username);
            student.setStuPassword(password);
            student.setStuMobile(tel);
            studentService.addStudent(student);
            model.setViewName("login");
        }if (registType.equals("teacher")){
            Admin admin = new Admin();
            admin.setMobile(tel);
            admin.setUname(username);
            admin.setPassword(password);
            adminService.add(admin);
            model.setViewName("login");
        }
        return model;
    }
}

