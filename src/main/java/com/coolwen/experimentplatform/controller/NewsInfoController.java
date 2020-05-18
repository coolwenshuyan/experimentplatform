package com.coolwen.experimentplatform.controller;


import com.coolwen.experimentplatform.dao.ExpModelRepository;
import com.coolwen.experimentplatform.dao.NewsInfoRepository;
import com.coolwen.experimentplatform.dao.StudentRepository;
import com.coolwen.experimentplatform.dao.TeacherRepository;
import com.coolwen.experimentplatform.model.ExpModel;
import com.coolwen.experimentplatform.model.NewsInfo;
import com.coolwen.experimentplatform.model.SetInfo;
import com.coolwen.experimentplatform.service.NewsInfoService;
import com.coolwen.experimentplatform.service.SetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@Controller
@RequestMapping(value = "/newsinfo")
public class NewsInfoController {

    @Autowired
    NewsInfoRepository newsInfoRepository;
    @Autowired
    ExpModelRepository expModelRepository;
    @Autowired
    NewsInfoService newsInfoService;
    @Autowired
    SetInfoService setInfoService;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;

    //进入前端展示页面
    @GetMapping(value = "/newslist")
    public String newslist(Model model,@RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum){
        //公告信息
        Pageable pageable = PageRequest.of(pageNum,10);
        Page<NewsInfo> page = newsInfoRepository.findAllorderby(pageable);
        model.addAttribute("newsPageInfo",page);
        //实验列表
        Pageable pageable1 = PageRequest.of(pageNum,9);
        Page<ExpModel> page1 = expModelRepository.findAllexp(pageable1);
        model.addAttribute("allexp",page1);
        //轮播展示
        SetInfo setInfo = setInfoService.findById(1);
        String ids = setInfo.getSet_rotateimg();
        String[] sid =ids.split(",");
        for (int i = 0; i < sid.length ; i++) {
//            String imgurl = setInfoService.findexpimg(Integer.parseInt(sid[i]));
            String imgurl = expModelRepository.findexpimg(Integer.parseInt(sid[i]));
            model.addAttribute("img"+String.valueOf(i),imgurl);
        }
        //平台统计数据
        //实验模块总数
        int modenum = (int) expModelRepository.count();
        model.addAttribute("modenum",modenum);
        System.out.println(modenum);
        //平台总用户数
        int studentnum = (int) studentRepository.count();
        int teachernum = (int) teacherRepository.count();
        model.addAttribute("usernum",studentnum+teachernum);
        //参与考核人数
        int studentmodel = newsInfoService.findAllmodelpeople();
        model.addAttribute("studentmodel",studentmodel);
        System.out.println(studentmodel);
        //通过考核人数
        int passpeople = newsInfoService.findAllPass();
        model.addAttribute("passpeople",passpeople);
        System.out.println(passpeople);
        return "home_page/index";
    }

    //点击公告，查看详情
    @GetMapping(value = "/{id}/noticeDetails")
    public String noticedetails(@PathVariable int id,Model model){
        NewsInfo newsInfo = newsInfoService.findById(id);
        model.addAttribute("newsInfo",newsInfo);
        return "home_page/noticeDetails";
    }


    //公告列表
    @GetMapping(value = "/list")
    public String list(Model model,@RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum){
        Pageable pageable = PageRequest.of(pageNum,3);
        Page<NewsInfo> page = newsInfoRepository.findAll(pageable);
        model.addAttribute("newsPageInfo",page);
        return "shouye/notice";
    }

    //进入公告添加
    @GetMapping(value = "/add")
    public String add(){
        return "shouye/notice_add";
    }

    //完成公告添加
    @PostMapping(value = "/add")
    public String add(NewsInfo newsInfo){
        newsInfo.setDic_datetime(new Date());
        newsInfo.setDic_num(0);
        newsInfoService.add(newsInfo);
        return "redirect:/newsinfo/list";
    }

    //进入公告修改
    @GetMapping(value = "/{id}/update")
    public String update(@PathVariable int id,Model model){
        NewsInfo newsInfo = newsInfoService.findById(id);
        model.addAttribute("newsInfo",newsInfo);
        return "shouye/notice_update";
    }

    //完成修改操作
    @PostMapping(value = "/{id}/update")
    public String update(@PathVariable int id, NewsInfo newsInfo){
        NewsInfo newsInfo1 = newsInfoService.findById(id);
        newsInfo.setId(id);
        newsInfo.setDic_datetime(newsInfo1.getDic_datetime());
        newsInfo.setDic_num(newsInfo1.getDic_num());
        newsInfoService.add(newsInfo);
        return "redirect:/newsinfo/list";
    }

    //公告删除
    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable int id){
        newsInfoService.delete(id);
        return "redirect:/newsinfo/list";
    }
}
