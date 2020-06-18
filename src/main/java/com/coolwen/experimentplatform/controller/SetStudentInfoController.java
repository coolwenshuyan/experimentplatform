package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.kit.ShiroKit;
import com.coolwen.experimentplatform.model.Student;
import com.coolwen.experimentplatform.service.SetStudentInfoService;
import com.coolwen.experimentplatform.service.SetStudentInfoServiceImpl;
import com.coolwen.experimentplatform.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "/setstudentinfo")
public class SetStudentInfoController {

    @Autowired
    SetStudentInfoService setStudentInfoService;
    @Autowired
    SetStudentInfoServiceImpl setStudentInfoServiceimpl;
    @Autowired
    StudentService studentService;

    @GetMapping(value = "/update")
    public String update(Model model) {
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        String classname = setStudentInfoService.findByClassName(student.getClassId());
        model.addAttribute("student", student);
        model.addAttribute("classname", classname);
        System.out.println(classname);
        return "home_shiyan/change";
    }

    @PostMapping(value = "/update")
    public String update(Student student,Model model,@RequestParam String pass){
        Student student3 = (Student) SecurityUtils.getSubject().getPrincipal();
        Student student1 = studentService.findByUname(student.getStuUname());
        if (pass.equals(student.getStuPassword())) {
            if (student3.getStuUname().equals(student.getStuUname())){
                System.out.println("通过");
            }
            else if (student1 != null) {
                System.out.println("不通过");
                model.addAttribute("msg1", "用户名已存在");
                return "home_shiyan/change";
            }
            Pattern p = Pattern.compile("^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$");
            Matcher m = p.matcher(student.getStuMobile());
            if (m.matches() != true){
                model.addAttribute("telmsg", "请输入11位数字");
                return "home_shiyan/change";
            }
            System.out.println("student:"+student);
            Student student2 = new Student();
            student2.setStuUname(student.getStuUname());
            student2.setStuName(student.getStuName());
            student2.setStuMobile(student.getStuMobile());
            student2.setId(student3.getId());
            student2.setStuPassword(ShiroKit.md5(student.getStuPassword(),student.getStuUname()));
            student2.setClassId(student3.getClassId());
            student2.setStuCheckstate(student3.isStuCheckstate());
            student2.setStuIsinschool(student3.isStuIsinschool());
            student2.setStuXuehao(student3.getStuXuehao());
            setStudentInfoService.add(student2);
            //退出登录
            SecurityUtils.getSubject().logout();
            return "redirect:/login";
        }
        model.addAttribute("msg3","两次输入密码不同");
        return "home_shiyan/change";
    }
}
