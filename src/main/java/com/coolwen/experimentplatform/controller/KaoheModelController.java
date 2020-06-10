package com.coolwen.experimentplatform.controller;


import com.coolwen.experimentplatform.model.DTO.KaoHeModelStuDTO;
import com.coolwen.experimentplatform.model.DTO.KaoheModelAndExpInfoDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 2020/6/3
 * 王雨来
 * 新增总成绩考核中考试与模块总成绩权重初始化 /GreatestWeight
 */

/**
 * 对考核进行编辑管理
 * 列出所有模块/所有考核模块,将实验模块移入/移出考核,修改/添加考核信息
 * @author 王雨来
 * @version 2020/5/13 12:21
 */

@Controller
@RequestMapping("kaohemodel")
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
     * 列出所有模块
     * @param model 传值
     * @param pageNum 分页
     * @return 列表页面
     */
    @RequestMapping(value = "/allModule", method = RequestMethod.GET)
    public String loadAllModel(Model model,
                               @RequestParam(defaultValue = "0", required = true, value = "pageNum") Integer pageNum) {
        // 这是一个整数列表,用来存放所有的考核模块的实验模块id,在视图上用来判断此实验是否已经在考核中
        List<Integer> check = kaoheModelService.inKaoheList();
        model.addAttribute("checkList", check);

        //这个是所有的实验模块
        Page<ExpModel> a = expModelService.findModelList(pageNum);
        model.addAttribute("allKaohe", a);
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
        return "kaohe/allModule";
    }

    /**
     * 列出所有考核模块 以下均为相同内容,不再赘述
     * @param model 传值
     * @param pageNum 分页
     * @return 页面
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/checkModule", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(defaultValue = "0", required = true, value = "pageNum") Integer pageNum) throws JsonProcessingException {
        // 所有的考核模块
//        Pageable pageable = PageRequest.of(pageNum, 5);
//        Page<KaoheModel> page = kaoheModelService.findAll(pageable);
        Page<KaoheModelAndExpInfoDTO> page = kaoheModelService.findAllKaoheModelAndExpInfoDTO(pageNum);

        model.addAttribute("kaoheModelPageInfo", page);
        System.out.println("page:" + page.getTotalElements());

        //分页
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("json:" + mapper.writeValueAsString(page));
        return "kaohe/checkModule";
    }

    /**
     * 移入考核
     */
    @RequestMapping(value = {"/{mid}/moveIn"}, method = RequestMethod.GET)
    public String add(@PathVariable int mid, Model model) {
        // 获得此模块的信息
        ExpModel expModel = expModelService.findExpModelByID(mid);
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
//        u.setExperiment_name(expModel.getM_name());
//        u.setClass_hour(expModel.getClasshour());
        u.setM_order(moveIn.getM_order());
        u.setM_scale(moveIn.getM_scale());
//        u.setShiyan_Purpose(expModel.getPurpose());
//        u.setShiyan_Types(expModel.getM_type());
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
        // 当期限定
        // 表13 考核项目数增加
        expModelService.save(expModel);
        System.out.println(">>>>>>>>>>>>add");
        kaoheModelService.deleteMTestAnswerByMid(mid);
        return "redirect:/kaohemodel/allModule";
    }

    /**
     * 修改考核配置
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model) {
        System.out.println("id:>>>>>>>>>>>>>>>>>>>>>>>" + id);
        KaoheModelAndExpInfoDTO kaoheModelAndExpInfoDTO = kaoheModelService.findKaoheModelAndExpInfoDTOByKaoheid(id);
//        KaoheModel kaoheModel = kaoheModelService.findById(id);
//        System.out.println(kaoheModel.toString());
        model.addAttribute("kaohemodel", kaoheModelAndExpInfoDTO);

        return "/kaohe/kaoheupdate";
    }

    /**
     * 修改考核配置
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable int id, KaoheModel kaoheModel) {
        KaoheModel u = new KaoheModel();
        u = kaoheModelService.findById(id);
//        u.setClass_hour(kaoheModel.getClass_hour());
        u.setM_id(kaoheModel.getM_id());
//        u.setExperiment_name(kaoheModel.getExperiment_name());
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

        // 遍历当期需要参加考核的学生 ,查到这个学生的,这个考核模块的成绩
        // 更新考核额数

        // 获得此考核模块的,实验模块
        int mid = kaoheModelService.findById(id).getM_id();
        ExpModel expModel = expModelService.findExpModelByID(mid);
        // 将实验模块的考核状态设置为不考核
        expModel.setNeedKaohe(false);
        expModelService.save(expModel);

        //删除所有学生此模块的成绩
        kaoheModelService.deleteByMid(mid);
        // 删除此考核模块
        kaoheModelService.delete(id);

//        kaoHeModelScoreService.deleteAllByKaohemId(id);
        System.out.println("移出成功");
        return "redirect:/kaohemodel/checkModule";
    }



}

