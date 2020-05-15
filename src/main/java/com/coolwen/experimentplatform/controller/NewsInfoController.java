package com.coolwen.experimentplatform.controller;


import com.coolwen.experimentplatform.dao.NewsInfoRepository;
import com.coolwen.experimentplatform.model.Effect;
import com.coolwen.experimentplatform.model.NewsInfo;
import com.coolwen.experimentplatform.service.NewsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "/newsinfo")
public class NewsInfoController {

    @Autowired
    NewsInfoRepository newsInfoRepository;

    @Autowired
    NewsInfoService newsInfoService;


    @GetMapping(value = "/list")
    public String list(Model model,@RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum){
        Pageable pageable = PageRequest.of(pageNum,3);
        Page<NewsInfo> page = newsInfoRepository.findAll(pageable);
        model.addAttribute("newsPageInfo",page);
        return "shouye/notice";
    }

    @GetMapping(value = "/add")
    public String add(){
        return "shouye/notice_add";
    }

    @PostMapping(value = "/add")
    public String add(NewsInfo newsInfo){
        newsInfo.setDic_datetime(new Date());
        newsInfo.setDic_num(0);
        newsInfoService.add(newsInfo);
        return "redirect:/newsinfo/list";
    }

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

    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable int id){
        newsInfoService.delete(id);
        return "redirect:/newsinfo/list";
    }
}
