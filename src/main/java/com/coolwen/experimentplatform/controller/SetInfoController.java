package com.coolwen.experimentplatform.controller;


import com.coolwen.experimentplatform.dao.TeacherRepository;
import com.coolwen.experimentplatform.model.SetInfo;
import com.coolwen.experimentplatform.model.Teacher;
import com.coolwen.experimentplatform.service.SetInfoService;
import com.google.inject.internal.util.$SourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/setinfo")
public class SetInfoController {

    @Autowired
    SetInfoService setInfoService;
    @Autowired
    TeacherRepository teacherRepository;

    //前端页面平台概况
    @GetMapping(value = "/situation")
    public String about(Model model){
        SetInfo setInfo = setInfoService.findById(1);
        model.addAttribute("setInfo",setInfo);
        return "home_page/situation";
    }


    //前端实验仿真介绍
    @GetMapping(value = "/jiesao")
    public String jiesao(Model model){
        SetInfo setInfo = setInfoService.findById(1);
        model.addAttribute("setInfo",setInfo);
        return "home_shiyan/jieshao";
    }

    //前端实验关于我们
    @GetMapping(value = "/aboutus")
    public String aboutus(Model model, @RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum){
        Pageable pageable = PageRequest.of(pageNum,100);
        Page<Teacher> page = teacherRepository.findAll(pageable);
        SetInfo setInfo = setInfoService.findById(1);
        model.addAttribute("setInfo",setInfo);
        model.addAttribute("teacherPageInfo",page);
        return "home_shiyan/team";
    }



    //进入平台概况设置页面
    @GetMapping(value = "/addus")
    public String addus(Model model){
        SetInfo setInfo = setInfoService.findById(1);
        if (setInfo==null){
            SetInfo setInfo1 = new SetInfo();
            setInfo1.setSet_aboutus("无数据");
            setInfo1.setSet_platintro("无数据");
            setInfo1.setSet_platstep("无数据");
            setInfo1.setSet_rotateimg("无数据");
            setInfoService.add(setInfo1);
            model.addAttribute("setInfo",setInfo1);
            return "/shouye/aboutUs";
        }
        model.addAttribute("setInfo",setInfo);
        return "/shouye/aboutUs";
    }

    //完成设置添加
    @ResponseBody
    @PostMapping(value = "/addus")
    public String addus(SetInfo setInfo){
        setInfo.setId(1);
        SetInfo setInfo1 = setInfoService.findById(1);
        setInfo.setSet_platintro(setInfo1.getSet_platintro());
        setInfo.setSet_platstep(setInfo1.getSet_platstep());
        setInfo.setSet_rotateimg(setInfo1.getSet_rotateimg());
        setInfoService.add(setInfo);
        return "添加成功";
    }

    //进入平台概况设置页面
    @GetMapping(value = "/addplat")
    public String add(Model model){
        SetInfo setInfo = setInfoService.findById(1);
        if (setInfo==null){
            SetInfo setInfo1 = new SetInfo();
            setInfo1.setSet_aboutus("无数据");
            setInfo1.setSet_platintro("无数据");
            setInfo1.setSet_platstep("无数据");
            setInfo1.setSet_rotateimg("无数据");
            setInfoService.add(setInfo1);
            model.addAttribute("setInfo",setInfo1);
            return "/shouye/aboutUs";
        }
        model.addAttribute("setInfo",setInfo);
        return "/shouye/aboutPlatform";
    }

    //完成设置添加
    @ResponseBody
    @PostMapping(value = "/addplat")
    public String add(SetInfo setInfo){
        setInfo.setId(1);
        SetInfo setInfo1 = setInfoService.findById(1);
        setInfo.setSet_aboutus(setInfo1.getSet_aboutus());
        setInfo.setSet_rotateimg(setInfo1.getSet_rotateimg());
        setInfoService.add(setInfo);
        return "添加成功";
    }


    //进入设置轮播页面
    @GetMapping(value = "/lunbo")
    public String addlunbo(Model model) {
        SetInfo setInfo = setInfoService.findById(1);
        if (setInfo == null) {
            SetInfo setInfo1 = new SetInfo();
            setInfo1.setSet_aboutus("无数据");
            setInfo1.setSet_platintro("无数据");
            setInfo1.setSet_platstep("无数据");
            setInfo1.setSet_rotateimg("无数据");
            setInfoService.add(setInfo1);
            model.addAttribute("setInfo", setInfo1);
            return "shouye/lunbo";
        }
        String ids = setInfo.getSet_rotateimg();
        String[] sid =ids.split(",");
        model.addAttribute("setInfo",setInfo);
        model.addAttribute("id1",sid[0]);
        model.addAttribute("id2",sid[1]);
        model.addAttribute("id3",sid[2]);
        model.addAttribute("id4",sid[3]);
        return "shouye/lunbo";
    }

    @ResponseBody
    @PostMapping(value = "/lunbo")
    public String addlunbo(String id1,String id2,String id3,String id4){
        SetInfo setInfo = new SetInfo();
        setInfo.setId(1);
        SetInfo setInfo1 = setInfoService.findById(1);
        setInfo.setSet_platintro(setInfo1.getSet_platintro());
        setInfo.setSet_platstep(setInfo1.getSet_platstep());
        setInfo.setSet_aboutus(setInfo1.getSet_aboutus());
        setInfo.setSet_rotateimg(id1+","+id2+","+id3+","+id4);
        setInfoService.add(setInfo);
        return "添加成功";
    }

}
