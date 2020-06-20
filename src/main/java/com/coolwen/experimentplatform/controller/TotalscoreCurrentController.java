package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.dao.StudentRepository;
import com.coolwen.experimentplatform.model.*;
import com.coolwen.experimentplatform.model.DTO.StuTotalScoreCurrentDTO;
import com.coolwen.experimentplatform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * 总成绩管理
 * 列出所有的成绩
 * @author 王雨来
 * @version 2020/5/13 12:21
 */

@Controller
@RequestMapping(value = "/totalscore")
public class TotalscoreCurrentController {

    @Autowired
    public TotalScoreCurrentService totalScoreCurrentService;

    @Autowired
    public StudentService studentService;

    @Autowired
    public KaoheModelService kaoheModelService;

    @Autowired
    public ClazzService clazzService;

    @Autowired
    public ExpModelService expModelService;

    @Autowired
    public KaoHeModelScoreService kaoHeModelScoreService;

    @Autowired
    public StudentService studentservice;


    @Autowired
    public TotalScorePassService totalScorePassService;
    @Autowired
    public ModuleTestAnswerStuService moduleTestAnswerStuService;
    @Autowired
    public ReportAnswerService reportAnswerService;


//    @GetMapping("/test")
//    public String hello() {
//        return "AllModel";
//    }
//
//    /**
//     *
//     *
//     */
//    @GetMapping("/list")
//    public String expModelList(Model model,@RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum){
//        model.addAttribute("page",totalScoreCurrentService.listTotalScoreCurrent());
//        return "totalscore/list";
//    }

//    @GetMapping(value = "/score_manage")
//    public String loadAllModel(Model model) {
////        Pageable pageable = PageRequest.of(pageNum, 5);
////        ExpModel expModel = expModelService.findModelList(pageNum);
////        Page<KaoheModel> page = kaoheModelService.findAll(pageable);
////        model.addAttribute("allKaohe", page);
////        List<Integer> check = kaoheModelService.inKaoheList();
////        model.addAttribute("allKaohe",expModelService.findModelList(pageNum));
////        model.addAttribute("checkList",check);
//
//        List<StudentTestScoreDTO> a = studentRepository.listStudentMTestAnswerDTO();
//        model.addAttribute("allInfo",a);
//        return "kaohe/score_manage";
//    }

    /**
     * 列出所有成绩
     * @param model 传值
     * @param pageNum 分页
     * @return 页面
     */
    @GetMapping("/list")
    public String expModelList(Model model,
                               @RequestParam(required = true, defaultValue = "")String select_orderId ,
                               @RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum){
        //从数据库得到所有的总成绩
        Page<StuTotalScoreCurrentDTO> totalScore = null;
        try {
            totalScore= studentService.listStuTotalScoreCurrentDTO(pageNum,select_orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("selectOrderId",select_orderId);
        //获得所有考核模块的列表
        List<KaoheModel> toGetBaiFenBi=kaoheModelService.findAll();

        List<ClassModel> classList = clazzService.findCurrentClass();
        model.addAttribute("classList",classList);

        //初始化 最后权重
        float kaoheBaifenbi = 0;
        float testBaifenbi = 0;
        if (toGetBaiFenBi.size()>0){
            kaoheBaifenbi=toGetBaiFenBi.get(0).getKaohe_baifenbi();
            testBaifenbi=toGetBaiFenBi.get(0).getTest_baifenbi();
        }
        model.addAttribute("pageTotalScore",totalScore);
        model.addAttribute("kaoheBaifenbi",kaoheBaifenbi);
        model.addAttribute("testBaifenbi",testBaifenbi);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+totalScore);
        return "kaohe/all_score";
    }

    @GetMapping("/{classId}/list")
    public String getTotalScoreCirrentByGroupId(Model model,
                                                @PathVariable int classId,
                                                @RequestParam(required = true, defaultValue = "")String select_orderId ,
                                                @RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum){
        //从数据库得到所有的总成绩
        Page<StuTotalScoreCurrentDTO> totalScore= studentService.listStuTotalScoreCurrentDTOByClassId(pageNum,select_orderId,classId);
        //获得所有考核模块的列表
        List<KaoheModel> toGetBaiFenBi=kaoheModelService.findAll();

        List<ClassModel> classList = clazzService.findCurrentClass();
        model.addAttribute("classList",classList);

        //初始化 最后权重
        float kaoheBaifenbi = 0;
        float testBaifenbi = 0;
        if (toGetBaiFenBi.size()>0){
            kaoheBaifenbi=toGetBaiFenBi.get(0).getKaohe_baifenbi();
            testBaifenbi=toGetBaiFenBi.get(0).getTest_baifenbi();
        }
        model.addAttribute("pageTotalScore",totalScore);
        model.addAttribute("kaoheBaifenbi",kaoheBaifenbi);
        model.addAttribute("testBaifenbi",testBaifenbi);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+totalScore);
        return "kaohe/all_score";
    }

    @PostMapping(value = "/GreatestWeight")
    public String GreatestWeight(@RequestParam(required = true,defaultValue = "0")float kaoheBaifenbi,
                                 @RequestParam(required = true,defaultValue = "0")float testBaifenbi){

        kaoheModelService.updateAllGreatestWeight(kaoheBaifenbi,testBaifenbi);
        System.out.println("设置所有"+kaoheBaifenbi+"++++++++++++++"+testBaifenbi);

        List<TotalScoreCurrent> totalScoreCurrents= totalScoreCurrentService.findAll();
        for (TotalScoreCurrent i :totalScoreCurrents){
            System.out.println(i);
            i.setTotalScore(i.getmTotalScore()*kaoheBaifenbi+i.getTestScore()*testBaifenbi);
            totalScoreCurrentService.update(i);
            System.out.println(i);
            System.out.println("----------------------------------------");
        }
        return "redirect:/totalscore/list";

    }


