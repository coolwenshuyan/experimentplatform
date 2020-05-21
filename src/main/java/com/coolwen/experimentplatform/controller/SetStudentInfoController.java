package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.kit.ShiroKit;
import com.coolwen.experimentplatform.model.Student;
import com.coolwen.experimentplatform.service.SetStudentInfoService;
import com.coolwen.experimentplatform.service.SetStudentInfoServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/setstudentinfo")
public class SetStudentInfoController {

    @Autowired
    SetStudentInfoService setStudentInfoService;
    @Autowired
    SetStudentInfoServiceImpl setStudentInfoServiceimpl;

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
    public String update(Student student) {
        Student student1 = (Student) SecurityUtils.getSubject().getPrincipal();
        student.setId(student1.getId());
        student.setStuPassword(ShiroKit.md5(student.getStuPassword(),student.getStuUname()));
        student.setClassId(student1.getClassId());
        student.setStuCheckstate(student1.isStuCheckstate());
        student.setStuIsinschool(student1.isStuIsinschool());
        student.setStuXuehao(student1.getStuXuehao());
        setStudentInfoService.add(student);
        return "redirect:/login";
    }
}
