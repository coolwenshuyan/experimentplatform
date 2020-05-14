package com.coolwen.experimentplatform.controller;


import com.coolwen.experimentplatform.dao.KaoheModelRepository;
import com.coolwen.experimentplatform.model.ExpModel;
import com.coolwen.experimentplatform.model.KaoheModel;
import com.coolwen.experimentplatform.service.ExpModelService;
import com.coolwen.experimentplatform.service.KaoheModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/kaohemodel")
public class KaoheModelController {
    @Autowired
    private KaoheModelService kaoheModelService;
    @Autowired
    private ExpModelService expModelService;


    /**
     *所有模块
     */
    @RequestMapping(value = "/allModule",method = RequestMethod.GET)
    public String loadAllModel(Model model,@RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum) {
//        Pageable pageable = PageRequest.of(pageNum, 5);
//        ExpModel expModel = expModelService.findModelList(pageNum);
//        Page<KaoheModel> page = kaoheModelService.findAll(pageable);
//        model.addAttribute("allKaohe", page);
        model.addAttribute("allKaohe",expModelService.findModelList(pageNum));
        return "kaohe/allModule";
    }

    /**
     *所有考核模块
     */
    @RequestMapping(value = "/checkModule",method = RequestMethod.GET)
    public String list(Model model,@RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum){
        Pageable pageable = PageRequest.of(pageNum,5);
        Page<KaoheModel> page = kaoheModelService.findAll(pageable);
        model.addAttribute("kaoheModelPageInfo",page);
        return "kaohe/checkModule";
    }

    /**
     *移入考核
    */
    @RequestMapping(value = {"/{mid}/moveIn"},method = RequestMethod.GET)
    public String add(@PathVariable int mid,Model model){
        ExpModel expModel = expModelService.findExpModelByID(mid);
        model.addAttribute("expInfo",expModel);
        model.addAttribute("moveIn",new KaoheModel());
        return "kaohe/moveIn";
    }

    /**
     *移入考核
     */
    @RequestMapping(value = {"/{mid}/moveIn"},method = RequestMethod.POST)
    public String add(@PathVariable int mid,KaoheModel moveIn){
        System.out.println(">>>>>>>>>>>>"+moveIn);
        KaoheModel u = new KaoheModel();
        ExpModel expModel = expModelService.findExpModelByID(mid);
        u.setM_id(expModel.getM_id());
        u.setExperiment_name(expModel.getM_name());
        u.setClass_hour(expModel.getClasshour());
        u.setM_order(moveIn.getM_order());
        u.setM_scale(moveIn.getM_scale());
        u.setShiyan_Purpose(expModel.getPurpose());
        u.setShiyan_Types(expModel.getM_type());
        u.setM_test_baifenbi(moveIn.getM_test_baifenbi());
        u.setM_report_baifenbi(moveIn.getM_report_baifenbi());
        System.out.println(u);
        kaoheModelService.add(u);
        System.out.println(">>>>>>>>>>>>add");
        return "redirect:/kaohemodel/allModule";
    }

    /**
     *修改考核配置
     */
    @RequestMapping(value = "/{id}/update",method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+id);
        KaoheModel kaoheModel = kaoheModelService.findById(id);
        System.out.println(kaoheModel.toString());
        model.addAttribute("kaohemodel",kaoheModel);

        return "/kaohe/kaoheupdate";
    }
    /**
     *修改考核配置
     */
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
        return "redirect:/kaohemodel/checkModule";
    }

    /**
     *移出考核
     */
    @RequestMapping(value = "/{id}/delete",method = RequestMethod.GET)
    public String delete(@PathVariable int id){
        kaoheModelService.delete(id);
        System.out.println("移出成功");
        return "redirect:/kaohemodel/checkModule";
    }


}