    @GetMapping("/GuHuaAll")
    public String GuHuaAll(Model model,
                               @RequestParam(required = true, defaultValue = "")String select_orderId ,
                               @RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum){

        //获得所有当期班级
        List<ClassModel> classList = clazzService.findCurrentClass();
        for (ClassModel i : classList){
            int id=i.getClassId();

            TotalScorePass totalScorePass = null;
            //初始化固化成绩信息
            String kaoheModuleName = "";
            String kaohe_mtestscore = "";
            String kaohe_mreportscore = "";
            String kaohe_mtestscore_baifengbi = "";
            String kaohe_mreportscore_baifengbi = "";
            String kaohe_mscale = "";
            float test_baifenbi = 0;
            float kaohe_baifenbi = 0;
            List<KaoheModel> kaoheModelList = kaoheModelService.findAll();
            //获得考核项目名字
            for (KaoheModel k : kaoheModelList){
                ExpModel expModel = expModelService.findExpModelsByKaoheMid(k.getM_id());
                kaoheModuleName += expModel.getM_name()+";";
                test_baifenbi = k.getTest_baifenbi();
                kaohe_baifenbi = k.getKaohe_baifenbi();
            }

            //拼接之后，如果有数据要去除最后一个分号，
            if(kaoheModuleName.length() > 0)
            {
                kaoheModuleName = kaoheModuleName.substring(0,kaoheModuleName.length()-1);
            }
            List<Student> studentList = studentservice.findStudentByClassId(id);
            for(Student s : studentList){
                //拼接之前要初始化
                kaohe_mtestscore = "";
                kaohe_mreportscore = "";
                kaohe_mtestscore_baifengbi = "";
                kaohe_mreportscore_baifengbi = "";
                kaohe_mscale = "";

                for(KaoheModel k : kaoheModelList){
                    //获取该班级下学生每个考核模块信息
                    KaoHeModelScore kaoHeModelScore = kaoHeModelScoreService.findKaoHeModelScoreByStuIdAndId(s.getId(),k.getId());
                    //进行成绩固化临时存储
                    kaohe_mtestscore += kaoHeModelScore.getmTestScore()+";";
                    kaohe_mreportscore += kaoHeModelScore.getmReportScore()+";";
                    kaohe_mtestscore_baifengbi += k.getM_test_baifenbi()+";";
                    kaohe_mreportscore_baifengbi += k.getM_report_baifenbi()+";";
                    kaohe_mscale += k.getM_scale()+";";
                }

                //拼接之后，如果有数据要去除最后一个分号
                if(kaohe_mtestscore.length() > 0)
                {
                    kaohe_mtestscore = kaohe_mtestscore.substring(0,kaohe_mtestscore.length()-1);
                    kaohe_mreportscore = kaohe_mreportscore.substring(0,kaohe_mreportscore.length()-1);
                    kaohe_mtestscore_baifengbi = kaohe_mtestscore_baifengbi.substring(0,kaohe_mtestscore_baifengbi.length()-1);
                    kaohe_mreportscore_baifengbi = kaohe_mreportscore_baifengbi.substring(0,kaohe_mreportscore_baifengbi.length()-1);
                    kaohe_mscale = kaohe_mscale.substring(0,kaohe_mscale.length()-1);
                }

                TotalScoreCurrent totalScoreCurrent = totalScoreCurrentService.findTotalScoreCurrentByStuId(s.getId());
                //进行成绩固化操作
                totalScorePass = new TotalScorePass();
                totalScorePass.setStuId(s.getId());
                totalScorePass.setKaoheNum(kaoheModelList.size());
                totalScorePass.setKaoheName(kaoheModuleName);
                totalScorePass.setKaoheMtestscore(kaohe_mtestscore);
                totalScorePass.setKaoheMreportscore(kaohe_mreportscore);
                totalScorePass.setKaoheMtestscoreBaifengbi(kaohe_mtestscore_baifengbi);
                totalScorePass.setKaoheMreportscoreBaifengbi(kaohe_mreportscore_baifengbi);
                totalScorePass.setKaoheMscale(kaohe_mscale);
                totalScorePass.setmTotalScore(totalScoreCurrent.getmTotalScore());
                totalScorePass.setTestScore(totalScoreCurrent.getTestScore());
                totalScorePass.setTestBaifenbi(test_baifenbi);
                totalScorePass.setKaoheBaifenbi(kaohe_baifenbi);
                totalScorePass.setTotalScore(totalScoreCurrent.getTotalScore());
                totalScorePass.setFinalDatetime(new Date());
                totalScorePassService.save(totalScorePass);
                //删除学生模块回答，报告回答，考核成绩表，以及当期总评成绩表
                moduleTestAnswerStuService.deleteModuleTestAnswerStuByStuId(s.getId());
                reportAnswerService.deleteReportAnswerByStuId(s.getId());
                kaoHeModelScoreService.deleteKaoheModuleScoreByStuId(s.getId());
                totalScoreCurrentService.deleteTotalScoreCurrentByStuId(s.getId());
            }

            //更新班级状态为往期
            i.setClassIscurrent(true);
            clazzService.saveClazz(i);
        }




        return "redirect:/passTotalscore/list";
    }
}
