package com.coolwen.experimentplatform.controller;


import com.coolwen.experimentplatform.model.SetInfo;
import com.coolwen.experimentplatform.service.SetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/setinfo")
public class SetInfoController {

    @Autowired
    SetInfoService setInfoService;
    //进入设置页面
    @GetMapping(value = "/add")
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
        return "/shouye/aboutUs";
    }

    @ResponseBody
    @PostMapping(value = "/add")
    public String add(SetInfo setInfo){
        setInfo.setId(1);
        SetInfo setInfo1 = setInfoService.findById(1);
        setInfo.setSet_platstep(setInfo1.getSet_platstep());
        setInfo.setSet_rotateimg(setInfo1.getSet_rotateimg());
        setInfoService.add(setInfo);
        return "添加成功";
    }
}
