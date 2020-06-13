package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.dao.StudentRepository;
import com.coolwen.experimentplatform.model.ClassModel;
import com.coolwen.experimentplatform.model.DTO.StuTotalScoreCurrentDTO;
import com.coolwen.experimentplatform.model.KaoHeModelScore;
import com.coolwen.experimentplatform.model.KaoheModel;
import com.coolwen.experimentplatform.model.TotalScoreCurrent;
import com.coolwen.experimentplatform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

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
        Page<StuTotalScoreCurrentDTO> totalScore= studentService.listStuTotalScoreCurrentDTO(pageNum,select_orderId);
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

}
