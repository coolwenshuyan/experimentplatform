package com.coolwen.experimentplatform.controller;


import com.coolwen.experimentplatform.model.ExpModel;
import com.coolwen.experimentplatform.model.KaoHeModelScore;
import com.coolwen.experimentplatform.model.KaoheModel;
import com.coolwen.experimentplatform.model.Student;
import com.coolwen.experimentplatform.service.ExpModelService;
import com.coolwen.experimentplatform.service.KaoHeModelScoreService;
import com.coolwen.experimentplatform.service.KaoheModelService;
import com.coolwen.experimentplatform.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private ExpModelService expModelService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private KaoHeModelScoreService kaoHeModelScoreService;


    /**
     * 所有模块
     */
    @RequestMapping(value = "/allModule", method = RequestMethod.GET)
    public String loadAllModel(Model model,
                               @RequestParam(defaultValue = "0", required = true, value = "pageNum") Integer pageNum) {
//    public String loadAllModel(Model model) {

        List<Integer> check = kaoheModelService.inKaoheList();
        model.addAttribute("checkList", check);
        Page<ExpModel> a = expModelService.findModelList(pageNum);
//        List <ExpModels> b = null;
//        for (ExpModel x:a){
//            ExpModels c = new ExpModels(x.getM_id(),
//                    x.getM_name(),
//                    x.getM_manager(),
//                    x.getM_type(),
//                    x.getClasshour(),
//                    x.getImageurl(),
//                    x.getIntroduce(),
//                    x.getPurpose(),
//                    x.getPrinciple(),
//                    x.getM_content(),
//                    x.getM_edata_intro(),
//                    x.getM_edataurl(),
//                    x.getM_step(),
//                    x.getM_inurl());
//
//            if (check.contains(x.getM_id())){
//                c.setStatus(true);
//            }else {
//                c.setStatus(false);
//            }
//            b.add(c);
//        }
        model.addAttribute("allKaohe", a);
        return "kaohe/allModule";
    }

    /**
     * 所有考核模块
     */
    @RequestMapping(value = "/checkModule", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(defaultValue = "0", required = true, value = "pageNum") Integer pageNum) throws JsonProcessingException {
        Pageable pageable = PageRequest.of(pageNum, 5);
        Page<KaoheModel> page = kaoheModelService.findAll(pageable);
        model.addAttribute("kaoheModelPageInfo", page);
        System.out.println("page:" + page.getTotalElements());
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("json:" + mapper.writeValueAsString(page));
        return "kaohe/checkModule";
    }

    /**
     * 移入考核
     */
    @RequestMapping(value = {"/{mid}/moveIn"}, method = RequestMethod.GET)
    public String add(@PathVariable int mid, Model model) {
        ExpModel expModel = expModelService.findExpModelByID(mid);
        expModelService.findExpModelByID(mid);
        model.addAttribute("expInfo", expModel);
        model.addAttribute("moveIn", new KaoheModel());
        return "kaohe/moveIn";
    }

    /**
     * 移入考核
     */
    @RequestMapping(value = {"/{mid}/moveIn"}, method = RequestMethod.POST)
    public String add(@PathVariable int mid, KaoheModel moveIn) {
        System.out.println(">>>>>>>>>>>>" + moveIn);
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
        u.setKaohe_baifenbi(moveIn.getKaohe_baifenbi());
        u.setTest_baifenbi(moveIn.getTest_baifenbi());
        System.out.println(u);
        kaoheModelService.add(u);
        expModel.setNeedKaohe(true);
        for (Student i : studentService.findAll()) {
            kaoHeModelScoreService.add(new KaoHeModelScore(u.getId(), i.getId(), 0, 0, u.getM_order(), u.getM_scale()));
        }
        expModelService.save(expModel);
        System.out.println(">>>>>>>>>>>>add");
        return "redirect:/kaohemodel/allModule";
    }

    /**
     * 修改考核配置
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>" + id);
        KaoheModel kaoheModel = kaoheModelService.findById(id);
        System.out.println(kaoheModel.toString());
        model.addAttribute("kaohemodel", kaoheModel);

        return "/kaohe/kaoheupdate";
    }

    /**
     * 修改考核配置
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable int id, KaoheModel kaoheModel) {
        KaoheModel u = new KaoheModel();
        u = kaoheModelService.findById(id);
        u.setClass_hour(kaoheModel.getClass_hour());
        u.setM_id(kaoheModel.getM_id());
        u.setExperiment_name(kaoheModel.getExperiment_name());
        u.setM_order(kaoheModel.getM_order());
        u.setM_scale(kaoheModel.getM_scale());
        u.setM_test_baifenbi(kaoheModel.getM_test_baifenbi());
        u.setM_report_baifenbi(kaoheModel.getM_report_baifenbi());
        u.setKaohe_baifenbi(kaoheModel.getKaohe_baifenbi());
        u.setTest_baifenbi(kaoheModel.getTest_baifenbi());
        kaoheModelService.add(u);
        return "redirect:/kaohemodel/checkModule";
    }

    /**
     * 移出考核
     */
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {

        int mid = kaoheModelService.findById(id).getM_id();
        ExpModel expModel = expModelService.findExpModelByID(mid);
        expModel.setNeedKaohe(false);
        expModelService.save(expModel);
        kaoheModelService.delete(id);
//        kaoHeModelScoreService.deleteAllByKaohemId(id);
        System.out.println("移出成功");
        return "redirect:/kaohemodel/checkModule";
    }


}

