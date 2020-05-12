package com.coolwen.experimentplatform.controller;


import com.coolwen.experimentplatform.model.KaoheModel;
import com.coolwen.experimentplatform.service.KaoheModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/add")
public class KaoheModelController {
    @Autowired
    public KaoheModelService kaoheModelService;

    //添加
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){
        return "kaohemodel/addkaohe";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(int m_id,int m_order,float m_scale,float m_test_baifenbi,float m_report_baifenbi){
        KaoheModel u = new KaoheModel();
        u.setM_id(m_id);
        u.setM_order(m_order);
        u.setM_scale(m_scale);
        u.setM_test_baifenbi(m_test_baifenbi);
        u.setM_report_baifenbi(m_report_baifenbi);
        kaoheModelService.add(u);
        return "kaohemodel/addkaohe";
    }
    //修改
    @RequestMapping(value = "/{id}/update",method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model){
        KaoheModel kaoheModel = kaoheModelService.findById(id);
        model.addAttribute("k",kaoheModel);
        return " ";
    }
    @RequestMapping(value = "/{id}/update",method = RequestMethod.POST)
    public String update(@PathVariable int id,KaoheModel kaoheModel){
        KaoheModel u = new KaoheModel();
        u=kaoheModelService.findById(id);
        u.setM_id(kaoheModel.getM_id());
        u.setM_order(kaoheModel.getM_order());
        u.setM_scale(kaoheModel.getM_scale());
        u.setM_test_baifenbi(kaoheModel.getM_test_baifenbi());
        u.setM_report_baifenbi(kaoheModel.getM_report_baifenbi());
        kaoheModelService.add(u);
        return " k";
    }
}
