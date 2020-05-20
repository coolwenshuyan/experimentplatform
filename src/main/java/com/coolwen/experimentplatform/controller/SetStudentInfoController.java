//package com.coolwen.experimentplatform.controller;
//
//
//import com.coolwen.experimentplatform.model.Student;
//import com.coolwen.experimentplatform.service.SetStudentInfoService;
//import com.coolwen.experimentplatform.service.SetStudentInfoServiceImpl;
//import org.apache.shiro.session.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping(value = "/setstudentinfo")
//public class SetStudentInfoController {
//
//    @Autowired
//    SetStudentInfoService setStudentInfoService;
//    @Autowired
//    SetStudentInfoServiceImpl setStudentInfoServiceimpl;
//
//    @GetMapping(value = "/update")
//    public String update(Model model, Session session){
//        Student student = (Student) session.getAttribute("student");
//        String classname = setStudentInfoService.findByClassName();
////        Student student = setStudentInfoService.findById();
//        model.addAttribute("student",student);
//        model.addAttribute("classname",classname);
//        return "home_shiyan/change";
//    }
//
//
//    @PostMapping(value = "/update")
//    public String update(Student student){
//        setStudentInfoService.add(student);
//        return "redirect:/setstudentinfo";
//    }
//}
