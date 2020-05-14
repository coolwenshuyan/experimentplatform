package com.coolwen.experimentplatform.controller;


import com.coolwen.experimentplatform.dao.KaoheModelRepository;
import com.coolwen.experimentplatform.model.KaoheModel;
import com.coolwen.experimentplatform.service.KaoheModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/kaohemodel")
public class KaoheModelController {
    @Autowired
    private KaoheModelService kaoheModelService;
    @Autowired
    private KaoheModelRepository kaoheModelRepository;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model,@RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum){
//        kaoheModelService.
        Pageable pageable = PageRequest.of(pageNum,5);
        Page<KaoheModel> page = kaoheModelRepository.findAll(pageable);
        model.addAttribute("kaoheModelPageInfo",page);
        return "kaohe/check_Module";
    }

    //添加
    @RequestMapping(value = {"/add"},method = RequestMethod.GET)
    public String add(){
        return "kaohe/Examination";
    }
//    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
//    public String login() {
//        return "login";
//    }

    @RequestMapping(value = {"/add"},method = RequestMethod.POST)
    public String add(int m_id,int m_order,float m_scale,float m_test_baifenbi,
                      float m_report_baifenbi,String shiyan_Purpose,String shiyan_Types,String Experiment_name,int class_hour){
        KaoheModel u = new KaoheModel();
        u.setM_id(m_id);
        u.setExperiment_name(Experiment_name);
        u.setClass_hour(class_hour);
        u.setM_order(m_order);
        u.setM_scale(m_scale);
        u.setShiyan_Purpose(shiyan_Purpose);
        u.setShiyan_Types(shiyan_Types);
        u.setM_test_baifenbi(m_test_baifenbi);
        u.setM_report_baifenbi(m_report_baifenbi);
        kaoheModelService.add(u);
        return "redirect:/kaohemodel/list";
    }
    //修改
    @RequestMapping(value = "/{id}/update",method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+id);
        KaoheModel kaoheModel = kaoheModelService.findById(id);
        System.out.println(kaoheModel.toString());
        model.addAttribute("kaohemodel",kaoheModel);

        return "/kaohe/kaoheupdate";
    }
    @RequestMapping(value = "/{id}/update",method = RequestMethod.POST)
    public String update(@PathVariable int id,KaoheModel kaoheModel){
        KaoheModel u = new KaoheModel();
        u=kaoheModelService.findById(id);
        u.setClass_hour(kaoheModel.getClass_hour());
        u.setM_id(kaoheModel.getM_id());
        u.setExperiment_name(kaoheModel.getExperiment_name());
        u.setM_order(kaoheModel.getM_order());
        u.setM_scale(kaoheModel.getM_scale());
        u.setM_test_baifenbi(kaoheModel.getM_test_baifenbi());
        u.setM_report_baifenbi(kaoheModel.getM_report_baifenbi());
        kaoheModelService.add(u);
        return "redirect:/kaohemodel/list";
    }
    //删除
    @RequestMapping(value = "/{id}/delete",method = RequestMethod.GET)
    public String delete(@PathVariable int id){
        kaoheModelService.delete(id);
        System.out.println("删除成功");
        return "redirect:/kaohemodel/list";
    }

    @RequestMapping(value = "/loadAllModel",method = RequestMethod.GET)
    public String loadAllModel(Model model,@RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 5);
        Page<KaoheModel> page = kaoheModelRepository.findAll(pageable);
        model.addAttribute("kaoheModelPageInfo", page);
        List<KaoheModel> kaohelist = kaoheModelService.listKaoheModel();
        model.addAttribute("allKaohe", kaohelist);
        return "kaohe/allModel";
    }
}

